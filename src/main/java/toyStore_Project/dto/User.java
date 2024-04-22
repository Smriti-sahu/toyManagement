package toyStore_Project.dto;

public class User {
	private int userId;
	private String userName;
	private String userEmail;
	private long userPhone;
	private String address;
	private double wallet;
	private String userPassword;

	public User(int userId, String userName, String userEmail, long userPhone, String address, double wallet,
			String userPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.address = address;
		this.wallet = wallet;
		this.userPassword = userPassword;
	}

	public User(String userName, String userEmail, long userPhone, String address, double wallet, String userPassword) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.address = address;
		this.wallet = wallet;
		this.userPassword = userPassword;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public long getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	
	
}
