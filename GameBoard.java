import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;



public class GameBoard extends JFrame implements ActionListener {

	boolean steal = false;

	int rows;
	int cols;
	int scorpe;
	int scorpe2;
	
	int dailyDoubleRow = (int) Math.random() * 6;
	int dailyDoubleCol = (int) Math.random() * 5;

	int player1Score = 0;
	int player2Score = 0;

	
	
	JLabel question = new JLabel();
	JButton player1Correct = new JButton("Player 1 Correct");
	JButton player2Correct = new JButton("Player 2 Correct");
	JButton player1Wrong = new JButton("Player 1 Wrong");
	JButton player2Wrong = new JButton("Player 2 Wrong");
	JButton cancelButton = new JButton("Close");
	JLabel pl1text = new JLabel("Player 1", JLabel.CENTER);
	JLabel pl2text = new JLabel("Player 2", JLabel.CENTER);
	JLabel score1Text = new JLabel(String.valueOf(player1Score), JLabel.CENTER);
	JLabel score2Text = new JLabel(String.valueOf(player2Score), JLabel.CENTER);

	JPanel text = new JPanel(new FlowLayout());
	JPanel buttons = new JPanel(new GridLayout(2,2));
	JPanel cancel = new JPanel(new GridLayout(1,1));


	JFrame window;
	JFrame window2;



	String answers[][] = new String[6][5]; 
	JButton button[][] = new JButton[6][5];
	JPanel butts = new JPanel();
	//static ArrayList<String> catagories = new ArrayList<String>();
	int base = 200;

	String catagories [] = {"Safety", "Chain Of Command", "Work Attire", "Paper Trail", "The Color They Wear"};

	String Answers[][] = new String[][]{
			//{"","","","",""},
			{"<html>The amount of time the entire crew <br>has to exit a vehicle on a stretch</html>", "This person instructs the crew", "A Style boot worn for foot protection", "<html>This is supposed to be filled out<br> every week for pay roll purposes</html>", "Yellow Helmat"},
			{"<html>The direction in which the front tires <br> should be turned before exiting a vehicle<html>", "The Bosses, boss...", "The technical term for PPE", "<html>This form consist of vehicle mileage and work locations</html>", "Red Polo Shirt"},
			{"<html>This light is so that other drives know<br> to mind there speed at our work sites</html>", "This person instruct the facilitators", "not shorts, but...", "<html>Form you fill out when you get hurt <br> on the job</html>", "Grey Helmat"},
			{"<html>This person watches for traffic while <br> another gathers supplies</html>", "<html>This Person organizes mentoring activity <br> and gets called for attendance</html>", "<html>You need on of these for mentoring activities <br> or if your pants have loops</html>", "<html>This form is to be filled out durring auto accidents</html>", "purple helmat"},
			{"<html>The direction you face while working", "The head honcho", "These are all the items your required to wear", "<html>This form is to be clompleted <br>when you need a day off in advance", "Suit and Tie"}};

//	int scores [][] = new int[][]{
//			{200,200,200,200,200},
//			{400,400,400,400,400},
//			{600,600,600,600,600},
//			{800,800,800,800,800},
//			{1000,1000,1000,1000,1000}
//	};

	Container contentArea = getContentPane (); 

	public GameBoard(){

		super("Jeopardy");

		//		JMenuBar newGame = new JMenuBar();
		//		
		//		JMenu file = new JMenu("File");
		//		
		//		newGame.add(file);
		//		
		//		JMenuItem New = new JMenuItem("New Game");
		//		
		//		file.add(New);

		int i;
		int j;
		setSize(900, 1200);
		setVisible(true);
		setBackground(Color.YELLOW);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		butts = new JPanel (); 
		GridLayout experimentLayout = new GridLayout (6, 5); 
		butts.setLayout (experimentLayout); 
		butts.setBackground (Color.BLUE); 

		for( i = 0; i < 6; i++){
			for( j = 0; j < 5; j++){
				button[i][j] = new JButton();
				button[i][j].setFont(new Font("Arial", Font.BOLD, 16));
				button[i][j].setBackground (Color.BLUE); 
				button[i][j].addActionListener (this); 
				button[0][j].setText(catagories[j]);
				butts.add(button[i][j]); 
			}
		}
		for(j = 1; j < 6; j++){
			for(i = 0; i < 5; i++){
				button[j][i].setText(String.valueOf(base));
			}
			base = 200 + base;
		}
		//	contentArea.add(newGame);
		contentArea.add(butts); 
		setContentPane(contentArea); 

		pl1text.setFont(new Font("Arial", Font.BOLD, 30));
		pl1text.setForeground(Color.white);
		pl2text.setFont(new Font("Arial", Font.BOLD, 30));
		pl2text.setForeground(Color.WHITE);
		score1Text.setFont(new Font("Arial", Font.BOLD, 30));
		score1Text.setForeground(Color.white);
		score2Text.setFont(new Font("Arial", Font.BOLD, 30));
		score2Text.setForeground(Color.WHITE);

		window2 = new JFrame();
		window2.setLayout(new GridLayout(2,2));
		window2.add(pl1text);
		window2.add(pl2text);
		window2.add(score1Text);
		window2.add(score2Text);
		window2.setSize(400, 200);
		window2.getContentPane().setBackground(Color.BLUE);
		window2.setLocation(1000, 0);
		window2.setVisible(true);
		window2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int rows = 1; rows < 6 ; rows++){ 
			for (int cols = 0 ; cols < 5 ; cols++){ 
				if (e.getSource () == button[rows][cols]){ 

					window = new JFrame();
					//text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
					question.setForeground(Color.WHITE);
					question.setText(Answers[rows-1][cols]);
					question.setFont(new Font("Times New Roman", Font.BOLD, 30));
					window.setLayout(new GridLayout(3,1));
					text.add(question);
					buttons.add(player1Correct);
					buttons.add(player2Correct);
					buttons.add(player1Wrong);
					buttons.add(player2Wrong);
					cancel.add(cancelButton);
					window.add(text);
					window.add(buttons);
					window.add(cancel);
					buttons.setBackground(Color.BLUE);
					text.setBackground(Color.BLUE);
					cancel.setBackground(Color.BLUE);
					window.setSize(700, 250);
					window.setLocationRelativeTo(null);
					window.setVisible(true);
					window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



					int score = Integer.parseInt(button[rows][cols].getText());
					int score2 = Integer.parseInt(button[rows][cols].getText());
					
					scorpe = score;
					scorpe2 = score2;
					score = 0;
					score2 = 0;

					player1Correct.addActionListener(new ActionListener(){
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							player1Score = player1Score + scorpe;
							if(player1Score >= 0){
								score1Text.setForeground(Color.WHITE);
							}
							score1Text.setText(String.valueOf(player1Score));
							scorpe = 0;
							window.setVisible(false);

						}
					});

					player2Correct.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							player2Score = player2Score + scorpe2;
							if(player2Score >= 0){
								score2Text.setForeground(Color.WHITE);
							}
							score2Text.setText(String.valueOf(player2Score));
							scorpe2 = 0;
							window.setVisible(false);

						}

					});
					
					player1Wrong.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							player1Score = player1Score - scorpe;
							if(player1Score < 0){
								score1Text.setForeground(Color.RED);
							}
							score1Text.setText(String.valueOf(player1Score));
							scorpe = 0;
							//window.setVisible(false);
							
						}
						
					});
					
					player2Wrong.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							player2Score = player2Score - scorpe2;
							if(player2Score < 0){
								score2Text.setForeground(Color.RED);
							}
							score2Text.setText(String.valueOf(player2Score));
							scorpe2 = 0;
							//window.setVisible(false);
							
						}
						
					});
					
					cancelButton.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							window.setVisible(false);
						}
						
					});
					
					

					//button[rows][cols].setText ("x");
					//button[rows][cols].setOpaque(true);
					//button[rows][cols].setForeground(Color.BLUE);
					//button[rows][cols].setFont(new Font("Arial", Font.BOLD, 100));
					button[rows][cols].setEnabled(false);
					button[rows][cols].setVisible(false);
				} 
			} 
		}
	}


	public static void main(String[]args){		
		new GameBoard();




	}
}
