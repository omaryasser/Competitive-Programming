package codes;

public class IsItASquare {
    static int cross (int x1, int y1, int x2, int y2, int x3, int y3) {
        int x11 = x2 - x1, y11 = y2 - y1;
        int x22 = x3 - x2, y22 = y3 - y2;
        return x11 * x22 + y11 * y22;
    }
    int dist (int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
    public String isSquare(int[] x2, int[] y2) {
        int[] idx = new int[4];
        for (int i = 0; i < 4; i++) idx[i] = i;
        do {
            int[] x = new int[4], y = new int[4];
            for (int i = 0; i < 4; i++) {
                x[i] = x2[idx[i]];
                y[i] = y2[idx[i]];
            }

            boolean ok = true;
            for (int i = 0; i < 4; i++) {
                ok &= dist(x[i], y[i], x[(i + 1) % 4], y[(i + 1) % 4]) == dist(x[(i + 1) % 4], y[(i + 1) % 4], x[(i + 2) % 4], y[(i + 2) % 4]);
                ok &= cross(x[i], y[i], x[(i + 1) % 4], y[(i + 1) % 4], x[(i + 2) % 4], y[(i + 2) % 4]) == 0;
            }
            if (ok) return "It's a square";
        } while (next_permutation(idx));
        return "Not a square";
    }
    static boolean next_permutation(int[] A) {
        for (int a = A.length - 2; a >= 0; --a) {
            if (A[a] < A[a + 1]) {
                for (int b = A.length - 1;; --b) {
                    if (A[b] > A[a]) {
                        int t = A[a];
                        A[a] = A[b];
                        A[b] = t;
                        for (++a, b = A.length - 1; a < b; ++a, --b) {
                            t = A[a];
                            A[a] = A[b];
                            A[b] = t;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
