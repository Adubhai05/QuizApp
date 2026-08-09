package quiz.app;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Quiz extends JFrame implements ActionListener {

    private final String playerName;
    private final Topic selectedTopic;
    private List<Question> questions;

    private static final int TOTAL_TIME = 600; // 10 minutes in seconds
    private int timeLeft = TOTAL_TIME;
    private Timer countdownTimer;
    private boolean submitted = false;
    private int finalScore = 0;

    private JLabel timerLabel, scoreLabel;
    private JButton submit, back;

    private ButtonGroup[] questionGroups;
    private JRadioButton[][] optionButtons; // [questionIndex][0=A,1=B,2=C,3=D]
    private String[] userAnswers; // "A"/"B"/"C"/"D" or null if unanswered

    Quiz(String playerName, Topic selectedTopic) {
        this.playerName = playerName;
        this.selectedTopic = selectedTopic;

        questions = QuestionDAO.getQuestionsByTopic(selectedTopic.getTopicId());

        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No questions found for this topic yet.",
                    "No Questions", JOptionPane.WARNING_MESSAGE);
            dispose();
            new TopicSelection(playerName);
            return;
        }

        userAnswers = new String[questions.size()];

        // ---------------------------------------- BACKGROUND PANEL ---------------------------------------------

        JPanel background = new BackgroundPanel();
        background.setLayout(null);
        setContentPane(background);

        // ---------------------------------------- HEADER ----------------------------------------------------

        JLabel heading = new JLabel(selectedTopic.getTopicName() + " Quiz");
        heading.setBounds(50, 30, 500, 40);
        heading.setFont(new Font("Segoe Print", Font.BOLD, 26));
        heading.setForeground(new Color(30, 60, 110));
        background.add(heading);

        timerLabel = new JLabel();
        timerLabel.setBounds(600, 35, 150, 30);
        timerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        timerLabel.setForeground(new Color(219, 68, 59));
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        background.add(timerLabel);

        scoreLabel = new JLabel("");
        scoreLabel.setBounds(50, 555, 400, 25);
        scoreLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        scoreLabel.setForeground(new Color(30, 60, 110));
        background.add(scoreLabel);

        // ---------------------------------------- QUESTIONS PANEL (scrollable) --------------------------------

        JPanel questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));
        questionsPanel.setBackground(Color.WHITE);

        questionGroups = new ButtonGroup[questions.size()];
        optionButtons = new JRadioButton[questions.size()][4];

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            final int qIndex = i;

            JPanel qBlock = new JPanel();
            qBlock.setLayout(new BoxLayout(qBlock, BoxLayout.Y_AXIS));
            qBlock.setBackground(Color.WHITE);
            qBlock.setBorder(BorderFactory.createCompoundBorder(
                    new MatteBorder(0, 0, 1, 0, new Color(220, 222, 228)),
                    new EmptyBorder(15, 10, 15, 10)
            ));
            qBlock.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel qText = new JLabel("<html>" + (i + 1) + ". " + q.getQuestionText() + "</html>");
            qText.setFont(new Font("Segoe UI", Font.BOLD, 15));
            qText.setForeground(new Color(30, 60, 110));
            qText.setAlignmentX(Component.LEFT_ALIGNMENT);
            qBlock.add(qText);
            qBlock.add(Box.createRigidArea(new Dimension(0, 8)));

            ButtonGroup group = new ButtonGroup();
            questionGroups[i] = group;

            String[] optionTexts = { q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD() };
            String[] optionLabels = { "A", "B", "C", "D" };

            for (int j = 0; j < 4; j++) {
                final String label = optionLabels[j];
                JRadioButton opt = new JRadioButton(optionLabels[j] + ") " + optionTexts[j]);
                opt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                opt.setForeground(new Color(40, 40, 40));
                opt.setBackground(Color.WHITE);
                opt.setFocusPainted(false);
                opt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                opt.setAlignmentX(Component.LEFT_ALIGNMENT);
                opt.addActionListener(e -> userAnswers[qIndex] = label);
                group.add(opt);
                optionButtons[i][j] = opt;
                qBlock.add(opt);
            }

            questionsPanel.add(qBlock);
        }

        JScrollPane scrollPane = new JScrollPane(questionsPanel);
        scrollPane.setBounds(50, 85, 700, 450);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(new LineBorder(new Color(210, 213, 220), 1, true));
        background.add(scrollPane);

        // ---------------------------------------- BUTTONS ----------------------------------------------------

        back = new JButton("Back");
        back.setBounds(490, 550, 120, 38);
        back.setFont(new Font("Segoe UI", Font.BOLD, 13));
        back.setForeground(new Color(59, 91, 219));
        back.setBackground(Color.WHITE);
        back.setBorder(new LineBorder(new Color(59, 91, 219), 1, true));
        back.setFocusPainted(false);
        back.addActionListener(this);
        background.add(back);

        submit = new JButton("Submit");
        submit.setBounds(630, 550, 120, 38);
        submit.setFont(new Font("Segoe UI", Font.BOLD, 13));
        submit.setForeground(Color.WHITE);
        submit.setBackground(new Color(59, 91, 219));
        submit.setFocusPainted(false);
        submit.setBorderPainted(false);
        submit.addActionListener(this);
        background.add(submit);

        setSize(800, 650);
        setLocation(350, 100);
        setResizable(false);
        setVisible(true);

        startTimer();
    }

    private void startTimer() {
        timeLeft = TOTAL_TIME;
        updateTimerLabel();

        countdownTimer = new Timer(1000, e -> {
            timeLeft--;
            updateTimerLabel();
            if (timeLeft <= 0) {
                countdownTimer.stop();
                lockAllOptions();
                if (!submitted) {
                    calculateScore();
                    goToResult();
                }
            }
        });
        countdownTimer.start();
    }

    private void updateTimerLabel() {
        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }

    private void lockAllOptions() {
        for (int i = 0; i < optionButtons.length; i++) {
            for (int j = 0; j < 4; j++) {
                optionButtons[i][j].setEnabled(false);
            }
        }
    }

    private void calculateScore() {
        submitted = true;
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            String selected = userAnswers[i];

            if (selected != null && selected.equalsIgnoreCase(q.getCorrectOption())) {
                score++;
            }
        }

        lockAllOptions();
        finalScore = score;
        scoreLabel.setText("Score: " + score + " / " + questions.size());
    }

    private void goToResult() {
        if (countdownTimer != null) countdownTimer.stop();
        dispose();
        new Result(playerName, selectedTopic, questions, userAnswers, finalScore);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if (!submitted) {
                if (countdownTimer != null) countdownTimer.stop();
                calculateScore();
            }
            goToResult();
        } else if (e.getSource() == back) {
            if (countdownTimer != null) countdownTimer.stop();
            dispose();
            new TopicSelection(playerName);
        }
    }

    // ---------------------------------------- CUSTOM BACKGROUND ----------------------------------------------

    private static class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            Color powder = new Color(205, 226, 251);
            Color nearWhite = new Color(250, 251, 253);
            Color navy = new Color(30, 60, 110);
            Color accent = new Color(59, 91, 219);

            GradientPaint gp = new GradientPaint(0, 0, powder, 0, h * 0.75f, nearWhite);
            g2.setPaint(gp);
            g2.fillRect(0, 0, w, h);

            g2.setStroke(new BasicStroke(2f));
            int[] radiiTR = {260, 210, 160, 110};
            int cx1 = w - 60, cy1 = 40;
            for (int i = 0; i < radiiTR.length; i++) {
                int alpha = Math.max(16 - i * 3, 4);
                g2.setColor(new Color(navy.getRed(), navy.getGreen(), navy.getBlue(), alpha));
                int r = radiiTR[i];
                g2.drawOval(cx1 - r, cy1 - r, r * 2, r * 2);
            }

            int[] radiiBL = {220, 170, 120};
            int cx2 = 40, cy2 = h - 30;
            for (int i = 0; i < radiiBL.length; i++) {
                int alpha = Math.max(14 - i * 3, 4);
                g2.setColor(new Color(accent.getRed(), accent.getGreen(), accent.getBlue(), alpha));
                int r = radiiBL[i];
                g2.drawOval(cx2 - r, cy2 - r, r * 2, r * 2);
            }

            g2.setColor(new Color(navy.getRed(), navy.getGreen(), navy.getBlue(), 10));
            for (int gy = 0; gy < h; gy += 26) {
                for (int gx = 0; gx < w; gx += 26) {
                    g2.fillOval(gx - 1, gy - 1, 2, 2);
                }
            }

            g2.setColor(navy);
            g2.fillRect(0, 0, w, 5);
            g2.setColor(accent);
            g2.fillRect(0, 5, w, 2);

            g2.setColor(accent);
            g2.fillRect(0, h - 4, w, 2);
            g2.setColor(navy);
            g2.fillRect(0, h - 2, w, 2);

            int bracketLen = 34, bracketW = 3, margin = 18;
            g2.setStroke(new BasicStroke(bracketW));
            g2.setColor(new Color(navy.getRed(), navy.getGreen(), navy.getBlue(), 120));

            g2.drawLine(margin, margin, margin + bracketLen, margin);
            g2.drawLine(margin, margin, margin, margin + bracketLen);
            g2.drawLine(w - margin, margin, w - margin - bracketLen, margin);
            g2.drawLine(w - margin, margin, w - margin, margin + bracketLen);
            g2.drawLine(margin, h - margin, margin + bracketLen, h - margin);
            g2.drawLine(margin, h - margin, margin, h - margin - bracketLen);
            g2.drawLine(w - margin, h - margin, w - margin - bracketLen, h - margin);
            g2.drawLine(w - margin, h - margin, w - margin, h - margin - bracketLen);
        }
    }
}