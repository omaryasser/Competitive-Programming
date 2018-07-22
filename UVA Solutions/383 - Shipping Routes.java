
import java.io.*;
import java.util.*;

public class Main {

    static int V , E ;
    static HashMap<String , Integer> map;
    static ArrayList<Integer> adjList [] ;
    static int min ;
    public static int bfs (int u ,  int end)
    {
        Queue<Integer> q = new LinkedList<>();
        int distance [] = new int[V];
        Arrays.fill(distance , - 1);
        q.add(u); distance[u] = 0;
        while (!q.isEmpty())
        {
            int v = q.poll();
            if (v == end) return distance[v];
            for (int i : adjList[v])
                if (distance[i] == -1)
                {
                    q.add(i);
                    distance[i] = distance[v] + 1;
                }
        }
        return distance[end];
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        out.printf("SHIPPING ROUTES OUTPUT\n\n");
        int T = sc.nextInt();
        for (int t =  1;  t <= T ; ++t)
        {
            V = sc.nextInt();
            E = sc.nextInt();
            int P = sc.nextInt();
            map = new HashMap<>();
            for (int i = 0 ; i < V ; ++i) map.put(sc.next() , i);
            adjList = new ArrayList[V];
            for (int i = 0 ; i < V ; ++i) adjList[i] = new ArrayList<>();
            for (int i = 0 ; i < E ; ++i) {
                int u = map.get(sc.next()) , v = map.get(sc.next());
                adjList[u].add(v);
                adjList[v].add(u);
            }
            out.printf("DATA SET  %d\n\n" , t);

            for (int i = 0 ; i < P ; ++i)
            {
                int size = sc.nextInt();
                int res = bfs(map.get(sc.next()), map.get(sc.next()));
                if (res != -1) out.printf("$%d\n" , res * size * 100);
                else out.printf("NO SHIPMENT POSSIBLE\n");
            }
            out.printf("\n");

        }
        out.printf("END OF OUTPUT\n");
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
