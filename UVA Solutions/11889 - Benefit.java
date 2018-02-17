import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static ArrayList<Integer> primes;
    static int MAX = ((int)1e7) + 2;
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
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        seive();
        int T = sc.nextInt();
        while (T -- > 0){
            int A = sc.nextInt();
            int C = sc.nextInt();
            int B = 1;
            boolean good = true;
            for (int i = 0 , j = primes.get(i) ; j * j <= C ; j = primes.get(++ i)){

                int cntC = 0 ;
                int cntA = 0 ;

                while (C % j == 0){cntC ++ ; C /= j;}
                while (A % j == 0){cntA ++ ; A /= j;}
                if (cntA > cntC) good = false;
                if (cntA < cntC) B *= Math.pow(j , cntC);

                if (i == primes.size() - 1) break;
           }

            if (A != 1 && A != C) good = false;
            if (A != C) B *= C;
            if (good) System.out.println(B);
            else System.out.print("NO SOLUTION\n");
        }
    }


}
