package codes;

public class RecursiveFigures {
    public double area (double rad) {
        return Math.PI * rad * rad;
    }
    public double solve (double rad, int cur, int intended, boolean isSquare) {
        if (isSquare) {
            double length = Math.sqrt(2) * rad;
            return length * length - solve(length / 2, cur + 1, intended, !isSquare);
        } else {
            if (intended == cur) return area(rad);
            return area(rad) - solve(rad, cur, intended, !isSquare);
        }
    }
    public double getArea(int sideLength, int K) {
        return solve(sideLength / 2.0, 1, K, false);
    }
}
