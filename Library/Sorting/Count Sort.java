static void countSort (int arr []){
        int n = arr.length;
        int max = - (int)1e9;
        int min =   (int)1e9;
        for (int i = 0 ; i < n ; ++i){
            max = Math.max(max , arr[i]);
            min = Math.min(min , arr[i]);
        }

        int cnt [] = new int[max - min + 2];
        for (int i = 0 ; i < n ; ++i) ++ cnt[arr[i] - min];
        for (int i = 0 , j = 0 ; i < max - min + 2 ; ++i){
            while (cnt[i] -- > 0) arr[j ++] = i + min;
        }
    }
