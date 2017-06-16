package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class TakeFamiliarListener implements ActionListener {

	ArrayList<ActionButton> zones;
	
	public TakeFamiliarListener(ArrayList<ActionButton> zones) {
		this.zones = zones;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			FamiliarButton b = (FamiliarButton)e.getSource();
			//b.setEnabled(false);
			for(ActionButton zone: zones){
				zone.enableZone(b.getColor());
			}
		
		
	}

}