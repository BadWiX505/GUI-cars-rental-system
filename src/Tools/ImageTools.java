package Tools;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageTools {
	
	public static ImageIcon resizeImageIcon(ImageIcon originalIcon, int width, int height) {
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
	
	 public static void setImage(JLabel lab) {
	    JFileChooser filechooser = new JFileChooser();  
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image Files", "jpg", "jpeg", "png", "gif", "bmp");
        filechooser.setFileFilter(filter);
	   	int res =  filechooser.showOpenDialog(null);
	   	 if(res==0) {
	   		String  path= filechooser.getSelectedFile().getAbsolutePath();
	   	  lab.setIcon(resizeImageIcon(new ImageIcon(path),lab.getWidth(),lab.getHeight()));
	    }
	 }
	 
	 
	 public static byte[] imageIconToByteArray(ImageIcon imageIcon) {
	        if (imageIcon == null) {
	            return null;
	        }

	        BufferedImage bufferedImage = new BufferedImage(
	                imageIcon.getIconWidth(),
	                imageIcon.getIconHeight(),
	                BufferedImage.TYPE_INT_RGB
	        );

	        // Create a graphics context and paint the ImageIcon to the BufferedImage
	        Graphics g = bufferedImage.getGraphics();
	        imageIcon.paintIcon(null, g, 0, 0);
	        g.dispose();

	        // Convert BufferedImage to byte array
	        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
	            // Write the image data to the ByteArrayOutputStream in PNG format
	            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
	            return byteArrayOutputStream.toByteArray();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return null;
	    }
}
