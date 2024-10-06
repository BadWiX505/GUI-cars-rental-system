package Tools;

import java.sql.Date;

public class RentingClass {
    private String brand;
    private  String carNumber;
    private String firstname,lastname;
    private String cin;
    private Date rentalDate,returnDate;
    
    
    
    
    public RentingClass(String brand, String carNumber, String firstname, String lastname, String cin, Date rentalDate,
			Date returnDate) {
		super();
		this.brand = brand;
		this.carNumber = carNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.cin = cin;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
	}
    
    
    
    
    
    
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String tel) {
		this.cin = tel;
	}
	public Date getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
    
}
