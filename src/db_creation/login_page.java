package db_creation;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.Scanner;

	public class login_page {
		private static final String Driver = "com.mysql.cj.jdbc.Driver";
		private static final String Username = "root";
		private static final String Password = "RUshi@270604";
		private static Connection conn;
		private static PreparedStatement pmst;
	public static void main(String[] args) {
		int choice;
			do {
				Scanner src = new Scanner(System.in);
				
				DisplayMenu();
				choice = Integer.parseInt(src.next());
				switch (choice) {
				case 1:
					sign_up();
					break;
				case 2:
					sign_in();
					break;
				case 3:
					System.out.println("thankyou for using our page");
					System.exit(0);
					break;
				}
			}while(choice>0);

	}
	private static void sign_in() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(Driver);
			String url = "jdbc:mysql://localhost:3306/loginpage";
			conn = DriverManager.getConnection(url, Username, Password);
			String sql = "select * from details where username = ? and password = ?";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter username: ");
			pmst.setString(1, src.next());
			System.out.println("enter password: ");
			pmst.setString(2, src.next());
			ResultSet rs = pmst.executeQuery();
			if(rs.next()) {
				
				System.out.println("welcome " + rs.getString("Username"));
				
				
			}
			else {
				System.out.println("Username not found");
			}
			pmst.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	private static void sign_up() {
		try {
			Scanner src = new Scanner(System.in);
			Class.forName(Driver);
			String url = "jdbc:mysql://localhost:3306/loginpage";
			conn = DriverManager.getConnection(url, Username, Password);
			String sql = "insert into details (Username,Password) values(?,?)";
			pmst = conn.prepareStatement(sql);
			System.out.println("enter Username: ");
			pmst.setString(1, src.next());
			System.out.println("enter Password");
			pmst.setString(2, src.next());
			int i = pmst.executeUpdate();
			if(i>0) {
				System.out.println("user registered successfully");
			}else {
				System.out.println("oops there is an error in registeration!!!");
			}
			conn.close();
			pmst.close();

		} catch (Exception e) {
			e.printStackTrace();
	}
		// TODO Auto-generated method stub
		
	}
	private static void DisplayMenu() {
		System.out.println("choose your choice");
		System.out.println("\t1.sign up");
		System.out.println("\t2.sign in");
	}
		// TODO Auto-generated method stub
		
	}

