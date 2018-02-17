import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	static class Node {
		int h ;
		boolean isMale ;
		String music ;
		String sport ;
		
		Node (int hh , boolean i , String m , String s){
			h = hh;
			isMale = i ;
			music = m ;
			sport = s;
		}
		
		
		boolean matching (Node x) {
			return x.isMale != isMale && Math.abs(h - x.h) <= 40 && 
					x.music.equals(music) && !sport.equals(x.sport);
		}
	}
	static int V ; 
	static ArrayList<Integer> adjList [] ;
	static boolean visited [] ;
	static int matched [] ;
	
	static boolean aug (int node) {
		if (visited[node]) return false ;
		visited[node] = true;
		
		for (int child : adjList[node]) {
			if (matched[child] == - 1 || aug (matched[child])) {
				matched[child] = node ; return true ;
			}
		}
		return false ;
	}
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		int T = sc.nextInt();
		while (T -- > 0){
			V = sc.nextInt();
			adjList = new ArrayList[V];
			for (int i = 0 ; i < V ; ++ i) adjList[i] = new ArrayList<>();
			int menCnt = 0 ;
			Node nodes [] = new Node[V];
			
			for (int i = 0 ; i < V ; ++ i) {
				int h = sc.nextInt() ;
				boolean isMale = sc.next().charAt(0) == 'M';
				String music = sc.next() , sport = sc.next();	
				if (isMale) {
					menCnt ++ ;
				}
				nodes[i] = new Node (h , isMale , music , sport);
			}
			
			int menIndices [] = new int[menCnt];
			int idx = 0;
			for (int i = 0 ; i < V ; ++ i) if (nodes[i].isMale) menIndices[idx ++ ] = i ;
			
			for (int i = 0 ; i < menCnt ; ++ i) {
				for (int j = 0 ; j < V ; ++ j) {
					if (nodes[j].isMale == false) {
						if (nodes[menIndices[i]].matching(nodes[j])) {
							adjList[menIndices[i]].add(j);
							adjList[j].add(menIndices[i]);
						}
					}
				}
			}
			
			matched = new int[V];
			Arrays.fill(matched, - 1);
			int MCBM = 0 ;
			for (int i = 0 ; i < menCnt ; ++ i) {
				visited = new boolean[V];
				MCBM += (aug (menIndices[i]) == true ? 1 : 0);
			}
			out.print((V - MCBM) + "\n");
		}
		
		out.flush();
		out.close();
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
		
		public boolean nextEmpty() throws IOException
		{
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}
