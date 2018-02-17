
// APSP
p = new int[V][V];
for(int i = 0; i < V; i++)
        for(int j = 0; j < V; j++)
                p[i][j] = i;

for(int k = 0; k < V; ++k)
        for(int i = 0; i < V; ++i)
                for(int j = 0; j < V; ++j)
                        if (adjMat[i][k] + adjMat[k][j] < adjMat[i][j]) {
                                adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
                                p[i][j] = p[k][j];
                        }
// if adjList[i][i] < 0, there is a negative cycle

static boolean effectedByNegativeCycle (int src, int dest) {
        for (int i = 0; i < V; i++) {
                if (adjList[i][i] < 0 && adjList[src][i] < INF && adjList[i][dest] < INF) return true;
        }
        return false;
}

static void print(int i, int j)
{
        if(i != j) print(i, p[i][j]);
        out.add(j);
}


// Minimax
for (int k = 0; k <  V; ++k)
        for (int i = 0; i < V; ++i)
                for (int j = 0; j < V; ++j)
                        adjMat[i][j] = Math.min(adjMat[i][j], Math.max (adjMat[i][k], adjMat[k][j]));

// Maxmin
for (int k = 0; k <  V; ++k)
        for (int i = 0; i < V; ++i)
                for (int j = 0; j < V; ++j)
                        adjMat[i][j] = Math.max(adjMat[i][j], Math.min (adjMat[i][k], adjMat[k][j]));

// isConnected
for (int k = 0; k <  V; ++k)
        for (int i = 0; i < V; ++i)
                for (int j = 0; j < V; ++j)
                        adjMat[i][j] |= (adjMat[i][k] & adjMat[k][j]);

// CountPaths
for (int k = 0; k <  V; ++k)
        for (int i = 0; i < V; ++i)
                for (int j = 0; j < V; ++j)
                        adjMat[i][j] += (adjMat[i][k] * adjMat[k][j]);
// if adjList[i][i] > 0, then There is a cycle in the Graph
