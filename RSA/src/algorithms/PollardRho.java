package algorithms;

/******************************************************************************
 *   Usage:
 *   		javac PollardRho.java
 *   		java PollardRho
 *   
 *****************************************************************************/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
    
class PollardRho {
    private final static BigInteger TWO  = new BigInteger("2");

    public static void main(String[] args) {
		Thread thread80 = new Thread(() -> {
			String name = "Thread80";
			BigInteger n = new BigInteger("112582235914085774126011");
			PollardRho.factor(name,n);});
		thread80.start();
		//add additional test threads here . . . 
	}
    
    public static void factor(String name, BigInteger n) {
    	try(
    			//IO
    			FileWriter fw = new FileWriter("factorsfile", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
    			//print n if it is a probable prime
	            if (n.isProbablePrime(40)) 
	            {
	            	out.println(name +": "+n); 
	            	return; 
	            }
	            //find a divisor using pollardRho
	            BigInteger d = pollardRho(n);
	            //try to find factors using divisor, d
	            factor(name,d);
	            factor(name,n.divide(d));
			} catch (Exception e) {e.printStackTrace();
			}   
    }
    
    public static BigInteger pollardRho(BigInteger n) {
    	//use polynomial g(x) = (x^2 + 1)mod n
    	
    	//assign 2 to x
        BigInteger x = TWO;
        //assign x to y
        BigInteger y = x;
        //assign 1 to divisor d
        BigInteger d = BigInteger.ONE;
        
        //while divisor is equal to 1
        while((d.compareTo(BigInteger.ONE)) == 0) {
        	//assign g(x) to x
        	x = x.multiply(x).mod(n).add(BigInteger.ONE).mod(n);
        	//assign g(g(y)) to y
            y = y.multiply(y).mod(n).add(BigInteger.ONE).mod(n);
            y = y.multiply(y).mod(n).add(BigInteger.ONE).mod(n);
            //assign gcd(|y - x|, n) to divisor
            d = y.subtract(x).abs().gcd(n);
        }
        
        //return divisor
        return d;
    }

}