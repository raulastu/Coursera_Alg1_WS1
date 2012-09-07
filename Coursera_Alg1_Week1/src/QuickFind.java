import java.util.Arrays;



public class QuickFind extends UnionFind {
	public QuickFind() {
		
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
		return data[i];
	}
	
	public void union(int p,int q){
		int rootFirst = data[p];
		int rootSecond = data[q];
		for (int i = 0; i < data.length; i++) {
			if(data[i]==rootFirst)
				data[i]=rootSecond;
		}
		super.union(p, q);
	}
	
	public boolean connect(int p,int q){
		return data[p]==data[q];
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
		new QuickFind().test1();
	}
}
