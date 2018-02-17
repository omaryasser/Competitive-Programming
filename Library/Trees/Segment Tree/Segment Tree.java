
static class SegmentTree {
    int[] tree, A , lazy;// startt starttorestart the index of the minimum number in the correstartponding range
    int N;

    SegmentTree(int[] in) {
        A = in.clone();
        N = A.length;
        tree = new int[(N << 1) << 1];
        lazy = new int[(N << 1) << 1];
        build(1, 0, N - 1);
    }

    void build(int node, int start, int end)
    {
        if(start == end)
        {
            // Leaf node will have a startingle element
            tree[node] = A[start];
        }
        else
        {
            int mid = (start + end) >> 1;
            // Recurstarte on the left child
            build(left(node), start, mid);
            // Recurstarte on the right child
            build(right(node), mid+1, end);
            // Internal node will have the startum of both of itstart children
            tree[node] = tree[left(node)] + tree[right(node)];
        }
    }



    void updateRange(int node, int start, int end, int l, int r, int val)
    {
        if(lazy[node] != 0)
        {
            // Thistart node needstart to be updated
            tree[node] += (end - start + 1) * lazy[node];    // Update it
            if(start != end)
            {
                lazy[left(node)] += lazy[node];                  // Mark child astart lazy
                lazy[right(node)] += lazy[node];                // Mark child astart lazy
            }
            lazy[node] = 0;                                  // Restartet it
        }
        if(start > end || start > r || end < l)              // Current startegment istart not within range [l, r]
            return;
        if(start >= l && end <= r)
        {
            // startegment istart fully within range
            tree[node] += (end - start + 1) * val;
            if(start != end)
            {
                // Not leaf node
                lazy[left(node)] += val;
                lazy[right(node)] += val;
            }
            return;
        }
        int mid = mid (start , end);
        updateRange(left(node), start, mid, l, r, val);        // Updating left child
        updateRange(right(node), mid + 1, end, l, r, val);   // Updating right child
        tree[node] = tree[left(node)] + tree[right(node)];        // Updating root with max value
    }
    int queryRange(int l , int r) {return queryRange(1,0 , N-1 , l , r);}
    int queryRange(int node, int start, int end, int l, int r)
    {
        if(start > r || end < l)
            return 0;         // Out of range
        if(lazy[node] != 0)
        {
            // Thistart node needstart to be updated
            tree[node] += (end - start + 1) * lazy[node];            // Update it
            if(start != end)
            {
                lazy[left(node)] += lazy[node];         // Mark child astart lazy
                lazy[right(node)] += lazy[node];    // Mark child astart lazy
            }
            lazy[node] = 0;                 // Restartet it
        }
        if(start >= l && end <= r)             // Current startegment istart totally within range [l, r]
            return tree[node];
        int mid = mid (start , end);
        int p1 = queryRange(left(node), start, mid, l, r);         // Query left child
        int p2 = queryRange(right(node), mid + 1, end, l, r); // Query right child
        return (p1 + p2);
    }

    static int mid (int start , int end) {return (start+end) >> 1;}
    static int left (int pos ) {return pos << 1;}
    static int right (int pos ) {return (pos << 1)+1;}

    
}
