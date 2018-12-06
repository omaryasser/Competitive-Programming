#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

int x;
int h,m;
bool lucky(int h2,int m2){
    return h2%10==7||h2/10==7||m2%10==7||m2/10==7;
}
int reach(int h2,int m2){
    int curH=h2,curM=m2;
    int cnt=0;
    while(curH!=h||curM!=m){
        cnt++;
        FOR(i,0,x) {
            curM++;
//        cerr<<curH<<" "<<curM<<" "<<h2<<" "<<m2<<"\n";
            if (curM == 60) {
                curM = 0, curH = (curH + 1) % 24;
            }
        }
        if(cnt>=24*60+2)return 100000000;
    }
    return cnt;
}
int main() {
    scanf("%d%d%d",&x,&h,&m);
    int mn=100000000;
    FOR(h2,0,24)
        FOR(m2,0,60)
            if(lucky(h2,m2)){
                mn=min(mn,reach(h2,m2));
            }
    printf("%d\n",mn);
}