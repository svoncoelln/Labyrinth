import java.util.ArrayList;

public class Noah {

	static boolean[][] visited = new boolean[3][3];
	int count;
	static int totalCols;
	static int totalRows;
	static ArrayList<Integer> directions;
	static int [][] solution;

	public Noah() {

	}

	public void ariadnesThread(int row, int col, Labyrinth l) {
		visited[row][col] = true;

//		if (isSafe(row, col, Labyrinth.UP, l)) {
//			System.out.println("up");
//			//directions.add(0);
//			ariadnesThread(row, col, l);
//		}
		if (isSafe(row, col, Labyrinth.DOWN, l)) {
			System.out.println("down");
			//directions.add(1);
			ariadnesThread(row, col, l);
		}
//		if (isSafe(row, col, Labyrinth.LEFT, l)) {
//			System.out.println("left");
//			//directions.add(2);
//			ariadnesThread(row, col, l);
//		}
		if (isSafe(row, col, Labyrinth.RIGHT, l)) {
			System.out.println("right");
			//directions.add(3);
			ariadnesThread(row, col, l);
		}
	}

	public boolean isSafe(int row, int col, int[] x, Labyrinth l) {
		int tempRow = row + x[0];
		int tempCol = col + x[1];

		if (l.isValid(tempRow, tempCol) && l.isStone(tempRow, tempCol)) {
			System.out.println("true");
			return true;
		}
		else {
			System.out.println("false");
			return false;
		}
	}

	public static void main(String[] args) {
		Labyrinth crete = new Labyrinth(3,3);
		Noah ripke = new Noah();

		ripke.ariadnesThread(0,0, crete);
		crete.printGrid();
		System.out.println(directions.get(0));
	}
}