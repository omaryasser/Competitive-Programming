/* Nested Ranges Problem */
// **ExPlOsIvEs
int NesR(int i,int j){ //Always O(n^3)
  if (i == j || j-i == 1) {
    return 0;
  }
  if (j-i == 2) {
    return arr[i]*arr[j];
  }
  int maxi = 0;
  for (int k = i+1; k < j; k++) {
    maxi = max(maxi,NesR(i,k)+NesR(k,j)+arr[i]*arr[j]);
  }
  return maxi;
}
