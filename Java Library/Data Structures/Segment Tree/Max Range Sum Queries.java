import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;


public class MaxRangeSum {
    static int prefix [] ;
    public static void main(String [] args) throws  Exception
    {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        StringBuilder sb = new StringBuilder();
        int N =  sc.nextInt();
        int arr [] = new int[N];
        prefix = new int[N];
        for(int i = 0 ; i < N  ; ++i) {arr[i] = sc.nextInt(); prefix[i] = i==0 ? arr[i] : arr[i] + prefix[i-1];}
        SegmentTree segmentTree = new SegmentTree(arr);
        int M = sc.nextInt();
        while(M -- >0) out.println(segmentTree.queryRange(sc.nextInt()-1 , sc.nextInt()-1).max);
        out.flush();
        out.close();
    }





    static class Node {
        int maxLeft , max , maxRight;
        Node(int l , int m , int r)
        {
            maxLeft = l;
            max = m;
            maxRight = r;
        }
        Node (){}
    }




    static class SegmentTree {
        Node[] tree; int [] A ;// st stores the index of the minimum number in the corresponding range
        int N;

        SegmentTree(int[] in) {
            A = in.clone();
            N = A.length;
            tree = new Node[(N << 1) +4];
            build(1, 0, N - 1);
        }

        void build(int node, int start, int end)
        {
            if(start == end)
            {
                // Leaf node will have a single element
                tree[node] = new Node(A[start] , A[start] , A[start]);
            }
            else
            {
                int mid = (start + end) >> 1;
                // Recurse on the left child
                build(left(node), start, mid);
                // Recurse on the right child
                build(right(node), mid+1, end);
                // Internal node will have the sum of both of its children
                Node leftChild = tree[left(node)];
                Node rightChild = tree[right(node)];

                int maxLeft = Math.max(leftChild.maxLeft , prefix[(start + end)>> 1] - (start-1 < 0 ? 0 : prefix[start - 1]) + rightChild.maxLeft);
                int maxRight = Math.max(rightChild.maxRight , prefix[end] - prefix[(start + end) /2] + leftChild.maxRight);
                int max = Math.max(leftChild.max , Math.max(rightChild.max , leftChild.maxRight + rightChild.maxLeft));
                tree[node] = new Node(maxLeft , max , maxRight);

            }
        }




        Node queryRange(int l , int r) {return queryRange(1 , 0 , N-1 , l , r);}
        Node queryRange(int node, int start, int end, int l, int r)
        {
            if ( start == l && end == r ) {
                return tree[ node ];
            }
            if ( r <= ( start + end ) / 2 ) {
                return queryRange( node * 2, start, ( start + end ) / 2, l, r );
            }
            if ( l > ( start + end ) / 2 ) {
                return queryRange(node * 2 + 1, (start + end) / 2 + 1, end, l, r);
            }

            int mid = (start + end) >> 1;
            Node leftChild = queryRange(left(node), start, mid, l, (start+end)/2);         // Query left child
            Node rightChild = queryRange(right(node), mid + 1, end, (start+end)/2 +1, r); // Query right child
            return new Node(Math.max(leftChild.maxLeft , prefix[(start + end) /2] - (l-1 < 0 ? 0 : prefix[l - 1]) + rightChild.maxLeft),
                             Math.max(rightChild.maxRight , prefix[end] - prefix[(start + end) /2] + leftChild.maxRight),
                                Math.max(leftChild.max , Math.max(rightChild.max , leftChild.maxRight + rightChild.maxLeft)));
        }

        static int left (int pos ) {return pos << 1;}
        static int right (int pos ) {return (pos << 1)+1;}


    }






}
