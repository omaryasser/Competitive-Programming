package codes;

public class Surveyor {
    int area() 		
    {
        long area = 0;
        for(int i = 0; i < g.length - 1; ++i)
            area += g[i].x * g[i+1].y - g[i].y * g[i+1].x;
        return (int)(Math.abs(area) / 2);
    }
    Point [] g;
    class Point {
        long x, y;
        Point (long xx, long yy) {
            x = xx;
            y = yy;
        }
    }
    public int area(String direction, int[] length) {
        g = new Point[direction.length() + 1];
        g[0] = g[g.length - 1] = new Point(0, 0);
        for (int i = 1; i < direction.length(); i++) {
            if (direction.charAt(i - 1) == 'N' || direction.charAt(i - 1) == 'S') {
               g[i] = new Point(g[i - 1].x, g[i - 1].y + (direction.charAt(i - 1) == 'S' ? - 1 : 1) * length[i - 1]);
            } else {
                g[i] = new Point(g[i - 1].x + (direction.charAt(i - 1) == 'E' ? - 1 : 1) * length[i - 1], g[i - 1].y);
            }
        }
        return area();
    }
}
