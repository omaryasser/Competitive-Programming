package codes;

public class HockeyFault {
    public int numPlayers(int width, int height, int x, int y, int[] px, int[] py) {
        int cnt = 0;
        double rad = height / 2;
        for (int i = 0; i < px.length; i++)
            cnt += insideC(x, y + rad, rad, px[i], py[i]) ||
                    insideC(x + width, y + rad, rad, px[i], py[i]) ||
                    insideS (x, y, height, width, px[i], py[i]) ? 1 : 0;
        return cnt;
    }

    boolean insideC (double xc, double yc, double r, double x, double y) {
        return (xc - x) * (xc - x) + (yc - y) * (yc - y) <= r * r + 1e-9;
    }
    boolean insideS (int x0, int y0, int h, int w, int x, int y) {
        return x >= x0 && x <= x0 + w && y >= y0 && y <= y0 + h;
    }
}
