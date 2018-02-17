import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class KMP2D {

	static char T[][] , P[];
	static int b[] , r , c , m;
	static TreeSet<Pair> answers;

	public static void kmpPreProcess()
	{
			m = P.length ;
			b = new int[m + 1];
			int i = 0 , j = -1 ; b[0] = -1;
			while(i < m)
			{
					while(j >= 0 && P[i] != P[j]) j = b[j];
					i++; j++;
					b[i] = j;
			}
	}

	public static void kmpSearch(int i,int j,int dx,int dy)
	{
//		i =0 	,j=0,
		int	k=0;
			r = T .length ;
			c = T[i].length;
			while(i < r && i>=0 && j>=0 && j<c)
			{

					while(k >= 0 && T[i][j] != P[k]) k = b[k];
					i+=dx; j+=dy; k++;
					if(k == m) {
//							System.out.println( (1+i - k*dx)+" "+(1+j - k*dy));
							answers.add(new Pair((1+i - k*dx),(1+j - k*dy)));
							//storing the output for sorting
						k = b[k];
					}

			}
	}

	public static void main(String[] args) throws IOException {
		Scanner sc= new Scanner(System.in );
		PrintWriter out= new PrintWriter(System.out);
		
		int t=sc.nextInt();
		int tc=0;
		while(tc++<t){
			if(tc!=1){
				out.println();
			}
			r=sc.nextInt();
			c=sc.nextInt();
			
			T= new char [r][c];
			for (int i = 0; i < r	; i++) { //INPUT
				String s=sc.next().toLowerCase();
					T[i]=  s.toCharArray();
			}
			int q=sc.nextInt();
			while(q-->0){ 					//QUERIES
				answers= new TreeSet<Pair>();
				P=sc.next().toLowerCase().toCharArray();
				m= P.length+1;
				kmpPreProcess();
//				kmpSearch(i, j, dx, dy);
				for (int i = 0; i < r; i++) {
				//additional checks on size of p may be needed for pruning
						kmpSearch(i, 0, 0, 1); //right
						kmpSearch(i, 0, 1, 1); 
						kmpSearch(i, 0, -1, 1);
						kmpSearch(i, c-1, 0, -1); //left
						kmpSearch(i, c-1, -1, -1);
						kmpSearch(i, c-1, 1, -1);
				}
				for (int i = 0; i < c; i++) {
					kmpSearch(0, i, 1, 1); 
					kmpSearch(0, i, 1, -1);
					kmpSearch(0, i, 1, 0); // down
					kmpSearch(r-1, i, -1, 1);
					kmpSearch(r-1, i, -1, 0); //up
					kmpSearch(r-1, i, -1, -1);
					
				}
				out.println(answers.first());
			}
		}
		
		out.flush();
		out.close();
	}
	static class Pair implements Comparable<Pair>{
		int x,y;
		
		Pair(int a,int b){
			x=a;
			y=b;
		}

		public int compareTo(Pair o) {
			if(x!=o.x)
				return x-o.x;
			return y-o.y;
		}
		public String toString(){
			return x+" "+y;
		}
		
	}
}
