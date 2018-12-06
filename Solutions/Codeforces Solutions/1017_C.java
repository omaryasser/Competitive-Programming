import java.io.*;
import java.util.*;

public class Main {
    static int lis(int[] a, int n)
    {
        int lis = 0;
        int[] L = new int[n];
        for(int i = 0; i < n; ++i)
        {
            int cur_L = 1;
            for(int j = 0; j < i; ++j)
                if(a[j] < a[i])
                    cur_L = Math.max(cur_L, L[j] + 1);
            L[i] = cur_L;
            lis = Math.max(lis, cur_L);
        }
        return lis;
    }
    static int lds(int[] a, int n)
    {
        int lis = 0;
        int[] L = new int[n];
        for(int i = 0; i < n; ++i)
        {
            int cur_L = 1;
            for(int j = 0; j < i; ++j)
                if(a[j] > a[i])
                    cur_L = Math.max(cur_L, L[j] + 1);
            L[i] = cur_L;
            lis = Math.max(lis, cur_L);
        }
        return lis;
    }
    public static void main(String[] args) throws Throwable {
        MyScanner sc = new MyScanner();
        PrintWriter pw = new PrintWriter(System.out);

//        for(int n=1;n<=10;n++) {
        int n = sc.nextInt();
            int a[] = new int[n];
//            for (int i = 0; i < n; i++) {
//                a[i] = i + 1;
//            }
//
//            int best[] = a.clone();
            int bstL=(int)1e6,bstR=(int)1e6;
            for(int LIS=1;LIS<=n;LIS++){
                int LDS=(n+LIS-1)/LIS;
//                System.err.println(LIS+" " +LDS);
                if(LIS+LDS<bstR+bstL){
                    bstL=LIS;
                    bstR=LDS;
                }
            }
            int[] mine = new int[n];

            ArrayList<Integer>arrayList[]=new ArrayList[bstL];
            for (int i =0;i<bstL;i++)arrayList[i]=new ArrayList<>();
            for(int i=0;i<n;i++){
                arrayList[Math.min(bstL-1,i/bstR)].add(i+1);
            }
            for(ArrayList<Integer> aa:arrayList) {
//                System.err.println(aa);
                Collections.reverse(aa);
            }
//            System.err.println("asdfadsf");
            ArrayList<Integer>last=new ArrayList<>();
            for(ArrayList<Integer> aa:arrayList)
                for(int i:aa)
                    last.add(i);
            for(int i = 0; i < n; i++)
                mine[i]=last.get(i);
            for(int i = 0; i < n; i++){
                if(i > 0) pw.print(" ");
                pw.print(mine[i]);
            }
            pw.println();
//            int bst = 100;
//            System.err.println(Arrays.toString(mine));
//            do {
//                if (lis(a, n) + lds(a, n) < bst) {
//                    bst = lis(a, n) + lds(a, n);
//                    best = a.clone();
//                }
//            } while (next_permutation(a));

//            System.err.println(Arrays.toString(best));
//            System.err.println(lis(best, n) + " " + lds(best, n)+" "+(lis(best, n) + lds(best, n)) + " " + (lis(mine, n) + lds(mine, n)));
//        }
        pw.flush();
        pw.close();
    }

    static boolean next_permutation(int[] A) {
        for (int a = A.length - 2; a >= 0; --a) {
            if (A[a] < A[a + 1]) {
                for (int b = A.length - 1;; --b) {
                    if (A[b] > A[a]) {
                        int t = A[a];
                        A[a] = A[b];
                        A[b] = t;
                        for (++a, b = A.length - 1; a < b; ++a, --b) {
                            t = A[a];
                            A[a] = A[b];
                            A[b] = t;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
    static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}