package codes;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class SpaceWarDiv2 {
    boolean can (int[] magicalGirlStrength, int[] enemyStrength, int[] enemyCount, int MAX) {
        TreeMap<Integer,Integer> enemies = new TreeMap<>();
        for (int i = 0; i < enemyCount.length; i++)
            for (int j = 0; j < enemyCount[i]; j++)
                enemies.put(enemyStrength[i], enemies.getOrDefault(enemyStrength[i], 0) + 1);
        for (int strength : magicalGirlStrength) {
            for (int cnt = 0; cnt < MAX; cnt++) {
                Map.Entry<Integer,Integer> was = enemies.floorEntry(strength);
                if (was != null) {
                    if (was.getValue().intValue() == 1) enemies.remove(was.getKey());
                    else enemies.put(was.getKey(), was.getValue() - 1);
                }
            }
        }
        return enemies.isEmpty();
    }
    public int minimalFatigue(int[] magicalGirlStrength, int[] enemyStrength, int[] enemyCount) {
        int lo = 0, hi = 100 * 100, res = -1;
        while (lo <= hi) {
            int mid = (lo + hi) >> 1;
            if (can(magicalGirlStrength, enemyStrength, enemyCount, mid)) {
                res = mid;
                hi = mid - 1;
            } else
                lo = mid + 1;
        }
        return res;
    }
}
