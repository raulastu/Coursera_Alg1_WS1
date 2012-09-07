import java.util.Arrays;



public class QuickUnion extends UnionFind{
	
	public QuickUnion() {
	}
	
	void test1(){
		union(1,2); 
		union(9,5);
		union(7,3);
		union(0,8);
		union(0,5);
		union(0,6);
	}
	
	int find(int i){
		return root(i);
	}
	
	public void union(int p,int q){
		data[root(p)]=root(q);
		super.union(p, q);
	}
	
	public boolean connect(int p,int q){
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
	

	
	public static void main(String[] args) {
		new QuickUnion();
	}
}
