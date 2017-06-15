package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.answer.AnswerCopyLeader;
import it.polimi.ingsw.ps22.server.message.AskCopyLeader;

public class AskCopyLeaderDialog extends MessageDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4218985052105525888L;
	AskCopyLeader mex;
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> buttons = new ArrayList<JRadioButton>();
	
	public AskCopyLeaderDialog(ViewClient view, AskCopyLeader mex ){
		super(view);
		this.mex = mex;
		
		mainPanel.setLayout(new GridLayout(0, 1));
		
		this.setTitle("Which leader card you wanna copy?");
		
		for(int i = 0; i < mex.getLeaders().size(); i++){
			JRadioButton b1 = new JRadioButton(mex.getLeaders().get(i).getName());
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
					view.send(new AnswerCopyLeader(mex.getId(), b.getText()));
					AskCopyLeaderDialog.this.dispose();
				}
			}
			
		}		
	}
}
