#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define mp(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

const int N=1001;
int n,m,q;
struct cell{
    int v;
    cell *lft,*rght,*top,*bot;
};

cell *lft[N];
cell *top[N];
cell *lstRow[N];

void chanL(cell* c1, cell* c2){
    cell *l1=c1->lft,*l2=c2->lft;
    c1->lft=l2;
    c2->lft=l1;
    if(l2!=NULL)l2->rght=c1;
    if(l1!=NULL)l1->rght=c2;
}
void chanR(cell* c1, cell* c2){
    cell *l1=c1->rght,*l2=c2->rght;
    c1->rght=l2;
    c2->rght=l1;
    if(l2!=NULL)l2->lft=c1;
    if(l1!=NULL)l1->lft=c2;
}
void chanT(cell* c1, cell* c2){
    cell *l1=c1->top,*l2=c2->top;
    c1->top=l2;
    c2->top=l1;
    if(l2!=NULL)l2->bot=c1;
    if(l1!=NULL)l1->bot=c2;
}
void chanB(cell* c1, cell* c2){
    cell *l1=c1->bot,*l2=c2->bot;
    c1->bot=l2;
    c2->bot=l1;
    if(l2!=NULL)l2->top=c1;
    if(l1!=NULL)l1->top=c2;
}

vector<cell*>getH(int r1,int c1,int h){
    vector<cell*>ret;
    cell* cur=lft[r1];
    REP(j,c1+1)cur=cur->rght;
    REP(i,h){
        ret.push_back(cur);
        cur=cur->bot;
    }
    return ret;
}

vector<cell*>getV(int r1,int c1,int w){
    vector<cell*>ret;
    cell* cur=lft[r1];
    REP(j,c1+1)cur=cur->rght;
    REP(i,w){
        ret.push_back(cur);
        cur=cur->rght;
    }
    return ret;
}


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    cin>>n>>m>>q;
    REP(i,N)lft[i]=new cell();
    REP(i,N)top[i]=new cell();
    REP(i,N)lstRow[i]=top[i];

    REP(i,n){
        cell* lst=lft[i];
        REP(j,m){
            cell *c=new cell();
            cin>>c->v;
            c->top=lstRow[j];
            lstRow[j]->bot=c;
            c->lft=lst;
            lst->rght=c;
            lst=c;
            lstRow[j]=c;
        }
    }
    while(q--){
        int r1,c1,r2,c2,h,w;
        cin>>r1>>c1>>r2>>c2>>h>>w;
        r1--,r2--,c1--,c2--;
//        cerr<<"sdf\n";
        vector<cell*>left1=getH(r1,c1,h),left2=getH(r2,c2,h);
        vector<cell*>right1=getH(r1,c1+w-1,h),right2=getH(r2,c2+w-1,h);
        vector<cell*>top1=getV(r1,c1,w),top2=getV(r2,c2,w);
        vector<cell*>bot1=getV(r1+h-1,c1,w),bot2=getV(r2+h-1,c2,w);
        REP(i,h){
            chanL(left1[i],left2[i]);
            chanR(right1[i],right2[i]);
        }
        REP(i,w){
            chanT(top1[i],top2[i]);
            chanB(bot1[i],bot2[i]);
        }
    }
    REP(i,n){
        cell* lst=lft[i];
        REP(j,m){
            if(j)cout<<" ";
            cout<<lst->rght->v;
            lst=lst->rght;
        }
        cout<<"\n";
    }
}