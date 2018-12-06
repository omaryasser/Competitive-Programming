import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by omar on 07/04/18.
 */
public class D {

    static TreeMap<Long, PriorityQueue<Integer>> treeMap = new TreeMap<>();
    static HashMap<Long,Integer> alone = new HashMap<>();

    static void init (int idx, long num) {
        if (treeMap.containsKey(num)) {
            treeMap.get(num).add(idx);
        } else if (alone.containsKey(num)) {
            if (!treeMap.containsKey(num))
                treeMap.put(num, new PriorityQueue<>());
            treeMap.get(num).add(alone.remove(num));
            treeMap.get(num).add(idx);
        } else {
            alone.put(num, idx);
        }
    }

    static void remove (long num, PriorityQueue<Integer> priorityQueue) {
        if (priorityQueue.isEmpty()) {
            treeMap.remove(num);
        }
        else if (priorityQueue.size() == 1) {
            alone.put(num, priorityQueue.poll());
            treeMap.remove(num);
        }
    }


    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            init(i,  sc.nextLong());
        }
        while (!treeMap.isEmpty()) {
            Map.Entry<Long,PriorityQueue<Integer>> smallesEntry = treeMap.firstEntry();
            long num = smallesEntry.getKey();
            PriorityQueue<Integer> priorityQueue = smallesEntry.getValue();
            priorityQueue.poll();
            int idx = priorityQueue.poll();

            remove(num, priorityQueue);
            init(idx, num << 1L);
        }


        ArrayList<Pair> arrayList = new ArrayList<>();
        for (Map.Entry<Long,PriorityQueue<Integer>> mp : treeMap.entrySet()) {
            PriorityQueue<Integer> pq = mp.getValue();
            long num = mp.getKey();
            while (!pq.isEmpty())
                arrayList.add(new Pair(pq.poll(), num));
        }

        for (Map.Entry<Long,Integer> mp : alone.entrySet()) {
            long num = mp.getKey();
            arrayList.add(new Pair(mp.getValue(), num));
        }

        Collections.sort(arrayList, (a, b) -> a.idx - b.idx);
        out.println(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            if (i > 0) {
                out.print(" ");
            }
            out.print(arrayList.get(i).num);
        }
        out.println();
        out.flush();
    }

    static class Pair {
        int idx;
        long num;
        Pair (int i, long n) {
            idx = i;
            num = n;
        }
    }
    static class Scanner {
        StringTokenizer st;
        BufferedReader bf;

        Scanner(InputStream in) {
            bf = new BufferedReader(new InputStreamReader(in));
        }

        String next() throws Exception {
            while (st == null || !st.hasMoreTokens()) {
                st = new StringTokenizer(bf.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws Exception {
            return Integer.parseInt(next());
        }

        long nextLong() throws Exception {
            return Long.parseLong(next());
        }
    }
}
