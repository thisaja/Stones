/**
 * File Name: Player.java
 * Programmers: Thisaja
 * Course: ICS3U1
 * Date: January 16th, 2020
 */

import java.util.*;
import java.io.*;

/**
 * Description: Player Class; holds the array of each player and their designated ID
 */
public class Player {
	int ID;
	int array[];
	
    /**
     * Description: The constructor; gives an ID to the player-type object and initializes all the indices of the array except for the home bin with the inital number of stones.
     * Pre: The ID as an integer and the number of stones initially as an integer
     * Post: None
     */
	public Player(int ID, int numStones) {
		this.ID=ID;
		array=new int[7];
		for(int i=0;i<6;i++)array[i]=numStones;
	}
}
