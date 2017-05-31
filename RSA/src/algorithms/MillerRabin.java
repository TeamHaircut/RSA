package algorithms;

import java.math.BigInteger;
import java.util.Random;

//BigInteger implementation of Miller-Rabin test for primality
public class MillerRabin {

	private final static BigInteger TWO = new BigInteger("2");

	public static BigInteger probablePrime(int bitLength, int s) {
		Random rand = new Random();
		BigInteger b;
		do {
			b = new BigInteger(bitLength, rand);
		} while (!isProbablePrime(b, s));
		return b;
	}

	public static boolean witness(BigInteger a, BigInteger n) {
		//let t and u be such that t >= 1, u is odd, and n - 1 = (2^t)u
		int t = 1;
		BigInteger u;
		do {
			u = n.subtract(BigInteger.ONE).divide(TWO.pow(t));
			t++;
		} while (u.mod(TWO).compareTo(BigInteger.ZERO) == 0);
		
		//modular-exponentiation
		BigInteger x0 = a.modPow(u, n);
		
		for (int i = 1; i <= t; i++) {
			BigInteger x0sqr = x0.multiply(x0);
			BigInteger xi = x0sqr.mod(n);
			if (xi.compareTo(BigInteger.ONE)==0 && x0.compareTo(BigInteger.ONE) != 0 && x0.compareTo(n.subtract(BigInteger.ONE)) != 0) { 
				return true;
			}
			x0 = xi;
		}
		if(x0.compareTo(BigInteger.ONE) != 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isProbablePrime(BigInteger n, int s) {
		//probable primes returns true
		for (int j = 1; j <= s; j++) {
			Random rand = new Random();
			BigInteger a;
			do {
				a = new BigInteger(n.bitLength(), rand);
			} while (a.compareTo(BigInteger.ONE) <= 0 || a.compareTo(n.subtract(BigInteger.ONE)) >= 0);

			if (witness(a, n)) 
				return false;
		}
		return true;
	}
	
	
}
