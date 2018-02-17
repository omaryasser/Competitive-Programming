import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	static int R;
	static int C;
	static char grid[][];
	static int destinationX;
	static int destinationY;
	static int initialX;
	static int initialY;
	static int initialBoxX;
	static int initialBoxY;
	static class Node implements Comparable<Node>{
		int personX;
		int personY;
		int boxX;
		int boxY;
		int cost;
		int push;
		Node (int px , int py , int bx , int by , int c , int p){
			personX = px;
			personY = py;
			boxX = bx;
			boxY = by;
			cost = c;
			push = p;
		}
		Node (){}
		
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return push == o.push ? cost - o.cost : push - o.push;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "pX = " + personX + " ,pY = " + personY + " ,boxX = " + boxX + " ,boxY = " + boxY + " ,cost = " + cost + " , push = " + push ;
		}
	}
	
	static boolean validWalk (int r , int c , int boxR , int boxC) {
		return r >= 0 && c >= 0 && r < R && c < C && ! (r == boxR && c == boxC) && grid[r][c] == '.';
	}
	
	static boolean valid (int r , int c) {
		return r >= 0 && c >= 0 && r < R && c < C && (grid[r][c] == '.' || grid[r][c] == 'T');
	}
	static char p [][][][] ;
	static int dx [] = {0 , 0 , 1 , - 1};
	static int dy [] = {1 , - 1 , 0 , 0};
	
	static class Pair {
		int walks;
		int push;
		Pair (int w , int p){
			walks = w;
			push = p;
		}
	}
	static Node dijkstra (Node source) {
		p = new char[R][C][R][C];
		Pair dist [][][][] = new Pair[R][C][R][C];
		int INF = (int)1e8;
		for (int i = 0 ; i < R ; ++ i) for (int j = 0 ; j < C ; ++ j) for (int k = 0 ; k < R ; ++ k) for (int f = 0 ; f < C ; ++ f) {
			dist[i][j][k][f] = new Pair (INF,INF);
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(source);
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.boxX == destinationX && cur.boxY == destinationY) {
				return new Node (cur.personX , cur.personY,cur.boxX,cur.boxY ,dist[cur.personX][cur.personY][cur.boxX][cur.boxY].push,cur.push);
			}
			if (cur.push > dist[cur.personX][cur.personY][cur.boxX][cur.boxY].push) continue;
			else if (cur.push == dist[cur.personX][cur.personY][cur.boxX][cur.boxY].push && 
					cur.cost > dist[cur.personX][cur.personY][cur.boxX][cur.boxY].walks) continue;
			for (int k = 0 ; k < 4 ; ++ k) {
				int newR = cur.personX + dx[k];
				int newC = cur.personY + dy[k];
				if (validWalk(newR, newC, cur.boxX, cur.boxY)) {
					if (cur.push < dist[newR][newC][cur.boxX][cur.boxY].push
							||(cur.push == dist[newR][newC][cur.boxX][cur.boxY].push && cur.cost + 1 < dist[newR][newC][cur.boxX][cur.boxY].walks)) {
						dist[newR][newC][cur.boxX][cur.boxY] = new Pair (cur.cost + 1 , cur.push);
						pq.add(new Node (newR , newC , cur.boxX , cur.boxY , dist[newR][newC][cur.boxX][cur.boxY].walks , 
								dist[newR][newC][cur.boxX][cur.boxY].push));
						p[newR][newC][cur.boxX][cur.boxY] = k == 0 ? 'w' : k == 1 ? 'e' : k == 2 ? 'n' : 's';
					}
				}
			}
			
			for (int k = 0 ; k < 4 ; ++ k) {
				int newR = cur.personX + dx[k];
				int newC = cur.personY + dy[k];
				if (cur.boxX == newR && cur.boxY == newC) {
					if (valid (newR + dx[k] , newC + dy[k])) {
						if (cur.push + 1 < dist[newR][newC][cur.boxX + dx[k]][cur.boxY + dy[k]].push
								||(cur.push + 1 == dist[newR][newC][cur.boxX + dx[k]][cur.boxY + dy[k]].push &&  cur.cost < dist[newR][newC][cur.boxX+dx[k]][cur.boxY+dy[k]].walks)) {
							dist[newR][newC][cur.boxX + dx[k]][cur.boxY + dy[k]] = new Pair (cur.cost , cur.push + 1);
							pq.add(new Node (newR , newC , cur.boxX + dx[k], cur.boxY + dy[k] , dist[newR][newC][cur.boxX + dx[k]][cur.boxY+dy[k]].walks , 
									dist[newR][newC][cur.boxX+dx[k]][cur.boxY+dy[k]].push));
							p[newR][newC][cur.boxX + dx[k]][cur.boxY + dy[k]] = k == 0 ? 'W' : k == 1 ? 'E' : k == 2 ? 'N' : 'S';
						}
					}
				}
			}
		}
		return new Node (-1,-1,-1,-1,-1 , -1);
	}
	static StringBuilder sb = new StringBuilder();
	static void print (int personX , int personY , int boxX , int boxY) {
		if (personX == initialX && personY == initialY && boxX == initialBoxX && boxY == initialBoxY) {
			return;
		}
		char c = p[personX][personY][boxX][boxY];
		switch (c) {
		case 'E':
			print(personX, personY + 1, boxX, boxY + 1);
			break;
		case 'W' :
			print(personX, personY - 1, boxX, boxY - 1);
			break;
		case 'N' :
			print(personX - 1 , personY, boxX - 1, boxY);
			break;
		case 'S' :
			print(personX + 1, personY, boxX + 1, boxY);
			break;
			
		case 'e':
			print(personX, personY + 1, boxX, boxY);
			break;
		case 'w' :
			print(personX, personY - 1, boxX, boxY);
			break;
		case 'n' :
			print(personX - 1 , personY, boxX, boxY);
			break;
		case 's' :
			print(personX + 1, personY, boxX, boxY);
			break;
		default:
			break;
		}
		char x = p[personX][personY][boxX][boxY];
//		System.out.println(personX + " " + personY + " " + boxX + " " +boxY + " " + x);
		if (x == 'e') x = 'w';
		else if (x == 'w') x = 'e';
		else if (x == 'n') x = 's';
		else if (x == 's') x = 'n';
		else if (x == 'E') x = 'W';
		else if (x == 'W') x = 'E';
		else if (x == 'N') x = 'S';
		else if (x == 'S') x = 'N';
		sb.append (x);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean first = true;
		for (int T = 1 ; ;T ++ ) {
			R = sc.nextInt(); C = sc.nextInt();
			if (R == 0 && C == 0) break;
			grid = new char[R][C];
			for (int i = 0 ; i < R ; ++ i) grid[i] = sc.next().toCharArray();
			Node cur = new Node ();
			for (int i = 0 ; i < R ; ++ i)
				for (int j = 0 ; j < C ; ++ j) {
					if (grid[i][j] == 'S') {
						cur.personX = i;
						cur.personY = j;
						grid[i][j] = '.';
					}
					else if (grid[i][j] == 'T') {
						destinationX = i;
						destinationY = j;
					}
					else if (grid[i][j] == 'B') {
						cur.boxX = i;
						cur.boxY = j;
						grid[i][j] = '.';
					}
				}
			initialX = cur.personX;
			initialY = cur.personY;
			initialBoxX = cur.boxX;
			initialBoxY = cur.boxY;
			cur.cost = 0;
			cur.push = 0;
			Node res = dijkstra (cur);
			
			if (first) first = false;
			else sb.append("\n");
			sb.append("Maze #" + T + "\n");
			if (res.cost == - 1) sb.append("Impossible.\n");
			else {
				print (res.personX , res.personY , res.boxX , res.boxY);
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}
