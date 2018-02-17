
#include<bits/stdc++.h>
using namespace std;

class ColorfulRoad {
public :
	int inf = 1e9;
	int memo[16];
	int n;
	int arr[16];
	int dp (int idx) {
		if(idx == n - 1) {
			return 0;
		}

		int &ret = memo[idx];
		if (ret != -1) {
			return ret;
		}

		ret = inf;
		for(int i = idx + 1; i < n; i++) {
			if(arr[i] == (arr[idx] + 1) % 3) {
				ret = min (ret, (i - idx) * (i - idx) + dp(i));
			}
		}
		return ret;
	}
	int getMin(string road) {
		n = (int)road.length();
		for (int i = 0; i < n; i++) {
			if(road[i] == 'R') {
				arr[i] = 0;
			} else if (road[i] == 'G') {
				arr[i] = 1;
			} else {
				arr[i] = 2;
			}
		}
		memset(memo, -1, sizeof memo);
		int res = dp(0);
		if (res < inf) {
			return res;
		} else {
			return -1;
		}
	}
};


