package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.move.LeaderPlaying;

public class LeaderPlayingListener implements ActionListener {

	private ViewClient view;
	private String username;
	private String nameCard;
	
	public LeaderPlayingListener(ViewClient view, String username, String nameCard) {
		super();
		this.username = username;
		this.nameCard = nameCard;
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		LeaderPlaying move = new LeaderPlaying(username, nameCard);
		view.send(move);
		
	}

}
