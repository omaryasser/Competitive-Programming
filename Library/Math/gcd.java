public static int gcd(int a, int b) {
								return b == 0 ? a : gcd(b, a%b);
}

public static long gcd(long a, long b) {
								return b == 0 ? a : gcd(b, a%b);
}
