#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


vector<int>arr;
ll overlap(int l1,int r1,int l2,int r2){
    return max(0,min(r1,r2)-max(l1,l2)+1);
}
ll solve(int minL,int minR,int maxL,int maxR,int l1,int r1,int l2,int r2){
    // 1, 2
    ll res=1ll*overlap(minL,minR,l1,r1)*overlap(maxL,maxR,l2,r2);
    // 2, 1
    res+=1ll*overlap(maxL,maxR,l1,r1)*overlap(minL,minR,l2,r2);

    return res;
}
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    REP1(i,1,10){
        REP(msk,1<<i){
            int num=0;
            REP(j,i)
                if((msk>>j)&1)num=num*10+4;
                else num=num*10+7;
            arr.push_back(num);
        }
    }
    sort(all(arr));
    int l1,r1,l2,r2,k;
    cin>>l1>>r1>>l2>>r2>>k;
    ll res=0;
    REP(i,arr.size()){
        int j=i+k-1;
        if(j>=arr.size())continue;
        int minL=i?arr[i-1]+1:1,minR=arr[i];
        int maxL=arr[j],maxR=j+1<arr.size()?arr[j+1]-1:1e9+2;
        if(k==1){
            res+=solve(minL,minR-1,minR,maxR,l1,r1,l2,r2);
            res+=overlap(l1,r1,minR,minR)*overlap(l2,r2,minR+1,maxR);
            res+=overlap(l2,r2,minR,minR)*overlap(l1,r1,minR+1,maxR);
            res+=overlap(l2,r2,minR,minR)*overlap(l1,r1,minR,minR);
        }
        else res+=solve(minL,minR,maxL,maxR,l1,r1,l2,r2);
    }
    cout<<fixed<<setprecision(12)<<1.0*res/(1ll*(r1-l1+1)*(r2-l2+1))<<"\n";
}