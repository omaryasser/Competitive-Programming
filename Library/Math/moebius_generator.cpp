
const int MAX=1000000;
void moebius_generator(){
	int moebius[MAX+1];
	bool isPrime[MAX+1];
	FOR1(i,2,MAX+1)
		moebius[i]=1,isPrime[i]=1;
	FOR1(i,2,MAX+1)if(isPrime[i]){
		moebius[i]=-1;
		for(int j=2*i;j<=MAX;j+=i)
			isPrime[j]=0,moebius[j]=(j%(i*i)==0)?0:-moebius[j];
	}
}
