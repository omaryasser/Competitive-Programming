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