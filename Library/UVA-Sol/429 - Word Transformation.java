
import java.io.*;
import java.util.*;

public class Main {

    static int V ;
    static ArrayList<Integer> adjList [];
    public static boolean ok (String first , String second)
    {
        if (first.length() != second.length()) return false;
        int cnt = 0;
        for (int i = 0 ; i < first.length() ; ++i)
            if (first.charAt(i) != second.charAt(i)) cnt ++;
        return cnt == 1;
    }

    public static int bfs (int start , int end)
    {
        Queue<Integer> q = new LinkedList<>();
        int distance [] = new int[V];
        distance[start] = 1;
        q.add(start);
        while (!q.isEmpty())
        {
            int v = q.poll();
            if (v == end) return distance[v] - 1;
            for (int i : adjList[v])
                if (distance[i] == 0)
                {
                    distance[i] = distance[v] + 1;
                    q.add(i);
                }
        }
        return distance[end] - 1;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        boolean firstt = true;
        while (T -- > 0) {
            if (1 == 2) break;
            ArrayList<String> arr = new ArrayList<>();
            HashMap<String, Integer> map = new HashMap<>();
            V = 0;
            while (true) {
                String inp = sc.nextLine();
                if (inp.equals("*")) break;
                if (!map.containsKey(inp)) {
                    map.put(inp, V++);
                    arr.add(inp);
                }
            }
            adjList = new ArrayList[V];
            for (int i = 0; i < V; ++i) adjList[i] = new ArrayList<>();
            for (int i = 0; i < V; ++i)
                for (int j = i + 1; j < V; ++j) {
                    if (ok(arr.get(i), arr.get(j))) {
                        adjList[i].add(j);
                        adjList[j].add(i);
                    }
                }
            if (firstt) firstt = false;
            else out.print("\n");
            String first = "omar";
            while (sc.ready() && !((first = sc.nextLine()) .equals(""))) {
                String split [] = first.split(" " );
                int res = bfs(map.get(split[0]), map.get(split[1]));
                out.printf("%s %s %d\n", split[0], split[1], res);
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
