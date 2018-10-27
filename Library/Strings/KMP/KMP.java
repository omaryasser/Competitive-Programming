static char T[], P[];
    static int b[], n, m; // b[i] = the largest suffix ending at i - 1 matches a prefix (of the pattern)

    // smallest repeated string println((m % (m - b[m])) == 0 ? m / (m - b[m]) : 1);

    public static void kmpPreProcess() {
        m = P.length;
        b = new int[m + 1];
        int i = 0, j = -1;
        b[0] = -1;
        while (i < m) {
            while (j >= 0 && P[i] != P[j]) j = b[j];
            i++;
            j++;
            b[i] = j;
        }
    }

    public static void kmpSearch() {
        int i = 0, j = 0;
        n = T.length;
        while (i < n) {

            while (j >= 0 && T[i] != P[j]) j = b[j];
            i++;
            j++;
            if (j == m) {
                System.out.println("Pattern is Found in index" + (i - j));
                j = b[j];
            }

        }
    }
