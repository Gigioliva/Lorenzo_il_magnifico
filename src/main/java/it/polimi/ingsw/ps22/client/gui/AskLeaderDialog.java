package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.answer.AnswerLeader;
import it.polimi.ingsw.ps22.server.message.AskLeader;

public class AskLeaderDialog extends MessageDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AskLeader mex;
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
	
	public AskLeaderDialog(ViewClient view, AskLeader mex ){
		super(view);
		this.mex = mex;
		
		mainPanel.setLayout(new GridLayout(0, 1));
		
		this.setTitle("Which leader card you want? ");
		
		for(int i = 0; i < mex.getLeaders().size(); i++){
			JRadioButton b1 = new JRadioButton(mex.getLeaders().get(i));
			group.add(b1);
			buttons.add(b1);
		    mainPanel.add(b1);
		}

	    mainPanel.add(confirmButton);
	    
	    confirmButton.addActionListener(new ConfirmListener());
		
	    this.setMinimumSize(new Dimension(150,200));
	}
	
	
	private class ConfirmListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(JRadioButton b: buttons){
				if (b.isSelected()){
					view.send(new AnswerLeader(mex.getId(), b.getText()));
					AskLeaderDialog.this.dispose();
				}
			}
			
		}		
	}
}
