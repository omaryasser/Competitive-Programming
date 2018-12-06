#include<bits/stdc++.h>
#define f(i, j, k) for (int i = j; i < k; i++)
using namespace std;

const int n_ = 5e5 + 10;
int n, c;
int a[n_], z[n_];
unordered_map<int,vector<int> > pos;

int cnt_zeros(int l, int r){
	if(l >= n || r < 0)return 0;
	return z[r] - (!l ? 0 : z[l - 1]);	
}

int solve(int type){
	set<pair<int,int> > diff;
	f(i, 0, (int)pos[type].size()){
		int idx = pos[type][i];
		diff.insert(make_pair(i + 1 + cnt_zeros(idx + 1, n - 1), idx));	
//		cout << type << " " << i << " " << (i + 1 + cnt_zeros(idx + 1, n - 1) - cnt_zeros(pos[type][0], idx)) << " " << idx << "\n";
	}
	int best = ((--diff.end())->first) + cnt_zeros(0, pos[type][0] - 1);
//	cout << ((--diff.end())->first) << "\n";
	int add = 0;
	f(i, 0, (int)pos[type].size() - 1){
		add += cnt_zeros(pos[type][i], pos[type][i + 1] - 1);
		while((int)diff.size() && (--diff.end())->second <= pos[type][i])	diff.erase(--diff.end());
//		cout << type << " " << (((--diff.end())->first) + add - i - 1 + cnt_zeros(0, z[pos[type][0]] - 1)) << " " << add << "\n";
 
		best =  max(best, ((--diff.end())->first) + add - i - 1 + cnt_zeros(0, pos[type][0] - 1));
	}
	return best;	
}


int main() {
	ios_base::sync_with_stdio(0); cin.tie(0);
	
 	#ifndef ONLINE_JUDGE
 	freopen("E.in", "r", stdin);
	freopen("E.out", "w", stdout);
	#endif
	
	cin >> n >> c;
	f(i, 0, n)cin >> a[i], a[i] -= c, pos[a[i]].push_back(i);
	f(i, 0, n){
	 	z[i] += !a[i];
	 	if(i) z[i] += z[i - 1];
	}
	
	int res = z[n - 1];
	for(auto p : pos) {
		res = max(res, solve(p.first));	
	}
	cout << res << "\n";
}
