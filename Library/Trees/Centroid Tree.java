int fillSize (int node, int p) {
	sz[node] = 1;
	for (int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (child != p && !done[child]) {
			fillSize(child, node);
			sz[node] += sz[child];
		}
	}
	return sz[node];
}
int decompose (int node = 0, int p = -1, int size = n) {
	if (p == -1) size = fillSize(node, -1);

	int centroid = -1;
	for (int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (child != p && !done[child] && sz[child] > size / 2) {
			centroid = child;
			break;
		}
	}

	if (centroid != -1) {
		return decompose (centroid, node, size);
	}

	// node is the centroid
	done[node] = true;
	for (int i = 0; i < (int)T[node].size(); i++) {
		int child = T[node][i];
		if (!done[child]) {
			P[decompose(child, -1, 0)] = node;
		}
	}

	return node;
}

void update (int node, int col) {
	int cur = node;
	while (1) {
		if(col) {
			int cur_dist = distance(cur, node);
			nodeD[cur][node] = cur_dist;
			cntD[cur][cur_dist]++;
		}
		else {
			int distancee = nodeD[cur][node];
			cntD[cur][distancee]--;
			if (cntD[cur][distancee] == 0) cntD[cur].erase(distancee);
		}
		cur = P[cur];
		if (cur == -1) break;
	}
}

int query (int node) {
	int res = -1;
	int cur = node;
	while (1) {
		if ((int)cntD[cur].size()) {
			int best_distance = distance (node, cur) + (cntD[cur].begin()->first);
			if (res == -1 || res > best_distance) {
				res = best_distance;
			}
		}
		cur = P[cur];
		if (cur == -1) break;
	}
	return res;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main{
	static int V ;
	static ArrayList<Integer> adjList [];
	static int parent [];
	static int subtreeSize [];
	static int NOT_COLORED = -2;
	static int Color [];
	static int distance [][] ; // distance [i][j] = The distance between node j level i in the centroid tree.
	static int minDist [];

	static int getSize (int u , int p) {
		int size = 1;
		for (int child : adjList[u]) {
			if (child != p && parent[child] == NOT_COLORED) {
				size += getSize (child , u);
			}
		}
		return subtreeSize[u] = size ;
	}

	static void computeDistance (int u , int color , int parent , int dist) {
		distance[color][u] = dist;

		for (int child : adjList[u]) {
			if (child != parent && Color[child] >= color) {
				computeDistance(child, color, u, dist + 1);
			}
		}
	}
	static int decompose (int u , int p , int size , int color) {
		if (p == - 1) size = getSize (u , - 1);

		int idx = - 1;
		for (int child : adjList[u]) {
			if (child != p && parent[child] == NOT_COLORED && subtreeSize[child] > size / 2) {
				idx = child ;
				break;
			}
		}

		// This u isn't the Centroid.
		if (idx != - 1) {
			return decompose (idx , u , size , color);
		}

		// This u is the Centroid of the current SubTree.
		parent[u] = -1;
		for (int child : adjList[u]) {
			if (parent[child] == NOT_COLORED) {
				parent[decompose(child, - 1, size , color + 1)] = u;
			}
		}

		Color[u] = color;
		return u ;
	}
	static void changeToRed (int u) {
		int ancestor = u;
		while (ancestor != - 1) {
			minDist[ancestor] = Math.min(minDist[ancestor], distance[Color[ancestor]][u]);
			ancestor = parent[ancestor];
		}
	}

	static int getShortestDistTo (int u) {
		int res = (int)1e7;
		int ancestor = u;
		while (ancestor != -1) {
			res = Math.min(res, distance[Color[ancestor]][u]  + minDist[ancestor]);
			ancestor = parent[ancestor];
		}
		return res;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		V = sc.nextInt() ; int  Q = sc.nextInt();
		adjList = new ArrayList[V];
		for (int i = 0 ; i < V ; ++ i) adjList[i] = new ArrayList<>();
		for (int i = 0 ; i < V - 1 ; ++ i) {
			int u = sc.nextInt() - 1 , v = sc.nextInt() - 1;
			adjList[u].add(v);
			adjList[v].add(u);
		}
		subtreeSize = new int[V];
		parent = new int[V];
		Color = new int[V];
		distance = new int[18][V]; // 18 because the maximum Depth of the Centroid Tree is O (log2(1e5)) ~= 17
		Arrays.fill(parent, NOT_COLORED);
		decompose (0 , -1 , V , 0);
		for (int i = 0 ; i < V ; ++ i) {
			computeDistance (i , Color[i] , - 1 , 0);
		}

		minDist = new int[V];
		Arrays.fill(minDist, (int)1e7);

		StringBuilder sb = new StringBuilder();
		changeToRed (0);
		while (Q -- > 0) {
			int op = sc.nextInt();
			if (op == 1) {
				changeToRed (sc.nextInt() - 1);
			}
			else {
				sb.append(getShortestDistTo (sc.nextInt() - 1) + "\n");
			}
		}

		PrintWriter out = new PrintWriter(System.out);
		out.print(sb); out.flush(); out.close();
	}
}
