package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.answer.AnswerExcomm;
import it.polimi.ingsw.ps22.server.message.AskExcomm;

public class AskExcommDialog extends MessageDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7438327380031572667L;
	AskExcomm mex;
	ButtonGroup group = new ButtonGroup();
	JRadioButton b1;
	JRadioButton b2;
	//ArrayList<Button> buttons = new ArrayList<Button>();
	
	public AskExcommDialog(ViewClient view, AskExcomm mex ){
		super(view);
		this.mex = mex;
		
		mainPanel.setLayout(new GridLayout(0, 1));
		
		
		this.setTitle("Vuoi appoggiare la chiesa?");
		JRadioButton b1 = new JRadioButton("Si");
		group.add(b1);
	    mainPanel.add(b1);
	    
	    JRadioButton b2 = new JRadioButton("No");
	    group.add(b2);
	    mainPanel.add(b2);

	    mainPanel.add(confirmButton);
	    
	    confirmButton.addActionListener(new ConfirmListener());
		
	    this.setMinimumSize(new Dimension(150,200));
	}
	
	
	private class ConfirmListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (b1.isSelected()){
				view.send(new AnswerExcomm(mex.getId(), b1.getText()));
				AskExcommDialog.this.dispose();
			}
			else if (b2.isSelected()){
				view.send(new AnswerExcomm(mex.getId(), b2.getText()));
				AskExcommDialog.this.dispose();
			}

			
		}		
	}

}
