
import java.io.*;
import java.util.*;

public class Main {
static HashMap<String,String> parent = new HashMap<>();
static String find (String s) {
        if (!parent.containsKey(s)) return s;
        parent.put(s, find(parent.get(s)));
        return parent.get(s);
}

static String removeOmElSpaces (StringTokenizer s) {
        StringTokenizer st = s;
        StringBuilder res = new StringBuilder();
        while (st.hasMoreTokens()) {
                String nxt = st.nextToken();
                boolean ok = false;
                for (int i = 0; i < nxt.length(); i++)
                        ok |= Character.isDigit(nxt.charAt(i)) || Character.isLetter(nxt.charAt(i));
                if (ok) res.append(nxt.toLowerCase());
        }
        return res.toString();
}
static char[] expression;
public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader("plcool.in"));
        PrintWriter out = new PrintWriter("plcool.out");
//      Scanner sc = new Scanner(System.in);
//      PrintWriter out = new PrintWriter(System.out);

//        int tt = 0;
        while (sc.ready()) {
//        while (tt++ < 1) {
                StringTokenizer st = new StringTokenizer(sc.nextLine());
                String op = st.nextToken();
                if (op.charAt(0) == 'p') {
                        expression = removeOmElSpaces(st).toCharArray();
                        out.println(solve(0, expression.length - 1));
                }
                else {
                        String child = st.nextToken(), parentt = st.nextToken();
                        if (!(parent.containsKey(child) || (find(parentt).equals(child))))
                                parent.put(child, parentt);
                }
        }
        out.close();
}

static boolean operand (int idx) {
        if (idx < 0) return false;
        return expression[idx] == '+' || expression[idx] == '-' || expression[idx] == '/' || expression[idx] == '*' || expression[idx] == '^' || expression[idx] == '%';
}
static long solve (int l, int r) {

        if (l > r) return 0;
        // PLUS & MINUS
        int opened = 0;
        for (int i = r; i >= l; i--) {
                if (expression[i] == '(') opened++;
                else if (expression[i] == ')') opened--;
                else if (opened == 0) {
                        // inner bracket
                        if (!operand(i - 1) && expression[i] == '+')
                                return solve(l, i - 1) + solve(i + 1, r);
                        if (!operand(i - 1) && expression[i] == '-')
                                return solve(l, i - 1) - solve(i + 1, r);
                }
        }

        // MODULUS, DIVISION & MULTIPLICATION
        opened = 0;
        for (int i = r; i >= l; i--) {
                if (expression[i] == '(') opened++;
                else if (expression[i] == ')') opened--;
                else if (opened == 0) {
                        // inner bracket
                        if (expression[i] == '*')
                                return solve(l, i - 1) * solve(i + 1, r);
                        if (expression[i] == '/') {
                                return solve(l, i - 1) / solve(i + 1, r);
                        }
                        if (expression[i] == '%') {
                                long first = solve(l, i - 1), second = solve(i + 1, r);
                                if (first < 0 && second >= 0 || first >= 0 && second < 0)
                                        return -(Math.abs(first) % Math.abs(second));
                                return (Math.abs(first) % Math.abs(second));
                        }
                }
        }

        // POWER
        opened = 0;
        for (int i = l; i <= r; i++) {
                if (expression[i] == '(') opened++;
                else if (expression[i] == ')') opened--;
                else if (opened == 0) {
                        // inner bracket
                        if (expression[i] == '^') {
                                return pow(solve(l, i - 1), solve(i + 1, r));
                        }
                }
        }
        if (expression[l] == '(') return solve(l + 1, r - 1);
        if (expression[l] == '-') return -solve(l + 1, r);
        if (expression[l] == '+') return solve(l + 1, r);
        StringBuilder num = new StringBuilder();
        for (int i = l; i <= r; i++)
                num.append(expression[i]);
        num = new StringBuilder(find(num.toString()));
        if (Character.isDigit(num.charAt(0))) return Long.parseLong(num.toString());
        else {
                return 0;
        }
}






static long pow(long a, long e) {
        long res = 1;
        while(e > 0) {
                if((e & 1L) == 1)
                        res *= a;
                a *= a;
                e >>= 1L;
        }
        return res;
}


































static class Scanner {


StringTokenizer st;
BufferedReader br;

public Scanner (InputStream s) {
        br = new BufferedReader(new InputStreamReader(s));
}

public Scanner(FileReader f) {
        br = new BufferedReader(f);
}

public String next() {
        while (st == null || !st.hasMoreTokens())
                try {
                        st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
        return st.nextToken();
}

public int nextInt() {
        return Integer.parseInt(next());
}

public long nextLong() {
        return Long.parseLong(next());
}

public String nextLine() {
        try {
                return br.readLine();
        } catch (Exception e) {
                return null;
        }
}

public double nextDouble() {
        String x = next();
        StringBuilder sb = new StringBuilder("0");
        double res = 0, f = 1;
        boolean dec = false, neg = false;
        int start = 0;
        if (x.charAt(0) == '-') {
                neg = true;
                start++;
        }
        for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                        res = Long.parseLong(sb.toString());
                        sb = new StringBuilder("0");
                        dec = true;
                } else {
                        sb.append(x.charAt(i));
                        if (dec)
                                f *= 10;
                }
        res += Long.parseLong(sb.toString()) / f;
        return res * (neg ? -1 : 1);
}

public boolean ready() {
        try {
                return br.ready();
        } catch (Exception e) {
                return false;
        }
}
}

}
