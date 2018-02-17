        // If you just check cumXOR[r] = cumXOR[l - 1] This is not enough {1, 2, 3}
        // So You may use randomization to avoid these sets
        // use 4-5 different hash codes and check if all of them produce cumXOR[r] = cumXOR[l - 1]
        int n = sc.nextInt();
        int m = sc.nextInt();
        HashSet<Long> taken = new HashSet<>();
        HashMap<Integer,Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            while (true) {
                long random = (long) (Math.random() * 1e18);
                if (!taken.contains(random)) {
                    taken.add(random);
                    map.put(i, random);
                    break;
                }
            }
        }

        long cumXOR[] = new long[m];
        cumXOR[0] = map.get(sc.nextInt() - 1) ^ map.get(sc.nextInt() - 1);
        for (int i = 1; i < m; i++) {
            cumXOR[i] = cumXOR[i - 1] ^ map.get(sc.nextInt() - 1) ^ map.get(sc.nextInt() - 1);
        }

        int q = sc.nextInt();
        while (q-- > 0) {
            int l = sc.nextInt() - 1, r = sc.nextInt() - 1;
            long res = cumXOR[r] - (l == 0 ? 0 : cumXOR[l - 1]);
            out.println(res == 0 ? "Yes" : "No");
        }
