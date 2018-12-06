#include<bits/stdc++.h>
#include <cstdio>
#include <iostream>

#define FAST ios_base::sync_with_stdio(0); cin.tie(0);

using namespace std;
typedef long long ll;

class Triple {
public:
    int left, right, len;
    Triple (int le, int ri, int ll) : left(le), right(ri), len(ll) {};
};
int n;
class comparator {
public :
    bool operator()(const Triple &t1, const Triple &t2) {
        int l1 = t1.len, l2 = t2.len;
        return (l1 == l2 ? t1.left < t2.left : l1 > l2);
    }
};

multiset<Triple, comparator> pq;

const int MAX = 200006;
int arr[MAX], myLen[MAX], leftt[MAX], rightt[MAX], myStart[MAX], myEnd[MAX];
bool finished[MAX];

int get_left (int idx) {
    return !finished[leftt[idx]] ? leftt[idx] : leftt[idx] = get_left(myStart[leftt[idx]]);
}

int get_right (int idx) {
    return !finished[rightt[idx]] ? rightt[idx] : rightt[idx] = get_right(myEnd[rightt[idx]]);
}

int main() {
    FAST;

    cin >> n;
    for (int i = 1; i <= n; i++) cin >> arr[i];
    arr[0] = -2; arr[n + 1] = -1;

    for (int i = 1; i <= n;) {
        int j = i;
        while(j + 1 <= n && arr[j + 1] == arr[i]) j++;
        pq.insert(Triple(i, j, j - i + 1));
        myLen[i] = myLen[j] = j - i + 1;
        leftt[i] = i - 1;
        rightt[j] = j + 1;
        myStart[j] = i;
        myEnd[i] = j;
        i = j + 1;
    }

    int cnt = 0;
    while(pq.size()) {
        cnt++;
        Triple cur = *pq.begin(); pq.erase(pq.begin());
        int left = cur.left, right = cur.right;
        finished[left] = finished[right] = true;
        int idxToLeft = get_left(left), idxToRight = get_right(right);
        if (arr[idxToLeft] == arr[idxToRight]) {
            pq.erase(Triple(myStart[idxToLeft], idxToLeft, myLen[idxToLeft]));
            pq.erase(Triple(idxToRight, myEnd[idxToRight], myLen[idxToRight]));
            myEnd[myStart[idxToLeft]] = myEnd[idxToRight];
            myStart[myEnd[idxToRight]] = myStart[idxToLeft];
            myLen[myStart[idxToLeft]] = myLen[myEnd[idxToRight]] = myLen[idxToLeft] + myLen[idxToRight];
            pq.insert(Triple(myStart[idxToLeft], myEnd[idxToRight], myLen[myStart[idxToLeft]]));
        }
    }
    cout << cnt << "\n";
}