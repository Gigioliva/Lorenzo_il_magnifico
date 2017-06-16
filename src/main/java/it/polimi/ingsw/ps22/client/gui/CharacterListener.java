package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.move.TowerCharacterMove;

public class CharacterListener implements ActionListener{
	
	ViewClient view;
	
	public  CharacterListener(ViewClient view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ActionButton pressedButton = (ActionButton)e.getSource();
		TowerCharacterMove move = new TowerCharacterMove(pressedButton.getUsername(), pressedButton.getColor(), pressedButton.getSpace() + 1, 
				pressedButton.getNumServants());
		view.send(move);
		System.out.println("creo character Move, color fam: " + pressedButton.getColor() +"  piano torre: " +   pressedButton.getSpace() + 
				" servitori aggiunti " + pressedButton.getNumServants());
	}
}
