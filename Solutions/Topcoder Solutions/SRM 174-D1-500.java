import java.util.*;

/**
 * Created by omar on 23/07/17.
 */
public class RangeGame {
    TreeSet<Integer> borders = new TreeSet<>();
    HashMap<Integer,Integer> map = new HashMap<>();
    HashMap<Integer,Integer> mapReverse = new HashMap<>();
    int mapIdx;
    class Interval {
        int s, e;
        Interval (int ss, int ee) {s = ss; e = ee;}
    }
    Interval[][] intervals;
    Interval[][] bad;
    public int[] bestDoors(String[] possible, String[] hints) {
        intervals = new Interval[possible.length][];
        bad = new Interval[hints.length][];
        for (int i = 0; i < possible.length; i++) {
            StringTokenizer st = new StringTokenizer(possible[i]);
            intervals[i] = new Interval[st.countTokens()];
            int idx = 0;
            while (st.hasMoreTokens()) {
                StringTokenizer st2 = new StringTokenizer(st.nextToken(), "-");
                if (st2.countTokens() == 1) {
                    int s;
                    intervals[i][idx++] = (new Interval(s = Integer.parseInt(st2.nextToken()), s));
                    borders.add(s);
                } else {
                    int s, e;
                    intervals[i][idx++] = (new Interval(s = Integer.parseInt(st2.nextToken()), e = Integer.parseInt(st2.nextToken())));
                    borders.add(s);
                    borders.add(e);
                }
            }
        }
        for (int i = 0; i < hints.length; i++) {
            StringTokenizer st = new StringTokenizer(hints[i]);
            bad[i] = new Interval[st.countTokens()];
            int idx = 0;
            while (st.hasMoreTokens()) {
                StringTokenizer st2 = new StringTokenizer(st.nextToken(), "-");
                if (st2.countTokens() == 1) {
                    int s;
                    bad[i][idx++] = (new Interval(s = Integer.parseInt(st2.nextToken()), s));
                    borders.add(s);
                } else {
                    int s, e;
                    bad[i][idx++] = (new Interval(s = Integer.parseInt(st2.nextToken()), e = Integer.parseInt(st2.nextToken())));
                    borders.add(s);
                    borders.add(e);
                }
            }
        }

        for (int x : borders) {
            if (map.containsKey(x)) continue;
            map.put(x, mapIdx);
            mapReverse.put(mapIdx++, x);
        }

        int[] res = new int[hints.length + 1];
        res[0] = solve();
        for (int i = 1; i <= hints.length; i++) {
            change(i - 1);
            res[i] = solve();
        }
        return res;
    }

    void change (int badIdx) {
        Interval[] badIntervals = bad[badIdx];
        for (Interval badInterval : badIntervals) {
            for (int i = 0; i < intervals.length; i++) {
                if (intervals[i] != null) {
                    for (int j = 0; j < intervals[i].length; j++) {
                        if (badInterval.e < intervals[i][j].s || badInterval.s > intervals[i][j].e);
                        else {
                            intervals[i] = null;
                            break;
                        }
                    }
                }
            }
        }
    }
    int solve () {
        int cntOcc[] = new int[mapIdx + 2];
        for (Interval[] i : intervals) {
            if (i != null) {
                for (Interval interval : i) {
                    System.err.println(interval.s + " " + interval.e + " " + map.get(interval.s) + " " + map.get(interval.e));
                    cntOcc[map.get(interval.s)]++;
                    cntOcc[map.get(interval.e) + 1]--;
                }
            }
        }
        int mostOcc = 0;
        for (int i = 1; i < mapIdx + 2; i++) {
            cntOcc[i] += cntOcc[i - 1];
            if (cntOcc[i] > cntOcc[mostOcc])
                mostOcc = i;
        }
        return mapReverse.get(mostOcc);
    }
}
