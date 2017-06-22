package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.answer.AnswerUsername;

public class LoginFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1941598498652425988L;
	JPanel mainPanel = new JPanel();
	
	public LoginFrame(ViewClient view){
		
		mainPanel.setLayout(new GridLayout(3, 1));
		
		JTextField t = new JTextField();
		
		JTextField t2 = new JTextField();
		
		t.setMinimumSize(new Dimension(100, 20));
		
		t2.setMinimumSize(new Dimension(100, 20));
		
		JButton confirm = new JButton("Confirm");
		
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.send(new AnswerUsername(t.getText(), t2.getText()));
				LoginFrame.this.dispose();
			}
		});
		
		this.add(mainPanel);
		
		mainPanel.add(t);
		mainPanel.add(t2);
		mainPanel.add(confirm);
		
		this.setMinimumSize(new Dimension(150, 80));
		
		
		
		this.pack();
		this.setVisible(true);
		
	
		
	}
}
