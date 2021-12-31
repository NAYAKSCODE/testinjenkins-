package com.rmgyantra.testScript;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class AddProjectToDataBase {

	public static void main(String[] args) throws Throwable {
		Connection connection = null;
		try {
			Driver driver= new Driver();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into project(project_id,created_by,created_on,project_name,status,team_size)values('TY_PROJ_375','suhas','16/12/2021','silvermine','Completed',0)");
			String expectedID="TY_PROJ_375";
			ResultSet resul = statement.executeQuery("select * from project");
			while(resul.next())
			{
				if(resul.getString(1).equals(expectedID))
				{
					System.out.println("pass--data present");
				}
			}
		}
		finally {
			connection.close();

		}

	}
}
