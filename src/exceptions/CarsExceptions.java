package exceptions;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

import DB.DatabaseManagement;
import Pack.BookCar;
import Pack.Costumerspanel;
import Pack.carpanel;
import Pack.modifiedcar;
import objectClass.Admin;

public class CarsExceptions extends Exception {

    public CarsExceptions(String message) {
        super(message);
    }
    
    
    
    
    
    public static void principalexception(carpanel cp) throws CarsExceptions {
    
    		emptyexception(cp.jTextField1);
    		emptyexception(cp.jTextField2);
    		emptyexception(cp.jTextField3);
    		emptyexception(cp.jTextField4);
    		emptyexception(cp.jTextField5);
    		emptyexception(cp.jTextField6);
    		emptyexception(cp.jTextField7);
    		CheckSpinner(cp.jSpinner1);
    		CheckImage(cp.jLabel10);
    		checkAlldigit(cp.jTextField5);
    		checkAlldigit(cp.jTextField6);
    		checkAlldigit(cp.jTextField3);
            isSqlDateFormat(cp.jTextField7.getText());    		
    	}
    
    public static void principalexceptionCustomers(Costumerspanel cp) throws CarsExceptions {
        
  		emptyexception(cp.jTextField1);
  		emptyexception(cp.jTextField2);
  		emptyexception(cp.jTextField3);
  		emptyexception(cp.jTextField4);
  		emptyexception(cp.jTextField5);
  		emptyexception(cp.jTextField7);
  		emptyexception(cp.jTextField8);
  		emptyexception(cp.jTextField9);
  		emptyexception(cp.jTextField10);
  		CheckImage(cp.jLabel1);
  		checkAlldigit(cp.jTextField2);
  		checkAlldigit(cp.jTextField7);
  		isSqlDateFormat(cp.jTextField9.getText());  
  		 		
  	}

    public static void principalExceptionsBook(BookCar BK) throws CarsExceptions {
    	emptyexception(BK.jTextField1);
    	CheckImage(BK.jLabel5);
    	CheckImage(BK.jLabel6);
    	isSqlDateFormat(BK.jTextField1.getText());
    }
    
    public static void principalexception(modifiedcar cp) throws CarsExceptions {
        
		emptyexception(cp.jTextField1);
		emptyexception(cp.jTextField2);
		emptyexception(cp.jTextField3);
		emptyexception(cp.jTextField4);
		emptyexception(cp.jTextField5);
		emptyexception(cp.jTextField6);
		emptyexception(cp.jTextField7);
		CheckSpinner(cp.jSpinner1);
		CheckImage(cp.jLabel10);
		checkAlldigit(cp.jTextField5);
		checkAlldigit(cp.jTextField6);
		checkAlldigit(cp.jTextField3);
        isSqlDateFormat(cp.jTextField7.getText());    		
	}
    	
    public static void checkdispo(String carNumber) throws CarsExceptions {
    	if(!Admin.checkdispo(carNumber)) throw new CarsExceptions("Impossible , This car is rented!");
    }
    
    public static void checkcusRent(String cin) throws CarsExceptions {
    	if(!Admin.checkCusRent(cin)) throw new CarsExceptions("Impossible , This customer is renting cars!");
    }

    
    public static void showErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Erreur", JOptionPane.ERROR_MESSAGE);
    }

       
  public static void   emptyexception(JTextField textfield) throws CarsExceptions{
    	  String text = textfield.getText().trim();
    
    	 if (text.isEmpty()) {
             throw new CarsExceptions("This field can not be empty ");
    	
             }
}
  
  public static void CheckSpinner(JSpinner sp) throws CarsExceptions {
	    Object spinnerValue = sp.getValue();

	    if (spinnerValue instanceof Number) {
	        int seatingCapacity = ((Number) spinnerValue).intValue();

	        if (seatingCapacity <= 0) {
	            throw new CarsExceptions("Seating capacity must be greater than zero");
	        }
	    } else {
	        throw new CarsExceptions("Invalid value type for seating capacity");
	    }
	}

    
    public static void CheckImage(JLabel imagelab) throws CarsExceptions{
       if(imagelab.getIcon()==null) {
           throw new CarsExceptions("An image is necessary! ");
       }
    }
  
  
     public static void checkAlldigit(JTextField tf) throws CarsExceptions {
    	 char tabchar[] = tf.getText().toCharArray();
    	 for(char i:tabchar) {
    		 if(!Character.isDigit(i) && i!='.') {
    			 throw new CarsExceptions("Respect fields requirement !");
    		 }
    			 
     }
     }
    	 
    	 
     public static void isSqlDateFormat(String dateString) throws CarsExceptions {
         // Define the expected SQL Date format
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         dateFormat.setLenient(false);

         try {
             // Attempt to parse the string to a Date
             Date date = new Date(dateFormat.parse(dateString).getTime());
             
             // Check if the parsed date matches the original string
             if(!dateString.equals(date.toString()))throw new CarsExceptions("Enter a valid date");
         } catch (ParseException e) {
             // Parsing failed, the string is not in SQL Date format
        	 throw new CarsExceptions("Enter a valid date");         }
     }
  
  
  
   /* public static void validtextfield(JTextField textField) {
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    validateTextField(textField);
                    validateCharactersOnly(textField);
                } catch (CarsExceptions ex) {
                    showErrorMessage("Erreur : " + ex.getMessage());
                    textField.requestFocusInWindow();
                }
            }
        });
    }

    public static void validateTextField(JTextField textField) throws CarsExceptions {
        String text = textField.getText().trim();
        if (text.isEmpty()) {
            throw new CarsExceptions("This field can not be empty ");
        } else if (text.length() > 20) {
            throw new CarsExceptions("invalid name ");
        }
    }

   public static void validateCharactersOnly(JTextField textField) throws CarsExceptions {
        String text = textField.getText().trim();
        if (!text.matches("^[a-zA-Z]+$")) {
            throw new CarsExceptions("invalid name");
        }
    }

  
    
    
    
    
    
    //this about the date
    
   
    */

     }
     
