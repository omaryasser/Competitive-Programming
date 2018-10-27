// write your function here
static double f (double x) {
        return Math.sqrt(Math.abs(r1 * r1 - x * x) * Math.abs(r2 * r2 - x * x));
}
static int n; // n should be even
static double x (int j) {
        return a + j * h;
}
static double integrate (double aa, double bb) {
        a = aa;
        b = bb;
        a = 0;
        b = r1;
        n = (int)1e7;
        h = (b - a) / n;
        double res = 0;
        res += f(x(0));
        double res2 = 0;
        for (int j = 1; j <= n / 2 - 1; j++)
                res2 += f(x(2 * j));
        res2 *= 2;
        res += res2;
        res2 = 0;
        for (int j = 1; j <= n / 2; j++)
                res2 += f(x(2 * j - 1));
        res2 *= 4;
        res += res2;
        res += f(x(n));
        res *= h / 3;

        return res * 8;
}
