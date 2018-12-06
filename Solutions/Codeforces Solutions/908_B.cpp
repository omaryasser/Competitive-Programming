#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int n, m;
const int MAX =51;
string grid[MAX];
int commands[101];
int commandsLen;
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};
int curMap[4];
bool valid (int r, int c) {return r >= 0 && c >= 0 && r < n && c < m && grid[r][c] != '#';}
bool can () {
    int curX = -1, curY = -1;
    int endX = -1, endY = -1;
    FOR (i, 0, n) FOR (j, 0, m) if (grid[i][j] == 'S') {curX = i, curY = j;} else if (grid[i][j] == 'E') {endX = i, endY = j;}
    FOR (i, 0, commandsLen) {
        int nX = curX + dx[curMap[commands[i]]];
        int nY = curY + dy[curMap[commands[i]]];
        if (!valid(nX, nY)) return false;
        curX = nX, curY = nY;
        if (curX == endX && curY == endY) return true;
    }
    return false;
}
int main() {
    FAST;

    cin >> n >> m;
    FOR (i, 0, n) cin >> grid[i];
    string s; cin >> s;
    commandsLen = s.length();
    FOR (i, 0, s.length())
        commands[i] = s[i] - '0';
    int arr[4] = {0, 1, 2, 3};
    int cnt = 0;
    do {

        FOR(i, 0, 4) curMap[i] = arr[i];
        if (can()) cnt++;


    } while(next_permutation(arr, arr + 4));
    cout << cnt << "\n";
}