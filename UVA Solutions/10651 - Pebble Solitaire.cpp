#include<bits/stdc++.h>
using namespace std;



int memo [(1 << 12) + 3];
int dp (int mask){
    if (memo[mask] != -1) return memo[mask];
    int cnt = 0;
    for (int i = 0 ; i < 12 ; ++i)
        if ((1 << i) & mask) ++ cnt;
    for (int i = 0 ; i < 12 ; ++i)
        if ((1 << i) & mask){
            if (i <= 9 && ((1 << (i + 1)) & mask) && !((1 << (i + 2)) & mask)){
                int tmpMask =((mask ^ (1 << i)) ^ (1 << (i + 1))) | (1 << i + 2);
                cnt = min(cnt , dp(tmpMask));
            }
            if (i >= 2 && ((1 << (i - 1)) & mask) && !((1 << (i - 2)) & mask)){
                int tmpMask =((mask ^ (1 << i)) ^ (1 << (i - 1))) | (1 << i - 2);
                cnt = min(cnt , dp(tmpMask));
            }
        }

        return memo[mask] = cnt;
}
int main (){
//    freopen("input.txt", "r", stdin);
    int T; cin >> T;
    string word;
    memset(memo , - 1 , sizeof(memo));
    while (T -- ){
        cin >> word;
        int mask = 0;
        for (int i = 0 ; i < 12 ; ++i)
            mask |= ((word[i] == 'o') << i);
        cout << dp(mask) << endl;
    }
    return 0;
}
