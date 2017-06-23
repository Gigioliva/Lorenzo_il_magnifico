package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;

public abstract class ActionButton extends JButton  {
	
		/**
	 * 
	 */
	protected int space;
	protected Color color;
	protected int numServants;
	protected String username;
	protected transient Rectangle dim;
	
	protected List<Family> familiars = new ArrayList<>();
	
	
	
	private static final long serialVersionUID = 7616297999407953048L;

		public ActionButton(int space, String username, Rectangle dim, ActionListener actionListener){
			super();
			this.setEnabled(false);
			this.setOpaque(false);
			this.setBorderPainted(false);
			this.setContentAreaFilled(false);
			
			this.addActionListener(actionListener);
			
			this.space = space;
			this.username = username;
			
			this.dim = dim;
			
		    
			this.getModel().addChangeListener(new BorderEffect(this));
			
			setMeasures(dim);
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
		
		public abstract void updateButton(Model model);
		
		private void setMeasures(Rectangle dim){
			this.setBounds(dim.getInitx(), dim.getInity(), dim.getOffsetX(), dim.getOffsetY());
		}
		
		protected void enableZone(Color color){
			this.setEnabled(true);
			this.setColor(color);
			this.setBorderPainted(true);

	}
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			MyImage.updatePlayersSpaces(familiars, this, g);
		}
	
		
}
