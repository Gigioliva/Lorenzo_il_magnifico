package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

public class MyImage {
	
	
	public static JLabel getScaledImageinLabel(String path, Rectangle dim){
		ImageIcon img = createScaledImageIcon(path, dim);
		JLabel icon = new JLabel(img);
	    icon.setBounds(dim.getInitx(), dim.getInity(), dim.getOffsetX(), dim.getOffsetY());
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
	    g2.dispose();

	    return resizedImg;
	}
	
	public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = BoardPanel.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	
	public static void updatePlayersSpaces(ArrayList<Family> familiars, ActionButton b, Graphics g){
		
		ArrayList<Rectangle> recs = Rectangle.divideRectangle(new Rectangle(0,b.getWidth(),0,b.getHeight()));
		
		for(int i = 0 ; i < familiars.size(); i++){
			
			//g.setColor(familiars.get(i).getPlayer().getColor().getColor());
			Rectangle rec = recs.get(i);
			ImageIcon img =MyImage.createScaledImageIcon(FamilyPath.getFamilyPathname(familiars.get(i).getPlayer().getColor().getColor(),
					familiars.get(i).getColor()), recs.get(i));

			//g.fillOval(rec.getInitx(), rec.getInity(), rec.getOffsetX(), rec.getOffsetY());
			g.drawImage(img.getImage(), rec.getInitx(), rec.getInity(), null);
			//g.setColor(familiars.get(i).getColor().getColor());
			//g.drawOval(rec.getInitx(), rec.getInity(), rec.getOffsetX(), rec.getOffsetY());
		}
	}
	
	public static void updatePlayersSpaces(ArrayList<Player> players, JLabel b, Graphics g){
		
		ArrayList<Rectangle> recs = Rectangle.divideRectangle(new Rectangle(0,b.getWidth(),0,b.getHeight()));
		
		for(int i = 0 ; i < players.size(); i++){
			g.setColor(players.get(i).getColor().getColor());
			Rectangle rec = recs.get(i);
			g.fillRect(rec.getInitx(), rec.getInity(), rec.getOffsetX(), rec.getOffsetY());
			g.setColor(players.get(i).getColor().getColor());
			g.drawRect(rec.getInitx(), rec.getInity(), rec.getOffsetX(), rec.getOffsetY());
		}
	}
	


}
