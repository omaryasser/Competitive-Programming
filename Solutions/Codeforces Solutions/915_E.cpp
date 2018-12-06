#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)


using namespace std;
typedef long long ll;

int n,q;

set<pair<int,int> >intervals;
int main() {
    scanf("%d%d",&n,&q);
    int nonworking=0;
    while(q--){
        int l,r,k;
        scanf("%d%d%d",&l,&r,&k);
        vector<pair<int,int> >remove,add;
        if(k==1){
            for(set<pair<int,int> >::iterator it=intervals.lower_bound({l,-1});it!=intervals.end();it++){
                int l2=(*it).second,r2=(*it).first;
                if(l2>=l&&r2<=r)remove.push_back({r2,l2}),nonworking-=(r2-l2+1);
                else if(l2>r)break;
                else{
                    l=min(l,l2);
                    r=max(r,r2);
                    remove.push_back({r2,l2});
                    nonworking-=(r2-l2+1);
                }
            }
            for(auto p:remove)intervals.erase(p);
            intervals.insert({r,l});
            nonworking+=(r-l+1);
        }else{
            for(set<pair<int,int> >::iterator it=intervals.lower_bound({l,-1});it!=intervals.end();it++){
                int l2=(*it).second,r2=(*it).first;
                if(l2>=l&&r2<=r)remove.push_back({r2,l2}),nonworking-=r2-l2+1;
                else if(l2>r)break;
                else{
                    remove.push_back({r2,l2});
                    nonworking-=(r2-l2+1);
                    if(l2<l)add.push_back({l-1,l2});
                    if(r2>r)add.push_back({r2,r+1});
                }
            }
            for(auto p:remove)intervals.erase(p);
            for(auto p:add)intervals.insert(p),nonworking+=p.first-p.second+1;
        }
//        for(auto p:intervals)
//            cerr<<q<<" "<<p.second<<" "<<p.first<<"\n";
        printf("%d\n",n-nonworking);
    }
}