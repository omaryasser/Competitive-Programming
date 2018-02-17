package codes;

public class DreamingAboutCarrots {
    public int carrotsBetweenCarrots(int x1, int y1, int x2, int y2) {
        int cnt = 0;
        if (x1 > x2) {x1 ^= x2; x2 ^= x1; x1 ^= x2; y1 ^= y2; y2 ^= y1; y1 ^= y2;}
        int dx = x2 - x1;
        int dy = y2 - y1;
        if (dx == 0) return Math.abs(dy) - 1;
        for (int x = x1 + 1; x <= x2 - 1; x++) {
            int y = y1 + (x - x1) * dy / dx;
            if ((y - y1) * dx == (x - x1) * dy) cnt++;
        }
        return cnt;
    }
}
