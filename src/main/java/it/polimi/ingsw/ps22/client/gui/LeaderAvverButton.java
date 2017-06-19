package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.model.Model;

public class LeaderAvverButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8136957738988127711L;
	private final int NUMLEADERS = 4;
	private final String leaderBackPath = "./image/leadercard/leadersback.jpg";
	private String username;
	private Rectangle cardDim;
	private ArrayList<CardLeader> leaders;
	
	public LeaderAvverButton(String username, double resizeFactor, java.awt.Color c, ArrayList<CardLeader> leaders){
		super(username + " leaders");
		this.username = username;
		this.leaders = leaders;
		cardDim = AdaptiveLayout.getCardBuildingSpace(resizeFactor/2, 0);
		this.setFont(new Font("Papyrus",Font.ITALIC + Font.BOLD , 20));
		this.setForeground(c);
		this.setText(username);
		this.addActionListener(new ShowLeaders());
	}
	
	private class ShowLeaders implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFrame frame = new JFrame(username);
			frame.setLayout(new GridLayout(2,2));
			if(leaders.size() == NUMLEADERS){
				for(int i=0; i<NUMLEADERS; i++){
					if(!leaders.get(i).isPlay())
						frame.add(MyImage.getScaledImageinLabel(leaderBackPath, cardDim));
					
				}
			}
			
			frame.setMinimumSize(new Dimension(200, 200));
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		}
	
	}
	
	public void updateAvverLeaders(Model model){
		this.leaders = model.getPlayers().get(username).getLeaders();
	}
}
