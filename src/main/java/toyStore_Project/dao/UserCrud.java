package toyStore_Project.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import toyStore_Project.dto.Admin;
import toyStore_Project.dto.Toy;
import toyStore_Project.dto.User;

public class UserCrud {
	//Create Table
	public Connection createTable() throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		FileInputStream stream = new FileInputStream("DbConfig.properties");
		Properties p = new Properties();
		p.load(stream);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/toystore?createDatabaseIfNotExist=true",p);
		PreparedStatement ps = con.prepareStatement("create table if not exists User(userId int primary key AUTO_INCREMENT , userName varchar(45) , userEmail varchar(45) unique , userPhone bigint(10) ,address varchar(45) , wallet double  , password varchar(45) )");
		ps.execute();
	
		return con;
	}
	
	//Insert Data
	public int insertUserDetail(User user) throws ClassNotFoundException, SQLException, IOException {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("insert into User( userName ,userEmail , userPhone , address ,wallet , password) values(? , ?  , ?  , ? , ? ,?)");
		ps.setString(1, user.getUserName());
		ps.setString(2, user.getUserEmail());
		ps.setLong(3 , user.getUserPhone());
		ps.setString(4, user.getAddress());
		ps.setDouble(5, user.getWallet());
		ps.setString(6, user.getUserPassword());
		
		int result = ps.executeUpdate();
		return result;
	}

	//Fetch User Data
	public User fetctUserDetail(String email1) throws Exception {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("select * from user where userEmail = ?");
		ps.setString(1, email1);
		ResultSet res = ps.executeQuery();
		if(res.next()) {
			int userId = res.getInt("userId");
			String userName  = res.getString("userName");
			String userEmail = res.getString("userEmail");
			long userPhone = res.getLong("userPhone");
			String address = res.getString("address");
			int wallet  = res.getInt("wallet");
			String password = res.getString("password");
			
			User user = new User(userId, userName, userEmail, userPhone, address , wallet, password);
			return user;
		}else {
			return null;
		}
	}
	//Update User Detail
	public User updateUserDetail(User user, int choice, Object o) throws Exception {
		Connection con = createTable();
		PreparedStatement ps;
		// update name
		if(choice ==1) {
			String userName = (String) o;
			ps = con.prepareStatement("update User set userName=? where userEmail=?");
			ps.setString(1, userName);
			ps.setString(2, user.getUserEmail());
			ps.executeUpdate();
			return new User(user.getUserId(), user.getUserName(), user.getUserEmail(), user.getUserPhone(), user.getAddress(), user.getWallet() , user.getUserPassword());
		}
		//update email
		else if(choice ==2) {
			String userEmail = (String) o;
			ps = con.prepareStatement("update User set userEmail=? where userEmail=?");
			ps.setString(1, userEmail);
			ps.setString(2, user.getUserEmail());
			ps.executeUpdate();
			return new User(user.getUserId(), user.getUserName(), user.getUserEmail(), user.getUserPhone(), user.getAddress(), user.getWallet() , user.getUserPassword());
		}
		//update phone
		else if(choice==3) {
			long phone = (long) o;
			ps = con.prepareStatement("update User set userPhone=? where userEmail=?");
			ps.setLong(1 , phone);
			ps.setString(2, user.getUserEmail());
			ps.executeUpdate();
			return new User(user.getUserId(), user.getUserName(), user.getUserEmail(), user.getUserPhone(), user.getAddress(), user.getWallet() , user.getUserPassword());
		}
		//update address
		else if(choice ==4) {
			String address = (String) o;
			ps = con.prepareStatement("update User set address=? where userEmail=?");
			ps.setString(1, address);
			ps.setString(2, user.getUserEmail());
			ps.executeUpdate();
			return new User(user.getUserId(), user.getUserName(), user.getUserEmail(), user.getUserPhone(), user.getAddress(), user.getWallet() , user.getUserPassword());
		}
		//update wallet Value
		else if(choice==5) {
			double wallet = (double) o;
			ps = con.prepareStatement("update User set wallet=? where userEmail=?");
			ps.setDouble(1, wallet);
			ps.setString(2, user.getUserEmail());
			ps.executeUpdate();
			return new User(user.getUserId(), user.getUserName(), user.getUserEmail(), user.getUserPhone(), user.getAddress(), user.getWallet() , user.getUserPassword());
		}
		//update password
		else if(choice==6) {
			String password = (String) o;
			ps = con.prepareStatement("update User set password=? where userEmail=?");
			ps.setString(1, password);
			ps.setString(2, user.getUserEmail());
			ps.executeUpdate();
			return new User(user.getUserId(), user.getUserName(), user.getUserEmail(), user.getUserPhone(), user.getAddress(), user.getWallet() , user.getUserPassword());
		}
		else {
			return null;
		}
	}
	
	//delete Data
	public void deleteUserDetail(String email) throws ClassNotFoundException, SQLException, IOException {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("delete from User where userEmail =?");
		ps.setString(1, email);
		
		ps.execute();
	}
	
	//fetch All toy detail
	public void fetchAllToyDetails() throws ClassNotFoundException, SQLException, IOException {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("select toyId , toyName , toyBrand , toyPrice , Quantity  from toyTable");
//		ps.setString(1, email1);
		ResultSet res = ps.executeQuery();
		System.out.println("\t-----------------------------------------------------------------------------");
		System.out.println("\tTOY ID \t Toy Name \t Toy Brand \t Toy Price \t Quantity ");				
		System.out.println("\t-----------------------------------------------------------------------------");
		while(res.next()) {
			int toyId = res.getInt("toyId");
			String toyName  = res.getString("toyName");
			String toyBrand = res.getString("toyBrand");
			double toyPrice = res.getDouble("toyPrice");
			int quantity = res.getInt("quantity");
			System.out.println("\t" + toyId +"\t" + toyName +"\t\t" + toyBrand +"\t\t" + toyPrice + "\t\t" + quantity );
		}
		System.out.println("\t-------------------------------------------------------------------------------");
	}
	
	public Toy fetchToyDetailWithID(int toyId1) throws ClassNotFoundException, SQLException, IOException {
		Toy toy ;
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("select toyId , toyName , toyBrand , toyPrice , Quantity  from toyTable where toyId =?");
		ps.setInt(1, toyId1);
		ResultSet res = ps.executeQuery();
		System.out.println("\t-----------------------------------------------------------------------------");
		System.out.println("\tTOY ID \t Toy Name \t Toy Brand \t Toy Price \t Quantity ");				
		System.out.println("\t-----------------------------------------------------------------------------");
		if(res.next()) {
			int toyId = res.getInt("toyId");
			String toyName  = res.getString("toyName");
			String toyBrand = res.getString("toyBrand");
			double toyPrice = res.getDouble("toyPrice");
			int quantity = res.getInt("quantity");
			System.out.println("\t" + toyId +"\t" + toyName +"\t\t" + toyBrand +"\t\t" + toyPrice + "\t\t" + quantity );
			System.out.println("\t-------------------------------------------------------------------------------");

			return new Toy(toyId , toyName , toyBrand , toyPrice , quantity);
		}else {
			return null;
		}		
	}
	
	public void wallteData(String email , double wallet) throws SQLException, ClassNotFoundException, IOException {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("update user set wallet = ? where userEmail = ?");
		ps.setDouble(1, wallet);
		ps.setString(2, email);
		ps.execute();
	}
	
	
	
}
