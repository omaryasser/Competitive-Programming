#include<bits/stdc++.h>

#define PI 3.14159265358979323846
#define fill(x,y) memset (x , y , sizeof(x))
#define lp(x , y) for (int x = 0 ; x < y ; ++ x)
#define upcase(x) transform(x.begin(), x.end(), x.begin(), ::toupper)
#define locase(x) transform(x.begin(), x.end(), x.begin(), ::tolower)
using namespace std;

int V;
bool adjMat[51][51];

int main() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
//	freopen("input.txt", "r", stdin);

	int T;
	string line;
	getline(cin, line);
	istringstream iss(line);
	iss >> T;
	while (T--) {
		getline(cin, line);
		istringstream iss(line);
		V = line.length();
		lp (i , V)
		{
			adjMat[0][i] = line[i] == 'Y';
		}
		lp (i , V - 1)
		{
			getline(cin, line);
			istringstream iss(line);
			lp(j , V)
			{
				adjMat[i + 1][j] = line[j] == 'Y';
			}
		}

		int best_id = 0;
		int max = 0;
		set<int> hs;
		for (int i = 0; i < V; ++i) {
			hs.clear();
			for (int j = 0; j < V; ++j)
				if (adjMat[i][j]) {
					for (int k = 0; k < V; ++k)
						if (adjMat[j][k] && k != i && !adjMat[i][k]) {
							hs.insert(k);
						}
				}
			if (hs.size() > max) {
				max = hs.size();
				best_id = i;
			}
		}

		cout << best_id << " " << max << "\n";
	}

	return 0;
}
