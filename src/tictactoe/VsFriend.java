package tictactoe;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class VsFriend extends JFrame implements ActionListener {
	
	Random random = new Random();
	JPanel button_panel = new JPanel();
	private JPanel contentPane;
	JLabel liveText = new JLabel();
	JButton[] buttons = new JButton[9];
	JLabel player1Score = new JLabel("0");
	JLabel player2Score = new JLabel("0");
	private Clip clip;
	boolean player1_turn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VsFriend frame = new VsFriend();
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
	public VsFriend() {
		setTitle("Tic-Tac-Toe");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 680);
		setLocationRelativeTo(null);
		
		try {
			URL soundUrl = getClass().getClassLoader().getResource("tictactoe/sounds/bgmusic2.wav");
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 30, 29));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblXo = new JLabel("BLITZ");
		lblXo.setForeground(new Color(255, 182, 0));
		lblXo.setFont(new Font("Eras Demi ITC", Font.ITALIC, 35));
		lblXo.setBounds(90, 21, 100, 30);
		contentPane.add(lblXo);
		
		button_panel.setBackground(new Color(30, 30, 29));
		button_panel.setBounds(90, 60, 480, 480);
		contentPane.add(button_panel);
		button_panel.setLayout(new GridLayout(3, 3, 10, 10));
		
		RoundedPanel player1Panel = new RoundedPanel(20);
		player1Panel.setBackground(new Color(244, 68, 46));
		player1Panel.setBounds(90, 551, 180, 60);
		contentPane.add(player1Panel);
		player1Panel.setLayout(new BorderLayout(0, 0));
		
		player1Score.setHorizontalAlignment(SwingConstants.CENTER);
		player1Score.setForeground(new Color(30, 30, 29));
		player1Score.setFont(new Font("Eras Demi ITC", Font.BOLD, 40));
		player1Panel.add(player1Score, BorderLayout.CENTER);
		
		JLabel player1Label = new JLabel("X(YOU)");
		player1Label.setHorizontalAlignment(SwingConstants.CENTER);
		player1Label.setForeground(new Color(30, 30, 29));
		player1Label.setFont(new Font("Eras Demi ITC", Font.BOLD, 20));
		player1Panel.add(player1Label, BorderLayout.NORTH);
		
		RoundedPanel player2Panel = new RoundedPanel(20);
		player2Panel.setBackground(new Color(255, 182, 0));
		player2Panel.setBounds(390, 551, 180, 60);
		contentPane.add(player2Panel);
		player2Panel.setLayout(new BorderLayout(0, 0));
		
		JLabel player2Label = new JLabel("O(FRIEND)");
		player2Label.setHorizontalAlignment(SwingConstants.CENTER);
		player2Label.setForeground(new Color(30, 30, 29));
		player2Label.setFont(new Font("Eras Demi ITC", Font.BOLD, 20));
		player2Panel.add(player2Label, BorderLayout.NORTH);
		
		player2Score.setHorizontalAlignment(SwingConstants.CENTER);
		player2Score.setForeground(new Color(30, 30, 29));
		player2Score.setFont(new Font("Eras Demi ITC", Font.BOLD, 40));
		player2Panel.add(player2Score, BorderLayout.CENTER);
		
		JButton restartButton = new JButton("↻");
		restartButton.setForeground(new Color(30, 30, 29));
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0; i<9; i++) {
		            buttons[i].setText("");
		            buttons[i].setEnabled(true);
		            buttons[i].setBackground(new Color(28, 49, 68));
				}
				firstTurn();
				
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
		});
		restartButton.setBackground(new Color(255, 255, 255));
		restartButton.setFont(new Font("Dialog", Font.BOLD, 45));
		restartButton.setBounds(280, 551, 100, 60);
		restartButton.setFocusable(false);
		restartButton.setBorderPainted(false);
		restartButton.setUI(new RoundedCorner(20));
		contentPane.add(restartButton);
		
		JButton exitButton = new JButton("✖");
		exitButton.setForeground(new Color(30, 30, 29));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame main = new Main();
				clip.stop();
				setVisible(false);
				main.setVisible(true);
				
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
		});
		exitButton.setFont(new Font("Dialog", Font.BOLD, 20));
		exitButton.setFocusable(false);
		exitButton.setBackground(new Color(255, 255, 255));
		exitButton.setBounds(519, 17, 50, 40);
		exitButton.setBorderPainted(false);
		exitButton.setUI(new RoundedCorner(20));
		contentPane.add(exitButton);
		
		liveText.setForeground(new Color(255, 255, 255));
		liveText.setFont(new Font("Eras Demi ITC", Font.BOLD, 33));
		liveText.setBounds(260, 23, 140, 28);
		liveText.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(liveText);
		
		for(int i=0; i<9; i++) {
			buttons[i] = new JButton();
			button_panel.add(buttons[i]);
			buttons[i].setUI(new RoundedCorner(20));
			buttons[i].setFont(new Font("Dialog", Font.BOLD, 150));
			buttons[i].setBackground(new Color(28, 49, 68));
			buttons[i].setBorder(null);
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		firstTurn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i=0; i<9; i++) {
			if(e.getSource()==buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(244, 68, 46));
						buttons[i].setText("X");
						player1_turn=false;
						liveText.setText("O TURN");
						check();
						
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
				else {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(255, 182, 0));
						buttons[i].setText("O");
						player1_turn=true;
						liveText.setText("X TURN");
						check();
						
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
			}
		}
	}
	
public void firstTurn() {
		if(random.nextInt(2) == 0) {
			player1_turn = true;
			liveText.setText("X TURN");
		}else {
			player1_turn = false;
			liveText.setText("O TURN");
		}
		
	}
	
	public void check() {
		
		boolean gameOver = false;
		//check X win condition
		if(
				(buttons[0].getText()=="X") &&
				(buttons[1].getText()=="X") &&
				(buttons[2].getText()=="X")
				){
			xWins(0, 1, 2);
			gameOver = true;
		}
		if(
				(buttons[3].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[5].getText()=="X")
				){
			xWins(3, 4, 5);
			gameOver = true;
		}
		if(
				(buttons[6].getText()=="X") &&
				(buttons[7].getText()=="X") &&
				(buttons[8].getText()=="X")
				){
			xWins(6, 7, 8);
			gameOver = true;
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[3].getText()=="X") &&
				(buttons[6].getText()=="X")
				){
			xWins(0, 3, 6);
			gameOver = true;
		}
		if(
				(buttons[1].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[7].getText()=="X")
				){
			xWins(1, 4, 7);
			gameOver = true;
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[5].getText()=="X") &&
				(buttons[8].getText()=="X")
				){
			xWins(2, 5, 8);
			gameOver = true;
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[8].getText()=="X")
				){
			xWins(0, 4, 8);
			gameOver = true;
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[6].getText()=="X")
				){
			xWins(2, 4, 6);
			gameOver = true;
		}
		
		//check O win condition
		if(
				(buttons[0].getText()=="O") &&
				(buttons[1].getText()=="O") &&
				(buttons[2].getText()=="O")
				){
			oWins(0, 1, 2);
			gameOver = true;
		}
		if(
				(buttons[3].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[5].getText()=="O")
				){
			oWins(3, 4, 5);
			gameOver = true;
		}
		if(
				(buttons[6].getText()=="O") &&
				(buttons[7].getText()=="O") &&
				(buttons[8].getText()=="O")
				){
			oWins(6, 7, 8);
			gameOver = true;
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[3].getText()=="O") &&
				(buttons[6].getText()=="O")
				){
			oWins(0, 3, 6);
			gameOver = true;
		}
		if(
				(buttons[1].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[7].getText()=="O")
				){
			oWins(1, 4, 7);
			gameOver = true;
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[5].getText()=="O") &&
				(buttons[8].getText()=="O")
				){
			oWins(2, 5, 8);
			gameOver = true;
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[8].getText()=="O")
				){
			oWins(0, 4, 8);
			gameOver = true;
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[6].getText()=="O")
				){
			oWins(2, 4, 6);
			gameOver = true;
		}
		
		boolean fullBoard = true;
	    for (int i = 0; i < 9; i++) {
	        if (buttons[i].getText().equals("")) {
	            fullBoard = false;
	            break;
	        }
	    }
	    if (fullBoard) {
	        if (!gameOver) {
	            liveText.setText("DRAW");
	            gameOver = true;
	            disableButtons();
	        }
	    }
	}
	
	public void xWins(int a, int b, int c) {
		buttons[a].setBackground(new Color(244, 68, 46));
		buttons[b].setBackground(new Color(244, 68, 46));
		buttons[c].setBackground(new Color(244, 68, 46));
		buttons[a].setForeground(new Color(30, 30, 29));
		buttons[b].setForeground(new Color(30, 30, 29));
		buttons[c].setForeground(new Color(30, 30, 29));
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		liveText.setText("X WINS");
		
		updateScore(player1Score);
		
	}
	
	public void oWins(int a, int b, int c) {
		buttons[a].setBackground(new Color(255, 182, 0));
		buttons[b].setBackground(new Color(255, 182, 0));
		buttons[c].setBackground(new Color(255, 182, 0));
		buttons[a].setForeground(new Color(30, 30, 29));
		buttons[b].setForeground(new Color(30, 30, 29));
		buttons[c].setForeground(new Color(30, 30, 29));
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
		}
		liveText.setText("O WINS");
		
		updateScore(player2Score);
		
	}
	
	private void updateScore(JLabel scoreLabel) {
	    int score = Integer.parseInt(scoreLabel.getText());
	    score++;
	    scoreLabel.setText(String.valueOf(score));
	}
	
	public void disableButtons() {
	    for (int i = 0; i < 9; i++) {
	        buttons[i].setEnabled(false);
	        buttons[i].setBackground(new Color(169, 169, 169));
	        buttons[i].setForeground(new Color(30, 30, 29));

	    }

	}
}
