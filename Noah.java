import java.util.ArrayList;

public class Noah {
	static int totalCols;
	static int totalRows;
	static boolean[][] visited;
	static ArrayList<Integer> directions = new ArrayList<>();
	static Integer[] moves;
	static int[] solution;

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
				directions.remove(directions.size()-1);
				visited[row][col] = false;
			}

			if (isSafe(row, col, Labyrinth.RIGHT, l)) {
				directions.add(3);
				if (findSafeMove(row, col + 1, l)) {
					return true;
				}
				directions.remove(directions.size()-1);
				visited[row][col] = false;
			}

			if (isSafe(row, col, Labyrinth.UP, l)) {
				directions.add(0);
				if (findSafeMove(row - 1, col, l)) {
					return true;
				}
				directions.remove(directions.size()-1);
				visited[row][col] = false;
			}

			if (isSafe(row, col, Labyrinth.LEFT, l)) {
				directions.add(2);
				if (findSafeMove(row, col - 1, l)) {
					return true;
				}
				directions.remove(directions.size()-1);
				visited[row][col] = false;
			}
		}
		return false;
	}
	
	public static int[] solve(Labyrinth l) {
		findSafeMove(0, 0, l);
		directions.toArray(moves);
		
		solution = new int[moves.length];
		
		for (int i = 0; i < moves.length; i++)
			solution[i] = moves[i];
		
		return solution;
	}

	public static boolean isSafe(int row, int col, int[] x, Labyrinth l) {
		int tempRow = row + x[0];
		int tempCol = col + x[1];

		if (l.isValid(tempRow, tempCol) && l.isStone(tempRow, tempCol) && !visited[tempRow][tempCol]) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void main(String[] args) {
		totalCols = (int)(Math.random()*15);
		totalRows = (int)(Math.random()*15);

		visited = new boolean[totalRows][totalCols];

		Labyrinth crete = new Labyrinth(totalRows,totalCols);
		crete.printGrid();

		solve(crete);

		for (int i = 0; i < solution.length; i++) {
			System.out.println(solution[i]);
		}
	}
}