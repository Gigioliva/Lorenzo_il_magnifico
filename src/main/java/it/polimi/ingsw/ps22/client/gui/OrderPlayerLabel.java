package it.polimi.ingsw.ps22.client.gui;

import java.awt.Graphics;

import javax.swing.JLabel;

import it.polimi.ingsw.ps22.server.model.Model;

public class OrderPlayerLabel extends JLabel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 825229972480374761L;
	private String username;
	private java.awt.Color c;
	private int pos;
	private transient AdaptiveLayout layout = AdaptiveLayout.instance();
	
	public OrderPlayerLabel(int pos, double resizeFactor){
		super();
		this.pos = pos;
		Rectangle rec = layout.getPlayerGridSpace(resizeFactor, pos);
		this.setBounds(rec.getInitx(), rec.getInity(), rec.getOffsetX(), rec.getOffsetY());
	}
	
	public void updateOrderLabel(Model model){
		this.username = model.getOrderedPlayers(pos);
		this.c = model.getPlayers().get(username).getColor().getColor();
		this.setVisible(true);
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.setColor(c);
		g.fillOval(0, 0, this.getWidth(), this.getHeight());
	}
}
