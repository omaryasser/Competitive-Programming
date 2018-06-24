const int MAX=1000;
ll comb[MAX][MAX];//init with -1
ll nCr(int n,int k){
    if(n<k)return 0;
    if(k==0||k==n)return 1;
    if(comb[n][k]!=-1)return comb[n][k];
    if(n-k<k)return comb[n][k]=nCr(n,n-k);
    return comb[n][k]=nCr(n-1,k-1)+nCr(n-1,k);
}
