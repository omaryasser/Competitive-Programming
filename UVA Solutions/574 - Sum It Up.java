
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static class Sorter implements Comparator<Integer>
    {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 != o2 ? o2 - o1 : 0;
        }
    }

    static class Sorter2 implements Comparator<ArrayList<Integer> >
    {
        @Override
        public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
            for(int i = 0 ; i < Math.min(o1.size() , o2.size()) ; ++i)
                if(o1.get(i) != o2.get(i))
                    return o2.get(i) - o1.get(i);
            return o2.size() - o1.size();
        }
    }

    public static boolean has (ArrayList<Integer> arr)
    {
        for(int i = 0 ; i < res.size() ; ++i)
        {
            if (res.get(i).size() != arr.size()) continue;
            boolean foundDifferent = true;
            for(int j = 0 ; j < res.get(i).size() ; ++j)
                if(arr.get(j) != res.get(i).get(j)) {
                    foundDifferent = false;
                    break;
                }
            if (foundDifferent) return true;
        }

        return false;
    }
    static ArrayList<ArrayList<Integer> > res;
    public static void main(String[]args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true)
        {
            int T = sc.nextInt() , N = sc.nextInt();
            if(T == 0) break;
            res = new ArrayList<>();
            int arr [] = new int[N];
            for(int i = 0 ; i < N ; ++i) arr[i] = sc.nextInt();
            int mask = 1 << N;
            while (mask -- > 0)
            {
                int sum = 0;
                ArrayList<Integer> tmp = new ArrayList<>();
                for(int i = 0 ; i < N ; ++i)
                    if(sum == T) break;
                    else if(((1 << i) & mask) != 0) {
                        tmp.add(arr[i]);
                        sum+=arr[i];
                    }

                    Collections.sort(tmp , new Sorter ());
                if(sum == T) if( !has (tmp)) res.add(tmp);
            }
            out.printf("Sums of %d:\n" , T);
            if(res.size() == 0) out.printf("NONE\n");
            Collections.sort(res , new Sorter2 ());
            for(int i = 0 ; i < res.size() ; ++i) {
                for (int j = 0; j < res.get(i).size(); ++j)
                    if (j == 0) out.printf("%d", res.get(i).get(j));
                    else out.printf("+%d", res.get(i).get(j));
                out.print("\n");
            }
        }
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
