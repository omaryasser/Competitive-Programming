import java.util.TreeMap;

/**
 * Created by omar on 12/4/16.
 */
public class PalindromeMatrixDiv2 {
    static class Pair implements Comparable<Pair>{
        int i , j;
        Pair(int ii ,int jj) {
            i = ii;
            j = jj;
        }

        @Override
        public int compareTo(Pair pair) {
            return i == pair.i ? j - pair.j : i - pair.i;
        }
    }
    static int n , m;
    static TreeMap<Pair,Integer> posToIdx;
    static void fillMap (int rowMask , int colMask){
        posToIdx = new TreeMap<>();
        int idx = 0 ;
        for (int i = 0 ; i < n ; ++ i) {
            if (((1 << i) & rowMask) != 0) {
                for (int j = 0 ; j < m ; ++ j) {
                    if (!posToIdx.containsKey(new Pair(i , j))) {
                        posToIdx.put(new Pair(i , j) , idx ++ );
                    }
                }
            }
        }
        for (int j = 0 ; j < m ; ++ j) {
            if (((1 << j) & colMask) != 0) {
                for (int i = 0 ; i < n ; ++ i) {
                    if (!posToIdx.containsKey(new Pair(i , j))) {
                        posToIdx.put(new Pair(i , j) , idx ++ );
                    }
                }
            }
        }
    }
    static class UnionFind {
        private int [] p , rank , setSize , ZO[];
        private int numSets ;
        public UnionFind(int size)
        {
            p = new int[size] ;
            ZO = new int[size][2];
            rank = new int[size] ;
            setSize = new int[size];
            numSets = size ;
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
                --numSets;
                int x = findSet(i) , y = findSet(j);
                if(rank[x] > rank[y]) {p[y] = x; setSize[x] = setSize[x] + setSize[y];ZO[x][0] += ZO[y][0]; ZO[x][1] += ZO[y][1];}
                else
                {
                    p[x] = y;
                    setSize[y] = setSize[y] + setSize[x];
                    if(rank[x] == rank[y]) ++rank[y];
                    ZO[y][0] += ZO[x][0]; ZO[y][1] += ZO[x][1];
                }
            }
        }

        int numDisjointSets ()
        {
            return numSets;
        }

        int sizeOfSet(int i) {return setSize[findSet(i)];}
    }
    static int solve (String A [] , int rowMask , int colMask) {
        fillMap(rowMask , colMask);
        UnionFind UF = new UnionFind(posToIdx.size());
        for (int i = 0 ; i < n ; ++ i) {
            if (((1 << i) & rowMask) != 0) {
                for (int j = 0 ; j < m ; ++ j) {
                    if (A[i].charAt(j) == '0') {
                        UF.ZO[posToIdx.get(new Pair(i , j))][0] = 1;
                    }
                    else {
                        UF.ZO[posToIdx.get(new Pair(i , j))][1] = 1;
                    }
                }
            }
        }

        for (int j = 0 ; j < m ; ++ j) {
            if (((1 << j) & colMask) != 0) {
                for (int i = 0 ; i < n ; ++ i) {
                    if (A[i].charAt(j) == '0') {
                        UF.ZO[posToIdx.get(new Pair(i , j))][0] = 1;
                    }
                    else {
                        UF.ZO[posToIdx.get(new Pair(i , j))][1] = 1;
                    }
                }
            }
        }
        for (int i = 0 ; i < n ; ++ i) {
            if (((1 << i) & rowMask) != 0) {
                for (int j = 0 ; j < m ; ++ j) {
                    UF.unionSet(posToIdx.get(new Pair(i , j)) , posToIdx.get(new Pair(i , m - j - 1)));
                }
            }
        }
        for (int j = 0 ; j < m ; ++ j) {
            if (((1 << j) & colMask) != 0) {
                for (int i = 0 ; i < n ; ++ i) {
                    UF.unionSet(posToIdx.get(new Pair(i , j)) , posToIdx.get(new Pair(n - i - 1 , j)));
                }
            }
        }
        int cnt = 0 ;
        boolean taken [] = new boolean[posToIdx.size()];
        for (int i = 0 ; i < posToIdx.size() ; ++ i) {
            int parent = UF.findSet(i);
            if (!taken[parent]) {
                taken[parent] = true;
                cnt += Math.min(UF.ZO[parent][0] , UF.ZO[parent][1]);
            }
        }
        return cnt;
    }
    public static int minChange(String[] A, int rowCount, int columnCount) {
        n = A.length ; m = A[0].length();
        int min = 100000000;
        for (int i = 0 ; i < (1 << n) ; ++ i) {
            for (int j = 0 ; j < (1 << m) ; ++ j) {
                int cntR = 0 , cntC = 0;
                for (int k = 0 ; k < n ; ++ k) {
                    if (((1 << k) & i) != 0) cntR ++ ;
                }
                for (int k = 0 ; k < m ; ++ k) {
                    if (((1 << k) & j) != 0) cntC ++ ;
                }
                if (cntR >= rowCount && cntC >= columnCount){
                    min = Math.min(min , solve(A , i , j));
                }
            }
        }
        return min;
    }

    public static void main (String [] args) {
        System.out.println(PalindromeMatrixDiv2.minChange(new String [] {"0000"
                ,"1000"
                ,"1100"
                ,"1110"} , 2 ,2));
    }
}
