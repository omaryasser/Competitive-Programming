#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

class SpecialStrings {
public:
    bool valid(string s, int n) {
        for(int i = 0; i < n - 1; i++) {
            if(s.substr(0, i + 1) >= s.substr(i + 1, n - (i + 1)))
                return false;
        }
        return true;
    }
    string findNext(string current) {
        int n = (int) current.length();
        string res = string(n, '1');
        for(int i = 0; i < n; i++) {
            res[i] = '0';
            bool ok = res > current && valid(res, n);
            if(!ok) res[i] = '1';
        }
        if(res == string(n, '1'))
            return "";
        return res;
    }
};
