import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by omar on 23/07/17.
 */
public class RandomFA {
    int STATES_NUM, destination, n;
    double[][] A, B, C;
    double[][] memo;

    double dp (int idx, int state) {
        if (idx == n) {
            return state == destination ? 1 : 0;
        }

        if (memo[idx][state] > -1) return memo[idx][state];

        double res = state == destination ? 1 : 0;
        if (state == 999) return memo[idx][state] = res + 3 * dp(idx + 1, 999);
        for (int i = 0; i < STATES_NUM; i++) {
            res += A[state][i] * dp(idx + 1, i);
            res += B[state][i] * dp(idx + 1, i);
            res += C[state][i] * dp(idx + 1, i);
        }
        res += A[state][999] * dp(idx + 1, 999);
        res += B[state][999] * dp(idx + 1, 999);
        res += C[state][999] * dp(idx + 1, 999);
        return memo[idx][state] = res;
    }
    public double getProbability(String[] rulesa, String[] rulesb, String[] rulesc, int finalState, int maxLength) {
        STATES_NUM = rulesa.length;
        n = maxLength;
        destination = finalState;
        A = new double[1000][1000];
        B = new double[1000][1000];
        C = new double[1000][1000];
        for (int i = 0; i < rulesa.length; i++) {
            StringTokenizer st = new StringTokenizer(rulesa[i]);
            double sum = 0;
            while (st.hasMoreTokens()) {
                StringTokenizer st2 = new StringTokenizer(st.nextToken(), ":");
                double y;
                A[i][Integer.parseInt(st2.nextToken())] = y = (Integer.parseInt(st2.nextToken())) / 100.0;
                sum += y;
            }
            A[i][999] = 1 - sum;
        }
        for (int i = 0; i < rulesa.length; i++) {
            StringTokenizer st = new StringTokenizer(rulesb[i]);
            double sum = 0;
            while (st.hasMoreTokens()) {
                StringTokenizer st2 = new StringTokenizer(st.nextToken(), ":");
                double y;
                B[i][Integer.parseInt(st2.nextToken())] = y = (Integer.parseInt(st2.nextToken())) / 100.0;
                sum += y;
            }
            B[i][999] = 1 - sum;
        }
        for (int i = 0; i < rulesa.length; i++) {
            StringTokenizer st = new StringTokenizer(rulesc[i]);
            double sum = 0;
            while (st.hasMoreTokens()) {
                StringTokenizer st2 = new StringTokenizer(st.nextToken(), ":");
                double y;
                C[i][Integer.parseInt(st2.nextToken())] = y = (Integer.parseInt(st2.nextToken())) / 100.0;
                sum += y;
            }
            C[i][999] = 1 - sum;
        }
        memo = new double[maxLength + 1][1000];
        for (double[] d : memo) Arrays.fill(d, -2);
        return dp(0,0) / all(maxLength);
    }

    static int all (int len) {
        int res = 1, pow = 1;
        for (int i = 1; i <= len; i++) {
            pow *= 3;
            res += pow;
        }
        return res;
    }
}
