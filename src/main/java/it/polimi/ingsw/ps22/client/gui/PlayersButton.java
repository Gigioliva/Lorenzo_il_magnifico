package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.model.Model;

public class PlayersButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4395364097597264389L;
	PersonalBoardPanel personalBoard;
	ArrayList<CardLeader> leaders;
	 double resizeFactor;
	 java.awt.Color c;
	 String username;
	
	public PlayersButton(double widthScreen, double heightScreen, double resizeFactor, String username, int pos, String pathPersonalBonus,
			java.awt.Color c, ArrayList<CardLeader> leaders ){
		
		personalBoard = new PersonalBoardPanel(widthScreen, heightScreen, username, pathPersonalBonus);
		
		this.leaders = leaders;
		this.resizeFactor = resizeFactor;
		this.c = c;
		this.username = username;
		Rectangle dim = PersonalBoardAdaptive.getPlayerButtonSLot(personalBoard.resizeFactor, pos);
		this.setBounds(dim.getInitx() + personalBoard.getBounds().width, dim.getInity(), dim.getOffsetX(), dim.getOffsetY()); 
		
		this.setFont(new Font("Papyrus",Font.ITALIC + Font.BOLD ,(int) (this.getHeight()/3) ));
		this.setForeground(c);
		this.setText(username);
		
		this.addActionListener(new ShowPersonalBoard(username));
		
	}
	
	public void update(Model model){
		personalBoard.update(model);
		this.leaders = model.getPlayers().get(username).getLeaders();
	}
	
	private class ShowPersonalBoard implements ActionListener{
		
		
		private  String username;
		
		public ShowPersonalBoard(String username) {
			this.username = username;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFrame frame = new JFrame(username);
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			JLayeredPane p = new JLayeredPane();
			
			LeaderAvverButton b = new LeaderAvverButton(username, resizeFactor, c, leaders);
			
			personalBoard.setBounds(0,0,personalBoard.getWidth(), personalBoard.getHeight());
			
			b.setBounds(personalBoard.getWidth(), 0, b.cardDim.getOffsetX()*3, b.cardDim.getOffsetY()*3);
			p.add(personalBoard, new Integer(1));
			p.add(b, new Integer(2));
			
		
			panel.add(p);
			//frame.setBounds(0,0,personalBoard.getWidth(), personalBoard.getHeight());
			
			frame.add(panel);
			frame.setMinimumSize(new Dimension(600, 600));
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		}
	
		
	}
}
