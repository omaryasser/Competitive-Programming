import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	
	static boolean primes [] ;
	static void seive (int n ) {
		primes = new boolean[n+1];
        Arrays.fill(primes, true);
        primes[0]=primes[1]=false;
        for(int i =0 ; i*i<=n; i++)
            if(primes[i])
                for(int j =2 ;i*j <=n ; j++)
                    primes [i*j]=false;

	}
	static boolean eq (char [] a , char [] b) {
		for (int i = 0 ; i < 4 ; ++ i)
			if (a[i] !=  b[i]) return false;
		return true;
	}
	
	static int toNum (int arr [] ) {
		return 1000 * (arr[0]) + 100 * (arr[1] ) 
				+ 10 * (arr[2]) + (arr[3] );
	}
	static int bfs (int s , int e) {
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		int dist [] = new int[10001];
		Arrays.fill(dist, -1);
		dist[s] = 0;
		while (!q.isEmpty()) {

			int cur = q.poll();
			if (cur == e) return dist[cur];
			
			int arr [] = toArr (cur);
			int [] newArr = arr.clone();
			
			for (int i = 0 ; i < 4 ; ++ i) {
				for (int j = 0 ; j < 10 ; ++ j) {
					if (i == 0 && j == 0) continue;
					newArr [i] = j;
					int num = toNum(newArr);
					if (!primes[num]) continue;
					if (dist[num] == -1) {
						dist[num] = dist[cur] + 1;
						q.add(num);
					}
					
				}
				newArr[i] = arr[i];
				
			}
		}
		
		return - 1;
	}
	
	static int [] toArr (int num) {
		int res [] = new int[4];
		for (int i = 3 ;i >= 0 ; -- i) {
			res[i] = num % 10;
			num /= 10;
		}
		return res;
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		seive(10000);
		int T = sc.nextInt();
		while (T -- > 0) {
			int first = sc.nextInt();
			int second = sc.nextInt();
			int bfs = bfs(first , second);
			if (bfs == - 1) System.out.println("Impossible");
			else System.out.println(bfs);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public double nextDouble() throws IOException {
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if (x.charAt(0) == '-') {
				neg = true;
				start++;
			}
			for (int i = start; i < x.length(); i++)
				if (x.charAt(i) == '.') {
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				} else {
					sb.append(x.charAt(i));
					if (dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg ? -1 : 1);
		}

		public boolean ready() throws IOException {
			return br.ready();
		}

	}
}
