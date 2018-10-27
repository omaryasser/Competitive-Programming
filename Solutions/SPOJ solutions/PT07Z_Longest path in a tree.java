import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static class Triple {
		int node;
		int parentNode;
		int distFromRoot;
		public Triple(int n , int p , int d) {
			node = n;
			parentNode = p;
			distFromRoot = d;
		}
	}
	static int V;
	static ArrayList<Integer> adjList [];
	static Triple bfs (int root) {
		Queue<Triple> queue = new LinkedList<>();
		Triple curPair = new Triple(root, -1 , 0);
		queue.add(curPair);
		
		while (!queue.isEmpty()) {
			curPair = queue.poll();
			int node = curPair.node;
			int distance = curPair.distFromRoot;
			int parent = curPair.parentNode;
			
			for (int v : adjList[node])
				if (v != parent)
					queue.add(new Triple(v, node, distance + 1));
		}
		return curPair;
	}
	
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		adjList = new ArrayList[V];
		
		for (int i = 0 ; i < V ; ++ i) adjList[i] = new ArrayList<>();
		
		for (int i = 0 ; i < V - 1 ; ++ i) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			adjList[u].add(v);
			adjList[v].add(u);
		}
		System.out.println(bfs(bfs(0).node).distFromRoot);
	}
}
