import java.util.Arrays;

/**
 * Created by omar on 4/15/17.
 */
public class Arrfix {
    public int mindiff(int[] A, int[] B, int[] F) {
        int n = A.length, m = F.length;
        int cnt[] = new int[1001];
        for (int i = 0; i < m; i++)
            cnt[F[i]]++;

        int res = 0, didnt = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] == B[i]) continue;
            if (cnt[B[i]] != 0) cnt[B[i]]--;
            else didnt++;
        }

        for (int i = 0; i < n; i++)
            if (A[i] == B[i] && cnt[B[i]] != 0)
                cnt[B[i]]--;

        for (int i = 0; i < 1001; i++)
            res += cnt[i];

        return Math.min(Math.max(didnt, res), n);
    }

    public static void main (String [] args) {
        Arrfix a = new Arrfix();
        System.out.println(a.mindiff(new int []{2,2,2}
        , new int [] {2,2,2},
        new int []{1, 2, 3}));
    }
}
