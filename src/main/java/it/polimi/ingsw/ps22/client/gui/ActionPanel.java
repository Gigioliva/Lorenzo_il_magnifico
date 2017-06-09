package it.polimi.ingsw.ps22.client.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import it.polimi.ingsw.ps22.server.model.Color;

public class ActionPanel extends JPanel  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2811878893905465346L;
	ActionButton b;
	Rectangle dim;
	
	public ActionPanel(String name,Rectangle dim, ActionListener actionListener, int space){
		
		ActionButton b = new ActionButton(space,name);
		this.b = b;
		b.addActionListener(actionListener);
		
		this.setLayout(new GridLayout(1, 1));
		this.add(b);
		this.setOpaque(false);
		
		this.dim = dim;
		
		setMeasures(dim);
		
	}
	
	protected void enableZone(Color color){
			b.setEnabled(true);
			b.setColor(color);
			b.setBorderPainted(true);

	}
	
	@Override
	public String getName() {

		return b.getName();
	}
	
	protected void setNumServants(int value){
		b.setNumServants(value);
		
	}
	
	private void setMeasures(Rectangle dim){
		this.setBounds(dim.getInitx(), dim.getInity(), dim.getOffsetX(), dim.getOffsetY());
	}


}
