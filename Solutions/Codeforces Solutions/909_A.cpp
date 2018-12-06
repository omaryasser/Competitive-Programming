#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, n) for (int i = 0; i < n; i++)

using namespace std;
typedef long long ll;

int main() {
    FAST;

    string s1, s2;
    cin >> s1 >> s2 ;
    string mn = s1 + s2;
    FOR (i, s1.length())
    FOR (j, s2.length())
         mn = min(mn, s1.substr(0, i + 1) + s2.substr(0, j + 1));
    cout << mn << "\n";
}