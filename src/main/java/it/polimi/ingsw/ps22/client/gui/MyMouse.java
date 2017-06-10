package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyMouse implements MouseListener {
	
	private JLabel label;
	private String path;

	public MyMouse(JLabel la, String path){
		this.label = la;
		this.path = path;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		ImageIcon temp = MyImage.createScaledImageIcon(path, new Rectangle(0, label.getWidth(),0, label.getHeight()));
		this.label.setIcon(temp);
		label.setVisible(true);
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		label.setVisible(false);
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
