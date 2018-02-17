/**
 * Created by omar on 20/07/17.
 */
public class BirthdayOdds {
    public int minPeople(int percent, int days) {
        for (int people = 2; ; people++) {
            double percentHere = 1;
            for (int j = 0; j < people; j++) {
                percentHere *= (1.0 * (days - j) / days);
            }
            percentHere = (1 - percentHere) * 100;
            if (percentHere >= percent) return people;
        }
    }
}
