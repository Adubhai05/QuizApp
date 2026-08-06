package quiz.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Rules extends JFrame implements ActionListener {

    private String playerName;
    private Topic selectedTopic;
    JButton start, back;

    Rules(String playerName, Topic selectedTopic) {
        this.playerName = playerName;
        this.selectedTopic = selectedTopic;

        JLabel heading = new JLabel("Read the Rules");
        heading.setBounds(150, 90, 700, 40);
        heading.setFont(new Font("Segoe Print", Font.BOLD, 26));
        heading.setForeground(new Color(30, 60, 110));
        add(heading);

        JLabel rules = new JLabel();
        rules.setBounds(120, 160, 600, 320);
        rules.setFont(new Font("Segoe Print", Font.BOLD, 13));
        rules.setForeground(new Color(60, 70, 90));
        rules.setText("<html>" +
                "1. Participation in the quiz is free and open to all persons above 18 years old." + "<br><br>" +
                "2. There are a total 10 questions." + "<br><br>" +
                "3. You only have 15 seconds to answer the question." + "<br><br>" +
                "4. No cell phones or other secondary devices in the room or test area." + "<br><br>" +
                "5. No talking." + "<br><br>" +
                "6. No one else can be in the room with you." + "<br><br>" +
                "</html>");
        add(rules);

        back = new JButton("Back");
        back.setBounds(300, 500, 110, 36);
        back.setFont(new Font("Segoe UI", Font.BOLD, 13));
        back.setForeground(new Color(59, 91, 219));
        back.setBackground(Color.WHITE);
        back.setBorder(BorderFactory.createLineBorder(new Color(59, 91, 219), 1, true));
        back.setFocusPainted(false);
        back.setContentAreaFilled(true);
        back.addActionListener(this);
        add(back);

        start = new JButton("Start");
        start.setBounds(430, 500, 110, 36);
        start.setFont(new Font("Segoe UI", Font.BOLD, 13));
        start.setForeground(Color.WHITE);
        start.setBackground(new Color(59, 91, 219));
        start.setBorderPainted(false);
        start.setFocusPainted(false);
        start.addActionListener(this);
        add(start);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/back.png"));
        Image i = i1.getImage().getScaledInstance(800, 650, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i);
        JLabel image = new JLabel(i2);
        image.setBounds(0, 0, 800, 650);
        add(image);

        setSize(800, 650);
        setLocation(350, 100);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            dispose();
            // Quiz screen goes here later
            System.out.println("Starting quiz: " + selectedTopic.getTopicName());
        } else {
            dispose();
            new TopicSelection(playerName);
        }
    }
}
