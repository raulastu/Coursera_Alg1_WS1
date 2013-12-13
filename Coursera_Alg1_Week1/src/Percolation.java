import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Percolation {

	private int[][] grid;
	// private int[] grid1;
	private WeightedQuickUnionUF w;
	// private WeightedQuickUnionUF w2;
	private int N;
	private int[] di = { 1, -1, 0, 0 };
	private int[] dj = { 0, 0, 1, -1 };
	private boolean percolates = false;
	private int root = -1;

	public Percolation(int N) { // create N-by-N grid, with all sites blocked
		// grid=new int[N][N];
		this.N = N;
		grid = new int[N][N];
		w = new WeightedQuickUnionUF(N * N);
		// w2 = new WeightedQuickUnionUF(N * N);
		for (int i = 1; i < N; i++) {
			w.union(i, i - 1);
			// w2.union(i, i - 1);
			// w.union(N * (N - 1) + i, N * (N - 1) + i - 1);
		}
		root = w.find(0);
	}

	private HashSet<Integer> hs = new HashSet<>();
	
	public void open(int x1, int y1) { // open site (row i, column j) if it is
										// not// already
		// grid[x][y]=1;
		if (!inBound(x1 - 1) || !inBound(y1 - 1))
			throw new IndexOutOfBoundsException();
		int x = x1 - 1;
		int y = y1 - 1;
		int me = N * x + y;
		grid[x][y] = 1;
		// boolean gotConnected = false;

		for (int i = 0; i < di.length; i++) {
			int X = x + di[i];
			int Y = y + dj[i];
			int you = N * X + Y;
			if (X >= 0 && X < N && Y >= 0 && Y < N && grid[X][Y] == 1) { // &&
				// grid[X][Y]==1
				w.union(me, you);
				
				// w2.union(me, you);
			}
		}
		if(me>=N*(N-1)){
			hs.add(w.find(me));
		}
//		 pr(grid,root,w.find(me));
//		 pr(w.find(me),w.find(root));
//		if (me >= N * (N - 1)) {
//			if (w.find(me) == w.find(root)) {
//				percolates = true;
//			}
//		}
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
		// w.find(you)
		return w.connected(0, you);
	}

	public boolean percolates() { // does the system percolate?
		
		if (N == 1) {
			return grid[0][0] == 1;
		}
		if (N == 2) {
			if (grid[0][0] == 1 && grid[1][0] == 1)
				return true;
			if (grid[0][1] == 1 && grid[1][1] == 1)
				return true;
			return false;
		}
//		if (percolates)
//			return percolates;
		root=w.find(root);
		for (int i = 0; i < N; i++) {
			if(grid[N-1][i]==1){
				int myroot=w.find(N*(N-1)+i);
				if(myroot==root)
					return true;
				while(i+1<N && grid[N-1][i+1]==1)i++;
			}	
		}
		return false;
//		return w.connected(0, N * N - 1);
	}

	public static void main(String[] args) {
		// p.isOpen(-1, 5);
		// p.open(0, 0);
		// p.open(1, 0);
		// // p.open(1, 0);
		// printPass(p.percolates());
		 test6();
		 test1();
		 test_1();
		test2();
		test4();
		test5();

		test7();
		test8();
		test9();
		testFull1();
		testFull2();
		testFull3();
		testFull4();
		testFull5(5);
		testFull20();
		// test3();
	}

	private static void testFull5(int nro) {
		// 25
		// 1 7
		// 1 8
		// 1 9
		// 1 17
		// 1 18
		// 1 19
		// 2 5
		// 2 6
		// 2 7
		Percolation p = new Percolation(25);
		p.open(1, 7);
		p.open(1, 8);
		p.open(1, 9);
		p.open(1, 17);
		p.open(1, 18);
		p.open(1, 19);
		p.open(2, 5);
		p.open(2, 6);
		p.open(2, 7);
		// p.open(1, 0);
		String cas = "Full 5";
		if (p.isFull(2, 5))
			printPass(cas + "is Full Passed");
		else
			printFail(cas + "Failed");
	}

	private static void test6() {
		Percolation p = getGrid("src/input6.txt");
		if (p.percolates())
			System.out.println("percolates input6 Passed");
		else
			printFail("percolates input6 Failed");
	}

	private static void testFull4() {
		// 6
		// 1 6
		// 2 6
		// 3 6
		// 4 6
		// 5 6
		// 5 5
		// 4 4
		// 3 4
		// 2 4
		// 2 3
		// 2 2
		// 2 1
		// 3 1
		// 4 1
		// 5 1
		// 5 2
		// 6 2
		// 5 4
		Percolation p = new Percolation(6);
		p.open(1, 6);
		p.open(2, 6);
		// p.open(1, 0);
		if (p.isFull(2, 6))
			printPass("is Full Passed");
		else
			printFail("Failed");
	}

	private static void testFull20() {
		Percolation p = getGrid("src/input20.txt");
		if (!p.isFull(18, 1))
			System.out.println("is not Full Passed");
		else
			printFail("testFull20() Failed");
	}

	private static Percolation getGrid(String path) {
		Percolation p = null;
		try {
			int count = 0;
			Scanner sc = new Scanner(new File(path));
			int n = sc.nextInt();
			p = new Percolation(n);
			while (sc.hasNext()) {
				count++;
				int a = sc.nextInt();
				int b = sc.nextInt();
				p.open(a, b);
			}
			System.out.println(count + " calls ");
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	private static void testFull3() {
		Percolation p = new Percolation(3);
		// 3
		// 1 3
		// 2 3
		// 3 3
		// 3 1
		// 2 1
		// 1 1

		p.open(1, 3);
		p.open(2, 3);
		p.open(3, 3);
		p.open(3, 1);
		// p.open(1, 0);
		if (!p.isFull(3, 1))
			System.out.println("is not Full Passed");
		else
			printFail("Failed");
	}

	private static void testFull1() {
		Percolation p = new Percolation(2);
		p.open(1, 1);
		// p.open(2, 2);
		// p.open(1, 0);
		if (p.isFull(1, 1))
			printPass("is Full Passed");
		else
			printFail("Failed");
	}

	private static void testFull2() {
		Percolation p = new Percolation(2);
		p.open(1, 1);
		p.open(2, 2);
		// p.open(1, 0);
		if (!p.isFull(2, 2))
			printPass("is not Full Passed");
		else
			printFail("testFull2() Failed!!!!");
	}

	private static void test9() {
		Percolation p = new Percolation(4);
		p.open(1, 4);
		p.open(2, 3);
		p.open(2, 2);
		p.open(2, 4);
		p.open(3, 2);
		p.open(3, 1);
		p.open(4, 1);
		// p.open(3, 3);
		// p.open(2, 2);
		// p.open(1, 0);
		if (p.percolates())
			printPass("Passed");
		else
			printFail("test9() Failed!!!!!!!!!!!");
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
			printPass("Passed");
		else
			printFail("Failed");
	}

	private static void test7() {
		Percolation p = new Percolation(4);
		p.open(1, 1);
		p.open(1, 2);
		p.open(2, 3);
		// p.open(2, 2);
		// p.open(1, 0);
		if (!p.percolates())
			printPass("Passed");
		else
			printFail("Failed");
	}

	private static void test_1() {
		Percolation p = new Percolation(1);
		p.open(1, 1);
		// p.open(2, 2);
		// p.open(1, 0);
		if (p.percolates())
			printPass("Passed");
		else
			printFail("Failed");
	}

	private static void test5() {
		Percolation p = new Percolation(2);
		// p.open(1, 1);
		// p.open(2, 2);
		// p.open(1, 0);
		if (!p.percolates())
			printPass("Passed");
		else
			printFail("Failed");
	}

	private static void test4() {
		Percolation p = new Percolation(2);
		// p.open(1, 1);
		// p.open(2, 2);
		// p.open(1, 0);
		if (!p.isFull(1, 1))
			printPass("is Full Passed");
		else
			printFail("Failed");
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
			printPass("Passed");
		else
			printFail("Failed");
	}

	private static void test2() {
		Percolation p = new Percolation(2);
		p.open(1, 1);
		p.open(2, 2);
		// p.open(1, 0);
		if (!p.percolates())
			printPass("Passed");
		else
			printFail("Failed");
	}

	private static void printPass(String s) {
		System.out.println(s);
	}

	private static void printFail(String s) {
		System.out.println(s);
	}

	private static void pr(Object... ob) {
		System.err.println(Arrays.deepToString(ob).replaceAll("]", "]\n"));
	}
}