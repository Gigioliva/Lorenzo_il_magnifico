package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.answer.AnswerExcomm;
import it.polimi.ingsw.ps22.server.answer.AnswerServant;
import it.polimi.ingsw.ps22.server.message.AskExcomm;
import it.polimi.ingsw.ps22.server.message.AskServant;

public class AskServantDialog extends MessageDialog{
		
		AskServant mex;
		ServantSpinner spin;
		
		public AskServantDialog(ViewClient view, AskServant mex ){
			super(view);
			this.mex = mex;
			
			mainPanel.setLayout(new GridLayout(0, 1));
			
			
			this.setTitle("Quanti servitori vuoi spendere?");
			
			spin = new ServantSpinner();
			
			mainPanel.add(spin);

		    mainPanel.add(confirmButton);
		    
		    confirmButton.addActionListener(new ConfirmListener());
			
		    this.setMinimumSize(new Dimension(150,200));
		}
		
		
		private class ConfirmListener implements ActionListener {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int servants =(int) spin.getSpin().getValue();
				
				view.send(new AnswerServant(mex.getId(), servants));
				
				AskServantDialog.this.dispose();
				
			}		
		}

}
