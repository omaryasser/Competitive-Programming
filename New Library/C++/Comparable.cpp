sort(queries, queries+q, [](query &f, query &s){
       if(f.l / SQ != s.l / SQ) return f.l < s.l;
       if((f.l / SQ) & 1) return f.r < s.r;
       return f.r > s.r;
    });
