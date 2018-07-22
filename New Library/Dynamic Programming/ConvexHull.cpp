/*
 * Divide & Conquer: dp[i][j] = min k < j{dp[i - 1][k] + C[k][j]} A[i][j] <= A[i][j + 1]
 * Knuth: dp[i][j] = mini < k < j {dp[i][k] + dp[k][j]} + C[i][j] A[i, j - 1] ≤ A[i, j] ≤ A[i + 1, j] O(n^2)
 * Convex Hull Optimization
 * dp[i] = min(j < i){ dp[j] + a[i] * b[j] } where b[j] >= b[j + 1]
 * 
 * Original Complexity: O(n^2)
 * Optimized Complexity: O(n log n) or O(n) if a[i] <= a[i + 1]
 * 
 * Following operations are used within the DP function
 * 
 * Can be trapped in overflow
 */
class convexhull {
public:
    vector<ll>A,B,starts,ends;
    int ptr,len;
    ll INF=(ll)1e10;
    convexhull (int n) {
        A.assign(n,0);
        B.assign(n,0);
        starts.assign(n,0);
        ends.assign(n,0);
        ptr=0,len=0;
    }

    void addLine(ll a,ll b){// lines are sorted with their slopes decreasing
        while(len>=2&&(A[len-1]-A[len-2])*(B[len-1]-b)<=(A[len-1]-a)*(B[len-1]-B[len-2]))--len;
        A[len]=a;
        B[len]=b;
        if(len==0)starts[len]=-INF;
        else starts[len]=(B[len]-B[len-1]+A[len-1]-A[len]-1)/(A[len-1]-A[len]);
        ++len;
    }

    ll queryPointer(ll x){// O(n), queries must be performed in increasing order
        ptr=min(ptr,len-1);
        while(ptr<len-1&&A[ptr+1]*x+B[ptr+1]<=A[ptr]*x+B[ptr])
            ++ptr;
        return A[ptr]*x+B[ptr];
    }

    void addLine2(ll a,ll b){ // lines are sorted with their slopes increasing
        while(len>=2&&(A[len-1]-A[len-2])*(B[len-1]-b)>=(A[len-1]-a)*(B[len-1]-B[len-2]))--len;
        A[len]=a;
        B[len]=b;
        if(len==0)ends[0]=INF;
        else ends[len]=(B[len]-B[len-1])/(A[len-1]-A[len]);
        ++len;
    }


    ll queryBS(ll x){ // use it if you filled starts[]
        int ans=0;
        int lo=1,hi=len-1;
        while(lo<=hi){
            int mid=lo+hi>>1;
            if(starts[mid]<=x){
                ans=mid;
                lo=mid+1;
            } else hi=mid-1;
        }
        return x*A[ans]+B[ans];
    }

    ll queryBS2(ll x){ // use it if you filled ends[]
        int ans=0;
        int lo=1,hi=len-1;
        while(lo<=hi){
            int mid=lo+hi>>1;
            if(ends[mid]>=x){
                ans=mid;
                lo=mid+1;
            } else hi=mid-1;
        }
        return x*A[ans]+B[ans];
    }
};
