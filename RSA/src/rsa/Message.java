package rsa;

import java.math.BigInteger;

public class Message {
	
	private BigInteger msg;
	
	public Message(BigInteger msg) {
		this.msg = msg;
	}
	
	public BigInteger getM() {
		return this.msg;
	}

}
