/*
Simulate
*/
#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;


int a[5];
map<string,int>mp;
map<int,string>mp2;
char dum[5];

int main() {
    #ifndef ONLINE_JUDGE
    freopen("in.txt","r",stdin);
    #endif     

    REP(i,5)scanf("%d",a+i);
    int k;
    scanf("%d",&k);
    mp["S"]=0;mp2[0]="S";
    mp["M"]=1;mp2[1]="M";
    mp["L"]=2;mp2[2]="L";
    mp["XL"]=3;mp2[3]="XL";
    mp["XXL"]=4;mp2[4]="XXL";
    while(k--){
        scanf("%s",dum);
        int w=mp[string(dum)];
        int l,r;
        l=r=w;
        while(1){
            if(r<5&&a[r]){
                printf("%s\n", mp2[r].c_str());
                a[r]--;
                break;
            }
            if(l>=0&&a[l]){
                printf("%s\n", mp2[l].c_str());
                a[l]--;
                break;
            }
            r++;
            l--;
        }
    }    
    
    return 0;
}