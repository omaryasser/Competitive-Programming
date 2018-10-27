
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
        HashMap<Integer , String> type = new HashMap<>();
        for (int i = 0 ; i <= 8 ; ++i) type.put(i , (2 + i) +"");
        type.put(9,"Jack");type.put(10,"Queen");type.put(11,"King");type.put(12 , "Ace");
        HashMap<Integer , String> suit = new HashMap<>();
        suit.put(0 , "Clubs"); suit.put(1 , "Diamonds"); suit.put(2 , "Hearts"); suit.put(3 , "Spades");

        int T = Integer.parseInt(sc.nextLine());
        sc.nextLine();
        while (T -- > 0){
            ArrayList<Integer> input = new ArrayList<>();
            String line = "";
            while (sc.ready() && !(line = sc.nextLine()).equals("")){
                StringTokenizer st = new StringTokenizer(line);
                while (st.hasMoreTokens()) input.add(Integer.parseInt(st.nextToken()));
            }



            int idx = 0 ;
            int n = input.get(idx ++);
            int shuffles [][] = new int[n][52];
            for (int i = 0 ; i < n ; ++i){
                for (int j = 0 ; j < 52 ; ++j){
                    shuffles[i][j] = input.get(idx ++) - 1;
                }
            }
            int original [] = new int[52];
            for (int i = 0 ; i < 52 ; ++i) original[i] = i;
            while (idx < input.size()){
                int k = input.get(idx ++);
                original = apply(shuffles[k - 1] , original);
            }

            for (int i = 0 ; i < 52 ; ++i)
                sb.append(type.get(original[i] % 13 )).append(" of ").append(suit.get(original[i] / 13)).append("\n");
            if (T != 0) sb.append("\n");
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }



    static int [] apply (int shuffle [] , int arr []){
        int tmp [] = new int[52];
        for (int i = 0 ; i < 52 ; ++i)
            tmp[i] = arr[shuffle[i]];

        return tmp;
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
