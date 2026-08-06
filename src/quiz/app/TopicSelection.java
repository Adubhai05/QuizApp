package quiz.app;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TopicSelection extends JFrame implements ActionListener {

    private String playerName;
    private ButtonGroup topicGroup;
    private JButton next, back;
    private Topic selectedTopic;

    TopicSelection(String playerName) {
        this.playerName = playerName;

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 246, 250));



        // ----------------------------------------LEFT PANEL ----------------------------------------------------

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(254, 246, 250));
        leftPanel.setPreferredSize(new Dimension(450, 500));
        add(leftPanel, BorderLayout.WEST);


        // adding logo

        ImageIcon logoIconRaw = new ImageIcon(ClassLoader.getSystemResource("icons/brain.png"));
        Image logoScaled = logoIconRaw.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel logoIcon = new JLabel(new ImageIcon(logoScaled));
        logoIcon.setBounds(60, 40, 40, 40);
        leftPanel.add(logoIcon);

        JLabel brandText = new JLabel("QuizMaster");
        brandText.setBounds(108, 40, 200, 40);
        brandText.setFont(new Font("Segoe UI", Font.BOLD, 28));
        brandText.setForeground(new Color(30, 60, 110));
        leftPanel.add(brandText);

        JLabel greeting = new JLabel("Hi " + playerName + ", choose a topic:");
        greeting.setBounds(60, 100, 350, 20);
        greeting.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        greeting.setForeground(new Color(100, 100, 100));
        leftPanel.add(greeting);


        // Dynamic topic list from database

        topicGroup = new ButtonGroup();
        List<Topic> topics = TopicDAO.getAllTopics();

        int yPos = 135;
        for (Topic t : topics) {
            JRadioButton topicBtn = createTopicButton(t.getTopicName());
            topicBtn.setBounds(60, yPos, 320, 42);
            topicBtn.addActionListener(e -> selectedTopic = t);
            topicGroup.add(topicBtn);
            leftPanel.add(topicBtn);
            yPos += 52;
        }

        // Back / Next buttons


        back = new JButton("Back");
        back.setBounds(60, yPos + 15, 120, 38);
        back.setFont(new Font("Segoe UI", Font.BOLD, 13));
        back.setForeground(new Color(59, 91, 219));
        back.setBackground(new Color(245, 246, 250));
        back.setBorder(new LineBorder(new Color(59, 91, 219), 1, true));
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.addActionListener(this);
        leftPanel.add(back);

        next = new JButton("Next");
        next.setBounds(190, yPos + 15, 120, 38);
        next.setFont(new Font("Segoe UI", Font.BOLD, 13));
        next.setForeground(Color.WHITE);
        next.setBackground(new Color(59, 91, 219));
        next.setFocusPainted(false);
        next.setBorderPainted(false);
        next.addActionListener(this);
        leftPanel.add(next);



        // -------------------------------------------RIGHT PANEL -------------------------------------------------


        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(450, 0, 550, 500);
        rightPanel.setBackground(new Color(205, 226, 251));
        add(rightPanel);

        ImageIcon logoRaw = new ImageIcon(ClassLoader.getSystemResource("icons/topics.png"));
        logoScaled = logoRaw.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(logoScaled));
        logo.setBounds(245, 140, 70, 70);
        rightPanel.add(logo);

        JLabel title = new JLabel("Pick Your Topic");
        title.setBounds(0, 220, 550, 42);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Segoe Print", Font.BOLD, 40));
        title.setForeground(new Color(30, 60, 110));
        rightPanel.add(title);

        JLabel subtitle = new JLabel("What is your brain craving today");
        subtitle.setBounds(0, 275, 550, 20);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe Print", Font.BOLD, 13));
        subtitle.setForeground(new Color(30, 60, 110));
        rightPanel.add(subtitle);

        setSize(1000, 500);
        setLocation(200, 150);
        setResizable(false);
        setVisible(true);
    }

    // Styled radio button that looks like a selectable card


    private JRadioButton createTopicButton(String text) {
        JRadioButton btn = new JRadioButton(text);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setForeground(new Color(40, 40, 40));
        btn.setBackground(Color.WHITE);
        btn.setOpaque(true);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(210, 213, 220), 1, true),
                new EmptyBorder(0, 12, 0, 12)
        ));
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next) {
            if (selectedTopic == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a topic to continue.",
                        "Topic Required", JOptionPane.WARNING_MESSAGE);
                return;
            }
            dispose();
            new Rules(playerName, selectedTopic);

        } else if (e.getSource() == back) {
            dispose();
            new Login();
        }
    }

}
