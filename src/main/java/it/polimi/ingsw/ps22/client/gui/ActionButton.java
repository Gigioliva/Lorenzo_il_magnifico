package it.polimi.ingsw.ps22.client.gui;

import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.polimi.ingsw.ps22.server.model.Color;

public class ActionButton extends JButton  {
	
		/**
	 * 
	 */
	int floor;
	Color color;
	int numServants;
	String username;
	
	private static final long serialVersionUID = 7616297999407953048L;

		public ActionButton(int floor){
			super();
			this.setEnabled(false);
			this.setOpaque(true);
			this.setBorderPainted(false);
			this.setContentAreaFilled(false);
			
			this.floor = floor;
			
			final Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();
			final Insets insets = raisedBevelBorder.getBorderInsets(this);
		    final EmptyBorder emptyBorder = new EmptyBorder(insets);
		    
			this.getModel().addChangeListener(new ChangeListener() {
			        @Override
			        public void stateChanged(ChangeEvent e) {
			            ButtonModel model = (ButtonModel) e.getSource();
			            if (model.isRollover()) {
			                ActionButton.this.setBorder(raisedBevelBorder);
			            } else {
			                ActionButton.this.setBorder(emptyBorder);
			            }
			        }
			    });
		}

		
		public void setColor(Color color){
			this.color = color;
		}
		
		public void setNumServants(int numServants) {
			this.numServants = numServants;
		}
	
		
		public int getFloor() {
			return floor;
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
