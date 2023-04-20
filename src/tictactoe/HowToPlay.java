package tictactoe;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

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
		setBounds(100, 100, 495, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(20, 83, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnTheGameIs = new JTextPane();
		txtpnTheGameIs.setForeground(new Color(255, 182, 0));
		txtpnTheGameIs.setEditable(false);
		txtpnTheGameIs.setBackground(new Color(30, 30, 29));
		txtpnTheGameIs.setFont(new Font("Dialog", Font.BOLD, 12));
		txtpnTheGameIs.setText("\r\n1. The game is played on a 3x3 grid, which is displayed on the screen."
		        + "\r\n\r\n2. Two players take turns to mark a square on the grid with their respective symbols. "
		        + "One player uses the symbol \"X\", and the other player uses the symbol \"O\".\r\n\r\n3. "
		        + "The player who goes first chooses any square to place their symbol.\r\n\r\n4. "
		        + "The second player then takes their turn and places their symbol in an empty square.\r\n\r\n5. "
		        + "Players continue taking turns until one of the following conditions is met:\r\n\r\n"
		        + "\u2022 One player has three of their symbols in a row (horizontally, vertically, or diagonally).\r\n"
		        + "\u2022 All squares on the grid have been filled with symbols, and no player has won.\r\n"
		        + "\r\n\r\n6. To start a new game, click on either the \"Player Vs Friend\" or \"Player Vs Robot\" buttons.");
		txtpnTheGameIs.setBounds(10, 49, 460, 370);
		contentPane.add(txtpnTheGameIs);

		
		JTextPane txtpnHowToPlay = new JTextPane();
		txtpnHowToPlay.setForeground(new Color(30, 30, 29));
		txtpnHowToPlay.setEditable(false);
		txtpnHowToPlay.setBackground(new Color(255, 182, 0));
		txtpnHowToPlay.setFont(new Font("Dialog", Font.BOLD, 25));
		txtpnHowToPlay.setText("HOW TO PLAY");
		StyledDocument doc = txtpnHowToPlay.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false); 
		txtpnHowToPlay.setBounds(10, 11, 460, 39);
		contentPane.add(txtpnHowToPlay);
		
		
		
	}
	
}