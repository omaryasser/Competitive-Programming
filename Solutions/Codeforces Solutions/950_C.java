import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.ArrayList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, Scanner sc, PrintWriter out) {
            char[] a = sc.next().toCharArray();
            int n = a.length;
            ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
            Stack<Integer> zebras = new Stack<>(), almostZebras = new Stack<>();
            for (int i = 0; i < n; i++) {
                if (a[i] == '0') {
                    if (!almostZebras.isEmpty()) {
                        arrayLists.get(almostZebras.peek()).add(i);
                        zebras.push(almostZebras.pop());
                    } else {
                        ArrayList<Integer> arrayList = new ArrayList<>();
                        arrayList.add(i);
                        zebras.add(arrayLists.size());
                        arrayLists.add(arrayList);
                    }
                } else {
                    if (zebras.isEmpty()) {
                        out.println(-1);
                        return;
                    }
                    arrayLists.get(zebras.peek()).add(i);
                    almostZebras.add(zebras.pop());
                }
            }
            if (almostZebras.size() != 0) {
                out.println(-1);
                return;
            }
            out.println(arrayLists.size());
            for (ArrayList<Integer> arrayList : arrayLists) {
                out.print(arrayList.size());
                for (int num : arrayList)
                    out.print(" " + (num + 1));
                out.println();
            }
        }

    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
                ;
            }
            return st.nextToken();
        }

    }
}

