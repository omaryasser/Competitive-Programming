import java.util.ArrayList;
import java.util.Arrays;

public class SubtreesCounting {
    ArrayList<Integer> tree [];
    int n;
    int mod = (int)1e9 + 7;
    int f [][]; // f[i][j] = # of subtrees rooted at i considering first j children
    int g [][]; // f[i][j] = sum of subtrees sizes rooted at i considering first j children
    void solve (int node, int p) {
        f[node] = new int[(p == - 1 ? tree[node].size() : tree[node].size() - 1) + 1];
        g[node] = new int[(p == - 1 ? tree[node].size() : tree[node].size() - 1) + 1];
        f[node][0] = 1;
        g[node][0] = 1;
        
        int idx = 0;
        for (int i = 0; i < tree[node].size(); i++) {
            int child = tree[node].get(i);
            if (child != p) {
                solve(child, node);
                f[node][idx + 1] = (int)(((long)f[node][idx] * (f[child][f[child].length - 1] + 1)) % mod);
                int a = f[node][idx];
                int b = f[child][f[child].length - 1];
                int c = g[node][idx];
                int d = g[child][g[child].length - 1];
                g[node][idx + 1] = (g[node][idx] + (int)(((long)c * b + (long)d * a) % mod)) % mod;
                idx++;
            }
        }
    }
    public int sumOfSizes(int nn, int a0, int b, int c, int m) {
        n = nn;
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++)
            tree[i] = new ArrayList<>();
        int a [] = new int[n];
        a[0] = a0;
        for (int i = 1; i <= n - 2; i++)
            a[i] = (int)(((long)b * a[i-1] + c) % m);

        for(int i = 1; i <= n - 1; i++) {
            int j = a[i - 1] % i;
            tree[j].add(i);
            tree[i].add(j);
        }

        f = new int[n][];
        g = new int[n][];
        solve(0, -1);
        int res = 0;
        for(int i = 0; i < n; i++) {
            res += g[i][g[i].length - 1];
            if (res >= mod) res -= mod;
        }
        return res;
    }

    public static void main (String [] args) {
        System.out.println(new SubtreesCounting().sumOfSizes(3,
                1,
                1,
                1,
                1));
    }
}
