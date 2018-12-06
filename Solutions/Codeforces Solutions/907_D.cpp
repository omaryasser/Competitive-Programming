#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

int n, m;

void print (vector<vector<int> > v) {
    cout << "YES\n";
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (j > 0) cout << " ";
            cout << v[i][j]+1;
        }
        cout << "\n";
    }
}

int mapp (int r, int c) {return r * m + c;}

void print (vector<int> v) {
    cout << "YES\n";
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (j > 0) cout << " ";
            cout << v[mapp(i, j)]+1;
        }
        cout << "\n";
    }
}

bool ok (vector<int> v) {
    for (int i = 0; i < n * m; i++) {
        int num = v[i];
        unordered_set<int> bad;
        int row = num / m, col = num % m;
        bad.insert(mapp(row - 1, col));
        bad.insert(mapp(row + 1, col));
        bad.insert(mapp(row, col + 1));
        bad.insert(mapp(row, col - 1));
        row = i / m, col = i % m;
        if (row + 1 < n && bad.find(v[mapp(row + 1, col)]) != bad.end() ||
                col + 1 < m && bad.find(v[mapp(row, col + 1)]) != bad.end())
            return false;
    }
    return true;
}

int main() {
    FAST;
    cin >> n >> m;
    int was[n][m], res[n][m], res2[n][m];
    for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) was[i][j] = i * m + j;
    if (n >= 4) {
        int realRow = 0;
        for (int row = 1; row < n; row += 2, realRow++)
            for (int j = 0; j < m; j++)
                res[realRow][j] = was[row][j];
        for (int row = 0; row < n; row += 2, realRow++)
            for (int j = 0; j < m; j++)
                res[realRow][j] = was[row][j];
        if (n % 2 == 0) {
            for (int j = 0; j < m; j++) {
                bool rev = j % 2;
                for (int i = 0; i < n; i++) {
                    res2[i][j] = rev ? res[n - i - 1][j] : res[i][j];
                }
            }
        } else {
            for (int j = 0; j < m; j++) {
                bool rev = j % 2;
                for (int i = 0; i < n; i++) {
                    res2[i][j] = rev ? (res[(i + 1) == n ? 0 : i + 1][j]) : res[i][j];
                }
            }
        }
        vector<vector<int> > tmp;
        for (int i = 0; i < n; i++) tmp.push_back(vector<int>(res2[i], res2[i] + m));
        print(tmp);
    } else if (m >= 4) {
        int realCol = 0;
        for (int col = 1; col < m; col += 2, realCol++)
            for (int i = 0; i < n; i++)
                res[i][realCol] = was[i][col];
        for (int col = 0; col < m; col += 2, realCol++)
            for (int i = 0; i < n; i++)
                res[i][realCol] = was[i][col];
        if (m % 2 == 0) {
            for (int i = 0; i < n; i++) {
                bool rev = i % 2;
                for (int j = 0; j < m; j++) {
                    res2[i][j] = rev ? (res[i][m - j - 1]) : res[i][j];
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                bool rev = i % 2;
                for (int j = 0; j < m; j++) {
                    res2[i][j] = rev ? (res[i][j + 1 == m ? 0 : j + 1]) : res[i][j];
                }
            }
        }
        vector<vector<int> > tmp;
        for (int i = 0; i < n; i++) tmp.push_back(vector<int>(res2[i], res2[i] + m));
        print(tmp);
    } else {
        vector<int> v(n * m);
        for (int i = 0; i < n * m; i++) v[i] = i;
        do {
            if (ok(v)) {print(v); return 0;}
        } while (next_permutation(v.begin(), v.end()));
        cout << "NO\n";
    }
}