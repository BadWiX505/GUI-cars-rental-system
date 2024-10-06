package Tools;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTools {
	
	
	
	
	
	 public static java.sql.Date ToDate(String dateString) {
	        // Define the format of the input string
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	        try {
	            // Parse the string to obtain a java.util.Date object
	            java.util.Date utilDate = dateFormat.parse(dateString);

	            // Convert java.util.Date to java.sql.Date
	            return new java.sql.Date(utilDate.getTime());
	        } catch (ParseException e) {
	            e.printStackTrace();
	            return null; // Handle the exception as needed
	        }
	    }
	 
	 
	 public static String formatDate(Date sqlDate) {
	        // Specify the desired date format
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	        // Format the java.sql.Date as a string
	        return sdf.format(sqlDate);
	    }
}
