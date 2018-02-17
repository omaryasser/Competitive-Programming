/**
 * Created by omar on 24/07/17.
 */
public class TestScores {
    double[] percentage;
    double probability (int problemsSolved) {
        if (problemsSolved == 0) {
            double p = 1;
            for (double pp : percentage) p *= (1 - pp);
            return p;
        }
        double[][] dp = new double[percentage.length][problemsSolved + 1];
        dp[0][1] = percentage[0];
        dp[0][0] = 1 - dp[0][1];
        for (int i = 1; i < percentage.length; i++) {
            for (int solved = 0; solved <= problemsSolved; solved++) {
                if (solved <= i + 1) {
                    dp[i][solved] = (1 - percentage[i]) * dp[i - 1][solved] + (solved != 0 ? (percentage[i] * dp[i - 1][solved - 1]) : 0);
                }
            }
        }
        return dp[percentage.length - 1][problemsSolved];
    }
    public double weightedScore(double[] questions, int testScore) {
        percentage = questions;
        double average = 0;
        for (double x : questions) average += x;
        double standardDev = 0;
        for (int solved = 0; solved <= questions.length; solved++)
            standardDev += (solved - average) * (solved - average) * probability(solved);
        standardDev = Math.sqrt(standardDev);
        double dev = (testScore - average) / standardDev;
        return 1000.0 + 300.0 * dev;
    }
    public static void main (String[] args) {
        System.err.println(new TestScores().weightedScore(new double[] {0.5, 0.5, 0.5}, 3));
    }
}
