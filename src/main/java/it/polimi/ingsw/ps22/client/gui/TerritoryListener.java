package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.move.TowerTerritoryMove;

public class TerritoryListener implements ActionListener {
	
	ViewClient view;
	
	public  TerritoryListener(ViewClient view) {
		this.view = view;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ActionButton pressedButton = (ActionButton)e.getSource();
		TowerTerritoryMove move = new TowerTerritoryMove(pressedButton.getUsername(), pressedButton.getColor(), pressedButton.getSpace(), 
				pressedButton.getNumServants());
		view.send(move);
		System.out.println("creo territory Move, color fam: " + pressedButton.getColor() +"  piano torre: " +   pressedButton.getSpace() + 
				" servitori aggiunti " + pressedButton.getNumServants());
	}
}
