
import java.io.*;
import java.util.*;

public class Main {

    static int V ;
    static ArrayList<Integer> adjList [];


    public static ArrayList<Integer> bfs (int start , int end)
    {
        if (start == end) return new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        int distance [] = new int[V];
        int parent [] = new int[V];
        Arrays.fill(parent , - 1);
        Arrays.fill(distance , - 1);

        distance[start] = 0;
        q.add(start);
        while (!q.isEmpty())
        {
            int v = q.poll();
            if (v == end) break;
            for (int i : adjList[v])
                if (distance[i] == -1)
                {
                    distance[i] = distance[v] + 1;
                    parent[i] = v;
                    q.add(i);
                }
        }
        ArrayList<Integer> res = new ArrayList<>();
        res.add(end);
        while (parent[end] != -1)
        {
            res.add(parent[end]);
            end = parent[end];
        }
        Collections.reverse(res);
        return res;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (sc.ready()) {
            V = Integer.parseInt(sc.nextLine());
            adjList = new ArrayList[V];
            for (int i = 0; i < V; ++i) adjList[i] = new ArrayList<>();
            for (int i = 0; i < V; ++i) {
                StringTokenizer st = new StringTokenizer(sc.nextLine(), "-,");
                int u = Integer.parseInt(st.nextToken());
                while (st.hasMoreTokens()) adjList[u - 1].add(Integer.parseInt(st.nextToken()) - 1);
            }
            out.print("-----\n");
            int Q = Integer.parseInt(sc.nextLine());
            while (Q-- > 0) {
                StringTokenizer st = new StringTokenizer(sc.nextLine());
                int start = Integer.parseInt(st.nextToken()) , end = Integer.parseInt(st.nextToken());
                ArrayList<Integer> result = bfs(start - 1 , end - 1);
                if (result .size() == 1 && start != end) out.print("connection impossible");
                else for (int i = 0; i < result.size(); ++i)
                    if (i == 0) out.print((result.get(i) + 1));
                    else out.printf(" %d", (result.get(i) + 1));
                out.print("\n");
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
