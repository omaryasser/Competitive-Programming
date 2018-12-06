import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner();
    static PrintWriter out = new PrintWriter(System.out);




    public static void main(String[] args) {

        int n = sc.nextInt(), k = sc.nextInt();
        Integer[] arr = new Integer[n];
        for(int i = 0; i < n; i++)arr[i] = sc.nextInt();
        Arrays.sort(arr);
        int[] a = new int[n];
        for(int i = 0; i < n; i++)a[i] = arr[i];
        int sub = 0;
        int done = 0;
        for(int i = 0; i < n; i++){
            if(a[i] - sub == 0)continue;
            out.println(a[i] - sub);
            sub += a[i] - sub;
            done++;
            if(done == k)break;
        }
        k -= done;
        while(k-- > 0)
            out.println(0);
        out.flush();
        out.close();
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