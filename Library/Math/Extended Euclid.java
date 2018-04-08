x=x0 + k(b/gcd(a,b)), y0-k(a/gcd(a,b))

only two pairs of x, y will satisfy
|x| < |b / gcd(a, b)|    |y| < |a / gcd(a, b)|
extended euclid gets us one of those two pairs

// to get mod inverse of a with mod m, call extendedEuclid(a, m), res = (x % m) + m

// assume that X, Y are solutions for the real equation, then get other solutions by (X-(b/g)*k,  Y+(a/g)*k)
static class Result
{
int d, x, y;
Result (int dd, int xx, int yy)
{
        d = dd;
        x = xx;
        y = yy;
}
}
public static Result extendedEuclid(int A, int B)
{
        if (A < 0) {
                Result nxt = extendedEuclid(-A, B);
                return new Result(nxt.d, nxt.x * -1, nxt.y);
        }
        if (B < 0) {
                Result nxt = extendedEuclid(A, -B);
                return new Result(nxt.d, nxt.x, nxt.y * -1);
        }
        if( B == 0)
                return new Result(A, 1, 0);

        else
        {
                Result nxt = extendedEuclid(B, A%B);
                return new Result (nxt.d, nxt.y, nxt.x - (A/B)*nxt.y);
        }
}


// # of solutions if wanted x is bounded by minX, maxX, y is bounded by minY, maxY

public class Main {
public static void main(String[] args) throws Exception {

        int a = sc.nextInt(), b = sc.nextInt(), c = -sc.nextInt(), x1 = sc.nextInt(), x2 = sc.nextInt(), y1 = sc.nextInt(), y2 = sc.nextInt();
        Result result = extendedEuclid(a, b);
        if (a == 0 || b == 0) {
                if (a == 0 && b != 0) {
                        if (c % b == 0 && c / b >= y1 && c / b <= y2) System.out.println(1);
                        else System.out.println(0);
                } else if (a != 0 && b == 0) {
                        if (c % a == 0 && c / a >= x1 && c / a <= x2) System.out.println(1);
                        else System.out.println(0);
                }
                else if (c != 0) System.out.println(0);
                else System.out.println(1L * (x2 - x1 + 1) * (y2 - y1 + 1));
                return;
        }
        g = (int) result.d;
        x = (int) (c * result.x / g);
        y = (int) (c * result.y / g);
        m = c / g;
        if (c % g != 0) {
                out.println(0);
                out.close();
                return;
        }

        out.println(find_all_solutions(a, b, x1, x2, y1, y2));
        out.close();
}


static int x, y, g, m;

static void shift_solution(int a, int b, int cnt) {
        x += cnt * b;
        y -= cnt * a;
}

static int find_all_solutions(int a, int b, int minx, int maxx, int miny, int maxy) {
        a /= g;
        b /= g;

        int sign_a = a > 0 ? +1 : -1;
        int sign_b = b > 0 ? +1 : -1;

        shift_solution(a, b, (minx - x) / b);
        if (x < minx) shift_solution(a, b, sign_b);
        if (x > maxx) return 0;
        int lx1 = x;

        shift_solution(a, b, (maxx - x) / b);
        if (x > maxx) shift_solution(a, b, -sign_b);
        int rx1 = x;

        shift_solution(a, b, -(miny - y) / a);
        if (y < miny) shift_solution(a, b, -sign_a);
        if (y > maxy) return 0;
        int lx2 = x;

        shift_solution(a, b, -(maxy - y) / a);
        if (y > maxy) shift_solution(a, b, sign_a);
        int rx2 = x;

        if (lx2 > rx2) {
                lx2 ^= rx2;
                rx2 ^= lx2;
                lx2 ^= rx2;
        }
        int lx = Math.max(lx1, lx2);
        int rx = Math.min(rx1, rx2);

        return (rx - lx) / Math.abs(b) + 1;
}

// # Of Positive solutions
    public void solve(int testNumber, Scanner sc, PrintWriter out) {
        long a = sc.nextInt(), b = sc.nextInt(), n = sc.nextInt();
        Forgive.Result r = extendedEuclid(a, b);
        long x = r.x * n / r.d;
        long y = r.y * n / r.d;
        long a_ = a / r.d, b_ = b / r.d;
        long first = (x - 1) % b_ != 0 && (x - 1) / b_ < 0 ? (x - 1) / b_ - 1 : (x - 1) / b_;
        long second = (1 - y) % a_ != 0 && (1 - y) / a_ > 0? (1 - y) / a_ + 1 : (1 - y) / a_;
        out.println(1l * 5 * 21 * Math.max(0, first - second + 1));
    }


