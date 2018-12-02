
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static char [] inp;
	static int memo [][];
	public static int len (int left , int right)
	{
		if(left == right) return 1;
		if(left+1 == right)
			return inp[left] == inp[right] ? 2:1;
		if(memo[left][right]!=-1 )
			return memo[left][right];
		if(inp[left]== inp [right])
			return memo[left][right]=2+len (left+1 , right-1);
		else return memo[left][right]=Math.max(len(left+1,right),len(left,right-1));
	}
	public static void main(String[] args) throws IOException {
			Scanner sc = new Scanner(System.in);
			int t = Integer.parseInt(sc.nextLine());
			while(t-->0)
			{
				inp = sc.nextLine().toCharArray();
				if(inp.length ==0){System.out.println(0);continue;}
				memo = new int[inp.length+1][inp.length+1];
				for(int i = 0 ; i < inp.length ; i ++)
					Arrays.fill(memo[i], -1);
				System.out.println(len(0,inp.length-1));
			}
		}
	}







