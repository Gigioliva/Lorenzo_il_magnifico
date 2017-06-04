package it.polimi.ingsw.ps22.client.gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class AllTowersPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5512457746792602857L;
	TowerPanel t1;
	TowerPanel t2;
	TowerPanel t3;
	TowerPanel t4;
	
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
	
	protected void enableTowers(){
		t1.enableTower();
		t2.enableTower();
		t3.enableTower();
		t4.enableTower();
	}
}
