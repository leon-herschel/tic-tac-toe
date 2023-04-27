package tictactoe;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main extends JFrame {

	private JPanel contentPane;
	private Clip clip;
	boolean soundEnabled = true;

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
		setBounds(100, 100, 680, 680);
		setLocationRelativeTo(null);
		
		try {
			URL soundUrl = getClass().getClassLoader().getResource("tictactoe/sounds/bgmusic.wav");
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundUrl);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(28, 49, 68));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		RoundButton roundBtn = new RoundButton();
		
		UIManager.put("Button.select", new Color(251, 255, 121));
		
		JButton friendButton = new JButton("PLAY VS FRIEND");
		friendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame singlePlayer = new VsFriend();
				clip.stop();
				setVisible(false);
				singlePlayer.setVisible(true);
				
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
		friendButton.setForeground(new Color(28, 49, 68));
		friendButton.setBackground(new Color(255, 182, 0));
		friendButton.setFont(new Font("Eras Demi ITC", Font.BOLD, 45));
		friendButton.setBounds(60, 300, 540, 80);
		friendButton.setFocusable(false);
		friendButton.setBorderPainted(false);
		friendButton.setUI(new RoundedCorner(20));
		contentPane.add(friendButton);
		
		JButton robotButton = new JButton("PLAY VS ROBOT");
		robotButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame multiPlayer = new VsRobot();
				clip.stop();
				setVisible(false);
				multiPlayer.setVisible(true);
		        
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
		robotButton.setUI(new RoundedCorner(20));
		robotButton.setForeground(new Color(28, 49, 68));
		robotButton.setFont(new Font("Eras Demi ITC", Font.BOLD, 45));
		robotButton.setBackground(new Color(244, 68, 46));
		robotButton.setBounds(60, 420, 540, 80);
		robotButton.setFocusable(false);
		robotButton.setBorderPainted(false);
		contentPane.add(robotButton);
		
		JButton helpButton = new JButton("?");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame howToPlay = new HowToPlay();
				howToPlay.setVisible(true);
				
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
		helpButton.setFont(new Font("Gadugi", Font.BOLD, 25));
		helpButton.setBackground(Color.WHITE);
		helpButton.setBounds(604, 580, 50, 50);
		helpButton.setUI(roundBtn);
		helpButton.setContentAreaFilled(false);
		helpButton.setBorderPainted(false);
		helpButton.setFocusPainted(false);
		contentPane.add(helpButton);
		
		JButton muteButton = new JButton("♫");
		muteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (soundEnabled) {
                    // Music is playing, stop it
                    clip.stop();
                    soundEnabled = false;
                } else {
                    // Music is stopped, start it
                    clip.start();
                    soundEnabled = true;
                }
				
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
		muteButton.setFont(new Font("Arial", Font.BOLD, 20));
		muteButton.setBackground(Color.WHITE);
		muteButton.setBounds(10, 580, 50, 50);
		muteButton.setUI(roundBtn);
		muteButton.setContentAreaFilled(false);
		muteButton.setBorderPainted(false);
		muteButton.setFocusPainted(false);
		contentPane.add(muteButton);
		
		RoundedPanel titlePanel = new RoundedPanel(20);
		titlePanel.setForeground(new Color(29, 211, 176));
		titlePanel.setBackground(new Color(244, 68, 46));
		titlePanel.setBounds(60, 90, 540, 115);
		contentPane.add(titlePanel);
		titlePanel.setLayout(null);
		
		JLabel titleLabel = new JLabel("TIC-TAC-TOE");
		titleLabel.setBounds(10, 30, 515, 90);
		titlePanel.add(titleLabel);
		titleLabel.setBackground(Color.BLACK);
		titleLabel.setForeground(new Color(28, 49, 68));
		titleLabel.setFont(new Font("Eras Demi ITC", Font.BOLD, 77));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblXo_1 = new JLabel("BLITZ");
		lblXo_1.setBounds(0, -75, 540, 150);
		titlePanel.add(lblXo_1);
		lblXo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblXo_1.setForeground(new Color(28, 49, 68));
		lblXo_1.setFont(new Font("Eras Demi ITC", Font.ITALIC, 99));
		
		JLabel lblXo = new JLabel("BLITZ");
		lblXo.setBounds(60, 52, 540, 80);
		contentPane.add(lblXo);
		lblXo.setHorizontalAlignment(SwingConstants.CENTER);
		lblXo.setForeground(new Color(255, 182, 0));
		lblXo.setFont(new Font("Eras Demi ITC", Font.ITALIC, 99));
		
		JButton btnCredits = new JButton("CREDITS");
		btnCredits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame credits = new Credits();
				credits.setVisible(true);
				
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
		btnCredits.setForeground(new Color(28, 49, 68));
		btnCredits.setFont(new Font("Eras Demi ITC", Font.BOLD, 30));
		btnCredits.setFocusable(false);
		btnCredits.setBorderPainted(false);
		btnCredits.setBackground(new Color(255, 182, 0));
		btnCredits.setBounds(230, 585, 200, 40);
		btnCredits.setUI(new RoundedCorner(20));
		contentPane.add(btnCredits);
	}
}
