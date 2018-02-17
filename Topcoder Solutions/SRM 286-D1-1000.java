import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by omar on 03/08/17.
 */
public class InfiniteSoup {
    public int[] countRays(String[] g, String[] words, int k) {
        int rows = g.length, cols = g[0].length();
        HashMap<String,Integer> cnt = new HashMap<>();
        for (int i = 0; i <= k; i++)
            for (int j = i == 0 ? 1 : 0; j <= k; j++) {
                if (gcd (i, j) == 1) {
                    StringBuilder res = new StringBuilder();
                    for (int len = 0, x = 0, y = 0; len < 2 * 35 * 35; len++, x += i, y += j) {
                        res.append(g[y % rows].charAt(x % cols));
                    }
                    Integer was = cnt.get(res.toString());
                    if (was == null) was = 0;
                    cnt.put(res.toString(), was + 1);
                }
            }

        int m = words.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            P = words[i].toCharArray();
            for (Map.Entry<String,Integer> mp : cnt.entrySet()) {
                T = mp.getKey().toCharArray();
                kmpPreProcess();
                if (kmpSearch()) res[i] += mp.getValue();
            }
        }
        return res;
    }
    char T[], P[];
    int b[], n, m;

    void kmpPreProcess() {
        m = P.length;
        b = new int[m + 1];
        int i = 0, j = -1; b[0] = -1;
        while(i < m) {
            while(j >= 0 && P[i] != P[j]) j = b[j];
            i++; j++;
            b[i] = j;
        }
    }
    boolean kmpSearch() {
        int i = 0, j = 0;
        n = T.length;
        while(i < n) {
            while(j >= 0 && T[i] != P[j]) j = b[j];
            i++; j++;
            if(j == m) return true;

        }
        return false;
    }
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a%b);
    }
}
