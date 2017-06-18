package it.polimi.ingsw.ps22.client.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import it.polimi.ingsw.ps22.server.card.DevelopmentCard;
import it.polimi.ingsw.ps22.server.model.Model;

public class PersonalBoardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6100463989920230583L;

	private static final int NUM_SLOT = 6;
	private static final int NUM_TOWERS = 4;
	JLayeredPane personalBoard = new JLayeredPane();
	HashMap<Integer, ArrayList<TowerPanel>> cards = new HashMap<Integer, ArrayList<TowerPanel>>();
	double resizeFactor;
	private final ImageIcon personal = MyImage.createImageIcon("./image/PersonalBoard.png");
	private JLabel coinLabel;
	private JLabel stoneLabel;
	private JLabel servantLabel;
	private JLabel woodLabel;
	private String username;
	
	
	public PersonalBoardPanel(double widthScreen, double heightScreen, String username, String pathPersonalBonus){
		
		double factorScalePersonalBoard = resizeFactor(personal, heightScreen);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.username = username;
		
		resizeFactor = factorScalePersonalBoard;
		
		ImageIcon personalBonus = MyImage.createImageIcon(pathPersonalBonus);
	    
	    Image imgBonus = personalBonus.getImage();
	    Rectangle dimBonus = new Rectangle((int)(heightScreen*0.71) ,(int)(personalBonus.getIconWidth()/factorScalePersonalBoard + heightScreen*0.71),0, (int)(personalBonus.getIconHeight()/factorScalePersonalBoard));
		Image imageBonus = MyImage.getScaledImage(imgBonus, dimBonus);
		ImageIcon personalB = new ImageIcon(imageBonus);		
	    JLabel bonusLabel = new JLabel(personalB);
	    bonusLabel.setBounds(0, 0, personalB.getIconWidth(), personalB.getIconHeight());
 	    personalBoard.add(bonusLabel, new Integer(30), 0);	
	    
		Image img = personal.getImage();
		Rectangle dimBoard = new Rectangle((int)(heightScreen*0.71) + personalBonus.getIconWidth() ,(int)(personal.getIconWidth()/factorScalePersonalBoard + heightScreen*0.71 + personalBonus.getIconWidth()),0, (int)(personal.getIconHeight()/factorScalePersonalBoard));
		Image image = MyImage.getScaledImage(img, dimBoard);
		ImageIcon board = new ImageIcon(image);
		
		JLabel mapLabel = new JLabel(board);
	    mapLabel.setBounds(bonusLabel.getWidth(), 0, board.getIconWidth(), board.getIconHeight());
	    personalBoard.add(mapLabel, new Integer(30), 0);
	    
	    for(int i = 0; i < NUM_TOWERS; i++){
	    	cards.put(i, new ArrayList<TowerPanel>());
	    }
	    
	    for(int i = 1; i < NUM_SLOT + 1; i++){
	    	TowerPanel p = new TowerPanel(PersonalBoardAdaptive.getVentureCardSlot(resizeFactor, i), "Venture" , i);
	    	personalBoard.add(p, new Integer(40));
	    	cards.get(3).add(p);
	    	TowerPanel p1 = new TowerPanel(PersonalBoardAdaptive.getCharacterCardSlot(resizeFactor, i), "Character" , i);
	    	personalBoard.add(p1, new Integer(40));
	    	cards.get(1).add(p1);
	    	TowerPanel p2 = new TowerPanel(PersonalBoardAdaptive.getTerritoryCardSlot(resizeFactor, i), "Territory" , i);
	    	personalBoard.add(p2, new Integer(40));
	    	cards.get(0).add(p2);
	    	TowerPanel p3 = new TowerPanel(PersonalBoardAdaptive.getBuildingCardSlot(resizeFactor, i), "Building", i);
	    	personalBoard.add(p3, new Integer(40));
	    	cards.get(2).add(p3);
	    }
	    

	    coinLabel = new JLabel("0",JLabel.CENTER);
	    Rectangle dimCoin = PersonalBoardAdaptive.getCoinSlot(resizeFactor);
	    coinLabel.setForeground(java.awt.Color.WHITE);
	    coinLabel.setOpaque(false);
	    coinLabel.setBounds(dimCoin.getInitx(), dimCoin.getInity(), dimCoin.getOffsetX(), dimCoin.getOffsetY());
	    Font font = new Font("Papyrus", Font.ITALIC + Font.BOLD , coinLabel.getHeight());
	    coinLabel.setFont(font);
	    personalBoard.add(coinLabel, new Integer(40));
	    
	    woodLabel = new JLabel("0", JLabel.CENTER);
	    Rectangle dimWood = PersonalBoardAdaptive.getWoodSlot(resizeFactor);
	    woodLabel.setOpaque(false);
	    woodLabel.setBounds(dimWood.getInitx(), dimWood.getInity(), dimWood.getOffsetX(), dimWood.getOffsetY());
	    woodLabel.setFont(font);
	    woodLabel.setForeground(java.awt.Color.BLACK);
	    personalBoard.add(woodLabel, new Integer(40));
	    
	    servantLabel = new JLabel("0", JLabel.CENTER);
	    servantLabel.setFont(font);
	    Rectangle dimServant = PersonalBoardAdaptive.getServantSlot(resizeFactor);
	    servantLabel.setOpaque(false);
	    servantLabel.setBounds(dimServant.getInitx(), dimServant.getInity(), dimServant.getOffsetX(), dimServant.getOffsetY());
	    servantLabel.setForeground(java.awt.Color.BLACK);
	    personalBoard.add(servantLabel, new Integer(40));
	    
	    stoneLabel = new JLabel("0", JLabel.CENTER);
	    stoneLabel.setFont(font);
	    Rectangle dimStone = PersonalBoardAdaptive.getStoneSlot(resizeFactor);
	    stoneLabel.setOpaque(false);
	    stoneLabel.setBounds(dimStone.getInitx(), dimStone.getInity(), dimStone.getOffsetX(), dimStone.getOffsetY());
	    stoneLabel.setForeground(java.awt.Color.BLACK);
	    personalBoard.add(stoneLabel, new Integer(40));
	    
	  
	    this.setBounds((int)(heightScreen*0.71), 0,(int)(personal.getIconWidth()/factorScalePersonalBoard) + bonusLabel.getWidth(),
	    		+ PersonalBoardAdaptive.getCharacterCardSlot(resizeFactor, 1).getFinaly());
	    
	    this.setOpaque(false);;
	    this.add(personalBoard);		
	    
		
	}
	
	
	public double resizeFactor(ImageIcon c, double heightScreen){
		double factorScaleX = (double)c.getIconHeight() / (0.5*heightScreen);
		return factorScaleX;
	}
	
	
	private void updateTerritory(ArrayList<DevelopmentCard> newcards){
		for(int i=0; i<newcards.size(); i++){
			cards.get(0).get(i).setCard(CardPath.getDevCardPathname(newcards.get(i)));;
		}
	}
	
	private void updateCharacter(ArrayList<DevelopmentCard> newcards){
		for(int i=0; i<newcards.size(); i++){
			cards.get(1).get(i).setCard(CardPath.getDevCardPathname(newcards.get(i)));;
		}
	}
	
	private void updateBuilding(ArrayList<DevelopmentCard> newcards){
		for(int i=0; i<newcards.size(); i++){
			cards.get(2).get(i).setCard(CardPath.getDevCardPathname(newcards.get(i)));;
		}
	}
	
	private void updateVenture(ArrayList<DevelopmentCard> newcards){
		for(int i=0; i<newcards.size(); i++){
			cards.get(3).get(i).setCard(CardPath.getDevCardPathname(newcards.get(i)));;
		}
	}

	
	public void update(Model model){
		coinLabel.setText((String.valueOf(model.getPlayers().get(username).getSpecificResource("Coin").getQuantity())));
		stoneLabel.setText((String.valueOf(model.getPlayers().get(username).getSpecificResource("Stone").getQuantity())));
		servantLabel.setText((String.valueOf(model.getPlayers().get(username).getSpecificResource("Servant").getQuantity())));
		woodLabel.setText((String.valueOf(model.getPlayers().get(username).getSpecificResource("Wood").getQuantity())));
		
		ArrayList<DevelopmentCard> terr = model.getPlayers().get(username).getDevelopmentCard("Territory");
		ArrayList<DevelopmentCard> chara = model.getPlayers().get(username).getDevelopmentCard("Character");
		ArrayList<DevelopmentCard> building = model.getPlayers().get(username).getDevelopmentCard("Building");
		ArrayList<DevelopmentCard> venture = model.getPlayers().get(username).getDevelopmentCard("Venture");
		
		updateBuilding(building);
		updateCharacter(chara);
		updateTerritory(terr);
		updateVenture(venture);
	}
	
	public JLayeredPane getLayeredPane(){
		return this.personalBoard;
	}
}
