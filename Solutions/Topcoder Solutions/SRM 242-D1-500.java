/**
 * Created by omar on 20/07/17.
 */
public class DiceThrows {
    
    public double winProbability(int numDiceA, int[] sidesA, int numDiceB, int[] sidesB) {
        double[] dpA = new double[200 * 100 + 1];
        dpA[0] = 1;
        for (int i = 0; i < numDiceA; i++) {
            double[] dpAA = new double[200 * 100 + 1];
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 200 * 100 + 1; k++) {
                    if (k + sidesA[j] <= 200 * 100)
                        dpAA[k + sidesA[j]] += 1.0 / 6 * dpA[k];
                }
            }
            for (int j = 0; j < 200 * 100 + 1; j++) {
                dpA[j] = dpAA[j];
                dpAA[j] = 0;
            }
        }

        double[] dpB = new double[200 * 100 + 1];
        dpB[0] = 1;
        for (int i = 0; i < numDiceB; i++) {
            double[] dpBB = new double[200 * 100 + 1];
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 200 * 100 + 1; k++) {
                    if (k + sidesB[j] <= 200 * 100)
                        dpBB[k + sidesB[j]] += 1.0 / 6 * dpB[k];
                }
            }
            for (int j = 0; j < 200 * 100 + 1; j++) {
                dpB[j] = dpBB[j];
                dpBB[j] = 0;
            }
        }

        double res = 0;
        for (int i = 1; i < 200 * 100 + 1; i++) {
            for (int j = 0; j < i; j++)
                res += dpA[i] * dpB[j];
        }
        return res;
    }
}
