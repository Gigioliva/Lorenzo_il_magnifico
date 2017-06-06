package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TakeFamiliarListener implements ActionListener {

	ArrayList<TowerPanel> towers;
	
	public TakeFamiliarListener(ArrayList<TowerPanel> towers) {
		this.towers = towers;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			FamiliarButton b = (FamiliarButton)e.getSource();
			b.setEnabled(false);
			for(TowerPanel tower: towers){
				tower.enableTower(b.getColor());
			}
		
	}

}