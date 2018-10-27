#include <bits/stdc++.h>
using namespace std;

int curX , curY;
int V;
vector<int>X , Y;
vector<vector<int> >adjList;

typedef long long ll;
ll sq(int x) {return (ll)x * (ll)x;}
ll dist (int x , int y , int x2 , int y2) {
	return sq(x - x2) + sq(y - y2);
}
bool sorter(int idx1 , int idx2) {
	if(dist(X[idx1] , Y[idx1] , curX , curY) != dist(X[idx2] , Y[idx2] , curX , curY)) {
		return dist(X[idx1] , Y[idx1] , curX , curY) < dist(X[idx2] , Y[idx2] , curX , curY);
	}
	if(X[idx1] != X[idx2]) {
		return X[idx1] < X[idx2];
	}
	return Y[idx1] < Y[idx2];
}
int teleportCount;
set<ll> possibleSubsets;

string toB(ll mask) {
	string res = "";
	for (int i = 0 ; i < 10 ; ++ i) if (mask & (1 << i)) res += "1" ;else res += "0";
	return res;
}
set<ll> mp;
class TeleportsNetwork {
public :
	void fill (int idx , ll mask , int cnt) {
		if(mp.find(mask) != mp.end()) return;
		if(cnt == teleportCount){
			possibleSubsets.insert(mask);
			return ;
		}
		mp.insert(mask);
		if(idx == V) return ;
		fill(idx + 1 , mask , cnt);
		for (int i = idx ; i < V ; ++ i) {
			fill(idx + 1 , mask | (1LL << i) , cnt + 1);
		}
	}
	int getInconvenience(ll mask) {
		int dist[V];
		int INF = 1e8;
		queue<int>q;
		for (int i = 0 ; i < V ; ++ i) {
			if(!i || ((1LL << i) & mask) != 0) {
				dist[i] = 0;
				q.push(i);
			}
			else dist[i] = INF;
		}
		while(!q.empty()) {
			int cur = q.front() ; q.pop();
			for (auto &it : adjList[cur]) {
				if(dist[it] > dist[cur] + 1) {
					dist[it] = dist[cur] + 1;
					q.push(it);
				}
			}
		}
		int cnt = 0 ;
		for (int i = 0 ; i < V ; ++ i) cnt = max(dist[i] , cnt);
		return cnt;
	}
	int distribute(int tC, vector <int> c1, vector <int> c2) {
		teleportCount = tC;
		V = (int)c1.size();
		for (int i = 0 ; i < V ; ++ i) {
			X.push_back(c1[i]);
			Y.push_back(c2[i]);
		}
		adjList.assign(V , vector<int>());
		for (int i = 1 ; i < V ; ++ i) {
			ll distToCap = dist(X[i] , Y[i] , X[0] , Y[0]);
			curX = X[i] , curY = Y[i];
			vector<int> closest;
			for (int j = 0 ; j < V ; ++ j) {
				if (i != j) {
					if (dist(X[j] , Y[j] , X[0] , Y[0]) < distToCap) {
						closest.push_back(j);
					}
				}
			}
			sort(closest.begin() , closest.end() , sorter);
			adjList[i].push_back(closest[0]);
			adjList[closest[0]].push_back(i);
		}
		fill(0,0,0);
		int best = 1e9;
		for (auto &it : possibleSubsets) {
			best = min(best,getInconvenience(it));
		}
		return best;
	}
};
int main() {
	TeleportsNetwork t;
	vector<int> aa , bb;
	for (int i = 0 ; i < 50 ; ++ i) aa.push_back(i) , bb.push_back(50 + i);
	printf("%d\n" , t.distribute(4, aa , bb));
	return 0;
}
