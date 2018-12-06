import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] arr;
    static boolean[] removed;
    static boolean[] negative;

    static void solve () {
        int maxIdx = 0;
        while (maxIdx < n && removed[maxIdx]) maxIdx++;
        if (maxIdx == n) return;

        for (int i = maxIdx + 1; i < n; i++)
            if (!removed[i] && arr[i] > arr[maxIdx])
                maxIdx = i;
        int maxNum = arr[maxIdx];

        int cnt = 0;
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == maxNum) {
                cnt++;
                positions.add(i);
            }
        }

        int[] left = new int[n], right = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0) left[i] = left[i - 1];
            if (!removed[i] && arr[i] != maxNum)
                left[i]++;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1) right[i] = right[i + 1];
            if (!removed[i] && arr[i] != maxNum)
                right[i]++;
        }

        int[][] dp = new int[cnt + 1][cnt + 1];
        for (int[] d : dp) Arrays.fill(d, (int)1e9);
        dp[0][0] = left[positions.get(0)];
        dp[0][1] = right[positions.get(0)];
        for (int curIdx = 1; curIdx < cnt; curIdx++) {
            for (int positives = 0; positives <= curIdx + 1; positives++) {
                int pos = positions.get(curIdx);
                int make_positive = positives >= 1 ? right[pos] + dp[curIdx - 1][positives - 1] : (int)1e9;
                int make_negative = left[pos] + positives + dp[curIdx - 1][positives];
                dp[curIdx][positives] = Math.min(make_positive, make_negative);
            }
        }

        int best = 0;
        for (int positives = 1; positives <= cnt - 1 + 1; positives++) {
            if (dp[cnt - 1][positives] < dp[cnt - 1][best])
                best = positives;
        }


        boolean isPositive[] = new boolean[cnt];
        for (int i = cnt - 1; i >= 0; i--) {
            if (i > 0) {
                int pos = positions.get(i);
                if (dp[i][best] == (best >= 1 ? right[pos] + dp[i - 1][best - 1] : (int)1e9)) {
                    isPositive[i] = true;
                    best--;
                }
            } else {
                if (best == 1) {
                    isPositive[i] = true;
                }
            }
        }

        for (int i = 0; i < cnt; i++) {
            negative[positions.get(i)] = !isPositive[i];
            removed[positions.get(i)] = true;
        }

        solve();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        n = sc.nextInt();
        arr = new int[n];
        removed = new boolean[n];
        negative = new boolean[n];
        for (int i = 0; i < n; i++)
            arr[i] = Math.abs(sc.nextInt());

        solve();

        int inv = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                int first = negative[i] ? -arr[i] : arr[i];
                int second = negative[j] ? -arr[j] : arr[j];
                if (first > second)
                    inv++;
            }
//        for (int i = 0; i < n; i++) {
//            int first = negative[i] ? -arr[i] : arr[i];
//            System.err.print(" " + first);
//        }
        out.println(inv);

        out.flush();
        out.close();
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(String file) {
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
