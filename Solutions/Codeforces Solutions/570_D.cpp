#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int n, m;
const int MAX = 500002;
char s[MAX];
vector<int> tree[MAX];
int depth[MAX], tin[MAX], tout[MAX];
int tim;
vector<pair<int, vector<int> > > lvl[MAX];

void dfs(int u = 0) {
    tin[u] = tim++;
    vector<int> cnt=lvl[depth[u]].size()?lvl[depth[u]][lvl[depth[u]].size()-1].second:vector<int>(26, 0);
    cnt[s[u] - 'a']++;
    lvl[depth[u]].push_back({tin[u], cnt});
    for(int i = 0; i < tree[u].size(); i++) {
        int v=tree[u][i];
        depth[v] = depth[u] + 1;
        dfs(v);
    }
    tout[u] = tim - 1;
}

int main() {
    scanf("%d%d", &n, &m);
    FOR(i, 1, n) {
        int p;
        scanf("%d", &p);
        p--;
        tree[p].push_back(i);
    }
    FOR(i,0,MAX)lvl[i]=vector<pair<int,vector<int> > >();
    scanf("%s", s);
    dfs();
    while (m--) {
        int v, h;
        scanf("%d%d", &v, &h);
        v--;
        h--;
        vector<int>left(26,0),right(26,0);
        int lo=0,hi=(int)lvl[h].size()-1,best=-1;
        while(lo<=hi){
            int mid=(lo+hi)>>1;
            if(lvl[h][mid].first<tin[v]){
                best=mid;
                lo=mid+1;
            }else hi=mid-1;
        }
        if(best!=-1)left=lvl[h][best].second;
        right=left;
        lo=0,hi=(int)lvl[h].size()-1,best=-1;
        while(lo<=hi){
            int mid=(lo+hi)>>1;
            if(lvl[h][mid].first<=tout[v]){
                best=mid;
                lo=mid+1;
            }else hi=mid-1;
        }
        if(best!=-1)right=lvl[h][best].second;
        int odd=0;
        FOR(i,0,26)odd+=(right[i]-left[i])&1;
        if(odd<=1)printf("%s\n","Yes");
        else printf("%s\n","No");
    }
}