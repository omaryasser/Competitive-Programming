import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class A {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt(), m = sc.nextInt();
        HashSet<Integer> rowsDone = new HashSet<>();
        char[][] a = new char[n][m];
        for (int i = 0; i < n; i++)
            a[i] = sc.next().toCharArray();


        for (int row = 0; row < n; row++) {
            if (rowsDone.contains(row))
                continue;
            HashSet<Integer> rowsNeeded = new HashSet<>();
            HashSet<Integer> colsNeeded = new HashSet<>();

            Queue<Integer> rows = new LinkedList<>(), cols = new LinkedList<>();
            rows.add(row);
            while (!rows.isEmpty() || !cols.isEmpty()) {
                if (!rows.isEmpty()) {
                    int curRow = rows.poll();
                    for (int j = 0; j < m; j++)
                        if (!colsNeeded.contains(j) && a[curRow][j] == '#') {
                            colsNeeded.add(j);
                            cols.add(j);
                        }
                } else {
                    int curCol = cols.poll();
                    for (int i = 0; i < n; i++)
                        if (!rowsNeeded.contains(i) && a[i][curCol] == '#') {
                            rowsNeeded.add(i);
                            rows.add(i);
                        }
                }
            }

            for (int roww : rowsNeeded)
                for (int colss : colsNeeded)
                    if (a[roww][colss] != '#') {
                        System.out.println("No");
                        return;
                    }
        }
        System.out.println("Yes");
        out.flush();
    }


    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader s) {
            br = new BufferedReader(s);
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }
    }
}
