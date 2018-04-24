
import java.io.*;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {
    static HashMap<Character,Integer> rankType;
    static HashMap<Character,Integer> rankSuit;
    static class MyString implements  Comparable<MyString>{
        char suit;
        char type;
        MyString (char s , char n){
            suit = s;
            type = n;
        }

        @Override
        public int compareTo(MyString myString) {
            if (rankSuit.get(suit) != rankSuit.get(myString.suit))
                return rankSuit.get(suit) - rankSuit.get(myString.suit);
            else return rankType.get(type) - rankType.get(myString.type);
        }
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        HashMap<Character , Integer> orientation = new HashMap<>();
        orientation.put('S' , 0);
        orientation.put('W' , 1);
        orientation.put('N' , 2);
        orientation.put('E' , 3);
        char [] orientation2 = {'S' , 'W' , 'N' , 'E'};
        rankType = new HashMap<>();
        for (int i = 2 ; i <= 9 ; ++i) rankType.put((char)('0' + i) , i);
        rankType.put('T' , 10);
        rankType.put('J' , 11);
        rankType.put('Q' , 12);
        rankType.put('K' , 13);
        rankType.put('A' , 14);
        rankSuit = new HashMap<>();
        rankSuit.put('C' , 0);
        rankSuit.put('D' , 1);
        rankSuit.put('S' , 2);
        rankSuit.put('H' , 3);

        MyString [][] arr = new MyString[4][13];
        while (true){
            char dealer = sc.next().charAt(0);
            if (dealer == '#') break;
            char deck [] = (sc.next() + sc.next()).toCharArray();
            for (int i = 0 , j = 0 ; i < deck.length ; i += 2 , j ++){
                int x = (orientation.get(dealer) + 1 + j) % 4;
                arr[x][j / 4] = new MyString(deck[i] , deck[i + 1]);
            }
            Arrays.sort(arr[0]);Arrays.sort(arr[1]);Arrays.sort(arr[2]);Arrays.sort(arr[3]);
            for (int i = 0 ; i < 4 ; ++i){
                sb.append(orientation2[i]).append(":");
                for (int j = 0 ; j < 13 ; ++j){
                    sb.append(" " + arr[i][j].suit +"" + arr[i][j].type);
                }
                sb.append("\n");
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
