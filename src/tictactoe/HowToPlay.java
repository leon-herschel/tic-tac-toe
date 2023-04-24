package tictactoe;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.CardLayout;

public class HowToPlay extends JFrame {

	private JPanel contentPane;

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
		setTitle("Tic-Tac-Toe");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 595, 570);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(28, 49, 68));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		
		JPanel panel1 = new JPanel();
	    panel1.setBackground(new Color(28, 49, 68));
	    JLabel label1 = new JLabel(new ImageIcon("src/tictacroe/step1.png"));
	    panel1.add(label1);

	    // create a panel for the second image
	    JPanel panel2 = new JPanel();
	    panel2.setBackground(new Color(28, 49, 68));
	    JLabel label2 = new JLabel(new ImageIcon("src/tictactoe/step2.png"));
	    panel2.add(label2);

	    // add the panels to the content pane using CardLayout
	    contentPane.add(panel1, "panel1");
	    contentPane.add(panel2, "panel2");
	    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
	    cardLayout.show(contentPane, "panel1");
	    cardLayout.show(contentPane, "panel2");
	}
	
}