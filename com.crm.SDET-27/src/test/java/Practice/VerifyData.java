package Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class VerifyData {
	public static void main(String[] args) throws Throwable
	{
		String name="ashutosh";
		//resister the database
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		//established the connection with database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
	
		//issue the statement
		Statement statement = connection.createStatement();
		
		//execute queries
		ResultSet result = statement.executeQuery("select*from studentinfo");
		//verification
		while(result.next()) {
			if(result.getString(1).equals(name)) {
				System.out.println("pased");
			}
		}
		//close the database connection
		connection.close();
		}

}
