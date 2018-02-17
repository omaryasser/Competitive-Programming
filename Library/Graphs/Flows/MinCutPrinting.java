boolean [] visited = new boolean[V];
Queue<Integer> reachable = new LinkedList<>();
reachable.add(0);

while (!reachable.isEmpty()) {
        int cur = reachable.poll();
        visited[cur] = true;
        for (int nxt : adjList[cur])
                if (!visited[nxt] && res[cur][nxt] != 0)
                        reachable.add(nxt);
}

for (int i = 0; i < V; i++)
        for (int nxt : adjList[i]) {
                if (visited[i] && !visited[nxt])
                        out.println((i + 1) + " " + (nxt + 1));
        }
out.println();
