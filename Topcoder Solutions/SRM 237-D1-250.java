import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by omar on 01/08/17.
 */
public class BoxUnion {
    Event[] eventsV, eventsH;
    int n;
    int union () {
        Arrays.sort(eventsV, new verticalSorter());
        Arrays.sort(eventsH, new horizontalSorter());
        boolean[] inSet = new boolean[n];
        int area = 0;
        inSet[eventsH[0].rectangleIdx] = true;
        for (int i = 1; i < 2 * n; i++) {
            Event cur = eventsH[i];
            int cnt = 0;
            int delta_x = cur.p.x - eventsH[i - 1].p.x;
            int yStart = 0;
            for (int j = 0; j < 2 * n; j++) {
                if (inSet[eventsV[j].rectangleIdx]) {
                    if (eventsV[j].isLeft) { // here left means bottom
                        if (cnt == 0) yStart = eventsV[j].p.y;
                        cnt++;
                    } else {
                        cnt--;
                        if (cnt == 0) {
                            int delta_y = eventsV[j].p.y - yStart;
                            area += delta_x * delta_y;
                        }
                    }
                }
            }
            inSet[cur.rectangleIdx] = cur.isLeft;
        }
        return area;
    }
    public int area(String[] rectangles) {
        n = rectangles.length;
        eventsV = new Event[2 * n];
        eventsH = new Event[2 * n];
        for (int i = 0; i < n; i++) {
            String[] s = rectangles[i].split(" ");
            Point left = new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
            Point right = new Point(Integer.parseInt(s[2]), Integer.parseInt(s[3]));
            eventsV[i << 1] = new Event(left, true, i); eventsV[i << 1 | 1] = new Event(right, false, i);
            eventsH[i << 1] = new Event(left, true, i); eventsH[i << 1 | 1] = new Event(right, false, i);
        }
        return union();
    }

    class verticalSorter implements Comparator<Event> {
        @Override
        public int compare(Event e1, Event e2) {
            return e1.p.y - e2.p.y;
        }
    }
    class horizontalSorter implements Comparator<Event> {
        @Override
        public int compare(Event e1, Event e2) {
            return e1.p.x - e2.p.x;
        }
    }
    class Event {
        Point p;
        boolean isLeft;
        int rectangleIdx;
        Event (Point pp, boolean left, int rI) {p = pp; isLeft = left; rectangleIdx = rI;}
    }
    class Point {
        int x, y;
        Point (int xx, int yy) {x = xx; y = yy;}
    }
}
