static void solve (int count, char source, char destination, char intermediate) {
        if (count == 1)
                System.out.printf("Move top disc from pole %c to pole %c\n", source, destination);
        else {
                solve(count - 1, source, intermediate, destination);
                solve(1, source, destination, intermediate);
                solve(count - 1, intermediate, destination, source);
        }
}

// min solution for n disks and m pigs

public class Main {
static StringBuilder sb = new StringBuilder();
static int[][] memo;
static Stack<Integer> s[];
public static void main(String[] args) throws Exception {


        int n = sc.nextInt(), m = sc.nextInt();
        s = new Stack[m + 1];
        for (int i = 0; i < m + 1; i++) s[i] = new Stack<>();
        for (int i = n; i >= 1; i--) s[1].add(i);
        memo = new int[n + 1][m + 1];
        for (int [] mm : memo)
                Arrays.fill(mm, -1);

        out.println(dp(n, m));
        print(1, n, 1, m, m);
        out.println(sb);
        out.close();
}

static void print (int min_disk, int max_disk, int from_peg, int to_peg, int numPegs) {
        if (min_disk == max_disk) {
                if (s[to_peg].empty()) sb.append(String.format("move %d from %d to %d\n", min_disk, from_peg, to_peg));
                else sb.append(String.format("move %d from %d to %d atop %d\n", min_disk, from_peg, to_peg, s[to_peg].peek()));
                s[to_peg].add(s[from_peg].pop());
        }
        else {
                int numDisks = max_disk - min_disk + 1;
                int split = -1;
                for (int i = 1; i < numDisks; i++) {
                        if (dp(numDisks, numPegs) == 2 * dp(i, numPegs) + dp(numDisks - i, numPegs - 1))
                                split = i;
                }
                split = min_disk + split - 1;
                int first_empty = 0;
                while(true)
                {
                        first_empty++;
                        if(first_empty == to_peg)
                                continue;
                        if(s[first_empty].empty() || s[first_empty].peek() > split)
                                break;
                }
                print(min_disk,split,from_peg,first_empty,numPegs);
                print(split+1,max_disk,from_peg,to_peg,numPegs-1);
                print(min_disk,split,first_empty,to_peg,numPegs);
        }
}
static int dp (int n, int m) {
        if (m == 1) return (int)(1e9/2) - 3;
        if (n == 1) return 1;
        if (memo[n][m] != -1) return memo[n][m];
        int res = (int)(1e9/2) - 3;
        for (int i = 1; i <= n - 1; i++)
                res = Math.min(res, 2 * dp(i, m) + dp(n - i, m - 1));
        return memo[n][m] = res;
}
}
