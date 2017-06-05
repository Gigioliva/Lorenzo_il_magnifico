package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.move.TowerCharacterMove;

public class ActionButton extends JButton {
	
		/**
	 * 
	 */
	int floor;
	String tower;
	Color color;
	int numServants;
	String username;
	
	private static final long serialVersionUID = 7616297999407953048L;

		public ActionButton(String tower, int floor){
			super();
			this.addActionListener(new TowerActionListener());
			this.setEnabled(false);
			this.setOpaque(true);
			this.setBorderPainted(false);
			this.setContentAreaFilled(false);
			
			this.tower = tower;
			this.floor = floor;
		}

		
		public void setColor(Color color){
			this.color = color;
		}
		
		public void setNumServants(int numServants) {
			this.numServants = numServants;
		}
		
		private class TowerActionListener implements ActionListener {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				ActionButton pressedButton = (ActionButton)e.getSource();
				if(tower == "Character"){
					TowerCharacterMove move = new TowerCharacterMove("player", color, floor, 0);
					System.out.println("creo Character Move, color fam: " + color +"  piano torre: " +  floor  + " servitori aggiunti " + numServants);
				}
			}
			
		}
	
		
}
