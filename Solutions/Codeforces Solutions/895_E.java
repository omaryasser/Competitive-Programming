import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            int n = sc.nextInt(), q = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            TaskE.SegmentTree st = new TaskE.SegmentTree(arr);
            while (q-- > 0) {
                if (sc.nextInt() == 1) {
                    int l1 = sc.nextInt() - 1, r1 = sc.nextInt() - 1, l2 = sc.nextInt() - 1, r2 = sc.nextInt() - 1;
                    double sum1 = st.query(l1, r1), sum2 = st.query(l2, r2);
                    long len1 = r1 - l1 + 1, len2 = r2 - l2 + 1;
                    double add1 = (sum2 / (len1 * len2)), mult1 = (len1 - 1.0) / len1;
                    double add2 = (sum1 / (len1 * len2)), mult2 = (len2 - 1.0) / len2;
//                System.err.println(add1 + " " + add2 + " " + mult1 + " " + mult2);
                    st.update(l1, r1, mult1, add1);
                    st.update(l2, r2, mult2, add2);
                } else out.printf("%.9f\n", st.query(sc.nextInt() - 1, sc.nextInt() - 1));
            }
        }

        static class SegmentTree {
            int n;
            int[] arr;
            double[] tree;
            double[] lazyMult;
            double[] lazyAdd;

            SegmentTree(int[] a) {
                arr = a;
                n = a.length;
                tree = new double[n << 1 << 1];
                lazyMult = new double[n << 1 << 1];
                lazyAdd = new double[n << 1 << 1];
                build(1, 0, n - 1);
            }

            void build(int node, int s, int e) {
                if (s == e) {
                    tree[node] = arr[s];
                } else {
                    int mid = (s + e) >> 1;
                    build(node << 1, s, mid);
                    build(node << 1 | 1, mid + 1, e);
                    tree[node] = tree[node << 1] + tree[node << 1 | 1];
                }
                lazyMult[node] = 1;
                lazyAdd[node] = 0;
            }

            void update(int s, int e, double mult, double add) {
                update(1, 0, n - 1, s, e, mult, add);
            }

            void update(int node, int s, int e, int l, int r, double mult, double add) {
                propagate(node, s, e);
                if (s >= l && e <= r) {
                    tree[node] = mult * tree[node] + (e - s + 1.0) * add;
                    if ((node << 1) < lazyMult.length) {
                        lazyMult[node << 1] *= mult;
                        lazyAdd[node << 1] *= mult;
                        lazyAdd[node << 1] += add;
                    }
                    if ((node << 1 | 1) < lazyMult.length) {
                        lazyMult[node << 1 | 1] *= mult;
                        lazyAdd[node << 1 | 1] *= mult;
                        lazyAdd[node << 1 | 1] += add;
                    }
                } else if (s > r || e < l) {
                    return;
                } else {
                    int mid = (s + e) >> 1;
                    update(node << 1, s, mid, l, r, mult, add);
                    update(node << 1 | 1, mid + 1, e, l, r, mult, add);
                    tree[node] = tree[node << 1] + tree[node << 1 | 1];
                }
            }

            void propagate(int node, int s, int e) {
                tree[node] = lazyMult[node] * tree[node] + (e - s + 1.0) * lazyAdd[node];
                if ((node << 1) < lazyMult.length) {
                    lazyMult[node << 1] *= lazyMult[node];
                    lazyAdd[node << 1] *= lazyMult[node];
                    lazyAdd[node << 1] += lazyAdd[node];
                }
                if ((node << 1 | 1) < lazyMult.length) {
                    lazyMult[node << 1 | 1] *= lazyMult[node];
                    lazyAdd[node << 1 | 1] *= lazyMult[node];
                    lazyAdd[node << 1 | 1] += lazyAdd[node];
                }
                lazyMult[node] = 1;
                lazyAdd[node] = 0;
            }

            double query(int s, int e) {
                return query(1, 0, n - 1, s, e);
            }

            double query(int node, int s, int e, int l, int r) {
                propagate(node, s, e);
                if (s >= l && e <= r) return tree[node];
                if (s > r || e < l) return 0;
                int mid = (s + e) >> 1;
                return query(node << 1, s, mid, l, r) + query(node << 1 | 1, mid + 1, e, l, r);
            }

        }

    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
                ;
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}

