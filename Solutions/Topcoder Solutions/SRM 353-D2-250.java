package codes;

public class EllipseCoverage {
    public int calculateCoverage(int x1, int y1, int x2, int y2, int d) {
        int cnt = 0;
        for (int x = -200; x <= 200; x++)
            for (int y = -200; y <= 200; y++) {
                if (Math.sqrt((x1 - x) * (x1 - x) + (y1 - y) * (y1 - y)) +
                        Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y)) + 1e-9 < d)
                    cnt++;
            }
        return cnt;
    }
}
