package codes;

public class Segments {
    public String intersection(int[] s1, int[] s2) {
        if (s1[0] != s1[2]) {
            s1[0] ^= s1[1]; s1[1] ^= s1[0]; s1[0] ^= s1[1];
            s1[2] ^= s1[3]; s1[3] ^= s1[2]; s1[2] ^= s1[3];
            s2[0] ^= s2[1]; s2[1] ^= s2[0]; s2[0] ^= s2[1];
            s2[2] ^= s2[3]; s2[3] ^= s2[2]; s2[2] ^= s2[3];
        }
        if (s2[0] == s2[2]) {
            if (s1[0] == s2[0]) {
                if (Math.max(s1[1], s1[3]) - Math.min(s1[1], s1[3]) + Math.max(s2[1], s2[3]) - Math.min(s2[1], s2[3]) ==
                        Math.max(s1[1], Math.max(s2[1], Math.max(s1[3], s2[3]))) - Math.min(s1[1], Math.min(s2[1], Math.min(s1[3], s2[3]))))
                    return "POINT";
                else if (Math.max(s1[1], s1[3]) - Math.min(s1[1], s1[3]) + Math.max(s2[1], s2[3]) - Math.min(s2[1], s2[3]) >
                        Math.max(s1[1], Math.max(s2[1], Math.max(s1[3], s2[3]))) - Math.min(s1[1], Math.min(s2[1], Math.min(s1[3], s2[3]))))
                    return "SEGMENT";
                else return "NO";
            } else return "NO";
        } else {
            if (s2[1] <= Math.max(s1[1], s1[3]) && s2[1] >= Math.min(s1[1], s1[3]) &&
                    s1[0] <= Math.max(s2[0], s2[2]) && s1[0] >= Math.min(s2[0], s2[2]))
                return "POINT";
            else return "NO";
        }
    }
}
