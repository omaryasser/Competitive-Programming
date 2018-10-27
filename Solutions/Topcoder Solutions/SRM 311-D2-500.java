/**
 * Created by omar on 4/15/17.
 */
public class MatchNumbersEasy {
    static int [] arr;
    static String [] memo;

    static String max(String a, String b) {
        if (a.length() != b.length())
            return a.length() > b.length() ? a : b;
        for (int i = 0; i < a.length(); i++)
            if (a.charAt(i) != b.charAt(i))
                return a.charAt(i) > b.charAt(i) ? a : b;
        return a;
    }

    static String dp(int rem, int all) {
        if (rem == 0) return "";
        if (memo[rem] != null) return memo[rem];

        String best = "";
        for (int i = rem == all ? 1 : 0; i < arr.length; i++) {
            if (arr[i] <= rem)
                best = max(best, i + dp(rem - arr[i], all));
        }
        return memo[rem] = best;
    }
    public static String maxNumber(int[] matches, int nn) {
        arr = matches;
        memo = new String[nn + 1];

        String res = dp(nn, nn);
        return res.length() == 0 ? "0" : res;
    }

    public static void main (String [] args) {
        System.out.println(MatchNumbersEasy.maxNumber(new int [] {6,7,8}, 21));
    }
}
