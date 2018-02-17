
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while(true)
        {
            TreeMap<Frosh , Integer> treeMap = new TreeMap<Frosh , Integer>();
            int max = 0 , res = 0;
            int N = sc.nextInt();
            if(N == 0) break;
            while (N-- > 0)
            {
                int arr [] = new int[5];
                for(int i= 0 ; i < 5 ; ++i) arr[i] = sc.nextInt();
                Frosh current = new Frosh(arr);
                Integer freq = treeMap.get(current);
                if (freq == null) freq = 1;
                else ++freq;

                treeMap.put(current , freq);

                if(freq > max) {
                    max = freq;
                    res = freq;
                }
                else if(freq == max) res+=freq;
            }
            out.printf("%d\n" , res);
        }
        out.flush();
        out.close();
    }
    static class Frosh implements  Comparable<Frosh>{
        TreeSet<Integer> ts = new TreeSet<Integer>();
        Frosh (int arr []) {for(int i = 0 ; i < 5 ; ++i) ts.add(arr[i]);}

        @Override
        public int compareTo(Frosh o) {
            int first [] = new int[5] , second [] = new int[5];
            int idx = 0; for(int i : ts) first[idx++] =i;
            idx = 0 ;    for(int i : o.ts) second[idx++] =i;
            for (int i = 0 ; i < 5 ;++i)
                if (first[i] != second[i])
                    return first[i] - second[i];
            return 0 ;
        }

        @Override
        public boolean equals(Object obj) {
            return ts.equals(((Frosh)(obj)).ts);
        }
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
