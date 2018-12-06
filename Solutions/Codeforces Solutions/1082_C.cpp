#include<bits/stdc++.h>
#define BUG cerr << "BUG\n";
#define f(i, j, k) for(int i = j; i < k; i++)
#define all(x) x.begin(), x.end()

using namespace std;

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	#ifndef ONLINE_JUDGE
	freopen("C.in", "r", stdin);
	freopen("C.out", "w", stdout);
	#endif

	int n, m;
	cin >> n >> m;
	unordered_map<int,vector<int> > mp;
	f(i, 0, n){
		int s, r;
		cin >> s >> r;
		mp[s].push_back(r);
	}

	int mx_len = 0;
	for(auto &p : mp)sort(all(p.second)), reverse(all(p.second)), mx_len = max(mx_len, (int)p.second.size());
	for(auto &p : mp)
		f(i, 1, (int)p.second.size()){                   
			p.second[i] += p.second[i - 1];               
		}

        

	int mx = 0;
			
	f(i, 0, mx_len){
		vector<int> del;
		vector<int> found;
		for(auto p : mp){
			if(i < (int)p.second.size()){
				found.push_back(p.second[i]);
			}else {
				del.push_back(p.first);
			}
		}
		for(int d : del)mp.erase(d);
		int cur = 0;
		for(int f : found){
			cur += max(0, f);
		}
		mx = max(mx, cur);
	}	
	cout << mx << "\n";

	return 0;
}
