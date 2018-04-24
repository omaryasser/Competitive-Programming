
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while(sc.ready())
        {
            int N = sc.nextInt() , M = sc.nextInt();
            boolean done [] = new boolean[26];
            ArrayList<Integer> adjList [] = new ArrayList[26];
            for(int i = 0 ; i < 26 ; ++i ) adjList[i] = new ArrayList<>();
            char waken [] = sc.next().toCharArray();
            done[waken[0] - 'A'] = done[waken[1] - 'A'] = done[waken[2] - 'A'] = true;
            while (M -- > 0)
            {
                String inp = sc.next();
                int a = inp.charAt(0) - 'A' , b = inp.charAt(1) - 'A';
                adjList[a].add(b);
                adjList[b].add(a);
            }

            int cnt = 3;
            boolean ok = true;
            int years = 0;
            while (cnt < N)
            {
                int tmpCnt = 0;
                boolean tmpDone [] = new boolean[26];
                for (int i = 0 ; i < 26 ; ++i)
                    if(!done[i])
                    {
                        int connectedWaken = 0;
                        for(int j : adjList[i])
                            if (done[j]) ++connectedWaken;

                        if (connectedWaken >= 3){
                            ++ tmpCnt;
                            tmpDone[i] = true;
                        }
                    }
                if (tmpCnt == 0) {
                    ok = false;
                    break;
                }
                cnt +=tmpCnt;
                ++years;
                for (int i = 0 ; i < 26 ; ++i) done[i] |= tmpDone[i];
            }
            if (ok) out.printf("WAKE UP IN, %d, YEARS\n" , years);
            else out.printf("THIS BRAIN NEVER WAKES UP\n");
        }
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
