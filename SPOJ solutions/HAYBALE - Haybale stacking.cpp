#include<bits/stdc++.h>

using namespace std;

const int MAX = 1e6 + 1;
int N ;
int K ;
int arr [MAX] ;

int main() {
//	freopen("input.txt", "r", stdin);
	ios_base::sync_with_stdio(0); cin.tie(0);

	cin >> N >> K;
	while (K -- ){
		int l , r ; cin >> l >> r ; l -- ; r -- ;
		arr[l] ++ ;
		arr[r + 1] -- ;
	}

	int soFar = 0;
	for (int i = 0 ; i < N ; ++ i){
		soFar += arr[i];
		arr[i] = soFar;
	}

	sort (arr , arr + N);
	cout << arr[N / 2];
	return 0 ;
}
