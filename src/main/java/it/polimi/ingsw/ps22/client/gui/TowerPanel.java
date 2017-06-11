package it.polimi.ingsw.ps22.client.gui;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TowerPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4631811818202113054L;
	
	JButton b;
	Rectangle dim;
	String path;
	

	public TowerPanel(Rectangle dim){
		JButton b = new JButton();
		
		b.setEnabled(true);
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
	
		this.b = b;
		
		
		this.setLayout(new GridLayout(1, 1));
		this.add(b);
		this.setOpaque(false);
		
		this.dim = dim;
		
		setMeasures(dim);
	    
	    b.getModel().addChangeListener(new BorderEffect(b));
	
	}

	public void takeCard(){
		b.setBorderPainted(false);
	}
	
	public void setCard(String path){
		this.path = path;
		ImageIcon cardIcon = MyImage.createScaledImageIcon(path, dim);
		b.setIcon(cardIcon);
	}
	
	private void setMeasures(Rectangle dim){
		this.setBounds(dim.getInitx(), dim.getInity(), dim.getOffsetX(), dim.getOffsetY());
	}

}
