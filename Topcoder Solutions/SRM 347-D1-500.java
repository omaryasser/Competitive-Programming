#include<bits/stdc++.h>

#define FOR(i, s, n) for (int i = s; i < n; i++)

using namespace std;
typedef long long ll;

class Aircraft{
public:
    double sq(double a){return a*a;}
    double di(vector<int>p1,double x,double y,double z){
        return sqrt(sq(p1[0]-x)+sq(p1[1]-y)+sq(p1[2]-z));
    }
    double f(double l,vector<int>p1,vector<int>p2,vector<int>v2){
        return di(p1,p2[0]+l*v2[0],p2[1]+l*v2[1],p2[2]+l*v2[2]);
    }
    string nearMiss(vector <int> p1, vector <int> v1, vector <int> p2, vector <int> v2, int R){
        FOR(i,0,3)v2[i]-=v1[i];
        double l=0,r=20001;
        double best=di(p1,p2[0],p2[1],p2[2]);
        FOR(cnt,0,10000000){
            double l2=l+(r-l)/3,r2=l+2*(r-l)/3;
            if(f(l2,p1,p2,v2)>=f(r2,p1,p2,v2))
                l=l2;
            else
                r=r2;
        }
        best=min(best,di(p1,p2[0]+l*v2[0],p2[1]+l*v2[1],p2[2]+l*v2[2]));
        cerr<<best<<"\n";
        return best<=R+1e-5?"YES":"NO";
    }
};

int main(){
    int a[]={-10000, 0, 0},b[]=
    {10000, 0, 0},c[]=
{1, 0, 0},d[]=
{0, 0, 0};
    cout<<Aircraft().nearMiss(vector<int>(a,a+ sizeof(a)/ sizeof(a[0])),vector<int>(b,b+ sizeof(b)/ sizeof(b[0])),
                              vector<int>(c,c+ sizeof(c)/ sizeof(c[0])),vector<int>(d,d+ sizeof(d)/ sizeof(d[0])) ,0);
}
