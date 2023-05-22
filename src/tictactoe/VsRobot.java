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

public class VsRobot extends JFrame implements ActionListener {
	
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
	public VsRobot() {
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
		
		JLabel player2Label = new JLabel("O(ROBOT)");
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
		exitButton.setBackground(Color.WHITE);
		exitButton.setBounds(519, 17, 50, 40);
		exitButton.setBorderPainted(false);
		exitButton.setUI(new RoundedCorner(20));
		contentPane.add(exitButton);
		
		liveText.setForeground(new Color(255, 255, 255));
		liveText.setFont(new Font("Eras Demi ITC", Font.BOLD, 33));
		liveText.setBounds(255, 23, 155, 28);
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
						check();
					}
				}
			}
		}
	}
	
	public void firstTurn() {
			
			if(random.nextInt(2) == 0) {
				 player1_turn = true;
			        if (Integer.parseInt(player1Score.getText()) <= 4) {
			            liveText.setText("EASY");
			        } else if (Integer.parseInt(player1Score.getText()) <= 9) {
			            liveText.setText("MEDIUM");
			        } else {
			            liveText.setText("HARD");
			        }
			}else {
				player1_turn = false;
				if (Integer.parseInt(player1Score.getText()) <= 4) {
		            liveText.setText("EASY");
		        } else if (Integer.parseInt(player1Score.getText()) <= 9) {
		            liveText.setText("MEDIUM");
		        } else {
		            liveText.setText("HARD");
		        }
				botTurn();
			}
			
		}
	
	public void check() {
		boolean gameOver = false;
		if (!gameOver && !player1_turn) {
	        botTurn();
	    }
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
	            
	            try {
	                URL soundUrl = getClass().getClassLoader().getResource("tictactoe/sounds/draw.wav");
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
	
	public void xWins(int a, int b, int c) {
		buttons[a].setBackground(new Color(244, 68, 46));
		buttons[b].setBackground(new Color(244, 68, 46));
		buttons[c].setBackground(new Color(244, 68, 46));
		buttons[a].setForeground(new Color(30, 30, 29));
		buttons[b].setForeground(new Color(30, 30, 29));
		buttons[c].setForeground(new Color(30, 30, 29));
		
		try {
            URL soundUrl = getClass().getClassLoader().getResource("tictactoe/sounds/winner.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
            Clip buttonClip = AudioSystem.getClip();
            buttonClip.open(audioInputStream);
            buttonClip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
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
		
		try {
            URL soundUrl = getClass().getClassLoader().getResource("tictactoe/sounds/winner.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
            Clip buttonClip = AudioSystem.getClip();
            buttonClip.open(audioInputStream);
            buttonClip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
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
	public boolean checkForWin(String player) {
	    // Check rows
	    for (int i = 0; i < 3; i++) {
	        if (buttons[i*3].getText().equals(player) &&
	            buttons[i*3+1].getText().equals(player) &&
	            buttons[i*3+2].getText().equals(player)) {
	            return true;
	        }
	    }
	
	    // Check columns
	    for (int i = 0; i < 3; i++) {
	        if (buttons[i].getText().equals(player) &&
	            buttons[i+3].getText().equals(player) &&
	            buttons[i+6].getText().equals(player)) {
	            return true;
	        }
	    }
	
	    // Check diagonals
	    if (buttons[0].getText().equals(player) &&
	        buttons[4].getText().equals(player) &&
	        buttons[8].getText().equals(player)) {
	        return true;
	    }
	    if (buttons[2].getText().equals(player) &&
	        buttons[4].getText().equals(player) &&
	        buttons[6].getText().equals(player)) {
	        return true;
	    }
	
	    return false;
	}
	
	private boolean hasDoubleThreatInRow(int row) {
		int emptyCount = 0;
	    int emptyIndex = -1;
	    boolean hasX = false;

	    for (int i = 0; i < 3; i++) {
	        String text = buttons[row * 3 + i].getText();
	        if (text.equals("")) {
	            emptyCount++;
	            emptyIndex = row * 3 + i;
	        } else if (text.equals("X")) {
	            hasX = true;
	        } else {
	            // The cell is already occupied by O
	            return false;
	        }
	    }

	    if (hasX && emptyCount == 1) {
	        buttons[emptyIndex].setText("O");
	        boolean doubleThreat = checkForWin("O");
	        buttons[emptyIndex].setText("");
	        return doubleThreat;
	    }

	    return false;
	}

	private boolean hasDoubleThreatInColumn(int col) {
	    int emptyCount = 0;
	    int emptyIndex = -1;
	    for (int i = 0; i < 3; i++) {
	        if (buttons[col + i * 3].getText().equals("")) {
	            emptyCount++;
	            emptyIndex = col + i * 3;
	        } else if (!buttons[col + i * 3].getText().equals("X")) {
	            return false;
	        }
	    }
	    if (emptyCount == 1) {
	        return false;
	    }
	    buttons[emptyIndex].setText("O");
	    boolean doubleThreat = checkForWin("O");
	    buttons[emptyIndex].setText("");
	    return doubleThreat;
	}
	
	private boolean hasDoubleThreatInDiagonal() {
		  // Check left diagonal
	    if (buttons[0].getText().equals("X") && buttons[4].getText().equals("") && buttons[8].getText().equals("X")) {
	        return true;
	    }
	    if (buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("")) {
	        return true;
	    }
	    if (buttons[0].getText().equals("") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X")) {
	        return true;
	    }

	    // Check right diagonal
	    if (buttons[2].getText().equals("X") && buttons[4].getText().equals("") && buttons[6].getText().equals("X")) {
	        return true;
	    }
	    if (buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("")) {
	        return true;
	    }
	    if (buttons[2].getText().equals("") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X")) {
	        return true;
	    }

	    // Check other diagonals
	    String[] diagonal1 = {buttons[0].getText(), buttons[4].getText(), buttons[8].getText()};
	    String[] diagonal2 = {buttons[2].getText(), buttons[4].getText(), buttons[6].getText()};
	    return checkForDoubleThreatInArray(diagonal1) || checkForDoubleThreatInArray(diagonal2);
	}

	private boolean checkForDoubleThreatInArray(String[] array) {
	    int countO = 0;
	    int countEmpty = 0;
	    for (String s : array) {
	        if (s.equals("O")) {
	            countO++;
	        } else if (s.equals("")) {
	            countEmpty++;
	        }
	    }
	    return countO == 2 && countEmpty == 1;
	}
	
	public void botTurn() {
		if (checkForWin("X") || checkForWin("O")) {
	        return;
	    }
	    boolean moveMade = false;
	    if (player1_turn) {
	        int randomIndex = random.nextInt(buttons.length);
	        buttons[randomIndex].setText("O");
	        buttons[randomIndex].setForeground(new Color(255, 182, 0));
	        moveMade = true;
	    } else {
	    // Check rows
	    for (int i = 0; i < 3; i++) {
	        if (buttons[i*3].getText().equals("O") && buttons[i*3+1].getText().equals("O") && buttons[i*3+2].getText().equals("")) {
	            buttons[i*3+2].setText("O");
	            buttons[i*3+2].setForeground(new Color(255, 182, 0));
	            moveMade = true;
	            break;
	        }
	        else if (buttons[i*3].getText().equals("O") && buttons[i*3+1].getText().equals("") && buttons[i*3+2].getText().equals("O")) {
	            buttons[i*3+1].setText("O");
	            buttons[i*3+1].setForeground(new Color(255, 182, 0));
	            moveMade = true;
	            break;
	        }
	        else if (buttons[i*3].getText().equals("") && buttons[i*3+1].getText().equals("O") && buttons[i*3+2].getText().equals("O")) {
	            buttons[i*3].setText("O");
	            buttons[i*3].setForeground(new Color(255, 182, 0));
	            moveMade = true;
	            break;
	        	}
	    	}
	
	        // Check columns
	        if (!moveMade) {
	            for (int i = 0; i < 3; i++) {
	        	 if (buttons[i].getText().equals("O") && buttons[i+3].getText().equals("O") && buttons[i+6].getText().equals("")) {
	                 buttons[i+6].setText("O");
	                 buttons[i+6].setForeground(new Color(255, 182, 0));
	                 moveMade = true;
	                 break;
	             }
	             else if (buttons[i].getText().equals("O") && buttons[i+3].getText().equals("") && buttons[i+6].getText().equals("O")) {
	                 buttons[i+3].setText("O");
	                 buttons[i+3].setForeground(new Color(255, 182, 0));
	                 moveMade = true;
	                 break;
	             }
	             else if (buttons[i].getText().equals("") && buttons[i+3].getText().equals("O") && buttons[i+6].getText().equals("O")) {
	                 buttons[i].setText("O");
	                 buttons[i].setForeground(new Color(255, 182, 0));
	                 moveMade = true;
	                 break;
	             } 	
	        }	
	        if (!moveMade) {
	            for (int i = 0; i < 3; i++) {
	            	if (buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("")) {
	    	        buttons[8].setText("O");
	    	        buttons[8].setForeground(new Color(255, 182, 0));
	    	        moveMade = true;
	    	        break;
	    	    }
	    	    else if (buttons[0].getText().equals("O") && buttons[4].getText().equals("") && buttons[8].getText().equals("O")) {
	    	        buttons[4].setText("O");
	    	        buttons[4].setForeground(new Color(255, 182, 0));
	    	        moveMade = true;
	    	        break;
	    	    }
	    	    else if (buttons[0].getText().equals("") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O")) {
	    	        buttons[0].setText("O");
	    	        buttons[0].setForeground(new Color(255, 182, 0));
	    	        moveMade = true;
	    	        break;
	    	       }
	            }
	        
	    }
	    if (liveText.getText().equals("MEDIUM") || liveText.getText().equals("HARD")) {
	     // If no winning move found, check for a blocking move for X
	        if (!moveMade) {
	            for (int i = 0; i < buttons.length; i++) {
	                if (buttons[i].getText().equals("")) {
	                    buttons[i].setText("X");
	                    if (checkForWin("X")) {
	                        buttons[i].setText("O");
	                        buttons[i].setForeground(new Color(255, 182, 0));
	                        moveMade = true;
	                        break;
	                    }
	                    buttons[i].setText("");
	                }
	            }
	        }
	    } 
	    
	    // Check for double threat
	    if (liveText.getText().equals("HARD")) {
		    if (!moveMade) {
		        for (int i = 0; i < buttons.length; i++) {
		            if (buttons[i].getText().equals("")) {
		                buttons[i].setText("O");
		
		                // Check for double threat in row
		                int row = i / 3;
		                if (hasDoubleThreatInRow(row)) {
		                    buttons[i].setForeground(new Color(255, 182, 0));
		                    moveMade = true;
		                    break;
		                }
		
		                // Check for double threat in column
		                int col = i % 3;
		                if (hasDoubleThreatInColumn(col)) {
		                    buttons[i].setForeground(new Color(255, 182, 0));
		                    moveMade = true;
		                    break;
		                }
		
		                // Check for double threat in diagonal
		                if (hasDoubleThreatInDiagonal()) {
		                    buttons[i].setForeground(new Color(255, 182, 0));
		                    moveMade = true;
		                    break;
		                }
		
		                buttons[i].setText("");
		            }
		        }
		    }
	    }
	     // If no winning move found, make a random move
	    while (!moveMade) {
	        int randomIndex = random.nextInt(buttons.length);
	        if (buttons[randomIndex].getText().equals("")) {
	            buttons[randomIndex].setText("O");
	            buttons[randomIndex].setForeground(new Color(255, 182, 0));
	            moveMade = true;
	        } else {
	            boolean allFilled = true;
	            for (JButton button : buttons) {
	                if (button.getText().equals("")) {
	                    allFilled = false;
	                    break;
	                }
	            }
	            if (allFilled) {
	                break;
	                }
	            }
	        }
	
	        // Switch turns and check for win
	        player1_turn = true;
	        checkForWin("X");
			}
	    }
	}
}
