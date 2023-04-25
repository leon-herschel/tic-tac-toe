package tictactoe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class HowToPlay extends JFrame implements ActionListener {

    private JPanel contentPane;
    private CardLayout cardLayout;
    private JPanel cards;
    private JButton nextButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HowToPlay frame = new HowToPlay();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public HowToPlay() {
        setTitle("How To Play");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 640);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(28, 49, 68));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        cards = new JPanel(new CardLayout());
        contentPane.add(cards, BorderLayout.CENTER);
        
        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(28, 49, 68));
        JLabel label1 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("tictactoe/img/step1.png")));
        panel1.add(label1);

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(28, 49, 68));
        JLabel label2 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("tictactoe/img/step2.png")));
        panel2.add(label2); 

        JPanel panel3 = new JPanel();
        panel3.setBackground(new Color(28, 49, 68));
        JLabel label3 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("tictactoe/img/step3.png")));
        panel3.add(label3);
        
        JPanel panel4 = new JPanel();
        panel4.setBackground(new Color(28, 49, 68));
        JLabel label4 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("tictactoe/img/step4.png")));
        panel4.add(label4);
        
        JPanel panel5 = new JPanel();
        panel5.setBackground(new Color(28, 49, 68));
        JLabel label5 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("tictactoe/img/step5.png")));
        panel5.add(label5);
        
        JPanel panel6 = new JPanel();
        panel6.setBackground(new Color(28, 49, 68));
        JLabel label6 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("tictactoe/img/step6.png")));
        panel6.add(label6);
        
        JPanel panel7 = new JPanel();
        panel7.setBackground(new Color(28, 49, 68));
        JLabel label7 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("tictactoe/img/step7.png")));
        panel7.add(label7);
        
        JPanel panel8 = new JPanel();
        panel8.setBackground(new Color(28, 49, 68));
        JLabel label8 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("tictactoe/img/step8.png")));
        panel8.add(label8);
        
        JPanel panel9 = new JPanel();
        panel9.setBackground(new Color(28, 49, 68));
        JLabel label9 = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("tictactoe/img/step9.png")));
        panel9.add(label9);

        cards.add(panel1, "panel1");
        cards.add(panel2, "panel2");
        cards.add(panel3, "panel3");
        cards.add(panel4, "panel4");
        cards.add(panel5, "panel5");
        cards.add(panel6, "panel6");
        cards.add(panel7, "panel7");
        cards.add(panel8, "panel8");
        cards.add(panel9, "panel9");
		
        nextButton = new JButton("Next");
        nextButton.setForeground(new Color(28, 49, 68));
    	nextButton.setBackground(new Color(255, 182, 0));
    	nextButton.setFont(new Font("Eras Demi ITC", Font.BOLD, 30));
    	nextButton.setBounds(60, 300, 140, 50);
    	nextButton.setFocusable(false);
    	nextButton.setBorderPainted(false);
    	nextButton.setUI(new RoundedCorner(20));
        nextButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(28, 49, 68));
        buttonPanel.add(nextButton);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);

        cardLayout = (CardLayout) cards.getLayout();
    }

    public void actionPerformed(ActionEvent e) {
    	
            cardLayout.next(cards);
            try {
	            URL soundUrl = getClass().getClassLoader().getResource("tictactoe/sounds/buttonsound.wav");
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
	            Clip buttonClip = AudioSystem.getClip();
	            buttonClip.open(audioInputStream);
	            buttonClip.start();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
    }
}
