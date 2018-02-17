#include <bits/stdc++.h>

using namespace std;

bool comp(pair<int,int> p1, pair<int,int> p2){
	return p1.second < p2.second;
}

int main() {
	while(1){
		int n;
		scanf("%d", &n);
		if(!n) break;

		pair<int,int> arr[n];
		for(int i = 0; i < n; i++){
			int x;
			scanf("%d", &x);
			arr[i] = make_pair(x, i);
		}

		sort(arr, arr + n);
		reverse(arr, arr + n);

		if(arr[0].first <= 0) printf("%d\n", arr[0]);
		else{
			vector<pair<int,int> > res;
			for(int i = 0; i < n; i++)
				if(arr[i].first > 0)
					res.push_back(arr[i]);

			sort(res.begin(), res.end(), comp);
			for(int i = 0; i < (int)res.size(); i++)
				if(!i) printf("%d", res[i].first);
				else printf(" %d", res[i].first);
			printf("\n");
		}
	}
}

