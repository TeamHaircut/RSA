package rsa;

import java.math.BigInteger;

public class PublicKey {
	
	private BigInteger[] publickey = new BigInteger[2];
	
	public PublicKey(BigInteger e, BigInteger n) {
		this.publickey[0]=e;
		this.publickey[1]=n;
	}
	
	public BigInteger[] getPublickey() {
		return publickey;
	}

	public void setPublickey(BigInteger[] publickey) {
		this.publickey = publickey;
	}
	
	public BigInteger getE() {
		return this.publickey[0];
	}
	
	public BigInteger getN() {
		return this.publickey[1];
	}
	
}
