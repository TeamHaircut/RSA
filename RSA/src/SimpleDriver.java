import java.math.BigInteger;
import java.util.Random;

//usage: 
//javac Driver.java
//java Driver bitLength || java Driver
//default bitLength: 1024 bits
public class Driver {

	public static void main(String[] args) {
		int defaultLength = 1024;
		int bitLength = 0;
		if(args.length == 1) {
			try{
				bitLength = Integer.parseInt(args[0]);
			} catch (Exception e) {
				bitLength = defaultLength;
			}
		}
		else {
				bitLength = defaultLength;
		}
				BigInteger p = BigInteger.probablePrime(bitLength, new Random());
				BigInteger q = BigInteger.probablePrime(bitLength, new Random());
			
				BigInteger n = p.multiply(q);
				BigInteger e = new BigInteger("65537");
				BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
				BigInteger d = e.modInverse(phi);
				
				String str = "A secret message";
			    BigInteger msg = new BigInteger(str.getBytes());
			    BigInteger cipherText = msg.modPow(e, n);
			    BigInteger decipherText = cipherText.modPow(d, n);
			    String clearText = new String(decipherText.toByteArray());
			    
			    System.out.println("Public Key: P(e,n)");
			    System.out.println("e: "+e);
				System.out.println("n: "+n);
				System.out.println("\nPrivate Key: P(d,n)");
				System.out.println("d: "+d);
				System.out.println("n: "+n);	
				
			    System.out.println("\nEncoded Message = " + cipherText);
			    System.out.println("Decoded Message = " + decipherText);
			    System.out.println("Clear Text = " + clearText);
	}	
}