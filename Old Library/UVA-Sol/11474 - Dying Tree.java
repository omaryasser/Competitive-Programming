    import java.io.*;
    import java.util.*;


    public class G {

        public static void main(String [] args) throws  Exception
        {
            Scanner sc = new Scanner(System.in);
            PrintWriter out = new PrintWriter(System.out);
            StringBuilder sb = new StringBuilder();

            int T = Math.max(0,sc.nextInt());
            while (T -- > 0)
            {
                int n = sc.nextInt();
                int m = sc.nextInt();
                int k = sc.nextInt();
                int d = sc.nextInt();

                Point doctors [] = new Point[m];
                for (int i = 0 ; i < m ; ++i)
                {
                    doctors[i] = new Point(sc.nextInt() , sc.nextInt());
                }

                ArrayList<ArrayList<Point>> trees = new ArrayList<>();

                int idx = 0 ;
                for (int i = 0 ; i < n ; ++i)
                {
                    int b = sc.nextInt();
                    ArrayList<Point> tree = new ArrayList<>();
                    for (int j = 0 ; j < b ; ++j)
                    {
                        Point cur = new Point(sc.nextInt() , sc.nextInt());
                        tree.add(cur);
                    }
                    trees.add(tree);
                }
                UnionFind UF = new UnionFind(n);
                for (int i = 0 ; i < n - 1; ++i)
                {
                    for (int j = i + 1 ; j < n ; ++j)
                    {
                        for (int h = 0 ; h < trees.get(i).size() ; ++h)
                        {
                            for (int l = 0 ; l < trees.get(j).size() ; ++l)
                            {
                                int x1 = trees.get(i).get(h).x;
                                int y1 = trees.get(i).get(h).y;
                                int x2 = trees.get(j).get(l).x;
                                int y2 = trees.get(j).get(l).y;
                                if ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) <= k * k)
                                    if (!UF.isSameSet(i , j))
                                        UF.unionSet(i , j);
                            }
                        }
                    }
                }



                boolean ok = false;
                for (int i = 0 ; i < trees.size() && !ok; ++i)
                {
                    if (!UF.isSameSet(0,i)) continue;
                    for (int j = 0 ; j < trees.get(i).size() ; ++j) {
                        for (int f = 0; f < doctors.length; ++f) {
                            int x1 = trees.get(i).get(j).x;
                            int y1 = trees.get(i).get(j).y;
                            int x2 = doctors[f].x;
                            int y2 = doctors[f].y;

                            if ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) <= d * d) {
                                ok = true;
                                break;
                            }
                        }
                    }

                }

                if (ok) sb.append("Tree can be saved :)\n");
                else sb.append("Tree can't be saved :(\n");

            }

            out.print(sb.toString());
            out.flush();
            out.close();
        }


        static class UnionFind {
            int [] p , rank , setSize;
            public UnionFind(int size)
            {
                p = new int[size] ;
                rank = new int[size] ;
                setSize = new int[size];
                for(int i = 0 ; i < size ; ++i) {
                    p[i] = i;
                    setSize[i] = 1;
                }
            }

            int findSet(int i ) {return (p[i] == i)? i : (p[i] = findSet(p[i]));}
            boolean isSameSet(int i , int j) {return findSet(i) == findSet(j);}
            void unionSet(int i , int j)
            {
                if(!isSameSet(i,j))
                {
                    int x = findSet(i) , y = findSet(j);
                    if(rank[x] > rank[y]) {p[y] = x; setSize[x] = setSize[x] + setSize[y];}
                    else
                    {
                        p[x] = y;
                        setSize[y] = setSize[y] + setSize[x];
                        if(rank[x] == rank[y]) ++rank[y];
                    }
                }
            }


        }
        static class Point implements Comparable<Point> {
            int x;
            int y ;
            Point (int xx , int yy)
            {
                x = xx;
                y = yy;
            }

            @Override
            public int compareTo(Point point) {
                return x == point.x && y == point.y ? 0 : x == point.x ? y - point.y : x - point.x  ;
            }

            @Override
            public boolean equals(Object o) {
                return x == ((Point)(o)).x && y == ((Point)(o)).y;
            }

            @Override
            public String toString() {
                return ("(" + x+","+y +")");
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
