/**
 * Created by omar on 25/07/17.
 */
public class PrimePolynom {
    public int reveal(int A, int B, int C) {
        for (int m = 0; m <= 80; m++) {
            if (!isP(1L * A * m * m + 1L * B * m + C))
                return m;
        }
        return 0;
    }

    boolean isP (long num) {
        if (num < 0) return false;
        if (num == 0 || num == 1) return false;
        for (int i = 2; 1L * i * i <= num; i++)
            if (num % i == 0) return false;
        return true;
    }
}
