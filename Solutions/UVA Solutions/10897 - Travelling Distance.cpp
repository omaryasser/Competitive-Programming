#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";
#define pair(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

double gcDistance(double pLat, double pLong,
                  double qLat, double qLong, double radius) {
    pLat *= M_PI / 180; pLong *= M_PI / 180;
    qLat *= M_PI / 180; qLong *= M_PI / 180;
    return radius * acos(cos(pLat)*cos(pLong)*cos(qLat)*cos(qLong) +
                         cos(pLat)*sin(pLong)*cos(qLat)*sin(qLong) +
                         sin(pLat)*sin(qLat));
}

double p[2][2];
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin>>n;
    while(n--){
        REP(i,2){
            double a,b,c;
            char d;
            cin>>a>>b>>c>>d;
            p[i][0]=a+b/60+c/(60*60);
            if(d=='N')p[i][0]=360-(p[i][0]);
            cin>>a>>b>>c>>d;
            p[i][1]=a+b/60+c/(60*60);
            if(d=='E')p[i][1]=180+180-p[i][1];
        }
        cout<<setprecision(2)<<fixed<<gcDistance(p[0][0],p[0][1],p[1][0],p[1][1],6371.01)<<"\n";
    }
}
