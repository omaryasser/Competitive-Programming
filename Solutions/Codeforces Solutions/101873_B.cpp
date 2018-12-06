#include <bits/stdc++.h>
using namespace std;
#define ll long long
#define mp(x, y) make_pair(x, y)
#define all(x) x.begin(), x.end()
#define f(i, x, n) for (int i = x; i < n; i++)

const int K = 26;

struct Vertex {
    int next[K];
    int p = -1;
    char pch;
    int link = -1;
    int go[K];
    vector<int>indices;
    Vertex(int p=-1, char ch='$') : p(p), pch(ch) {
        fill(begin(next), end(next), -1);
        fill(begin(go), end(go), -1);
    }
};

vector<Vertex> t(1);

void add_string(string const& s, int idx) {
    int v = 0;
    for (char ch : s) {
        int c = ch - 'a';
        if (t[v].next[c] == -1) {
            t[v].next[c] = t.size();
            t.emplace_back(v, ch);
        }
        v = t[v].next[c];
    }
    t[v].indices.push_back(idx);
}

int go(int v, char ch);

int get_link(int v) {
    if (t[v].link == -1) {
        if (v == 0 || t[v].p == 0)
            t[v].link = 0;
        else
            t[v].link = go(get_link(t[v].p), t[v].pch);
    }
    return t[v].link;
}

int go(int v, char ch) {
    int c = ch - 'a';
    if (t[v].go[c] == -1) {
        if (t[v].next[c] != -1)
            t[v].go[c] = t[v].next[c];
        else
            t[v].go[c] = v == 0 ? 0 : go(get_link(v), ch);
    }
    return t[v].go[c];
}

void bfs(){
    queue<int>q;
    q.push(0);
    while(!q.empty()){
        int node = q.front(); q.pop();
        int suff_link = get_link(node);
        for(int x : t[suff_link].indices){
            t[node].indices.push_back(x);
        }
        f(i, 0, K){
            if(t[node].next[i] != -1){
                q.push(t[node].next[i]);
            }
        }
    }
}
int main () {
    ios_base::sync_with_stdio(0); cin.tie(0);

    string s;
    int n;
    cin >> s >> n;
    vector<pair<int,string> >in(n);
    f(i, 0, n){
        cin >> in[i].first >> in[i].second;
        add_string(in[i].second, i);
    }
    bfs();
    vector<int> positions[n];
    int state = 0;
    f(i, 0, (int)s.length()){
        state = go(state, s[i]);
        for(int idx : t[state].indices){
            positions[idx].push_back(i);
        }
    }
    vector<int> res(n, (int)1e6 + 3);
    f(i, 0, n){
       f(i1, 0, (int)positions[i].size() - in[i].first + 1){
           int i2 = i1 + in[i].first - 1;
           if(i2 >= (int)positions[i].size())break;
           res[i] = min(res[i], positions[i][i2] - (positions[i][i1] - (int)in[i].second.length() + 1) + 1);
       }
    }
    f(i, 0, n){
        if(res[i] >= 1e6){
            cout << "-1\n";
        }else{
            cout << res[i] << "\n";
        }
    }
}