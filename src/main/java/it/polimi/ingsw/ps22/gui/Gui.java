package it.polimi.ingsw.ps22.gui;

import javax.swing.JFrame;

public class Gui extends JFrame  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LayeredPanel board;
	

	
	public void initGui(){
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		
		
		this.pack();
		this.setVisible(true);
		
	
		board = new LayeredPanel(this.getWidth(), this.getHeight()); 
		
		this.add(board);
		
		
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	
	}

}
