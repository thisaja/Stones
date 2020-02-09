/**
 * File Name: Display.java
 * Programmers: Thisaja
 * Course: ICS3U1
 * Date: January 16th, 2020
 */

import java.util.*;
import java.io.*;

/**
 * Description: GUI Class; Creates the whoile GUI and links the Client variables to the GUI Components
 */
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;

public class Display implements ActionListener{
	private JFrame window;
	private JPanel panel,gamePanel,endPanel;
	private String title;
	private int height,width;

	private JLabel gameTitle,numStonesText,playerLabel,playerIDLabel,enterBinLabel,winnerLabel;
	private JLabel p1[]=new JLabel[7];
	private JLabel p2[]=new JLabel[7];
	private JTextField numStones,enterBin;
	private JButton enterNumStones,enterBinNum;
	
    /**
     * Description: The constructor; initializes the JFrames title, height and width
     * Pre: A string value for the title and 2 integer values for the height and width
     * Post: None
     */
	public Display(String title,int height,int width) {
		this.title=title;
		this.height=height;
		this.width=width;
		run();
	}
	
    /**
     * Description: A helper method to get create and place all the components of the JFrame
     * Pre: None
     * Post: None
     */
	private void run() {
		window=new JFrame(title);
		window.setMinimumSize(new Dimension(width,height));
		window.setMaximumSize(new Dimension(width,height));
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		//First Screen
		panel=new JPanel();
		panel.setLayout(null);
		
		gameTitle=new JLabel("Stones Game");
		gameTitle.setBounds((width-100)/2, 0, 100, 50);
		panel.add(gameTitle);
		
		
		numStonesText=new JLabel("Enter the number of stones you want to begin with: ");
		numStonesText.setBounds(0, 200, 300, 20);
		panel.add(numStonesText);
		
		numStones=new JTextField(16);
		numStones.setBounds(300, 200, 150, 20);
		panel.add(numStones);
		
		enterNumStones=new JButton("Enter");
		enterNumStones.setBounds((width-200)/2,250,200,50);
		enterNumStones.setActionCommand("EnterStones");
		enterNumStones.addActionListener(this);
		panel.add(enterNumStones);
		
		//Game Screen
		gamePanel=new JPanel();
		gamePanel.setLayout(null);
		gamePanel.setVisible(false);
		
		enterBinLabel=new JLabel("Enter the bin you want to move: ");
		enterBinLabel.setBounds((width-200)/2,0,200,20);
		gamePanel.add(enterBinLabel);
		
		playerLabel=new JLabel("Player:");
		playerLabel.setBounds(0,300,300,20);
		gamePanel.add(playerLabel);
		
		playerIDLabel=new JLabel(Integer.toString(Client.player+1));
		playerIDLabel.setBounds(50,300,300,20);
		gamePanel.add(playerIDLabel);
		
		enterBin=new JTextField(16);
		enterBin.setBounds(300,300, 150, 20);
		gamePanel.add(enterBin);
		
		enterBinNum=new JButton("Enter");
		enterBinNum.setBounds((width-200)/2,350,200,50);
		enterBinNum.setActionCommand("EnterBin");
		enterBinNum.addActionListener(this);
		gamePanel.add(enterBinNum);
		
		//Done Screen
		endPanel=new JPanel();
		endPanel.setLayout(null);
		endPanel.setVisible(false);
		
		winnerLabel=new JLabel("");
		winnerLabel.setBounds(50,50,200,200);
		endPanel.add(winnerLabel);
		
		window.setContentPane(panel);
		window.pack();
	}
	
    /**
     * Description: Completes an action based on which button is pressed
     * Pre: An ActionEvent type object containing the ActionCommand of the button pressed
     * Post: None
     */
	public void actionPerformed(ActionEvent e) {
		String action=e.getActionCommand();
		if(action.equals("EnterStones")) {
			int x=Integer.valueOf(numStones.getText());
			if(x>=2 && x<=5) {
				Client.numStones=x;
				panel.setVisible(false);
				Client.s=new Stones(Client.numStones);
				for(int i=0;i<7;i++) {
					p1[i]=new JLabel(Integer.toString(Client.s.player[0].array[i]));
					p1[i].setBounds(100+50*i, 100, 20, 20);
					gamePanel.add(p1[i]);
					p2[i]=new JLabel(Integer.toString(Client.s.player[1].array[i]));
					p2[i].setBounds(350-50*i, 50, 20, 20);
					gamePanel.add(p2[i]);
					gamePanel.setVisible(true);
					window.setContentPane(gamePanel);
				}
			}
		}
		if(action.equals("EnterBin")){
			int x=Integer.valueOf(enterBin.getText());
			if(x>=1 && x<=6 && Client.s.player[Client.player].array[x-1]!=0) {
				Client.player=Client.s.moveStones(Client.player, x);
				showStones();
			}
		}
		
	}
	
    /**
     * Description: A helper method to update all the labels containing the number of stones in each pit
     * Pre: None
     * Post: None
     */
	private void showStones() {
		for(int i=0;i<7;i++) {
			p1[i].setText((Integer.toString(Client.s.player[0].array[i])));
			p2[i].setText((Integer.toString(Client.s.player[1].array[i])));
			playerIDLabel.setText(Integer.toString(Client.player+1));
			gamePanel.setVisible(true);
			window.setContentPane(gamePanel);
		}
		if(Client.s.isOver()) {
			System.out.println(toString());
			if(Client.s.player[0].array[6]>Client.s.player[1].array[6]) {
				winnerLabel.setText("Player 1 Wins!");
			}
			else if(Client.s.player[1].array[6]>Client.s.player[0].array[6]) {
				winnerLabel.setText("Player 2 Wins!");
			}
			else {
				winnerLabel.setText("It's a Draw!");
			}
			gamePanel.setVisible(false);
			endPanel.setVisible(true);
			window.setContentPane(endPanel);
		}
	}
}
