// a * b ^ k

static int [] apply (int a [] , int b []) {
        int len = a.length;
        int res [] = new int[len];
        for (int i = 0 ; i < len ; ++ i)
            res[i] = b[a[i]];
        return res;
    }
    static int [] permutationPowPerm (int [] a , int [] b , int k) {
        while (k > 0) {
            if ((k & 1) == 1)
                a = apply (a , b);

            b = apply (b , b);
            k >>= 1;
        }
        return a;
    }
