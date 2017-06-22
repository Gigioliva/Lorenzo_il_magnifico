package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.answer.AnswerUsername;

public class LoginFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1941598498652425988L;
	JPanel mainPanel = new JPanel();
	ViewClient view;
	JTextField t;
	JTextField t2;
	JRadioButton confirm4;
	JRadioButton confirm5;
	
	public LoginFrame(ViewClient view){
		
		this.view=view;
		
		mainPanel.setLayout(new GridLayout(5, 1));
		
		t = new JTextField();
		
		t2 = new JTextField();
		
		t.setMinimumSize(new Dimension(100, 20));
		
		t2.setMinimumSize(new Dimension(100, 20));
		
		confirm4 = new JRadioButton("4");
		
		confirm5 = new JRadioButton("5");
		
		JButton confirm=new JButton("Confirm");
		
		this.add(mainPanel);
		mainPanel.add(t);
		mainPanel.add(t2);
		mainPanel.add(confirm4);
		mainPanel.add(confirm5);
		mainPanel.add(confirm);
		
		this.setMinimumSize(new Dimension(150, 80));
		
		confirm.addActionListener(new ConfirmListener());
		
		this.pack();
		this.setVisible(true);
		
	
		
	}
	
	private class ConfirmListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(confirm4.isSelected()){
				view.send(new AnswerUsername(t.getText(), t2.getText(),4));
			}
			if(confirm5.isSelected()){
				view.send(new AnswerUsername(t.getText(), t2.getText(),5));
			}
			LoginFrame.this.dispose();
			
		}
		
	}
}
