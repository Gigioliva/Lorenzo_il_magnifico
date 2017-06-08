package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.ps22.server.move.HarvestMove;

public class HarvestListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ActionButton pressedButton = (ActionButton)e.getSource();
		HarvestMove move = new HarvestMove(pressedButton.getUsername(), pressedButton.getColor(), pressedButton.getSpace(), 
				pressedButton.getNumServants());
		System.out.println("creo harvest Move, color fam: " + pressedButton.getColor() +"  spazio: " +   pressedButton.getSpace() + 
				" servitori aggiunti " + pressedButton.getNumServants());
		
	}

}
