int g (int n) {
	return n ^ (n >> 1);
}
int rev_g (int g) {
	int n = 0;
	for (; g; g>>=1)
 n ^= g;
	return n;
}

// n-bit gray code corresponds to a Hamiltonian cycle in the n-dimensional cube.
// Gray codes are used in the solution of the problem of towers of Hanoi.
