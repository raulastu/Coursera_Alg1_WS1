public class Sorting {
	static void selection(String input) {
		char[] ch = input.replace(" ", "").toCharArray();
		int nroexh = 0;
		for (int i = 0; i < ch.length; i++) {
			char min = ch[i];
			int indexSelected=0;
			for (int j = i + 1; j < ch.length; j++) {
				if (ch[j] < min) {
					min=ch[j];
					indexSelected=j;
				}
			}
			if(indexSelected!=i){
				exch(ch,i,indexSelected);
				nroexh++;
				if (nroexh <= 4) {
					System.err.println(new String(ch));
				}
			}
			
		}		
//		System.err.println(new String(ch));
		System.err.println();
	}
	static void selection1(String input) {
		char[] ch = input.replace(" ", "").toCharArray();
		int nroexh = 0;
		for (int i = 0; i < ch.length; i++) {
			for (int j = i + 1; j < ch.length; j++) {
				if (ch[j] < ch[i]) {
					
					exch(ch,i,j);
					nroexh++;
					if (nroexh <= 6) {
						System.err.println(new String(ch));
					}
				}
			}
		}		
//		System.err.println(new String(ch));
		System.err.println();
	}
	
	static void insertion(String input){
		char[] ch = input.replace(" ", "").toCharArray();
		int nroexh = 0;
		for (int i = 1; i < ch.length; i++) {
			
			int xi=i;
			int j=i-1;
			while(j>=0 && ch[xi]<ch[j] ){
				exch(ch,xi,j);
				xi--;
				j--;
				nroexh++;
				if (nroexh <= 6) {
					System.err.println(new String(ch));
				}
			}			
		}
//		System.err.println(new String(ch));
		System.err.println();
	}
	
	static void shellsort(String input, int h){
		char[] ch = input.replace(" ", "").toCharArray();
		int nroexh = 0;
		for (int i = h; i < ch.length; i++) {
			
			int xi=i;
			int j=i-h;
			while(j>=0 && ch[xi]<ch[j] ){
				exch(ch,xi,j);
				xi-=h;
				j-=h;
				nroexh++;
				if (nroexh == 6) {
					System.err.println(new String(ch));
				}
			}			
		}
		System.err.println(new String(ch));
		
	}
	static void exch(char[] ch, int i, int j){
		char temp = ch[j];
		ch[j] = ch[i];
		ch[i] = temp;
	}

	public static void main(String[] args) {
//		selection("J F N O C A X Y W S");
////		selection1("F M N R S O V H Z U");
//		insertion("F M N R S O V H Z U");
//		shellsort("X B P D S F L I N C",4);

		selection("W J A T V G O I P U");
		insertion("C E M R W S O Y A U");
		shellsort("G D S J U B N H C M",4);
	}
}
