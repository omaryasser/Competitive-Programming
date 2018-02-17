package codes;

public class BiggestRectangleEasy {
    public int findArea(int N) {
        int max = 0;
        for (int x = 1; x <= 10000; x++)
            for (int y = 1; y <= 10000; y++)
                if (2 * x + 2 * y <= N)
                    max = Math.max(max, x * y);
        return max;
    }
}
