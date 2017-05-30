import docs.Email;
import users.User;
import rsa.Message;

public class Driver {
	
	public static void main(String[] args) {
		
		User Alice = new User("Alice");
		User Bob = new User("Bob");
		User Eve = new User("Eve");
		
		Message M = User.sendMessage(new Email(Alice,Bob));
		
		Eve.readMessage(M);
		Bob.readMessage(M);
		
	}
}
