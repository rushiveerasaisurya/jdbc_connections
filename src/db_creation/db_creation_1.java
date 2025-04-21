package db_creation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class db_creation_1 {
	private static final String driver="com.mysql.cj.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/";
	private static final String userName="root";
	private static final String password="RUshi@270604";
	private static PreparedStatement pmst;
	private static Connection con;
	
public static void main(String[] args) {
	try {
		Scanner src=new Scanner	(System.in);
		Class.forName(driver);
		con=DriverManager.getConnection(url,userName,password);
		System.out.println("Enter database name: ");
		String sql="create database "+ src.nextLine();
		pmst=con.prepareStatement(sql);
		int i = pmst.executeUpdate();
		if(i>0) {
			System.out.println("db is created");
			
		}
		else {
			System.out.println("db is not created");
		}
		pmst.close();
		con.close();
		src.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
