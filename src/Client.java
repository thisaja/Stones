/**
 * File Name: Client.java
 * Programmers: Thisaja
 * Course: ICS3U1
 * Date: January 16th, 2020
 */

import java.util.*;
import java.io.*;

/**
 * Description: Client code; Lets the user change various parameters.
 */
public class Client {
	static Scanner input=new Scanner(System.in);
	static int numStones;
	static Stones s;
	static int player=0;
	
    /**
     * Description: Displays a prompt asking the user for their input and returns it as a string
     * Pre: A string containing the prompt
     * Post: The users input as a string.
     */
	public static String input(String s) {
		System.out.print(s);
		String x=input.nextLine();
		return x;
	}
	public static void main(String[] args) {
		new Display("Stones",500,500);
	}
}
