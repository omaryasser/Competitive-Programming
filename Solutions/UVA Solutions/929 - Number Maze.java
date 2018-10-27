
import java.io.*;
import java.util.*;

public class Main {


    static class Edge implements  Comparable<Edge>
    {
        int node , cost ;
        Edge (int vv , int c) {node = vv ; cost = c;}

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
    static int dx [] = {0,0,1,-1};
    static int dy [] = {1,-1,0,0};
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T -- > 0)
        {
            int R = sc.nextInt() , C = sc.nextInt();
            int grid [][] = new int[R][C];
            for (int i = 0 ; i < R ; ++i) for (int j = 0 ; j < C ; ++j) grid[i][j] = sc.nextInt();

            int dist [] = new int[R * C];
            Arrays.fill(dist, (int)1e9);
            PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
            dist[0] = 0;
            pq.add(new Edge(0, 0));
            int res = 0;
            while(!pq.isEmpty())
            {
                Edge cur = pq.remove();
                int x = cur.node / C;
                int y = cur.node % C;
                if(x == R - 1 && y == C - 1)		//remove if all nodes are sinks
                {
                    res = dist[R * C - 1] + grid[R - 1] [C - 1];
                    break;
                }

                if(cur.cost > dist[cur.node])
                    continue;
                for(int i = 0 ; i < 4 ; ++i) {
                    int newX = x + dx[i] , newY = y + dy[i];
                    if (!(newX >= 0 && newY >=0 && newX < R && newY < C)) continue;
                    Edge nxt = new Edge(newX * C + (newY) , grid[x][y]);
                    if (cur.cost + nxt.cost < dist[nxt.node]) {
                        dist[nxt.node] = cur.cost + nxt.cost;
                        pq.add(new Edge(nxt.node, dist[nxt.node]));
                    }
                }

            }
            out.printf("%d\n" , res);
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
