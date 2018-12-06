import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Collections;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author OmarYasser
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskF solver = new TaskF();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskF {
        static int n;
        static int time;
        static int[] tin;
        static int[] tout;
        static int[] level;
        static ArrayList<Integer>[] adjList;
        static int[] a;
        static TaskF.Vertex[] arr;

        static void dfs(int node, int p, int lvl) {
            level[node] = lvl;
            tin[node] = time;
            arr[time++] = new TaskF.Vertex(lvl, a[node]);
            for (int nxt : adjList[node])
                if (nxt != p)
                    dfs(nxt, node, lvl + 1);
            tout[node] = time - 1;
        }

        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            n = sc.nextInt();
            arr = new TaskF.Vertex[n];
            a = new int[n];
            int root = sc.nextInt() - 1;
            tin = new int[n];
            level = new int[n];
            tout = new int[n];
            adjList = new ArrayList[n];
            for (int i = 0; i < n; i++)
                adjList[i] = new ArrayList<>();
            for (int i = 0; i < n; i++)
                a[i] = sc.nextInt();
            for (int i = 0; i < n - 1; i++) {
                int x = sc.nextInt() - 1, y = sc.nextInt() - 1;
                adjList[x].add(y);
                adjList[y].add(x);
            }
            dfs(root, -1, 0);
            TaskF.SegmentTree st = new TaskF.SegmentTree(arr);
            int m = sc.nextInt();
            int last = 0;
            while (m-- > 0) {
                int x = (sc.nextInt() + last) % n;
                int y = (sc.nextInt() + last) % n;
                out.println(last = st.query(tin[x], tout[x], level[x] + y + 1));
            }
        }

        static class Vertex {
            int level;
            int cost;

            Vertex(int l, int c) {
                level = l;
                cost = c;
            }

        }

        static class SegmentTree2 {
            ArrayList<TaskF.Vertex> vertices;
            int[] t;
            int n;
            int LOG;

            SegmentTree2(ArrayList<TaskF.Vertex> vertices) {
                this.vertices = vertices;
                n = vertices.size();
                LOG = (int) (Math.log(n) / Math.log(2) + 2);
                t = new int[n << 1 << 1];
                Collections.sort(vertices, new Comparator<TaskF.Vertex>() {

                    public int compare(TaskF.Vertex v1, TaskF.Vertex v2) {
                        return v1.level - v2.level;
                    }
                });
                build(1, 0, n - 1);
            }

            void build(int node, int s, int e) {
                if (s == e) t[node] = vertices.get(s).cost;
                else {
                    int mid = (s + e) >> 1;
                    build(node << 1, s, mid);
                    build(node << 1 | 1, mid + 1, e);
                    t[node] = Math.min(t[node << 1], t[node << 1 | 1]);
                }
            }

            int query(int badLevel) {
                int lo = 0, hi = vertices.size() - 1, best = -1;
                for (int cnt = 0; cnt < LOG; cnt++) {
                    int mid = lo + ((hi - lo) >> 1);
                    if (vertices.get(mid).level < badLevel) {
                        best = Math.max(best, mid);
                        lo = Math.min(hi, mid + 1);
                    } else
                        hi = Math.max(lo, mid - 1);
                }
                return query(0, best);
            }

            int query(int s, int e) {
                return e == -1 ? (int) 1e9 + 100 : query(1, 0, n - 1, s, e);
            }

            int query(int node, int s, int e, int l, int r) {
                if (s >= l && e <= r) return t[node];
                if (s > r || e < r) return (int) 1e9 + 100;
                int mid = (s + e) >> 1;
                return Math.min(query(node << 1, s, mid, l, r), query(node << 1 | 1, mid + 1, e, l, r));
            }

        }

        static class SegmentTree {
            TaskF.SegmentTree2[] T;
            int N;
            TaskF.Vertex[] arr;

            SegmentTree(TaskF.Vertex[] a) {
                arr = a;
                N = a.length;
                T = new TaskF.SegmentTree2[N << 1 << 1];
                build(1, 0, N - 1);
            }

            void build(int node, int s, int e) {
                if (s == e) {
                    ArrayList<TaskF.Vertex> vertices = new ArrayList<>();
                    vertices.add(arr[s]);
                    T[node] = new TaskF.SegmentTree2(vertices);
                } else {
                    int mid = (s + e) >> 1;
                    build(node << 1, s, mid);
                    build(node << 1 | 1, mid + 1, e);
                    ArrayList<TaskF.Vertex> vertices = new ArrayList<>();
                    vertices.addAll(T[node << 1].vertices);
                    vertices.addAll(T[node << 1 | 1].vertices);
                    T[node] = new TaskF.SegmentTree2(vertices);
                }
            }

            int query(int l, int r, int badLevel) {
                return query(1, 0, N - 1, l, r, badLevel);
            }

            int query(int node, int s, int e, int l, int r, int badLevel) {
                if (s >= l && e <= r) {
                    return T[node].query(badLevel);
                }
                if (s > r || e < l) return (int) 1e9 + 1000;
                int mid = (s + e) >> 1;
                return Math.min(query(node << 1, s, mid, l, r, badLevel), query(node << 1 | 1, mid + 1, e, l, r, badLevel));
            }

        }

    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader f) {
            br = new BufferedReader(f);
        }

        public String next() {
            while (st == null || !st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

