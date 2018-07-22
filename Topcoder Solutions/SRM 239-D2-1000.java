/**
 * Created by omar on 25/07/17.
 */
public class DivisibilityCriteria {
    public int[] multipliers(int N, int P) {
        int[] res = new int[N];
        long acc = 1;
        for (int i = N - 1; i >= 0; i--) {
            acc %= P;
            res[i] = (int)(acc % P);
            acc *= 10;
        }
        return res;
    }
}
