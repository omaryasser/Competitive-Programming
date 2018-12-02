/*
 * Divide & Conquer: dp[i][j] = min k < j{dp[i - 1][k] + C[k][j]} A[i][j] <= A[i][j + 1]
 * Knuth: dp[i][j] = mini < k < j {dp[i][k] + dp[k][j]} + C[i][j] A[i, j - 1] ≤ A[i, j] ≤ A[i + 1, j] O(n^2)
 * Convex Hull Optimization
 * dp[i] = min(j < i){ dp[j] + a[i] * b[j] } where b[j] >= b[j + 1]
 * 
 * Original Complexity: O(n^2)
 * Optimized Complexity: O(n log n) or O(n) if a[i] <= a[i + 1]
 * 
 * Following operations are used within the DP function
 * 
 * Can be trapped in overflow
 */
public class ConvexHull {

        long[] A, B;            //initialized with size n
        long[] starts;
        long[] ends;
        int ptr, len;
        long INF = (long)1e10;
        ConvexHull (int n) {
            A = new long[n];
            B = new long[n];
            starts = new long[n];
            ends = new long[n];
        }

        void addLine(long a, long b) // // lines are sorted with their slopes decreasing
        {
            while (len >= 2 &&
                    (A[len - 1] - A[len - 2]) * (B[len - 1] - b) <= (A[len - 1] - a) * (B[len - 1] - B[len - 2]))
                --len;
            A[len] = a;
            B[len] = b;
            if (len == 0) starts[len] = -INF;
            else {
                starts[len] = (B[len] - B[len - 1] + A[len - 1] - A[len] - 1) / (A[len - 1] - A[len]);
            }
            ++len;
        }

        long queryPointer(long x)	// O(n), queries must be performed in increasing order (TODO : Test it)
        {
            ptr = Math.min(ptr, len - 1);
            while(ptr < len - 1 && A[ptr + 1] * x + B[ptr + 1] <= A[ptr] * x + B[ptr])
                ++ptr;
            return A[ptr] * x + B[ptr];
        }

        void addLine2(long a, long b) // lines are sorted with their slopes increasing
        {
            while (len >= 2 &&
                    (A[len - 1] - A[len - 2]) * (B[len - 1] - b) >= (A[len - 1] - a) * (B[len - 1] - B[len - 2]))
                --len;
            A[len] = a;
            B[len] = b;
            if (len == 0) ends[0] = INF;
            else {
                ends[len] = (B[len] - B[len - 1]) / (A[len - 1] - A[len]);
            }
            ++len;
        }


        long queryBS(long x) { // use it if you filled starts[]
            int ans = 0;
            int lo = 1, hi = len - 1;
            while (lo <= hi) {
                int mid = lo + hi >> 1;
                if (starts[mid] <= x ) {
                    ans = mid;
                    lo = mid + 1;
                } else
                    hi = mid - 1;
            }
            return  x * A[ans] + B[ans];
        }

        long queryBS2(long x) { // use it if you filled ends[]
            int ans = 0;
            int lo = 1, hi = len - 1;
            while (lo <= hi) {
                int mid = lo + hi >> 1;
                if (ends[mid] >= x ) {
                    ans = mid;
                    lo = mid + 1;
                } else
                    hi = mid - 1;
            }
            return  x * A[ans] + B[ans];
        }
    }
