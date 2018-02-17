package codes;

public class Jumping {
    public String ableToGet(int x, int y, int[] jumpLengths) {
        int lo = jumpLengths[0], hi = jumpLengths[0];
        for (int i = 1; i < jumpLengths.length; i++) {
            int loRangeL = lo - jumpLengths[i], loRangeR = hi - jumpLengths[i];
            if (loRangeL >= 0) lo = loRangeL;
            else if (loRangeR < 0) lo = -loRangeR;
            else lo = 0;
            hi += jumpLengths[i];
        }
        return x * x + y * y >= 1l * lo * lo && x * x + y * y <= 1l * hi * hi ? "Able" : "Not able";

    }
}
