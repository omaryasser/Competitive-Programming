
import java.io.*;
import java.util.*;

public class Main {

    static class X implements  Comparable<X>{
        int idx ;
        TreeSet<Integer> treeSet;
        X (int i){
            idx = i;
            treeSet = new TreeSet<>();
        }

        @Override
        public int compareTo(X x) {
            return x.treeSet.size() ==  treeSet.size() ? idx - x.idx : x.treeSet.size() - treeSet.size();
        }
    }

    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        for (int tt = 1 ; tt <= T ; ++tt) {
            X treeSets[] = new X[3];
            for (int i = 0; i < 3; ++i) {
                int S = sc.nextInt();
                treeSets[i] = new X(i + 1);
                while (S -- > 0) treeSets[i].treeSet.add(sc.nextInt());
            }

            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int x : treeSets[0].treeSet) {
                if (treeSets[1].treeSet.contains(x) || treeSets[2].treeSet.contains(x)) {
                    if (treeSets[1].treeSet.contains(x)) treeSets[1].treeSet.remove(x);
                    if (treeSets[2].treeSet.contains(x)) treeSets[2].treeSet.remove(x);
                    arrayList.add(x);
                }
            }
            for (int x : arrayList) treeSets[0].treeSet.remove(x);
            arrayList.clear();
            for (int x : treeSets[1].treeSet) {
                if (treeSets[2].treeSet.contains(x)) {
                    treeSets[2].treeSet.remove(x);
                    arrayList.add(x);
                }
            }
            for (int x : arrayList) treeSets[1].treeSet.remove(x);

            Arrays.sort(treeSets);
            int max = treeSets[0].treeSet.size();
            sb.append("Case #" + tt + ":\n");
            int idx = 0;
            while (idx < 3 && treeSets[idx].treeSet.size() == max) {
                sb.append(treeSets[idx].idx);
                sb.append(" " + treeSets[idx].treeSet.size());
                for (int num : treeSets[idx].treeSet)
                    sb.append(" " + num);

                idx++;
                sb.append("\n");
            }
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }











































    static  class Scanner
    {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

        public String next() throws IOException
        {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {return Integer.parseInt(next());}

        public long nextLong() throws IOException {return Long.parseLong(next());}

        public String nextLine() throws IOException {return br.readLine();}

        public double nextDouble() throws IOException
        {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if(x.charAt(0) == '-')
            {
                neg = true;
                start++;
            }
            for(int i = start; i < x.length(); i++)
                if(x.charAt(i) == '.')
                {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                }
                else
                {
                    sb.append(x.charAt(i));
                    if(dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg?-1:1);
        }

        public boolean ready() throws IOException {return br.ready();}


    }

}
