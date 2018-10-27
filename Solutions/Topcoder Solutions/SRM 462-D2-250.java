package codes;

public class Archery {
    public double expectedPoints(int N, int[] ringPoints) {
        double exp = 0;
        double totArea = (N + 1) * (N + 1) * Math.PI;
        for (int i = 0; i <= N; i++) {
            double areaHere = (Math.PI * (i + 1) * (i + 1) - (Math.PI * i * i)) / (totArea);
            exp += ringPoints[i] * areaHere;
        }
        return exp;
    }
}
