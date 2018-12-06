import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long gcd (long a, long b){
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        long a = sc.nextLong(), b = sc.nextLong(), x = sc.nextLong(), y = sc.nextLong();
        long x_ = x / gcd(x, y);
        long y_ = y / gcd(x, y);

        if(x_ > a || y_ > b)out.println(0);
        else out.println(Math.min(a / x_, b / y_));
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
