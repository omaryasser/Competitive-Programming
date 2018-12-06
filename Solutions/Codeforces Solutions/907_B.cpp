#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

string grid[9];
char grid2[3][3][3][3];
int main() {
    FAST;

    for (int i = 0; i < 9; i++) {
        string s = "";
        for (int j = 0; j < 3; ++j) {
            string ss; cin >> ss;
            s += ss;
        }
        grid[i] = s;
    }
    for (int i = 0; i < 9; i++)
        for (int j = 0; j < 9; j++)
            grid2[i / 3][j / 3][i % 3][j % 3] = grid[i][j];
    int x, y;
    cin >> x >> y;
    x--, y--;

    int row = x % 3, col = y % 3;
    bool f = 0;
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            f |= grid2[row][col][i][j] == '.';

    if (f) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                grid2[row][col][i][j] = grid2[row][col][i][j] == '.' ? '!' : grid2[row][col][i][j];
    }
    else {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                grid2[r][c][i][j] = grid2[r][c][i][j] == '.' ? '!' : grid2[r][c][i][j];
    }

    for (int i = 0; i < 9; i++) {
        if (i == 3 || i == 6) cout << "\n";
        for (int j = 0; j < 9; j++) {
            if (j == 3 || j == 6) cout << " ";
            cout << grid2[i / 3][j / 3][i % 3][j % 3];
        }
        cout << "\n";
    }
}