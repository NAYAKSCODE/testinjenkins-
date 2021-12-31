package Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class InsertTheDataIntoDataBase {
	public static void main(String[] args) throws Throwable
	{
		//resister the database
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		//established the connection with database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
	
		//issue the statement
		Statement statement = connection.createStatement();
		
		//execute queries
		  int result = statement.executeUpdate("insert into studentinfo(fname,lname,age)value('ram','sahu',39)");
		  //verificatio
		
		if(result==1) {
			System.out.println("one set of data is added to database");
		}else {
			System.out.println("data is not added not database");
		}
		//close the database connection
		connection.close();
	}

}
