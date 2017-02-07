package ticTac.main;



import java.util.Scanner;

import gameEngine.Board;
import gameEngine.Player;

/**
 * This class runs the program. It handles all user interaction and
 * displays to the console.
 * 
 * For playing, the board is mapped out like so:
 *  7 | 8 | 9
 *  ----------
 *  4 | 5 | 6
 *  ----------
 *  1 | 2 | 3
 *  
 *  Basically, just like a numpad.
 * 
 * @author Nishi
 */
public class Runner {

	/**
	 * This is the main method. This is what gets called when the program
	 * runs. The methods calls describe the process it goes through.
	 */
	public static void main(String[] args) {
		Board board = new Board();
		
		String winner = playGame(board);
		
		printBoard(board);
		
		printWinner(winner);
	}
	
	/**
	 * The main code for interacting with the user.
	 * 
	 * @param board
	 *             the board to play on
	 * @return the name of the winner
	 */
	private static String playGame(Board board) {
	
		Player playerX = new Player('X',board);
		Player playerO = new Player('O',board);
		Scanner in = new Scanner(System.in);
		
		Player currPlayer;
		int turnCount=0;
		int play;
		String winner = "Tie";
		boolean keepPlaying  = true;
		boolean hasWon = false; //flag to mark if any player has won
		while(keepPlaying){
			printBoard(board);
			turnCount++;
			if(turnCount%2 == 0){ //player O will play for even turns
				currPlayer = playerO;
			}else{
				currPlayer = playerX;
				
			}
		System.out.print(currPlayer.getName()+" ,make a move (1-9): ");
		play = in.nextInt();
		hasWon = currPlayer.makePlay(Player.cells[play-1]);
			if(hasWon){
				winner = currPlayer.getName();
				keepPlaying = false;
			}
			if(turnCount==9){ // dealing with a tie
				keepPlaying = false; 
			}
		}
		return winner;
	}

	/**
	 * Prints the board.
	 * 
	 * @param board
	 *            the board to print
	 */
	private static void printBoard(Board board) {
		
		// These are for loops. They start at a designated number and keep looping
		// until  a condition is met. These loop through These loops go through each
		// row of the board, then  go through each cell in that row.
		for (int row = 0; row < board.getRowCount(); row++) {
			for (int col = 0; col < board.getColumnCount(); col++) {
				
				// Prints out the contents of the current cell
				System.out.print(" " + board.getCellContents(row, col));
				
				// If this is not the last cell, bring a divider
				if (col < board.getColumnCount() - 1) {
					System.out.print(" |");
				}
			}
			
			// If this is not the last row, print a divider
			if (row < board.getRowCount() - 1) {
				System.out.println();
				System.out.println("------------");
			}
		}
		
		// Prints a line break
		System.out.println();
	}
	
	/**
	 * Simply prints the given winner.
	 * 
	 * @param winner
	 *            the winner to print
	 */
	private static void printWinner(String winner) {
		if(winner.equals("Tie")){
			System.out.println("Its a tie");
		}else{
			System.out.println(winner+" won ");
		}
	}

}
