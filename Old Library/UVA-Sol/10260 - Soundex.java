import java.io.*;
import java.util.*;


public class Main {

    static HashMap<Character , Integer> hashMap = new HashMap<>();
    static void fillMap (){
        hashMap.put('B' , 1);
        hashMap.put('F' , 1);
        hashMap.put('P' , 1);
        hashMap.put('V' , 1);
        hashMap.put('C' , 2);
        hashMap.put('G' , 2);
        hashMap.put('J' , 2);
        hashMap.put('K' , 2);
        hashMap.put('Q' , 2);
        hashMap.put('S' , 2);
        hashMap.put('X' , 2);
        hashMap.put('Z' , 2);
        hashMap.put('D' , 3);
        hashMap.put('T' , 3);
        hashMap.put('L' , 4);
        hashMap.put('M' , 5);
        hashMap.put('N' , 5);
        hashMap.put('R' , 6);
    }


    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        fillMap();

        int t = 0;
        while (sc.ready()){
            char [] arr = sc.nextLine().toCharArray();
            String ans = "-";
            for (int i = 0 ; i < arr.length ; ++i) {
                if (!hashMap.containsKey(arr[i])) ans += "-" ;
                else if (hashMap.get(arr[i])  == (ans.charAt(ans.length() - 1)) - '0') continue;
                else {
                    ans += hashMap.get(arr[i]);
                }
            }
            out.print(ans.replace("-" , "") + "\n");
        }
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
