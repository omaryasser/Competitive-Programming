import java.io.*;
import java.util.*;

public class A {


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            int E = sc.nextInt(), Q = sc.nextInt();
            int adjMat [][] = new int[27][27];
            for (int i = 0; i < 27; i++) Arrays.fill(adjMat[i], 100);
            HashMap<Character,Integer> charInt = new HashMap<>();
            HashMap<Integer,Character> intChar = new HashMap<>();

            int mapIdx = 0;
            while (E-- > 0) {
                char [] f = sc.next().toCharArray();
                char [] s = sc.next().toCharArray();
                if (!charInt.containsKey(f[0])) {
                    charInt.put(f[0], mapIdx);
                    intChar.put(mapIdx++, f[0]);
                }
                if (!charInt.containsKey(s[0])) {
                    charInt.put(s[0], mapIdx);
                    intChar.put(mapIdx++, s[0]);
                }
                adjMat[charInt.get(f[0])][charInt.get(s[0])] = 1;
                adjMat[charInt.get(s[0])][charInt.get(f[0])] = 1;
            }

            int p [][] = new int[27][27];
            for (int i = 0; i < 27; i++) for (int j = 0; j < 27; j++) p[i][j] = i;
            for (int k = 0; k < 27; k++) {
                for (int i = 0; i < 27; i++) {
                    for (int j = 0; j < 27; j++) {
                        if (adjMat[i][k] + adjMat[k][j] < adjMat[i][j]) p[i][j] = p[k][j];
                        adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
                    }
                }
            }
            while (Q-- > 0) {
                char [] f = sc.next().toCharArray();
                char [] s = sc.next().toCharArray();
                int i = charInt.get(f[0]), j = charInt.get(s[0]);
                String res = "";
                while (true) {
                    res = intChar.get(j) + res;
                    if (p[i][j] == i) break;
                    j = p[i][j];
                }
                res = intChar.get(i) + res;
                out.println(res);
            }
            if (tc != 0) out.println();
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
