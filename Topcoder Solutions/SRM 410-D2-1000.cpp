    #include <bits/stdc++.h>
    #define mp(x,y) make_pair(x,y)
    using namespace std;


    class ClosestRegex {

    public:
    		int INF = 1000;
    		string text , regex;
    		int lastRegex;
    		pair<string,int> memo[55][55];
    		pair<string , int> dp (int textIdx , int regexIdx) {
    			if(textIdx == (int)text.length()) {
    				if(regexIdx <= lastRegex) return memo[textIdx][regexIdx] = mp("" , INF);
    				else return memo[textIdx][regexIdx]= mp("" , 0);
    			}
    			if(regexIdx == (int)regex.length()) return memo[textIdx][regexIdx] = mp("" , INF);
    			if(memo[textIdx][regexIdx].second != - 1) return memo[textIdx][regexIdx];

    			if(regexIdx == (int)regex.length() - 1 || regex[regexIdx + 1] != '*') {
    				pair<string , int> nxt = dp(textIdx + 1 , regexIdx + 1);
    				return memo[textIdx][regexIdx] = mp(regex[regexIdx] + nxt.first , (regex[regexIdx] != text[textIdx]) + nxt.second);
    			}

    			pair<string , int> nxtIfMatch = dp(textIdx + 1 , regexIdx);
    			pair<string , int> match = mp(regex[regexIdx] + nxtIfMatch.first , (regex[regexIdx] != text[textIdx]) + nxtIfMatch.second);
    			pair<string , int> notMatch = dp(textIdx , regexIdx + 2);
    			cout << textIdx << " " << regexIdx << " " << nxtIfMatch.first << " " << nxtIfMatch.second << " " << match.first << " " << match.second << " " << notMatch.first <<  " " << notMatch.second << "\n";
    			return memo[textIdx][regexIdx] = (match.second == notMatch.second) ? (match.first < notMatch.first) ?
    					match : notMatch : (match.second < notMatch.second) ? match : notMatch;
    		}
    	string closestString(string t, string r) {
    		text = t , regex = r;
    		for (int i = 0 ; i < (int)r.length() ; ++ i)
    			if (i == (int)r.length() - 1 || r[i + 1] != '*') {
    				lastRegex = i;
    			} else i ++ ;
    		for (int i = 0 ; i < 51 ; ++ i) for (int j = 0 ; j < 51 ; ++ j) memo[i][j] = mp("" , - 1);
    		pair<string , int> res = dp(0 , 0);
    		if(res.second >= INF) return "" ;
    		else return res.first;
    	}
    };
    int main() {
    	ClosestRegex c;
    	cout << c.closestString("cbc", "a*b*") << "\n";
    	return 0;
    }
