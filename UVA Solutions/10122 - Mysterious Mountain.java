import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        System.err.println(new Point(20, 0).time(new Point(13, 0), 13, 10, 1));
        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;
            V = 2 * n + 2;
            t = V - 1;
            s = V - 2;
            Point[] endPoints = new Point[n + 2];
            for (int i = 0; i < n + 2; i++) endPoints[i] = new Point(sc.nextInt(), sc.nextInt());
            Point[] people = new Point[n];
            int[] c = new int[n], w = new int[n];
            for (int i = 0; i < n; i++) {
                c[i] = sc.nextInt();
                w[i] = sc.nextInt();
                people[i] = new Point(sc.nextInt(), 0);
            }

            double[][] bestTime = new double[n][n + 2];
            for (int person = 0; person < n; person++) {
                for (int endPoint = 1; endPoint < n + 1; endPoint++) {
                    bestTime[person][endPoint] = 1e10;
                    if (people[person].x <= endPoints[endPoint].x) {
                        int left = (int)people[person].x;
                        for (int i = 0; i < endPoint; i++) {
                            if (endPoints[i].y < endPoints[endPoint].y)
                                left = Math.max(left, (int)Math.ceil(new Line(endPoints[i], endPoints[endPoint]).intersect(new Line(new Point(0, 0), new Point(2, 0))).x));
                        }
                        for (int i = left; i <= endPoints[endPoint].x; i++) {
                            bestTime[person][endPoint] = Math.min(bestTime[person][endPoint], people[person].time(endPoints[endPoint], i, c[person], w[person]));
                        }
                    }
                    else {
                        int right = (int)people[person].x;
                        for (int i = endPoint + 1; i < n + 2; i++) {
                            if (endPoints[i].y < endPoints[endPoint].y)
                                right = Math.min(right, (int)Math.floor(new Line(endPoints[i], endPoints[endPoint]).intersect(new Line(new Point(0, 0), new Point(2, 0))).x));
                        }
                        for (int i = (int)endPoints[endPoint].x; i <= right; i++) {
                            bestTime[person][endPoint] = Math.min(bestTime[person][endPoint], people[person].time(endPoints[endPoint], i, c[person], w[person]));
                        }
                    }
                }
            }
            double lo = 0, hi = 1e6 + 100;
            for (int cnt = 0; cnt <= 100; cnt++) {
                double check = (lo + hi) / 2;
                res = new int[V][V];
                adjList = new ArrayList[V];
                for (int i = 0; i < V; i++) adjList[i] = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    edge(s, i);
                    edge(n + i, t);
                }
                for (int person = 0; person < n; person++)
                    for (int endPoint = 1; endPoint < n + 1; endPoint++) {
                        if (bestTime[person][endPoint] <= check) edge(person, endPoint + n - 1);
                    }
                if (edmondsKarp() == n) {
                    hi = check;
                } else lo = check;
            }
            out.printf("%.2f\n", hi);
        }

        out.close();
    }

    static class Line {

        static final double EPS = 1e-9;

        double a, b, c;

        Line(Point p, Point q) {
            if (Math.abs(p.x - q.x) < EPS) {
                a = 1;
                b = 0;
                c = -p.x;
            } else {
                a = -(p.y - q.y) / (p.x - q.x);
                b = 1.0;
                c = -(a * p.x + p.y);
            }

        }

        boolean parallel(Line l) {
            return Math.abs(a - l.a) < EPS && Math.abs(b - l.b) < EPS;
        }

        Point intersect(Line l) {
            if (parallel(l))
                return null;
            double x = (b * l.c - c * l.b) / (a * l.b - b * l.a);
            double y;
            if (Math.abs(b) < EPS)
                y = -l.a * x - l.c;
            else
                y = -a * x - c;

            return new Point(x, y);

        }
    }

    static class Point {
        double x, y;
        Point (double xx, double yy) {
            x = xx;
            y = yy;
        }
        double dist (Point p2) {
            return Math.sqrt(sq(x - p2.x) + sq(y - p2.y));
        }
        double time (Point p2, int xx, int c, int w) {
            // x,y for the person
            return Math.abs(x - xx) / (w * 1.0) + p2.dist(new Point(xx, 0)) / (1.0 * c);
        }

        double sq (double x) {
            return x * x;
        }
    }
    static void edge (int u, int v) {
        res[u][v] = 1;
        adjList[u].add(v);
        adjList[v].add(u);
    }
    static final int INF = (int)1e9;
    static int V, s, t;
    static ArrayList<Integer>[] adjList;
    static int[][] res;
    static int[] p;

    static int augment(int v, int flow)
    {
        if(v == s)
            return flow;
        flow =  augment(p[v], Math.min(res[p[v]][v],flow));
        res[p[v]][v] -= flow;
        res[v][p[v]] += flow;

        return flow;
    }

    static int edmondsKarp()
    {
        int mf = 0;
        while(true)
        {
            Queue<Integer> q = new LinkedList<Integer>();
            p = new int[V];
            Arrays.fill(p, -1);
            p[s] = s;
            q.add(s);
            while(!q.isEmpty())
            {
                int u = q.remove();
                if(u == t)
                    break;
                for(int v : adjList[u])
                    if(res[u][v] > 0 && p[v] == -1)
                    {
                        p[v] = u;
                        q.add(v);
                    }
            }

            if(p[t] == -1)
                break;
            mf += augment(t, INF);

        }



        return mf;
    }
    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader s) {
            br = new BufferedReader(s);
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
            }
            return st.nextToken();
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
        public double nextDouble() {
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
    }
}
