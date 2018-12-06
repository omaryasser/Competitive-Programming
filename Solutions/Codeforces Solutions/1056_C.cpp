#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)
#define pb(x) push_back(x)
#define F first
#define S second

using namespace std;

void print(int idx){
    cout << idx + 1 << "\n";
    cout.flush();
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n, m;
    cin >> n >> m;
    vector<int> powers(n << 1);
    f(i, 0, n << 1)cin >> powers[i];
    vector<bool> in_pair(n << 1, 0);
    vector<int> free;
    vector<pair<int,int> > pairs;
    while(m--){
        int a, b;
        cin >> a >> b;
        a--, b--;
        in_pair[a] = in_pair[b] = 1;
        pairs.push_back({a, b});
    }
    f(i, 0, n << 1)
        if(!in_pair[i])
            free.push_back(i);

    vector<bool> taken(n << 1, 0);

    int turn;
    cin >> turn;
    turn = turn == 2 ? 0 : turn;
    int obliged = -1;
    f(i, 0, n << 1){
        if(turn == 1){
            if(obliged != -1){
                print(obliged);
                taken[obliged] = true;
                obliged = -1;
            }else {
                pair<int,int> best_in_pair = {-1, -1};
                for(auto p : pairs) {

                    if (!taken[p.first]) {
                        best_in_pair = max(best_in_pair, {powers[p.first], p.first});
                        best_in_pair = max(best_in_pair, {powers[p.second], p.second});
                    }

                }

                if(best_in_pair.first != -1) {
                    print(best_in_pair.second);
                    taken[best_in_pair.second] = true;
                }
                else {
                    pair<int,int> bst = {-1, -1};
                    for(int f : free){
                        if(!taken[f])
                            bst = max(bst, {powers[f], f});
                    }
                    print(bst.second);
                    taken[bst.second] = true;
                }
            }
        } else {
            int x;
            cin >> x;
            x--;
            for(auto p : pairs)
                if(x == p.first || x == p.second)
                    if(!taken[p.first] && !taken[p.second]){
                        taken[p.first] = taken[p.second] = true;
                        obliged = (x == p.first ? p.second : p.first);
                    }
            taken[x] = true;
        }
        turn ^= 1;
    }
}