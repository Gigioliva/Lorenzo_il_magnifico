package it.polimi.ingsw.ps22.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import it.polimi.ingsw.ps22.server.model.Model;

public class Gui extends JFrame implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LayeredPanel board;
	

	
	public void initGui(String username){
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		
		
		this.pack(); 
		this.setVisible(true);
		
	
		board = new LayeredPanel(this.getWidth(), this.getHeight(), username); 
		
		this.add(board);
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	
	}
	
	public void updateGui(Model model){
		//board.update(model.getBoard());
	}



	@Override
	public void update(Observable arg0, Object arg1) {
		if (!(arg1 instanceof HashMap<?,?>))
			throw new IllegalArgumentException();
		board.setCards((HashMap<Integer,ArrayList<String>>) arg1);
		
	}

}
