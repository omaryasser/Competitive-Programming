import java.util.TreeSet;

/**
 * Created by omar on 25/07/17.
 */
public class RugSizes {
    public int rugCount(int area) {
        TreeSet<Pair> set = new TreeSet<>();
        for (int i = 1; i <= area; i++) {
            if (area % i == 0) {
                if ((i & 1) == 1 || ((area / i) & 1) == 1 || i == area / i)
                    set.add(new Pair(i, area / i));
            }
        }
        return set.size();
    }
    class Pair implements Comparable<Pair>{
        int a, b;
        Pair (int aa, int bb) {
            a = Math.min(aa, bb);
            b = Math.max(aa, bb);
        }

        @Override
        public int compareTo(Pair pair) {
            return a != pair.a ? a - pair.a : b - pair.b;
        }
    }
}
