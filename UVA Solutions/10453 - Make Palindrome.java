import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static char word[];
	static long memo[][];
	static int parent[][];
	static int SAME = 0;
	static int LEFT = 1;
	static int RIGHT = 2;

	static long dp(int start, int end) {
		if (start == end)
			return 0;

		if (start + 1 == end) {
			if (word[start] == word[end]) {
				parent[start][end] = SAME;
				return memo[start][end] = 0;
			} else {
				parent[start][end] = LEFT;
				return memo[start][end] = 1;
			}
		}

		if (memo[start][end] != -1)
			return memo[start][end];

		if (word[start] == word[end]) {
			parent[start][end] = SAME;
			return memo[start][end] = dp(start + 1, end - 1);
		}

		long putAtLeft = 1 + dp(start, end - 1);
		long putAtRight = 1 + dp(start + 1, end);

		if (putAtLeft < putAtRight) {

			parent[start][end] = LEFT;
			return memo[start][end] = putAtLeft;
		} else {
			parent[start][end] = RIGHT;
			return memo[start][end] = putAtRight;
		}
	}

	static String res;
	static int made;

	static void print(int start, int end) {
		if (start == end) {
			res = res.substring(0, made) + word[start] + res.substring(made);
		} else if (start + 1 == end) {
			if (parent[start][end] == SAME) {
				res = res.substring(0, made) + word[start] + word[start] + res.substring(made);
			} else {
				res = res.substring(0, made) + word[start] + word[end] + word[start] + res.substring(made);
			}
		} else if (parent[start][end] == SAME) {
			res = res.substring(0, made) + word[start] + word[start] + res.substring(made);
			++made;
			print(start + 1, end - 1);
		} else if (parent[start][end] == LEFT) {
			res = res.substring(0, made) + word[end] + word[end] + res.substring(made);
			made++;
			print(start, end - 1);
		} else {
			res = res.substring(0, made) + word[start] + word[start] + res.substring(made);
			made++;
			print(start + 1, end);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PrintWriter out = new PrintWriter(System.out);
		int t = 0;
		while (bf.ready()) {
			word = bf.readLine().toCharArray();
			memo = new long[word.length][word.length];
			parent = new int[word.length][word.length];
			res = "";
			for (int i = 0; i < word.length; ++i)
				Arrays.fill(memo[i], -1);
			long needed = dp(0, word.length - 1);
			made = 0;
			sb.append(needed + " ");
			print(0, word.length - 1);
			sb.append(res);
			sb.append("\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}
}
