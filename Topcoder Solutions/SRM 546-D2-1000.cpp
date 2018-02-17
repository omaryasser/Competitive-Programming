#include <bits/stdc++.h>
using namespace std;

typedef long long ll ;

class FavouriteDigits {
	public : 
	ll toLL(string x) {istringstream iss(x); ll res ; iss >> res; return res;}
	ll findNext(ll NN, int d1, int c1, int d2, int c2){
		if(!c1 && !c2) return NN;
		if(d1 > d2) swap(d1 , d2) , swap(c1 , c2);
		string N = to_string(NN - 1);
		int all = max((int)N.length() , c1 + c2);
		int zeros = all - (c1 + c2 - (c1&&(d1==1)) - (c2&&(d2==1)));
		string res1 = string(1 , '1') + string (zeros - (c1&&d2 == 1 && zeros), '0') + string (c1 - (c1&&(d1 == 1) ) , d1 + '0') + string (c2 - (c2&&(d2 == 1)) , d2 + '0');
		int cntD1[(int)max((int)N.length() , c1 + c2)],cntD2[(int)max((int)N.length() , c1 + c2)];
		string n2 = N;
		while((int)n2.length() < max((int)N.length() , c1 + c2)) n2 = "0" + n2;
		if(n2[0] == '0') n2[0] = '1';
		if(c1 + c2 > (int)N.length()){
		 	n2 = string (d1 == 0 && c2 == 0 , '1') + string(d1 == 0 && c2, d2 + '0') + string (c1 , d1 + '0') + string (c2 - (d1 == 0 && c2) , d2 + '0');
		 	n2 = to_string(toLL(n2) - 1);
		}
		for(int i = 0 ; i < (int)n2.length() ; ++ i) {
				if(i) cntD1[i] = cntD1[i - 1] , cntD2[i] = cntD2[i - 1];
				else cntD1[i] = cntD2[i] = 0 ;
				cntD1[i] += n2[i] == '0' + d1;
				cntD2[i] += n2[i] == '0' + d2;
				cntD1[i] = min(c1 , cntD1[i]);
				cntD2[i] = min(c2 , cntD2[i]);
		}
		string ans = "" ;
		bool f = 0;
		for (int i = n2.length() - 1 ;!f && i >= 0 ; -- i) {
			for (char j = n2[i] + 1; !f && j <= '9' ; j ++ ) {
				int cntD1P = i == 0 ? 0 : cntD1[i - 1];
				int cntD2P = i == 0 ? 0 : cntD2[i - 1];
				cntD1P += (j == '0' + d1);
				cntD2P += (j == '0' + d2);
				cntD1P = min(c1 , cntD1P);
				cntD2P = min(c2 , cntD2P);
				if (n2.length() - 1 - i + cntD1P + cntD2P >= c1 + c2){
					ans = n2.substr(0 , i) + string(1 , j) + string(n2.length() - i - 1 - (c1 - cntD1P) - (c2 - cntD2P ) , '0') + string(c1 - cntD1P, d1 + '0') + string(c2 - cntD2P , d2 + '0');
					f = 1;
				}
			}
		}
		if(ans == "") return toLL(res1) < NN ? toLL("10" + res1.substr(1,res1.length())) : toLL(res1);
		else return toLL(ans) < NN ? toLL("10" + ans.substr(1,ans.length())) : toLL(ans);
	}
};

int main(){
	FavouriteDigits f;
	cout << f.findNext (1, 1, 0, 0, 0) <<"\n";
}
