import java.util.Arrays;



public class WeightedQuickUnion extends UnionFind{
	
	int sz[];
	
	public WeightedQuickUnion(int len) {
		sz=new int[len];
		Arrays.fill(sz, 1);
//		test2();
	}
	
	void test1(){
//		printData();
		union(1,2);
//		printData(); 
		union(9,5);
		union(7,3);
		union(0,8);
		union(0,5);
		union(0,6);
//		printData();
	}
	
	public void test2() {
		union(4,3);
		union(1,6);
		union(3,2);
		union(0,7);
		union(4,8);
		union(3,9);
		union(0,1);
		union(6,9);
		union(5,2);
//		printData();
	}
	
	int find(int i){
		return root(i);
	}
	
	public void union(int p,int q){
		int firstTree=root(p);
		int secondTree=root(q);
		if(sz[firstTree]<sz[secondTree]){
			data[firstTree]=secondTree;
			sz[secondTree]+=sz[firstTree];
		}else{
			data[secondTree]=firstTree;
			sz[firstTree]+=sz[secondTree];
		}
//		printSizes();
		super.union(p, q);
	}
	
	void printSizes(){
		String s = Arrays.deepToString(new Object[]{sz});
		s=s.replace(",", "");
		s=s.replaceAll("\\[", "");
		s=s.replaceAll("\\]", "");
		System.err.println("size:"+s);
	}

	
	public boolean connected(int p,int q){
		return root(p)==root(q);
	}
	
	int root(int p){
		while(data[p]!=p){
			return root(data[p]);
		}
		return p;
	}
	
	void print(Object... ob){
		System.err.println(Arrays.deepToString(ob));
	}
	
	void printData(){
		String s = Arrays.deepToString(new Object[]{data});
		s=s.replace(",", "");
		s=s.replaceAll("\\[", "");
		s=s.replaceAll("\\]", "");
		System.err.println(s);
	}
	
	public static void main(String[] args) {
		new WeightedQuickUnion(10).test2();
	}
}
