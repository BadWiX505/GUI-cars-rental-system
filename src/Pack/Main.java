package Pack;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import objectClass.Admin;

public class Main {

	public static void main(String[] args) {
	setSysTheme();
		JFrame frame= new JFrame();
       LogPage pane =new LogPage(frame);
        frame.setIconImage(new ImageIcon("Logo.jpeg").getImage());
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setUndecorated(true);
       frame.getContentPane().add(pane);
       
       frame.pack();
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
       frame.setMaximumSize(new Dimension(1100,600));
       Admin.TestCon();    
}

	
	   public static void setSysTheme() {
		   try {
			   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

	            // Forcefully set the button look and feel to default
	            UIManager.put("ButtonUI", "javax.swing.plaf.basic.BasicButtonUI");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
	   
	   
}
