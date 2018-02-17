import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main (String [] args) throws Exception{
		Scanner  sc = new Scanner (System.in);
		StringBuilder sb = new StringBuilder ();
		PrintWriter out = new PrintWriter(System.out);
		for (int T = 1 ; T <= 10 ; ++T){
			int n = sc.nextInt();
			char [] arr = sc.next().toCharArray();
			int  [] num = new int[n];
			for (int i = 0 ; i < n ; ++i)
				num [i] = arr[i] == '(' ? 1 : 0;
			
			SegmentTree segmentTree = new SegmentTree(num);
			
			sb.append("Test " + T + ":\n");
			int q = sc.nextInt();
			while (q -- > 0){
				int k = sc.nextInt();
				if (k == 0){
					if(segmentTree.tree[1].closedNotOpened == 0 && segmentTree.tree[1].openedNotClosed == 0){
						sb.append("YES\n");
					}
					else {
						sb.append("NO\n");
					}
				}
				else {
					segmentTree.update(1, 0, n - 1, k - 1);
				}
			}
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	
	static class Node {
		int openedNotClosed;
		int closedNotOpened;
		Node (int o , int c){
			openedNotClosed = o;
			closedNotOpened = c;
		}
	}
	static class SegmentTree {
		Node tree [] ;
		int arr [] ;
		int n ;
		SegmentTree (int a []){
			arr = a;
			n = arr.length;
			tree = new Node[(n << 1) << 1];
			build (1 , 0 , n - 1);
		}
		
		void build (int node , int start , int end){
			if (start == end){
				tree[node] = new Node (arr[start] == 1 ? 1 : 0 , arr[start] == 1 ? 0 : 1);
			}
			else {
				int mid = (start + end) >> 1;
				build (node << 1 , start , mid);
				build (node << 1 | 1 , mid + 1 , end);
				
				int openedL = tree[node << 1].openedNotClosed;
				int openedR = tree[node << 1 | 1].openedNotClosed;
				int closedL = tree[node << 1].closedNotOpened;
				int closedR = tree[node << 1 | 1].closedNotOpened;
				
				int opened = openedR + Math.max(0, openedL - closedR);
				int closed = closedL + Math.max(0, closedR - openedL);
				Node current = new Node(opened , closed);
				tree[node] = current;
			}
		}
		
		void update (int node , int start , int end , int idx){
			if (start == end){
				Node was = new Node(tree[node].openedNotClosed , tree[node].closedNotOpened);
				tree[node] = new Node(was.closedNotOpened , was.openedNotClosed);
			}
			else {
				int mid = (start + end) >> 1;
				if (idx <= mid){
					update (node << 1 , start , mid , idx);
					int openedL = tree[node << 1].openedNotClosed;
					int openedR = tree[node << 1 | 1].openedNotClosed;
					int closedL = tree[node << 1].closedNotOpened;
					int closedR = tree[node << 1 | 1].closedNotOpened;
					
					int opened = openedR + Math.max(0, openedL - closedR);
					int closed = closedL + Math.max(0, closedR - openedL);
					Node current = new Node(opened , closed);
					tree[node] = current;
				}
				else {
					update (node << 1 | 1, mid + 1 , end , idx);
					int openedL = tree[node << 1].openedNotClosed;
					int openedR = tree[node << 1 | 1].openedNotClosed;
					int closedL = tree[node << 1].closedNotOpened;
					int closedR = tree[node << 1 | 1].closedNotOpened;
					
					int opened = openedR + Math.max(0, openedL - closedR);
					int closed = closedL + Math.max(0, closedR - openedL);
					Node current = new Node(opened , closed);
					tree[node] = current;
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }


    }
}
