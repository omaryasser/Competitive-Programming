import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class F {
    public static void main(String[] args) throws Throwable {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] val = new ArrayList[3];
        for (int i = 0; i < 3; i++)
            val[i] = new ArrayList<>();

        long ans = 0;
        int cntTotal = 0;
        int cntX = 0, cntY = 0;

        while (n-- > 0) {
            String[] sa = br.readLine().split(" ");
            int idx = Integer.parseInt(sa[0], 2);
            int v = Integer.parseInt(sa[1]);
            if (idx == 3) {
                ans += v;
                cntTotal++;
                cntX++;
                cntY++;
            } else
                val[idx].add(-v);
        }
        for (int i = 0; i < 3; i++)
            Collections.sort(val[i]);

        int mn = Math.min(val[1].size(), val[2].size());
        for (int i = 0; i < val[1].size() && i < val[2].size(); i++) {
            cntTotal += 2;
            cntX++;
            cntY++;
            ans -= val[1].get(i);
            ans -= val[2].get(i);
        }

        ArrayList<Integer> rem = new ArrayList<>();
        for (int i = mn; i < Math.max(val[1].size(), val[2].size()); i++) {
            if (i < val[1].size())
                rem.add(-val[1].get(i));
            else
                rem.add(-val[2].get(i));
        }

//        System.err.println(rem);

        int tmoTot = cntTotal;
        int tmpX = cntX;
        int tmpY = cntY;
        long tmpAns = 0;

        int lst = -1;
        for (int i = 0; i < rem.size(); i++) {
            if (Math.min(tmpX + 1, tmpY) * 2 >= tmoTot + 1) {
                tmpX++;
                tmoTot++;
                lst = i;
                tmpAns += rem.get(i);
            } else
                break;
        }

        long ans2 = tmpAns;

//        System.err.println(ans2);

        for (int i = 0; i < val[0].size(); i++) {
            tmoTot++;
            tmpAns -= val[0].get(i);
//            System.err.println(tmpAns);
            while (lst >= 0 && Math.min(tmpX, tmpY) * 2 < tmoTot) {
                tmpX--;
                tmoTot--;
                tmpAns -= rem.get(lst);
                lst--;
            }
//            System.err.println(tmpAns+" "+lst);
            if (Math.min(tmpX, tmpY) * 2 >= tmoTot) {
                ans2 = Math.max(ans2, tmpAns);
            } else
                break;
        }


        pw.println(ans + ans2);

        pw.flush();
        pw.close();
    }
}
