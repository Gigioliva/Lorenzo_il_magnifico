package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.answer.AnswerCosts;
import it.polimi.ingsw.ps22.server.message.AskCosts;

public class AskCostDialog extends MessageDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7092751202658591700L;
	AskCosts mex;
	ButtonGroup group = new ButtonGroup();
	ArrayList<Button> buttons = new ArrayList<Button>();
	
	public AskCostDialog(ViewClient view, AskCosts mex ){
		super(view);
		this.mex = mex;
		
		mainPanel.setLayout(new GridLayout(0, 1));
		
		this.setTitle("Scegli tra i seguenti costi");
		
		for(int i = 0; i < mex.getPossibleCost().size(); i++){
			Button b1 = new Button(i + 1, mex.getPossibleCost().get(i).toString());
			group.add(b1);
			buttons.add(b1);
		    mainPanel.add(b1);
		}

	    mainPanel.add(confirmButton);
	    
	    confirmButton.addActionListener(new ConfirmListener());
		
	    this.setMinimumSize(new Dimension(150,200));
	}
	
	private class Button extends JRadioButton{
		/**
		 * 
		 */
		private static final long serialVersionUID = 27887861690710109L;
		private int answer;
		
		public Button(int answer, String text){
			super(text);
			this.answer = answer;
			
		}
		
		public int getAnswer(){
			return this.answer;
		}
	}
	
	private class ConfirmListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(Button b: buttons){
				if (b.isSelected()){
					view.send(new AnswerCosts(mex.getId(), b.getAnswer()));
					AskCostDialog.this.dispose();
				}
			}
			
		}		
	}

}
