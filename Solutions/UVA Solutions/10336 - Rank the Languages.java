import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static class Pair implements Comparable<Pair> {
		char character;
		int CC;

		Pair(char c) {
			character = c;
			CC = 0;
		}

		@Override
		public int compareTo(Pair o) {
			return CC == o.CC ? character - o.character : o.CC - CC;
		}

	}

	static int R;
	static int C;
	static char grid[][];
	static boolean visited[][];
	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	static boolean valid(int row, int col) {
		return row >= 0 && col >= 0 && row < R && col < C && !visited[row][col];
	}

	static void floodfill(int row, int col, char c) {
		visited[row][col] = true;
		for (int i = 0; i < 4; ++i) {
			int newRow = row + dx[i];
			int newCol = col + dy[i];
			if (valid(newRow, newCol) && grid[newRow][newCol] == c)
				floodfill(newRow, newCol, c);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int T = Integer.parseInt(bf.readLine());
		for (int tt = 1; tt <= T; ++tt) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			visited = new boolean[R][C];
			grid = new char[R][C];

			for (int i = 0; i < R; ++i)
				grid[i] = bf.readLine().toCharArray();
			
			Pair cnt [] = new Pair [26];
			for (int i = 0 ; i < 26 ; ++ i) cnt[i] = new Pair ((char)('a' + i));
			for (int i = 0; i < R; ++i)
				for (int j = 0; j < C; ++j)
					if (!visited[i][j]) {
						char current = grid[i][j];
						floodfill(i, j, current);
						cnt[current - 'a'].CC ++ ;
					}
			
			Arrays.sort(cnt);
			out.printf("World #%d\n", tt);
			for (int i = 0; i < cnt.length; ++i)
				if (cnt[i].CC > 0 )
					out.printf("%c: %d\n", cnt[i].character, cnt[i].CC);
		}
		out.flush();
		out.close();
	}
}
