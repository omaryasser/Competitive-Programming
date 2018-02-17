#include<bits/stdc++.h>
#define FOR(x,y) for (int x = 0 ; x < y ; ++ x)
using namespace std;



int N ;
const int MAX = 2 * (1e5  + 7);
set<int> times;
vector<pair<int , int > > avail;
vector<pair<int , int > > queries;
vector<int> workingOn;
int arr[MAX];
bool comp (int a , int b){
	return a <= b;
}
int tree[MAX << 1 << 1];
void build (int node , int s , int e){
	if (s == e) tree[node] = arr[s];
	else {
		int mid = s + e >> 1;
		build(node << 1 , s , mid);
		build(node << 1 | 1 , mid + 1 , e);
		tree[node] = max (tree[node << 1] , tree[node << 1 | 1]);
	}
}

int query (int node , int s , int e , int wS , int wE){
	if (s >= wS && e <= wE) return tree[node];
	if (s > wE || e < wS) return - (int)1e7 ;
	int mid = s + e >> 1;
	return max(query(node << 1 , s , mid , wS , wE) ,
			query(node << 1 | 1 , mid + 1 , e , wS , wE));
}
int main() {
//	freopen("input.txt", "r", stdin);
	ios_base::sync_with_stdio(0); cin.tie(0);


	int NN;
	cin >> NN ;
	FOR (i , NN){
		int x , y ; cin >> x >> y; x -- ; y -- ;
		times.insert(x);
		times.insert(y);
		avail.push_back(make_pair(x , y));
	}
	int Q ; cin >> Q;
	FOR (i , Q){
		int l , r ; cin >> l >> r ; l -- ; r -- ;
		times.insert(l);
		times.insert(r);
		queries.push_back(make_pair(l , r));
	}

	for (set<int> ::iterator it = times.begin() ; it != times.end() ; it ++ ){
		workingOn.push_back(*it);
	}

	N = workingOn.size();
	FOR (i , NN){
		vector <int> ::iterator start = lower_bound(workingOn.begin() , workingOn.end() , avail[i].first);
		vector <int> ::iterator end = lower_bound(workingOn.begin() , workingOn.end() , avail[i].second);
		arr[start - workingOn.begin()] ++ ;
		arr[end - workingOn.begin() + 1] -- ;
	}

	int soFar = 0;
	FOR (i , N){
		soFar += arr[i];
		arr[i] = soFar;
	}
	build (1 , 0 , N - 1);
	FOR (i , Q){
		vector <int> ::iterator start = lower_bound(workingOn.begin() , workingOn.end() , queries[i].first);
		vector <int> ::iterator end = lower_bound(workingOn.begin() , workingOn.end() , queries[i].second);
		cout << query (1 , 0 , N - 1 , start - workingOn.begin() , end - workingOn.begin()) << "\n";
	}
	return 0 ;
}
