// O(maxFlow * E)
static int V;
static int [][] res;
static ArrayList<Integer> adjList [];
static int S, D;
static boolean visited [];
static int curFlow;
static int INF = (int)1e9;
static int nxtNode [];
static boolean aug (int node, int bottle) {
        visited[node] = true;
        if (node == D) {curFlow = bottle; return true; }
        for (int nxt : adjList[node]) {
                if (!visited[nxt] && res[node][nxt] != 0) {
                        if (aug(nxt, Math.min(bottle, res[node][nxt]))) {
                                nxtNode[node] = nxt;
                                return true;
                        }
                }
        }
        return false;
}
static int ford_fulkerson () {
        int maxFlow = 0;
        while (true) {
                visited = new boolean[V];
                nxtNode = new int[V];
                curFlow = 0;
                aug(S, INF);
                if (curFlow == 0) { break; }
                int curNode = S;
                while (curNode != D) {
                        res[curNode][nxtNode[curNode]] -= curFlow;
                        res[nxtNode[curNode]][curNode] += curFlow;
                        curNode = nxtNode[curNode];
                }
                maxFlow += curFlow;
        }
        return maxFlow;
}
