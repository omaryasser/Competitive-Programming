package codes;

public class PointDistance {
    public int[] findPoint(int x1, int y1, int x2, int y2) {
        if (x1 < x2) return new int [] {x2 + 1, y2};
        return new int[] {x2 - 1, y2};
    }
}
