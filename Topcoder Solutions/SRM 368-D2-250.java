package codes;

import java.util.HashMap;

public class PirateTreasure {
    String[] names = {"NORTH", "SOUTH", "EAST", "WEST", "NORTHEAST", "NORTHWEST", "SOUTHEAST", "SOUTHWEST"};
    double sq = Math.sqrt(2);
    double[] dx = {0,0,1,-1,1/sq,-1/sq,1/sq,-1/sq};
    double[] dy = {1,-1,0,0,1/sq,1/sq,-1/sq,-1/sq};
    public double getDistance(int[] steps, String[] directions) {
        HashMap<String,Integer> map = new HashMap<>();
        for (int i = 0; i < names.length; i++)
            map.put(names[i], i);

        double x = 0, y = 0;
        for (int i = 0; i < steps.length; i++) {
            x += steps[i] * dx[map.get(directions[i])];
            y += steps[i] * dy[map.get(directions[i])];
        }
        return Math.sqrt(x * x + y * y);
    }
}
