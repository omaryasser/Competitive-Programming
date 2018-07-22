import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by omar on 28/07/17.
 */
public class MonomorphicTyper {
    HashMap<String,String> map = new HashMap<>();
    char[] exp;

    String solve (int s, int e) {
        boolean isConstant = true;
        for (int i = s; i <= e; i++) {
            isConstant &= Character.isAlphabetic(exp[i]);
        }
        if (isConstant) return constant (s, e);
        String functionName = "";
        int i;
        for (i = s;; i++)
            if (exp[i] == '(') break;
            else functionName += exp[i];
        String res = functionName + "(";
        String arguments = arguments (i + 1, e - 1);
        if (arguments == null) return null;
        res += arguments + ")";
        String was = map.get(res);
        return was;
    }

    String arguments (int s, int e) {
        int open = 0;
        int end = s;
        int start = s;
        String all = "";
        while (true) {
            while (true) {
                if (end == e + 1) break;
                else if (exp[end] == '(') open++;
                else if (exp[end] == ',' && open == 0) break;
                else if (exp[end] == ')') open--;
                end++;
            }
            String here = solve(start, end - 1);
            if (here == null) return null;

            all += here;
            if (end == e + 1) break;
            else {
                all += ",";
                start = end + 1;
                end = start;
            }
        }
        return all;
    }
    String constant (int s, int e) {
        return map.get(sub(s, e));
    }
    String sub (int s, int e) {
        String part = "";
        for (int i = s; i <= e; i++) part += exp[i];
        return part;
    }
    public String infer(String expression, String[] definitions) {
        for (String s : definitions) {
            String[] a = s.split(":");
            map.put(a[0], a[1]);
        }
        exp = expression.toCharArray();
        String ret = solve(0, expression.length() - 1);
        return ret == null ? "" : ret;
    }
}
