package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.ps22.server.move.TowerCharacterMove;

public class CharacterListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		ActionButton pressedButton = (ActionButton)e.getSource();
		TowerCharacterMove move = new TowerCharacterMove(pressedButton.getUsername(), pressedButton.getColor(), pressedButton.getSpace(), 
				pressedButton.getNumServants());
		System.out.println("creo character Move, color fam: " + pressedButton.getColor() +"  piano torre: " +   pressedButton.getSpace() + 
				" servitori aggiunti " + pressedButton.getNumServants());
	}
}
