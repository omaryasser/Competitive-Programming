
import java.io.*;
import java.util.*;

public class Main {


    static int V;
    static int adjMat [][];
    public static void floyd_warshall()
    {

        for(int k = 0 ; k < V ; ++k)
            for(int i = 0 ; i < V ; ++i)
                for(int j = 0 ; j < V ; ++j)
                    adjMat[i][j] = Math.min(adjMat[i][j] , adjMat[i][k] + adjMat[k][j]);

    }


    static class Pair {
        int u , v;
        Pair(int uu , int vv) {u = uu ; v = vv;}
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        for (int T = 1 ; ; T ++)
        {
            HashMap<Integer , Integer> map = new HashMap<>();
            ArrayList<Pair> pairs = new ArrayList<>();
            int first = sc.nextInt() , second = sc.nextInt();
            if (first == 0 && second == 0) break;
            map.put(first , 0); map.put(second , 1);
            pairs.add(new Pair(map.get(first) , map.get(second)));
            V = 2;
            while (true)
            {
                first = sc.nextInt() ; second = sc.nextInt();
                if (first == 0 && second == 0) break;
                if (!map.containsKey(first)) map.put(first , V ++);
                if (!map.containsKey(second)) map.put(second , V ++);
                pairs.add(new Pair(map.get(first) , map.get(second)));
            }
            adjMat = new int[V][V];
            for (int i = 0 ; i < V ; ++i) Arrays.fill(adjMat[i] , (int)1e9);
            for (int i = 0 ; i < pairs.size() ; ++i)
                adjMat[pairs.get(i).u][pairs.get(i).v] = 1;


            floyd_warshall();

            int dist = 0;
            int sum = 0;
            for (int i = 0 ; i < V; ++i)
                for (int j = 0 ; j < V ; ++j)
                    if (i != j && adjMat[i][j] < (int)1e9){
                        ++sum;
                        dist+=adjMat[i][j];
                    }
            out.printf("Case %d: average length between pages = %.3f clicks\n" , T , (double)dist /sum);
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
