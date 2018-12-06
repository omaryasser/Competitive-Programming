#include <bits/stdc++.h>

#define ll long long
#define all(x) x.begin(), x.end()
#define f(i, j, k) for (int i = j; i < k; i++)

using namespace std;


int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n, m; cin >> n >> m;
    vector<int> a, b;
    int lo = 0, hi = 1e6, best= -1;
    vector<int> best_a, best_b;
    while(lo <=  hi){
        int mid = lo + hi >> 1;
        a.clear(); b.clear();
        int n_ = n, m_ = m, mid_ = mid;
        while(mid_ && (n_ >= mid_ || m_ >= mid_)){
            if(n_ > m_){
                n_ -= mid_;
                a.push_back(mid_);
            }else{
                m_ -= mid_;
                b.push_back(mid_);
            }
            mid_--;
            if(!mid_)break;
        }
//        cerr << mid << " " << mid_ << "\n";
        if(!mid_){
            best = mid;
            best_a = a, best_b = b;
            lo = mid + 1;
        }else hi = mid - 1;
    }
    cout << (int)best_a.size() << "\n";
    f(i, 0, (int)best_a.size()){
        if(i)cout << " ";
        cout << best_a[i];
    }
    cout << "\n";

    cout << (int)best_b.size() << "\n";
    f(i, 0, (int)best_b.size()){
        if(i)cout << " ";
        cout << best_b[i];
    }
    cout << "\n";
}