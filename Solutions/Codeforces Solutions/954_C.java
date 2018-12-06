import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int cols = -1;
        for (int i = 0; i < n - 1; i++) {
            int here = Math.abs(a[i] - a[i + 1]);
            if (here == 0) {
                System.out.println("NO");
                return;
            }
            if (here != 1) {
                if (cols == -1) cols = here;
                else if (cols != here) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        if (cols == -1) {
            System.out.printf("YES\n");
            System.out.printf("%d %d\n", (int) 1e9, cols == -1 ? 1 : cols);
        } else {
            for (int i = 0; i < n; i++) {
                if (a[i] % cols == 0) {
                    if (i > 0 && a[i - 1] == a[i] + 1 || i < n - 1 && a[i + 1] == a[i] + 1) {
                        System.out.printf("NO");
                        return;
                    }
                }
                if (a[i] % cols == 1) {
                    if (i > 0 && a[i - 1] == a[i] - 1 || i < n - 1 && a[i + 1] == a[i] - 1) {
                        System.out.printf("NO");
                        return;
                    }
                }
            }
            System.out.printf("YES\n");
            System.out.printf("%d %d\n", (int) 1e9,  cols);
        }
        out.flush();
        out.close();
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(String file) {
            try {
                br = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
