import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to;
        boolean used;
        int idx;
        Edge (int t, int i, boolean u) {
            idx = i;
            to = t;
            used = u;
        }
    }
    static ArrayList<Edge> adjList[];
    static ArrayList<Integer> tour;
    static void dfs (int node) {
        for (Edge nxt : adjList[node]) {
            if (!nxt.used) {
                nxt.used = true;
                dfs(nxt.to);
                tour.add(nxt.idx);
            }
        }
    }
    public static void main(String [] args) throws  Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int tc = sc.nextInt();
        while (tc-- > 0) {
            int n = sc.nextInt();
            String [] dic = new String [n];
            for (int i = 0; i < n; i++)
                dic[i] = sc.next();
            Arrays.sort(dic);
            tour = new ArrayList<>();
            adjList = new ArrayList[26];
            for (int i = 0; i < 26; i++) adjList[i] = new ArrayList<>();

            int [] inDeg = new int[26], outDeg = new int[26];
            for (int i = 0; i < n; i++) {
                int u = dic[i].charAt(0) - 'a', v = dic[i].charAt(dic[i].length() - 1) - 'a';
                adjList[u].add(new Edge(v, i, false));
                inDeg[v]++;
                outDeg[u]++;
            }

            int start = -1, end = -1;
            boolean ok = true;
            for (int i = 0; i < 26; i++) {
                if (inDeg[i] == outDeg[i] - 1) {
                    ok &= start == -1;
                    start = i;
                } else if (inDeg[i] - 1 == outDeg[i]) {
                    ok &= end == -1;
                    end = i;
                } else {
                    ok &= inDeg[i] == outDeg[i];
                }

            }
            if (!ok) {
                out.println("***");
                continue;
            }

            int startNode = start;
            if (start == -1) {
                for (int i = 0; i < 26; i++) {
                    if (outDeg[i] != 0) {
                        startNode = i;
                        break;
                    }
                }
            }

            dfs (startNode);
            if (tour.size() != n) {
                out.println("***");
            }
            else {
                for (int i = tour.size() - 1; i > 0; i--) {
                    out.print(dic[tour.get(i)] + ".");
                }
                out.println(dic[tour.get(0)]);
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
