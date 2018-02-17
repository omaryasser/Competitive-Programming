import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int H , W;
	static String [] grid;
	
	static boolean valid (int row , int col) {
		if(row >= 0 && col >= 0 && row < H && col < W) return true ;
		else return false;
	}
	
	static int dx [] = {0 , 0 , 1 , - 1 , 1 , - 1 , 1 , - 1};
	static int dy [] = {1 , - 1 , 0 , 0 , 1 , 1 , - 1 , - 1};
	
	static int memo[][];
	static int search(int row , int col) {
		if(memo[row][col] != -1) return memo[row][col];
		int max = 1;
		for (int k = 0 ; k < 8 ; ++ k) {
			int newRow = row + dx[k];
			int newCol = col + dy[k];
			
			if (valid(newRow, newCol) && grid[newRow].charAt(newCol) == grid[row].charAt(col) + 1){
				max = Math.max(max, 1 + search(newRow, newCol));
			}
		}
		return memo[row][col] = max;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int caseNum = 1;
		while(true) {
			H = sc.nextInt();
			W = sc.nextInt();
			memo = new int[H][W];
			for(int i = 0 ; i < H ; ++ i) {
				Arrays.fill(memo[i],  - 1);
			}
			if(H == 0 && W == 0) break;
			grid = new String[H];
			for (int i = 0 ; i < H ; ++ i) {
				grid[i] = sc.next();
			}
			
			int max = 0;
			for(int i = 0 ; i < H ; ++ i) {
				for(int j = 0 ; j < W ; ++ j) {
					if(grid[i].charAt(j) == 'A') {
						max = Math.max(max, search(i , j));
					}
				}
			}
			System.out.println("Case " + (caseNum ++) + ": " + max);
		}
	}
}
