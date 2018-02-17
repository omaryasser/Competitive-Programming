#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

class RadarFinder{
public:
    ll sq(ll a){return a*a;}
    int possibleLocations(ll x1, ll y1, ll r1, ll x2, ll y2, ll r2){
        ll dCenters=sq(x1-x2)+sq(y1-y2);
        if(x1==x2&&y1==y2&&r1==r2)return -1;
        if(dCenters==sq(r1-r2)||dCenters==sq(r2-r1)||dCenters==sq(r1+r2))return 1;
        if(dCenters>sq(r1+r2)||dCenters<sq(max(r1,r2)-min(r1,r2)))return 0;
        return 2;
    }
};
