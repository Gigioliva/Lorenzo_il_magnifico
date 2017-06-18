package it.polimi.ingsw.ps22.client.gui;

import javax.swing.JButton;

import it.polimi.ingsw.ps22.server.card.CardLeader;

public class LeaderButton extends JButton {
	
	String username;
	int slot;
	
	public LeaderButton(int slot, PersonalBoardPanel p, CardLeader card, String username){
		this.username = username;
		this.slot = slot;
		Rectangle dim = PersonalBoardAdaptive.getLeaderSlot(p.resizeFactor).get(slot);
		this.setBounds(dim.getInitx() + (int)p.getBounds().getWidth(), dim.getInity(), dim.getOffsetX(), dim.getOffsetY());
		String path = CardPath.getLeaderCardPathname(card);
		this.setIcon(MyImage.getScaledImageinLabel(path, dim).getIcon());
		this.addMouseListener(new MyMouse(BoardPanel.zoomedCard, path));
		//this.addChangeListener(new BorderEffect(this));
	}
	
	/*
	public void updateLeader(Model model){
		model.getPlayers().get(username).getLeaders().get(slot).isPlay()
	}
	*/
}
