#include<bits/stdc++.h>
using namespace std;

const int n_ = 180;
double memo[n_][n_][n_];
class PrimeSoccer {
public :
	double a, b;
	bool isPrime (int n) {
		if(n == 1 || !n) {
			return false;
		}
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	double dp (int idx, int A, int B) {
		if(idx == 18) {
			return isPrime(A) || isPrime(B);
		}

		double &ret = memo[idx][A][B];
		if(ret != -1) {
			return ret;
		}

		return ret = (1 - a) * (1 - b) * dp (idx + 1, A, B) +
				a * (1 - b) * dp(idx + 1, A + 1, B) +
				(1 - a) * b * dp(idx + 1, A, B + 1) +
					a * b * dp(idx + 1, A + 1, B + 1);
	}
	double getProbability(int skillOfTeamA, int skillOfTeamB) {
		a = skillOfTeamA / 100.0;
		b = skillOfTeamB / 100.0;
		for (int i = 0; i < n_; i++) {
			for (int j = 0; j < n_; j++) {
				for (int k = 0; k < n_; k++) {
					memo[i][j][k] = -1;
				}
			}
		}
		return dp(0, 0, 0);
	}
};
