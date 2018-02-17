import java.util.TreeSet;

/**
 * Created by omar on 4/15/17.
 */
public class BinarySearchable {
    public int howMany(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        int max [] = new int[n];
        int min [] = new int[n];
        max[0] = arr[0];
        min[n - 1] = arr[n - 1];
        for (int i = 1; i < n; i++) {
            max[i] = Math.max(arr[i], max[i - 1]);
            min[n - i - 1] = Math.min(arr[n - i - 1], min[n - i]);
        }

        int res = n;
        for (int i = 0; i < n; i++) {
            if ((i > 0 && max[i - 1] > arr[i]) || (i < n - 1 && min[i + 1] < arr[i]))
                res--;
        }
        return res;
    }
    public static void main (String [] args) {
        System.out.println(new BinarySearchable().howMany(new int [] { 1, 3, 2 }));
    }
}
