if (grid[row][col] == Pawn){
            if (valid(row + 1 , col + 1)) checked[row + 1][col + 1] = true;
            if (valid(row + 1 , col - 1)) checked[row + 1][col - 1] = true;
        }
        if (grid[row][col] == 'P'){
            if (valid(row - 1 , col + 1)) checked[row - 1][col + 1] = true;
            if (valid(row - 1 , col - 1)) checked[row - 1][col - 1] = true;
        }
        char c = Character.toLowerCase(grid[row][col]);

        if (c == 'King'){
            int dx [] = {-1,-1,0,1,1,1,0,-1};
            int dy [] = {0,1,1,1,0,-1,-1,-1};
            for (int i = 0 ; i < 8 ; ++i){
                if (valid(row + dx[i] , col + dy[i])) checked[row + dx[i]][col + dy[i]] = true;
            }
        }

        if (c == 'Rook' || c == 'Queen'){
            int dx [] = {1,-1,0,0};
            int dy [] = {0,0,1,-1};
            for (int k = 0 ; k < 4 ; ++k){
                int x = dx[k];
                int y = dy[k];
                for (int i = row + x , j = col + y ; valid(i , j) ; x += dx[k] , y += dy[k] , i = row + x , j = col + y){
                    checked[i][j] = true;
                }
            }
        }

        if (c == 'Bishop' || c == 'Queen'){
            int dx [] = {-1,1,1,-1};
            int dy [] = {1,1,-1,-1};
            for (int k = 0 ; k < 4 ; ++k){
                int x = dx[k];
                int y = dy[k];
                for (int i = row + x , j = col + y ; valid(i , j) ; x += dx[k] , y += dy[k] , i = row + x , j = col + y){
                    checked[i][j] = true;
                }
            }
        }

        if (c == 'Knight'){
            int dx [] = {-2,-2,-1,1,2,2,1,-1};
            int dy [] = {-1,1,2,2,1,-1,-2,-2};
            for (int i = 0 ; i < 8 ; ++ i){
                if (valid(row + dx[i] , col + dy[i])) {
                    checked[row + dx[i]][col + dy[i]] = true;
                }
            }
        }
