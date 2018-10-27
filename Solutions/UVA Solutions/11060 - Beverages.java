import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        for (int tc = 1; sc.ready(); tc++) {
            int n = sc.nextInt();
            HashMap<String,Integer> stringToInt = new HashMap<>();
            HashMap<Integer,String> intToString = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String x = sc.next();
                stringToInt.put(x, i);
                intToString.put(i, x);
            }

            ArrayList<Integer> adjList [] = new ArrayList[n];
            for (int i = 0; i < n; i++) adjList[i] = new ArrayList<>();
            int inDeg [] = new int[n];

            int m = sc.nextInt();
            while (m-- > 0) {
                int u = stringToInt.get(sc.next()), v = stringToInt.get(sc.next());
                adjList[u].add(v);
                inDeg[v]++;
            }

            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
            for (int i = 0; i < n; i++) if (inDeg[i] == 0) priorityQueue.add(i);

            ArrayList<String> res = new ArrayList<>();
            while (!priorityQueue.isEmpty()) {
                int cur = priorityQueue.poll();
                res.add(intToString.get(cur));

                for (int nxt : adjList[cur]) {
                    if (--inDeg[nxt] == 0) priorityQueue.add(nxt);
                }
            }

            out.printf("Case #%d: Dilbert should drink beverages in this order:", tc);
            for (String x : res) out.print(" " + x);
            out.print(".\n\n");
            sc.nextLine();
        }

        out.flush();
        out.close();
    }


    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner (FileReader f) {br = new BufferedReader(f);}

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }

    }
}
