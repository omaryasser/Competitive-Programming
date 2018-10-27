public static long gcd (long a , long b )
	{
		return a == 0 ?b:gcd(b%a,a);
	}
	
public static long lcm (long a , long b)
	{
		return a*(b/gcd(a,b));
	}