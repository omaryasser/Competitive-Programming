// all submasks of all masks, complexity is 3 ^ n
    static ArrayList<Integer> generateSubMasks(int mask) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int maskCpy = mask; ; maskCpy = (maskCpy - 1) & mask) {
            res.add(maskCpy);
            if (maskCpy == 0) break;
        }
        return res;
    }
