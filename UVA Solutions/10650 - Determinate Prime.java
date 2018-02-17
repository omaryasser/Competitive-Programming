import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<Integer> primes;
    static int MAX = 33300;
    static class Result implements  Comparable<Result>{
        int left ;
        int right ;
        ArrayList<Integer> determinates;

        Result (int l , int r , ArrayList<Integer> d){
            left = l;
            right = r;
            determinates = d;
        }

        @Override
        public int compareTo(Result result) {
            return left != result.left ? left - result.left : right - result.right;
        }
    }
    static void seive (){
        primes = new ArrayList<>();
        boolean isPrime [] = new boolean[MAX + 1];
        Arrays.fill(isPrime , true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 0 ; i <= MAX ; ++ i){
            if (isPrime[i]){
                primes.add(i);
                for (int j = i + i ; j <= MAX ; j += i)
                    isPrime[j] = false;
            }
        }
    }
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        seive();
        ArrayList<Result> results = new ArrayList<>();
        for (int i = 0 , j = primes.get(0) ; i < primes.size() - 2 ; j = primes.get(++ i)){
            int leftIdx = i;
            if (primes.get(leftIdx + 1) - primes.get(leftIdx) !=
                    primes.get(leftIdx + 2) - primes.get(leftIdx + 1)) continue;
            ArrayList<Integer> here = new ArrayList<>();
            here.add(primes.get(leftIdx));
            here.add(primes.get(leftIdx + 1));
            here.add(primes.get(leftIdx + 2));
            int cnt = 3;
            while (leftIdx + 2 < primes.size()){
                leftIdx ++ ;
                if (primes.get(leftIdx + 1) - primes.get(leftIdx) !=
                        primes.get(leftIdx + 2) - primes.get(leftIdx + 1)) break;
                here.add(primes.get(leftIdx + 2));
            }
            results.add(new Result(primes.get(i) , primes.get(leftIdx + 1) , here ));
            i = leftIdx;
        }
        Collections.sort(results);
        StringBuilder sb = new StringBuilder();
        while (true){
            int l = sc.nextInt();
            int r = sc.nextInt();
            if (l > r) {
                l ^= r;
                r ^= l;
                l ^= r;
            }
            if (l == 0 && r == 0) break;
            for (int i = 0 ; i < results.size() ; ++ i){
                if (results.get(i).left >= l && results.get(i).right <= r){
                    for (int j = 0 ; j < results.get(i).determinates.size() ; ++ j){
                        if (j == 0) sb.append(results.get(i).determinates.get(j));
                        else sb.append(" " + results.get(i).determinates.get(j));
                    }
                    sb.append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}
