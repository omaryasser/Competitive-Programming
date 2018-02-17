import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    static ArrayList<Integer> [] adjList;
    static HashSet<Character> chars;
    static Stack<Integer> ts = new Stack<>();
    static boolean visited [];
    static StringBuilder sb;
    public static void toposort (int idx)
    {
        visited[idx] = true;
        for (int i : adjList[idx])
            if (!visited[i])
                toposort(i);
        ts.push(idx);
    }

    public static void print ()
    {
        while (!ts.isEmpty()) {
            int i = ts.pop();
            if (chars.contains((char)(i + 'A'))) sb.append((char)(i + 'A'));
        }
    }

    public static void found (char arr [] )
    {
        for (int i = 0 ; i < arr.length ; ++i)
            chars.add(arr[i]);
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        sb = new StringBuilder();
        chars = new HashSet<>();
        adjList = new ArrayList[26];
        visited = new boolean[26];
        for (int i = 0 ; i < 26 ; ++i) adjList[i] = new ArrayList<>();
        char last [] = sc.nextLine().toCharArray(); found (last);
        if (last[0] != '#') {
            while (true) {
                char arr[] = sc.next().toCharArray(); found(arr);
                if (arr[0] == '#') break;

                int i ;
                for (i = 0 ; i < arr.length && i < last.length ; ++i)
                    if (arr[i] != last[i]) break;
                if (i == arr.length || i == last.length) continue;
                adjList[last[i] - 'A'].add(arr[i] - 'A');
                last = arr;
            }

            for (int i = 0; i < 26; ++i)
                if (!visited[i]) toposort(i);

            print();
            sb.append("\n");
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }





    static class Scanner
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
