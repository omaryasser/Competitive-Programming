import java.util.Arrays;

/**
 * Created by omar on 20/07/17.
 */
public class PipeCuts {
    public double probability(int[] weldLocations, int L) {
        Arrays.sort(weldLocations);
        int cnt = 0;
        int n = weldLocations.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int max = Math.max(L, Math.max(weldLocations[i], Math.max(weldLocations[j] - weldLocations[i], 100 - weldLocations[j])));
                if (max != L) cnt++;
            }
        }
        return 1.0 * cnt / (1.0 * n * (n - 1) / 2);
    }
}
