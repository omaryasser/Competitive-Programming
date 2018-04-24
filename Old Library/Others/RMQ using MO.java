import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class B {
    static int n, arr[], q, SQRT;
    static TreeMap<Integer, Integer> cnt = new TreeMap<>();
    static int moL = 0, moR = -1;
    static void rightR (int L, int R) {
        // moR < R
        while (moR < R) {
            moR++;
            Integer was = cnt.get(arr[moR]);
            if (was == null) cnt.put(arr[moR], 1);
            else cnt.put(arr[moR], was.intValue() + 1);
        }
    }

    static void leftL (int L, int R) {
        // moL > L
        while (moL > L) {
            moL--;
            Integer was = cnt.get(arr[moL]);
            if (was == null) cnt.put(arr[moL], 1);
            else cnt.put(arr[moL], was.intValue() + 1);
        }
    }

    static void rightL (int L, int R) {
        // moR > R
        while (moR > R) {
            Integer was = cnt.get(arr[moR]);
            if (was == 1) cnt.remove(arr[moR]);
            else cnt.put(arr[moR], was.intValue() - 1);
            moR--;
        }
    }

    static void leftR (int L, int R) {
        // moL < L
        while (moL < L) {
            Integer was = cnt.get(arr[moL]);
            if (was == 1) cnt.remove(arr[moL]);
            else cnt.put(arr[moL], was.intValue() - 1);
            moL++;
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        n = sc.nextInt();
        SQRT = (int)Math.sqrt(n) + 1;
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        q = sc.nextInt();
        Query queries [] = new Query[q];
        for (int i = 0; i < q; i++)
            queries[i] = new Query(sc.nextInt(), sc.nextInt(), i);
        Arrays.sort(queries, new Comparator<Query>() {
            @Override
            public int compare(Query q1, Query q2) {
                if (q1.l / SQRT != q2.l / SQRT) return q1.l / SQRT - q2.l / SQRT;
                return q1.r - q2.r;
            }
        });

        int res [] = new int[q];
        for (int i = 0; i < q; i++) {
            int L = queries[i].l, R = queries[i].r;
            rightR(L, R);
            leftL(L, R);
            leftR(L, R);
            rightL(L, R);
            res[queries[i].idx] = cnt.firstKey();
        }

        for (int i = 0; i < q; i++) out.println(res[i]);
        out.flush();
        out.close();
    }

    static class Query {
        int l, r, idx;
        Query (int ll, int rr, int ii) {l = ll; r = rr; idx = ii;}
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
