#include <bits/stdc++.h>
#define pb(x) push_back(x)
using namespace std;

typedef long long ll;

vector<vector<int> > adjList;
vector<int>CC_IDX;
vector<bool>visited;
int UNVISITED = -1 , res , f;
void dfs (int u , int idx){
	CC_IDX[u] = idx;
	for (auto &v : adjList[u]) if (CC_IDX[v] == UNVISITED) dfs(v , idx);
}

void dfs2(int u , int node , int p = - 1) {
	if (u == node) f = 1;
	visited[u] = 1;
	for (auto &it : adjList[u]) if (!visited[it] && it != p) {
		dfs2(it , node , u);
	}
}
void check (int CCidx){
	bool good = 1 ;
	for (int i = 0; i < (int)adjList.size(); ++ i) {
		if(CC_IDX[i] == CCidx) {
			int all = 0;
			for (auto &it : adjList[i]){
				f = 0;
				visited.assign((int)adjList.size() , false);
				dfs2(it , i , i);
				all += f;
			}
			good &= (all <= 2);
		}
	}
	if (good) res ++ ;
}
class CactusCount {
	public :
	int countCacti(int n, vector <string> edges){
		adjList.assign(n , vector<int>());
		CC_IDX.assign(n , UNVISITED);
		string edgess = "" ;
		for (int i = 0; i < (int)edges.size(); ++ i){
			edgess += edges[i];
		}
		istringstream iss(edgess);
		int x , y;
		char c;
		while(iss >> x) {
			iss >> y;
			x -- ; y -- ;
			if(iss >> c);
			adjList[x].pb(y);
			adjList[y].pb(x);
		}
		int idx = 0 ;
		for (int i = 0; i < n; ++ i) if(CC_IDX[i] == UNVISITED) {
			dfs(i , idx);
			idx ++ ;
		}
		res = 0 ;
		set<int> CC_Finished;
		for (int i = 0; i < n; ++ i) {
			if(CC_Finished.find(CC_IDX[i]) == CC_Finished.end()) {
				CC_Finished.insert(CC_IDX[i]);
				check(CC_IDX[i]);
			}
		}
		return res;

	}
};
int main(){
	CactusCount  c;
	string arr[] = {"1 2,2 3,3 4,4 5,5 3,1 3,6 7,7 8,6 8,8 9,9 1",
			 "0,10 11,11 9,12 13,14 15,15 16,16 17,14 17,14 16"};
	vector<string> v(arr , arr + sizeof arr / sizeof arr[0]);
	printf("%d\n" , c.countCacti(17 , v));
	return 0;
}
