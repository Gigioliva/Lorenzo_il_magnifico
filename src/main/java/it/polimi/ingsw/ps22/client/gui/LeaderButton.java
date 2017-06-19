package it.polimi.ingsw.ps22.client.gui;

import javax.swing.JButton;

import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.model.Model;

public class LeaderButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2049747823690383400L;
	String username;
	int slot;
	private Rectangle dim;
	private final static String pathPlayedLeader = "./image/leadercard/leadersback.jpg";
	
	public LeaderButton(int slot, PersonalBoardPanel p, CardLeader card, String username){
		this.username = username;
		this.slot = slot;
		Rectangle dim = PersonalBoardAdaptive.getLeaderSlot(p.resizeFactor);
		this.dim = dim;
		this.setBounds(dim.getInitx() + (int)p.getBounds().getWidth(), dim.getInity(), dim.getOffsetX(), dim.getOffsetY());
		String path = CardPath.getLeaderCardPathname(card);
		this.setIcon(MyImage.getScaledImageinLabel(path, dim).getIcon());
		this.addMouseListener(new MyMouse(BoardPanel.zoomedCard, path));
	}
	

	public void updateLeader(Model model){
		if(slot<model.getPlayers().get(username).getLeaders().size()){
			if (model.getPlayers().get(username).getLeaders().get(slot).isPlay()){
				this.setIcon(MyImage.getScaledImageinLabel(pathPlayedLeader, dim).getIcon());
			}
		}
			
	}
}
