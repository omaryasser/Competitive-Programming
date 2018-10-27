import java.io.*;
import java.util.*;


public class Main {
    static char first [][] ;
    static char second [][] ;
    static int n;
    static boolean pres (){
        for (int i = 0 ; i < n ; ++i){
            for (int j = 0 ; j < n ; ++j){
                if (first[i][j] != second[i][j]) return false;
            }
        }
        return true;
    }

    static boolean rot90 (){
        for (int i = 0 ; i < n ; ++i){
            for (int j = 0 ; j < n ; ++j){
                if (first[i][j] != second[j][n - 1 - i]) return false;
            }
        }
        return true;
    }

    static boolean rot180 (){
        for (int i = 0 ; i < n ; ++i){
            for (int j = 0 ; j < n ; ++j){
                if (first[i][j] != second[n - 1 - i][n - 1 - j]) return false;
            }
        }
        return true;
    }

    static  boolean rot270 (){
        for (int i = 0 ; i < n ; ++i){
            for (int j = 0; j < n ; ++j){
                if (first[i][j] !=  second[n - 1 - j][i]) return false;
            }
        }
        return true;
    }

    static boolean vertR (){
        for (int i = 0 ; i < n >> 1; ++i){
            for (int j = 0 ; j < n ; ++j){
                if (first[i][j] != second[n - 1 - i][j]) return false;
            }
        }
        return true;
    }
    static int vertR_rot (){
        char tmp [][] = new char[n][n];
        for (int i = 0 ; i < n ; ++i) for (int j = 0 ; j < n ; ++j) tmp[i][j] = first[i][j];
        for (int i = 0 ; i < n ; ++i){
            for (int j = 0 ; j < n ; ++j){
                first[i][j] = tmp[n - 1 - i][j];
            }
        }

        if (rot90()) return 90;
        else if (rot180()) return 180;
        else if (rot270()) return 270;
        else return - 1;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int t = 0;
        for (int tt = 1 ; sc.ready() ; ++ tt){
            n = sc.nextInt();
            first = new char[n][n];
            second = new char[n][n];
            for (int i = 0 ; i < n ; ++i){
                first[i] = sc.next().toCharArray();
                second[i] = sc.next().toCharArray();
            }
            if (pres()){
                sb.append("Pattern " + tt + " was preserved.\n");
            }
            else if (rot90()){
                sb.append("Pattern " + tt + " was rotated 90 degrees.\n");
            }
            else if (rot180()){
                sb.append("Pattern " + tt + " was rotated 180 degrees.\n");
            }
            else if (rot270()){
                sb.append("Pattern " + tt + " was rotated 270 degrees.\n");
            }
            else if (vertR()){
                sb.append("Pattern " + tt + " was reflected vertically.\n");
            }
            else {
                int x = vertR_rot();
                if (x != -1) {
                    sb.append("Pattern " + tt + " was reflected vertically and rotated " + x + " degrees.\n");
                }
                else sb.append("Pattern " + tt + " was improperly transformed.\n");

            }
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }






















    static class Scanner
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
