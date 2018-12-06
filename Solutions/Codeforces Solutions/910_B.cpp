#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

int n, a, b;
int main() {
    FAST;

    cin >> n >> a >> b;
    int arr[6];
    for (int i = 0; i < 4; i++) arr[i] = a;
    arr[4] = arr[5] = b;
    sort(arr, arr + 6);

    int best = 6;
    do {
        int soFar = 0, here = 1;
        for (int i = 0; i < 6; i++) {
            soFar += arr[i];
            if (soFar > n) {here++; soFar = arr[i];}
        }
        best = min (best, here);
    } while (next_permutation(arr, arr + 6));
    cout << best << "\n";
}