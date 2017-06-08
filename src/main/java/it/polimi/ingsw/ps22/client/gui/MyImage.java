package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyImage {

	
	public static JLabel getScaledImageinLabel(String path, Rectangle dim){
		ImageIcon img = createScaledImageIcon(path, dim);
		JLabel icon = new JLabel(img);
	    icon.setBounds(dim.getInitx(), dim.getInity(), dim.getOffsetX(), dim.getOffsetY());
	    System.out.println("dice bounds" + dim.getInitx() + " " + dim.getInity());
	    return icon;
	}
	
	public static ImageIcon createScaledImageIcon(String path, Rectangle dim){
		ImageIcon icon = createImageIcon(path);
		Image image = icon.getImage();
		Image img = getScaledImage(image, dim);
		return new ImageIcon(img);
	}
	
	public static Image getScaledImage(Image srcImg, Rectangle dim){
	    BufferedImage resizedImg = new BufferedImage(dim.getOffsetX(), dim.getOffsetY(), BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, dim.getOffsetX(), dim.getOffsetY(), null);
	    System.out.println("draw Image " + dim.getInitx() + " " + dim.getInity() + " " + dim.getOffsetX() + " " + dim.getOffsetY());
	    g2.dispose();

	    return resizedImg;
	}
	
	public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = LayeredPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
