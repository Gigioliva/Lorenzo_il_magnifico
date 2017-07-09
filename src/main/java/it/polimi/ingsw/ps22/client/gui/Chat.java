package it.polimi.ingsw.ps22.client.gui;

import java.awt.BorderLayout;
import java.awt.Font;
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
	private static final int MAX_MESS = 6;
	private JTextField userText = new JTextField();
	private JButton send = new JButton("Send");
	private JLabel mex = new JLabel();
	private ArrayList<String> messagge = new ArrayList<>();
	private ViewClient view;
	// da utilizzare per dimensioni corrette
	//private double resizeFactor;
	
	public Chat(ViewClient view, double resizeFactor){
		this.view = view;
		//this.resizeFactor = resizeFactor;
		//this.setOpaque(true);
		this.setBackground(new java.awt.Color(154, 92, 43));
		this.setLayout(new BorderLayout());
		this.add(mex, BorderLayout.CENTER);
		JLabel title=new JLabel("CHAT");
		title.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 20));
		send.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 15));
		mex.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 12));
		this.add(title, BorderLayout.PAGE_START);
		send.setBackground(new java.awt.Color(154, 92, 43));
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		panel.add(userText);
		panel.add(send);
		this.add(panel, BorderLayout.PAGE_END);
		send.addActionListener(new SendListener());
	}

	public void addMex(String str) {
		messagge.add(str);
		while (messagge.size() > MAX_MESS) {
			messagge.remove(0);
		}
		StringBuilder tot = new StringBuilder();
		tot.append("<html>");
		for (String el : messagge) {
			tot.append(el + "<br/>");
		}
		tot.append("</html>");
		mex.setText(tot.toString());
	}

	private class SendListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.send(new ChatMessage(userText.getText(), view.getUsername()));
			userText.setText("");
		}
	}

}
