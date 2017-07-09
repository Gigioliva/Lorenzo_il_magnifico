package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.model.Model;

public class PlayersButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4395364097597264389L;
	private PersonalBoardPanel personalBoard;
	private ArrayList<CardLeader> leaders;
	private double resizeFactor;
	private java.awt.Color c;
	private String username;
	private transient PersonalBoardAdaptive layoutPersonal = PersonalBoardAdaptive.instance();
	
	public PlayersButton(double widthScreen, double heightScreen, double resizeFactor, String username, int pos, String pathPersonalBonus,
			java.awt.Color c, ArrayList<CardLeader> leaders ){
		
		personalBoard = new PersonalBoardPanel(widthScreen, heightScreen, username, pathPersonalBonus);
		
		this.leaders = leaders;
		this.resizeFactor = resizeFactor;
		this.c = c;
		this.username = username;
		Rectangle dim = layoutPersonal.getPlayerButtonSLot(personalBoard.resizeFactor, pos);
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
			frame.setMinimumSize(new Dimension((int)(3800/resizeFactor),(int)(3400/resizeFactor)));
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
			JLabel sfondo = MyImage.getScaledImageinLabel("./image/sfondo.png",
					new Rectangle(0, (int)(4200/resizeFactor), 0, (int) (4000/resizeFactor)));
			sfondo.setBounds(-100, -100, (int) (5000/resizeFactor), (int) (5000/resizeFactor));
			p.add(sfondo, new Integer(0));
			
			frame.add(panel);
			frame.setMinimumSize(new Dimension(600, 600));
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		}
	
		
	}
}
