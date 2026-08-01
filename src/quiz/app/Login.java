package quiz.app;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    JTextField text;
    JButton Next, back;

    Login() {
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 246, 250));



        // ---------------------------------LEFT side of the login frame ---------------------------------------

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(254, 246, 250));
        leftPanel.setPreferredSize(new Dimension(450, 500));
        add(leftPanel, BorderLayout.WEST);



        // Logo image

        ImageIcon logoIconRaw = new ImageIcon(ClassLoader.getSystemResource("icons/brain.png"));
        Image logoScaled = logoIconRaw.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel logoIcon = new JLabel(new ImageIcon(logoScaled));
        logoIcon.setBounds(60, 90, 40, 40);
        leftPanel.add(logoIcon);


        // "QuizMaster" brand text beside the logo

        JLabel brandText = new JLabel("QuizMaster");
        brandText.setBounds(108, 90, 200, 40);
        brandText.setFont(new Font("Segoe UI", Font.BOLD, 35));
        brandText.setForeground(new Color(30, 60, 110));
        leftPanel.add(brandText);


        JLabel name = new JLabel("Enter your name");
        name.setBounds(60, 175, 300, 18);
        name.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        name.setForeground(new Color(100, 100, 100));
        leftPanel.add(name);



        text = new JTextField();
        text.setBounds(60, 205, 320, 36);
        text.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        text.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(210, 213, 220), 1, true),
                new EmptyBorder(0, 10, 0, 10)
        ));

        leftPanel.add(text);



        back = new JButton("Back");
        back.setBounds(60, 270, 120, 38);
        back.setFont(new Font("Segoe UI", Font.BOLD, 13));
        back.setForeground(new Color(59, 91, 219));
        back.setBackground(new Color(245, 246, 250));
        back.setBorder(new LineBorder(new Color(59, 91, 219), 1, true));
        back.setFocusPainted(false);
        back.setBorderPainted(true);
        back.setContentAreaFilled(false);
        back.addActionListener(this);
        leftPanel.add(back);

        Next = new JButton("Next");
        Next.setBounds(190, 270, 120, 38);
        Next.setFont(new Font("Segoe UI", Font.BOLD, 13));
        Next.setForeground(Color.WHITE);
        Next.setBackground(new Color(59, 91, 219));
        Next.setFocusPainted(false);
        Next.setBorderPainted(false);
        Next.addActionListener(this);
        leftPanel.add(Next);



        // ------------------------ RIGHT side of the login frame -----------------------------------------


        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBounds(450, 0, 550, 500);
        rightPanel.setBackground(new Color(205, 226, 251));
        add(rightPanel);



        // Logo

        ImageIcon logoRaw = new ImageIcon(ClassLoader.getSystemResource("icons/mind-control.png"));
        logoScaled = logoRaw.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(logoScaled));
        logo.setBounds(245, 140, 70, 70);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(logo);



        // Title

        JLabel title = new JLabel("Time to Quiz");
        title.setBounds(0, 220, 550, 40);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Segoe Print", Font.BOLD, 45));
        title.setForeground(new Color(30, 60, 110));
        rightPanel.add(title);



        // Subtitle

        JLabel subtitle = new JLabel("Let's get started");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Next) {

        } else if (e.getSource() == back) {
            System.exit(50);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}