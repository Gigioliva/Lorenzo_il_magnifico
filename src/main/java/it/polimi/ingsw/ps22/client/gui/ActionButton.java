package it.polimi.ingsw.ps22.client.gui;

import javax.swing.JButton;

import it.polimi.ingsw.ps22.server.model.Color;

public class ActionButton extends JButton  {
	
		/**
	 * 
	 */
	int space;
	Color color;
	int numServants;
	String username;
	
	private static final long serialVersionUID = 7616297999407953048L;

		public ActionButton(int space, String username){
			super();
			this.setEnabled(false);
			this.setOpaque(true);
			this.setBorderPainted(false);
			this.setContentAreaFilled(false);
			
			this.space = space;
			this.username = username;
			
		    
			this.getModel().addChangeListener(new BorderEffect(this));
		}

		
		public void setColor(Color color){
			this.color = color;
		}
		
		public void setNumServants(int numServants) {
			this.numServants = numServants;
		}
	
		
		public int getSpace() {
			return space;
		}


		public Color getColor() {
			return color;
		}


		public int getNumServants() {
			return numServants;
		}


		public String getUsername() {
			return username;
		}
	
		
}
