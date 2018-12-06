import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer> adjList[];
    static int[] cnt;
    static int n;

    static void dfs(int u, int p){
        cnt[u] = adjList[u].size() == 1 && u != 0 || u == 0 && n == 1 ? 1 : 0;

        for(int child : adjList[u])
            if(child != p){
                dfs(child, u);
                cnt[u] += cnt[child];
            }
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner();
        PrintWriter out = new PrintWriter(System.out);

        n = sc.nextInt();
        adjList = new ArrayList[n];
        cnt = new int[n];
        for(int i = 0; i < n; i++)adjList[i] = new ArrayList<>();

        for(int i = 1; i < n; i++){
            int p = sc.nextInt() - 1;
            adjList[p].add(i);
            adjList[i].add(p);
        }

        dfs(0, -1);
        shuffle(cnt);
        Arrays.sort(cnt);

        for(int i = 0; i < n; i++){
            if(i != 0)out.print(" ");
            out.print(cnt[i]);
        }

        out.flush();
        out.close();
    }




    static void shuffle(int[] a){
        for(int i = 0; i < a.length; i++){
            int idx = (int)(Math.random() * n);
            int tmp = a[idx];
            a[idx] = a[i];
            a[i] = tmp;
        }
    }








    static class Scanner {
        BufferedReader br;
        StringTokenizer st;

        public Scanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public Scanner(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}