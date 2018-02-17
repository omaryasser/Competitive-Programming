import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair implements Comparable<Pair>{
		int w;
		int p;
		int idx;
		Pair (int ww  ,int pp , int i){
			w = ww;
			p = pp;
			idx = i + 1;
		}
		
		
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return w - o.w;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		PrintWriter out = new PrintWriter(System.out);
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		String line ;
		ArrayList<Pair> pairs = new ArrayList<>();
		int i = 0;
		while (bf.ready()) {
			line = bf.readLine();
			st = new StringTokenizer(line, " ");
			pairs.add(new Pair (Integer.parseInt(st.nextToken()) , Integer.parseInt(st.nextToken()) , i ++ ));
		}
		Collections.sort(pairs);
		int N = pairs.size();
		int P [] = new int[N];
		P[0] = 0;
		int lis_start = 0;
		int lis = 1;
		int LIS [] = new int[N];
		Arrays.fill(LIS, 1);
		
		for (i = 1 ; i < N ; ++i) {
			Pair cur = pairs.get(i);
			P[i] = i;
			for (int j =  0 ; j < i ; ++j) {
				if (cur.w > pairs.get(j).w && cur.p < pairs.get(j).p && LIS[j] + 1 > LIS[i]) {
					P[i] = j;
					LIS[i] = LIS[j] + 1;
					if (LIS[i] > lis) {
						lis_start = i;
						lis = LIS[i];
					}
				}
			}
		}
		sb.append(lis + "\n");
		Stack<Integer> res = new Stack<>();
		while (P[lis_start] != lis_start) {
			res.push(pairs.get(lis_start).idx);
			lis_start = P[lis_start];
		}
		res.push(pairs.get(lis_start).idx);
		while (!res.isEmpty()) sb.append(res.pop() + "\n");
		out.print(sb);
		out.flush();
		out.close();
	}
	
}
