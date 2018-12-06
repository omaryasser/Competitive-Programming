#include<bits/stdc++.h>
#define BUG cerr << "BUG\n";
#define f(i, j, k) for(int i = j; i < k; i++)

using namespace std;

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);

	#ifndef ONLINE_JUDGE
	freopen("B.in", "r", stdin);
	freopen("B.out", "w", stdout);
	#endif

	int n;
	cin >> n;
	
	string s; 
	cin >> s;
	int first = 0, last = n - 1;
	while(first < n && s[first] == 'S')first++;
	while(last >=0 && s[last] == 'S')last--;
	
	int lo = 1, hi = n, best = 0;
	while(lo <= hi) {
	 	int mid = lo + hi >> 1;
		int cnt_good = 0, cnt_bad = 0;
		f(i, 0, mid)cnt_good += s[i] == 'G';
		bool ok = cnt_good + (last > mid - 1) >= mid;
		f(lst, mid, n){
		 	cnt_good -= s[lst - mid] == 'G';
			cnt_good += s[lst] == 'G';
			ok |= cnt_good + (last > lst || first < lst - mid) >= mid;
		}		
		if(ok){
		 	best = mid;
			lo = mid + 1;
		}            
		else hi = mid - 1;
	}
	cout << best << "\n";
	return 0;
}
