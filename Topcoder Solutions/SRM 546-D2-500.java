package codes;

public class TwoRectangles {
    public String describeIntersection(int[] A, int[] B) {
        int left = Math.max(A[0], B[0]);
        int right = Math.min(A[2], B[2]);
        int bottom = Math.max(A[1], B[1]);
        int top = Math.min(A[3], B[3]);
        if (left > right || bottom > top) return "none";
        if (left == right && top == bottom) return "point";
        if (left == right || bottom == top) return "segment";
        return "rectangle";
    }
}
