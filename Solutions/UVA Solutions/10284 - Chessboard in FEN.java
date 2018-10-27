
import java.io.*;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {
    static char grid [][];
    static int MAX_R;
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int t = 0 ;
        while(t ++ < 2){
            MAX_R = 0 ;
            char arr [] = sc.nextLine().toCharArray();
            for (int i = 0 ; ; ++i) if (arr[i] == '/') break; else if (Character.isDigit(arr[i])) MAX_R += arr[i] - '0'; else ++ MAX_R;
            grid = new char[MAX_R][8];

            for (int z = 0 , i = 0 , j = 0 ; z < arr.length ; ++z ){
                if (j == 8) {
                    j = 0;
                    ++i;
                    continue;
                }
                if (Character.isDigit(arr[z])){
                    for (int k = 0 ; k < arr[z] - '0' ; ++k){
                        if (j == 8) {
                            j = 0 ;
                            ++ i;
                        }
                        grid[i][j ++] = '.';
                    }
                }
                else {
                    grid[i][j] = arr[z];
                    ++ j;
                }
            }
            boolean checked [][] = new boolean[MAX_R][8];
            for (int i = 0 ; i < MAX_R ; ++i){
                for (int j = 0 ; j < 8 ; ++j){
                    if (grid[i][j] != '.') {
                        fill(checked , i, j);
                    }
                }
            }

            int cnt = 0 ;
            for (int i = 0 ; i < MAX_R ; ++i){
                for (int j = 0 ; j < 8 ; ++j){
                    if (!checked[i][j] && grid[i][j] == '.'){
                        ++ cnt;
                    }
                }
            }

            out.printf("%d\n" , cnt);
        }
        out.flush();
        out.close();
    }






    static void fill (boolean checked [][] , int row , int col){
        if (grid[row][col] == 'p'){
            if (valid(row + 1 , col + 1)) checked[row + 1][col + 1] = true;
            if (valid(row + 1 , col - 1)) checked[row + 1][col - 1] = true;
        }
        if (grid[row][col] == 'P'){
            if (valid(row - 1 , col + 1)) checked[row - 1][col + 1] = true;
            if (valid(row - 1 , col - 1)) checked[row - 1][col - 1] = true;
        }
        char c = Character.toLowerCase(grid[row][col]);

        if (c == 'k'){
            int dx [] = {-1,-1,0,1,1,1,0,-1};
            int dy [] = {0,1,1,1,0,-1,-1,-1};
            for (int i = 0 ; i < 8 ; ++i){
                if (valid(row + dx[i] , col + dy[i])) checked[row + dx[i]][col + dy[i]] = true;
            }
        }

        if (c == 'r' || c == 'q'){
            int dx [] = {1,-1,0,0};
            int dy [] = {0,0,1,-1};
            for (int k = 0 ; k < 4 ; ++k){
                int x = dx[k];
                int y = dy[k];
                for (int i = row + x , j = col + y ; valid(i , j) ; x += dx[k] , y += dy[k] , i = row + x , j = col + y){
                    checked[i][j] = true;
                }
            }
        }

        if (c == 'b' || c == 'q'){
            int dx [] = {-1,1,1,-1};
            int dy [] = {1,1,-1,-1};
            for (int k = 0 ; k < 4 ; ++k){
                int x = dx[k];
                int y = dy[k];
                for (int i = row + x , j = col + y ; valid(i , j) ; x += dx[k] , y += dy[k] , i = row + x , j = col + y){
                    checked[i][j] = true;
                }
            }
        }

        if (c == 'n'){
            int dx [] = {-2,-2,-1,1,2,2,1,-1};
            int dy [] = {-1,1,2,2,1,-1,-2,-2};
            for (int i = 0 ; i < 8 ; ++ i){
                if (valid(row + dx[i] , col + dy[i])) {
                    checked[row + dx[i]][col + dy[i]] = true;
                }
            }
        }

    }

    static boolean valid (int x , int y){
        return x >= 0 && y >= 0 && x < MAX_R && y < 8 && grid[x][y] == '.';
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
