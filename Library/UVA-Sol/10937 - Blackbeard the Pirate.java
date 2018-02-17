import java.io.*;
import java.util.*;


public class E {
    static int h, w, x_start, y_start, cnt_treasures;
    static char map[][];
    static int dx[] = {0, 0, 1, -1, 1, -1, 1, -1};
    static int dy[] = {1, -1, 0, 0, 1, -1, -1, 1};
    static boolean clear [][] ;
    static ArrayList<Pair> adjList[];
    static TreeMap<Point, Integer> map_idx;

    static boolean valid(int x, int y) {
        return x >= 0 && y >= 0 && x < h && y < w;
    }

    static int get_idx(int x, int y) {
        return map_idx.get(new Point(x, y));
    }

    static void change_map() {
        int cnt = 1;
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                char c = map[i][j];
                switch (c) {
                    case '~':
                        map[i][j] = '#';
                        break;
                    case '*':
                        map[i][j]  ='#';
                        for (int k = 0; k < 8; ++k)
                            if (valid(i + dx[k], j + dy[k]))
                            {
                                clear[i + dx[k]] [j + dy[k]] = true ;
                            }
                        break;
                    case '@':
                        x_start = i;
                        y_start = j;
                        map_idx.put(new Point(i, j), 0);
                        break;
                    case '!':
                        cnt_treasures++;
                        map_idx.put(new Point(i, j), cnt++);
                        break;
                }
            }
        }
    }

    static void bfs(int s_x, int s_y) {
        Queue<Triple> q = new LinkedList<>();
        int dist[][] = new int[h][w];
        int INF = (int) 300000000;
        for (int i = 0; i < h; ++i)
            Arrays.fill(dist[i], INF);
        dist[s_x][s_y] = 0;
        q.add(new Triple(s_x, s_y, 0));
        while (!q.isEmpty()) {
            Triple cur = q.poll();
            int x = cur.v_x, y = cur.v_y, w = cur.w;
            for (int i = 0; i < 4; ++i) {
                if (valid(x + dx[i], y + dy[i]) && map[x + dx[i]][y + dy[i]] != '#') {
                    if (dist[x + dx[i]][y + dy[i]] > w + 1) {
                        q.add(new Triple(x + dx[i], y + dy[i], w + 1));
                        dist[x + dx[i]][y + dy[i]] = w + 1;
                    }
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < h; ++i)
            for (int j = 0; j < w; ++j)
                if (map[i][j] == '!' || map[i][j] == '@') {
                    adjList[map_idx.get(new Point(s_x, s_y))].add(new Pair(map_idx.get(new Point(i, j)), dist[i][j]));
                }

    }

    static long memo[][];

    static long tsp(int idx, int mask) {
        if (mask == (1 << (cnt_treasures + 1)) - 1) {
            for (Pair pair : adjList[idx])
                if (pair.v == 0)
                    return pair.w;
            return 300000000;
        }
        if (memo[idx][mask] != -1) return memo[idx][mask];
        long ans = 300000000;
        for (int i = 0; i < adjList[idx].size(); ++i) {
            int v = adjList[idx].get(i).v;
            int w = adjList[idx].get(i).w;
            if (v != idx && (mask & (1 << v)) == 0)
                ans = Math.min(ans, w + tsp(v, mask | (1 << v)));
        }
        return memo[idx][mask] = ans;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true) {
            h = sc.nextInt();
            w = sc.nextInt();
            if (h == 0 && w == 0) break;
            map = new char[h][w];
            clear = new boolean[h][w];
            for (int i = 0; i < h; ++i) map[i] = sc.next().toCharArray();
            map_idx = new TreeMap<Point, Integer>();
            cnt_treasures = 0;
            change_map();
            for (int i = 0 ; i < h ; ++i)
                for (int j = 0 ; j < w ; ++j)
                    if (clear[i][j])
                        map[i][j] = '#';
            adjList = new ArrayList[cnt_treasures + 1];
            for (int i = 0; i < cnt_treasures + 1; ++i) adjList[i] = new ArrayList<>();
            bfs(x_start, y_start);
            for (int i = 0; i < h; ++i) {
                for (int j = 0; j < w; ++j) {
                    if (map[i][j] == '!') {
                        bfs(i, j);
                    }
                }
            }
//            for (int i = 0 ; i < adjList.length ; ++i)
//                for (Pair p : adjList[i])
//                    System.out.println(i + " " + p.v + " " + p.w);
            memo = new long[cnt_treasures + 1][1 << (cnt_treasures + 1)];
            for (int i = 0; i <= cnt_treasures; ++i)
                Arrays.fill(memo[i], -1);

            long res = tsp(0, 1);
            out.printf("%d\n",  res >= 300000000 ? -1 : res);
        }
        out.flush();
        out.close();
    }

    static class UnionFind {
        private int [] p , rank , setSize;
        private int numSets ;
        public UnionFind(int size)
        {
            p = new int[size] ;
            rank = new int[size] ;
            setSize = new int[size];
            numSets = size ;
            for(int i = 0 ; i < size ; ++i) {
                p[i] = i;
                setSize[i] = 1;
            }
        }

        int findSet(int i ) {return (p[i] == i)? i : (p[i] = findSet(p[i]));}
        boolean isSameSet(int i , int j) {return findSet(i) == findSet(j);}
        void unionSet(int i , int j)
        {
            if(!isSameSet(i,j))
            {
                --numSets;
                int x = findSet(i) , y = findSet(j);
                if(rank[x] > rank[y]) {p[y] = x; setSize[x] = setSize[x] + setSize[y];}
                else
                {
                    p[x] = y;
                    setSize[y] = setSize[y] + setSize[x];
                    if(rank[x] == rank[y]) ++rank[y];
                }
            }
        }

        int numDisjointSets ()
        {
            return numSets;
        }

        int sizeOfSet(int i) {return setSize[findSet(i)];}
    }
    static class Triple {
        int v_x, v_y, w;

        Triple(int vx, int vy, int ww) {
            v_x = vx;
            v_y = vy;
            w = ww;
        }
    }

    static class Point implements Comparable<Point> {
        int x, y;

        Point(int xx, int yy) {
            x = xx;
            y = yy;
        }

        @Override
        public int compareTo(Point point) {
            return (x == point.x && y == point.y) ? 0 : (x != point.x) ? x - point.x : y - point.y;
        }

        @Override
        public boolean equals(Object o) {
            int x2 = ((Point) (o)).x;
            int y2 = ((Point) (o)).y;
            return x == x2 && y == y2;
        }
    }


    static class Pair {
        int v, w;

        Pair(int vv, int ww) {
            v = vv;
            w = ww;
        }
    }


    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

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
