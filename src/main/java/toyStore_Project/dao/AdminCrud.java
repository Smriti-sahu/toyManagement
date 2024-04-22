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

public class AdminCrud {
	
	public Connection createTable() throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		FileInputStream stream = new FileInputStream("DbConfig.properties");
		Properties p = new Properties();
		p.load(stream);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/toystore?createDatabaseIfNotExist=true",p);
		PreparedStatement ps = con.prepareStatement("create table if not exists admin(adminId int primary key AUTO_INCREMENT , adminName varchar(45) , adminEmail varchar(45) unique , adminPhone bigint(10) , toyStore varchar(45) unique , password varchar(45) )");
		ps.execute();
	
		return con;
	}
	
	
	
	public int insertAdminDetail(Admin admin) throws ClassNotFoundException, SQLException, IOException {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("insert into admin( adminName ,adminEmail , adminPhone , toyStore ,password) values(? , ?  , ?  , ? , ?)");
		ps.setString(1, admin.getAdminName());
		ps.setString(2, admin.getAdminEmail());
		ps.setLong(3, admin.getAdminPhone());
		ps.setString(4, admin.getToyStore());
		ps.setString(5, admin.getadminPassword());
		
		int result = ps.executeUpdate();
		return result;
	}
	
	
	
	
	public Admin fetchAdminDetail(String email1) throws ClassNotFoundException, SQLException, IOException {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("select * from admin where adminEmail = ?");
		ps.setString(1, email1);
		ResultSet res = ps.executeQuery();
		if(res.next()) {
			int adminId = res.getInt("adminId");
			String adminName  = res.getString("adminName");
			String adminEmail = res.getString("adminEmail");
			long adminPhone = res.getLong("adminPhone");
			String toyStore = res.getString("toyStore");
			String password = res.getString("password");
			
			Admin admin = new Admin(adminId, adminName, adminEmail, adminPhone, toyStore, password);
			return admin;
		}else {
			return null;
		}
	}

	public Admin updateAdminDetail(Admin admin , int choice ,Object o) throws ClassNotFoundException, SQLException, IOException {
		Connection con = createTable();
		PreparedStatement ps;
		// update name
		if(choice ==1) {
			String name = (String) o;
			ps = con.prepareStatement("update admin set adminName=? where adminId=?");
			ps.setString(1, name);
			ps.setInt(2, admin.getAdminId());
			ps.executeUpdate();
			return new Admin(admin.getAdminId(), admin.getAdminEmail(), admin.getAdminName(), admin.getAdminPhone(), admin.getToyStore(), admin.getadminPassword());
		}
		//update email
		else if(choice ==2) {
			String email = (String) o;
			ps = con.prepareStatement("update admin set adminEmail=? where adminId=?");
			ps.setString(1, email);
			ps.setInt(2, admin.getAdminId());
			ps.executeUpdate();
			return new Admin(admin.getAdminId(), admin.getAdminEmail(), admin.getAdminName(), admin.getAdminPhone(), admin.getToyStore(), admin.getadminPassword());
		}
		//update phone
		else if(choice==3) {
			long phone = (long) o;
			ps = con.prepareStatement("update admin set adminPhone=? where adminId=?");
			ps.setLong(1 , phone);
			ps.setInt(2, admin.getAdminId());
			ps.executeUpdate();
			return new Admin(admin.getAdminId(), admin.getAdminEmail(), admin.getAdminName(), admin.getAdminPhone(), admin.getToyStore(), admin.getadminPassword());
		}
		//update Toy Store Name
		else if(choice ==4) {
			String toyStore = (String) o;
			ps = con.prepareStatement("update admin set toyStore=? where adminId=?");
			ps.setString(1, toyStore);
			ps.setInt(2, admin.getAdminId());
			ps.executeUpdate();
			return new Admin(admin.getAdminId(), admin.getAdminEmail(), admin.getAdminName(), admin.getAdminPhone(), admin.getToyStore(), admin.getadminPassword());
		}
		else if(choice==5) {
			String password = (String) o;
			ps = con.prepareStatement("update admin set password=? where adminId=?");
			ps.setString(1, password);
			ps.setInt(2, admin.getAdminId());
			ps.executeUpdate();
			return new Admin(admin.getAdminId(), admin.getAdminEmail(), admin.getAdminName(), admin.getAdminPhone(), admin.getToyStore(), admin.getadminPassword());
		}
		else {
			return null;
		}
		
	}
	
	public void deleteAdminDetail(String email) throws ClassNotFoundException, SQLException, IOException {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("delete from admin where adminEmail =?");
		ps.setString(1, email);
		
		ps.execute();
		
	}
}
