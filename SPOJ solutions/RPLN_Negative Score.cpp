#include<bits/stdc++.h>

#define PI 3.14159265358979323846
#define fill(x,y) memset (x , y , sizeof(x))
#define lp(x , y) for (int x = 0 ; x < y ; ++ x)
using namespace std;

int T;
int N , Q;
int arr [100001];
int tree [(100001 << 1) << 1];

void build (int node , int start , int end){
	if (start == end) tree[node] = arr[start];
	else {
		int mid = start + end >> 1;
		build(node << 1 , start , mid);
		build(node << 1 | 1 , mid + 1 , end);
		tree[node] = min (tree[node << 1] , tree[node << 1 | 1]);
	}
}

inline int query (int node , int start , int end , int l , int r){
	if (start >= l && end <= r) return tree[node];
	else if (end < l || start > r) return 1e9 + 1;
	else {
		int mid = start + end >> 1;
		int left = query(node << 1 , start , mid , l , r);
		int right = query(node << 1 | 1 , mid + 1 , end , l , r);
		return min (left , right);
	}
}
int main() {
	ios_base::sync_with_stdio(0);cin.tie(0);
//	freopen("input.txt", "r", stdin);


	cin >> T;
	lp(i , T){
		cout << "Scenario #" << i + 1 << ":\n" ;
		cin >> N >> Q;
		lp (i , N) cin >> arr[i];
		build(1 , 0 , N - 1);
		lp (i , Q){
			int l , r; cin >> l >> r; l -- ; r -- ;
			cout << query (1 , 0 , N - 1 , l , r) << "\n";
		}
	}
	return 0;
}
