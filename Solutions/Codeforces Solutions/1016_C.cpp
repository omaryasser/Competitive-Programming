#include <bits/stdc++.h>
#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;

const int n_=3e5+2;
ll cont[2][2][n_];
ll cont2[2][2][n_];
ll a[2][n_];
int LEFT=0,RIGHT=1;
int n;
void compL(int rw){
    cont[rw][RIGHT][n-1]=0;
    ll cum=0,sum=0;
    for(int j=n-2;j>=0;j--){
        sum+=a[rw][j+1];
        cum+=sum;
        cont[rw][RIGHT][j]=cum;
        cont2[rw][RIGHT][j]=sum;
    }
}
void compR(int rw){
    ll cum=0,cum2=0;
    for(int j=n-1;j>=0;j--){
        cum+=a[rw][j]*(n-j);
        cum2+=a[rw][j];
        cont[rw][LEFT][j]=cum;
        cont2[rw][LEFT][j]=cum2;
//        if(rw==1)cerr<<cum<<"\n";
    }
}
void compp(int rw){
    compL(rw);
    compR(rw);
}

ll go_right(int rw,int cl,int strt){
    if(cl>=n)return 0;
    return cont[rw][RIGHT][cl]+(strt-1)*cont2[rw][RIGHT][cl];
}
ll go_left(int rw,int cl,ll strt){
    if(cl>=n)return 0;
    return cont[rw][LEFT][cl]+(strt-1)*cont2[rw][LEFT][cl];
}
ll dp(int cl){
    if(cl>=n)return 0;
    ll tim=cl*2;
//    end right left
        ll bst=0;
        ll first,second;
    if(cl!=n-1) {
        first = go_right(0, cl, (tim+1));
        second = go_left(1, cl,(tim + (n - cl)));
        bst = first + second;
//        cerr<<"Col = " << cl << " FIRST " << second << "\n";
    }
//    go down then end
     ll down=(tim+1)*a[1][cl];
     first=go_right(1,cl,(tim+2));
     second=go_left(0,cl+1,(tim+1+(n-cl)));
     bst=max(bst,first+second+down);
//    cerr<<"Col = " << cl << " SECOND " << first+second+down << "\n";
//       go down right up then continue
    if(cl!=n-1) {
        down = (tim + 1) * a[1][cl] + (tim + 2) * a[1][cl + 1] + (tim + 3) * a[0][cl + 1]+(cl+2>=n?0:(tim+4)*a[0][cl+2]);
        first = dp(cl + 2);
//        cerr<<"Col = " << cl << " THIRD " << down+first << "\n";
        bst = max(bst, down + first);
    }
    return bst;
}
int main() {
    scanf("%d",&n);
    REP(rw,2)REP(j,n)scanf("%lld",&a[rw][j]);
    REP(i,2)compp(i);
//    cerr<<go_right(0,0)<<"\n";
    printf("%lld\n",dp(0));
}