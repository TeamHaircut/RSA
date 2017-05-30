package rsa;

import java.math.BigInteger;
import java.util.Random;

public class KeyGenerator {
	
	private PublicKey pub;
	private PrivateKey pri;
	
	public KeyGenerator() {
		BigInteger p = new BigInteger(1024,40,new Random());
		BigInteger q = new BigInteger(1024,40, new Random());
		BigInteger n = p.multiply(q);
		BigInteger e = new BigInteger("65537");
		BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		BigInteger d = e.modInverse(phi);
		
		setPub(new PublicKey(e,n));
		setPri(new PrivateKey(d,n));
	}

	public PublicKey getPub() {
		return pub;
	}

	private void setPub(PublicKey pub) {
		this.pub = pub;
	}

	public PrivateKey getPri() {
		return pri;
	}

	private void setPri(PrivateKey pri) {
		this.pri = pri;
	}
	
}
