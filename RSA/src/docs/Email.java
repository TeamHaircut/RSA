package docs;

import users.User;

public class Email {
	
	private String text;
	private User sender;
	private User receiver;
	
	public Email(User sender, User receiver) {
		this.setSender(sender);
		this.setReceiver(receiver);
		this.setText("Hi " + receiver +", it's " + sender+"\n\tThis is an important secret message");
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
	
	
	
}
