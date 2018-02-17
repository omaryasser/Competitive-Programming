import java.io.*;
import java.util.*;

public class Main {
    static int V;
    static class Edge
    {
        int node;
        boolean used;

        Edge(int x) { node = x; }
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
                itr.add(u);
                eulerTour(itr, nxt.node);
                itr.previous();
            }
    }

    public static void main(String [] args) throws  Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        LinkedList<Integer> linkedList = new LinkedList<>();
        ListIterator<Integer> it = linkedList.listIterator();
        it.add(2);
        it.add(3);
        it.previous();
        it.add(4);
        System.err.println(linkedList);
        int testCases = sc.nextInt();
        for (int tc = 1; tc <= testCases; tc++) {
            V = sc.nextInt();
            int [] u = new int[V], v = new int[V];
            int [] degree = new int[51];
            adjList = new ArrayList[51];
            for (int i = 0; i < 51; i++) adjList[i] = new ArrayList<>();
            int found = -1;
            for (int i = 0; i < V; i++) {
                u[i] = sc.nextInt();
                v[i] = sc.nextInt();
                found = u[i];
                degree[u[i]]++;
                degree[v[i]]++;
                adjList[u[i]].add(new Edge(v[i]));
                adjList[v[i]].add(new Edge(u[i]));
            }
            tour = new LinkedList<>();
            boolean ok = true;
            for (int i = 0; i < 51; i++)
                ok &= (degree[i] % 2 == 0);

            eulerTour (tour.listIterator(), found);
            tour.add(found);
            if (tc != 1) out.println();
            out.printf("Case #%d\n", tc);
            if (ok && tour.size() == V + 1) {
                for (int i = 0; i < tour.size() - 1; i++) {
                    out.println(tour.get(i) + " " + tour.get(i + 1));
                }
            } else {
                out.println("some beads may be lost");
            }
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
