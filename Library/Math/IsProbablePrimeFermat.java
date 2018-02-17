/* Fermat's test for checking primality, the more iterations the more is accuracy */
bool Fermat(long long p,int iterations){
        if(p == 1) { // 1 isn't prime
                return false;
        }
        for(int i=0; i<iterations; i++) {
                // choose a random integer between 1 and p-1 ( inclusive )
                long long a = rand()%(p-1)+1;
                // modulo is the function we developed above for modular exponentiation.
                if(modulo(a,p-1,p) != 1) {
                        return false; /* p is definitely composite */
                }
        }
        return true; /* p is probably prime */
}
