import java.util.ArrayList;

/**
* Recursively solve a randomly generated labyrinth and stores the moves made
* to accomplish this in an ArrayList. Convert the ArrayList to an int[] array
* where: <ul> 
* <li> 0 represents a move up </li>
* <li> 1 represents a move down </li>
* <li> 2 represents a move left </li>
* <li> 3 represents a move right </li>
* </ul>
*/ 
public class Noah {

	static int totalCols; // number of rows in the maze, randomly generated between 5 and 15
	static int totalRows; // number of columns in the maze, randomly generated between 5 and 15
	static boolean[][] visited; // 2 dimensional array which tracks the squares the algorithm has already visited
	static ArrayList<Integer> directions = new ArrayList<>(); // arraylist of moves the program makes represented by integers 0-3

	/**
    * Recursively solves the maze, with the exit case being that the final 
	* square has been visited. Store the moves made to get there in an 
	* arraylist.
    *
    * @param row the current "row" in the labyrinth, used as the first index in
	* references to arrays representing the labyrinth. 
	* @param col the current "column" in the labyrinth, used as the second index
	* in references to arrays representing the labyrinth.
	* @param l the labyrinth to be solved.
	* @return a boolean representing whether the algorithm has been to the last
	* square in the maze. 
    */
	public static boolean findSafeMove(int row, int col, Labyrinth l) {
		visited[row][col] = true;

		if (visited[totalRows-1][totalCols-1]) {
			return true;
		}
		else {
			if (isSafe(row, col, Labyrinth.DOWN, l)) {
				directions.add(1);
				if (findSafeMove(row + 1, col, l)) {
					return true;
				}
			}

			if (isSafe(row, col, Labyrinth.RIGHT, l)) {
				directions.add(3);
				if (findSafeMove(row, col + 1, l)) {
					return true;
				}
			}

			if (isSafe(row, col, Labyrinth.UP, l)) {
				directions.add(0);
				if (findSafeMove(row - 1, col, l)) {
					return true;
				}
			}

			if (isSafe(row, col, Labyrinth.LEFT, l)) {
				directions.add(2);
				if (findSafeMove(row, col - 1, l)) {
					return true;
				}
			}
			directions.remove(directions.size()-1);
			visited[row][col] = false;
		}
		return false;
	}
	
	/**
	* Call findSafeMove to solve the labyrinth. Convert the arraylist of moves
	* made to an array of Integers, then convert that array to an array of ints
	* and return it. 
	*
	* @param l the labyrinth to be solved.
	* @return an int array containing the moves made to solve the maze. 
	*/
	public static int[] solve(Labyrinth l) {
		
		visited = new boolean[l.rows][l.cols];
		
		findSafeMove(0, 0, l);
		Integer[] moves = new Integer[directions.size()];
		directions.toArray(moves);
		
		int[] solution = new int[moves.length];
		
		for (int i = 0; i < moves.length; i++) {
			solution[i] = moves[i];
		}
		
		return solution;
	}

	/**
	* Determine if a move is in the labyrinth, and if so, check if it is stone
	* and if the algorithm has visited it previously. 
	* 
	* @param row the current "row" in the labyrinth, used as the first index in
	* references to arrays representing the labyrinth. 
	* @param col the current "column" in the labyrinth, used as the second index
	* in references to arrays representing the labyrinth.
	* @param x an array of integers representing movement in the array.
	* @param l the labyrinth in which the move is being made.
	* @return a boolean representing whether or not the move is safe (stone 
	* and within the grid). 
	*/
	public static boolean isSafe(int row, int col, int[] x, Labyrinth l) {
		int tempRow = row + x[0];
		int tempCol = col + x[1];

		if (!l.isValid(tempRow, tempCol)) {
			return false; 
		}
		else if (l.isStone(tempRow, tempCol) && !visited[tempRow][tempCol]) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/** 
	* Initialize variables for the total rows and columns in the labyrinth 
	* to randomly generated values as well as a 2 dimensional array to keep 
	* track of which squares of the maze the algorithm has already visited. 
	* Declare and initialize a Labyrinth of those dimensions. Print the 
	* labyrinth as well as the result of a Labyrinth method testing whether
	* the solution to the maze correctly solves it. 
	*/
	public static void main(String[] args) {
		totalCols = (int)((Math.random()*20)+5);
		totalRows = (int)((Math.random()*20)+5);

		Labyrinth crete = new Labyrinth(totalRows,totalCols);
		crete.printGrid();

		System.out.println(crete.solves(solve(crete)));
	}
}