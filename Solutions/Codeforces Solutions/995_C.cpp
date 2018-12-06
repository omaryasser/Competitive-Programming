#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


int n;
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n;
    vector<pair<pair<int,int>,int> >v(n);
    vector<int>pos(n);
    REP(i,n){
        cin>>v[i].first.first>>v[i].first.second;
        v[i].second=i;
    }
    while(1){
        random_shuffle(all(v));
        ll x=0,y=0;
        REP(i,n)pos[i]=1;

        REP(i,n){
            ll nx=x+v[i].first.first,ny=y+v[i].first.second;
            ll nx2=x-v[i].first.first,ny2=y-v[i].first.second;
            if(nx*nx+ny*ny>nx2*nx2+ny2*ny2){
                x=nx2;
                y=ny2;
                pos[v[i].second]=-1;
            }else x=nx,y=ny;
        }
        if(x*x+y*y<=1ll*1500000*1500000)break;
    }

    REP(i,n){
        if(i)cout<<" ";
        cout<<(pos[i]);
    }
    cout<<"\n";
}