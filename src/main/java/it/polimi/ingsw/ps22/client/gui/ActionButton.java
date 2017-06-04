package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ActionButton extends JButton {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 7616297999407953048L;

		public ActionButton(String name){
			super(name);
			this.addActionListener(new ActionListener() {
		
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("bottone cliccato");			
				}
			});
			this.setEnabled(false);
			this.setOpaque(true);
			this.setBorderPainted(false);
			this.setContentAreaFilled(false);
		}
}
