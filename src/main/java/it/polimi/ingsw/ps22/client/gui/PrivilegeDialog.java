package it.polimi.ingsw.ps22.client.gui;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import it.polimi.ingsw.ps22.client.main.ViewClient;

public class PrivilegeDialog extends MessageDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5295328520342279302L;
	ButtonGroup group = new ButtonGroup();
	
	public PrivilegeDialog(ViewClient view){
		super(view);
		
		JRadioButton b1 = new JRadioButton(new ExchangeElem(1, "1 legno e 1 pietra").toString());
		JRadioButton b2 = new JRadioButton(new ExchangeElem(2, "2 servitori").toString());
		JRadioButton b3 = new JRadioButton(new ExchangeElem(3, "2 monete").toString());
		JRadioButton b4 = new JRadioButton(new ExchangeElem(4, "2 punti militari").toString());
		JRadioButton b5 = new JRadioButton(new ExchangeElem(5, "1 punto fede").toString());
	
	    group.add(b1);
	    group.add(b2);
	    group.add(b3);
	    group.add(b4);
	    group.add(b5);
	    
	    mainPanel.setLayout(new GridLayout(0, 1));
	    mainPanel.add(b1);
	    mainPanel.add(b2);
	    mainPanel.add(b3);
	    mainPanel.add(b4);
	    mainPanel.add(b5);
		
	    mainPanel.add(confirmButton);
	    
	    this.setMinimumSize(new Dimension(150,200));;
	}
	
	private class ConfirmListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ExchangeElem el = (ExchangeElem)group.getSelection();
			view.send(el.getId());
			//nelle classi innestate per poter usare this devo mettere nome della classe che lo contiene.this altrimenti this sarebbe ConfirmListener
			PrivilegeDialog.this.dispose();
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
