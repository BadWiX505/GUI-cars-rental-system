package Tools;

import DB.DatabaseManagement;

public class AstaticticInfosTools {
     
public static  int getCarsNum() {
	DatabaseManagement DB = new DatabaseManagement();
	return DB.getCarsNum();
}

public static  int getCusNum() {
	DatabaseManagement DB = new DatabaseManagement();
	return DB.getcusNumber();
}

public static  int getBookedCarsNum() {
	DatabaseManagement DB = new DatabaseManagement();
	return DB.getBookedcars();
}
	
public static  int getActiveCus() {
	DatabaseManagement DB = new DatabaseManagement();
	return DB.getActivecus();
}

public static double getTotalProfits() {
	DatabaseManagement DB = new DatabaseManagement();
	return DB.getTotalProfits();
}


}
