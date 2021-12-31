package com.rmgyantra.testScript;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

public class AddProjectToRmgyantra {
	public static void main(String[]args) throws Throwable
	{
	//lunch the Chrome browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		//lunch the application
		driver.get("http://localhost:8084/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		//provide user credential
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
		driver.findElement(By.name("projectName")).sendKeys("gama");
		driver.findElement(By.name("createdBy")).sendKeys("bharat");

		Select s= new Select( driver.findElement(By.xpath("(//select[@name='status'])[2]")));
		s.selectByIndex(1);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();	

		//verifying the database

		String expectedName="bharat";
		//register with database
		Driver driver1 = new Driver();
		DriverManager.registerDriver(driver1);

		//established connection with database
		Connection connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		//issue statement
		Statement state = connec.createStatement();

		//ececute query
		ResultSet result = state.executeQuery("select * from project");

		while(result.next())
		{
			if(result.getString(2).equals(expectedName))
			{
				System.out.println("pass--data is present");
			}

		}
		driver.close();
	}

	}


