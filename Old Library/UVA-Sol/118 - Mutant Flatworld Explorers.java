import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collector;

/**
 * Created by aa on 1/06/2016.
 */
public class Main {

    static int R , C ;
    static int currentX , currentY , currentDir;
    static boolean care [][] ;
    static boolean lost;
    static int dx [] = {0,1,0,-1};
    static int dy [] = {1,0,-1,0};
    public static boolean valid (int i , int j)
    {
        return i >=0 && j >=0 && i <= R && j <= C ;
    }
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        R = sc.nextInt();  C = sc.nextInt();
        int t = 0 ;
        care = new boolean[R + 2][C + 2];

        while (sc.ready()) {
            currentX = sc.nextInt();  currentY = sc.nextInt();
            char c = sc.next().charAt(0);
            currentDir = getDir(c);
            char inst[] = sc.next().toCharArray();
            lost = false;
            for (int i = 0; i < inst.length && !lost; ++i)
                process(inst[i]);
            sb.append(currentX + " " + currentY + " " + getAlpha(currentDir));
            if (lost) sb.append(" LOST");
            sb.append("\n");
        }
        out.print(sb.toString());
        out.flush();
        out.close();
    }

    public static int getDir (char c)
    {
        switch (c){
            case 'N' : return 0;
            case 'E' : return 1;
            case 'S' : return 2;
            default:  return 3;
        }
    }
    public static void process (char inst)
    {
        switch (inst){
            case 'R' : currentDir = (currentDir + 1) % 4; break;
            case 'L' : currentDir = (((currentDir - 1) % 4) + 4) % 4; break;
            case 'F' : {
                int newX = currentX + dx[currentDir] , newY = currentY + dy[currentDir];
                if (!valid(newX,newY) && !care[currentX][currentY])
                {
                    care[currentX][currentY] = true;
                    lost = true;
                    break;
                }
                else if (!valid(newX,newY)) break;

                currentX = newX; currentY = newY;
            }
        }
    }
    public static char getAlpha (int num)
    {
        switch (num)
        {
            case 0 : return 'N';
            case 1 : return 'E';
            case 2 : return 'S';
            default: return 'W';
        }
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
