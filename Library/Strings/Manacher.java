static int manacher(char[] ss)
    {
        char [] s = new char[2 * ss.length + 1];
        for (int i = 0; i < 2 * ss.length + 1; i++) {
            if ((i & 1) != 0) s[i] = ss[i / 2];
            else s[i] = '#';
        }
        int n = s.length, d[] = new int[n];
        for(int i = 0, l = 0, r = -1; i < n; ++i)
        {
            int k = i > r ? 1 : Math.min(r - i + 1, d[l + r - i]);
            while(i + k < n && i - k >= 0 && s[i + k] == s[i - k])
                ++k;
            d[i] = k--;
            if(i + k > r) { l = i - k; r = i + k; }
        }

        int max = d[0];
        for (int i = 1; i < n; i++)
            max = Math.max(max, d[i]);
        return max - 1;
    }
