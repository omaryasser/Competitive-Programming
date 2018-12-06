import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class K {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        TreeSet<Integer> set = new TreeSet<>();
        set.add(arr[0]);
        for (int i = 1; i < n; i++) {
            arr[i] += arr[i-1];
            set.add(arr[i]);
        }

        int sum = arr[n-1];
        if(sum%k != 0){
            System.out.println("No");
            return;
        }
        int gp = sum/k;

        for (int i = gp; i <= sum; i+=gp) {
            if(!set.contains(i)){
                System.out.println("No");
                return;
            }
        }

        pw.println("Yes");

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(-1);
        for (int i = 0; i < n; i++) {
            if(arr[i] %gp == 0)
                ans.add(i);
        }
//        System.err.println(ans);

        for (int i = 1; i < ans.size(); i++) {
            if(i != 1)
                pw.print(" ");
            pw.print(ans.get(i) - ans.get(i-1));
        }
        pw.println();

        pw.flush();
        pw.close();
    }
}
