package DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Tools.RentingClass;
import exceptions.CarsExceptions;
import objectClass.Admin;
import objectClass.Car;
import objectClass.Customer;

public class DatabaseManagement {
     Connection con=DatabaseConnection.getConnection();
          
     
     public List<Car> showCarsList() {
    	 List<Car> carsList=null;
    	 try {
    		 carsList = new ArrayList<>();
			 PreparedStatement PT = con.prepareStatement("Select carNumber,yearr,transmitionType,dateOfEntry,maxSpeed,price,seatingCapacity,fuelType,carImage,b.brandname,m.modelname,disponible from cars JOIN Brand b on b.idBrand=cars.idBrand JOIN modeltable m on m.idModel=cars.idModel;");
			ResultSet res = PT.executeQuery();
			while(res.next()) {
			  Car car =new Car(res.getString("b.brandname"),res.getString("m.modelname"),res.getInt("yearr"), res.getString("transmitionType"),res.getDate("dateOfEntry"),res.getDouble("maxSpeed"),
			res.getDouble("price"),res.getString("carNumber"),res.getByte("seatingCapacity"),res.getString("fuelType"),new ImageIcon(res.getBytes("carImage")),res.getBoolean("disponible"));
			 carsList.add(car);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return carsList ;
     }
     
     
     
     
     
     public int DeleteCar(Car car) {
         try {
			PreparedStatement PT=con.prepareStatement("DELETE FROM cars where carNumber=?");
			PT.setString(1,car.getCarNumber());
			int res=PT.executeUpdate();
			return res;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
     }
     
     
       
     
     
    public List<Car> SearchCar(String carNumber,String Brand) {
   	 List<Car> carsList = null;
    	String carNumberQuery;
    	String brandQuery;
    	byte i=0;
    	//// small trick
    	   if(carNumber!=null) {
    		   carNumberQuery="carNumber = ?";
    		   i++;
    	   }
    	   else {
    		   carNumberQuery="1=1"; 
    	   }
    	   if(Brand!=null) {
    		   brandQuery="b.brandname=?";
    		   i++;
    	   }
    	   else {
    		   brandQuery="1=1";
    	   }

    	   ////
         try {
        	 carsList=new ArrayList<>();
			PreparedStatement PT = con.prepareStatement("Select carNumber,yearr,transmitionType,dateOfEntry,maxSpeed,price,seatingCapacity,fuelType,carImage,b.brandname,m.modelname,disponible from cars JOIN Brand b on b.idBrand=cars.idBrand JOIN modeltable m on m.idModel=cars.idModel "
					+ "where "+carNumberQuery+" and "+brandQuery+";");
			if(carNumber!=null) {
	    		 PT.setString(1,carNumber);
	    	   }
	    	   if(Brand!=null) {
	    		  PT.setString(i,Brand);
	    	   }
			
			ResultSet res = PT.executeQuery();
			while(res.next()) {
				  Car car =new Car(res.getString("b.brandname"),res.getString("m.modelname"),res.getInt("yearr"), res.getString("transmitionType"),res.getDate("dateOfEntry"),res.getDouble("maxSpeed"),
				res.getDouble("price"),res.getString("carNumber"),res.getByte("seatingCapacity"),res.getString("fuelType"),new ImageIcon(res.getBytes("carImage")),res.getBoolean("disponible"));
				 carsList.add(car);
				 }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    
		return carsList;	
    }
    
    
    
    public int addDataToDatabase(Car car) {
        int r = 0;
        try {
        	if(!checkBrandname(car.getBrand()))
        	setBrand(car);
        	if(!checkmodelname(car.getModel()))
        	setModel(car);
            String insertCarSql = "INSERT INTO cars VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement PT = con.prepareStatement(insertCarSql);
            PT.setString(1, car.getCarNumber());
            PT.setInt(2, car.getYear());
            PT.setString(3, car.getTransmitionType());
            PT.setDate(4, car.getDateOfEntry());
            PT.setDouble(5, car.getMaxSpeed());
            PT.setDouble(6, car.getPrice());
            PT.setInt(7, car.getSeatingCapacity());
            PT.setString(8, car.getFuelType());
            PT.setBytes(9, Tools.ImageTools.imageIconToByteArray(car.getCarImage()));
            PT.setInt(10, getIdBrand(car.getBrand()));
            PT.setInt(11, getIdModel(car.getModel()));
            PT.setBoolean(12,car.isDiponible());

            r = PT.executeUpdate();
              PT.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            CarsExceptions.showErrorMessage("there was an Error you might need to check carNumber");
        } 
        return r;
    }
    
    
    
    public void setBrand(Car car) {
    	  String insertBrandSql = "INSERT INTO brand (brandname) VALUES (?) ON DUPLICATE KEY UPDATE brandname = VALUES(brandname);";
          try {
			PreparedStatement PT = con.prepareStatement(insertBrandSql);
			  PT.setString(1, car.getBrand());
	          PT.executeUpdate();
	          PT.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    
    public void setModel(Car car) {
    	
    	  String insertModelSql = "INSERT INTO modeltable (modelname) VALUES (?) ON DUPLICATE KEY UPDATE modelname = VALUES(modelname);";
          try {
			PreparedStatement PT = con.prepareStatement(insertModelSql); 
			PT.setString(1, car.getModel());
          PT.executeUpdate();
          PT.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
    }
    
   public int getIdBrand(String brand) {
	   int id=0;
	   try {
		PreparedStatement PT = con.prepareStatement("Select idBrand from brand where brandname= ?");
		   PT.setString(1,brand);
         ResultSet res= PT.executeQuery();
          while(res.next()) {
          id=res.getInt("idBrand");
          }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return id;
   }
    
   
   public int getIdModel(String model) {
	    int id = 0;
	    try {
	        PreparedStatement PT = con.prepareStatement("SELECT idModel FROM modeltable WHERE modelname = ?");
	        PT.setString(1, model);

	        ResultSet res = PT.executeQuery();
	        if (res.next()) {
	            id = res.getInt("idModel");
	        }
	        PT.close();
	        res.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return id;
	}

    
   public boolean checkBrandname(String Brand) {
	   try {
		PreparedStatement PT = con.prepareStatement("Select brandname from brand where brandname=?;");
		PT.setString(1,Brand);
		ResultSet res = PT.executeQuery();
		while(res.next()) {
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	return false;
	   
   }
   
   public boolean checkmodelname(String Model) {
	   try {
		PreparedStatement PT = con.prepareStatement("Select modelname from modeltable where modelname=?;");
		PT.setString(1,Model);
		ResultSet res = PT.executeQuery();
		while(res.next()) {
			return true;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	return false;
	   
   }
    
   
   public int UpdateCar(Car car,String carNum) {
	  try {
		  if(!checkBrandname(car.getBrand()))
	        	setBrand(car);
	        	if(!checkmodelname(car.getModel()))
	        	setModel(car);
		PreparedStatement PT = con.prepareStatement("Update Cars set carNumber=?,yearr=?,transmitionType=?,dateOfentry=?,maxSpeed=?,price=?,SeatingCapacity=?,FuelType=?,carImage=?,idBrand=?,idModel=? where carNumber=?;");
		 PT.setString(1, car.getCarNumber());
         PT.setInt(2, car.getYear());
         PT.setString(3, car.getTransmitionType());
         PT.setDate(4, car.getDateOfEntry());
         PT.setDouble(5, car.getMaxSpeed());
         PT.setDouble(6, car.getPrice());
         PT.setInt(7, car.getSeatingCapacity());
         PT.setString(8, car.getFuelType());
         PT.setBytes(9, Tools.ImageTools.imageIconToByteArray(car.getCarImage()));
         PT.setInt(10, getIdBrand(car.getBrand()));
         PT.setInt(11, getIdModel(car.getModel()));	
         PT.setString(12,carNum);
         return PT.executeUpdate();
         } catch (SQLException e) {
		e.printStackTrace();
        CarsExceptions.showErrorMessage("there was an Error you might need to check carNumber");
	}
	   
	return 0;
	   
   }
   
   ////////////////////////////////////////////////////customer///////////////////////////////////////////////////////////////////
   
   public int UpdateCus(Customer cus,String cin)
   {
	   int result=0;
	   try {
		PreparedStatement PT = con.prepareStatement("UPDATE `customers` SET cin=? ,`first_name` = ?, `last_name` = ?, `age` = ?, `adress` = ?, `tel` = ?, `Licence_number` = ?, `email` = ?, `date_entry` = ?, `image` = ? WHERE `cin` = ?;");
	    PT.setString(1, cus.getCin());
        PT.setString(2, cus.getFirstname());
        PT.setString(3, cus.getLastname());
        PT.setInt(4, cus.getAge());
        PT.setString(5, cus.getAddress());
        PT.setString(6, cus.getTel());
        PT.setString(7, cus.getPermit());
        PT.setString(8, cus.getEmail());
        PT.setDate(9, cus.getDateEntry());
        PT.setBytes(10, Tools.ImageTools.imageIconToByteArray(cus.getCusImage()));
        PT.setString(11,cin);
        return result= PT.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return result;
   }
   
   public int addCustomerToDatabase(Customer customer) {
	     
       try  {
           // Vérifier si le CIN existe déjà
           if (isCinExists(customer.getCin())) {
               JOptionPane.showMessageDialog(null, "Error : Already existing CIN. Please use a different CIN.", "Error", JOptionPane.ERROR_MESSAGE);
               return 0; // Arrêter l'opération d'insertion
           }

           String sql = "INSERT INTO customers  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
              PreparedStatement statement = con.prepareStatement(sql) ;
               statement.setString(1, customer.getCin());
               statement.setString(2, customer.getFirstname());
               statement.setString(3, customer.getLastname());
               statement.setByte(4, customer.getAge());
               statement.setString(5, customer.getAddress());
               statement.setString(6, customer.getTel());
               statement.setString(7, customer.getPermit());
               statement.setString(8, customer.getEmail());
               statement.setDate(9, customer.getDateEntry());
               statement.setBytes(10, Tools.ImageTools.imageIconToByteArray(customer.getCusImage()));
               
               int rowsInserted = statement.executeUpdate();
               if (rowsInserted > 0) {
                   JOptionPane.showMessageDialog(null, "The customer was added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                   return rowsInserted;
               }
           
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return 0;
   }

   public boolean isCinExists( String cin) throws SQLException {
       String query = "SELECT COUNT(*) FROM customers WHERE cin = ?";
       try (PreparedStatement statement = con.prepareStatement(query)) {
           statement.setString(1, cin);
           try (ResultSet resultSet = statement.executeQuery()) {
               resultSet.next();
               return resultSet.getInt(1) > 0;
           }
       }
   }
   
   
   
   
   public List<Customer> ShowCustomers(){
	   List<Customer> cusList;
	   try {
		   cusList = new ArrayList<>();
		PreparedStatement PT = con.prepareStatement("Select * from customers;");
		ResultSet res = PT.executeQuery();
		while(res.next()) {
		 Customer cus = new Customer(res.getString("cin"),res.getString("first_name"),res.getString("last_name"),res.getString("email"),res.getString("adress"),new ImageIcon(res.getBytes("image")),
					res.getString("tel"),res.getByte("age"),res.getString("Licence_number"),res.getDate("date_entry"));
		 cusList.add(cus);
		}
		return cusList;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return null; 
   }
   
   
   public List<Customer> cusSearch(String item){
	   List<Customer> cusList;
	   cusList = new ArrayList<>();
		PreparedStatement PT;
		try {
			PT = con.prepareStatement("Select * from customers WHERE cin =? OR first_name =? OR last_name=?;");
			PT.setString(1, item);
			PT.setString(2, item);
			PT.setString(3, item);
			ResultSet res = PT.executeQuery();
			while(res.next()) {
				 Customer cus = new Customer(res.getString("cin"),res.getString("first_name"),res.getString("last_name"),res.getString("email"),res.getString("adress"),new ImageIcon(res.getBytes("image")),
							res.getString("tel"),res.getByte("age"),res.getString("Licence_number"),res.getDate("date_entry"));
				 cusList.add(cus);
				}
				return cusList;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return null; 
		   }
   
       
   
   public int cusDelete(String cin) {
	   int d=0;
	   PreparedStatement PT;
	   try {
		PT = con.prepareStatement("DELETE FROM customers WHERE cin =?;");
		PT.setString(1, cin);
		d=PT.executeUpdate();
		return d;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   return d;
   }
   
   //////////////////////////////////////////////////////////Booking section/////////////////////////////////////////////////////////
    
    public List<Car> getDiponibleCars(){
    	List<Car> carsList;
    	try {
    		carsList=new ArrayList<>();
			PreparedStatement PT = con.prepareStatement("Select carNumber,yearr,transmitionType,dateOfEntry,maxSpeed,price,seatingCapacity,fuelType,carImage,b.brandname,m.modelname,disponible from cars JOIN Brand b on b.idBrand=cars.idBrand JOIN modeltable m on m.idModel=cars.idModel where disponible=1");
			ResultSet res = PT.executeQuery();
			while(res.next()) {
				  Car car =new Car(res.getString("b.brandname"),res.getString("m.modelname"),res.getInt("yearr"), res.getString("transmitionType"),res.getDate("dateOfEntry"),res.getDouble("maxSpeed"),
				res.getDouble("price"),res.getString("carNumber"),res.getByte("seatingCapacity"),res.getString("fuelType"),new ImageIcon(res.getBytes("carImage")),res.getBoolean("disponible"));
				 carsList.add(car);
				 }
			PT.close();
			res.close();
			return carsList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    
    public int bookCar(String cin,String carNum,Date rentalDate,int periode) {
    	try {
    		int res;
			PreparedStatement PT = con.prepareStatement("insert into rentalTable values(?,?,?,?)");
			PT.setString(1,cin);
			PT.setString(2,carNum);
			PT.setDate(3,rentalDate);
			PT.setInt(4,periode);
			res= PT.executeUpdate();
			if(res>0) {
				changeAvailablity(carNum,false);
			}
			PT.close();
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return 0;
    }
    
    
    public void changeAvailablity(String carNum,boolean dis) {
		try {
			PreparedStatement PT = con.prepareStatement("Update cars set disponible=? where carNumber=?");
			PT.setBoolean(1,dis);
			PT.setString(2,carNum);
			PT.executeUpdate();	
			PT.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    public List<RentingClass> getBooked(){
    	try {
        	List<RentingClass> RC;
    		RC=new ArrayList<>();
			PreparedStatement PT = con.prepareStatement("SELECT rentaltable.carNumber, b.brandname, rentaltable.rentaldate, rentaltable.rentalperiode, c.first_name, c.last_name, rentaltable.cin FROM rentaltable JOIN cars cc ON cc.carNumber = rentaltable.carNumber JOIN Brand b ON b.idBrand = cc.idBrand JOIN customers c ON c.cin = rentaltable.cin;"
					+ "");
			ResultSet res = PT.executeQuery();
			while(res.next()) {
			   RC.add(new  RentingClass(  res.getString(2),res.getString(1),res.getString(5),res.getString(6),res.getString(7),res.getDate(3),
			Tools.OtherTools.getDateFromPeriode(res.getDate(3),res.getInt(4))));
			}
			PT.close();
			return RC;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    
    public int stopRenting(String carNum) {
    	try {
    		int res;
			PreparedStatement PT = con.prepareStatement("Delete from RentalTable where carNumber=?");
			PT.setString(1,carNum);
			res=PT.executeUpdate();
			if(res>0) {
			changeAvailablity(carNum,true);
			}
			PT.close();
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return 0;
    }
    
    public boolean checkDisponibility(String carNum) {
		try {
			PreparedStatement PT = con.prepareStatement("SELECT disponible from cars where carNumber=?");
			PT.setString(1, carNum);
			ResultSet res = PT.executeQuery();
			while(res.next()) {
				return res.getBoolean(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
    }
    
    public  boolean checkCusRenting(String cin) {
		try {
			PreparedStatement PT = con.prepareStatement("SELECT cin from rentalTable where cin=?");
			PT.setString(1,cin);
			ResultSet res = PT.executeQuery();
			if(res.next()) return false;
			else
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return true;
    }
    
    
    
    
    //////////////////////////////////////////////////////////////////othertools//////////////////////////////////////////////////////////
    
    
    
    public int getCarsNum() {
    	
		try {
			PreparedStatement PT = con.prepareStatement("SELECT COUNT(carNumber) from cars");
			ResultSet res = PT.executeQuery();
			if(res.next())
	    		return res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return 0;
    }
    
    
    public int getcusNumber() {
    	try {
			PreparedStatement PT = con.prepareStatement("SELECT COUNT(cin) from customers");
			ResultSet res = PT.executeQuery();
			if(res.next())
	    		return res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return 0;
    }
    
    
    public int getBookedcars() {
    	try {
			PreparedStatement PT = con.prepareStatement("SELECT COUNT(carNumber) from cars where disponible=0");
			ResultSet res = PT.executeQuery();
			if(res.next())
	    		return res.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return 0;
    }
    
    
    public int getActivecus() {
    	try {
    		PreparedStatement PT = con.prepareStatement("SELECT COUNT(DISTINCT cin) FROM RentalTable;");
    		ResultSet res = PT.executeQuery();
    		if(res.next())
    		return res.getInt(1);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
    }
    
    public double getTotalProfits() {
    	try {
    		PreparedStatement PT = con.prepareStatement("SELECT SUM(rentalTable.rentalPeriode*c.Price) from rentalTable JOIN cars c ON c.carNumber=rentaltable.carNumber ");
    		ResultSet res = PT.executeQuery();
    		if(res.next())
    		return res.getDouble(1);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return 0;
    }
    
    
    
    ///////////////////////User Panel /////////////////////////////////////////////////////////////////////////
    
    
    
    
    
    public int addUser(Admin user) {
        try  {
     	   if(user.getUserName().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error :  Please use a USERNAME.", "Error", JOptionPane.ERROR_MESSAGE);
                  return 0;
     	   }
     	   if(user.getPassWord().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error :  Please Enter a user Password.", "Error", JOptionPane.ERROR_MESSAGE);
                   return 0;
     	   }
     	   else if (isUserExists(user.getUserName())) {
                JOptionPane.showMessageDialog(null, "Error : Already existing USERNAME. Please use a different USERNAME.", "Error", JOptionPane.ERROR_MESSAGE);
                return 0; 
            }
            String sql = "INSERT INTO user  VALUES (?, ?)";
               PreparedStatement statement = con.prepareStatement(sql) ;
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getPassWord());
                               
                int rowsInserted = statement.executeUpdate();
                statement.close();
                return rowsInserted;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }   
    
    
      public boolean isUserExists(String username) {
    	  String query = "SELECT COUNT(*) FROM user WHERE username = ?";
    	  try {
			PreparedStatement statement = con.prepareStatement(query);
		    statement.setString(1, username);
		    ResultSet resultSet = statement.executeQuery();
		    resultSet.next();
            return resultSet.getInt(1) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    	  return false;
      }
    
      
      
      public List<String> getAllAdmins(){
    	  List<String> aList;
    	  try {
    		  aList=new ArrayList<>();
      		PreparedStatement PT = con.prepareStatement("SELECT username from user");
      		ResultSet res = PT.executeQuery();
      		while(res.next()) {
      			aList.add(res.getString(1));
      		}
      		PT.close();
      		res.close();
      		return aList;
      	} catch (SQLException e) {
      		e.printStackTrace();
      	}
      	return null;
      }
      
      
      
      public boolean checkUser(String username,String password) {
    	  try {
  			PreparedStatement PT = con.prepareStatement("SELECT COUNT(username) from user where username=? and password=?");
  			PT.setString(1,username);
  			PT.setString(2,password);
  			ResultSet res = PT.executeQuery();
  			res.next();
  			if(res.getInt(1)>0)
  	    		return true;
  			else
  				return false;
  		} catch (SQLException e) {
  			e.printStackTrace();
  			exceptions.CarsExceptions.showErrorMessage("there is a problem in database connection!");
  		}
          return false;
      }
      
      
      public int modifyUser(Admin user,String username){
    	  int i=2;
    	  try {
      		int res;
      		String query="";
      		if(!user.getPassWord().isEmpty()) {
      			query=",password=?";
      			i++;
      		}
  			PreparedStatement PT = con.prepareStatement("Update user set username=?"+query+" where username=?");
  			PT.setString(1,user.getUserName());
  			if(!user.getPassWord().isEmpty()) {
  				PT.setString(2,user.getPassWord());
  			}
  			PT.setString(i,username);
  			res=PT.executeUpdate();
  			PT.close();
  			return res;
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
      	return 0;
      }
      
      
      public int DeleteUser(String username) {
    	  try {
  			PreparedStatement PT=con.prepareStatement("DELETE FROM user where username=?");
  			PT.setString(1,username);
  			int res=PT.executeUpdate();
  			return res;
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
  		return 0;
      }
      
      
      
      public void Testcon() {
    	  if(con==null) {
    		  exceptions.CarsExceptions.showErrorMessage("there is a problem in database connection! reload the program or check the server");
    		  System.exit(0);
    	  }
      }
}
