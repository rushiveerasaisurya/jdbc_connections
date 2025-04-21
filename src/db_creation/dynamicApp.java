package db_creation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class dynamicApp {
	private static final String Driver="com.mysql.cj.jdbc.Driver";
	private static final String userName="root";
	private static final String password="RUshi@270604";
	private static Connection con;
	private static PreparedStatement pmst;
public static void main(String[] args) {
	int choice;
	do {
		Scanner src=new Scanner(System.in);
		System.out.println("choose your choice: ");
		displayMenu();
		choice=Integer.parseInt(src.next());
		switch (choice) {
		case 1:
			createdb();
			break;
		
		case 2:
			dropdb();
			break;
	
		case 3:
			insertData();
			break;
		case 4:
			deleteByEmail();
			break;
		case 5:
			updateData();
			break;
		case 6:
			getByEmail();
			break;
		case 7:
			getAll();
			break;
		case 8:
			System.out.println("thankyou for using db!!");
			System.exit(0);
			break;
		default:
			System.out.println("invalid option!!!");
			
}
		
	} while (choice>0);
}

private static void getAll() {
	// TODO Auto-generated method stub
	try {
		Scanner sc = new Scanner(System.in);
		Class.forName(Driver);
		System.out.println("Enter database name:");
		String url ="jdbc:mysql://localhost:3306/"+sc.next();
		con=DriverManager.getConnection(url, userName, password);
		System.out.println("enter database table name: ");
		String sql="select * from "+sc.next();
		pmst=con.prepareStatement(sql);
		ResultSet rs=pmst.executeQuery();
		while(rs.next()) {
			System.out.println("order id: "+rs.getLong("order_id"));
			System.out.println("order name: "+rs.getString("order_name"));
			System.out.println("order pincode: "+rs.getInt("order_pincode"));
			System.out.println("order address: "+rs.getString("order_address"));
		}
		
		con.close();
		pmst.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
}

private static void getByEmail() {
	try {
		Scanner sc=new Scanner(System.in);
		Class.forName(Driver);
		System.out.println("enter database name:");
		String url="jdbc:mysql://localhost:3306/"+sc.next();
		con=DriverManager.getConnection(url,userName,password);
		System.out.println("enter table name:");
		String sql="select * from "+sc.next()+" where order_id=?";
		pmst=con.prepareStatement(sql);
		System.out.println("enter order id");
		pmst.setLong(1, sc.nextLong());
		ResultSet rs=pmst.executeQuery();
		while(rs.next()) {
			System.out.println("order id: "+rs.getLong("order_id"));
			System.out.println("order name: "+rs.getString("order_name"));
			System.out.println("order pincode: "+rs.getInt("order_pincode"));
			System.out.println("order address: "+rs.getString("order_address"));
		}
		
		con.close();
		pmst.close();
	} catch (Exception e) {
		e.printStackTrace();
	}	
}

private static void updateData() {
	try {
		Scanner sc = new Scanner(System.in);
		Class.forName(Driver);
		System.out.println("Enter database name to update data");
		String url ="jdbc:mysql://localhost:3306/"+sc.next();
		con=DriverManager.getConnection(url, userName, password);
		System.out.println("enter database table name: ");
		String sql="update "+sc.next()+" set order_name = ?,order_pincode = ?,order_address = ? where order_id = ?";
		pmst=con.prepareStatement(sql);
		System.out.println("enter order name:");
		pmst.setString(1, sc.next());
		System.out.println("enter order pincode:");
		pmst.setInt(2, sc.nextInt());
		System.out.println("enter order address:");
		pmst.setString(3, sc.next());
		System.out.println("enter order id:");
		pmst.setLong(4, sc.nextLong());
		int i=pmst.executeUpdate();
		if(i>0) {
			System.out.println("database updated successfully!!");
		}
		else {
			System.out.println("database not updated");
		}
		con.close();
		pmst.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

private static void deleteByEmail() {
	try {
		Scanner sc = new Scanner(System.in);
		Class.forName(Driver);
		System.out.println("Enter database name:");
		String url ="jdbc:mysql://localhost:3306/"+sc.next();
		con=DriverManager.getConnection(url, userName, password);
		System.out.println("enter database table name: ");
		String sql="delete from "+sc.next()+" where order_id=?";
		pmst=con.prepareStatement(sql);
		System.out.println("enter order id:");
		pmst.setLong(1, sc.nextLong());
		int i=pmst.executeUpdate();
		if(i>0) {
			System.out.println("data deleted successfully!!");
		}
		else {
			System.out.println("data not deleted");
		}
		con.close();
		pmst.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
}

private static void insertData() {
	try {
		Scanner sc = new Scanner(System.in);
		Class.forName(Driver);
		System.out.println("Enter database name to insert data");
		String url ="jdbc:mysql://localhost:3306/"+sc.next();
		con=DriverManager.getConnection(url, userName, password);
		System.out.println("enter database table name: ");
		String sql="insert into "+sc.next()+"(order_id,order_name,order_pincode,order_address) values(?,?,?,?)";
		pmst=con.prepareStatement(sql);
		System.out.println("enter order id:");
		pmst.setLong(1, sc.nextLong());
		System.out.println("enter order name:");
		pmst.setString(2, sc.next());
		System.out.println("enter order pincode:");
		pmst.setInt(3, sc.nextInt());
		System.out.println("enter order address:");
		pmst.setString(4, sc.next());
		int i=pmst.executeUpdate();
		if(i>0) {
			System.out.println("data inserted successfully!!");
		}
		else {
			System.out.println("data not inserted");
		}
		con.close();
		pmst.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

private static void dropdb() {
	try {
		Class.forName(Driver);
		String url ="jdbc:mysql://localhost:3306/";
		con=DriverManager.getConnection(url, userName, password);
		System.out.println("enter database name: ");
		Scanner sc = new Scanner(System.in);
		String sql="drop database "+sc.next();
		pmst=con.prepareStatement(sql);
		int i=pmst.executeUpdate();
		if(i>0) {
			System.out.println("database dropped successfully!!");
		}
		else {
			System.out.println("database not dropped");
		}
		con.close();
		pmst.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

private static void createdb() {
	try {
		Class.forName(Driver);
		String url ="jdbc:mysql://localhost:3306/";
		con=DriverManager.getConnection(url, userName, password);
		System.out.println("enter database name: ");
		Scanner sc = new Scanner(System.in);
		String sql="create database "+sc.next();
		pmst=con.prepareStatement(sql);
		int i=pmst.executeUpdate();
		if(i>0) {
			System.out.println("database created successfully!!");
		}
		else {
			System.out.println("database not created");
		}
		con.close();
		pmst.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}

private static void displayMenu() {
	System.out.println("\t1.create database");
	System.out.println("\t2.drop database");
	System.out.println("\t3.data insertion");
	System.out.println("\t4.delete by email");
	System.out.println("\t5.update data");
	System.out.println("\t6.get by email");
	System.out.println("\t7.get all");
	System.out.println("\t8.exit");	
}
}
