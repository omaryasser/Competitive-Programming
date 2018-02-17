static int MAX=(int)1e6+3;
    static class AhoCorasickAho{
        int nxtId=1;
        HashMap<Character,Integer>[]mp=new HashMap[MAX];
        HashSet<Integer>[] ends=new HashSet[MAX];
        char[]fromP=new char[MAX];
        {for(int i=0;i<MAX;i++){
            mp[i]=new HashMap<>();
            ends[i]=new HashSet<>();
        }}
        int []fail=new int[MAX],parent=new int[MAX];

        AhoCorasickAho(char[][]a){
            for(int i=0;i<a.length;i++)insert(a[i],i);
            Queue<Integer>q=new LinkedList<>();
            for(int i=0;i<128;i++) {
                if (!mp[0].containsKey((char) i)) {
                    mp[0].put((char) i, 0);
                }
                else q.add(mp[0].get((char)i));
            }
            while (!q.isEmpty()){
                int cur=q.poll();
                int ancestor=parent[cur];
                while (!mp[fail[ancestor]].containsKey(fromP[cur]))
                    ancestor=fail[ancestor];
                fail[cur]=ancestor;
                ends[cur].addAll(ends[ancestor]);
                for(int nxt:mp[cur].values())q.add(nxt);
            }
        }
        void insert(char[]a,int i){
            int cur=0;
            for(char c:a){
                if(!mp[cur].containsKey(c)){
                    mp[cur].put(c,nxtId);
                    fromP[nxtId]=c;
                    parent[nxtId++]=cur;
                }
                cur=mp[cur].get(c);
            }
            ends[cur].add(i);
        }
        HashSet<Integer> match(char[]a){
            HashSet<Integer> found=new HashSet<>();
            int cur=0;
            for(char c:a){
                while (!mp[cur].containsKey(c))cur=fail[cur];
                cur=mp[cur].get(c);
                found.addAll(ends[cur]);
            }
            return found;
        }
    }
