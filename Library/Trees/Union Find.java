static class UnionFind {
private int [] p, rank, setSize;
private int numSets;
public UnionFind(int size)
{
        p = new int[size];
        rank = new int[size];
        setSize = new int[size];
        numSets = size;
        for(int i = 0; i < size; ++i) {
                p[i] = i;
                setSize[i] = 1;
        }
}

int findSet(int i ) {
        return (p[i] == i) ? i : (p[i] = findSet(p[i]));
}
boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
}
void unionSet(int i, int j)
{
        if(!isSameSet(i,j))
        {
                --numSets;
                int x = findSet(i), y = findSet(j);
                if(rank[x] > rank[y]) {p[y] = x; setSize[x] = setSize[x] + setSize[y]; }
                else
                {
                        p[x] = y;
                        setSize[y] = setSize[y] + setSize[x];
                        if(rank[x] == rank[y]) ++rank[y];
                }
        }
}

int numDisjointSets ()
{
        return numSets;
}

<<<<<<< HEAD
    void close ()
		{
			for (int i = 0; i < sz; i++)
				find(i);
		}

    int sizeOfSet(int i) {return setSize[findSet(i)];}
=======
void close ()
{
        for (int i = 0; i < p.length; i++)
                findSet(i);
}

int sizeOfSet(int i) {
        return setSize[findSet(i)];
}
>>>>>>> 56a558d15fded1110f61556e56a59791a3df1d80
}
