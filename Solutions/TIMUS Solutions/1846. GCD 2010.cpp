#include <bits/stdc++.h>
#include <bitset>

#define REP(i,n) for(int i=0;i<(int)n;i++)
#define REP1(i,j,n) for(int i=j;i<(int)n;i++)
#define all(x) x.begin(),x.end()
#define double long double
#define BUG cerr<<"BUG\n";

typedef long long ll;

using namespace std;


struct ST{
    vector<int>tree,cnt,val;
    int n;
    void init(int n,vector<int>a){
        this->n=n;
        tree.assign(n<<1<<1,0);
        cnt.assign(n<<1<<1,0);
        val=a;
    }

    void update(int u,int s,int e,int l,int r,int ad){
        if(s>=l&&e<=r){
            cnt[u]+=ad;
            if(cnt[u])tree[u]=val[r];
            else tree[u]=0;
        }else if(s>r||e<l);
        else{
            int m=s+e>>1;
            update(u<<1,s,m,l,r,ad);
            update(u<<1^1,m+1,e,l,r,ad);
            tree[u]=__gcd(tree[u<<1],tree[u<<1^1]);
        }
    }

    void update(int l,int r,int ad){
        update(1,0,n-1,l,r,ad);
    }

    int query(int u,int s,int e,int l,int r){
        if(s>=l&&e<=r)return tree[u];
        if(s>r||e<l)return 0;
        int m=s+e>>1;
        return __gcd(query(u<<1,s,m,l,r),query(u<<1^1,m+1,e,l,r));
    }

    int query(int l,int r){
        return query(1,0,n-1,l,r);
    }
};

int main() {
//    ios_base::sync_with_stdio(0); cin.tie(0);

    int q;
    vector<int>qs;
    scanf("%d",&q);
    int mpIdx=0;
    vector<int>vals;
    unordered_map<int,int>mp;
    REP(i,q){
        char c;
        int x;
        scanf("\n%c %d",&c,&x);
        qs.push_back(c=='-'?-x:x);
        if(mp.find(x)==mp.end()){
            mp[x]=mpIdx++;
            vals.push_back(x);
        }
    }
    ST st; st.init(mpIdx,vals);
    REP(i,q){
        if(qs[i]<0){
            int mpVal=mp[-qs[i]];
            st.update(mpVal,mpVal,-1);
        }else{
            int mpVal=mp[qs[i]];
            st.update(mpVal,mpVal,1);
        }
        printf("%d\n",max(1,st.query(0,mpIdx)));
    }


    return 0;
}
