package codes;

public class TheBoringStoreDivTwo {
    public String find(String J, String K) {
        char[] A = J.toCharArray(), B = K.toCharArray();
        String best = "";
        for (int s1 = 0; s1 < A.length - 1; s1++) { // A
            for (int e1 = s1; e1 < A.length - 1; e1++) {
                for (int s2 = e1 + 1; s2 < A.length; s2++) { // B
                    for (int e2 = s2; e2 < A.length; e2++) {
                        for (int s11 = 0; s11 < B.length - 1; s11++) { // C
                            for (int e11 = s11; e11 < B.length - 1; e11++) {
                                for (int s22 = e11 + 1; s22 < B.length; s22++) { // D
                                    for (int e22 = s22; e22 < B.length; e22++) {
                                        if (e1 - s1 + e22 - s22 == e2 - s2 + e11 - s11) {
                                            char[] first = new char[e2 - s2 + e11 - s11 + 2];
                                            int idx = 0;
                                            for (int k = s1; k <= e1; k++, idx++)
                                                first[idx] = A[k];
                                            for (int k = s22; k <= e22; k++, idx++)
                                                first[idx] = B[k];

                                            idx = 0;
                                            boolean same = true;
                                            for (int k = s2; k <= e2; k++, idx++)
                                                same &= A[k] == first[idx];
                                            for (int k = s11; k <= e11; k++, idx++)
                                                same &= B[k] == first[idx];
                                            if (same) {
                                                String r = new String(first);
                                                if (e2 - s2 + e11 - s11 + 2 > best.length())
                                                    best = r;
                                                else if (e2 - s2 + e11 - s11 + 2 == best.length())
                                                    best = best.compareTo(r) < 0 ? best : r;
                                            }
                                        }
                                        if (e1 - s1 + e11 - s11 == e2 - s2 + e22 - s22) {
                                            char[] first = new char[e1 - s1 + e11 - s11 + 2];
                                            int idx = 0;
                                            for (int k = s1; k <= e1; k++, idx++)
                                                first[idx] = A[k];
                                            for (int k = s11; k <= e11; k++, idx++)
                                                first[idx] = B[k];

                                            idx = 0;
                                            boolean same = true;
                                            for (int k = s2; k <= e2; k++, idx++)
                                                same &= A[k] == first[idx];
                                            for (int k = s22; k <= e22; k++, idx++)
                                                same &= B[k] == first[idx];
                                            if (same) {
                                                String r = new String(first);
                                                if (e1 - s1 + e11 - s11 + 2 > best.length())
                                                    best = r;
                                                else if (e1 - s1 + e11 - s11 + 2 == best.length())
                                                    best = best.compareTo(r) < 0 ? best : r;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return best;
    }
}
