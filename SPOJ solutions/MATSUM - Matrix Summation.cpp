#include <bits/stdc++.h>
#define f(i, j, n) for(int i = j; i < n; i++)
using namespace std;

#define ll long long

const int n_ = 1029;
ll BIT[n_][n_];
ll arr[n_][n_];

void update(int x, int y, ll ad){
    x++, y++;
    int ty = y;
    while(x < n_){
        while(y < n_){
            BIT[x][y] += ad;
//            cerr << ad << " " << x << " " << y << "\n";
            y += y & -y;
        }
        y = ty;
        x += x & -x;
    }
}

ll query(int x, int y){
    x++, y++;
    ll res = 0;
    int ty = y;
    while(x > 0){
        while(y > 0){
            res += BIT[x][y];
//            cerr << " " << x << " " << y << "\n";
            y -= y & -y;
        }
        y = ty;
        x -= x & -x;
    }
    return res;
}

ll query(int x1, int y1, int x2, int y2){
    ll res = query(x2, y2);
    if(y1) res -= query(x2, y1 - 1);
    if(x1) res -= query(x1 - 1, y2);
    if(x1 && y1) res += query(x1 - 1, y1 - 1);
    return res;
}

int main()
{
    int tc; scanf("%d", &tc);
    while(tc--){
        int n; scanf("%d", &n);
        // TODO: reinitialize
        set<pair<int,int> > pos;
        while(1){
            char s[5]; scanf("%s", s);
            ll x1, y1, x2, y2; scanf("%lld %lld %lld", &x1, &y1, &x2);
            if(s[0] == 'E')break;
            else if(s[1] == 'E'){
                update(x1, y1, x2 - arr[x1][y1]);
                arr[x1][y1] = x2;
                pos.insert({x1, y1});
            }else{
                scanf("%lld", &y2);
                printf("%lld\n", query(x1, y1, x2, y2));
            }
        }
        for(auto p : pos)update(p.first, p.second, -arr[p.first][p.second]), arr[p.first][p.second] = 0;
        printf("\n");
    }
}
