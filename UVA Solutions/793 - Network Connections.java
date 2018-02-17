import java.io.*;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by omar on 22/06/17.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = Integer.parseInt(sc.nextLine()); sc.nextLine();
        while (tc-- > 0) {
            int n = Integer.parseInt(sc.nextLine());
            DisjointSets disjointSets = new DisjointSets(n);
            int successful = 0, unsuccessful = 0;
            String line;
            while (sc.ready() && !(line = sc.nextLine()).isEmpty()) {
                StringTokenizer st = new StringTokenizer(line);
                char op = st.nextToken().charAt(0);
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                if (op == 'c') {
                    disjointSets.merge(u, v);
                } else {
                    if (disjointSets.find(u) == disjointSets.find(v))
                        successful++;
                    else
                        unsuccessful++;
                }
            }
            out.println(successful + "," + unsuccessful);
            if (tc != 0) out.println();
        }

        out.flush();
        out.close();
    }


    static class DisjointSets {
        int[] p, rank;
        DisjointSets (int n) {
            p = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) p[i] = i;
        }
        int find (int u) {
            if (p[u] == u) return u;
            else return p[u] = find(p[u]);
        }
        void merge (int u, int v) {
            int x = find(u), y = find(v);
            if (x != y) {
                if (rank[x] > rank[y]) {
                    p[y] = x;
                } else {
                    p[x] = y;
                    if (rank[x] == rank[y]) rank[x]++;
                }
            }
        }
    }
    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
            }
            return st.nextToken();
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }
}
