import java.io.*;
import java.util.*;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        while (true){
            String inp [][] = new String[4][];
            inp[0] = sc.nextLine().split(" ");
            if (inp[0][0].charAt(0) == '#') break;
            inp[1] = sc.nextLine().split(" ");
            inp[2] = sc.nextLine().split(" ");
            inp[3] = sc.nextLine().split(" ");

            Stack<Character> []  stacks = new Stack[2];
            stacks[0] = new Stack<>(); stacks[1] = new Stack<>();

            int idx = 0 ;
            for (int i = 0 ; i < 4 ; ++i){
                for (int j = 0 ; j < inp[i].length ; ++j){
                    if (((idx ++ ) & 1) == 0) stacks[0].add(inp[i][j].charAt(1));
                    else stacks[1].add(inp[i][j].charAt(1));
                }
            }

            int order = 0;
            boolean normal = true;
            int must = 0;
            Stack<Character> heap = new Stack<>();
            while ((order == 0 && !stacks[0].isEmpty()) || (order == 1 && !stacks[1].isEmpty())){

                if (!normal){
                        char c = stacks[order].pop();
                        heap.add(c);
                        boolean isInterrupted = false;
                    must --;
                        switch (c) {
                            case 'A': must = 4; normal = false; isInterrupted = true ; break;
                            case 'K': must = 3; normal = false; isInterrupted = true ; break;
                            case 'Q': must = 2; normal = false; isInterrupted = true ; break;
                            case 'J': must = 1; normal = false; isInterrupted = true ; break;
                            default: break;
                        }

                    if (must == 0){
                        Stack<Character> tmp = new Stack<>();
                        while (!stacks[1 - order].isEmpty()) tmp.add(stacks[1 - order].pop());
                        while (!heap.isEmpty()) stacks[1 - order].add(heap.pop());
                        while (!tmp.isEmpty()) stacks[1 - order].add(tmp.pop());
                        normal = true;
                        order = 1 - order;
                    }
                    else if (isInterrupted){
                        order = 1 - order;
                    }
                }
                else{
                    char c = stacks[order].pop();
                    heap.add(c);
                    switch (c) {
                        case 'A': must = 4; normal = false; break;
                        case 'K': must = 3; normal = false; break;
                        case 'Q': must = 2; normal = false; break;
                        case 'J': must = 1; normal = false; break;
                        default: break;
                    }
                    order = 1 - order;
                }

            }

            if (stacks[0].isEmpty()) out.printf("%d%3d\n" , 1 , stacks[1].size());
            else out.printf("%d%3d\n" , 2 , stacks[0].size());



        }
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
