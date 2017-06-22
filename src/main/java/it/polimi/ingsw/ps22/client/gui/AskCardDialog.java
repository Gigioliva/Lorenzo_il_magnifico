package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.answer.AnswerCard;
import it.polimi.ingsw.ps22.server.message.AskCard;

public class AskCardDialog extends MessageDialog {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 727347642126013376L;
	AskCard mex;
	ButtonGroup group = new ButtonGroup();
	ArrayList<Button> buttons = new ArrayList<>();

	public AskCardDialog(ViewClient view, AskCard mex){
		super(view);
		
		mainPanel.setLayout(new GridLayout(0, 1));
		
		this.mex = mex;
		
		this.setTitle("Scegli tra le seguenti carte");
	
		
		for(String type: mex.getPossibleCard().keySet()){
			for(int i = 0; i < mex.getPossibleCard().get(type).size(); i++){
				Button b = new Button(mex.getPossibleCard().get(type).get(i).getName(), type);
				buttons.add(b);
				group.add(b);
				mainPanel.add(b);
			}
		}
		
		 mainPanel.add(confirmButton);
		    
		 confirmButton.addActionListener(new ConfirmListener());
		
		 this.setMinimumSize(new Dimension(150,200));
		
	}
	
	private class Button extends JRadioButton{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -74226909844700947L;
		private String name;
		private String type;
		
		public Button(String name, String type){
			super(name);
			this.name= name;
			this.type = type;
			
		}
		
		@Override
		public String getName(){
			return this.name;
		}
		
		public String getType(){
			return this.type;
		}
	}
	
	private class ConfirmListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for(Button b: buttons){
				if (b.isSelected()){
					view.send(new AnswerCard(mex.getId(), b.getType(), b.getName()));
					AskCardDialog.this.dispose();
				}
			}
				
		}
	}
}
	
