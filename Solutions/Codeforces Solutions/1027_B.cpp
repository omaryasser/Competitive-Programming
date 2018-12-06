#include <bits/stdc++.h>


typedef long long ll;

using namespace std;

int d[] = {1, -1};
int main()
{
	ios_base::sync_with_stdio(0); cin.tie(0);

    ll n, q;
    cin >> n >> q;
    while(q--)
    {
        ll x, y;
        cin >> x >> y;
        if((x + y) & 1ll)
        {
            ll finished = (n * n + 1) / 2;
            ll last_row = x - 1;
            ll above = (last_row * n) / 2;
            ll beside = (x & 1ll) ? (y - 1) / 2 : y / 2;
            cout << finished + above + beside + 1 << "\n";
        }
        else
        {
            ll last_row = x - 1;
            ll above = (last_row * n + 1) / 2;
            ll beside = ((x & 1ll) == 0ll) ? (y - 1) / 2 : y / 2;
//            cerr << above << " " << beside << " " << (x & 1ll)==0 << "df " << (y-1) / 2<< "\n";
            cout << above + beside + 1 << "\n";
        }
    }
}