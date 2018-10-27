/*
Solved the problem greedily, for the current empty cell try to put the smallest remaining number you can,
if you can fill the grid by anyway after putting this number then you should put this number and do the same
for the next empty cell and so on ...
/*
#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define pb(x) push_back(x)
#define fi first
#define se second
#define all(x) x.begin(),x.end()
#define double long double
#define mk(x,y) make_pair(x,y)

typedef long long ll;

using namespace std;

int a[5][5];
int b[5][5];
char s[5][3];
set<int>lft,lft2;

class NewMagicSquare {
public:
    bool last(){
        REP(i,5)REP(j,4)if(b[i][j]>b[i][j+1])return 0;
        return 1;
    }
    bool chk(){
        REP(i,5)REP(j,5)b[i][j]=a[i][j];
        while(1){
            vector<pair<int,pair<int,int> > >v;
            REP(i,5){
                bool emp=1;
                int l=-1;
                REP(j,5){
                    if(b[i][j]!=0){
                        emp=0;
                    }else l=j;
                }
                if(!emp&&l!=-1){
                    int nx=l+1==5?100:b[i][l+1];
                    bool f=0;
                    for(int j=l-1;j>=0;j--)
                        if(b[i][j]!=0){
                            v.pb(mk(b[i][j],mk(nx,i*5+l)));
                            f=1;
                            break;
                        }
                    if(!f)v.pb(mk(-1,mk(nx,i*5+l)));
                }
            }
            sort(all(v));
            reverse(all(v));
            if(!(int)v.size()){
                return last();
            }
            for(auto p:v){
                auto it=lft2.lower_bound(p.se.fi);
                if(it!=lft2.begin()){
                    b[p.se.se/5][p.se.se%5]=*(prev(it));
                    lft2.erase(--it);
                }else return 0;
                break;
            }
        }
        return 1;
    }

    vector <string> completeTheSquare(vector <string> square){
        REP(i,25)lft.insert(i+1);
        REP(i,5){
            sscanf(square[i].c_str(),"%s %s %s %s %s",s[0],s[1],s[2],s[3],s[4]);
            REP(j,5)
                a[i][j]=s[j][0]=='?'?0:(s[j][0]-'0')*10+s[j][1]-'0',lft.erase(a[i][j]);
        }
        REP(i,5)REP(j,5){
            if(a[i][j]==0){
                bool f=0;
                for(int x:lft){
                    a[i][j]=x;
                    lft2.clear();
                    for(int m:lft)if(m!=x)lft2.insert(m);
                    if(chk()){
                        lft.erase(x);
                        f=1;
                        break;
                    }
                }
                if(!f)return vector<string>();
            }
        }
        vector<string>r;
        REP(i,5){
            string rr="";
            REP(j,5){
                if(j)rr+=" ";
                rr+=(a[i][j]<10?"0":"")+to_string(a[i][j]);
            }
            r.pb(rr);
        }
        return r;
    }
};
