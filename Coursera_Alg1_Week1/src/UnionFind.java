import java.util.Arrays;


public abstract class UnionFind {

	int data[];
	int len=10;
	
	public UnionFind() {
		initialize();
	}
	void initialize(){
		data=new int[len];
		for (int i = 0; i < data.length; i++) {
			data[i]=i;
		}
	}
	
	public void union(int a,int b){
		System.err.print(a+"-"+b+": ");
//		printData();
	};
	
	public boolean connect(int a,int b){
		return false;
	}
	
	public static String test(UnionFind ob){
		String res = "";
		for (int i = 0; i < ob.data.length; i++) {
			for (int j = 0; j < ob.data.length; j++) {
				if(ob.connect(i, j)){
					res+="1";
				}else
					res+="0";
			}
		}
		return res;
	}
	void printData(){
		String s = Arrays.deepToString(new Object[]{data});
		s=s.replace(",", "");
		s=s.replaceAll("\\[", "");
		s=s.replaceAll("\\]", "");
		System.err.println(s);
	}
	/*
	 * sample input = 4-3 1-6 3-2 0-7 4-8 3-9 0-1 6-9 5-2 
	 */
	public static void testWithString(UnionFind ob, String input){
		ob.initialize();
		String [] ar = input.trim().split(" ");
		for (int i = 0; i < ar.length; i++) {
			String strPQ[]= ar[i].split("-");
			int p = Integer.parseInt(strPQ[0]);
			int q = Integer.parseInt(strPQ[1]);
			ob.union(p,q);
		}
	}
	
	public static void main(String[] args) {
		QuickFind qf = new QuickFind();
//		QuickUnion qu = new QuickUnion();
//		WeightedQuickUnion wqu = new WeightedQuickUnion();
//		System.err.println(test(qf));
//		System.err.println(test(qu));
//		testWithString(wqu,"0-2 0-6 0-4 5-8 7-3 3-8 0-7 6-9 1-7");
//		testWithString(qf,"7-1 2-1 3-5 8-0 2-5 4-2"); 
	}
}
