package toyStore_Project.dto;

public class Toy {
	private int toyId;
	private String toyName;
	private String toyBrand;
	private double toyPrice;
	private int quantity;
	private String email;
	
	public Toy(int toyId, String toyName, String toyBrand, double toyPrice, int quantity) {
		this.toyId = toyId;
		this.toyName = toyName;
		this.toyBrand = toyBrand;
		this.toyPrice = toyPrice;
		this.quantity = quantity;
	}
	
	public Toy(int toyId, String toyName, String toyBrand, double toyPrice, int quantity, String email) {
		this.toyId = toyId;
		this.toyName = toyName;
		this.toyBrand = toyBrand;
		this.toyPrice = toyPrice;
		this.quantity = quantity;
		this.email = email;
	}
	public Toy(String toyName, String toyBrand, double toyPrice, int quantity, String email) {
		this.toyName = toyName;
		this.toyBrand = toyBrand;
		this.toyPrice = toyPrice;
		this.quantity = quantity;
		this.email = email;
	}
	public Toy(int toyId) {
		this.toyId = toyId;
	}


	
	public int getToyId() {
		return toyId;
	}


	public void setToyId(int toyId) {
		this.toyId = toyId;
	}


	public String getToyName() {
		return toyName;
	}


	public void setToyName(String toyName) {
		this.toyName = toyName;
	}


	public String getToyBrand() {
		return toyBrand;
	}


	public void setToyBrand(String toyBrand) {
		this.toyBrand = toyBrand;
	}


	public double getToyPrice() {
		return toyPrice;
	}


	public void setToyPrice(double toyPrice) {
		this.toyPrice = toyPrice;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


//	@Override
//	public String toString() {
//		return "Toy [toyId=" + toyId + ", toyName=" + toyName + ", toyBrand=" + toyBrand + ", toyPrice=" + toyPrice
//				+ ", quantity=" + quantity + ", email=" + email + "]";
//	}
	
	
}
