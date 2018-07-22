import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Max_Range_2_numbers_Sum_Queries {


    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int N = sc.nextInt();
        int arr [] = new int[N];
        for(int i = 0 ; i < N ; ++i) arr[i] = sc.nextInt();
        SegmentTree segmentTree = new SegmentTree(arr);
        int Q = sc.nextInt();
        while(Q -- > 0)
        {
            char c = sc.next().charAt(0);
            if(c == 'Q')
            {
                Pair ans = segmentTree.query(sc.nextInt() -1 , sc.nextInt() -1);
                System.out.println(ans.max1 + ans.max2);
            }
            else segmentTree.update(sc.nextInt() - 1 , sc.nextInt());
        }
        out.flush();
        out.close();
    }


    static class Pair{
        int max1 , max2 ;
        Pair(int m1 , int m2) {max1 = m1 ; max2 = m2;}
    }

    static public class SegmentTree {
        Pair[] tree; int []  A ;// st stores the index of the minimum number in the corresponding range
        int N;

        SegmentTree(int[] in) {
            A = in.clone();
            N = A.length;
            tree = new Pair[(N << 1) << 2];
            build(1, 0, N - 1);
        }

        void build(int node, int start, int end)
        {
            if(start == end)
            {
                // Leaf node will have a single element
                tree[node] = new Pair(A[start] , -1);
            }
            else
            {
                int mid = (start + end) >> 1;
                // Recurse on the left child
                build(left(node), start, mid);
                // Recurse on the right child
                build(right(node), mid+1, end);
                // Internal node will have the sum of both of its children
                int arr [] = {tree[left(node)].max1 , tree[left(node)].max2 , tree[right(node)].max1 , tree[right(node)].max2};
                Arrays.sort(arr);
                tree[node] = new Pair(arr[2] , arr[3]);
            }
        }


        void update (int idx , int newVal) {update(1 , 0 , N-1 , idx , newVal);}
        void update(int node, int start, int end, int idx ,  int val)
        {
           // System.out.println(start + " "+end);
            if(start == idx && end == idx)
            {
                tree[node] = new Pair(val , -1);
                return ;
            }

            int mid = (start + end) >> 1;
            if(idx <= mid)
                update(left(node), start, mid, idx , val);
            else
                update(right(node), mid + 1, end, idx, val);

            int arr [] = {tree[left(node)].max1 , tree[left(node)].max2 , tree[right(node)].max1 , tree[right(node)].max2};
            Arrays.sort(arr);
            tree[node] = new Pair(arr[2] , arr[3]);
        }
        Pair query(int l , int r) {return query(1,0 , N-1 , l , r);}
        Pair query(int node, int start, int end, int l, int r)
        {
            if(start > r || end < l)
                return new Pair(-1,-1);         // Out of range

            if(start >= l && end <= r)             // Current segment is totally within range [l, r]
                return tree[node];

            int mid = (start + end) / 2;
            Pair left = query(left(node), start, mid, l, r);         // Query left child
            Pair right = query(right(node), mid + 1, end, l, r); // Query right child

            int arr [] = {left.max1 , left.max2 , right.max1 , right.max2};
            Arrays.sort(arr);

            return  new Pair(arr[2] , arr[3]);


        }

        static int left (int pos ) {return pos << 1;}
        static int right (int pos ) {return (pos << 1)+1;}


    }
}
