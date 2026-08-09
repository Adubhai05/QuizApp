package quiz.app;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Result extends JFrame implements ActionListener {

    private final String playerName;
    private final Topic selectedTopic;
    private JButton tryAgain, topicsBtn;

    Result(String playerName, Topic selectedTopic, List<Question> questions,
           String[] userAnswers, int score) {

        this.playerName = playerName;
        this.selectedTopic = selectedTopic;

        int total = questions.size();
        int answered = 0;
        for (String ans : userAnswers) {
            if (ans != null) answered++;
        }
        int correct = score;
        int wrong = answered - correct;

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 246, 250));

        // ---------------------------------------- LEFT PANEL (question review) --------------------------------

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(254, 246, 250));
        leftPanel.setPreferredSize(new Dimension(550, 560));
        add(leftPanel, BorderLayout.WEST);

        JLabel logoIcon;
        try {
            ImageIcon logoIconRaw = new ImageIcon(ClassLoader.getSystemResource("icons/brain.png"));
            Image logoScaled = logoIconRaw.getImage().getScaledInstance(36, 36, Image.SCALE_SMOOTH);
            logoIcon = new JLabel(new ImageIcon(logoScaled));
        } catch (Exception e) {
            logoIcon = new JLabel();
        }
        logoIcon.setBounds(30, 25, 36, 36);
        leftPanel.add(logoIcon);

        JLabel brandText = new JLabel("QuizMaster");
        brandText.setBounds(75, 25, 200, 36);
        brandText.setFont(new Font("Segoe UI", Font.BOLD, 22));
        brandText.setForeground(new Color(30, 60, 110));
        leftPanel.add(brandText);

        JLabel heading = new JLabel("Answer Review");
        heading.setBounds(30, 65, 300, 30);
        heading.setFont(new Font("Segoe Print", Font.BOLD, 18));
        heading.setForeground(new Color(30, 60, 110));
        leftPanel.add(heading);

        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));
        reviewPanel.setBackground(Color.WHITE);

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            String selected = userAnswers[i];
            boolean isCorrect = selected != null && selected.equalsIgnoreCase(q.getCorrectOption());

            JPanel qBlock = new JPanel();
            qBlock.setLayout(new BoxLayout(qBlock, BoxLayout.Y_AXIS));
            qBlock.setBackground(Color.WHITE);
            qBlock.setBorder(BorderFactory.createCompoundBorder(
                    new MatteBorder(0, 0, 1, 0, new Color(222, 224, 230)),
                    new EmptyBorder(12, 12, 12, 12)
            ));
            qBlock.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Question row: number/text + symbol
            JPanel qRow = new JPanel();
            qRow.setLayout(new BorderLayout());
            qRow.setBackground(Color.WHITE);
            qRow.setAlignmentX(Component.LEFT_ALIGNMENT);
            qRow.setMaximumSize(new Dimension(460, 200));

            JLabel qText = new JLabel("<html><body style='width:380px'>" + (i + 1) + ". "
                    + q.getQuestionText() + "</body></html>");
            qText.setFont(new Font("Segoe UI", Font.BOLD, 13));
            qText.setForeground(new Color(40, 40, 40));
            qRow.add(qText, BorderLayout.CENTER);

            SymbolIcon symbol = new SymbolIcon(isCorrect);
            symbol.setPreferredSize(new Dimension(28, 28));
            symbol.setBorder(new EmptyBorder(0, 10, 0, 0));
            qRow.add(symbol, BorderLayout.EAST);

            qBlock.add(qRow);
            qBlock.add(Box.createRigidArea(new Dimension(0, 6)));

            String yourAnswerText = (selected == null) ? "Not answered" : selected + ") " + optionText(q, selected);
            JLabel yourAnswerLabel = new JLabel("<html>Your answer: <span style='color:"
                    + (isCorrect ? "#228b57" : "#db443b") + "'>" + yourAnswerText + "</span></html>");
            yourAnswerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            yourAnswerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            qBlock.add(yourAnswerLabel);

            if (!isCorrect) {
                JLabel correctAnswerLabel = new JLabel("Correct answer: " + q.getCorrectOption()
                        + ") " + optionText(q, q.getCorrectOption()));
                correctAnswerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                correctAnswerLabel.setForeground(new Color(34, 139, 87));
                correctAnswerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                qBlock.add(Box.createRigidArea(new Dimension(0, 3)));
                qBlock.add(correctAnswerLabel);
            }

            reviewPanel.add(qBlock);
        }

        JScrollPane scrollPane = new JScrollPane(reviewPanel);
        scrollPane.setBounds(30, 105, 490, 430);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(new LineBorder(new Color(210, 213, 220), 1, true));
        leftPanel.add(scrollPane);

        // ------------------------------------------- RIGHT PANEL (stats) -----------------------------------------

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(550, 0, 450, 560);
        rightPanel.setBackground(new Color(205, 226, 251));
        add(rightPanel);

        JLabel title = new JLabel("Quiz Complete!");
        title.setBounds(0, 60, 450, 42);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Segoe Print", Font.BOLD, 30));
        title.setForeground(new Color(30, 60, 110));
        rightPanel.add(title);

        JLabel subtitle = new JLabel(playerName + " \u2022 " + selectedTopic.getTopicName());
        subtitle.setBounds(0, 105, 450, 22);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(new Color(70, 80, 100));
        rightPanel.add(subtitle);

        // Final score circle-ish card
        JPanel scoreCard = new JPanel();
        scoreCard.setLayout(null);
        scoreCard.setBounds(105, 150, 240, 130);
        scoreCard.setBackground(Color.WHITE);
        scoreCard.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(59, 91, 219), 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));
        rightPanel.add(scoreCard);

        JLabel scoreCaption = new JLabel("FINAL SCORE");
        scoreCaption.setBounds(0, 10, 240, 18);
        scoreCaption.setHorizontalAlignment(SwingConstants.CENTER);
        scoreCaption.setFont(new Font("Segoe UI", Font.BOLD, 12));
        scoreCaption.setForeground(new Color(120, 130, 150));
        scoreCard.add(scoreCaption);

        JLabel scoreValue = new JLabel(correct + " / " + total);
        scoreValue.setBounds(0, 30, 240, 55);
        scoreValue.setHorizontalAlignment(SwingConstants.CENTER);
        scoreValue.setFont(new Font("Segoe UI", Font.BOLD, 42));
        scoreValue.setForeground(new Color(30, 60, 110));
        scoreCard.add(scoreValue);

        int percentage = total == 0 ? 0 : Math.round((correct * 100f) / total);
        JLabel percentLabel = new JLabel(percentage + "%");
        percentLabel.setBounds(0, 90, 240, 20);
        percentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        percentLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        percentLabel.setForeground(new Color(59, 91, 219));
        scoreCard.add(percentLabel);

        // Stats rows: Answered / Correct / Wrong
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(null);
        statsPanel.setBounds(75, 300, 300, 140);
        statsPanel.setOpaque(false);
        rightPanel.add(statsPanel);

        addStatRow(statsPanel, 0, "Questions Answered", answered + " / " + total, new Color(30, 60, 110));
        addStatRow(statsPanel, 45, "Correct Answers", String.valueOf(correct), new Color(34, 139, 87));
        addStatRow(statsPanel, 90, "Wrong Answers", String.valueOf(wrong), new Color(219, 68, 59));

        // Buttons
        tryAgain = new JButton("Try Again");
        tryAgain.setBounds(75, 465, 140, 40);
        tryAgain.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tryAgain.setForeground(Color.WHITE);
        tryAgain.setBackground(new Color(59, 91, 219));
        tryAgain.setFocusPainted(false);
        tryAgain.setBorderPainted(false);
        tryAgain.addActionListener(this);
        rightPanel.add(tryAgain);

        topicsBtn = new JButton("Topics");
        topicsBtn.setBounds(235, 465, 140, 40);
        topicsBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        topicsBtn.setForeground(new Color(59, 91, 219));
        topicsBtn.setBackground(Color.WHITE);
        topicsBtn.setBorder(new LineBorder(new Color(59, 91, 219), 1, true));
        topicsBtn.setFocusPainted(false);
        topicsBtn.addActionListener(this);
        rightPanel.add(topicsBtn);

        setSize(1000, 560);
        setLocation(200, 130);
        setResizable(false);
        setVisible(true);
    }

    private String optionText(Question q, String label) {
        switch (label.toUpperCase()) {
            case "A": return q.getOptionA();
            case "B": return q.getOptionB();
            case "C": return q.getOptionC();
            case "D": return q.getOptionD();
            default: return "";
        }
    }

    private void addStatRow(JPanel parent, int y, String label, String value, Color valueColor) {
        JLabel labelText = new JLabel(label);
        labelText.setBounds(0, y, 180, 30);
        labelText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelText.setForeground(new Color(60, 70, 90));
        parent.add(labelText);

        JLabel valueText = new JLabel(value);
        valueText.setBounds(180, y, 120, 30);
        valueText.setHorizontalAlignment(SwingConstants.RIGHT);
        valueText.setFont(new Font("Segoe UI", Font.BOLD, 16));
        valueText.setForeground(valueColor);
        parent.add(valueText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tryAgain) {
            dispose();
            new Quiz(playerName, selectedTopic);
        } else if (e.getSource() == topicsBtn) {
            dispose();
            new TopicSelection(playerName);
        }
    }

    // ---------------------------------------- CUSTOM CHECK/CROSS SYMBOL ----------------------------------------
    // Drawn with Graphics2D instead of a Unicode glyph, so it always renders (no missing-glyph boxes).

    private static class SymbolIcon extends JComponent {
        private final boolean correct;

        SymbolIcon(boolean correct) {
            this.correct = correct;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setStroke(new BasicStroke(2.4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            int w = getWidth();
            int h = getHeight();

            if (correct) {
                g2.setColor(new Color(34, 139, 87));
                g2.drawLine((int) (w * 0.2), (int) (h * 0.55), (int) (w * 0.42), (int) (h * 0.75));
                g2.drawLine((int) (w * 0.42), (int) (h * 0.75), (int) (w * 0.82), (int) (h * 0.25));
            } else {
                g2.setColor(new Color(219, 68, 59));
                g2.drawLine((int) (w * 0.22), (int) (h * 0.22), (int) (w * 0.78), (int) (h * 0.78));
                g2.drawLine((int) (w * 0.78), (int) (h * 0.22), (int) (w * 0.22), (int) (h * 0.78));
            }

            g2.dispose();
        }
    }
}