// Moustafa Abdullah's implementation
// (n + m) log n

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class DominatorTree {
int N, dfs_num;
int[] tin, tinRev, sdom, dom, parent, label, dsu;
ArrayList<Integer>[] g, gRev, domTree, bucket;

public DominatorTree(int n) {
        N = n;
        tin = new int[N];
        tinRev = new int[N];
        sdom = new int[N];
        dom = new int[N];
        parent = new int[N];
        label = new int[N];
        dsu = new int[N];

        Arrays.fill(tin, -1);

        g = new ArrayList[N];
        gRev = new ArrayList[N];
        domTree = new ArrayList[N];
        bucket = new ArrayList[N];

        for(int i = 0; i < N; i++) {
                g[i] = new ArrayList<Integer>();
                gRev[i] = new ArrayList<Integer>();
                domTree[i] = new ArrayList<Integer>();
                bucket[i] = new ArrayList<Integer>();
        }
}


public void addDirectedEdge(int u, int v) {
        g[u].add(v);
}

public void generateTree(int source) {
        calculateDoms(source);

        for(int i = 1; i < N; i++) {
                if(dom[i] != sdom[i])
                        dom[i] = dom[dom[i]];

                domTree[tinRev[i]].add(tinRev[dom[i]]);
                domTree[tinRev[dom[i]]].add(tinRev[i]);
        }
}

public void printTree(PrintWriter pr) {
        for(int i = 0; i < N; i++)
                for(int v : domTree[i])
                        pr.println(i + " " + v);
}

private void preCalc(int u) {
        tin[u] = label[dfs_num] = sdom[dfs_num] = dsu[dfs_num] = dfs_num;
        tinRev[dfs_num++] = u;

        for(int v : g[u]) {
                if(tin[v] == -1) {
                        preCalc(v);
                        parent[tin[v]] = tin[u];
                }
                gRev[tin[v]].add(tin[u]);
        }
}

private void calculateDoms(int source) {
        preCalc(source);
        for(int i = N - 1; i >= 0; i--) {
                for(int v : gRev[i])
                        sdom[i] = Math.min(sdom[i], sdom[find(v, false)]);

                if(i > 0)
                        bucket[sdom[i]].add(i);

                for(int b : bucket[i]) {
                        int v = find(b, false);
                        if(sdom[v] == sdom[b])
                                dom[b] = sdom[b];
                        else
                                dom[b] = v;
                }

                if(i > 0)
                        union(parent[i], i);
        }
}

private int find(int u, boolean err) {
        if(u == dsu[u]) return err ? -1 : u;
        int v = find(dsu[u], true);
        if(v < 0) return u;
        if(sdom[label[dsu[u]]] < sdom[label[u]])
                label[u] = label[dsu[u]];
        dsu[u] = v;
        return err ? v : label[u];
}

private void union(int u, int v) {
        dsu[v] = u;
}
}
