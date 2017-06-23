package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import it.polimi.ingsw.ps22.server.card.CardLeader;

public class LeaderAvverButton extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8136957738988127711L;
	//private final int NUMLEADERS = 4;
	private final String leaderBackPath = "./image/leadercard/leadersback.jpg";
	protected transient Rectangle cardDim;
	private ArrayList<CardLeader> leaders = new ArrayList<>();
	private transient AdaptiveLayout layout = AdaptiveLayout.instance();
	
	public LeaderAvverButton(String username, double resizeFactor, java.awt.Color c, ArrayList<CardLeader> leaders){
		super();
		this.setLayout(new GridLayout(2, 2));
		this.leaders = leaders;
		cardDim = layout.getCardBuildingSpace(resizeFactor, 0);
	
		setLeaders();
		
		this.setMinimumSize(new Dimension(3*cardDim.getOffsetX(), 3*cardDim.getOffsetY()));
			
	}
	
	private void setLeaders(){
		for(int i=0; i < 4 - leaders.size(); i++){
			
			this.add(MyImage.getScaledImageinLabel(leaderBackPath, new Rectangle(0,(int)(cardDim.getOffsetX()*2.5), 0 ,
					(int)(cardDim.getOffsetY()*2.5))));

		}
		
		for(int i = 0; i<leaders.size(); i++){
			String path = CardPath.getLeaderCardPathname(leaders.get(i));
			this.add(MyImage.getScaledImageinLabel(path, new Rectangle(0,(int)(cardDim.getOffsetX()*2.5), 0 ,
					(int)(cardDim.getOffsetY()*2.5))));
		}
	}

	
}
