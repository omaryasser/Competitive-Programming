// Time complexity O (log b)

public static long mod_rec(int a , int b , int c) // returns (a ^ b) %c
    {
        if(b == 0) // (a ^ 0) %c = (1 % c) = 1
            return 1;
        if(b == 1)  // (a ^ 1) % c = a % c
            return a % c;
        if((b & 1) == 0) // b is even
            return mod_rec( (a*a) % c , b >> 1 , c);
        return (a * mod_rec( (a*a) % c , b >> 1 , c) ) % c; // b is odd
    }

 /*
 Idea :
 We know that a^b can be written as:

a^b = a^(2(b/2)) - If b is even, and b > 0.
ab= (a) * a2(b-1/2) - If b is odd.
ab= 1 - If b is 0.
 */