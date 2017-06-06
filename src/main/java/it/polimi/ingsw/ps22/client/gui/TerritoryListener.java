package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.ps22.server.move.TowerTerritoryMove;

public class TerritoryListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		ActionButton pressedButton = (ActionButton)e.getSource();
		TowerTerritoryMove move = new TowerTerritoryMove(pressedButton.getUsername(), pressedButton.getColor(), pressedButton.getFloor(), 
				pressedButton.getNumServants());
		System.out.println("creo territory Move, color fam: " + pressedButton.getColor() +"  piano torre: " +   pressedButton.getFloor() + 
				" servitori aggiunti " + pressedButton.getNumServants());
	}
}
