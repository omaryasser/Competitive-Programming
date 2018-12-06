#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)
#define pb(x) push_back(x)
#define F first
#define S second

using namespace std;

int n;
const int n_ = 2e5 + 10;
int mem[6][n_];
int arr[n_];

int dp(int lst, int idx){
    if(idx == n)return 1;
    int &ret = mem[lst][idx];
    if(ret != -1)return ret;
    ret = 0;
    f(cur, 1, 5 + 1){
        bool can = 0;
        if(arr[idx] > arr[idx - 1]){
            can |= cur > lst;
        }
        if(arr[idx] < arr[idx - 1]){
            can |= cur < lst;
        }
        if(arr[idx] == arr[idx - 1]){
            can |= cur != lst;
        }
        if(can)ret |= dp(cur, idx + 1) == 1;
    }
    return ret;
}
vector<int> res;

void print (int lst, int idx){
    if(idx == n)return;
//    cerr << lst << " " << idx << "\n";
    f(cur, 1, 5 + 1){
        bool can = 0;
        if(arr[idx] > arr[idx - 1]){
            can |= cur > lst;
        }
        if(arr[idx] < arr[idx - 1]){
            can |= cur < lst;
        }
        if(arr[idx] == arr[idx - 1]){
            can |= cur != lst;
        }
        if(can && dp(cur, idx + 1) == 1) {
            res.push_back(cur);
            print(cur, idx + 1);
            break;
        }
    }
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    memset(mem, -1, sizeof mem);
    cin >> n;
    if(n == 1) {
        cout << 1 << "\n";
        return 0;
    }
    f(i, 0, n)cin >> arr[i];
    f(i, 1, 5 + 1)
        if(dp(i, 1) == 1){
        res.push_back(i);
//        cerr << i << "\n";
        print(i, 1);
        break;
    }

//    cerr << (int)res.size() << "\n";
    if((int)res.size() != n)cout << -1 << "\n";
    else {
        f(i, 0, (int)res.size()){
            if(i)cout << " ";
            cout << res[i];
        }
        cout << "\n";
    }
}