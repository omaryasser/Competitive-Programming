// CPP 
int get_min (vector<int> cycles , int k) {
        // a are the distinct numbers
        // m[i] the number of times number i is there
        // dp[i] = 1 if we can get i as a sum, zero otherwise.
        
            set<int> a;
            map<int,int> m;
            for (int i = 0; i < cycles.size(); i++)
            	{
            		m[cycles[i]]++;
            		a.insert(cycles[i]);
            	}

            bitset<MAX_K> dp;
             dp[0] = 1;
             set<int> ::iterator it;
             for (it = a.begin(); it != a.end() ; it++) {
               for (int x = 0; (1<<x) <= m[*it]; x++) {
                 dp |= (dp << ((*it)*(1<<x)));
                 m[*it] -= (1<<x);
               }
               dp |= (dp << ((*it)*m[*it]));
             }
             	return dp[k] == 1 ? k : k + 1;
        }



public static boolean subsetSum(int input[], int total) {

        boolean T[][] = new boolean[input.length + 1][total + 1];
        for (int i = 0; i <= input.length; i++) {
            T[i][0] = true;
        }

        for (int i = 1; i <= input.length; i++) {
            for (int j = 1; j <= total; j++) {
                if (j - input[i - 1] >= 0) {
                    T[i][j] = T[i - 1][j] || T[i - 1][j - input[i - 1]];
                } else {
                    T[i][j] = T[i-1][j];
                }
            }
        }
        return T[input.length][total];

    }
