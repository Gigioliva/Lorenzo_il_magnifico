package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.ps22.server.move.ProductionMove;

public class ProductionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		ActionButton pressedButton = (ActionButton)e.getSource();
		ProductionMove move = new ProductionMove(pressedButton.getUsername(), pressedButton.getColor(), pressedButton.getSpace(), 
				pressedButton.getNumServants());
		System.out.println("creo Production Move, color fam: " + pressedButton.getColor() +"  spazio: " +   pressedButton.getSpace() + 
				" servitori aggiunti " + pressedButton.getNumServants());
		
	}

}