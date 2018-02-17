// Take Care !! this is true only if mod is prime
// because the equation is a ^ (phi(m - 1)) = a ^ -1 (mod m)
long inv(long x , long mod){
	    long r, y;
	    for(r = 1 , y = mod - 2 ; y != 0 ;x = x * x % mod, y>>=1)
	    	if ((y & 1) == 1)
	    		r = r * x % mod;
	    return r;
	}

long modInvEuclid (long a ,long m) {
	// call euclid on a , m
	// the answer is X
	// Take Care !! - ve value of X
}


// p is prime

static int inv [];
static void modInvRange (int p) {
	inv = new int[p];
	inv[1] = 1;
	for (int i = 2 ; i < p ; ++ i)
		inv[i] = (p - (p / i) * inv[p % i] % p) % p;
}
