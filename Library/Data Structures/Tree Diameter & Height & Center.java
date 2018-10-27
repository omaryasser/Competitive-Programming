
// NOTE : This code doesn't work for graphs but it gives a good approximation.


// NOTE : The Tree Center is the Node in the middle of the diameter path.

static int MAX = 10001;
    static ArrayList<Edge> adjList [];
    static Edge dfs (int node, int parent) {
        int heights [] = {-1, -1, -1};
        int bestDiam = 0;
        for (Edge e : adjList[node]) {
            int child = e.to;
            int weight = e.w;
            if (child != parent) {
                Edge nxt = dfs(child, node);
                bestDiam = Math.max(bestDiam, nxt.to);
                heights[0] = weight + nxt.w;
                Arrays.sort(heights);
            }
        }
        for (int i = 0; i < 3; i++) heights[i] = heights[i] == -1 ? 0 : heights[i];
        return new Edge(Math.max(bestDiam, heights[1] + heights[2]), heights[2]);
    }
	 static class Edge {
	        int to, w;
	        Edge (int t, int ww) {to = t; w = ww;}
	    }
