    static int[][] packU(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    static int[][] packD(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from) if(f != -1) p[f]++;
        for (int i = 0; i < n; i++) g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) if(from[i] != -1) {g[from[i]][--p[from[i]]] = to[i];}
        return g;
    }
