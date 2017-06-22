package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.answer.AnswerCouncilPrivilege;
import it.polimi.ingsw.ps22.server.message.AskCouncilPrivilege;

public class PrivilegeDialog extends MessageDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5295328520342279302L;
	ButtonGroup group = new ButtonGroup();
	AskCouncilPrivilege mex;
	ArrayList<Button> buttons = new ArrayList<>();
	
	public PrivilegeDialog(ViewClient view, AskCouncilPrivilege mex){
		super(view);
		
		this.mex = mex;
		
		Button b1 = new Button(new ExchangeElem(1, "1 legno e 1 pietra"));
		Button b2 = new Button(new ExchangeElem(2, "2 servitori"));
		Button b3 = new Button(new ExchangeElem(3, "2 monete"));
		Button b4 = new Button(new ExchangeElem(4, "2 punti militari"));
		Button b5 = new Button(new ExchangeElem(5, "1 punto fede"));
		
		buttons.add(b1);
		buttons.add(b2);
		buttons.add(b3);
		buttons.add(b4);
		buttons.add(b5);
	    
	    mainPanel.setLayout(new GridLayout(0, 1));
	    mainPanel.add(b1);
	    mainPanel.add(b2);
	    mainPanel.add(b3);
	    mainPanel.add(b4);
	    mainPanel.add(b5);
		
	    mainPanel.add(confirmButton);
	    
	    confirmButton.addActionListener(new ConfirmListener());
	    
	    this.setMinimumSize(new Dimension(150,200));
	}
	
	private class Button extends JRadioButton{
		/**
		 * 
		 */
		private static final long serialVersionUID = 4680334484801282487L;
		private ExchangeElem el;
		
		public Button(ExchangeElem el){
			super(el.toString());
			this.el = el;
			
		}
		
		public ExchangeElem getEl(){
			return this.el;
		}
	}
	
	private int numSelected(){
		int cont = 0;
		for(Button b: buttons){
			if(b.isSelected())
				cont++;
		}
		return cont;
	}

	private class ConfirmListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Integer> choices = new ArrayList<Integer>();
			if(numSelected() == mex.getNumChoice()){
				for(Button b: buttons){
					if (b.isSelected()){
						choices.add(b.getEl().getId());
					}
				}

				view.send(new AnswerCouncilPrivilege(mex.getId(), choices));
				PrivilegeDialog.this.dispose();
			}
			
		}		
	}
	
	
	private class ExchangeElem{
		private int id;
		private String str;
		
		private ExchangeElem(int id, String str){
			this.id = id;
			this.str = str;
		}
		
		public int getId(){
			return this.id;
		}
		
		@Override 
		public String toString() {
			return new String(str);
		}
		
			
	}
}
