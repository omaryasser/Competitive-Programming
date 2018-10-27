import java.io.*;
import java.util.*;

/**
 * Created by omar on 22/06/17.
 */
public class Main {
    public static void main (String [] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            int len = sc.nextInt();
            if (len == -1) break;

            DS d = new DS(5000 * 2);
            HashMap<Integer,Integer> map = new HashMap<>();
            int mapIdx = 0;
            int Q = sc.nextInt();
            int res = -1;
            for (int q = 1; q <= Q; q++) {
                int l = sc.nextInt() - 1, r = sc.nextInt();
                if (!map.containsKey(l)) map.put(l, mapIdx++);
                if (!map.containsKey(r)) map.put(r, mapIdx++);
                if (l < 0 || r > len || l > r) {if (res == -1) res = q - 1;}
                int parity = sc.next().equals("even") ? 0 : 1;
                if (!d.merge(map.get(l), map.get(r), parity)) {if (res == -1) res = q - 1;}
            }
            res = res == -1 ? Q : res;
            out.println(res);
        }

        out.flush();
        out.close();
    }


    static class DS {
        int sz;
        int [] rank, p, R;

        DS(int size) {sz = size; rank = new int[sz]; p = new int[sz]; R = new int[sz]; for (int i = 0; i < sz; i++) p[i] = i;}

        int find (int idx)
        {
            if (p[idx] == idx) return idx;
            int old_parent = p[idx];
            p[idx] = find(p[idx]);
            R[idx] = R[idx] + R[old_parent];
            return p[idx];
        }

        boolean merge (int i, int j, int t)
        {
            int x = find(i), y = find(j);
            if (x == y)
            {
                return (R[i] + R[j]) % 2 == t;
            }
            else {
                if (rank[x] > rank[y])
                {
                    p[y] = x;
                    R[y] = (R[i] + t + R[j]) % 2;
                }
                else
                {
                    p[x] = y;
                    R[x] = (R[i] + t + R[j]) % 2;
                    if (rank[x] == rank[y]) rank[y]++;
                }
            }
            return true;
        }

        int get_rel(int i, int j)
        {
            int x = find(i), y = find(j);
            if (x != y) return 3;
            return (R[i] + R[j]) % 2 + 1;
        }
    }















    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader f) {
            br = new BufferedReader(f);
        }

        public String next() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (Exception e) {
                return null;
            }
        }

        public double nextDouble() {
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

        public boolean ready() {
            try {
                return br.ready();
            } catch (Exception e) {
                return false;
            }
        }

    }
}

