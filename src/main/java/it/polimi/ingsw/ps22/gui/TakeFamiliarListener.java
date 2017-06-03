package it.polimi.ingsw.ps22.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TakeFamiliarListener implements ActionListener {

	AllTowersPanel towers;
	
	public TakeFamiliarListener(AllTowersPanel towers) {
		this.towers = towers;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			FamiliarButton b = (FamiliarButton)e.getSource();
			System.out.println("preso familiare");
			b.setEnabled(false);
			towers.enableTowers();
		
	}

}
