package objectClass;

import java.sql.Date;

import javax.swing.ImageIcon;

public class Customer {
    private String cin;
    private String firstname,lastname,email,address;
    private ImageIcon cusImage;
    private String tel;
    private byte age;
    private String permit;
    private Date dateEntry;
	public Customer(String cin, String firstname, String lastname, String email, String address, ImageIcon cusImage,
			String tel, byte age, String permit, Date dateEntry) {
		super();
		this.cin = cin;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.address = address;
		this.cusImage = cusImage;
		this.tel = tel;
		this.age = age;
		this.permit = permit;
		this.dateEntry = dateEntry;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ImageIcon getCusImage() {
		return cusImage;
	}
	public void setCusImage(ImageIcon cusImage) {
		this.cusImage = cusImage;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public byte getAge() {
		return age;
	}
	public void setAge(byte age) {
		this.age = age;
	}
	public String getPermit() {
		return permit;
	}
	public void setPermit(String permit) {
		this.permit = permit;
	}
	public Date getDateEntry() {
		return dateEntry;
	}
	public void setDateEntry(Date dateEntry) {
		this.dateEntry = dateEntry;
	}
    
    
}
