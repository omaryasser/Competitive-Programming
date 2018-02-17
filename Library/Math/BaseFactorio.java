
import java.util.Arrays;

public class BaseFactorio {
	static long fac[];

	//
	public static long KthPerm(char [] s){ //returns the earliest occurrence of this permutation.
		// init fac;
		int n=s.length;
		fac= new long [n];
		fac[0]= 1;
		for (int i = 1; i < n; i++) { //Don't forget to mod
			fac[i]=i*fac[i-1];
		}

		long res=0;



		char [] sorted= s.clone();
		Arrays.sort(sorted);
		boolean [] taken= new boolean [n];

		for (int i = 0; i < n; i++) {
			int pos=0;
			for (int j = 0; j < n; j++) {
				if(!taken[j]){

					if(sorted[j]==s[i]){
						taken[j]=true;
						break;
					}
					else
						pos++;
				}
			}
			res+= fac[(n-1-i)]*pos;
		}

		return res+1;
	}

	public static void main(String[] args) {

		char [] s=new char [] {'a','e','c'};

		System.out.println(KthPerm(s));
	}

}
