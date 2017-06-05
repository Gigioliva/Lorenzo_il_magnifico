package it.polimi.ingsw.ps22.client.gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import it.polimi.ingsw.ps22.server.model.Color;

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
		this.name = name;
	}
	
	public TowerPanel(String name){
		this.setLayout(new GridLayout(4,1));
		setTitle(name);
		
		this.name = name;
		
		this.setOpaque(false);
		
		b1 = new ActionButton(name,1);
		b2 = new ActionButton(name,2);
		b3 = new ActionButton(name,3);
		b4 = new ActionButton(name,4);
		
		this.add(b4);
		this.add(b3);
		this.add(b2);
		this.add(b1);
		
		this.setBorder(BorderFactory.createEmptyBorder());
		
	}
	
	protected void enableTower(Color color){
		b1.setEnabled(true);
		b1.setColor(color);
		
		b2.setEnabled(true);
		b2.setColor(color);
		
		b3.setEnabled(true);
		b3.setColor(color);

		b4.setEnabled(true);
		b4.setColor(color);
		
		b1.setBorderPainted(true);
		b2.setBorderPainted(true);
		b3.setBorderPainted(true);
		b4.setBorderPainted(true);
		
	}
	
	@Override
	public String getName() {

		return this.name;
	}
	
	protected void setNumServants(int value){
		b1.setNumServants(value);
		b2.setNumServants(value);
		b3.setNumServants(value);
		b4.setNumServants(value);
	}

	
}
