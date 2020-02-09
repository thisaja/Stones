/**
 * File Name: Stones.java
 * Programmers: Thisaja
 * Course: ICS3U1
 * Date: January 16th, 2020
 */

import java.util.*;
import java.io.*;

/**
 * Description: Stones; Does all the processing like moving the stones and checking if the game is over
 */
public class Stones {
	Player player[]=new Player[2];
	
    /**
     * Description: The constructor; initializes both the player-type objects with the number of stones they each contain
     * Pre: The initial number of stones in each pit as an integer
     * Post: None
     */
	public Stones(int numStones) {
		for(int i=0;i<2;i++)player[i]=new Player(i,numStones);
	}
	
    /**
     * Description: Moves the stones based on the player choosing and what bin they choose
     * Pre: The playerID as an integer and the bin the player chooses as an integer.
     * Post: The next players turn as an integer
     */
	public int moveStones(int playerID,int bin) {
		bin--;
		int playerTurn = playerID;
		int numStones = player[playerID].array[bin];
		player[playerID].array[bin]=0;
		
		bin++;
		
		while(numStones!=0) {
			player[playerTurn].array[bin%7]++;
			numStones--;
			bin++;
			if(bin%7==6 && playerTurn!=playerID)bin++;
			if(bin%7==0){
				playerTurn++;
				playerTurn%=2;
			}
		}
		
		
		bin--;
		if((playerID==playerTurn) && player[playerID].array[bin%7]==1 && bin%7!=6){
			player[playerTurn].array[6]+=player[(playerTurn+1)%2].array[5-(bin%7)];
			player[(playerTurn+1)%2].array[5-(bin%7)]=0;
		}
		if(playerID!=playerTurn && bin%7==6)return playerID;
		return (playerID+1)%2;
	}
	
    /**
     * Description: Returns a boolean value based on if the game is over or not
     * Pre: None
     * Post: Returns true if the game is over and false if it isn't
     */
	public boolean isOver() {
		for(int i=0;i<2;i++) {
			boolean flag=true;
			for(int j=0;j<6;j++)if(player[i].array[j]!=0)flag=false;
			if(flag) {
				for(int k=0;k<6;k++) {
					player[i].array[6]+=player[(i+1)%2].array[k];
					player[(i+1)%2].array[k]=0;
				}
				return flag;
			}
		}
		return false;
	}
	
    /**
     * Description: Prints out each players pits and their designated home bin
     * Pre: None
     * Post: None
     */
	public String toString() {
		String s="Player 2: "+player[1].array[6]+"\t";
		for(int i=5;i>=0;i--)s+=player[1].array[i]+" ";
		s+="\nPlayer 1: \t";
		for(int i=0;i<=5;i++)s+=player[0].array[i]+" ";
		s+="\t"+player[0].array[6];
		return s;
	}
}
