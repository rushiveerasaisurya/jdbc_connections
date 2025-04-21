package db_creation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class insertion {
	private static final String driver="com.mysql.cj.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/student_details";
	private static final String userName="root";
	private static final String password="RUshi@270604";
	private static PreparedStatement pmst;
	private static Connection con;
public static void main(String[] args) {
	try {
		Scanner src=new Scanner	(System.in);;
		Class.forName(driver);
		con=DriverManager.getConnection(url, userName, password);
		String sql="insert into details(id,name,email) values(?,?,?)";
		pmst=con.prepareStatement(sql);
		System.out.println("enter id:");
		pmst.setInt(1, src.nextInt());
		System.out.println("enter name:");
		pmst.setString(2, src.next());
		System.out.println("enter email:");
		pmst.setString(3, src.next());
		int i=pmst.executeUpdate();
		if(i>0) {
			System.out.println("data inserted");
		}
		else {
			System.out.println("data isn't inserted");
		}
		pmst.close();
		con.close();
		src.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
