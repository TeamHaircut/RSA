package users;

import java.math.BigInteger;

import docs.Email;
import rsa.KeyGenerator;
import rsa.PrivateKey;
import rsa.PublicKey;
import rsa.Message;

public class User {
	
	private String name;
	private PublicKey PA;
	private PrivateKey SA;
	
	public User(String name) {
		this.name = name;
		KeyGenerator kg = new KeyGenerator();
		this.PA = kg.getPub();
		this.SA = kg.getPri();
	}
	
	public static Message sendMessage(Email mail) {
	    BigInteger msg = new BigInteger(mail.getText().getBytes());
	    BigInteger cipherText = msg.modPow(mail.getReceiver().getPA().getE(), mail.getReceiver().getPA().getN());
	    return new Message(cipherText);
	}
	
	public void readMessage(Message m) {
		BigInteger decipherText = m.getM().modPow(this.SA.getD(), this.SA.getN());
	    String clearText = new String(decipherText.toByteArray());
	    System.out.println("How "+this.name  +" sees the message: \n\t"+clearText+"\n");
	}

	public PublicKey getPA() {
		return PA;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
