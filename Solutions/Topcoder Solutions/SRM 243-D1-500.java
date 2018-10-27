import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by omar on 21/07/17.
 */
public class TopFive {
    int[][] score;
    double[][] probabilty;
    double[][] memo;
    int n;
    int myScore;

    double dp (int idx, int better) {
        if (idx == n) {
            return better < 5 ? 1 : 0;
        }
        if (better >= 5) return 0;
        if (memo[idx][better] > -1) return memo[idx][better];
        double res = 0;
        for (int a = 0; a < 2; a++) {
            for (int b = 0; b < 2; b++) {
                for (int c = 0; c < 2; c++) {
                    int scoreHere = 0;
                    double prob = 1;
                    if (a == 1) {
                        scoreHere += score[idx][0];
                        prob *= probabilty[idx][0];
                    }
                    else
                        prob *= (1 - probabilty[idx][0]);

                    if (b == 1) {
                        scoreHere += score[idx][1];
                        prob *= probabilty[idx][1];
                    }
                    else
                        prob *= (1 - probabilty[idx][1]);

                    if (c == 1) {
                        scoreHere += score[idx][2];
                        prob *= probabilty[idx][2];
                    }
                    else
                        prob *= (1 - probabilty[idx][2]);

                    if (scoreHere >= myScore) res += prob * dp(idx + 1, better + 1);
                    else res += prob * dp(idx + 1, better);
                }
            }
        }
        return memo[idx][better] = res;
    }
    public double findProbability(String[] results, String[] accuracies, int points) {
        n = results.length;
        myScore = points;
        score = new int[n][3];
        probabilty = new double[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(results[i]);
            for (int j = 0; j < 3; j++)
                score[i][j] = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(accuracies[i]);
            for (int j = 0; j < 3; j++)
                probabilty[i][j] = 1.0 * Integer.parseInt(st.nextToken()) / 100;
        }
        memo = new double[n][5];
        for (double[] d : memo) Arrays.fill(d, -2);
        return dp(0, 0);
    }
}
