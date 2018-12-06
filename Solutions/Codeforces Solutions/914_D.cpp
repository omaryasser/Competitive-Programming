#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)
#define FAST ios_base::sync_with_stdio(0); cin.tie(0);
using namespace std;

typedef long long ll;

int n,Q;
const int MAX=500001;
int a[MAX],tree[MAX<<1<<1];
void build(int node,int s,int e){
    if(s==e)tree[node]=a[s];
    else{
        int mid=(s+e)>>1;
        build(node<<1,s,mid);
        build(node<<1|1,mid+1,e);
        tree[node]=__gcd(tree[node<<1],tree[node<<1|1]);
    }
}
void update(int node,int s,int e,int idx,int num){
    if(s==e&&s==idx)tree[node]=num;
    else if(s>idx||e<idx);
    else{
        int mid=(s+e)>>1;
        update(node<<1,s,mid,idx,num);
        update(node<<1|1,mid+1,e,idx,num);
        tree[node]=__gcd(tree[node<<1],tree[node<<1|1]);
    }
}
int query(int node,int s,int e,int l,int r,int x){
    if(s>r||e<l)return 0;
    if(s==e)return tree[node]%x==0;
    int mid=(s+e)>>1;
    if(s>=l&&e<=r){
        if(tree[node]%x==0)return e-s+1;
        if(tree[node<<1]%x==0)return mid-s+1+query(node<<1|1,mid+1,e,l,r,x);
        else return query(node<<1,s,mid,l,r,x);
    }else{
        int left=query(node<<1,s,mid,l,r,x);
        int wanted=max(0,min(mid,r)-max(s,l)+1);
        if(l>mid){
            return query(node<<1|1,mid+1,e,l,r,x);
        }
        if(left==wanted)return left+query(node<<1|1,mid+1,e,l,r,x);
        return left;
    }
}
int query2(int node,int s,int e,int l,int r){
    if(s>=l&&e<=r)return tree[node];
    if(s>r||e<l)return 0;
    int mid=(s+e)>>1;
    return __gcd(query2(node<<1,s,mid,l,r),query2(node<<1|1,mid+1,e,l,r));
}
int main() {
    FAST
    for(int i=0;i<MAX<<1<<1;i++)tree[i]=1e9+3;
    cin>>n;
    for(int i=0;i<n;i++)cin>>a[i];
    build(1,0,n-1);
    cin>>Q;
    while(Q--){
        int op;
        cin>>op;
        if(--op){
            int i,y;
            cin>>i>>y;
            i--;
            update(1,0,n-1,i,y);
        }
        else{
            int l,r,x;
            cin>>l>>r>>x;
            l--,r--;
            int rM=query(1,0,n-1,l,r,x);
            if(rM>=r-l||query2(1,0,n-1,l+rM+1,r)%x==0)cout<<"YES\n";
            else cout<<"NO\n";
        }
    }
}