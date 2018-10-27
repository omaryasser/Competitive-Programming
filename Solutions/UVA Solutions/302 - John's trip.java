import java.io.*;
import java.util.*;

public class Main {
    static int V;
    static class Edge implements Comparable<Edge>
    {
        int node;
        boolean used;
        int street;

        Edge(int x, int s) { node = x; street = s;}

        @Override
        public int compareTo(Edge edge) {
            return street - edge.street;
        }
    }

    static ArrayList<Edge>[] adjList;
    static LinkedList<Integer> tour;

    static void eulerTour(ListIterator<Integer> itr, int u)
    {
        for(Edge nxt: adjList[u])
            if(!nxt.used)
            {
                nxt.used = true;
                for(Edge rev: adjList[nxt.node])
                    if(rev.node == u && !rev.used)
                    {
                        rev.used = true;
                        break;
                    }
                itr.add(nxt.street);
                eulerTour(itr, nxt.node);
                itr.previous();
            }
    }

    public static void main(String [] args) throws  Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
            int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
            if (u == -1 && v == -1) break;
            int z = sc.nextInt();

            int degree [] = new int[44];
            adjList = new ArrayList[44];
            for (int i = 0; i < 44; i++) adjList[i] = new ArrayList<>();
            int start = Math.min(u, v);
            adjList[u].add(new Edge(v, z));
            adjList[v].add(new Edge(u, z));
            degree[u]++;
            degree[v]++;
            while (true) {
                u = sc.nextInt() - 1;
                v = sc.nextInt() - 1;
                if (u == -1 && v == -1) break;
                degree[u]++;
                degree[v]++;
                z = sc.nextInt();
                adjList[u].add(new Edge(v, z));
                adjList[v].add(new Edge(u, z));
            }

            boolean can = true;
            for (int i = 0; i < 44; i++) can &= (degree[i] % 2 == 0);
            for (int i = 0; i < 44; i++) Collections.sort(adjList[i]);
            if (!can) {out.println("Round trip does not exist.\n"); continue;}
            tour = new LinkedList<>();
            eulerTour(tour.listIterator(), start);
            for (int i = 0; i < tour.size() - 1; i++)
                out.print(tour.get(i) + " " );
            out.println(tour.get(tour.size() - 1) + "\n");
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
