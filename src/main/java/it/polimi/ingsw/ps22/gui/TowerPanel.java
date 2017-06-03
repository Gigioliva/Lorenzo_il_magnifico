package it.polimi.ingsw.ps22.gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class TowerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2290195974998616160L;
	
	String name;
	ActionButton b1;
	ActionButton b2;
	ActionButton b3;
	ActionButton b4;
	
	private void setTitle(String name){
		TitledBorder border = new TitledBorder(name);
	    border.setTitleJustification(TitledBorder.CENTER);
	    border.setTitlePosition(TitledBorder.TOP);
		this.setBorder(border);
	}
	
	public TowerPanel(String name){
		this.setLayout(new GridLayout(4,1));
		setTitle(name);
		
		this.name = name;
		
		this.setOpaque(false);
		
		b1 = new ActionButton("");
		b2 = new ActionButton("");
		b3 = new ActionButton("");
		b4 = new ActionButton("");
		
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		
		this.setBorder(BorderFactory.createEmptyBorder());
		
	}
	
	protected void enableTower(){
		b1.setEnabled(true);
		b2.setEnabled(true);
		b3.setEnabled(true);
		b4.setEnabled(true);
		b1.setBorderPainted(true);
		b2.setBorderPainted(false);
		b3.setBorderPainted(false);
		b4.setBorderPainted(false);
		
	}

	
}
