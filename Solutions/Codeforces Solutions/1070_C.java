import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class C {
    static ArrayList<Event>[] changes;
    static long[] cntPrices, prices;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);


        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        changes = new ArrayList[n+3];
        for (int i = 0; i < changes.length; i++)
            changes[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            int pr = Integer.parseInt(st.nextToken());

            changes[l].add(new Event(1, pr, cnt));
            changes[r].add(new Event(-1, pr, cnt));
        }


//        System.err.println(Arrays.toString(changes));
        long curAns = 0;
        long tot = 0;
        boolean updated = true;
        int maxDay = 1000005;
        cntPrices = new long[maxDay+2];
        prices = new long[maxDay+2];
        for (int d = 1; d <= n; d++) {
            if(!changes[d].isEmpty())
                updated = true;
            if(!updated){
                tot += curAns;
//                System.err.println(curAns);
                continue;
            }

            updated = false;

            for (Event e: changes[d]) {
                if(e.type != 1)
                    continue;
                update(prices, e.price, 1l*e.cores*e.price);
                update(cntPrices, e.price, 1l*e.cores);
                updated = true;
            }

            if(get(cntPrices, maxDay) <= k){
                curAns = get(prices, maxDay);
                tot += curAns;

                for (Event e: changes[d]) {
                    if(e.type != -1)
                        continue;
                    update(prices, e.price, 1l*e.type*e.cores*e.price);
                    update(cntPrices, e.price, 1l*e.type*e.cores);
                    updated = true;
                }

//                System.err.println(curAns);
                continue;
            }


            int lo = 1;
            int hi = 1000002;
            while(lo < hi){
                int mid = lo + (hi-lo)/2;
                if(get(cntPrices, mid) < k)
                    lo = mid+1;
                else
                    hi = mid;
            }

            curAns = get(prices, lo-1);
            long rem = k - get(cntPrices, lo-1);
            curAns += 1l*lo*rem;

            tot += curAns;

            for (Event e: changes[d]) {
                if(e.type != -1)
                    continue;
                update(prices, e.price, 1l*e.type*e.cores*e.price);
                update(cntPrices, e.price, 1l*e.type*e.cores);
                updated = true;
            }

//            System.err.println(curAns);
        }

        pw.println(tot);
        pw.flush();
        pw.close();

    }

    static long get(long[] arr, int idx){
        long ans = 0;
        if(idx == 0)
            return 0;
        while(idx > 0){
            ans += arr[idx];
            idx -= idx&-idx;
        }

        return ans;
    }

    static void update(long[] arr, int idx, long val){
        while(idx < arr.length){
            arr[idx] += val;
            idx += idx&-idx;
        }
    }

    static class Event{
        int type, price, cores;

        public Event(int type, int price, int cores) {
            this.type = type;
            this.price = price;
            this.cores = cores;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "type=" + type +
                    ", price=" + price +
                    ", cores=" + cores +
                    '}';
        }
    }
}
