package it.polimi.ingsw.ps22.client.gui;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class PersonalBoardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6100463989920230583L;

	private static final int NUM_TOWERS = 4;
	JLayeredPane personalBoard = new JLayeredPane();
	HashMap<Integer, ArrayList<TowerPanel>> cards = new HashMap<Integer, ArrayList<TowerPanel>>();
	double resizeFactor;
	private final ImageIcon personal = MyImage.createImageIcon("./image/punchboard_f_c_05.jpg");
	
	
	public PersonalBoardPanel(double widthScreen, double heightScreen, String username){
		
		double factorScalePersonalBoard = resizeFactor(personal, heightScreen);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		resizeFactor = factorScalePersonalBoard;
		
		Image img = personal.getImage();
		Rectangle dimBoard = new Rectangle((int)(heightScreen*0.75),(int)(personal.getIconWidth()/factorScalePersonalBoard + heightScreen*0.75),0, (int)(personal.getIconHeight()/factorScalePersonalBoard));
		Image image = MyImage.getScaledImage(img, dimBoard);
		ImageIcon board = new ImageIcon(image);
		
		JLabel mapLabel = new JLabel(board);
	    mapLabel.setBounds(0, 0, board.getIconWidth(), board.getIconHeight());
	    personalBoard.add(mapLabel, new Integer(30), 0);
	    
	    for(int i = 0; i < NUM_TOWERS; i++){
	    	cards.put(i, new ArrayList<TowerPanel>());
	    }
	    
	    TowerPanel card = new TowerPanel(new Rectangle(0, 100, 0, 200));
	    card.setCard("./image/devcards_f_en_c_76.png");
	    personalBoard.add(card, new Integer(2000));
	  
	    this.setBounds((int)(heightScreen*0.75), 0,(int)(personal.getIconWidth()/factorScalePersonalBoard), (int)(personal.getIconHeight()/factorScalePersonalBoard) + 200);
	    
	    this.setBackground(new Color(60, 60, 60));
	    this.add(personalBoard);		
	    
		
	}
	
	public double resizeFactor(ImageIcon c, double heightScreen){
		double factorScaleX = (double)c.getIconHeight() / (0.5*heightScreen);
		return factorScaleX;
	}
	
	//aggiorna le carte della personal board. Le misure vanno riviste
	public void updateCards(HashMap<Integer, ArrayList<String>> newcards){
		for(Integer tower: newcards.keySet()){
			ArrayList<String> paths = newcards.get(tower);
			for(int i = 0; i < paths.size() ; i++){
				TowerPanel card = new TowerPanel(AdaptiveLayout.getCardDevelopmentSpace(resizeFactor, tower, i));
				this.add(card);
			}
		}
	}
}
