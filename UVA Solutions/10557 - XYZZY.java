import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int INF = (int) 1e6;
	static int V;
	static boolean out ;
	static ArrayList<Edge> adjList [];
	static HashMap<Integer, Integer> roomEnergy;
	static int dist [];
	static boolean  bellmanFord(int S)		
	{
		dist = new int[V];
		Arrays.fill(dist, -INF);
		dist[S] = 100;
		boolean modified = true;
		for(int k = 0; modified && k < V - 1; ++k)
		{
			modified = false;
			for(int u = 0; u < V; ++u)		// these two loops run in O(E) in total
				for(Edge nxt: adjList[u])	
					if(dist[u] + nxt.cost > dist[nxt.node] && dist[u] + nxt.cost > 0)
					{
						modified = true;
						dist[nxt.node] = dist[u] + nxt.cost;
					}
		}
		
		boolean positive_cycle_exists = false;
		for(int u = 0; u < V; ++u)
			for(Edge nxt: adjList[u])
				if(dist[u] + nxt.cost > dist[nxt.node] && 
						dist[u] + nxt.cost > 0 && bfs(0, u) && bfs(u, V - 1))
					positive_cycle_exists = true;
		return positive_cycle_exists;
	}
	static class Edge implements Comparable<Edge>
	{
		int node, cost;
		
		Edge(int a, int b)
		{
			node = a;
			cost = b;
		}
		
		public int compareTo(Edge e)
		{
			if(cost != e.cost)
				return cost - e.cost;
			return node - e.node;
		}
		
	}
	
	static boolean bfs (int s , int e) {
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		boolean visited [] = new boolean[V];
		while (!q.isEmpty()) {
			int node = q.poll();
			visited[node] = true;
			if (node == e) return true;
			for (Edge child : adjList[node]) {
				if (!visited[child.node])
					q.add(child.node);
			}
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		while (true) {
			roomEnergy = new HashMap<>();
			V = sc.nextInt();
			if (V == - 1) break;
			
			int energy [] = new int[V];
			ArrayList<Integer> tmpAdj [] = new ArrayList[V];
			for (int i = 0 ; i < V ; ++ i) {
				tmpAdj[i] = new ArrayList<>();
				energy[i] = sc.nextInt();
				int N =sc.nextInt();
				while (N -- > 0) {
					tmpAdj[i].add(sc.nextInt() - 1);
				}
			}
			
			adjList = new ArrayList[V];
			for (int i = 0 ; i < V ; ++ i) adjList[i] = new ArrayList<>();
			
			for (int i = 0 ; i < V ; ++ i) {
				for (int child : tmpAdj[i])
					adjList[i].add(new Edge(child, energy[child]));
			}
			boolean canReach = bfs (0 , V - 1);
			boolean positiveCycle_exist = bellmanFord(0);
			if (canReach && (dist[V - 1] > 0 || positiveCycle_exist) ) System.out.print("winnable\n");
			else System.out.print("hopeless\n");
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
