package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.move.TowerVentureMove;

public class VentureListener implements ActionListener {
	
	ViewClient view;
	
	public  VentureListener(ViewClient view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ActionButton pressedButton = (ActionButton)e.getSource();
		TowerVentureMove move = new TowerVentureMove(pressedButton.getUsername(), pressedButton.getColor(), pressedButton.getSpace() + 1, 
				pressedButton.getNumServants());
		view.send(move);
	}

}
