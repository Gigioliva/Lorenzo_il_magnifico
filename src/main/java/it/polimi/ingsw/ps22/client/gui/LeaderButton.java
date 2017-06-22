package it.polimi.ingsw.ps22.client.gui;

import java.util.ArrayList;

import javax.swing.JButton;

public class LeaderButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2049747823690383400L;
	String username;
	private Rectangle dim;
	private String cardName;
	private PersonalBoardAdaptive layoutPersonal = PersonalBoardAdaptive.instance();
	//private final static String pathPlayedLeader = "./image/leadercard/leadersback.jpg";
	
	public LeaderButton(int slot, PersonalBoardPanel p, String username){
		this.username = username;
		Rectangle dimR = layoutPersonal.getLeaderSlot(p.resizeFactor);
		dimR = Rectangle.fillImageRatio(dimR, (float)1.5);
		ArrayList<Rectangle> recs = Rectangle.divideRectangle(dimR);
		Rectangle dim = recs.get(slot);
		this.dim = dim;
		this.setBounds(dim.getInitx() + (int)p.getBounds().getWidth(), dim.getInity(), dim.getOffsetX(), dim.getOffsetY());
		
		this.setEnabled(false);
		this.setVisible(false);
	
			
		/*
		String path = CardPath.getLeaderCardPathname(card);
		this.path = path;
		this.setIcon(MyImage.getScaledImageinLabel(path, dim).getIcon());
		this.addMouseListener(new MyMouse(BoardPanel.zoomedCard, path));
		*/
	}
	
	public Rectangle getDim(){
		return this.dim;
	}
	
	public void setCardName(String name){
		this.cardName = name;
	}
	
	public String getCardName(){
		return this.cardName;
	}

	/*
	public void updateLeader(Model model){
		
		for(int i = 0; i< model.getPlayers().get(username).getLeaders().size(); i++){
			if(CardPath.getLeaderCardPathname(model.getPlayers().get(username).getLeaders().get(i)).equals(path) ){
				
				if (model.getPlayers().get(username).getLeaders().get(i).isPlay()){
					this.setIcon(MyImage.getScaledImageinLabel(pathPlayedLeader, dim).getIcon());
				}
				
				else{
					CardLeader card = model.getPlayers().get(username).getLeaders().get(i);
					String path = CardPath.getLeaderCardPathname(card);
					this.setIcon(MyImage.getScaledImageinLabel(path, dim).getIcon());
					this.addMouseListener(new MyMouse(BoardPanel.zoomedCard, path));
				}
				
				
				return;
			}
			this.setIcon(null);
		}
			
	}
	*/
}
