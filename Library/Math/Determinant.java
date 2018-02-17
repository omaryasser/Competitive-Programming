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

static int determinant(int mat[][]) {
        int n = mat.length;
        int res = 0;
        if (n == 1) return mat[0][0];

        int sign = 1;
        for (int f = 0; f < n; f++) {
                int temp [][] = getCofactor(mat, 0, f);
                res += sign * mat[0][f] * determinant(temp);
                if (n == 3) System.err.println(determinant(temp) + " "  +res);
                sign = -sign;
        }
        return res;
}


// O(n ^ 3) using Guass
const double EPS = 1E-9;
int n;
vector <vector <double> > a (n, vector <double> (n));
...reading n and a...

double det = 1;
for (int i = 0; i <n; ++i) {
        int k = i;
        for (int j = i + 1; j <n; ++j)
                if (abs (a [j] [i])> abs (a [k] [i]))
                        k = j;
        if (abs (a [k] [i]) <EPS) {
                det = 0;
                break;
        }
        swap (a [i], a [k]);
        if (i ! = k)
                det = -det;
        det * = a [i] [i];
        for (int j = i + 1; j <n; ++j)
                a [i] [j] / = a [i] [i];
        for (int j = 0; j <n; ++j)
                if (j ! = i && abs (a [j] [i])> EPS)
                        for (int k = i + 1; k <n; ++k)
                                a [j] [k] - = a [i] [k] * a [j] [i];
}

cout << det;
