import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static class pair implements Comparable<pair>{
        char x;
        int y;
        pair(char x,int y){
            this.x=x;
            this.y=y;
        }
        public int compareTo(pair p){
            return x-p.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PrintWriter out = new PrintWriter(System.out);
        st=new StringTokenizer(br.readLine());

            int n=Integer.parseInt(st.nextToken());
            int k=Integer.parseInt(st.nextToken());
            String alph="abcdefghijklmnopqrstuvwxyz";
            PriorityQueue<pair>pq=new PriorityQueue<>();
        pair[]p=new pair[alph.length()];
            HashMap<Character,Integer>hm=new HashMap<>();
        for (int i = 0; i <alph.length() ; i++) {
            hm.put(alph.charAt(i),i);
            p[i]=new pair(alph.charAt(i),0);
        }
        String s=br.readLine();


        for (int i = 0; i <s.length() ; i++) {
            p[hm.get(s.charAt(i))].y++;
        }
        for (int i = 0; i <p.length ; i++) {
            pq.add(p[i]);
        }
        for (int i = 0; i <k ; i++) {
            while(!pq.isEmpty()&&pq.peek().y==0)
                pq.poll();
            if(!pq.isEmpty()){
                pq.peek().y--;
            }
        }
       hm=new HashMap<>();
        while (!pq.isEmpty()) {
            pair pa=pq.poll();
            hm.put(pa.x,pa.y);
        }

        StringBuilder x = new StringBuilder();
        for (int i =s.length()-1; i >=0; i--) {
            if(hm.containsKey(s.charAt(i))){

                if(hm.get(s.charAt(i))>0) {
                    x.append(s.charAt(i));
                    hm.replace(s.charAt(i), hm.get(s.charAt(i)) - 1);
                }

            }
        }
        out.println(x.reverse().toString());

        out.flush();
    }
}