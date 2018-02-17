/**
 * Created by omar on 20/07/17.
 */
public class Collision {
    public double probability(int[] assignments, int total) {
        double p1 = 1, p2 = 1;
        int assigned1 = 0, assigned2 = 0;
        long sum = 0;
        for (int cur : assignments) {
            if (cur > total) {p1 = p2 = 0;}
            sum += cur;
            for (int j = 0; j < cur; j++) {
                p1 *= 1.0 * (total - assigned1 - j) / (total - j);
                p2 *= 1.0 * (total - assigned2++) / total;
            }
            assigned1 += cur;
        }
        if (sum > total) {p1 = p2 = 0;}
        return p1 - p2;
    }
}
