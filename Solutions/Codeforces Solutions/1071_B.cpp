#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)

using namespace std;

const int n_ = 2001;
int n, k;
string s[n_];
int mx_a[n_][n_];
int drow[] = {0, 1};
int dcol[] = {1, 0};
int vis[n_][n_];
int visited_number;
pair<int,int> par[n_][n_];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    f(i, 0, n_)f(j, 0, n_)par[i][j] = {-1, -1};
    cin >> n >> k;
    f(i, 0, n) cin >> s[i];
    mx_a[0][0] = s[0][0] == 'a';
    f(i, 0, n)f(j, 0, n){
        if(!i && !j)continue;
        if(i)mx_a[i][j] = mx_a[i - 1][j];
        if(j)mx_a[i][j] = max(mx_a[i][j], mx_a[i][j - 1]);
        mx_a[i][j] += s[i][j] == 'a';
    }

    vector<pair<int,int> > candidates;
    int mx = -1;
    f(i, 0, n)f(j, 0, n)
            if(mx_a[i][j] + k >= i + j + 1){
                if(i + j > mx){
                    candidates.clear();
                    candidates.push_back({i, j});
                    mx = i + j;
                }else if(i + j == mx){
                    candidates.push_back({i, j});
                }
            }
    if(mx == -1)candidates.push_back({0, 0});
    while(candidates[0] != make_pair(n - 1, n - 1)){
        visited_number++;
        int mn = 27;
        for(auto p : candidates)
            f(k, 0, 2){
                int n_row = p.first + drow[k], n_col = p.second + dcol[k];
                if(n_row < n && n_col < n){
                    mn = min(mn, (int)(s[n_row][n_col] - 'a'));
                }
            }
        vector<pair<int,int> > new_candidates;
        for(auto p : candidates)
            f(k, 0, 2){
                int n_row = p.first + drow[k], n_col = p.second + dcol[k];
                if(n_row < n && n_col < n){
                    if((int)(s[n_row][n_col] - 'a') == mn){
                        par[n_row][n_col] = {p.first, p.second};
                        if(vis[n_row][n_col] != visited_number){
                            vis[n_row][n_col] = visited_number;
                            new_candidates.push_back({n_row, n_col});
                        }
                    }
                }
            }
        candidates = new_candidates;
    }

    string res = "";
    int cur_row = n - 1, cur_col = n - 1;
    while(cur_row + cur_col > mx){
        res += string(1, s[cur_row][cur_col]);
        int cur_row_ = par[cur_row][cur_col].first;
        int cur_col_ = par[cur_row][cur_col].second;
        cur_row = cur_row_;
        cur_col = cur_col_;
    }

    while((int)res.length() < 2 * n - 1)res += "a";
    reverse(all(res));
    cout << res << "\n";
}