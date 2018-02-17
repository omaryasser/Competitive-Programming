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
	static char word [];
	static long memo [][];
	static long dp (int left , int right) {
		if (left == right) return 1;
		if (left + 1 == right) return 2 + (word[left] == word[right] ? 1 : 0);
		if (memo[left][right] != -1) return memo[left][right];
		long res = 0;
		if (word[left] == word[right])
			res += 1 + dp(left + 1, right - 1);
		res += dp(left, right - 1);
		res += dp(left + 1, right);
		res -= dp(left + 1, right - 1);
		return memo[left][right] = res ;	
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		while (T -- > 0) {
			word = bf.readLine().toCharArray();
			memo = new long[word.length][word.length];
			for (int i = 0 ; i < word.length ; ++i)
				Arrays.fill(memo[i], -1);
			System.out.println(dp (0 , word.length - 1));
		}
	}
}
