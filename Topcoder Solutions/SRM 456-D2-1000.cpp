#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

class CutSticks {
	public :
	bool can(double num , vector<int> sticks , int C , int K) {
		ll cnt = 0 , present = 0;
		for (int i = 0 ; i < (int)sticks.size() ; ++ i)
			cnt += (ll)floor(sticks[i] / num) , present += sticks[i] >= num;
		return cnt >= K && present + C >= K;
	}
	double maxKth(vector <int> sticks, int C, int K) {
		double lo = 0 , hi = 1000000000;
		double ans = 0 ;
		for (int c = 0 ; c <= 100 ; ++ c) {
			double mid = (hi + lo) / 2;
			if (can(mid , sticks , C , K)) ans = mid , lo = mid;
			else hi = mid;
		}
		return ans;
	}
};
int main(){
	CutSticks c;
	int arr [] = {1000000000, 1000000000, 1};
	vector<int> v(arr , arr + sizeof arr / sizeof arr[0]);
//	printf("%d\n" , v[1]);
//	printf("%d" , c.can(8 , v , 1 , 1));
	printf("%f\n" , c.maxKth(v , 2 , 5));
//	printf("%d\n" , c.can(0.931323 , v , 2 , 5));
	return 0;
}
