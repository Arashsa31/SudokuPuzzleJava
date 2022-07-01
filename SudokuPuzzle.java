/**
 * @author Arash
 *
 *         Write a main method in the class Sudoku that creates a SudokuPuzzle
 *         object and sets its initial configuration. Then use a loop to allow
 *         someone to play Sudoku. Display the current configuration and ask for
 *         a row, column, and value. Update the game board and display it again.
 *         If the configuration does not satisfy the restrictions, let the user
 *         know. Indicate when the puzzle has been solved correctly. In that
 *         case, both checkPuzzle and isFull would return true. You should also
 *         allow options for resetting the puzzle and displaying the values that
 *         can be placed in a given square.
 */
public class SudokuPuzzle {

	// instance variables
	// a 9 by 9 array of integers that represents the current state of the puzzle, where 0 indicates a blank square
	private int[][] board = new int[9][9];
	// a 9 by 9 array of boolean values that indicates which squares in board are, given values that cannot be changed
	private boolean[][] start = new boolean[9][9];
	 
	// test case
	/*
	private int[][] board = { 
			{1, 2, 3, 4, 9, 7, 8, 6, 5},
			{4, 5, 9, 1, 6, 8, 2, 3, 7},
			{6, 7, 8, 2, 5, 3, 1, 4, 9},
			{3, 4, 5, 6, 1, 2, 7, 9, 8},
			{2, 1, 6, 7, 8, 9, 3, 5, 4},
			{9, 8, 7, 3, 4, 5, 6, 1, 2}, 
			{8, 3, 1, 5, 2, 4, 9, 7, 6}, 
			{7, 9, 4, 8, 3, 6, 5, 2, 1},
			{5, 6, 2, 9, 7, 1, 4, 8, 0/*3/},
	};
	 */
	// default constructor; constructor that creates an empty puzzle
	public SudokuPuzzle() {

	}

	// overloaded constructor
	public SudokuPuzzle(String defaultStart) {
		addInitial(0, 0, 1);
		addInitial(1, 0, 4);
		addInitial(2, 0, 6);
		addInitial(3, 0, 3);
		addInitial(4, 0, 2);
		addInitial(5, 0, 9);
		addInitial(6, 0, 8);
		addInitial(7, 0, 7);
		addInitial(8, 0, 5);

		addInitial(0, 1, 2);
		addInitial(0, 2, 3);
		addInitial(0, 3, 4);
		addInitial(0, 4, 9);
		addInitial(0, 5, 7);
		addInitial(0, 6, 8);
		addInitial(0, 7, 6);
		addInitial(0, 8, 5);

		addInitial(1, 1, 5);
		addInitial(2, 1, 7);
		addInitial(1, 2, 9);
		addInitial(2, 2, 8);

		addInitial(8, 3, 9);

		addInitial(3, 4, 1);

		addInitial(5, 5, 5);
	}
	
	/**
	 * @return a string representation of the puzzle that can be printed
	 */
	public String toString() {
		String puzzle = "------------------\n";
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				puzzle += board[row][col];
				puzzle += "|";
			}
			puzzle += "\n------------------\n";
		}
		return puzzle;
	}

	/**
	 * @return a table boolean representation of the puzzle that can be printed
	 */
	public String booleanTable() {
		String puzzle = "";
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				puzzle += start[row][col];
				puzzle += "\t";
			}
			puzzle += "\n";
		}
		return puzzle;
	}

	/**
	 * @param row
	 * @param col
	 * @param value; sets the given square to the given value as an initial value
	 *               that cannot be changed by the puzzle solver
	 */
	private void addInitial(int row, int col, int value) {
		board[row][col] = value;
		start[row][col] = true;
	}

	/**
	 * @param row
	 * @param col
	 * @param value; sets the given square to the given value; the value can be
	 *               changed later by another call to addGuess
	 */
	public void addGuess(int row, int col, int value) {
		if (start[row][col] != true)
			board[row][col] = value;
	}

	/**
	 * @return true if the values in the puzzle do not violate the restrictions
	 */
	public boolean checkPuzzle() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				for (int k=0; k<9; k++)
				if (this.getAllowedValues(i, j)[k] == true)
					return false;
			}
		}
		return true;
	}

	/**
	 * @param row
	 * @param col
	 * @param value
	 * @return true if value is valid for the smaller 3x3 grid
	 */
	private boolean subArray(int row, int col, int value) {
		int x = row - row % 3;
		int y = col - col % 3;
		for (int i = 0; i < board.length / 3; i++) {
			for (int j = 0; j < board.length / 3; j++) {
				if (value == this.getValueIn(i + x, j + y)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @param row
	 * @param col
	 * @param value
	 * @return true if value for row is valid
	 */
	private boolean rowValid(int row, int col, int value) {
		for (int i = 0; i < board.length; i++) {
			if (value == this.getValueIn(row, i))
				return false;
		}
		return true;
	}

	/**
	 * @param row
	 * @param col
	 * @param value
	 * @return true if value for column is valid
	 */
	private boolean colValid(int row, int col, int value) {
		for (int i = 0; i < board.length; i++) {
			if (value == this.getValueIn(i, col))
				return false;
		}
		return true;
	}

	/**
	 * @param row
	 * @param col
	 * @return value in a given square
	 */
	public int getValueIn(int row, int col) {
		return board[row][col];
	}

	/**
	 * @param row
	 * @param col
	 * @return boolTemp; a one-dimensional array of nine booleans, each of which
	 *         corresponds to a digit and is true if the digit can be placed in the
	 *         given square without violating the restrictions
	 */
	public boolean[] getAllowedValues(int row, int col) {
		boolean[] boolTemp = new boolean[board.length];

		for (int i = 0; i < board.length; i++) {
			if ((	   this.subArray(row, col, i + 1) 
					&& this.rowValid(row, col, i + 1) 
					&& this.colValid(row, col, i + 1))) {
				boolTemp[i] = true;
			}
		}
		return boolTemp;
	}

	/**
	 * @return true if every square has a value
	 */
	public boolean isFull() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] == 0)
					return false;
			}
		}
		return true;
	}

	/**
	 * changes all of the nonpermanent squares back to blanks (0s)
	 */
	public void reset() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (start[row][col] == false) {
					board[row][col] = 0;
				}
			}
		}
	}
}
