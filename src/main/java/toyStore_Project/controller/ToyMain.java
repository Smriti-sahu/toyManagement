package toyStore_Project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import toyStore_Project.dao.AdminCrud;
import toyStore_Project.dao.ToyCrud;
import toyStore_Project.dao.UserCrud;
import toyStore_Project.dto.Admin;
import toyStore_Project.dto.Toy;
import toyStore_Project.dto.User;

public class ToyMain {
	static Scanner sc = new Scanner(System.in);
	static AdminCrud adminCrud = new AdminCrud();
	static ToyCrud toyCrud = new ToyCrud();
	static UserCrud userCrud = new UserCrud();

	public static void main(String[] args) throws Exception {
		adminCrud.createTable();
		toyCrud.createTable();
		userCrud.createTable();
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("-******************Welcome to Toy Store Management*************************-");
		System.out.println("----------------------------------------------------------------------------");
		toyMain();

	}

	// *********************************************************
	// ***************Toy Main********************************
	// ******************************************************
	public static void toyMain() throws Exception {
		boolean check = true;
		do {
			System.out.println("\n 1.Admin \n 2.User \n 3.Exit");
			int choice = sc.nextInt();

			switch (choice) {
			case 1: {
				admin();
				break;
			}
			case 2: {
				user();
				break;
			}
			default: {
				break;
			}
			}
		} while (check);
	}
	// *****************************************************************
	// *****************************************************************
	// ****************************************************************
	// ************************Admin Page******************************
	// ***************************************************************
	// *****************************************************************
	// *****************************************************************

	public static void admin() throws Exception {
		System.out.println("\t----------Welcome to Admin Page-------------");
		boolean check = true;
		do {
			System.out.println("\tEnter Choice : \n\t 1.Registration \n\t 2.Login \n\t 3.Exit");
			int choice = sc.nextInt();

			if (choice == 1) {
				adminRegister();
			} else if (choice == 2) {
				adminLogin();
			} else if (choice == 3) {
				toyMain();
			} else {
				toyMain();
			}
		} while (check);
	}

	// ****************************************************************
	// *********************Admin Register*****************************
	// ********************************************************************
	public static void adminRegister() throws Exception {
		int data = 0;
		System.out.println("\t\t--------Enter Data for Registration---------");
		System.out.print("\n\t\tEnter Admin Name : ");
		String name = sc.next();
		System.out.print("\n\t\tEnter Admin Email : ");
		String email = sc.next();
		System.out.print("\n\t\tEnter Admin Phone : ");
		long phone = sc.nextLong();
		System.out.print("\n\t\tEnter toyStore Name : ");
		String toyStoreName = sc.next();
		System.out.print("\n\t\tEnter Admin Password : ");
		String password = sc.next();

		Admin admin = new Admin(name, email, phone, toyStoreName, password);
		try {
			data = adminCrud.insertAdminDetail(admin);
			System.out.println("\n\t\t*********** Data Saved Successfully *************");
//			adminLogin();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	// *********************************************************************
	// *********************Admin Login Page********************************
	// *********************************************************************

	public static void adminLogin() throws Exception {
		System.out.println("\n\t\t----------Enter Data for Login------------");
		System.out.print("\n\t\tEnter eamil :");
		String email = sc.next();
		System.out.print("\n\t\tEnter password : ");
		String password = sc.next();

		Admin a = adminCrud.fetchAdminDetail(email);

		if (a != null) {
			if (a.getadminPassword().equals(password)) {
				System.out.println("\n\t\t*********login success... Dear " + a.getAdminName() + "************");
				// *********** admin Section**********************
				adminSection(a);
			} else {
				System.out.println("\n\t\t\t Entered wrong password....check again\n");
				adminLogin();
			}
		} else {
			System.out.println("\n\t\t\t Entered wrong email...check again\n");
			adminLogin();
		}
	}

	// ***************************************************************
	// **********************Admin Section*****************************
	// *****************************************************************

	public static void adminSection(Admin a) throws Exception {
		boolean check = true;
		do {
			System.out.println("\n\t\t\t------------Welcom to Admin Section------------");
			System.out.println(
					"\n\t\t\tEnter Your Choice : \n\t\t\t 1.Admin Profile \n\t\t\t 2.Toy Store \n\t\t\t 3.Exit");
			int choice = sc.nextInt();
			switch (choice) {

			/////// admin Profile Page////////

			case 1: {
				System.out.println("\t\t\t\t**************Welcome to Admin Profile Section********************");
				boolean profileCheck = true;
				do {
					System.out.println(
							"\n\t\t\t\tChoice Admin Profile : \n\t\t\t\t 1.Fetch Admin Detail \n\t\t\t\t 2.Update Admin Detail \n\t\t\t\t 3.Delete Admin Detail \n\t\t\t\t 4.Exit");
					int profileChoice = sc.nextInt();
					switch (profileChoice) {
					case 1: {
						System.out.println("\t\t\t\t\t**********Admin Details************");
						System.out.println("\t\t\t\t\t-----------------------------------");
						System.out.println(
								"\t\t\t\t\tAdmin ID        :  " + a.getAdminId() + "\n\t\t\t\t\tAdmin Name      :  "
										+ a.getAdminName() + "\n\t\t\t\t\tAdmin Email     :  " + a.getAdminEmail()
										+ "\n\t\t\t\t\tAdmin Phone     :  " + a.getAdminPhone()
										+ "\n\t\t\t\t\tToy Store Name  :  " + a.getToyStore()
										+ "\n\t\t\t\t\tAdmin Password  :  " + a.getadminPassword());
						System.out.println("\t\t\t\t\t------------------------------------");
						break;
					}
					case 2: {
						adminUpdate(a);
						break;
					}
					case 3: {
						adminCrud.deleteAdminDetail(a.getAdminEmail());
						System.out.println("\n\t\t\t\t\t*******Admin Data Deleted*************");
						admin();
						profileCheck = false;
						break;
					}
					default:
						adminSection(a);
						profileCheck = false;
						break;
					}
				} while (profileCheck);
				check = false;
				break;
			}
			///// Toy Store Detail/////////
			case 2: {
				toyStore(a);
				break;
			}
			case 3: {
				admin();
				break;
			}
			default:
				admin();
				break;
			}
		} while (check);
	}

	// ***********************************************************************
	// ***************************Admin Update Section************************
	// *********************************************************************

	public static void adminUpdate(Admin admin) throws ClassNotFoundException, SQLException, IOException {
		boolean check = true;
		do {
			System.out.println(
					"\n\t\t\t\t\tWhich Column you want to update :\n\t\t\t\t\t 1.Admin Name \n\t\t\t\t\t 2.Admin Email \n\t\t\t\t\t 3.Admin PhoneNumber \n\t\t\t\t\t 4.Store Name \n\t\t\t\t\t 5.Password \n\t\t\t\t\t 6.Exit ");
			int choice = sc.nextInt();
			if (choice == 1) {
				System.out.println("\t\t\t\t\tYou are Updating Admin name ");
				System.out.print("\n\t\t\t\t\tEnter new Name :");
				String adminName = sc.next();
				adminCrud.updateAdminDetail(admin, choice, adminName);
				System.out.println("\n\t\t\t\t\t************* Name Updated**************");
			} else if (choice == 2) {
				System.out.println("\t\t\t\t\tYou are Updating Admin Email");
				System.out.print("\n\t\t\t\t\tEnter new Email  :");
				String adminEmail = sc.next();
				adminCrud.updateAdminDetail(admin, choice, adminEmail);
				System.out.println("\n\t\t\t\t\t************* Email Updated**************");
			} else if (choice == 3) {
				System.out.println("\t\t\t\t\tYou are Updating Admin Phone");
				System.out.print("\n\t\t\t\t\tEnter new Phone Number  :");
				long adminPhone = sc.nextLong();
				adminCrud.updateAdminDetail(admin, choice, adminPhone);
				System.out.println("\n\t\t\t\t\t*************Phone Number Updated**************");
			} else if (choice == 4) {
				System.out.println("\t\t\t\t\tYou are Updating Toy Store Name");
				System.out.print("\n\t\t\t\t\tEnter new ToyStore Name  :");
				String toyStore = sc.next();
				adminCrud.updateAdminDetail(admin, choice, toyStore);
				System.out.println("\n\t\t\t\t\t************* Toy Store Name Updated**************");
			} else if (choice == 5) {
				System.out.println("\t\t\t\t\tYou are Updating Admin Password");
				System.out.print("\n\t\t\t\t\tEnter new Password  :");
				String adminPassword = sc.next();
				adminCrud.updateAdminDetail(admin, choice, adminPassword);
				System.out.println("\n\t\t\t\t\t*************Password Updated**************");
			} else {
				break;
			}

			System.out.print("\n\t\t\t\t\tDo you Want to update Anything Else(Y/N) : ");
			String choiceNext = sc.next();
			if (choiceNext.equalsIgnoreCase("Y")) {
				check = true;
			} else if (choiceNext.equalsIgnoreCase("N")) {
				check = false;
			} else {
				check = false;
			}
		} while (check);
	}

	// *****************************************************************
	// *****************************************************************
	// ****************************************************************
	// *************************Toy Store ************************************
	// *******************************************************************
	// *****************************************************************
	// *****************************************************************
	public static void toyStore(Admin admin) throws Exception {
		boolean check = true;
		do {
			System.out.println(
					"\n\t\t\t\t********Which Operation You want to perform********* \n\t\t\t\t 1.Upload Toy Detail \n\t\t\t\t 2.Update Toy Detail \n\t\t\t\t 3.Fetch All the Toy \n\t\t\t\t 4.Delete Toy Detail \n\t\t\t\t 5.Exit ");
			int choice = sc.nextInt();

			if (choice == 1) {
				// **********Insert Toy Data*****************
				uploadToyData(admin);
			} else if (choice == 2) {
				// **************Update Toy Data*****************
				updateToyDetail();
			} else if (choice == 3) {
				// *****************Fetch Toy Data******************
				toyCrud.fetchToyDetail(admin.getAdminEmail());
			} else if (choice == 4) {
				// *************Delete Toy Detail********************
				System.out.println("\n\t\t\t\tEnter Toy ID to Perform Delete Operation : ");
				int toyId = sc.nextInt();

				Toy toy = new Toy(toyId);
				toyCrud.deleteToyDetail(toyId);
				System.out.println("\n\t\t\t\t**********Data Deleted successfully*************");

				System.out.print("\n\n\t\t\t\tDo You Want to Insert new Row (Y/N) : ");
				String deleteChoice = sc.next();
				if (deleteChoice.equalsIgnoreCase("y")) {
					uploadToyData(admin);
				} else {
					toyStore(admin);
				}
			} else {
				adminSection(admin);
			}
		} while (check);
	}

	// *******************************************************
	// ******************** Insert Toy Data***********************
	// ********************************************************
	public static void uploadToyData(Admin admin) throws Exception {
		System.out.println("\n\t\t\t\t\t--------Insert New Toy Details----------- ");
		int data = 0;
		System.out.print("\n\t\t\t\t\tEnter Toy Name  : ");
		String toyName = sc.next();
		System.out.print("\n\t\t\t\t\tEnter Toy Brand Name : ");
		String toyBrand = sc.next();
		System.out.print("\n\t\t\t\t\tEnter Toy Price : ");
		double price = sc.nextDouble();
		System.out.print("\n\t\t\t\t\tEnter toy Quantity : ");
		int quantity = sc.nextInt();

		Toy toy = new Toy(toyName, toyBrand, price, quantity, admin.getAdminEmail());

		data = toyCrud.insertToyDetail(toy, admin);
		System.out.println("\n\t\t\t\t\t******Toy Data inserted Successfully***********");

		System.out.print("\n\t\t\t\t\tDo you want to insert more Data(Y/N) : ");
		String choice = sc.next();
		if (choice.equalsIgnoreCase("y")) {
			uploadToyData(admin);
		} else {
			toyStore(admin);
		}
	}

	// ******************************************************************
	// **************** Update toy detail*********************************
	// ******************************************************************
	public static void updateToyDetail() throws Exception {
		boolean check = true;
		do {
			System.out.println(
					"\n\t\t\t\t\tWhich Column You want to Update :  \n\t\t\t\t\t 1.Toy Name \n\t\t\t\t\t 2.Toy Brand Name \n\t\t\t\t\t 3.Toy Price \n\t\t\t\t\t 4.Quantity \n\t\t\t\t\t 5.Exit");
			int choice = sc.nextInt();

			System.out.print("\n\t\t\t\t\t\tEnter Toy ID : ");
			int id = sc.nextInt();

			Toy toy = new Toy(id);

			if (choice == 1) {
				System.out.println("\t\t\t\t\tYou are Updating Toy Name ");
				System.out.print("\n\t\t\t\t\tEnter new Name :");
				String toyName = sc.next();
				toyCrud.updateToyDetail(toy, choice, toyName);
				System.out.println("\n\t\t\t\t\t*************Name Updated**************");
			} else if (choice == 2) {
				System.out.println("\t\t\t\t\tYou are Updating Toy Brand Name ");
				System.out.print("\n\t\t\t\t\tEnter new Brand Name :");
				String toyBrandName = sc.next();
				toyCrud.updateToyDetail(toy, choice, toyBrandName);
				System.out.println("\n\t\t\t\t\t*************Brand Name Updated**************");
			} else if (choice == 3) {
				System.out.println("\t\t\t\t\tYou are Updating Toy price ");
				System.out.print("\n\t\t\t\t\tEnter new Price :");
				double toyPrice = sc.nextDouble();
				toyCrud.updateToyDetail(toy, choice, toyPrice);
				System.out.println("\n\t\t\t\t\t*************Price Updated Updated**************");
			} else if (choice == 4) {
				System.out.println("\t\t\t\t\tYou are Updating Toy Quantity ");
				System.out.print("\n\t\t\t\t\tEnter new Quantity :");
				int toyQuantity = sc.nextInt();
				toyCrud.updateToyDetail(toy, choice, toyQuantity);
				System.out.println("\n\t\t\t\t\t*************Toy Quantity Updated**************");
			} else {
				break;
			}

			System.out.print("\n\t\t\t\t\tDo you Want to update Anything Else(Y/N) : ");
			String choiceNext = sc.next();
			if (choiceNext.equalsIgnoreCase("Y")) {
				check = true;
			} else if (choiceNext.equalsIgnoreCase("N")) {
				check = false;
			} else {
				check = false;
			}

		} while (check);
	}

	// *****************************************************************
	// *****************************************************************
	// ****************************************************************
	// ****************** USER DETAILS ********************************
	// *****************************************************************
	// *****************************************************************
	// *****************************************************************
	public static void user() throws Exception {
		System.out.println("\t*********This is User Section*****************");

		System.out.println("\tEnter Your CHoice : \n\t 1.Register YourSelf \n\t 2.Login \n\t 3.Exit");
		int choice = sc.nextInt();
		if (choice == 1) {
			userRegister();
		} else if (choice == 2) {
			userLogin();
		} else {
			toyMain();
		}
	}

	// **********************************************************************
	// **********************USER REGISTERATION******************************
	// ************************************************************************
	public static void userRegister() throws Exception {
		int data = 0;
		System.out.println("\t\t--------Enter Data for Registration---------");
		System.out.print("\n\t\tEnter Your Name : ");
		String userName = sc.next();
		System.out.print("\n\t\tEnter Your Email : ");
		String userEmail = sc.next();
		System.out.print("\n\t\tEnter Your Phone : ");
		long userPhone = sc.nextLong();
		System.out.print("\n\t\tEnter Your Wallet Balance : ");
		double wallet = sc.nextDouble();
		System.out.print("\n\t\tEnter Your Address : ");
		String address = sc.next();
		System.out.print("\n\t\tEnter Your Password : ");
		String password = sc.next();

		User user = new User(userName, userEmail, userPhone, address, wallet, password);

		try {
			data = userCrud.insertUserDetail(user);
			System.out.println("\n\t\t*********** Data Saved Successfully *************");
			userLogin();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	// *********************************************************
	// *****************USER LOGIN****************************
	// ******************************************************
	public static void userLogin() throws Exception {
		System.out.println("\n\t\t----------Enter Your Data for Login------------");
		System.out.print("\n\t\tEnter eamil :");
		String email = sc.next();
		System.out.print("\n\t\tEnter password : ");
		String password = sc.next();

		User u = userCrud.fetctUserDetail(email);

		if (u != null) {
			if (u.getUserPassword().equals(password)) {
				System.out.println("\n\t\t*********login success... Dear " + u.getUserName() + "************");
				// *********** User Section**********************
				userSection(u);
			} else {
				System.out.println("\n\t\t Entered wrong password....check again\n");
				userLogin();
			}
		} else {
			System.out.println("\n\t\t Entered wrong email...check again\n");
			userLogin();
		}
	}

	// *******************************************************************
	// **********************User Section*****************************
	// ***************************************************************
	public static void userSection(User user) throws Exception {
		System.out.println("\n\t\t\tEnter Your Choice : \n\t\t\t 1.User Profile \n\t\t\t 2.Toy Store \n\t\t\t 3.Exit");
		int choice = sc.nextInt();
		if (choice == 1) {
			userProfile(user);
		} else if (choice == 2) {
			toyStoreDetail(user);
		} else {
			toyMain();
		}
	}

	// ******************************************************************
	// *********************user Profile*********************************
	// ******************************************************************
	public static void userProfile(User user) throws Exception {
		System.out.println(
				"\t\t\t\t*******User Profile*********** \n\t\t\t\t 1.Fetch Your Detail \n\t\t\t\t 2.Update Your Data \n\t\t\t\t 3.Delete Your Profile \n\t\t\t\t 4.Exit");
		int choice = sc.nextInt();
		if (choice == 1) {
			System.out.println("\t\t\t\t\t*************** User Details **********************");
			System.out.println("\t\t\t\t\t-------------------------------------------------------");
			System.out.println("\t\t\t\t\tUser ID         :  " + user.getUserId() + "\n\t\t\t\t\tUser Name      :  "
					+ user.getUserName() + "\n\t\t\t\t\tUser Email     :  " + user.getUserEmail()
					+ "\n\t\t\t\t\tUser Phone     :  " + user.getUserPhone() + "\n\t\t\t\t\tUser Addresss  :  "
					+ user.getAddress() + "\n\t\t\t\t\tWallet Rs.     :  " + user.getWallet()
					+ "\n\t\t\t\t\tPassword       :  " + user.getUserPassword());
			System.out.println("\t\t\t\t\t-------------------------------------------------------\n");
			userProfile(user);
		} else if (choice == 2) {
			updateUserDetail(user);
			userProfile(user);
		} else if (choice == 3) {
			System.out.print("\n\t\t\t\t****Are you Sure ? You want to delete a Your Detail (Y/N) :");
			String deleteChoice = sc.next();
			if (deleteChoice.equalsIgnoreCase("y")) {
				userCrud.deleteUserDetail(user.getUserEmail());
				System.out.println("\n\t\t\t***********YOUR DATA IS DELETED SUCCESSFULLY*****************\n");
				user();
			} else {
				userProfile(user);
			}

		} else {
			userSection(user);
		}
	}

	// **********************************************************
	// ******************* Update User Detail *******************
	// ***********************************************************
	public static void updateUserDetail(User user) throws Exception {
		boolean check = true;
		do {
			System.out.println("\n\t\t\t\t\tWhich Column You want to Update :  " + "\n\t\t\t\t\t 1.User Name "
					+ "\n\t\t\t\t\t 2.User Email " + "\n\t\t\t\t\t 3.Phone Number " + "\n\t\t\t\t\t 4.User Address "
					+ "\n\t\t\t\t\t 5.Wallet Rs. " + "\n\t\t\t\t\t 6.Password " + "\n\t\t\t\t\t 7.Exit");
			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("\t\t\t\t\tYou are Updating Your Name ");
				System.out.print("\n\t\t\t\t\tEnter new Name :");
				String userName = sc.next();
				userCrud.updateUserDetail(user, choice, userName);
				System.out.println("\n\t\t\t\t\t*************Name Updated**************");
			} else if (choice == 2) {
				System.out.println("\t\t\t\t\tYou are Updating Your Email ");
				System.out.print("\n\t\t\t\t\tEnter new Email Id :");
				String userEmail = sc.next();
				userCrud.updateUserDetail(user, choice, userEmail);
				System.out.println("\n\t\t\t\t\t*************Email Updated**************");
			} else if (choice == 3) {
				System.out.println("\t\t\t\t\tYou are Updating Phone Number ");
				System.out.print("\n\t\t\t\t\tEnter new Phone :");
				long phone = sc.nextLong();
				userCrud.updateUserDetail(user, choice, phone);
				System.out.println("\n\t\t\t\t\t*************Phone Updated **************");
			} else if (choice == 4) {
				System.out.println("\t\t\t\t\tYou are Updating Your Address ");
				System.out.print("\n\t\t\t\t\tEnter new Address :");
				String address = sc.next();
				userCrud.updateUserDetail(user, choice, address);
				System.out.println("\n\t\t\t\t\t*************Address Updated**************");
			} else if (choice == 5) {
				System.out.println("\t\t\t\t\tYou are Updating Your Wallet Rs. ");
				System.out.print("\n\t\t\t\t\tEnter new Wallet Data :");
				double wallet = sc.nextDouble();
				userCrud.updateUserDetail(user, choice, wallet);
				System.out.println("\n\t\t\t\t\t*************Wallet Data Updated**************");
			} else if (choice == 6) {
				System.out.println("\t\t\t\t\tYou are Updating Your Password ");
				System.out.print("\n\t\t\t\t\tEnter new Password :");
				String password = sc.next();
				userCrud.updateUserDetail(user, choice, password);
				System.out.println("\n\t\t\t\t\t*************Password Updated**************");
			} else {
				userProfile(user);
			}

			System.out.print("\n\t\t\t\t\tDo you Want to update Anything Else(Y/N) : ");
			String choiceNext = sc.next();
			if (choiceNext.equalsIgnoreCase("Y")) {
				check = true;
			} else if (choiceNext.equalsIgnoreCase("N")) {
				check = false;
			} else {
				check = false;
			}
		} while (check);
	}

	// **************************************************************
	// *********************TOY STORE DETAIL************************
	// **************************************************************
	static List<String> list = new ArrayList<String>();
	static double addToCart;

	public static void toyStoreDetail(User user) throws Exception {
		System.out.println("\t\t\t\t********Welcome to Toy Store**************\n");
		System.out.println("\t\t\t\tEnter Your Choice \n\t\t\t\t 1.Fetch All Toy Details \n\t\t\t\t 2.Exit");
		int choice = sc.nextInt();
		
		double walletCurrentAmount = user.getWallet();
		
		if (choice == 1) {
			// Choice = 1 FetchDetail
			userCrud.fetchAllToyDetails();
			boolean check = true;
			// *************Want to buy any product or not**********************
			System.out.print("\n\t\t Do You Want to Buy Any Product (Y/N) : ");
			String userChoice = sc.next();
			if (userChoice.equalsIgnoreCase("y")) {
				addToCart();
				do {
					// *****Want to buy more product or not*****
					System.out.print("\n\t\t Do You Want to Buy More Product : (Y/N) ");
					String userAgainChoice = sc.next();
					if (userAgainChoice.equalsIgnoreCase("y")) {
						addToCart();
					} else {
						System.out.print("\n\t\t*****Do You Want to Purchase the Products (Y/N) :");
						String purchase = sc.next();
						if(purchase.equalsIgnoreCase("y")) {
							
							if(addToCart >= user.getWallet()) {
								System.out.println("\n\t\tYour Current Wallet Balance is : " + user.getWallet());
								System.out.println("\n\t\t--------------------------------------");
								System.out.println("\t\t  Your Total Bill is : " + addToCart);
								System.out.println("\t\t--------------------------------------");
								
								System.out.println("\n\t\t~~~~~Balance Insufficient~~~~~~~~");
								
								System.out.print("\n\t\t~~~~~Do You Want to Add Money To Your Wallet (Y/N) :");
								String walletYesNO = sc.next();
								
								if(walletYesNO.equalsIgnoreCase("y")) {
									System.out.print("\n\t\tHow much Amount you want to add : ");
									double amountAdd = sc.nextDouble();
									
									walletCurrentAmount += amountAdd;
									userCrud.wallteData(user.getUserEmail() , walletCurrentAmount);
									
									System.out.println("\n\t\tNow Your Current wallet Balance is  : " + walletCurrentAmount);
									
									System.out.println("\n\t\tYou Want to Purchase the Products or not (Y/N) : ");
									String purchaseOrNot = sc.next();
									
									if(purchaseOrNot.equalsIgnoreCase("y")) {
										System.out.println("\n\t\t Your Bill is Here :");
										System.out.println("\t\t----------------------------------------------");
										System.out.println("\t\t Toy Name \t Toy Quantity \t Toy Price  ");
										System.out.println("\t\t-----------------------------------------------");
										System.out.println(list);
										System.out.println("\t\t-----------------------------------------------");
										System.out.println("\t\t                        Total Amount  :" + addToCart);

										System.out.println("\n\t**********  THANKYOU TO BUYING PRODUCT FROM OUR STORE ***************");
										
										walletCurrentAmount -= addToCart;
										userCrud.wallteData(userChoice, walletCurrentAmount);
										
										System.out.println("\n\t\tNow Your Current Wallet Balance is : " + walletCurrentAmount);
										
										System.out.println("\n\t\t~~~~~Now you Can Exit From Your Profile~~~~~");
										userSection(user);
									}else {
										System.out.println("\\n\\t**********  THANKYOU TO VISITING OUR STORE , VISIT AGAIN..!!! ***************");
										System.out.println("\n\t\t~~~~~Now you Can Exit From Your Profile~~~~~");
										userSection(user);
									}
									
								}else {
									System.out.println("\\n\\t**********  THANKYOU TO VISITING OUR STORE , VISIT AGAIN..!!! ***************");
									System.out.println("\n\t\t~~~~~Now you Can Exit From Your Profile~~~~~");
									userSection(user);
								}
							}else {
								System.out.println("\n\t\t Your Bill is Here :");
								System.out.println("\t\t----------------------------------------------");
								System.out.println("\t\t Toy Name \t Toy Quantity \t Toy Price  ");
								System.out.println("\t\t-----------------------------------------------");
								System.out.println(list);
								System.out.println("\t\t-----------------------------------------------");
								System.out.println("\t\t                        Total Amount  :" + addToCart);

								
								walletCurrentAmount -= addToCart;
								userCrud.wallteData(userChoice, walletCurrentAmount);
								
								System.out.println("\t\tNow Your Current Wallet Balance is : " + walletCurrentAmount);

								
								System.out.println("\n\t**********  THANKYOU TO BUYING PRODUCT FROM OUR STORE ***************");

								
								System.out.println("\n\t\t~~~~~Now you Can Exit From Your Profile~~~~~");
								userSection(user);	
								
							}
							
						}else {
							System.out.println("\n\t\t****Visit Again*********");
							userSection(user);
						}	
					}
				} while (check);
			} else {
				userSection(user);
			}
		} else {
			// choice = 2 Exit
			userSection(user);
		}
	}

	public static void addToCart() throws ClassNotFoundException, SQLException, IOException {
		System.out.print("\n\t\tIf yes then Choose Toy Id : ");
		int toyId = sc.nextInt();
		double toyPrice = 0;

		System.out.println("\n\t***************This is Your Toy Detail***************");
		Toy toy = userCrud.fetchToyDetailWithID(toyId);

		System.out.print("\n\t\tQuantity of Toy : ");
		int toyQuantity = sc.nextInt();

		if (toyQuantity > toy.getQuantity()) {
			System.out.println("\t\t~~~~~~~Stock is not Aviailable~~~~~");
		} else {
			toyPrice = toy.getToyPrice() * toyQuantity;
			addToCart += toyPrice;
			System.out.println("\n\t~~~~~~~~~~~~Item is added in your Cart~~~~~~~~~~~~~");
//			System.out.println("Bill : " + addToCart);

			list.add("\t\t " + toy.getToyName() + "\t\t" + toyQuantity + "   \t   " + toyPrice + "\n");
		}
	}
}
