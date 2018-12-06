#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int arr[3];
int main() {
    FAST;

    FOR(i, 0, 3) cin >> arr[i];
    sort(arr, arr + 3);
    if (arr[0] == 1 || arr[0] == 2 && arr[1] == 2 || arr[0] == 3 && arr[1] == 3 && arr[2] == 3 || arr[0] == 2 && arr[1] == 4 && arr[2] == 4)
        cout << "YES\n";
    else
        cout << "NO\n";
}