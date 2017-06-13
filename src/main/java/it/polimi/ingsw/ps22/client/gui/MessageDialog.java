package it.polimi.ingsw.ps22.client.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import it.polimi.ingsw.ps22.client.main.ViewClient;

public abstract class MessageDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7332960474426750029L;
	protected JButton confirmButton;
	protected JPanel mainPanel;
	protected ViewClient view;
	
	
	public MessageDialog(ViewClient view){
		super();
		
		this.view = view;
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(10,10));
		
		confirmButton = new JButton("Confirm");
		mainPanel.add(confirmButton, BorderLayout.PAGE_END);
		
		this.add(mainPanel);
		pack();
		setMinimumSize(new Dimension(300, 30));
		setVisible(true);
	}

}
