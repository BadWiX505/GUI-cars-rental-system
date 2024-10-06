package Tools;

import javax.swing.JComboBox;

public class JcomboTools {
    
	
	
	public static boolean distainctComboBox(JComboBox<String> box , String item) {
		   for(int i=0;i<box.getItemCount();i++) {
			    if(box.getItemAt(i).equals(item)) {
			    	return false;
			    }
		   }
		   return true;
	}
}
