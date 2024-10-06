package objectClass;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import DB.DatabaseManagement;
import Tools.RentingClass;

public class Admin {
      private String userName;
      private String passWord;
      static DatabaseManagement DBM;
      
      
      
	public Admin(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public static List<Car> Showcars() {
		DBM=new DatabaseManagement();
		return DBM.showCarsList();
	}
	
	public static List<Car> searchCar(String carNum,String brand){
		DBM=new DatabaseManagement();
		return DBM.SearchCar(carNum, brand);
	}
	
	public static int DeleteCar(Car car) {
		  DBM=new DatabaseManagement();
		  return DBM.DeleteCar(car);
	}
	
	public static int Addcar(Car car) {
		DBM=new DatabaseManagement();
		return DBM.addDataToDatabase(car);
	}
	
	public static int UpdateCar(Car car,String carNum) {
		DBM=new DatabaseManagement();
		return DBM.UpdateCar(car,carNum);
	}
	
	/////////////////////////////////////////////////////////////////////////////customer////////////////////////////////////////////////////////////////
	
	public static int UpdateCus(Customer cus,String cin) {
		DBM=new DatabaseManagement();
		return DBM.UpdateCus(cus,cin);
	}
	
	
	public static List<Customer> ShowCus() {
		DBM=new DatabaseManagement();
		return DBM.ShowCustomers();
	}
	
	public static List<Customer> CusSearch(String item) {
		DBM=new DatabaseManagement();
		return DBM.cusSearch(item);
	}
	
	public static int DeleteCus(String cin) {
		DBM=new DatabaseManagement();
		return DBM.cusDelete(cin);
	}
	
	
	public static int Addcustomer(Customer cus) {
		DBM=new DatabaseManagement();
		return DBM.addCustomerToDatabase(cus);
	}
	
	/////////////////////////////////Booking section//////////////////////////////////////////////////////////////////////////////
	public static List<Car> consultDisponibleCars(){
		DBM=new DatabaseManagement();
		return DBM.getDiponibleCars();
	}
	
	public static int bookCar(String cin,String carNum,Date rentalDate,int periode) {
		DBM=new DatabaseManagement();
		return DBM.bookCar(cin, carNum, rentalDate, periode);
	}
	
	public static List<RentingClass> consultbookedCars(){
		DBM=new DatabaseManagement();
		return DBM.getBooked();
	}
	
	public static int stopRenting(String carNum) {
		DBM=new DatabaseManagement();
		return DBM.stopRenting(carNum);			
	}
	
	public static boolean checkdispo(String carNum) {
		DBM=new DatabaseManagement();
		return DBM.checkDisponibility(carNum);
	}
	
	public static boolean checkCusRent(String cin) {
		DBM=new DatabaseManagement();
		return DBM.checkCusRenting(cin);
	}
	
	
	public static int AddUser(Admin user) {
		DBM=new DatabaseManagement();
		return DBM.addUser(user);
	}
	
	public static List<String> ConsultUsers(){
		DBM=new DatabaseManagement();
		return DBM.getAllAdmins();
	}
	
	
	public static boolean login(String username, String password)
	{
		DBM=new DatabaseManagement();
		return DBM.checkUser(username, password);	
	}
	
	public static int modifyUser(Admin user,String username) {
		DBM=new DatabaseManagement();
		return DBM.modifyUser(user, username);
	}
	

	public static int DeleteUser(String user) {
		DBM=new DatabaseManagement();
		return DBM.DeleteUser(user);
	}
	
	
	
	public static void TestCon() {
		DBM=new DatabaseManagement();
		 DBM.Testcon();
	}
	
	
	
	
	
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
