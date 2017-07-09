package it.polimi.ingsw.ps22.client.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.message.ChatMessage;

public class Chat extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int MAX_MESS=4;
	private JTextField userText = new JTextField();
	private JButton send = new JButton("Send");
	private JLabel mex =new JLabel();
	private ArrayList<String> messagge=new ArrayList<>();
	private ViewClient view;
	
	public Chat(ViewClient view){
		this.view=view;
		this.setLayout(new GridLayout(3, 1));
		this.add(mex);
		this.add(userText);
		this.add(send);
		send.addActionListener(new SendListener());
	}
	
	public void addMex(String str){
		messagge.add(str);
		while(messagge.size()>=MAX_MESS){
			messagge.remove(0);
		}
		StringBuilder tot=new StringBuilder();
		tot.append("<html>Chat:<br/>");
		for(String el: messagge){
			tot.append(el +"<br/>");
		}
		tot.append("</html>");
		mex.setText(tot.toString());
	}
	
	private class SendListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.send(new ChatMessage(userText.getText()));
		}		
	}

}
