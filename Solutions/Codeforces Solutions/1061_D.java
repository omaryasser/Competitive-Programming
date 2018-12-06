
import javafx.util.Pair;

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Pair[] pairs;
    static int interesting[];
    static long x, y, mod = (int)1e9 + 7;
    public static void main(String[] args) {

        Scanner sc = new Scanner();
        PrintWriter out = new PrintWriter(System.out);

        n = sc.nextInt();
        x = sc.nextLong();
        y = sc.nextLong();
        pairs = new Pair[n];
        for(int i = 0; i < n; i++)
            pairs[i] = new Pair(sc.nextInt(), sc.nextInt());

        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.r != o2.r)
                    return Integer.compare(o1.r, o2.r);
                else
                    return Integer.compare(o1.l, o2.l);
            }
        });

        long res = 0;
        TreeSet<Triple> treeSet = new TreeSet<>(new Comparator<Triple>() {
            @Override
            public int compare(Triple o1, Triple o2) {
                if(o1.l != o2.l){
                    return Integer.compare(o1.l, o2.l);
                }
                return Integer.compare(o1.idx, o2.idx);
            }
        });
//        System.err.println(Arrays.toString(pairs));
        for(int i = n - 1; i >= 0; i--){
            Triple best = treeSet.higher(new Triple(pairs[i].r, (int)mod, (int)mod));
            if(best != null) System.err.println(pairs[i].r + " " + best.l);
            if(best == null){
                treeSet.add(new Triple(pairs[i].l, pairs[i].r, i));
                res = adjust(res + adjust(x + y * pairs[i].r));
            }else {
                if(y * best.l - y * pairs[i].r > x){
                    treeSet.add(new Triple(pairs[i].l, pairs[i].r, i));
                    res = adjust(res + adjust(x + y * pairs[i].r));
                }else {
                    treeSet.remove(best);
                    treeSet.add(new Triple(pairs[i].l, pairs[i].r, i));
                }
            }
//            System.err.println(best == null);
        }
        for(Triple t : treeSet)
            res = adjust(res - y * t.l);
        out.println(res);
        out.flush();
        out.close();
    }

    static class Triple {
        int l, r, idx;

        public Triple(int l, int r, int idx) {
            this.l = l;
            this.r = r;
            this.idx = idx;
        }
    }
    static long adjust(long x){
        return ((x % mod) + mod) % mod;
    }
    static class Pair {
        int l, r;

        public Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "l=" + l +
                    ", r=" + r +
                    '}';
        }
    }











    static class Scanner {
        BufferedReader br;
        StringTokenizer st;

        public Scanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public Scanner(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}