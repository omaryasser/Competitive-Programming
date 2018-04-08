// Cat(0) = 0
// Cat(n) = (C (2n , n)) / (n + 1)

/*
   1- Cn is the number of Dyck words[2] of length 2n.
      A Dyck word is a string consisting of n X's and n Y's such that no initial segment of the string has more Y's than X's
   2- Cn is the number of different ways n + 1 factors can be completely parenthesized 
      (or the number of ways of associating n applications of a binary operator)
   3- It follows that Cn is the number of full binary trees with n + 1 leaves
   4- Cn is the number of monotonic lattice paths along the edges of a grid with n × n square cells, 
      which do not pass above the diagonal
   5- The number of triangles formed in a convex polygon is n and the number of different ways that this can be achieved is Cn
   6- 
*/
static int cat [];
    public static void cat (int n)
    {
        cat = new int[n + 1];
        cat[0] = 1;
        for(int i = 1; i <= n; ++i)
            cat[i] = cat[i-1] * (i<<1) * ((i<<1) - 1) / (i * (i + 1));
    }
