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

public class ToyCrud {
	
	
	// create table
	public Connection createTable() throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		FileInputStream stream = new FileInputStream("DbConfig.properties");
		Properties p = new Properties();
		p.load(stream);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/toystore?createDatabaseIfNotExist=true",p);
		PreparedStatement ps = con.prepareStatement("create table if not exists ToyTable(toyId int primary key AUTO_INCREMENT , toyName varchar(45),toyBrand varchar(45) ,toyPrice double , quantity int , toyEmail varchar(45) , FOREIGN KEY (toyEmail) REFERENCES ADMIN(adminEmail) )");
		ps.execute();
	
		return con;
	}
	

	//insert toy date
	public int insertToyDetail(Toy toy, Admin admin) throws ClassNotFoundException, SQLException, IOException {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("insert into toyTable( toyName ,toyBrand , toyPrice , quantity ,toyEmail) values(? , ?  , ?  , ? , ?)");
		ps.setString(1, toy.getToyName());
		ps.setString(2, toy.getToyBrand());
		ps.setDouble(3, toy.getToyPrice());
		ps.setInt(4, toy.getQuantity());
		ps.setString(5, admin.getAdminEmail());
		
		int result = ps.executeUpdate();
		return result;
	}

	//fetch toy Data
	public void fetchToyDetail(String email1) throws ClassNotFoundException, SQLException, IOException {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("select * from toyTable where toyEmail=?");
		ps.setString(1, email1);
		ResultSet res = ps.executeQuery();
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("TOY ID \t Toy Name \t Toy Brand \t Toy Price \t Quantity \t Toy Email ");				
		System.out.println("-----------------------------------------------------------------------------------------------");
		while(res.next()) {
			int toyId = res.getInt("toyId");
			String toyName  = res.getString("toyName");
			String toyBrand = res.getString("toyBrand");
			double toyPrice = res.getDouble("toyPrice");
			int quantity = res.getInt("quantity");
			String toyEmail = res.getString("toyEmail");
			System.out.println(toyId +"\t" + toyName +"\t\t" + toyBrand +"\t\t" + toyPrice + "\t\t" + quantity +"\t\t" + toyEmail);
		}
		System.out.println("--------------------------------------------------------------------------------------------------");

	} 
	
	// update toy detail
	public Toy updateToyDetail(Toy toy , int choice , Object o) throws Exception {
		Connection con = createTable();
		PreparedStatement ps;
		// update name
		if(choice ==1) {
			String name = (String) o;
			ps = con.prepareStatement("update toyTable set toyName=? where toyId=?");
			ps.setString(1, name);
			ps.setInt(2, toy.getToyId());
			ps.executeUpdate();
			return new Toy(toy.getToyId(), toy.getToyName(), toy.getToyBrand(), toy.getToyPrice(), toy.getQuantity(), toy.getEmail());
		}
		//update email
		else if(choice ==2) {
			String brandName = (String) o;
			ps = con.prepareStatement("update toyTable set toyBrand=? where toyId=?");
			ps.setString(1, brandName);
			ps.setInt(2, toy.getToyId());
			ps.executeUpdate();
			return new Toy(toy.getToyId(), toy.getToyName(), toy.getToyBrand(), toy.getToyPrice(), toy.getQuantity(), toy.getEmail());
		}
		//update phone
		else if(choice==3) {
			double price =  (double) o;
			ps = con.prepareStatement("update toyTable set toyPrice=? where toyId=?");
			ps.setDouble(1 , price);
			ps.setInt(2, toy.getToyId());
			ps.executeUpdate();
			return new Toy(toy.getToyId(), toy.getToyName(), toy.getToyBrand(), toy.getToyPrice(), toy.getQuantity(), toy.getEmail());
		}
		//update Toy Store Name
		else if(choice ==4) {
			int quantity = (int) o;
			ps = con.prepareStatement("update toyTable set quantity=? where toyId=?");
			ps.setInt(1, quantity);
			ps.setInt(2, toy.getToyId());
			ps.executeUpdate();
			return new Toy(toy.getToyId(), toy.getToyName(), toy.getToyBrand(), toy.getToyPrice(), toy.getQuantity(), toy.getEmail());
		}
		else {
			return null;
		}
	}
	
	//delete toy detail
	public void deleteToyDetail(int toyId) throws ClassNotFoundException, SQLException, IOException {
		Connection con = createTable();
		PreparedStatement ps = con.prepareStatement("delete from toyTable where toyId =?");
		ps.setInt(1, toyId);
		
		ps.execute();
	}
	
}
