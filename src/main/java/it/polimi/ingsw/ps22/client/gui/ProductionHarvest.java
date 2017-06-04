package it.polimi.ingsw.ps22.client.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class ProductionHarvest extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7972754531170567693L;
	
	ActionButton p1;
	ActionButton p2;
	ActionButton h1;
	ActionButton h2;
	
	public ProductionHarvest(){
		this.setLayout(new GridLayout(2,2));
		p1 = new ActionButton("p1");
		p2 = new ActionButton("p2");
		h1 = new ActionButton("h1");
		h2 = new ActionButton("h2");
		
		this.add(p1);
		this.add(p2);
		this.add(h1);
		this.add(h2);
		
		
	}
}
