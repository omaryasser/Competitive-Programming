ll extended_euclid(ll a, ll b, ll &x, ll &y){
	if(!b){
		x=1,y=0;
		return a;
	}
	ll g=extended_euclid(b,a%b,y,x);
	y=-(a/b)*x;
	return g;
}
ll solveSystemOfCongurences_NOT_RELATIVES(vector<ll>rems,vector<ll>mods){
	ll rem=rems[0],mod=mods[0];
	FOR1(i,1,(int)rems.size()){
		ll x,y,a=mod,b=-mods[i],c=rems[i]-rem;
		ll g=extended_euclid(a,b,x,y);
		x*=(c/g),y*=(c/g);
		if(c%g!=0)return -1;
		rem+=mod*x;
		mod=mod/g*mods[i];
		rem=(rem%mod+mod)%mod;
	}
	return rem;
}

// relatives
ll inv(ll x,ll M){
    ll r,y;
    for(r=1,y=M-2;y;x=x*x%M,y>>=1)(y&1)&&(r=r*x%M);
    return r;
}
// all mods must be pair-wise co-primes
ll solveSystemOfCongurences_ch1(vector<ll>rems,vector<ll>mods){
	ll prod=1,x=0;
	FOR(i,(int)mods.size())prod*=mods[i];
	FOR(i,(int)mods.size()){
		ll subProd=prod/mods[i];
		x+=subProd*rems[i]*inv(subProd,mods[i]);
	}
	return x%prod;
}
