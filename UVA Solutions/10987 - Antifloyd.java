import java.io.*;
import java.util.*;

public class A {


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        for (int tt = 1; tt <= tc; tt++){
            int n = sc.nextInt();
            int adjMat [][] = new int[n][n];
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j <= i; j++) {
                    adjMat[i + 1][j] = sc.nextInt();
                    adjMat[j][i + 1] = adjMat[i + 1][j];
                }
            }
            boolean isEdge [][] = new boolean[n][n];
            for (int i = 0; i < n; i++) Arrays.fill(isEdge[i], true);
            for (int i = 0; i < n; i++) isEdge[i][i] = false;
            boolean ok = true;
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (k != j && k != i && adjMat[i][j] == adjMat[i][k] + adjMat[k][j]) isEdge[i][j] = false;
                        if (adjMat[i][k] + adjMat[k][j] < adjMat[i][j]) ok = false;
                    }
                }
            }
           ArrayList<Integer> a = new ArrayList<>(), b = new ArrayList<>(), c = new ArrayList<>();
           for (int i = 0; i < n; i++) for (int j = i + 1; j < n; j++) {
               if (isEdge[i][j]) {a.add(i + 1); b.add(j + 1); c.add(adjMat[i][j]);}
           }
           out.printf("Case #%d:\n", tt);
           if (ok) {
               out.println(a.size());
               for (int i = 0; i < a.size(); i++) out.println(a.get(i) + " " + b.get(i) + " " + c.get(i));
           }
           else out.println("Need better measurements.");
           out.println();
        }

        out.flush();
        out.close();
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner (FileReader f) {br = new BufferedReader(f);}

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }

    }
}
