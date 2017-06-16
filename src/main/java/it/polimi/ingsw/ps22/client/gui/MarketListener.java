package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.move.MarketMove;

public class MarketListener implements ActionListener {
	
	ViewClient view;
	
	public  MarketListener(ViewClient view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ActionButton pressedButton = (ActionButton)e.getSource();
		MarketMove move = new MarketMove(pressedButton.getUsername(), pressedButton.getColor(), pressedButton.getSpace() + 1, 
				pressedButton.getNumServants());
		view.send(move);
		System.out.println("creo Market Move, color fam: " + pressedButton.getColor() +"  spazio: " +   pressedButton.getSpace() + 
				" servitori aggiunti " + pressedButton.getNumServants());
		
	}

}