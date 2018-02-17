int N = 10000000;
int lp[] = new int[N + 1];
ArrayList<Integer> pr = new ArrayList<>();

for (int i = 2; i <= N; ++i) {
        if (lp[i] == 0) {
                lp[i] = i;
                pr.add(i);
        }
        for (int j = 0; j < pr.size() && pr.get(j) <= lp[i] && i * pr.get(j) <= N; ++j)
                lp[i * pr.get(j)] = pr.get(j);
}
