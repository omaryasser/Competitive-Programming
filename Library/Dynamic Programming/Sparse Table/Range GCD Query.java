public class Range_GCD_Query {
    static int Arr [] , table [][];
    public static int gcd(int a, int b)
    {
        return b == 0 ? a : gcd(b, a%b);
    }
    Range_GCD_Query(int [] in)
    {
        Arr = in.clone();
        int N = Arr.length;
        int k = (int) Math.floor(Math.log(N) / Math.log(2)) + 1;
        table = new int[N][k];

        for (int i = 0; i < N; i++)
            table[i][0] = Arr[i];


        for (int j = 1; j <= k; j++)
            for (int i = 0; i <= N - (1 << j); i++)
                table[i][j] = gcd(table[i][j - 1] , table[i + (1 << (j - 1))][j - 1]);

    }

    int query (int i , int j)
    {
        int k = (int)Math.floor(Math.log(j-i+1) / Math.log(2)); // 2^k <= (j-i+1)
        int  GCD = 0;
        for (int pow = k; pow >= 0; pow--) {
            if(pow == k) GCD = table[i][pow];
            if (i + (1 << pow) - 1 <= j) {
                GCD = gcd(GCD,table[i][pow]);
                i += 1 << pow; // instead of having L', we increment L directly
            }
        }
        return GCD;
    }
}
