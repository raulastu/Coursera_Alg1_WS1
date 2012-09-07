import java.util.Arrays;

public class PercolationWithFloodFill {

	private int[][] grid;
	private int N;

	public PercolationWithFloodFill(int N) { // create N-by-N grid, with all
												// sites blocked
		grid = new int[N][N];
		this.N = N;
		// for (int i = 0; i < grid.length; i++) {
		// Arrays.fill(grid[i], 1);
		// }
	}

	private boolean inBound(int a) {
		return a >= 0 && a < N;
	}

	public void open(int x, int y) { // open site (row i, column j) if it is //
										// not// already

		if (!inBound(x - 1) || !inBound(y - 1))
			throw new IndexOutOfBoundsException();
		grid[x-1][y-1] = 1;
	}

	public boolean isOpen(int i, int j) { // is site (row i, column j) open?
		return grid[i][j] == 1;
	}

	public boolean isFull(int i, int j) { // is site (row i, column j) full?
		return grid[i][j] == 0;
	}

	public boolean percolates() { // does the system percolate?
		int[][] grid1 = new int[grid.length][grid.length];
		for (int i = 0; i < grid1.length; i++) {
			if (grid[0][i] == 1)
				floodfill(grid1, 0, i);
		}
		for (int i = 0; i < grid1.length; i++) {
			if (grid1[grid1.length - 1][i] == 1)
				return true;
		}
		return false;
	}

	private int di[] = { 1, -1, 0, 0 };
	private int dj[] = { 0, 0, 1, -1 };

	private void floodfill(int grid1[][], int x, int y) {
		grid1[x][y] = 1;
		for (int i = 0; i < di.length; i++) {
			int X = x + di[i];
			int Y = y + dj[i];
			if (X >= 0 && X < grid.length && Y >= 0 && Y < grid.length
					&& grid[X][Y] == 1 && grid1[X][Y] == 0) {
				floodfill(grid1, X, Y);
			}
		}
	}

	// public static void main(String[] args) {
	// Percolation p = new Percolation(2);
	// p.open(0, 0);
	// // p.open(1, 0);
	// p.open(1, 0);
	// System.err.println(p.percolates());
	//
	// // p.open(11, 11);
	// }
}