package rsa;

import java.math.BigInteger;

public class PrivateKey {

private BigInteger[] privatekey = new BigInteger[2];
	
	public PrivateKey(BigInteger d, BigInteger n) {
		this.privatekey[0]=d;
		this.privatekey[1]=n;
	}
	
	public BigInteger[] getPrivatekey() {
		return privatekey;
	}

	public void setPrivatekey(BigInteger[] privatekey) {
		this.privatekey = privatekey;
	}
	
	public BigInteger getD() {
		return this.privatekey[0];
	}
	
	public BigInteger getN() {
		return this.privatekey[1];
	}
	
}
