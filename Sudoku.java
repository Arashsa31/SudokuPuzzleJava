import java.util.Scanner;

/**
 * @author Arash
 *
 */
public class Sudoku {
	public static void main(String[] args) {

		// declaring objects
		SudokuPuzzle puzzle = new SudokuPuzzle("Default_Start");
		
		Scanner keyboard = new Scanner(System.in);
		// instance variables
		int row, col, val, input;
		

		// continues until user quits or game is solved
		while (!(puzzle.checkPuzzle() && puzzle.isFull())) {
			System.out.println("\n" + "The puzzle is not yet solved.");
			System.out.println(puzzle);
			System.out.print("Enter -1 for hint, 1 to add a guess, or 0 to quit or reset the puzzle: ");
			input = keyboard.nextInt();
			if (input == 1) {
				System.out.print("Row: ");
				row = keyboard.nextInt() - 1;
				System.out.print("Column: ");
				col = keyboard.nextInt() - 1;
				System.out.print("Value: ");
				val = keyboard.nextInt();
				puzzle.addGuess(row, col, val);
			}

			if (input == -1) {
				System.out.print("Row: ");
				row = keyboard.nextInt() - 1;
				System.out.print("Column: ");
				col = keyboard.nextInt() - 1;

				System.out.print("Valid values available: ");
				for (int i = 0; i < 9; i++) {
					if (puzzle.getAllowedValues(row, col)[i]) {
						System.out.print(i + 1 + " ");
					}
				}
			}
			if (input == 0) {
				System.out.println("Type quit or reset:");
				String userInput = keyboard.next();
				if (userInput.equalsIgnoreCase("quit")) {
					System.out.println("Goodbye.");
					System.exit(0);
				} 
				else {
					System.out.println("Resetting puzzle...");
					puzzle.reset();
				}
			}			
		}
		System.out.println("\nYou solved the puzzle!");
		System.out.println(puzzle);
	}
}
