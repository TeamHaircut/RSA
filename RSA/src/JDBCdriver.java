import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

//inserts encrypted data into db, select decrypted data from db.
public class JDBCdriver {

    private static String dbURL = "jdbc:derby:cryptTest;password=xxxxxxxxxxxxxxxx";
    private static String tableName = "test";
    private static Connection conn = null;
    private static Statement stmt = null;
    
	private static BigInteger n;
    private static BigInteger d;
	
	public static void main(String[] args) {
		
		BigInteger p;
		do {
			Random rand = new Random();
			p = new BigInteger(1024, rand);
		} while (!p.isProbablePrime(40));
		
		BigInteger q;
		do {
			Random rand = new Random();
			q = new BigInteger(1024, rand);
		} while (!q.isProbablePrime(40));
		
		n = p.multiply(q);
		
		BigInteger e = new BigInteger("65537");
		BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
		d = e.modInverse(phi);
		
	    String str = "Data you want to Encrypt";
	    BigInteger msg = new BigInteger(str.getBytes());
	    BigInteger cipherText = msg.modPow(e, n);
	
		createConnection();
		insertItem(cipherText.toString());
		selectSQL();
	}
	
    private static void selectSQL()
    {
        try
        {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i=1; i<=numberCols; i++)
            {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i)+"\t\t");  
            }

            System.out.println("\n-------------------------------------------------");

            while(results.next())
            {
                String msg = results.getString(1);
                BigInteger mine = new BigInteger(msg);
                BigInteger decipherText = mine.modPow(d, n);
			    String clearText = new String(decipherText.toByteArray());
                System.out.println(clearText);
                
            }
            results.close();
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
	
    private static void insertItem(String value)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values ('" + value +"')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }

    private static void createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
    }
}
