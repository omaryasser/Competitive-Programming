// NOT TESTED
static int numberOfSpanningTree () {
        int mat [][] = new int[V - 1][V - 1];
        for (int i = 0; i < V - 1; i++) for (int j = 0; j < V - 1; j++) {
                        mat[i][j] = 0 - adjMat[i][j];
                        if (i == j) mat[i][j] += degree[i];
                }

        return determinant(mat);
}

static int determinant(int mat[][]) {
        int n = mat.length;
        int res = 0;
        if (n == 1) return mat[0][0];

        int sign = 1;
        for (int f = 0; f < n; f++) {
                int temp [][] = getCofactor(mat, 0, f);
                res += sign * mat[0][f] * determinant(temp);
                sign = -sign;
        }
        return res;
}

static int [][] getCofactor(int mat[][], int x, int y) {
        int n = mat.length;
        int temp [][] = new int[n - 1][n - 1];
        int i = 0, j = 0;
        for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                        if (row != x && col != y) {
                                temp[i][j++] = mat[row][col];
                                if (j == n - 1) {
                                        j = 0;
                                        i++;
                                }
                        }
                }
        }
        return temp;
}
