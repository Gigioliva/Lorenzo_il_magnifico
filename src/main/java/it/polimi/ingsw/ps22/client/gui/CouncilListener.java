package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.move.CouncilMove;

public class CouncilListener implements ActionListener{
	
	ViewClient view;
	
	public  CouncilListener(ViewClient view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ActionButton pressedButton = (ActionButton)e.getSource();
		CouncilMove move = new CouncilMove(pressedButton.getUsername(), pressedButton.getColor(), 
				pressedButton.getNumServants());
		view.send(move);
		System.out.println("creo council Move, color fam: " + pressedButton.getColor() +"  piano torre: " + 
				" servitori aggiunti " + pressedButton.getNumServants());
	}

}
