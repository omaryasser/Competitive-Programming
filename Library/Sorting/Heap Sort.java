// use the array 1 based.
// Time Complexity O(n  log n).
static void heapSort (int arr []){ // Starting from index 1
        int n = arr.length;
        for (int i = n / 2 ; i >= 1 ; -- i)
            max_heapify (arr , i , n);
    }

    static void max_heapify (int arr [] , int node , int n){
        int largest = node;
        int left = node << 1;
        int right = node << 1 | 1;
        if (left < n){
            if (arr[left] > arr[largest]) largest = left;
            if (right < n && arr[right] > arr[largest])  largest = right;
            if (largest != node) {
                if (largest < n) max_heapify(arr , largest , n);
            }
        }
    }

    static void swap (int arr [] , int f , int s){
        arr[f] ^= arr[s];
        arr[s] ^= arr[f];
        arr[f] ^= arr[s];
    }
