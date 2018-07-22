
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int arr [][];
    static int N , M;
    public static int BS_ROW (int row , int num)
    {
        int lo = 0 , hi = M - 1;
        int lastFound = 502;
        for (int i = 0 ; i < 9 ; ++i)
        {
            int mid = lo + ((hi - lo) >> 1);
            if (arr[row][mid] >= num)
            {
                lastFound = Math.min(lastFound , mid);
                hi = Math.max(mid - 1 , lo);
            }
            else lo = Math.min(mid + 1 , hi);
        }
        return lastFound == 502 ? -1 : lastFound;
    }

    public static int BS_DIAG (int rowNum , int colNum , int L , int U)
    {
        int loCol = colNum , hiCol = colNum + Math.min(N - rowNum - 1 , M - colNum - 1);
        int loRow = rowNum , hiRow = rowNum + Math.min(N - rowNum - 1 , M - colNum - 1);
        int lastCol = -1 ;
        for(int i = 0 ; i < 9 ; ++i)
        {
            int midCol = loCol + ((hiCol - loCol) >> 1) , midRow = loRow + ((hiRow - loRow) >> 1);
            if (arr[midRow][midCol] >= L && arr[midRow][midCol]<= U)
            {
                lastCol = Math.max(lastCol , midCol);
                loRow= Math.min(midRow + 1 , hiRow);
                loCol= Math.min(midCol + 1 , hiCol);
            }
            else if (arr[midRow][midCol] > U)
            {
                hiRow = Math.max(midRow - 1 , loRow);
                hiCol = Math.max(midCol - 1 , loCol);
            }
        }
        return lastCol;
    }
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while(true)
        {
            N = sc.nextInt() ; M = sc.nextInt();
            if(N == 0 && M ==0) break;
            arr = new int[N][M];
            for(int i = 0; i < N ; ++i) for(int j = 0 ; j < M ; ++j) arr[i][j] = sc.nextInt();
            int Q = sc.nextInt();
            while (Q -- > 0)
            {
                int L = sc.nextInt() , U = sc.nextInt();
                int max = 0;
                for (int i = 0 ; i < N ; ++i)
                {
                    int idx_row = BS_ROW(i , L);
                    if (idx_row == -1 || arr[i][idx_row] > U) continue;
                    int idx_diag = BS_DIAG(i , idx_row , L , U);
                    max = Math.max(idx_diag - idx_row + 1 , max);
                }
                sb.append(max+"\n");
            }
            sb.append("-\n");
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }













    static  class Scanner
    {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

        public String next() throws IOException
        {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {return Integer.parseInt(next());}

        public long nextLong() throws IOException {return Long.parseLong(next());}

        public String nextLine() throws IOException {return br.readLine();}

        public double nextDouble() throws IOException
        {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if(x.charAt(0) == '-')
            {
                neg = true;
                start++;
            }
            for(int i = start; i < x.length(); i++)
                if(x.charAt(i) == '.')
                {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                }
                else
                {
                    sb.append(x.charAt(i));
                    if(dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg?-1:1);
        }

        public boolean ready() throws IOException {return br.ready();}


    }

}
