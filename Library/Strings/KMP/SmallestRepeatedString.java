static char P[];
static int b[], m;

public static void kmpPreProcess()
{
        m = P.length;
        b = new int[m + 1];
        int i = 0, j = -1; b[0] = -1;
        while(i < m)
        {
                while(j >= 0 && P[i] != P[j]) j = b[j];
                i++; j++;
                b[i] = j;
        }
}
public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while (true) {
                P = sc.next().toCharArray();
                if (P[0] == '.' && P.length == 1) break;
                kmpPreProcess();
                out.println((m % (m - b[m])) == 0 ? m / (m - b[m]) : 1);
        }

        out.flush();
        out.close();
}
