package it.polimi.ingsw.ps22.client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.answer.AnswerUsername;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = -1941598498652425988L;
	transient ViewClient view;
	JTextField userText;
	JPasswordField passText;
	JLabel userLabel;
	JLabel passLabel;
	JLabel newUserLabel;
	JCheckBox newUser;
	JLabel matchmakingLabel;
	JLabel inProgressLabel;
	JLabel errorLabel;
	JRadioButton confirm4;
	JRadioButton confirm5;
	ButtonGroup radioGroup;
	JLabel topPlayersLabel;
	JLabel lobbyLabel;
	//JLabel lobbyMembersLabel;

	public LoginFrame(ViewClient view) {

		this.view = view;
		ImageIcon boardBack = MyImage.createImageIcon("./image/persboardback.png");
		JLabel allLabel = new JLabel(boardBack);
		allLabel.setMinimumSize(this.getSize());
		userText = new JTextField();
		passText = new JPasswordField();
		userLabel = new JLabel("Username:");
		passLabel = new JLabel("Password:");
		newUser = new JCheckBox();
		newUserLabel = new JLabel("New user?");
		matchmakingLabel = new JLabel("Matchmaking");
		inProgressLabel = new JLabel("in progress");
		errorLabel = new JLabel("Error!");
		confirm4 = new JRadioButton("4 Players");
		confirm5 = new JRadioButton("5 Players");
		radioGroup = new ButtonGroup();
		topPlayersLabel = new JLabel("Top Players:");
		JButton confirm = new JButton("Confirm");
		lobbyLabel = new JLabel("Lobby:");
		//lobbyMembersLabel = new JLabel("Lobby Members:");

		userLabel.setFont(new Font("Colibri", Font.BOLD, 16));
		userLabel.setForeground(Color.WHITE);
		userLabel.setOpaque(false);
		passLabel.setFont(new Font("Colibri", Font.BOLD, 16));
		passLabel.setForeground(Color.WHITE);
		passLabel.setOpaque(false);
		newUserLabel.setFont(new Font("Colibri", Font.BOLD, 16));
		newUserLabel.setForeground(Color.WHITE);
		newUserLabel.setOpaque(false);
		confirm4.setFont(new Font("Colibri", Font.BOLD, 16));
		confirm4.setForeground(Color.WHITE);
		confirm4.setOpaque(false);
		confirm5.setFont(new Font("Colibri", Font.BOLD, 16));
		confirm5.setForeground(Color.WHITE);
		confirm5.setOpaque(false);
		topPlayersLabel.setFont(new Font("Colibri", Font.BOLD, 16));
		topPlayersLabel.setForeground(Color.WHITE);
		topPlayersLabel.setOpaque(false);
		matchmakingLabel.setFont(new Font("Colibri", Font.BOLD, 26));
		matchmakingLabel.setForeground(Color.WHITE);
		matchmakingLabel.setOpaque(false);
		inProgressLabel.setFont(new Font("Colibri", Font.BOLD, 26));
		inProgressLabel.setForeground(Color.WHITE);
		inProgressLabel.setOpaque(false);
		errorLabel.setFont(new Font("Colibri", Font.BOLD, 26));
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setOpaque(false);
		newUser.setOpaque(false);
		confirm.setOpaque(false);
		//lobbyMembersLabel.setFont(new Font("Colibri", Font.BOLD, 16));
		//lobbyMembersLabel.setForeground(Color.WHITE);
		//lobbyMembersLabel.setOpaque(true);
		lobbyLabel.setFont(new Font("Colibri", Font.BOLD, 16));
		lobbyLabel.setForeground(Color.WHITE);

		userText.setBounds(120, 30, 160, 40);
		passText.setBounds(120, 80, 160, 40);
		userLabel.setBounds(30, 30, 160, 40);
		passLabel.setBounds(30, 80, 160, 40);
		newUserLabel.setBounds(30, 120, 160, 40);
		newUser.setBounds(120, 125, 30, 30);
		lobbyLabel.setBounds(30, 160, 120, 40);
		confirm4.setBounds(90, 160, 110, 40);
		confirm5.setBounds(200, 160, 110, 40);
		topPlayersLabel.setBounds(320, 30, 245, 340);
		matchmakingLabel.setBounds(60, 270, 200, 40);
		inProgressLabel.setBounds(70, 320, 200, 40);
		errorLabel.setBounds(115, 280, 200, 40);
		confirm.setBounds(50, 220, 210, 40);
		//lobbyMembersLabel.setBounds(30, 250, 230, 120);

		allLabel.add(userText);
		allLabel.add(passText);
		allLabel.add(userLabel);
		allLabel.add(passLabel);
		allLabel.add(newUserLabel);
		allLabel.add(newUser);
		allLabel.add(confirm4);
		allLabel.add(confirm5);
		allLabel.add(topPlayersLabel);
		allLabel.add(matchmakingLabel);
		allLabel.add(errorLabel);
		allLabel.add(inProgressLabel);
		allLabel.add(confirm);
		radioGroup.add(confirm4);
		radioGroup.add(confirm5);
		allLabel.add(lobbyLabel);
		//allLabel.add(lobbyMembersLabel);

		userText.setVisible(true);
		passText.setVisible(true);
		userLabel.setVisible(true);
		passLabel.setVisible(true);
		newUser.setVisible(true);
		newUserLabel.setVisible(true);
		confirm4.setVisible(true);
		confirm5.setVisible(true);
		topPlayersLabel.setVisible(true);
		confirm.setVisible(true);
		matchmakingLabel.setVisible(false);
		inProgressLabel.setVisible(false);
		errorLabel.setVisible(false);
		lobbyLabel.setVisible(true);
		//lobbyMembersLabel.setVisible(true);
		this.add(allLabel);

		this.setMinimumSize(new Dimension(600, 400));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		confirm.addActionListener(new ConfirmListener());
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.out.println("Error Login Frame");
		}
		this.setUndecorated(true);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);

	}

	private class ConfirmListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String temp = new String(passText.getPassword());
			if (confirm4.isSelected()) {
				view.send(new AnswerUsername(userText.getText(), temp, 4, newUser.isSelected()));
				matchmakingLabel.setVisible(true);
				inProgressLabel.setVisible(true);
				
			}
			if (confirm5.isSelected()) {
				view.send(new AnswerUsername(userText.getText(), temp, 5, newUser.isSelected()));
				matchmakingLabel.setVisible(true);
				inProgressLabel.setVisible(true);
			}

		}

	}

	public void closeLoginWindow() {
		LoginFrame.this.dispose();
	}
	
	public void notifyErrorForm() {
		matchmakingLabel.setVisible(false);
		inProgressLabel.setVisible(false);
		errorLabel.setVisible(true);
	}

	public void updateTopPlayers(String str) {
		topPlayersLabel.setText(str);
	}

	/*
	private void updateLobbyMembers(ArrayList<String> members) {
		StringBuilder temp = new StringBuilder("Players in your lobby:");
		for (String str : members) {
			temp.append("\n" + str);
		}
		lobbyMembersLabel.setText(temp.toString());
	}
	*/

}
