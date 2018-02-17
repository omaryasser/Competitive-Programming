package codes;

import java.util.Arrays;

public class ManySquares {
    public int howManySquares(int[] sticks) {
        Arrays.sort(sticks);
        int cnt = 0;
        for (int i = 0; i < sticks.length -3; i++) {
            boolean can = true;
            for (int j = 0; j < 4; j++)
                can &= sticks[i] == sticks[i + j];
            if (can) {
                cnt++;
                i += 3;
            }
        }
        return cnt;
    }
}
