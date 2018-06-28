struct Pair{
    int first,second;
    Pair(int f,int s):first(f),second(s){}
    bool operator==(const Pair& other)const{
        return first==other.first;
    }
};

namespace std{
template <>
struct hash<Pair>{
    size_t operator()(const Pair& p)const{
        return hash<int>()(p.first);
    }
};
}
