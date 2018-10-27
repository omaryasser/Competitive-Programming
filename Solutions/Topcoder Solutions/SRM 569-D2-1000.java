import java.util.*;

/**
 * Created by omar on 12/3/16.
 */
public class MegaFactorialDiv2 {

    static int mod = 1000000009;
    static int MAX_N = 1000 , MAX_K = 100 , MAX_P = 168;
    static ArrayList<Integer> primes;
    static void seive (){
        primes = new ArrayList<>(168);
        boolean isPrime[] = new boolean[MAX_N + 1];
        Arrays.fill(isPrime , true);
        for (int i = 2 ; i <= MAX_N ; ++ i) if (isPrime[i]) {
            for (int j = i * i ; j <= MAX_N ; j += i) {
                isPrime[j] = false;
            }
        }
        for (int i = 2 ; i <= MAX_N ; ++ i){
            if (isPrime[i]) primes.add(i);
        }
    }
    static int numOcc (int num , int f) {
        int p = primes.get(f);
        int cnt = 0 ;
        while (num % p == 0) {
            num /= p;
            cnt ++ ;
        }
        return cnt;
    }
    static int compute (int N , int K , int f){
        int com [][] = new int[MAX_N + 1][MAX_K + 1];
        for (int i = 1 ; i <= MAX_N ; ++ i) {
            for (int j = f ; j <= f ; ++ j) {
                com[i][0] = (com[i - 1][0] + numOcc(i , f)) % mod;
            }
        }
        for (int k = 1 ; k <= MAX_K ; ++ k){
            for (int n = 1 ; n <= MAX_N ; ++ n) {
                for (int p = f ; p <= f ; ++ p) {
                    com[n][k] = (com[n - 1][k] + com[n][k - 1]) % mod;
                }
            }
        }
        return (((com[N][K] - com[N - 1][K]) % mod) + mod) % mod;
    }

    public static int countDivisors(int N, int K){
        seive();
        long res = 1;
        for (int i = 0 ; i < MAX_P ; ++ i)
            res = (res *  ((long)compute(N , K , i) + 1L)) % mod;
        return (int)res;
    }

    public static void main (String [] args) {
        System.out.println(MegaFactorialDiv2.countDivisors(1000 , 99));
    }
}
