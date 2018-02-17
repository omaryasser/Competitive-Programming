static int len (int num){
        int cnt = 0;
        while (num > 0){
            ++cnt;
            num /= 10;
        }
        return cnt;
    }

    static int getDigitInIndex (int num , int idx){
        for (int i = 0 ;  ; ++i){
            if (i == idx) return num % 10;
            num /= 10;
        }
    }
    static void radixSort (int arr []){
        int maxLen = 0;
        int n = arr.length;
        for (int i = 0 ; i < n ; ++i) maxLen = Math.max(maxLen , len(arr[i]));

        ArrayList<Integer> tempArr [] = new ArrayList[10];
        for (int i = 0 ; i < 10 ; ++i) tempArr[i] = new ArrayList<>();

        for (int i = 0 ; i < maxLen ; ++i){
            for (int j = 0 ; j < n ; ++j){
                tempArr[getDigitInIndex (arr[j] , i)].add(arr[j]);
            }

            for (int idx = 0 , j = 0 ; j < 10 ; ++j){
                while (tempArr[j].size() > 0) arr[idx ++] = tempArr[j].remove(0);
            }
        }
    }
