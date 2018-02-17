import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	static char grid [][] ;
	static boolean visited [][];
	static int R;
	static int C;
	static int dx [] = {0 , 0  , 1 , -1 , 1 , 1  , -1 , -1};
	static int dy [] = {1 , -1 , 0 ,  0 , 1 , -1 , 1  , -1};
	
	static boolean valid (int row , int col) {
		return row >= 0 && col >= 0 && row < R && col < C && !visited[row][col];
	}
	
	static int search (int curRow , int curCol) {
		visited[curRow][curCol] = true;
		
		if (grid[curRow][curCol] != 'W') return 0;
		int res = 1;
		for (int i = 0 ; i < 8 ; ++ i) {
			int newRow = curRow + dx[i];
			int newCol = curCol + dy[i];
			if (valid(newRow, newCol)) res += search(newRow, newCol); 
		}
		return res;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		bf.readLine();
		
		while (T -- > 0) {
			String firstLine = bf.readLine();
			C = firstLine.length();
			grid = new char[100][C];
			grid[0] = firstLine.toCharArray();
			R = 1;
			String line ;
			while (true) {
				line = bf.readLine();
				if (line.charAt(0) != 'W' && line.charAt(0) != 'L') break;
				grid[R ++ ] = line.toCharArray();
			}
			do {
				StringTokenizer st = new StringTokenizer(line);
				int row = Integer.parseInt(st.nextToken()) - 1;
				int col = Integer.parseInt(st.nextToken()) - 1;
				
				visited = new boolean [R][C];
				System.out.println(search (row , col));
				if (bf.ready()) line = bf.readLine();
				else break;
			} while (!line.isEmpty());
			
			if (T != 0) System.out.println();
		}
	}
}
