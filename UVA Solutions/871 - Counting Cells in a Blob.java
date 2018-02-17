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
	

	static int R ;
	static int C ;
	static char grid [][];
	static ArrayList<Integer> adjList [];
	static boolean visited [][];
	static int dx [] = {0 , 0  , 1 , -1 , 1  , 1   , -1 , -1};
	static int dy [] = {1 , -1 , 0 , 0  , 1  , -1  , 1  , -1};
	
	static boolean valid (int row , int col) {
		return row >= 0 && col >= 0 && row < R && col < C && grid[row][col] == '1';
	}
	
	static int floodfill (int row , int col) {
		visited [row][col] = true;
		int res = 1;
		for (int i = 0 ; i < 8 ; ++ i) {
			int newRow = row + dx[i];
			int newCol = col + dy[i];
			if (valid(newRow, newCol) && !visited[newRow][newCol])
				res += floodfill(newRow, newCol);
		}
		
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		bf.readLine();
		while (T -- > 0) {
			grid = new char[25][];
			String line = bf.readLine();
			C = line.length();
			R = 0;
			grid[R ++] = line.toCharArray();
			while (bf.ready() && !(line = bf.readLine()).isEmpty())
				grid[R ++] = line.toCharArray();
			visited = new boolean[R][C];
			
			int max = 0;
			for (int i = 0 ; i < R ; ++ i)
				for (int j = 0 ; j < C ; ++ j)
					if (grid[i][j] == '1' && !visited[i][j])
						max = Math.max(max, floodfill(i , j));
			
			System.out.print(max + "\n");
			if (T != 0) System.out.println();
		}
	}
}
