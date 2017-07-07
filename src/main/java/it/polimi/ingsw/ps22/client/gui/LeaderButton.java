package it.polimi.ingsw.ps22.client.gui;

import java.util.ArrayList;

import javax.swing.JButton;

public class LeaderButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2049747823690383400L;
	private transient Rectangle dim;
	private String cardName;
	private transient PersonalBoardAdaptive layoutPersonal = PersonalBoardAdaptive.instance();
	
	public LeaderButton(int slot, PersonalBoardPanel p, String username, double heightScreen){
		
		Rectangle dimR = layoutPersonal.getLeaderSlot(p.resizeFactor);
		dimR = Rectangle.fillImageRatio(dimR, (float)2);
		ArrayList<Rectangle> recs = Rectangle.divideRectangle(dimR);
		Rectangle dim = recs.get(slot);
		this.dim = dim;
		this.setBounds(dim.getInitx() + (int)(heightScreen * 0.71), dim.getInity(), dim.getOffsetX(), dim.getOffsetY());
		
		this.setEnabled(false);
		this.setVisible(false);
	
	}
	
	public Rectangle getDim(){
		return this.dim;
	}
	
	public void setCardName(String name){
		this.cardName = name;
	}
	
	public String getCardName(){
		return this.cardName;
	}
}
