#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


typedef struct node{
    int prior,size;
    int val,mx;
    struct node *l,*r;
}node;
typedef node* pnode;
int sz(pnode t){
    return t?t->size:0;
}
void upd_sz(pnode t){
    if(t)t->size=sz(t->l)+1+sz(t->r);
}
void reset(pnode t){
    if(t)t->mx=t->val;
}
void combine(pnode& t,pnode l,pnode r){
    if(!l || !r)return void(t=l?l:r);
    t->mx =max(l->mx,r->mx);
}
void operation(pnode t){
    if(!t)return;
    reset(t);
    combine(t,t->l,t);
    combine(t,t,t->r);
}
void split(pnode t,pnode &l,pnode &r,int pos,int add=0){
    if(!t)return void(l=r=NULL);
    int curr_pos = add + sz(t->l);
    if(curr_pos<=pos)split(t->r,t->r,r,pos,curr_pos+1),l=t;
    else split(t->l,l,t->l,pos,add),r=t;
    upd_sz(t);
    operation(t);
}
void merge(pnode &t,pnode l,pnode r){
    if(!l || !r) t = l?l:r;
    else if(l->prior>r->prior)merge(l->r,l->r,r),t=l;
    else merge(r->l,l,r->l),t=r;
    upd_sz(t);
    operation(t);
}
pnode init(int val){
    pnode ret = new node();
    ret->prior=rand();ret->size=1;
    ret->val=val;
    ret->mx=val;
    return ret;
}
int range_query(pnode &t,int l,int r){
    pnode L,mid,R;
    split(t,L,mid,l-1);
    split(mid,t,R,r-l);
    int ans = t->mx;
    merge(mid,L,t);
    merge(t,mid,R);
    return ans;
}

void insert(pnode &t, int h,int p){
    pnode L,R;
    split(t,L,R,p-1);
    merge(L,L,init(h));
    merge(t,L,R);
}

int main() {
//    ios_base::sync_with_stdio(0); cin.tie(0);

    int m;
    scanf("%d",&m);
    pnode root=init(-1e9-2);
    while(m--){
        char op;
        scanf("\n%c",&op);
        if(op=='A'){
            int h,p;
            scanf("%d%d",&h,&p);
            insert(root,h,p);
        }else{
            int x,y;
            scanf("%d%d",&x,&y);
            printf("%d\n",range_query(root,x,y));
        }
    }

    return 0;
}
