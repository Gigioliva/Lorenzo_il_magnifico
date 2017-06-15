package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.answer.AnswerFamily;
import it.polimi.ingsw.ps22.server.message.AskFamily;
import it.polimi.ingsw.ps22.server.model.Color;

public class AskFamilyDialog extends MessageDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7560062916449308874L;
	ButtonGroup group = new ButtonGroup();
	AskFamily mex;
	ArrayList<Button> buttons = new ArrayList<Button>();
	
	public AskFamilyDialog(ViewClient view, AskFamily mex){
		super(view);
		
		this.mex = mex;
		
		this.setTitle("One familiar can have action value of 6, choose it ");
		
		Button b1 = new Button(Color.BLACK);
		Button b2 = new Button(Color.ORANGE);
		Button b3 = new Button(Color.WHITE);
		
		buttons.add(b1);
		buttons.add(b2);
		buttons.add(b3);
	    
	    mainPanel.setLayout(new GridLayout(0, 1));
	    mainPanel.add(b1);
	    mainPanel.add(b2);
	    mainPanel.add(b3);
		
	    mainPanel.add(confirmButton);
	    
	    confirmButton.addActionListener(new ConfirmListener());
	    
	    this.setMinimumSize(new Dimension(150,200));
	}
	
	private class Button extends JRadioButton{
		/**
		 * 
		 */
		private static final long serialVersionUID = 4680334484801282487L;
		private Color col;
		
		public Button(Color col){
			super(col.toString());
			this.col = col;
			
		}
		
		public Color getCol(){
			return this.col;
		}
	}

	private class ConfirmListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(Button b: buttons){
				if (b.isSelected()){
					view.send(new AnswerFamily(mex.getId(), b.getCol()));
					AskFamilyDialog.this.dispose();
				}
			}	
			
		}		
	}
}
