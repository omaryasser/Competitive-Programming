import java.io.*;
import java.util.*;

public class Main {
    static int MAX = 130;
    static long FT [][][] = new long[MAX][MAX][MAX];

    static void updateAll (int x, int y, int z, int val) {
        while (x < MAX) {
            updateY(x, y, z, val);
            x += x & -x;
        }
    }
    static void updateY (int x, int y, int z, int val) {
        while (y < MAX) {
            updateZ (x, y, z, val);
            y += y & -y;
        }
    }
    static void updateZ (int x, int y, int z, int val) {
        while (z < MAX) {
            FT[x][y][z] += val;
            z += z & - z;
        }
    }

    static long rsqAll (int x, int y, int z) {
        long sum = 0;
        while (x > 0) {
            sum += rsqY(x, y, z);
            x -= x & - x;
        }
        return sum;
    }
    static long rsqY (int x, int y, int z) {
        long sum = 0;
        while (y > 0) {
            sum += rsqZ(x, y, z);
            y -= y & -y;
        }
        return sum;
    }
    static long rsqZ (int x, int y, int z) {
        long sum = 0;
        while (z > 0) {
            sum += FT[x][y][z];
            z -= z & - z;
        }
        return sum;
    }

    static long query (int x1, int y1, int z1, int x2, int y2, int z2) {
        return rsqAll(x2, y2, z2) - rsqAll(x2, y1 - 1, z2) - rsqAll(x1 - 1, y2, z2) + rsqAll(x1 - 1, y1 - 1, z2) - query2(x1, y1, 0, x2, y2, z1 - 1);
    }static long query2 (int x1, int y1, int z1, int x2, int y2, int z2) {
        return rsqAll(x2, y2, z2) - rsqAll(x2, y1 - 1, z2) - rsqAll(x1 - 1, y2, z2) + rsqAll(x1 - 1, y1 - 1, z2);
    }

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        while (true) {
            int op = sc.nextInt();
            if (op == 1) {
                updateAll(sc.nextInt() + 1, sc.nextInt() + 1, sc.nextInt() + 1, sc.nextInt());
            } else if (op == 2) {
                out.println(query(sc.nextInt() + 1, sc.nextInt() + 1, sc.nextInt() + 1, sc.nextInt() + 1, sc.nextInt() + 1, sc.nextInt() + 1));
            } else break;
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

        public String next() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {}
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
            } catch (Exception e) {return null;}
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
            } catch (Exception e) {return false;}
        }

    }
}
