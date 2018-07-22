import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class A {

static final int VIS = 2, EXP = 1, UNVIS = 0;

static final double EPS = 1e-9;
static ArrayList<Integer>[] adjList;
static int[] state;

static int dfs(int u, int p)
{

        int ret = 0, mult = 0;
        state[u] = EXP;
        for(int v : adjList[u])
                if(state[v] == UNVIS)
                        ret += dfs(v, u);
                else if(state[v] == EXP && (v != p || mult++ > 0)) {
                        System.err.println(u + " " + p);
                        ++ret;
                }
        state[u] = VIS;
        return ret;
}

public static void main(String[] args) throws IOException {

//        Scanner sc = new Scanner("circles.in");
//        PrintWriter out = new PrintWriter(new FileWriter("circles.out"));
//
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        TreeSet<Point>[] set = new TreeSet[n];
        for(int i = 0; i < n; ++i)
                set[i] = new TreeSet<>();
        TreeMap<Point, Integer> map = new TreeMap<>();
        Circle[] cirs = new Circle[n];
        for(int i = 0; i < n; ++i)
                cirs[i] = new Circle(new Point(sc.nextInt(), sc.nextInt()), sc.nextInt());
        for(int i = 0; i < n; ++i)
                for(int j = i + 1; j < n; ++j)
                {
                        Point[] p = intersect(cirs[i], cirs[j]);

                        if(p == null)
                                continue;
                        System.err.println(p[0].x + " " + p[0].y + " " + p[1].x + " " + p[1].y);
                        get(map, p[0]);
                        get(map, p[1]);
                        set[i].add(p[0]); set[i].add(p[1]);
                        set[j].add(p[0]); set[j].add(p[1]);
                }
        int m = map.size();
        adjList = new ArrayList[m];
        for(int i = 0; i < m; ++i)
                adjList[i] = new ArrayList<>();
        state = new int[m];
        for(int i = 0; i < n; ++i) {
                build(map, set[i], cirs[i]);
                System.err.println(adjList[0]);
        }
        int ans = 1;
        for(int i = 0; i < m; ++i)
                if(state[i] == UNVIS)
                        ans += dfs(i, -1);
        for(int i = 0; i < n; ++i)
                if(set[i].size() == 0)
                        ++ans;
        out.println(ans);
        out.close();
}

static Point[] intersect(Circle cir1, Circle cir2)
{
        double r0 = cir1.r, r1 = cir2.r;
        double dx = cir1.center.x, dy = cir1.center.y;
        double x1 = cir2.center.x - dx, y1 = cir2.center.y - dy;

        double a = -2 * x1;
        double b = -2 * y1;
        double c = sq(x1) + sq(y1) + sq(r0) - sq(r1);

        double x0 = -a*c/(sq(a)+sq(b)),  y0 = -b*c/(sq(a)+sq(b)), yy1, yy2, xx1, xx2;
        if (c*c > r0*r0*(a*a+b*b)+EPS)
                return null;

        if (Math.abs(c*c - sq(r0)*(a*a+b*b)) < EPS) {
                xx1 = xx2 = x0;
                yy1 = yy2 = y0;
        }
        else {
                double d = sq(r0) - c*c/(a*a+b*b);
                double mult = Math.sqrt (d / (a*a+b*b));
                xx1 = x0 + b * mult;
                xx2 = x0 - b * mult;
                yy1 = y0 - a * mult;
                yy2 = y0 + a * mult;
        }
        return new Point[] {new Point(xx1 + dx, yy1 + dy), new Point(xx2 + dx, yy2 + dy)};
}

static double sq(double x) {
        return x * x;
}

static void addEdge(int x, int y)
{
        adjList[x].add(y);
        if(x != y)
                adjList[y].add(x);
}

static double angle(Point p, Point q)
{
        double x = q.x - p.x;
        double y = q.y - p.y;
        return Math.atan2(y, x);
}

static void build(TreeMap<Point, Integer> map, TreeSet<Point> set, Circle cir)
{
        int n = set.size();
        if(n == 0)
                return;
        Pair[] ps = new Pair[n];
        int i = 0;
        for(Point p : set)
                ps[i++] = new Pair(angle(cir.center, p), map.get(p));
        Arrays.sort(ps);
        for(i = 0; i < n - 1; ++i)
                addEdge(ps[i].idx, ps[i + 1].idx);
        addEdge(0, n - 1);
}

static int get(TreeMap<Point, Integer> map, Point p)
{
        Integer ret = map.get(p);
        if(ret == null)
                map.put(p, ret = map.size());
        return ret;
}

static class Point implements Comparable<Point>
{
double x, y;

Point(double a, double b) {
        x = a; y = b;
}

public int compareTo(Point p)
{
        if(Math.abs(x - p.x) > EPS)
                return x > p.x ? 1 : -1;
        if(Math.abs(y - p.y) > EPS)
                return y > p.y ? 1 : -1;
        return 0;
}
}

static class Pair implements Comparable<Pair>
{
double angle;
int idx;

Pair(double a, int b) {
        angle = a; idx = b;
}

public int compareTo(Pair p) {
        return Double.compare(angle, p.angle);
}
}

static class Circle { Point center; int r; Circle(Point a, int b) {
                              center = a; r = b;
                      } }

static class Scanner
{
StringTokenizer st;
BufferedReader br;

public Scanner(InputStream s){
        br = new BufferedReader(new InputStreamReader(s));
}

public Scanner(String s) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(s));
}

public String next() throws IOException
{
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
        return Double.parseDouble(next());
}

public boolean ready() throws IOException {
        return br.ready();
}
}
}
