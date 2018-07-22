import java.io.*;
import java.util.*;


public class Main {

    static HashMap<Character , Integer> hashMap = new HashMap<>();
    static void fillMap (){
        int cnt = 0 ;
        for (int i = 0 ; i < 26 ; i += 3){
            for (int j = 0 ; j < 3 ; ++j){
                char c = (char)(i + j + 'A');
                if (c == 'Q') { i ++ ; j -- ;}
                else if (c == 'Z') continue;
                else hashMap.put(c , cnt + 2);

            }
            ++cnt;
        }
        for (int i = 0 ; i < 10 ; ++i)
            hashMap.put((char)(i + '0') , i);
    }

    static String convert (String x){
        StringBuilder res = new StringBuilder();
        for (int i = 0 ; i < x.length() ; ++i)
            if (x.charAt(i) == '-') continue;
            else if (res.length() == 3) res.append('-').append(hashMap.get(x.charAt(i)));
            else res.append(hashMap.get(x.charAt(i)));
        return res.toString();
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();

        fillMap ();
        int T = sc.nextInt();
        while (T -- > 0){
            TreeMap<String,Integer> cnt = new TreeMap<>();
            int N = sc.nextInt();
            while (N --  >0){
                String current = convert (sc.next());
                if (cnt.containsKey(current)) cnt.put(current,cnt.get(current) + 1);
                else cnt.put(current , 1);
            }

            boolean thereIsOne = false;
            for (Map.Entry<String,Integer> map : cnt.entrySet()){
                if (map.getValue() > 1){
                    sb.append(map.getKey()).append(" ").append(map.getValue()).append("\n");
                    thereIsOne = true;
                }
            }
            if (!thereIsOne) sb.append("No duplicates.\n");
            if (T != 0) sb.append("\n");
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
