import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by omar on 5/19/18.
 */
public class A {

    static class Pair{
        int u,d;
        Pair(int u,int d){
            this.u=u;
            this.d=d;
        }
    }
    static ArrayList<Integer>[] adjList;
    static ArrayList<Integer>[] adjList3;
    static int V, counter, SCC, dfs_num[], dfs_low[];
    static int[] inSCC;
    static Stack<Integer> stack;


    static void tarjanSCC()
    {
        dfs_num=new int[V];
        dfs_low=new int[V];
        inSCC = new int[V];
        stack= new Stack<>();
        for(int i = 0; i < V; ++i)
            if(dfs_num[i] == 0)
                tarjanSCC(i);
        for(int i=0;i<V;i++)
            for(int v:adjList[i]){
                if(inSCC[i]==inSCC[v])continue;
                adjList3[inSCC[i]-1].add(inSCC[v]-1);
                adjList3[inSCC[v]-1].add(inSCC[i]-1);
            }
    }

    static void tarjanSCC(int u)
    {
        dfs_num[u] = dfs_low[u] = ++counter;
        stack.push(u);

        for(int v : adjList[u])
        {
            if(dfs_num[v] == 0)
                tarjanSCC(v);
            if(inSCC[v]==0)
                dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
        }
        if(dfs_num[u] == dfs_low[u])
        {
            //SCC found
            while(true)
            {
                int v = stack.pop();
                inSCC[v] = SCC+1;
                if(v == u)
                    break;
            }
            SCC++;
        }
    }

    static ArrayList<Integer>tree[];
    static boolean vis[];
    static void dfs(int u,int p){
        vis[u]=true;
        for(int v:tree[u]){
            if(v==p)continue;
            adjList[u].add(v);
            if(!vis[v])
                dfs(v,u);
        }
    }
    static Pair dfs2(int u,int p){
        Pair ret=new Pair(u,0);
        for(int v:adjList3[u])
            if(v!=p){
                Pair go=dfs2(v,u);
                if(go.d+1>ret.d)
                    ret=new Pair(go.u,go.d+1);
            }
        return ret;
    };
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        V=sc.nextInt();
        tree = new ArrayList[V];
        adjList = new ArrayList[V];
        adjList3 = new ArrayList[V];
        vis = new boolean[V];
        for(int i=0;i<V;i++)tree[i]=new ArrayList<>();
        for(int i=0;i<V;i++)adjList[i]=new ArrayList<>();
        for(int i=0;i<V;i++)adjList3[i]=new ArrayList<>();
        int m=sc.nextInt();
        while(m-->0){
            int u=sc.nextInt()-1,v=sc.nextInt()-1;
            tree[u].add(v);
            tree[v].add(u);
        }
        dfs(0,-1);

        tarjanSCC();
        int far1=dfs2(0,-1).u;
        System.out.println(dfs2(far1,-1).d);
        out.flush();
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader bf;

        Scanner(InputStream in) {
            bf = new BufferedReader(new InputStreamReader(in));
        }

        String next() throws Exception {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(bf.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws Exception {
            return Integer.parseInt(next());
        }

        long nextLong() throws Exception {
            return Long.parseLong(next());
        }
    }
}
