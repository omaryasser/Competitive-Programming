#include <bits/stdc++.h>
#define f(i, j, n) for(int i = j; i < n; i++)
using namespace std;

#define ll long long

const int n_ = 20002;
int BIT[n_];

void update(int idx, int ad){
    while(idx < n_){
        BIT[idx] += ad;
        idx += idx & -idx;
    }
}

int query_(int idx){
    int sum = 0;
    while(idx > 0){
        sum += BIT[idx];
        idx -= idx & -idx;
    }
    return sum;
}

int query(int wanted){
    int lo = 1, hi = n_ - 1;
    while(lo <= hi){
        int m = lo + hi >> 1;
        if(query_(m) >= wanted){
            hi = m - 1;
        }else{
            lo = m + 1;
        }
    }
    update(lo, -1);
    return lo;
}

int main()
{
    int tc; scanf("%d", &tc);
    f(i, 1, n_) update(i, 1);
    int n;
    while(tc--){
        scanf("%d", &n);
        int rem = n;
        vector<int> res(n + 1);
        int lst = 1;
        f(i, 1, n + 1){
            int pos = (lst - 1 + i + 1) % (rem);
            if(!pos) pos = rem;
            lst = pos;
            res[query(pos)] = i;
            rem--;
        }
        f(i, 1, n + 1){
            if(i > 1) printf(" ");
            update(i, 1);
            printf("%d", res[i]);
        }
        printf("\n");
    }
}
