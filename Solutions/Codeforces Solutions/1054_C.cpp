#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)

using namespace std;


const int n_ = 1001;
vector<int> adj[n_];
int n;
unordered_set<int> adj_[n_];
stack<int> s;
bool vis[n_];

void dfs(int u){
    vis[u] = 1;
    for(int v : adj[u])
        if(!vis[v])
            dfs(v);
    s.push(u);
}

int res[n_];


bool check(vector<int> l, vector<int> r){
    f(i, 0, n){
        int cnt_l = 0;
        f(j, 0, i){
            cnt_l += res[j] > res[i];
        }
        int cnt_r = 0;
        f(j, i + 1, n){
            cnt_r += res[j] > res[i];
        }
        if(cnt_l != l[i] || cnt_r != r[i])return false;
    }
    return true;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin >> n;
    vector<int> l(n), r(n), l_(n), r_(n);
    f(i, 0, n)cin >> l[i], l_[i] = l[i];
    f(i, 0, n)cin >> r[i], r_[i] = r[i];

    int idx = n;
    int need = n;
    while(need){
        vector<int> found;
        f(j, 0, n){
            if(!l[j] && !r[j] && !vis[j]){
                found.push_back(j);
                vis[j] = 1;
            }
        }
        if((int)found.size() == 0){
            cout << "NO\n";
            return 0;
        }
        for(int x : found)res[x] = idx;
        idx--;
        need -= (int)found.size();

        int cnt_r = (int)found.size();
        int idx_in_found = 0;
        f(j, 0, n){
            if(idx_in_found < (int)found.size() && found[idx_in_found] == j){
                cnt_r--;
                idx_in_found++;
            }else if(!vis[j]){
                r[j] -= cnt_r;
            }
        }

        int cnt_l = (int)found.size();
        idx_in_found = cnt_l - 1;
        for(int j = n - 1; j >= 0; j--){
            if(idx_in_found >= 0 && found[idx_in_found] == j){
                cnt_l--;
                idx_in_found--;
            }else if(!vis[j]){
                l[j] -= cnt_l;
            }
        }
    }

    if(check(l_, r_)){
        cout << "YES\n";
        f(i, 0, n){
            if(i)cout << " ";
            cout << res[i];
        }
        cout << "\n";
    }else cout << "NO\n";
}