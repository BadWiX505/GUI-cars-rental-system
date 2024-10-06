package objectClass;

import java.sql.Date;

import javax.swing.ImageIcon;

public class Car {
    private String brand;
    private String model;
    private int year;
    private String transmitionType;
    private Date dateOfEntry;
    private double maxSpeed;
    private double  price;
    private  String carNumber;
    private byte seatingCapacity;
    private  String fuelType;
    private ImageIcon carImage;
    private boolean diponible;
    
    
	public Car(String brand, String model, int year, String transmitionType, Date dateOfEntry, double maxSpeed,
			double price, String carNumber, byte seatingCapacity, String fuelType, ImageIcon carImage,boolean disponible) {
		super();
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.transmitionType = transmitionType;
		this.dateOfEntry = dateOfEntry;
		this.maxSpeed = maxSpeed;
		this.price = price;
		this.carNumber = carNumber;
		this.seatingCapacity = seatingCapacity;
		this.fuelType = fuelType;
		this.carImage = carImage;
		this.diponible=disponible;
	}
	
	
	
	
	 
	public boolean isDiponible() {
		return diponible;
	}

	public void setDiponible(boolean diponible) {
		this.diponible = diponible;
	}

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getTransmitionType() {
		return transmitionType;
	}
	public void setTransmitionType(String transmitionType) {
		this.transmitionType = transmitionType;
	}
	public Date getDateOfEntry() {
		return dateOfEntry;
	}
	public void setDateOfEntry(Date dateOfEntry) {
		this.dateOfEntry = dateOfEntry;
	}
	public double getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public byte getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(byte seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public ImageIcon getCarImage() {
		return carImage;
	}
	public void setCarImage(ImageIcon carImage) {
		this.carImage = carImage;
	}
    
    
    
    
}
