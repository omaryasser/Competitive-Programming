#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

int n;
vector<vector<int> > combinations;
int M[2][12] = {{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}, {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}};
vector<int> make (int f, int s, int t) {
    vector<int> v;
    int arr[3] = {f, s, t};
    for (int j = 0; j < 3; j++)
        for (int i = 0; i < 12; i++)
            v.push_back(M[arr[j]][i]);
    return v;
}
int main() {
    FAST;
    combinations.push_back(make(0, 0, 0));
    combinations.push_back(make(1, 0, 0));
    combinations.push_back(make(0, 1, 0));
    combinations.push_back(make(0, 0, 1));

    int n;
    cin >> n;
    int arr[n];
    for (int i = 0; i < n; i++) cin >> arr[i];
    for (int i = 0; i < 4; i++) {
        for (int s = 0; s <= 12 * 3 - n; s++) {
            bool ok = true;
            for (int cur = s; cur < s + n; cur++)
                ok &= arr[cur - s] == combinations[i][cur];
            if (ok) return puts("YES");
        }
    }
    puts("NO");
}