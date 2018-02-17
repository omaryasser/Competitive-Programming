static void mergeSort (int arr [] , int start , int end){

        if (start < end) {
            int mid = (start + end) >> 1;
            mergeSort(arr , start , mid);
            mergeSort(arr , mid + 1 , end);

            int size1 = mid - start + 1 ;
            int size2 = end - mid;

            int L [] = new int[size1 + 1];
            int R [] = new int[size2 + 1];

            for (int i = 0 ; i < size1 ; ++i) L[i] = arr[start + i]; L[size1] = (int)1e9;
            for (int i = 0 ; i < size2 ; ++i) R[i] = arr[mid + 1 + i]; R[size2] = (int)1e9;

            for (int i = 0 , j = 0 , idx = start ; idx <= end ; ++ idx)
                    if (L[i] < R[j]) arr[idx] = L[i ++];
                    else             {
                      arr[idx] = R[j ++];
                      // if (j - 1 != size2) res += (size1 - i); if u want to find # of inversions.
                    }

        }
    }
