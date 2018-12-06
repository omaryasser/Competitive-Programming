#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

int main() {
    FAST;

    int n; cin >> n;
    char op[n];
    string s[n];
    for (int i = 0; i < n; i++)
        cin >> op[i] >> s[i];

    unordered_set<char> mayBeBad, good;
    for (char i = 'a'; i <= 'z'; i++) mayBeBad.insert(i);
    for (int i = 0; i < n - 1; i++) {
        if (op[i] == '!') {
            unordered_set<char> mayBeBad2;
            for (int j = 0; j < s[i].length(); j++)
                if (mayBeBad.find(s[i][j]) != mayBeBad.end() && good.find(s[i][j]) == good.end())
                    mayBeBad2.insert(s[i][j]);
            mayBeBad = mayBeBad2;
        }
        else {
            for (int j = 0; j < s[i].length(); j++)
                good.insert(s[i][j]), mayBeBad.erase(s[i][j]);
        }
        if (mayBeBad.size() == 1) {
            int cnt = 0;
            for (int j = i + 1; j < n - 1; j++)
                if (op[j] != '.') cnt++;
            cout << cnt << "\n";
            return 0;
        }
    }
    puts("0");
}