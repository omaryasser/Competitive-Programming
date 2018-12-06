import java.io.*;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner();
    static PrintWriter out = new PrintWriter(System.out);

    static int A = 1, B = 3;
    static int print(long[] num) {
        out.println("? " + num[0] + " " + num[1]);
        out.flush();

//        if((num[0] ^ A) > (num[1] ^ B))return 1;
//        if((num[0] ^ A) < (num[1] ^ B))return -1;
//        return 0;
        return sc.nextInt();
    }

    static void set(long[] num, int i1, int i2) {
        num[i1] |= (1 << i2);
    }

    static void clr(long[] num, int i1, int i2) {
        long x = num[i1];
        if ((x & (1 << i2)) == 0) return;
        x -= (1 << i2);
        num[i1] = x;
    }

    public static void main(String[] args) {

        int[][] bits = new int[2][30];
        long num[] = new long[2];
        boolean firstIsBigger = print(num) == 1;
        for (int i = 29; i >= 0; i--) {
            int change = firstIsBigger == true ? 0 : 1;
            int one = 1 ^ change, zero = 0 ^ change;
            int responseNeg = change == 0 ? -1 : 1;
            int responsePos = change == 0 ? 1 : -1;
            set(num, zero, i);
            set(num, one, i);
            int response = print(num);
//            System.err.println(firstIsBigger + " " + i);
            if (response == responseNeg) {
                bits[zero][i] = 1;
                bits[one][i] = 0;
                // need to know next who is bigger
                set(num, zero, i);
                clr(num, one, i);

//                if(i == 3) System.err.println(num[0] + " " + num[1] + " " + responsePos + " " + print(num));
                firstIsBigger = print(num) == 1;
            } else {
                clr(num, zero, i);
                set(num, one, i);

                int response2 = print(num);
                if (response2 == responsePos) {
                    bits[zero][i] = 1;
                    bits[one][i] = 1;
                    set(num, zero, i);
                    set(num, one, i);
                } else {
                    bits[zero][i] = 0;
                    bits[one][i] = 0;
                    clr(num, zero, i);
                    clr(num, one, i);
                }
            }
        }

        long real[] = new long[2];
        for (int i = 0; i < 30; i++)
            for(int j = 0; j < 2; j++)
                if(bits[j][i] == 1)real[j] |= 1 << i;
        out.println("! " + real[0] + " " + real[1]);
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