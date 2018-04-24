#include<bits/stdc++.h>
#define FOR(x,y) for (int x = 0 ; x < y ; ++ x)
#define FILL(x,y) memset (x , y , sizeof(x));
using namespace std;

typedef long long ll;

const int MAX = 2*(1e5 + 7); // NOTE : The Problem limits was wrong , 1 <= N <= 2 * 1e5
ll tree[MAX << 1 << 1];
ll lazy[MAX << 1 << 1];
int T;
int N;
int C;

void propagate (int node , int s , int e){
	if (s == e){
		tree[node] += lazy[node];
	}
	else {
		tree[node] += (e - s + 1) * lazy[node];
		lazy[node << 1] += lazy[node];
		lazy[node << 1 | 1] += lazy[node];
	}
	lazy[node] = 0;
}

void update (int node , int s , int e , int wS , int wE , ll val){
	propagate (node , s , e);
	if (s >= wS && e <= wE){
		tree[node] += (e - s + 1) * val;
		lazy[node << 1] += val;
		lazy[node << 1 | 1] += val;
	}
	else if (s > wE || e < wS) return ;
	else {
		int mid = s + e >> 1;
		update(node << 1 , s , mid , wS , wE , val);
		update(node << 1 | 1 , mid + 1 , e , wS , wE , val);
		tree[node] = tree[node << 1] + tree[node << 1 | 1];
	}
}

ll query (int node , int s , int e , int wS , int wE){
	propagate(node , s , e);
	if (s >= wS && e <= wE) return tree[node];
	else if (s > wE || e < wS) return 0;
	int mid = s + e >> 1 ;
	return query (node << 1 , s , mid , wS , wE) +
			query(node << 1 | 1 , mid + 1 , e , wS , wE);
}
int main() {
//	freopen("input.txt", "r", stdin);
	ios_base::sync_with_stdio(0); cin.tie(0);

	cin >> T;
	while (T -- ){
		FILL (tree , 0);
		FILL (lazy , 0);
		cin >> N >> C;
		while (C -- ){
			int cmdd ; cin >> cmdd;
			if (cmdd == 0){
				int l , r , val ; cin >> l >> r >> val ; l -- ; r --;
				update (1 , 0 , N - 1 , l , r , val);
			}
			else {
				int l , r ; cin >> l >> r ; l -- ; r -- ;
				cout << query (1 , 0 , N - 1 , l , r) << "\n";
			}
		}
	}

	return 0 ;
}
