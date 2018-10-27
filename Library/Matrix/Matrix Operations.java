
public class Matrix {
    /* Matrices Basic Operations */
    static int[][] zero(int n, int m) {
        return new int[n][m];
    }
    static int[][] identity(int n) { // Always square matrix
        int[][] x = zero(n, n);
        for (int i = 0; i < x.length; i++)
            x[i][i]++;
        return x;
    }
    static int matrixTrace(int[][] a) { // sumOfDigonal Values
        int ret = 0;
        for (int i = 0; i < a.length; i++)
            ret += a[i][i];
        return ret;
    }
    static int[][] rotate(int[][] v) { // rotate clockwise
        int[][] r = zero(v[0].length, v.length);
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r.length; j++) {
                r[j][v.length - 1 - i] = v[i][j];
            }
        }
        return r;
    }
    static int[][] transpose(int[][] v) { // rotate clockwise
        int[][] r = zero(v[0].length, v.length);
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r.length; j++) {
                r[j][i] = v[i][j];
            }
        }
        return r;
    }
    static int[][] reflect(int[][] v) { // Reflect Horizontally
        int[][] r = zero(v.length, v[0].length);
        for (int i = 0; i < r.length; i++) {
            for (int k = 0; k < r[i].length; k++) {
                r[i][v[0].length - 1 - k] = v[i][k];
            }
        }
        return r;
    }
    static int[][] add(int[][] a, int[][] b) {
        int[][] ret = zero(a.length, a[0].length);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                ret[i][j] = a[i][j] + b[i][j];
            }
        }
        return ret;
    }
    static int[][] addIdentity(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            a[i][i]++;
        }
        return a;
    }

    static int [][] multiply(int[][] a, int[][] b){
    	  int[][] ret = zero(a.length, b[0].length);
    	  // Optimizations here not to enter the last loop of a[i][k] = 0
    	  // Also we can swtich last two loops if b[k][j] has more zeros

    	  //for MOD optimization we use the following
    	  for (int i = 0 ; i < a.length ; ++i) {
    	    for(int k = 0 ; k < a[0].length ; ++ k){

    	      for (int j = 0 ; j < b[0].length ; ++ j) {
    	        ret[i][j] += a[i][k] * b[k][j];
    	      }
    	    }

    	  }
    	  return ret;
    	}

    static 	int [][] matPow(int [][] a, long k){ //N^3 LOG
    	  if(k == 0) return identity(a.length);
    	  if (k % 2 == 1) {
    	    return multiply(a , matPow(a , k-1));
    	  }
    	  return matPow(multiply(a , a), k >> 1);
    	}

    static 	int [][] sumPows(int [][] a, long k){ //Log
    	  if (k==0) {
    	    return zero(a.length , a.length);
    	  }
    	  if (k%2 == 1) {
    	    return multiply(a , addIdentity(sumPows(a , k - 1)));
    	  }
    	  return multiply(sumPows(a , k >> 1), addIdentity(matPow(a , k >> 1)));
    	}


    	// XY-plane operation using Matices

    	/*
    	Rotation clockwise for point (x,y)

    	[x_,y_] = [cos(theta) sin(theta)]  * [x,y]
    	          [-sin(theta) cos(theta)]

    	Rotation counterclockwise

    	[x_,y_] = [cos(theta) -sin(theta)]  * [x,y]
    	          [sin(theta) cos(theta)]

    	*/
}
