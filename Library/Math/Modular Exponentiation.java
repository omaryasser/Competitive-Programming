// Time complexity O (log b)

public static long modular_exponentiation (int a  , int  b , int c ) // returns ( a ^ b ) % c
    {
        long ans = 1L;
        while(b!=0)
        {
            if((b & (1)) == 1)
                ans = (ans*a) %c;

            a = (a * a)%c;
            b >>=1L ;
        }

        return ans;
    }


/*
Idea :
Processing: 
1. Calculate (559)%19, where ‘%’ stands for modulo operator.
express 59 in binary = {111011} 2 
559 = 5 {111011}2 = 5^(2^5) * 5^(2^4) * 5^(2^3) * 5^(2^3) * 5^(2^1) * 5^(2^0) 
Let’s compute its value in 6 iterations:

Let ans=1, a=5, b=59, c=19;

Iteration 1:
Since the rightmost digit of ‘b’(111011) is 1:
ans=(ans * a)%c = (1 * 5)%19 = 5
a=(a * a)%c = (5 * 5)%19 = 6
b /= 2 (b = 11101)

Iteration 2:
Since the rightmost digit of ‘b’(11101) is 1:
ans=(ans * a)%c = (5 * 6)%19 = 11
a=(a * a)%c = (6 * 6)%19 = 17
b /= 2 (b = 1110)

Iteration 3:
Since the rightmost digit of ‘b’(1110) is 0:
We won’t multiply anything to ans
a=(a * a)%c = (17 * 17)%19 = 4
b /= 2 (b = 111)

Iteration 4:
Since the rightmost digit of ‘b’(111) is 1:
ans=(ans * a)%c = (11 * 4)%19 = 6
a=(a * a)%c = (4 * 4)%19 = 16
b /= 2 (b = 11)

Iteration 5:
Since rightmost digit of ‘b’(11) is 1:
ans=(ans * a)%c = (6 * 16)%19 = 1
a=(a * a)%c = (16 * 16)%19 = 9
b /= 2 (b = 1)

Iteration 6:
Since rightmost digit of ‘b’(1) is 1:
ans=(ans * a)%c = (1 * 9)%19 = 9
a=(a * a)%c = (9 * 9)%19 = 5 
b /= 2 (b = 0) //break

Hence, the final answer is ans = 9,
therefore (559)%19 = 9
*/