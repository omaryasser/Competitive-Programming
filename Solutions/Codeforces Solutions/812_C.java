import java.io.*;
import java.util.*;

public class Sagheer_And_Nubian_Market {
	static int n , S;
	static int[]items;
	static int maxUsed;
	static long minMoney;
	static long currMoney;
	public static void main(String[]args) throws IOException {
		Scanner sc = new Scanner(System.in);
		n =sc.nextInt();
		S =sc.nextInt();
		items= new int[n+1];
		
		for(int i = 1 ; i <= n ; i++)items[i]=sc.nextInt();
		
		BS();
		System.out.println(maxUsed + " "+minMoney);
	}
	
	
	
	static int valid(int k) {
		int sovUSED=0;
		currMoney=0;
		long []arr2=new long[n+1];
		for(int i = 1 ; i <= n;  i++) arr2[i]=1l*items[i]+1l*(1l*k*i);
		
		shuffle(arr2);
		Arrays.sort(arr2);
		
		for(int i = 1 ;i <= n ;i++) {
			if(currMoney+arr2[i]>S) break;
if(sovUSED == k) break;
			sovUSED++;
			currMoney+=arr2[i];
		}
		return sovUSED;
	}
	
	
	static void BS() {
		int st=0,end=n,mid=0,curr;
		while(st<=end) {
		
			mid =st + (end-st+1)/2;
			
			curr=valid(mid); //how many items are used with k factor
			
			if(curr>=mid) {
				if(mid>maxUsed) {maxUsed=mid;minMoney=currMoney;}
				else if(mid==maxUsed) {minMoney=Math.min(minMoney, currMoney); }
				st=mid+1;
			}else end=mid-1;
		}
	}
	
	static void shuffle(long[] a)
	{
		int n = a.length;
		for(int i = 0; i < n; i++)
		{
			int r = i + (int)(Math.random() * (n - i));
			long tmp = a[i];
			a[i] = a[r];
			a[r] = tmp;
		}
	}
	
	
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
		
		public double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}
		
		public boolean ready() throws IOException {return br.ready();}


	}

}