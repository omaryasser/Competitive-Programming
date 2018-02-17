import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by omar on 29/07/17.
 */
public class IncompleteBST {
    HashMap<Long, Character> map = new HashMap<>();
    char s = '&', e = '&';
    int max;
    public String missingValues(String[] tree) {
        int nodeNum = -1;
        for (String s : tree) {
            String[] ss = s.split(" ");
            map.put(Long.parseLong(ss[1]), ss[0].charAt(0));
            if (ss[0].charAt(0) == '?') nodeNum = Integer.parseInt(ss[1]);
            max = Math.max(max, Integer.parseInt(ss[1]));
        }
        solve (1, 0, 27);
        if (s == '&' || bad) return "";
        if (map.containsKey(new Long(nodeNum << 1L))) fill_underL(nodeNum << 1L);
        if (map.containsKey(new Long(nodeNum << 1L | 1L))) fill_underR(nodeNum << 1L | 1L);
        System.err.println(biggerThanOrEqual + " " + lessThan);
        String res = "";
        for (char i = s; i <= e; i++)
            if (i >= biggerThanOrEqual && i < lessThan)
                res += i;
        return res;
    }

    boolean bad;
    char biggerThanOrEqual = 'A', lessThan = 'Z' + 1;
    void fill_underL (long node) {
        biggerThanOrEqual = (char)Math.max(biggerThanOrEqual, map.get(node));
        if (map.containsKey(node << 1L)) fill_underL(node << 1L);
        if (map.containsKey(node << 1L | 1L)) fill_underL(node << 1L | 1L);
    }
    void fill_underR (long node) {
        lessThan = (char)Math.min(lessThan, map.get(node));
        if (map.containsKey(node << 1L)) fill_underR(node << 1L);
        if (map.containsKey(node << 1L | 1L)) fill_underR(node << 1L | 1L);
    }
    void solve (long node, int lo, int hi) {
        if (map.containsKey(node) && map.get(node) != '?' && (map.get(node) + 1 - 'A' <= lo || map.get(node) + 1 - 'A' > hi)) {
            bad = true;
            return;
        }
        if (map.containsKey(node) && map.get(node) == '?') {
            if (lo < hi) {
                s = (char) (lo + 1 -1 + 'A');
                e = (char) (hi - 1 + 'A');
            }
        }
        if ((node << 1L) <= max) solve(node << 1L, lo, map.containsKey(node) && map.get(node) != '?' ? (map.get(node) - 'A') + 1 : hi);
        if ((node << 1L | 1L) <= max) solve(node << 1L | 1L, map.containsKey(node) && map.get(node) != '?' ? (map.get(node) - 'A') + 1 : lo, hi);
    }
}
