//719424 
class TimingTest {
	static int tabsNumber= 3;
	public static void main(String []args){
		Timing t = new Timing();
		double x1=0,x2=0,seconds1=0,seconds2=0;
		double y1=0,y2=0;
		double ratio=0.0;
		int start = 1;
		int N = start;
		System.err.println("N"+tt()+"seconds"+tt()+"ratio"+tt()+"lg ratio");
		for (int i = 1;i<=20; i++) {
			Stopwatch stopwatch=new Stopwatch();
			int seed = Integer.parseInt(args[0]);
			t.trial(N,seed);
			double elapsed = stopwatch.elapsedTime();
			double lgT=Math.log(elapsed);
//			System.err.println("for N = "+N+"lgN="+i+ " lgT="+lgT);
			if(i==1){
				x1=i;
				y1=lgT;
				seconds2=elapsed;
			}else{
				x1=x2;
				y1=y2;
				x2=i;
				y2=lgT;
				seconds1=seconds2;
				seconds2=elapsed;
				ratio=seconds2/seconds1;
				double slope=(y2-y1)/(x2-x1);
//				System.err.println("slope"+slope);
			}
			System.err.println(N+tt()+elapsed+tt()+ratio+tt()+(Math.log(ratio))/Math.log(2));
			N*=2;
		}
	}
	public static String tt(){
		int t = tabsNumber;;
		String n = "";
		for (int i = 0; i < t; i++) {
			n+="\t";
		}
		return n;
	}
}



//16+16+3+4+216
//sqrt(4096**2+16**2)