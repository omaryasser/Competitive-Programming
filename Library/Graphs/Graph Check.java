static int EXPLORED = 2, UNVISITED = 0, VISITED = 1;
static int dfs_num [], dfs_parent [];
static int V;
static int numComp;
static ArrayList<Integer> adjList[];
static void graphCheck(int u)
{
        dfs_num[u] = EXPLORED;
        for(int j = 0; j < adjList[u].size(); ++j)
        {
                int v = adjList[u].get(j);
                if(dfs_num[v] == UNVISITED)
                {
                        dfs_parent[v] = u;
                        graphCheck(v);
                }
                else if (dfs_num[u] == EXPLORED)
                {
                        if(v == dfs_parent[u])
                                System.out.printf("Two ways (%d -> %d) - (%d -> %d) \n", u, v, v, u);
                        else System.out.printf("Back Edge (%d - > %d) (Cycle) \n", u, v);
                }
        }
        dfs_num[u] = VISITED;
}

public static void main (String [] args)
{
        Scanner sc = new Scanner(System.in);
        numComp = 0;
        V = sc.nextInt();
        dfs_num = new int[V];
        dfs_parent = new int[V];
        Arrays.fill(dfs_num, UNVISITED);
        Arrays.fill(dfs_parent, 0);
        for(int i = 0; i < V; ++i)
                if(dfs_num[i] == UNVISITED)
                {
                        System.out.printf("Component %d \n", ++numComp);
                        graphCheck(i);
                }
}
