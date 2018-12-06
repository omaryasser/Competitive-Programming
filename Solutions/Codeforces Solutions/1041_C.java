import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static class Pair implements Comparable<Pair> {
        int time, idx, days;
        Pair(int t, int i, int d){
            time = t;
            idx = i;
            days = d;
        }

        @Override
        public int compareTo(Pair o) {
            return time != o.time ? time - o.time : idx - o.idx;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);


        int n = sc.nextInt();
        long m = sc.nextLong(), d = sc.nextLong() + 1;
        int[] a = new int[n];
        Integer []b = new Integer[n];
        for(int i = 0; i < n; i++){
            a[i] = sc.nextInt();
            b[i] = i;
        }
        for(int i = 0; i < n - 1; i++){
            int random = (int)(Math.random() * (n - i - 1)) + 1;
            int tmp = b[i];
            b[i] = b[i + random];
            b[i + random] = tmp;
        }
        Arrays.sort(b, (x, y) -> a[x] - a[y]);
        for(int i = 0; i < n - 1; i++){
            int random = (int)(Math.random() * (n - i - 1)) + 1;
            int tmp = a[i];
            a[i] = a[i + random];
            a[i + random] = tmp;
        }
        Arrays.sort(a);

        PriorityQueue<Pair> available = new PriorityQueue<>();
        int idx = 0;
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            while(!available.isEmpty() && available.peek().days == m)available.poll();
            Pair first = available.isEmpty() ? null : available.peek();
            if(first == null || a[i] - first.time < d){
                available.add(new Pair(a[i], res[b[i]] = ++idx, 1));
            }else{
                available.poll();
                available.add(new Pair(a[i], res[b[i]] = first.idx, first.days + 1));
            }
        }
        out.println(idx);
        for(int i = 0; i < n; i++){
            if(i > 0)out.print(" ");
            out.print(res[i]);
        }
        out.println();

        out.flush();
    }
    static class Scanner
    {
        StringTokenizer st; BufferedReader br;
        public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
        public Scanner(String s) throws FileNotFoundException {	br = new BufferedReader(new FileReader(new File(s)));}
        public String next() throws IOException {while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());return st.nextToken();}
        public int nextInt() throws IOException {return Integer.parseInt(next());}
        public long nextLong() throws IOException {return Long.parseLong(next());}
        public String nextLine() throws IOException {return br.readLine();}
        public boolean ready() throws IOException {return br.ready();}
    }
}
