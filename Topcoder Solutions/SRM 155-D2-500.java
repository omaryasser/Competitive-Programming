/**
 * Created by omar on 21/07/17.
 */
public class BenfordsLaw {
    public int questionableDigit(int[] transactions, int threshold) {
        int[] freq = new int[10];
        for (int num : transactions) {
            int last = 0;
            while (num > 0) {
                if (num % 10 != 0) last = num % 10;
                num /= 10;
            }
            freq[last]++;
        }

        for (int i = 1; i < 10; i++){
            double expected = Math.log(1 + 1.0 / i) / Math.log(10);
            if (1.0 * freq[i] / transactions.length > expected * threshold ||
                    1.0 * freq[i] / transactions.length < expected / threshold)
                return i;
        }
        return -1;
    }
}
