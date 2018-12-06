#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;


int main() {
    FAST;

    string s; cin >> s;
    int cnt = 0;
    FOR (i, 0, s.length())
    if (s[i] == 'a' || s[i] ==  'e' || s[i] ==  'i' || s[i] ==  'o' || s[i] == 'u' || s[i] == '1' || s[i] =='3' || s[i] =='5' || s[i] == '7' || s[i] == '9')
        cnt++;
    cout << cnt << "\n";
}