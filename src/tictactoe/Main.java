package tictactoe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Tic-Tac-Toe");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 570);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(20, 83, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		RoundButton roundBtn = new RoundButton();
		
		JButton friendButton = new JButton("PLAYER VS FRIEND");
		friendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame singlePlayer = new VsFriend();
				setVisible(false);
				singlePlayer.setVisible(true);
			}
		});
		friendButton.setForeground(new Color(30, 30, 29));
		friendButton.setBackground(new Color(255, 182, 0));
		friendButton.setFont(new Font("Dialog", Font.BOLD, 30));
		friendButton.setBounds(60, 285, 440, 60);
		friendButton.setFocusable(false);
		friendButton.setBorderPainted(false);
		contentPane.add(friendButton);
		
		JButton robotButton = new JButton("PLAYER VS ROBOT");
		robotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame multiPlayer = new VsRobot();
				setVisible(false);
				multiPlayer.setVisible(true);
			}
		});
		robotButton.setForeground(new Color(77, 177, 165));
		robotButton.setFont(new Font("Dialog", Font.BOLD, 30));
		robotButton.setBackground(new Color(30, 30, 29));
		robotButton.setBounds(60, 370, 440, 60);
		robotButton.setFocusable(false);
		robotButton.setBorderPainted(false);
		contentPane.add(robotButton);
		
		JButton helpButton = new JButton("?");
		helpButton.setFont(new Font("Gadugi", Font.BOLD, 13));
		helpButton.setBackground(Color.WHITE);
		helpButton.setBounds(514, 480, 40, 40);
		helpButton.setUI(roundBtn);
		helpButton.setContentAreaFilled(false);
		helpButton.setBorderPainted(false);
		helpButton.setFocusPainted(false);
		contentPane.add(helpButton);
		
		JLabel lblXo = new JLabel("<html><font color='#1e1e1d'>X</font><font color='#ffb600'>O</font></html>");
		lblXo.setHorizontalAlignment(SwingConstants.CENTER);
		lblXo.setForeground(new Color(255, 182, 0));
		lblXo.setFont(new Font("Dialog", Font.BOLD, 55));
		lblXo.setBounds(59, 25, 440, 80);
		contentPane.add(lblXo);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(77, 177, 165));
		titlePanel.setBounds(60, 125, 440, 80);
		contentPane.add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel titleLabel = new JLabel("TIC-TAC-TOE");
		titleLabel.setBounds(10, 0, 415, 80);
		titlePanel.add(titleLabel);
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(new Color(19, 68, 85));
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 60));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
