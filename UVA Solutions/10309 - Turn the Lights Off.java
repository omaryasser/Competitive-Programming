import java.io.*;
import java.util.*;


public class E {



    static int res ;
    static void search (int [][] light , int row , int ans)
    {

        for (int i = 0 ; i < 10 ; ++i)
            if (light[row - 1][i] == 1)
            {
                ++ans;
                if (i == 0)
                {
                    light[row][0] = 1 - light[row][0];
                    if (row != 9) light[row + 1][0] = 1 - light[row + 1][0];
                    light[row][1] = 1 - light[row][1];
                }
                else if (i == 9)
                {
                    light[row][9] = 1 - light[row][9];
                    if(row != 9) light[row + 1][9] = 1 - light[row + 1][9];
                    light[row][8] = 1 - light[row][8];
                }
                else
                {
                    light[row][i] = 1 - light[row][i];
                    if (row != 9) light[row + 1][i] = 1 - light[row + 1][i];
                    light[row][i + 1] = 1 - light[row][i + 1];
                    light[row][i - 1] = 1 - light[row][i - 1];
                }
            }

        if (row == 9)
        {
            boolean ok = true;
            for (int i = 0 ; i < 10 ; ++i)
                if (light[9][i] == 1)
                    ok = false;
            if (ok) if (res == -1) res = ans ; else res = Math.min(res , ans);

            return;
        }

        search(light , row + 1 , ans);
    }

    static void fill (int [][] light , int rowIdx , char [] grid)
    {
        for (int i = 0 ; i < 10 ; ++i) light[rowIdx][i] = grid[i] == '#' ? 0 : 1;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        while (true)
        {
            int light [][] = new int[10][10];
            char grid [][] = new char [10][10];
            String testCaseName = sc.next();
            if (testCaseName.equals("end")) break;
            for (int i = 0 ; i < 10 ; ++i) grid[i] = sc.next().toCharArray();
            res = -1;
            int mask = (1 << 10) - 1;
            while (mask >= 0)
            {
                for (int i = 0 ; i < 10 ; ++i)
                    fill(light , i , grid[i]);

                int toggled = 0;
                for (int i = 0 ; i < 10 ; ++i)
                    if (((1 << i) & mask) == 0)
                    {
                        ++ toggled;
                        if (i == 0)
                        {
                            light[0][0] = 1 - light[0][0];
                            light[1][0] = 1 - light[1][0];
                            light[0][1] = 1 - light[0][1];
                        }
                        else if (i == 9)
                        {
                            light[0][9] = 1 - light[0][9];
                            light[0][8] = 1 - light[0][8];
                            light[1][9] = 1 - light[1][9];
                        }
                        else
                        {
                            light[0][i] = 1 - light[0][i];
                            light[1][i] = 1 - light[1][i];
                            light[0][i + 1] = 1 - light[0][i + 1];
                            light[0][i - 1] = 1 - light[0][i - 1];
                        }
                    }

                search(light , 1 , toggled);
                --mask;
            }
            out.printf("%s %d\n" , testCaseName , res);
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
