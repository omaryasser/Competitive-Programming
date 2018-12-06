import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

public class H {
    public static void main(String[] args) throws Throwable {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> c = new HashMap<>();
        HashMap<String, String> s = new HashMap<>();

        while (n-- > 0) {
            String x = br.readLine();
            HashSet<String> set = new HashSet<>();
            for (int i = 0; i < x.length(); i++) {
                String sub = "";
                for (int j = i; j < x.length(); j++) {
                    sub += x.charAt(j);
                    if (set.contains(sub))
                        continue;
                    set.add(sub);
                    c.put(sub, c.getOrDefault(sub, 0) + 1);
                    s.put(sub, x);
                }
            }
        }


        int q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            String x = br.readLine();
            pw.println(c.getOrDefault(x, 0) + " " + s.getOrDefault(x, "-"));
        }

        pw.flush();
        pw.close();
    }
}
