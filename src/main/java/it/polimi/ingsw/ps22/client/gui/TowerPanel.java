package it.polimi.ingsw.ps22.client.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import it.polimi.ingsw.ps22.server.model.Color;

public class TowerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2290195974998616160L;
	
	String name;
	ActionButton b1;
	ActionButton b2;
	ActionButton b3;
	ActionButton b4;
	
	
	public TowerPanel(String name, double resizeFactor, ActionListener actionListener){
		this.setLayout(new GridBagLayout());
		
		this.name = name;
		
		this.setOpaque(false);
		
		
		b1 = new ActionButton(0);
		b1.addActionListener(actionListener);
		b2 = new ActionButton(1);
		b2.addActionListener(actionListener);
		b3 = new ActionButton(2);
		b3.addActionListener(actionListener);
		b4 = new ActionButton(3);
		b4.addActionListener(actionListener);
		
		setMeasures(resizeFactor);
		
		this.setBorder(BorderFactory.createEmptyBorder());
		
	}
	
	private void setMeasures(double resizeFactor){
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.VERTICAL;
		
		Rectangle measures1 = AdaptiveLayout.getCardDevelopmentSpace(resizeFactor, 1, 0);
		Rectangle measures2 = AdaptiveLayout.getCardDevelopmentSpace(resizeFactor, 1, 1);
		
		c.weighty=1;
		c.ipadx = measures2.getOffsetX();
		c.gridx=0;
		
		c.ipady = measures1.getOffsetY();
		c.gridy=0;
		this.add(b4,c);
		
		JButton p1 =new JButton();
		p1.setEnabled(false);
		p1.setOpaque(false);
		p1.setBorderPainted(false);
		p1.setContentAreaFilled(false);
		c.ipady =measures1.getInity() - measures2.getFinaly();
		c.gridy = 1;
		this.add(p1,c);
		
	
		c.ipady = measures1.getOffsetY();
		c.gridy=2;
		this.add(b3,c);
		
		JButton p2 =new JButton();
		p2.setEnabled(false);
		p2.setOpaque(true);
		p2.setBorderPainted(false);
		p2.setContentAreaFilled(false);
		c.ipady =measures1.getInity() - measures2.getFinaly();
		c.gridy = 3;
		this.add(p2,c);
		
		
		c.ipady = measures1.getOffsetY();
		c.gridy=4;
		this.add(b2,c);
		
		JButton p3 =new JButton();
		p3.setEnabled(false);
		p3.setOpaque(true);
		p3.setBorderPainted(false);
		p3.setContentAreaFilled(false);
		c.ipady =measures1.getInity() - measures2.getFinaly();
		c.gridy = 5;
		this.add(p3,c);
		
		c.ipady = measures1.getOffsetY();
		c.gridy=6;
		this.add(b1,c);
	}
	
	protected void enableTower(Color color){
		b1.setEnabled(true);
		b1.setColor(color);
		
		b2.setEnabled(true);
		b2.setColor(color);
		
		b3.setEnabled(true);
		b3.setColor(color);

		b4.setEnabled(true);
		b4.setColor(color);
		
		b1.setBorderPainted(true);
		b2.setBorderPainted(true);
		b3.setBorderPainted(true);
		b4.setBorderPainted(true);
		
	}
	
	@Override
	public String getName() {

		return this.name;
	}
	
	protected void setNumServants(int value){
		b1.setNumServants(value);
		b2.setNumServants(value);
		b3.setNumServants(value);
		b4.setNumServants(value);
	}

		
}