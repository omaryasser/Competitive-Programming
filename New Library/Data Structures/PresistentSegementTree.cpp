struct Vertex {
    Vertex *l, *r;
    int sum;

    Vertex(int val) : l(nullptr), r(nullptr), sum(val) {}
    Vertex(Vertex *l, Vertex *r) : l(l), r(r), sum(0) {
        if (l) sum += l->sum;
        if (r) sum += r->sum;
    }
};

Vertex* build(int a[], int tl, int tr) {
    if (tl == tr)
        return new Vertex(a[tl]);
    int tm = (tl + tr) / 2;
    return new Vertex(build(a, tl, tm), build(a, tm+1, tr));
}

int get_sum(Vertex* v, int tl, int tr, int l, int r) {
    if (l > r)
        return 0;
    if (l == tl && tr == r)
        return v->sum;
    int tm = (tl + tr) / 2;
    return get_sum(v->l, tl, tm, l, min(r, tm))
         + get_sum(v->r, tm+1, tr, max(l, tm+1), r);
}

Vertex* update(Vertex* v, int tl, int tr, int pos, int new_val) {
    if (tl == tr)
        return new Vertex(new_val);
    int tm = (tl + tr) / 2;
    if (pos <= tm)
        return new Vertex(update(v->l, tl, tm, pos, new_val), v->r);
    else
        return new Vertex(v->l, update(v->r, tm+1, tr, pos, new_val));
}


// Finding K-th Smallest Number in range (numbers are distinct)
#include <bits/stdc++.h>
#include <bitset>
 
#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";
 
typedef long long ll;
 
using namespace std;
 
const int N=100001;
int ID;
int L[N*20],R[N*20],cnt[N*20];
int n;
 
void build(int id,int s,int e){
    if(s==e)return;
    int m=s+e>>1;
    L[id]=++ID;
    build(L[id],s,m);
    R[id]=++ID;
    build(R[id],m+1,e);
}
int update(int oldID,int s,int e,int idx){
    int curID=++ID;
    cnt[curID]=cnt[oldID]+1;
    if(s==e)return curID;
    L[curID]=L[oldID],R[curID]=R[oldID];
    int m=s+e>>1;
    if(idx<=m)L[curID]=update(L[curID],s,m,idx);
    else R[curID]=update(R[curID],m+1,e,idx);
    return curID;
}
int query(int lID,int rID,int s,int e,int k){
    if(s==e)return s;
    int m=s+e>>1;
    if(cnt[L[rID]]-cnt[L[lID]]>=k)
        return query(L[lID],L[rID],s,m,k);
    else
        return query(R[lID],R[rID],m+1,e,k-(cnt[L[rID]]-cnt[L[lID]]));
}
int main() {
 
    int m;
    scanf("%d%d",&n,&m);
    vector<int>a(n),b(n),root(n+1),ord(n);
    REP(i,n)scanf("%d",&a[i]),b[i]=i;
    sort(all(b),[&a](const int &i1,const int &i2){return a[i1]<a[i2];});
    REP(i,n)ord[b[i]]=i;
    build(0,0,n-1);
    REP(i,n)root[i+1]=update(root[i],0,n-1,ord[i]);
    while(m--){
        int l,r,k;
        scanf("%d%d%d",&l,&r,&k);
        printf("%d\n",a[b[query(root[l-1],root[r],0,n-1,k)]]);
    }
    return 0;
} 
