#include<bits/stdc++.h>
typedef long long ll;
using namespace std;


string s;
int idx, max_depth;

void dfs (int depth) {
	max_depth = max(depth, max_depth);
	if (s[idx] == 'l') return;
	idx++;
	dfs(depth + 1);
	idx++;
	dfs(depth + 1);
}
int main(){
    ios::sync_with_stdio(0);
    cin.tie(0);

    int t;
    cin >> t;
    while(t--) {
    	cin >> s;
    	idx = 0;
    	max_depth = 0;
    	dfs(0);
    	cout << max_depth << "\n";
    }

    return 0;
}
