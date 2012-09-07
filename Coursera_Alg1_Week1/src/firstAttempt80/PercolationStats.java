package firstAttempt80;
import java.util.ArrayList;
import java.util.Collections;

public class PercolationStats {
	private double mean;
	private double stddev;

	public PercolationStats(int N, int T) // perform T independent computational
											// experiments on an N-by-N grid
	{
		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException();

		class Node {
			private int x, y;

			public Node(int x, int j) {
				this.x = x;
				this.y = j;
			}

			@Override
			public String toString() {
				//
				return x + "" + y;
			}
		}
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				nodes.add(new Node(i, j));
			}
		}
		double sumx = 0;
		ArrayList<Double> xs = new ArrayList<Double>();

		int unionTotalCalls = 0;
		int findTotalCalls = 0;
		for (int i = 0; i < T; i++) {
			Collections.shuffle(nodes);
			Node[] d = new Node[nodes.size()];
			nodes.toArray(d);
			// System.err.println(i);
			Percolation p = new Percolation(N);
			// System.err.println(nodes);
			double x = 0;
			double start = System.currentTimeMillis();
			// Stopwatch stopwatch=new Stopwatch();
			for (int k = 0; k < nodes.size(); k++) {
				p.open(d[k].x, d[k].y);
				// System.err.println();
				if (p.percolates()) {
					x = (k + 1) / (double) nodes.size();
					xs.add(x);
					sumx += x;
					// System.err.println(x+" "+k);
					break;
				}
				// System.err.println((System.currentTimeMillis() - start1) /
				// 1000.0);
			}
			unionTotalCalls += p.w.unionCallCount;
			findTotalCalls += p.w.findCallCount;

			System.err.println((System.currentTimeMillis() - start) / 1000.0);
		}
		double u = sumx / (double) T;
		double o2 = 0;
		for (double double1 : xs) {
			o2 += (double1 - u) * (double1 - u);
		}
		o2 = o2 / (double) (T - 1);
		double o = Math.sqrt(o2);
		double i1 = u - (1.96 * o) / Math.sqrt(T);
		double i2 = u + (1.96 * o) / Math.sqrt(T);
		mean = u;
		stddev = o;
		System.err.println(u);
		System.err.println(o);
		System.err.println(i1 + " " + i2);
		System.err.println("N = " + N + " union=" + unionTotalCalls + " find="
				+ findTotalCalls);
	}

	public double mean() // sample mean of percolation threshold
	{
		return mean;
	}

	//
	public double stddev() // sample standard deviation of percolation threshold
	{
		return stddev;
	}

	public static void main(String[] args) // test client, described below
	{
		// int N = Integer.parseInt(args[0]);
		// int T = Integer.parseInt(args[1]);
		//
		// if (N < 0 || T < 0)
		// throw new IllegalArgumentException();
		// new PercolationStats(N, T);
		new PercolationStats(8, 1);
		new PercolationStats(32, 1);
		new PercolationStats(128, 1);
		new PercolationStats(512, 1);
//		new PercolationStats(200, 100);

		/*
		 * N = 8 union=32 find=152 N = 32 union=775 find=11750 N = 128
		 * union=10966 find=741222
		 */

	}
}
