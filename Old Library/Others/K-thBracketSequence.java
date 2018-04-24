// of One type

BigInteger d[][] = new BigInteger [n*2+1][n+1];
for (int i=0; i<=n*2; ++i)
        for (int j=0; j<=n; ++j)
                d[i][j] = BigInteger.ZERO;
d[0][0] = BigInteger.ONE;
for (int i=0; i<n*2; ++i)
        for (int j=0; j<=n; ++j) {
                if (j+1 <= n)
                        d[i+1][j+1] = d[i+1][j+1].add( d[i][j] );
                if (j > 0)
                        d[i+1][j-1] = d[i+1][j-1].add( d[i][j] );
        }

String ans = new String();
if (k.compareTo( d[n*2][0] ) > 0)
        ans = "No solution";
else {
        int depth = 0;
        for (int i=n*2-1; i>=0; --i)
                if (depth+1 <= n && d[i][depth+1].compareTo( k ) >= 0) {
                        ans += '(';
                        ++depth;
                }
                else {
                        ans += ')';
                        if (depth+1 <= n)
                                k = k.subtract( d[i][depth+1] );
                        --depth;
                }
}
System.out.println(ans);


// more than One type
int n;  BigInteger k;  // входные данные

BigInteger d[][] = new BigInteger [n*2+1][n+1];
for (int i=0; i<=n*2; ++i)
        for (int j=0; j<=n; ++j)
                d[i][j] = BigInteger.ZERO;
d[0][0] = BigInteger.ONE;
for (int i=0; i<n*2; ++i)
        for (int j=0; j<=n; ++j) {
                if (j+1 <= n)
                        d[i+1][j+1] = d[i+1][j+1].add( d[i][j] );
                if (j > 0)
                        d[i+1][j-1] = d[i+1][j-1].add( d[i][j] );
        }

String ans = new String();
int depth = 0;
char [] stack = new char[n*2];
int stacksz = 0;
for (int i=n*2-1; i>=0; --i) {
        BigInteger cur;
        // '('
        if (depth+1 <= n)
                cur = d[i][depth+1].shiftLeft( (i-depth-1)/2 );
        else
                cur = BigInteger.ZERO;
        if (cur.compareTo( k ) >= 0) {
                ans += '(';
                stack[stacksz++] = '(';
                ++depth;
                continue;
        }
        k = k.subtract( cur );
        // ')'
        if (stacksz > 0 && stack[stacksz-1] == '(' && depth-1 >= 0)
                cur = d[i][depth-1].shiftLeft( (i-depth+1)/2 );
        else
                cur = BigInteger.ZERO;
        if (cur.compareTo( k ) >= 0) {
                ans += ')';
                --stacksz;
                --depth;
                continue;
        }
        k = k.subtract( cur );
        // '['
        if (depth+1 <= n)
                cur = d[i][depth+1].shiftLeft( (i-depth-1)/2 );
        else
                cur = BigInteger.ZERO;
        if (cur.compareTo( k ) >= 0) {
                ans += '[';
                stack[stacksz++] = '[';
                ++depth;
                continue;
        }
        k = k.subtract( cur );
        // ']'
        ans += ']';
        --stacksz;
        --depth;
}
