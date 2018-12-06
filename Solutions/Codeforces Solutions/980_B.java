import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by omar on 07/04/18.
 */
public class B {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt(), k = sc.nextInt();
        out.println("YES");
        char[][] a = new char[4][n];
        for (char[] aa:a) Arrays.fill(aa,'.');
        for (int c = 0; c < (n - 2) / 2; c++) {
            for (int row = 1; row < 3; row++) {
                if(k>1){
                    a[row][c+1]='#';
                    a[row][n-2-c]='#';
                    k-=2;
                }
            }
        }
        int row=1;
        while(k>0){
            k--;
            a[row++][n/2]='#';
        }

        for (char[] aa : a)
            out.println(new String(aa));

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
