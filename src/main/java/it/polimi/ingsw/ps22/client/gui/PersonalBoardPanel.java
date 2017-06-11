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

	private static final int NUM_SLOT = 6;
	private static final int NUM_TOWERS = 4;
	JLayeredPane personalBoard = new JLayeredPane();
	HashMap<Integer, ArrayList<TowerPanel>> cards = new HashMap<Integer, ArrayList<TowerPanel>>();
	double resizeFactor;
	private ImageIcon personalBonus = MyImage.createImageIcon("./image/personalBonus/personalbonus1.png");
	private final ImageIcon personal = MyImage.createImageIcon("./image/PersonalBoard.png");
	private JLabel coinLabel;
	private JLabel stoneLabel;
	private JLabel servantLabel;
	private JLabel woodLabel;
	
	
	public PersonalBoardPanel(double widthScreen, double heightScreen, String username){
		
		double factorScalePersonalBoard = resizeFactor(personal, heightScreen);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		resizeFactor = factorScalePersonalBoard;
	    
	    Image imgBonus = personalBonus.getImage();
	    Rectangle dimBonus = new Rectangle((int)(heightScreen*0.75) ,(int)(personalBonus.getIconWidth()/factorScalePersonalBoard + heightScreen*0.75),0, (int)(personalBonus.getIconHeight()/factorScalePersonalBoard));
		Image imageBonus = MyImage.getScaledImage(imgBonus, dimBonus);
		ImageIcon personalB = new ImageIcon(imageBonus);		
	    JLabel bonusLabel = new JLabel(personalB);
	    bonusLabel.setBounds(0, 0, personalB.getIconWidth(), personalB.getIconHeight());
 	    personalBoard.add(bonusLabel, new Integer(30), 0);	    		
	    
		Image img = personal.getImage();
		Rectangle dimBoard = new Rectangle((int)(heightScreen*0.75) + personalBonus.getIconWidth() ,(int)(personal.getIconWidth()/factorScalePersonalBoard + heightScreen*0.75 + personalBonus.getIconWidth()),0, (int)(personal.getIconHeight()/factorScalePersonalBoard));
		Image image = MyImage.getScaledImage(img, dimBoard);
		ImageIcon board = new ImageIcon(image);
		
		JLabel mapLabel = new JLabel(board);
	    mapLabel.setBounds(bonusLabel.getWidth(), 0, board.getIconWidth(), board.getIconHeight());
	    personalBoard.add(mapLabel, new Integer(30), 0);
	    
	    for(int i = 0; i < NUM_TOWERS; i++){
	    	cards.put(i, new ArrayList<TowerPanel>());
	    }
	    
	    for(int i = 1; i < NUM_SLOT + 1; i++){
	    	personalBoard.add(new TowerPanel(PersonalBoardAdaptive.getVentureCardSlot(resizeFactor, i)), new Integer(40));
	    	personalBoard.add(new TowerPanel(PersonalBoardAdaptive.getCharacterCardSlot(resizeFactor, i)), new Integer(40));
	    	personalBoard.add(new TowerPanel(PersonalBoardAdaptive.getTerritoryCardSlot(resizeFactor, i)), new Integer(40));
	    	personalBoard.add(new TowerPanel(PersonalBoardAdaptive.getBuildingCardSlot(resizeFactor, i)), new Integer(40));
	    }
	    
	    coinLabel = new JLabel("0",JLabel.CENTER);
	    Rectangle dimCoin = PersonalBoardAdaptive.getCoinSlot(resizeFactor);
	    coinLabel.setOpaque(true);
	    coinLabel.setBounds(dimCoin.getInitx(), dimCoin.getInity(), dimCoin.getOffsetX(), dimCoin.getOffsetY());
	    personalBoard.add(coinLabel, new Integer(40));
	    
	    woodLabel = new JLabel("0", JLabel.CENTER);
	    Rectangle dimWood = PersonalBoardAdaptive.getWoodSlot(resizeFactor);
	    woodLabel.setOpaque(true);
	    woodLabel.setBounds(dimWood.getInitx(), dimWood.getInity(), dimWood.getOffsetX(), dimWood.getOffsetY());
	    personalBoard.add(woodLabel, new Integer(40));
	    
	    servantLabel = new JLabel("0", JLabel.CENTER);
	    Rectangle dimServant = PersonalBoardAdaptive.getServantSlot(resizeFactor);
	    servantLabel.setOpaque(true);
	    servantLabel.setBounds(dimServant.getInitx(), dimServant.getInity(), dimServant.getOffsetX(), dimServant.getOffsetY());
	    personalBoard.add(servantLabel, new Integer(40));
	    
	    stoneLabel = new JLabel("0", JLabel.CENTER);
	    Rectangle dimStone = PersonalBoardAdaptive.getStoneSlot(resizeFactor);
	    stoneLabel.setOpaque(true);
	    stoneLabel.setBounds(dimStone.getInitx(), dimStone.getInity(), dimStone.getOffsetX(), dimStone.getOffsetY());
	    personalBoard.add(stoneLabel, new Integer(40));
	    
	  
	    this.setBounds((int)(heightScreen*0.75), 0,(int)(personal.getIconWidth()/factorScalePersonalBoard) + bonusLabel.getWidth(),
	    		+ PersonalBoardAdaptive.getCharacterCardSlot(resizeFactor, 1).getFinaly());
	    
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
