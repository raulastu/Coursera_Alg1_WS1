package firstAttempt80;
import java.util.HashSet;

public class Percolation {

	private int[][] grid;
	// private int[] grid1;
	WeightedQuickUnionUF w;
	private int N;
	private int[] di = { 1, -1, 0, 0 };
	private int[] dj = { 0, 0, 1, -1 };
	private int xLastOpenend = -1;
	private int yLastOpenend = -1;
	private boolean percolates = false;
	private HashSet<Integer> openedNodes;

	public Percolation(int N) { // create N-by-N grid, with all sites blocked
		// grid=new int[N][N];
		this.N = N;
		grid = new int[N][N];
		w = new WeightedQuickUnionUF(N * N);
		openedNodes = new HashSet<Integer>();
		// for (int i = 0; i < grid.length; i++) {
		// Arrays.fill(grid[i], 1);
		// }
	}

	public void open(int x1, int y1) { // open site (row i, column j) if it is
										// not// already
		// grid[x][y]=1;
		if (!inBound(x1 - 1) || !inBound(y1 - 1))
			throw new IndexOutOfBoundsException();
		int x = x1 - 1;
		int y = y1 - 1;
		int me = N * x + y;
		grid[x][y] = 1;
		xLastOpenend = x;
		yLastOpenend = y;
		boolean gotConnected=false;
		for (int i = 0; i < di.length; i++) {
			int X = x + di[i];
			int Y = y + dj[i];
			int you = N * X + Y;
			if (X >= 0 && X < N && Y >= 0 && Y < N && grid[X][Y] == 1) {
				// && grid[X][Y]==1
				gotConnected=true;
				w.union(me, you);
			}
		}
		if(gotConnected){
			openedNodes.add(me);
		}
	}

	private boolean inBound(int a) {
		return a >= 0 && a < N;
	}

	public boolean isOpen(int xi, int xj) { // is site (row i, column j) open?
		int i = xi - 1;
		int j = xj - 1;
		if (!inBound(i) || !inBound(j))
			throw new IndexOutOfBoundsException();
		return grid[i][j] == 1;
	}

	public boolean isFull(int xi, int xj) { // is site (row i, column j) full?
		int i = xi - 1;
		int j = xj - 1;
		if (!inBound(i) || !inBound(j))
			throw new IndexOutOfBoundsException();

		if (grid[i][j] == 0)
			return false;

		int you = i * N + j;
		for (int j2 = 0; j2 < N; j2++) {
			if (w.connected(j2, you))
				return true;
		}
		return false;
	}

	public boolean percolates() { // does the system percolate?
		if (percolates)
			return percolates;
		if(N==1){
			return grid[0][0]==1;
		}
		for (int lastOneOpened : openedNodes) {
			for (int top = 0; top < N; top++) {

				// if(grid[0][top]!=1){
				// // nextOpen=false;
				// continue;
				// }
				if (top > 0 && grid[0][top - 1] == 1) {
					continue;
				}
				if (grid[0][top] != 1)
					continue;
				if (w.connected(top, lastOneOpened)) {
					for (int j = 0; j < N; j++) {
						if (j > 0 && grid[N - 1][j - 1] == 1) {
							continue;
						}
						if (grid[N - 1][j] != 1){
							continue;
						}
						int bottom = (N - 1) * (N) + j;

						if(w.connected(bottom, lastOneOpened)) {
							percolates = true;
							break;
						}
					}
					break;
				}

			}
		}
		openedNodes.clear();
		return percolates;
	}

	public static void main(String[] args) {
		Percolation p = new Percolation(10);
		// p.isOpen(-1, 5);
		// p.open(0, 0);
		// p.open(1, 0);
		// // p.open(1, 0);
		// System.err.println(p.percolates());
		test1();
		test2();
		test4();
		test5();
		test6();
		test7();
		test8();
		// test3();
	}

	private static void test8() {
		Percolation p = new Percolation(4);
		p.open(1, 1);
		p.open(2, 1);
		p.open(3, 1);
		p.open(4, 1);
		p.open(3, 3);
		// p.open(2, 2);
		// p.open(1, 0);
		if (p.percolates())
			System.err.println("Passed");
		else
			System.err.println("Failed");
	}

	private static void test7() {
		Percolation p = new Percolation(4);
		p.open(1, 1);
		p.open(1, 2);
		p.open(2, 3);
		// p.open(2, 2);
		// p.open(1, 0);
		if (!p.percolates())
			System.err.println("Passed");
		else
			System.err.println("Failed");
	}

	private static void test6() {
		Percolation p = new Percolation(1);
		p.open(1, 1);
		// p.open(2, 2);
		// p.open(1, 0);
		if (p.percolates())
			System.err.println("Passed");
		else
			System.err.println("Failed");
	}

	private static void test5() {
		Percolation p = new Percolation(2);
		// p.open(1, 1);
		// p.open(2, 2);
		// p.open(1, 0);
		if (!p.percolates())
			System.err.println("Passed");
		else
			System.err.println("Failed");
	}

	private static void test4() {
		Percolation p = new Percolation(2);
		// p.open(1, 1);
		// p.open(2, 2);
		// p.open(1, 0);
		if (!p.isFull(1, 1))
			System.err.println("Passed");
		else
			System.err.println("Failed");
	}

	private static void test3() {
		Percolation p = new Percolation(10);
		// p.isOpen(-1, 5);
		p.isOpen(5, -1);
	}

	private static void test1() {
		Percolation p = new Percolation(2);
		p.open(1, 1);
		p.open(2, 1);
		// p.open(1, 0);
		if (p.percolates())
			System.err.println("Passed");
		else
			System.err.println("Failed");
	}

	private static void test2() {
		Percolation p = new Percolation(2);
		p.open(1, 1);
		p.open(2, 2);
		// p.open(1, 0);
		if (!p.percolates())
			System.err.println("Passed");
		else
			System.err.println("Failed");
	}
}