#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)

using namespace std;


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n; cin >> n;
    string s; cin >> s;
    map<char,int> mp;
    f(i, 0, n)mp[s[i]]++;
    vector<pair<int,char> >v;
    for(auto it : mp)v.push_back({it.second, it.first});
    sort(all(v));
    reverse(all(v));
    string ss = "";
    for(int i = 0; i < (int)v.size(); i ++){
        int l1 = 0;
        int p = 0;
        while(1){
            if(!p){
                if(!(l1 < v[i].first))break;
                l1++;
                ss += string(1, v[i].second);
            }
        }
    }
    cout << ss << "\n";
}