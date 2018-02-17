
import java.io.*;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        for (int T = 1 ; ; ++ T){
            char [][] board = new char[8][8];
            for (int i = 0 ; i < 8 ; ++i) board[i] = sc.next().toCharArray();
            if (empty(board)) break;

            boolean whiteCheck = false;
            boolean blackCheck = false;

            for (int i = 0 ; i < 8 ; ++i){
                for (int j = 0 ; j < 8 ; ++j){
                    if (board[i][j] == 'k') blackCheck |= inCheck (board , i , j);
                    if (board[i][j] == 'K') whiteCheck |= inCheck (board , i , j);
                }
            }

            out.printf("Game #%d: " , T);
            if (whiteCheck) out.print("white king is in check.\n");
            else if (blackCheck) out.print("black king is in check.\n");
            else out.print("no king is in check.\n");
        }


        out.flush();
        out.close();
    }

    static boolean inCheck (char [][] b , int row , int col){
        boolean isLower = Character.isLowerCase(b[row][col]);
        char rook = isLower ? 'R' : 'r';
        char bishop = isLower ? 'B' : 'b';
        char queen = isLower ? 'Q' : 'q';
        char pawn = isLower ? 'P' : 'p';
        char knight = isLower ? 'N' : 'n';
        for (int i = row + 1 ; i < 8 ; ++i){
            if (b[i][col] != '.'){
                if (b[i][col] == rook || b[i][col] == queen) return true;
                else break;
            }
        }

        for (int i = row - 1 ; i >= 0 ; -- i){
            if (b[i][col] != '.'){
                if (b[i][col] == rook || b[i][col] == queen) return true;
                else break;
            }
        }

        for (int i = col + 1 ; i < 8 ; ++i){
            if (b[row][i] != '.'){
                if (b[row][i] == rook || b[row][i] == queen) return true;
                else break;
            }
        }

        for (int i = col - 1 ; i >= 0 ; --i){
            if (b[row][i] != '.'){
                if (b[row][i] == rook || b[row][i] == queen) return true;
                else break;
            }
        }
        for (int i = 1 ; i + row < 8 && i + col < 8 ; ++i){
            if (b[row + i][col + i] != '.'){
                if (b[row + i][col + i] == bishop || b[row + i][col + i] == queen) return true;
                else break;
            }
        }

        for (int i = - 1 ; row + i >= 0 && col + i >= 0 ; --i){
            if (b[row + i][col + i] != '.'){
                if (b[row + i][col + i] == bishop || b[row + i][col + i] == queen) return true;
                else break;
            }
        }

        for (int i = 1 ; i + row < 8 && col - i >= 0 ; ++i){
            if (b[row + i][col - i] != '.'){
                if (b[row + i][col - i] == bishop || b[row + i][col - i] == queen) return true;
                else break;
            }
        }

        for (int i = 1 ; row - i >= 0 && col + i < 8 ; ++i){
            if (b[row - i][col + i] != '.'){
                if (b[row - i][col + i] == bishop || b[row - i][col + i] == queen) return true;
                else break;
            }
        }

        if (!isLower) {
            if (row - 1 >= 0 && col - 1 >= 0 && b[row - 1][col - 1] == pawn) return true;
            if (row - 1 >= 0 && col + 1 < 8 && b[row - 1][col + 1] == pawn) return true;
        }
        else {
            if (row + 1 < 8 && col - 1 >= 0 && b[row + 1][col - 1] == pawn) return true;
            if (row + 1 < 8 && col + 1 < 8 && b[row + 1][col + 1] == pawn) return true;
        }
        int dx [] = {-2,-2,-1,1,2,2,1,-1};
        int dy [] = {-1,1,2,2,1,-1,-2,-2};
        for (int i = 0 ; i < 8 ; ++i)
            if (valid(row + dx[i] , col + dy[i]))
                if (b[row + dx[i]][col + dy[i]] == knight) return true;
        return false;
    }

    static boolean valid (int x , int y){
        return x >= 0 && y >= 0 && x < 8 && y < 8;
    }
    static boolean empty (char [][] b ){
        for (int i = 0 ; i < 8 ; ++i) for (int j = 0 ; j < 8 ; ++j) if (b[i][j] != '.') return false;
        return true;
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
