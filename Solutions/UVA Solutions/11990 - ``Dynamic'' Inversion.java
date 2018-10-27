import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        StringBuilder sb = new StringBuilder();

        while (sc.ready()) {
            int n = sc.nextInt(), m = sc.nextInt();
            int arr[] = new int[n];
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
                map.put(arr[i], i);
            }
            long res = new Merge(arr.clone()).solve();
            SegmentTree st = new SegmentTree(arr);
            while (m-- > 0) {
                sb.append(res + "\n");
                int num = sc.nextInt();
                int pos = map.get(num);
                st.update(pos, num);
                long left = pos == 0 ? 0 : st.query(0, pos - 1, 1, num);
                long right = pos == n - 1 ? 0 : st.query(pos + 1, n - 1, 0, num);
                res = res - (left + right);
            }
        }

        out.print(sb);
        out.flush();
        out.close();
    }

    static class SegmentTree {
        int n;
        int arr[];
        FenwickTree[] tree;
        int [] num_pos[];
        int [] pos_num[];
        int [] sz;
        SegmentTree(int a[]) {
            arr = a;
            n = a.length;
            int log = 0;
            while (1 << log <= n) log++; log++;
            num_pos = new int[log + 1] [(int)2e5 + 10];
            pos_num = new int[log + 1] [(int)2e5 + 10];
            sz = new int[1 << log];
            tree = new FenwickTree[1 << log];
            build(1, 0, n - 1, 0);
        }

        void build(int node, int s, int e, int lvl) {
            if (s == e) {
                tree[node] = new FenwickTree(1);
                num_pos[lvl][arr[s]] = s;
                pos_num[lvl][s] = arr[s];
                sz[node] = 1;
            } else {
                int mid = (s + e) >> 1;
                build(node << 1, s, mid, lvl + 1);
                build(node << 1 | 1, mid + 1, e, lvl + 1);
                int n = sz[node << 1], m = sz[node << 1 | 1], idx1 = s, idx2 = mid + 1, curIdx = s, szz = 0;
                tree[node] = new FenwickTree(n + m);
                while (idx1 <= mid || idx2 <= e) {
                    szz++;
                    if (idx1 != mid + 1 && (idx2 == e + 1 || pos_num[lvl + 1][idx1] < pos_num[lvl + 1][idx2])) {
                        pos_num[lvl][curIdx] = pos_num[lvl + 1][idx1];
                        num_pos[lvl][pos_num[lvl + 1][idx1]] =  curIdx++;
                        idx1++;
                    } else {
                        pos_num[lvl][curIdx] = pos_num[lvl + 1][idx2];
                        num_pos[lvl][pos_num[lvl + 1][idx2]] =  curIdx++;
                        idx2++;
                    }
                }
                sz[node] = szz;
            }
        }

        long query(int l, int r, int op, int val) {
            return query(1, 0, n - 1, l, r, val, op, 0);
        }

        long query(int node, int s, int e, int l, int r, int val, int op, int lvl) {
            if (s >= l && e <= r) {
                int lo = s, hi = e, best = op == 0 ? s - 1 : e + 1;
                int LOG = 0;
                while ((1 << LOG) <= hi) LOG++;
                for (int cnt = 0; cnt <= LOG; cnt++) {
                    int mid = lo + ((hi - lo) >> 1);
                    if (op == 0) {
                        if (pos_num[lvl][mid] < val) {
                            best = mid;
                            lo = Math.min(hi, mid + 1);
                        } else {
                            hi = Math.max(lo, mid - 1);
                        }
                    } else {
                        if (pos_num[lvl][mid] > val) {
                            best = mid;
                            hi = Math.max(lo, mid - 1);
                        } else {
                            lo = Math.min(hi, mid + 1);
                        }
                    }
                }
                if (op == 0) {
                    return best == s - 1 ? 0 : best + 1 - s - tree[node].rsq(best - s);
                } else {
                    return sz[node] - tree[node].rsq(sz[node] - 1) - (best == s ? 0 : best - s - tree[node].rsq(best - 1 - s));
                }
            } else if (s > r || e < l) return 0;
            else {
                int mid = (s + e) >> 1;
                return query(node << 1, s, mid, l, r, val, op, lvl + 1) + query(node << 1 | 1, mid + 1, e, l, r, val, op, lvl + 1);
            }
        }

        void update(int pos, int num) {
            update(1, 0, n - 1, pos, num, 0);
        }

        void update(int node, int s, int e, int pos, int num, int lvl) {
            if (s == e && pos == s) {
                tree[node].update(num_pos[lvl][num] - s, 1);
            } else if (s <= pos && e >= pos) {
                int mid = (s + e) >> 1;
                update(node << 1, s, mid, pos, num, lvl + 1);
                update(node << 1 | 1, mid + 1, e, pos, num, lvl + 1);
                tree[node].update(num_pos[lvl][num] - s, 1);
            }
        }
    }

    static class FenwickTree {
        int FT[];
        int n;

        FenwickTree(int nn) {
            n = nn;
            FT = new int[n + 1];
        }

        void update(int idx, int val) {
            idx++;
            while (idx <= n) {
                FT[idx] += val;
                idx += idx & -idx;
            }
        }

        int rsq(int idx) {
            idx++;
            int res = 0;
            while (idx > 0) {
                res += FT[idx];
                idx -= idx & -idx;
            }
            return res;
        }
    }

    static class Merge {
        static final int INF = Integer.MAX_VALUE;
        int a[];

        Merge(int arr[]) {
            a = arr;
        }

        long solve() {
            return mergeSort(0, a.length - 1);
        }

        long mergeSort(int b, int e) {
            long res = 0;
            if (b < e) {
                int q = (b + e) / 2;
                res += mergeSort(b, q);
                res += mergeSort(q + 1, e);
                res += merge(b, q, e);
            }
            return res;
        }


        long merge(int b, int mid, int e) {
            long res = 0;
            int n1 = mid - b + 1;
            int n2 = e - mid;
            int[] L = new int[n1 + 1], R = new int[n2 + 1];

            for (int i = 0; i < n1; i++) L[i] = a[b + i];
            for (int i = 0; i < n2; i++) R[i] = a[mid + 1 + i];
            L[n1] = R[n2] = INF;

            for (int k = b, i = 0, j = 0; k <= e; k++)
                if (L[i] <= R[j])
                    a[k] = L[i++];
                else {
                    res += n1 - i;
                    a[k] = R[j++];
                }

            return res;
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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (Exception e) {
                return null;
            }
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

        public boolean ready() {
            try {
                return br.ready();
            } catch (Exception e) {
                return false;
            }
        }

    }
}
