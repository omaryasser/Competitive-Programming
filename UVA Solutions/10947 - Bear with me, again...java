import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import java.util.Random;
public class Main {

    static class Circle {
        double x, y, r;
        Circle (double xx, double yy, double rr) {
            x = xx;
            y = yy;
            r = rr;
        }
        double dist (Circle other) {
            double d = Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
            return Math.max(0, d - r - other.r);
        }
    }
    static double adjMat[][];
    static int V;
    static boolean visited [];
    static boolean dfs (int node, double maxDist) {
        if (node == 1) return true;
        visited[node] = true;
        boolean ok = false;
        for (int i = 0; i < V; i++) {
            if (node != i && !visited[i]) {
                if (adjMat[node][i] <= maxDist) {
                    ok |= dfs(i, maxDist);
                }
            }
        }
        return ok;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (sc.ready()) {
            double bear = sc.nextDouble(), speed = sc.nextDouble();
            Circle src = new Circle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
            Circle dst = new Circle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());

            V = sc.nextInt() + 2;
            visited = new boolean[V];
            Circle [] circles = new Circle[V];
            circles[0] = src; circles[1] = dst;
            for (int i = 2; i < V; i++) circles[i] = new Circle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
            adjMat = new double[V][V];
            for (int i = 0; i < V; i++) {
                for (int j = i + 1; j < V; j++) {
                    adjMat[i][j] = adjMat[j][i] = circles[i].dist(circles[j]);
                }
            }


            if (dfs(0, bear * speed)) out.println("Larry and Ryan will escape!");
            else out.println("Larry and Ryan will be eaten to death.");
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
        public Scanner(FileReader s) {
            br = new BufferedReader(s);
        }

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
