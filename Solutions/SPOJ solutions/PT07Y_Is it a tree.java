import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int V;
	static ArrayList<Integer> adjList [];
	static boolean visited [];
	static boolean thereIsCycle;
	static void dfs (int u , int p) {
		visited[u] = true;
		for (int v : adjList[u])
			if (v != p)
				if (visited[v]) {
					thereIsCycle = true;
					return;
				}
				else dfs(v, u);
	}
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		visited = new boolean[V];
		adjList = new ArrayList[V];
		thereIsCycle = false;
		for (int i = 0 ; i < V ; ++ i) adjList[i] = new ArrayList<>();
		
		int E = sc.nextInt();
		while (E -- > 0) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			adjList[u].add(v);
			adjList[v].add(u);
		}
		
		boolean allVisited = true;
		dfs(0 , - 1);
		for (int i = 0 ; i < V ; ++ i) if (!visited[i]) allVisited = false;
		System.out.println((!thereIsCycle && allVisited)? "YES" : "NO");
	}
}
