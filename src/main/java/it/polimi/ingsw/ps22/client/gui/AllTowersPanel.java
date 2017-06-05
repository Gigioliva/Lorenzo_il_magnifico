package it.polimi.ingsw.ps22.client.gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import it.polimi.ingsw.ps22.server.model.Color;

public class AllTowersPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5512457746792602857L;
	private TowerPanel t1;
	private TowerPanel t2;
	private TowerPanel t3;
	private TowerPanel t4;
	
	public AllTowersPanel(){
		System.out.println("creo torri");
		this.setLayout(new GridLayout(1,4));
		
		t1 = new TowerPanel("Territory");
		t2 = new TowerPanel("Character");
		t3 = new TowerPanel("Building");
		t4 = new TowerPanel("Venture");
		
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		
		this.add(t1);
		this.add(t2);
		this.add(t3);
		this.add(t4);
	
		System.out.println("torri create");
	}
	
	protected void enableTowers(Color color){
		t1.enableTower(color);
		t2.enableTower(color);
		t3.enableTower(color);
		t4.enableTower(color);
	}
	
	protected void setNumServants(int value){
		t1.setNumServants(value);
		t2.setNumServants(value);
		t3.setNumServants(value);
		t4.setNumServants(value);
	}
	
	

}
