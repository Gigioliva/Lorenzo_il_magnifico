package it.polimi.ingsw.ps22.client.gui;


import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.EndTurn;
import it.polimi.ingsw.ps22.server.player.Player;

public class BoardPanel extends JPanel{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = -5630682030714330058L;

	private int NUM_PLAYERS;
	private static final int NUM_CARDEXCOMM = 3;
	private ServantSpinner spinner = new ServantSpinner();
	private final ImageIcon board = MyImage.createImageIcon("./image/gameboard.jpg");
	private ArrayList<ActionButton> actionSpaces = new ArrayList<ActionButton>();
	 HashMap<Integer,ArrayList<TowerPanel>> towers = new HashMap<Integer,ArrayList<TowerPanel>>();
	protected static JLabel zoomedCard;
	private PersonalBoardPanel personalBoard;
	private JLayeredPane layeredPane;
	private ArrayList<PlayersButton> players = new ArrayList<PlayersButton>();
	private double resizeFactor; 
	private ArrayList<VictoryPointLabel> victory = new ArrayList<VictoryPointLabel>();
	private ArrayList<MilitaryPointLabel> military = new ArrayList<MilitaryPointLabel>();
	private ArrayList<FaithTrackLabel> faith = new ArrayList<FaithTrackLabel>();
	private JLabel dice1;
	private JLabel dice2;
	private JLabel dice3;
	private FamiliarButton fam1;
	private FamiliarButton fam2;
	private FamiliarButton fam3;
	private FamiliarButton fam4;
	private ArrayList<OrderPlayerLabel> orederPlayers = new ArrayList<OrderPlayerLabel>();

	
	
	public double resizeFactor(ImageIcon c, double heightScreen){
		double factorScaleX = (double)c.getIconHeight() / (heightScreen);
		return factorScaleX;
	}
	
	public BoardPanel(double widthScreen, double heightScreen, String username, ViewClient view, Model model) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		ArrayList<String> avver = new ArrayList<String>(model.getPlayers().keySet());
		avver.remove(username);
		
		ArrayList<String> personBonusPaths = new ArrayList<String>();
		for(String user: avver){
			personBonusPaths.add(CardPath.getPersonalBoardPathname(model.getPlayers().get(user).getPersonalBoard()));
		}
		
		this.NUM_PLAYERS = model.getPlayers().size();
		
		double factorScaleBoard = resizeFactor(board, heightScreen);
		this.resizeFactor = factorScaleBoard;
		
		
		personalBoard = new PersonalBoardPanel(widthScreen, heightScreen, username,CardPath.getPersonalBoardPathname(model.getPlayers().get(username).getPersonalBoard()));
		layeredPane = new JLayeredPane();
		
		Image img = board.getImage();
		Rectangle dimBoard = new Rectangle(0,(int)(board.getIconWidth()/factorScaleBoard),0, (int)(board.getIconHeight()/factorScaleBoard));
		Image image = MyImage.getScaledImage(img, dimBoard);
		ImageIcon board = new ImageIcon(image);
	
        JLabel mapLabel = new JLabel(board);
        mapLabel.setBounds(0, 0, board.getIconWidth(), board.getIconHeight());
        
        layeredPane.add(mapLabel, new Integer(20), 0);
	
		HarvestButton harvest1 = new HarvestButton(1, username, AdaptiveLayout.getHarvestRightSpace(factorScaleBoard), new HarvestListener(view));
		addActionPanelToLayeredPane(harvest1);
		
		HarvestButton harvest2 = new HarvestButton(0, username, AdaptiveLayout.getHarvestLeftSpace(factorScaleBoard), new HarvestListener(view));
		addActionPanelToLayeredPane(harvest2);
		
		ProductionButton prod1 = new ProductionButton(1,username, AdaptiveLayout.getProdRightSpace(factorScaleBoard), new ProductionListener(view));
		ProductionButton prod2 = new ProductionButton(0, username, AdaptiveLayout.getProdLeftSpace(factorScaleBoard), new ProductionListener(view));
		addActionPanelToLayeredPane(prod1);
		addActionPanelToLayeredPane(prod2);
		
		MarketButton mark1 = new MarketButton(0, username, AdaptiveLayout.getMarket1Space(factorScaleBoard), new MarketListener(view));
		MarketButton mark2 = new MarketButton(1, username, AdaptiveLayout.getMarket2Space(factorScaleBoard), new MarketListener(view));
		MarketButton mark3 = new MarketButton(2, username, AdaptiveLayout.getMarket3Space(factorScaleBoard), new MarketListener(view));
		MarketButton mark4 = new MarketButton(3, username, AdaptiveLayout.getMarket4Space(factorScaleBoard), new MarketListener(view));
		addActionPanelToLayeredPane(mark1);		
		addActionPanelToLayeredPane(mark2);
		addActionPanelToLayeredPane(mark3);
		addActionPanelToLayeredPane(mark4);
		
		CouncilButton council = new CouncilButton(0, username, AdaptiveLayout.getCouncilPalaceSpace(factorScaleBoard), new CouncilListener(view));
		addActionPanelToLayeredPane(council);
	
		fam1 = new FamiliarButton(Color.BLACK, java.awt.Color.BLACK, new TakeFamiliarListener(actionSpaces),username);
		Rectangle dimFam1 = PersonalBoardAdaptive.getBlackButtonSLot(personalBoard.resizeFactor);
		fam1.setBounds(personalBoard.getBounds().width, dimFam1.getInity(), dimFam1.getOffsetX(), dimFam1.getOffsetY());
		fam1.setText("black");
		
		fam2 = new FamiliarButton(Color.ORANGE, java.awt.Color.ORANGE,new TakeFamiliarListener(actionSpaces), username);
		Rectangle dimFam2 = PersonalBoardAdaptive.getOrangeButtonSLot(personalBoard.resizeFactor);
		fam2.setBounds(personalBoard.getBounds().width + dimFam2.getInitx(), dimFam2.getInity(), dimFam2.getOffsetX(), dimFam2.getOffsetY());
		fam2.setText("orange");
		
		fam3 = new FamiliarButton(Color.WHITE, java.awt.Color.WHITE, new TakeFamiliarListener(actionSpaces), username);
		Rectangle dimFam3 = PersonalBoardAdaptive.getWhiteButtonSLot(personalBoard.resizeFactor);
		fam3.setBounds(personalBoard.getBounds().width + dimFam3.getInitx(), dimFam3.getInity(), dimFam3.getOffsetX(), dimFam3.getOffsetY());
		fam3.setText("white");
		
		fam4 = new FamiliarButton(Color.NEUTRAL, java.awt.Color.lightGray, new TakeFamiliarListener(actionSpaces), username);
		Rectangle dimFam4 = PersonalBoardAdaptive.getNeutralButtonSLot(personalBoard.resizeFactor);
		fam4.setBounds(personalBoard.getBounds().width + dimFam4.getInitx(), dimFam4.getInity(), dimFam4.getOffsetX(), dimFam4.getOffsetY());
		fam4.setText("neutral");
		
		layeredPane.add(fam1, new Integer(50), 0);
		layeredPane.add(fam2, new Integer(50), 0);
		layeredPane.add(fam3, new Integer(50), 0);
		layeredPane.add(fam4, new Integer(50), 0);
		
		towers.put(0, new ArrayList<TowerPanel>());
		towers.put(1, new ArrayList<TowerPanel>());
		towers.put(2, new ArrayList<TowerPanel>());
		towers.put(3, new ArrayList<TowerPanel>());
		
		 //carta a caso (mi servono solo le dimensioni)
		Rectangle dimCard = AdaptiveLayout.getCardBuildingSpace(factorScaleBoard, 0);
		zoomedCard = new JLabel();
		zoomedCard.setBounds((int)(heightScreen*0.75),(int) heightScreen/2,(int)( dimCard.getOffsetX()*2.2),(int)( dimCard.getOffsetY()*2.2));
		layeredPane.add(zoomedCard, new Integer(2000));
		
		for(int i = 0; i < 4; i++){
			TowerPanel tower1 = new TowerPanel(AdaptiveLayout.getCardTerritorySpace(factorScaleBoard, i),"Territory",i);
			TowerButton space1 = new TowerButton(i , username, AdaptiveLayout.getFamiliarSpace(factorScaleBoard, 0, i), new TerritoryListener(view),"Territory");
			addActionPanelToLayeredPane(space1);
			towers.get(0).add(tower1);
			layeredPane.add(tower1, new Integer(30));
			
			TowerPanel tower2 = new TowerPanel(AdaptiveLayout.getCharacterSpace(factorScaleBoard, i), "Character", i);
			TowerButton space2 = new TowerButton(i,username, AdaptiveLayout.getFamiliarSpace(factorScaleBoard, 1, i), new CharacterListener(view), "Character");
			addActionPanelToLayeredPane(space2);
			towers.get(1).add(tower2);
			layeredPane.add(tower2, new Integer(30));

			TowerPanel tower3 = new TowerPanel(AdaptiveLayout.getCardBuildingSpace(factorScaleBoard, i), "Building", i);
			TowerButton space3 = new TowerButton(i, username, AdaptiveLayout.getFamiliarSpace(factorScaleBoard, 2, i), new BuildingListener(view), "Building");
			addActionPanelToLayeredPane(space3);
			towers.get(2).add(tower3);
			layeredPane.add(tower3, new Integer(30));
			
			TowerPanel tower4 = new TowerPanel(AdaptiveLayout.getCardVentureSpace(factorScaleBoard, i), "Venture", i );
			TowerButton space4 = new TowerButton(i, username, AdaptiveLayout.getFamiliarSpace(factorScaleBoard, 3, i), new VentureListener(view), "Venture");
			addActionPanelToLayeredPane(space4);
			towers.get(3).add(tower4);
			layeredPane.add(tower4, new Integer(30));
		}
		
		
		this.setBackground(new java.awt.Color(154, 92, 43));
		
	
		layeredPane.add(personalBoard, new Integer(400),0);
		
		spinner.setBounds((int)(heightScreen*0.75) + personalBoard.getWidth(), (int)(heightScreen/2.5), 
				(int)(widthScreen - (heightScreen*0.75 + personalBoard.getWidth())),  
					(int)(widthScreen - (heightScreen*0.75 + personalBoard.getWidth()))/3);
		spinServant();
		
		for(int i = 0 ; i < NUM_PLAYERS - 1; i++){
			PlayersButton b1 = new PlayersButton(widthScreen, heightScreen, avver.get(i) ,i, personBonusPaths.get(i));
			players.add(b1);
			layeredPane.add(b1, new Integer(40));
		}
		
		JButton quitTurn = new JButton("End turn");
		quitTurn.setBackground(java.awt.Color.BLACK);
		quitTurn.setForeground(java.awt.Color.WHITE);
		quitTurn.setBounds((int)widthScreen - 100,(int) heightScreen - 100,
				100, 100);
		quitTurn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.setFlag(true);
				EndTurn end = new EndTurn(username);
				view.send(end);
				
			}
		});
		layeredPane.add(quitTurn, new Integer(2000));
		
		
		dice1 = new JLabel();
		
		Rectangle rec = AdaptiveLayout.getOrangeDiceSpace(factorScaleBoard);
		dice1.setBounds(rec.getInitx(), rec.getInity(), rec.getOffsetX(), rec.getOffsetY());
		layeredPane.add(dice1, new Integer(400));
		
		Font font = new Font("Papyrus", Font.ITALIC + Font.BOLD , dice1.getHeight());
		dice1.setFont(font);
		dice1.setForeground(java.awt.Color.BLACK);
		dice1.setHorizontalAlignment(SwingConstants.CENTER);
		
		dice2 = new JLabel();
		Rectangle rec2 = AdaptiveLayout.getBlackDiceSpace(factorScaleBoard);
		dice2.setBounds(rec2.getInitx(), rec2.getInity(), rec2.getOffsetX(), rec2.getOffsetY());
		layeredPane.add(dice2, new Integer(400));
		dice2.setFont(font);
		dice2.setForeground(java.awt.Color.WHITE);
		dice2.setHorizontalAlignment(SwingConstants.CENTER);
		
		dice3 = new JLabel();
		Rectangle rec3 = AdaptiveLayout.getWhiteDiceSpace(factorScaleBoard);
		dice3.setBounds(rec3.getInitx(), rec3.getInity(), rec3.getOffsetX(), rec3.getOffsetY());
		layeredPane.add(dice3, new Integer(400));
		dice3.setFont(font);
		dice3.setForeground(java.awt.Color.BLACK);
		dice3.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i = 0; i<NUM_PLAYERS; i++){
			OrderPlayerLabel lab = new OrderPlayerLabel(i, resizeFactor);
			lab.setVisible(false);
			orederPlayers.add(lab);
			layeredPane.add(lab, new Integer(200));
		}
		
		JLabel sfondo = MyImage.getScaledImageinLabel("./image/sfondo.png", new Rectangle(0,  (int)widthScreen + 300, 0,(int) heightScreen + 300));
		sfondo.setBounds(0,0,(int) widthScreen,(int) heightScreen);
		//sfondo.setText("Ciao");
		layeredPane.add(sfondo, new Integer(0));
		
		this.add(layeredPane);
        
        
	}
	

	
	private void spinServant(){
		
		spinner.getSpin().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = (int) spinner.getSpin().getValue();
				for(int i= 0; i < actionSpaces.size(); i++)
					actionSpaces.get(i).setNumServants(value);
				
			}
		});
		layeredPane.add(spinner, new Integer(2000),0);
	}
	
	private void addActionPanelToLayeredPane(ActionButton c){
		actionSpaces.add(c);
		layeredPane.add(c, new Integer(30));
	}
	
	public void updateBoard(Model model){
		updateActionSpaces(model);
		updateTowers(model);
		updatePersonalBoard(model);
		updatePlayers(model);
		updateFaithTrack(model);
		updateMilitaryTrack(model);
		updateVictoryTrack(model);
		updateDices(model);
		updateOrderPlayers(model);
		updateCardExcomm(model);
		updateFamiliars(model);
		repaint();
	}
	
	private void updateActionSpaces(Model model){
		for(ActionButton button: actionSpaces){
			button.updateButton(model);
		}
	}
	
	private void updateTowers(Model model){
		for(Integer tower: towers.keySet()){
			for(TowerPanel pan: towers.get(tower)){
				pan.update(model);
			}
		}
	}
	
	private void updatePersonalBoard(Model model){
		personalBoard.update(model);
	}
	
	private void updatePlayers(Model model){
		for(PlayersButton button: players){
			button.update(model);
		}
	}
	
	private void updateFaithTrack(Model model){
		
		HashMap<Integer, ArrayList<Player>> tempPlay = updatePointTrack(model, "FaithPoint");
		
		for(FaithTrackLabel lab: faith){
			layeredPane.remove(lab);
		}
		faith.clear();
		
		for(Integer qty: tempPlay.keySet()){
			FaithTrackLabel lab = new FaithTrackLabel(resizeFactor, qty, tempPlay.get(qty));
			lab.update(model);
			faith.add(lab);
			layeredPane.add(lab, new Integer(100));
		}
	}
	
	private void updateMilitaryTrack(Model model){
		
		HashMap<Integer, ArrayList<Player>> tempPlay = updatePointTrack(model, "MilitaryPoint");
		for(MilitaryPointLabel lab: military){
			layeredPane.remove(lab);
		}
		military.clear();
		
		for(Integer qty: tempPlay.keySet()){
			MilitaryPointLabel lab = new MilitaryPointLabel(resizeFactor, qty, tempPlay.get(qty));
			lab.update(model);
			military.add(lab);
			layeredPane.add(lab, new Integer(100));
		}
	}
	
	private void updateVictoryTrack(Model model){
		
		HashMap<Integer, ArrayList<Player>> tempPlay = updatePointTrack(model, "VictoryPoint");
		for(VictoryPointLabel lab: victory){
			layeredPane.remove(lab);
		}
		victory.clear();
		for(Integer qty: tempPlay.keySet()){
			VictoryPointLabel lab = new VictoryPointLabel(resizeFactor, qty, tempPlay.get(qty));
			victory.add(lab);
			lab.update(model);
			layeredPane.add(lab, new Integer(100));
		}
	}
	
	private HashMap<Integer, ArrayList<Player>> updatePointTrack(Model model, String type){
		HashMap<Integer, ArrayList<Player>> tempPlay = new HashMap<Integer, ArrayList<Player>>();
		
		for(String user: model.getPlayers().keySet()){
			int qty = model.getPlayers().get(user).getSpecificResource(type).getQuantity();
			if(!tempPlay.containsKey(qty))
				tempPlay.put(qty, new ArrayList<Player>());
			tempPlay.get(qty).add(model.getPlayers().get(user));
		}
		return tempPlay;
	}
	
	private void updateDices(Model model){
		dice1.setText(String.valueOf(model.getBoard().getDice().getDice(Color.ORANGE)));
		dice2.setText(String.valueOf(model.getBoard().getDice().getDice(Color.BLACK)));
		dice3.setText(String.valueOf(model.getBoard().getDice().getDice(Color.WHITE)));
	}
	
	private void updateOrderPlayers(Model model){
		for(int i=0; i< NUM_PLAYERS; i++){
			OrderPlayerLabel lab = orederPlayers.get(i);
			lab.updateOrderLabel(model);
		}
	}
	
	private void updateCardExcomm(Model model){
		
		for(int i = 0; i < NUM_CARDEXCOMM; i++){
			String path = CardPath.getExcommCardPathname(model.getBoard().getChurch((i+1)*2).getCardExcomm());
			JLabel exCardLabel = MyImage.getScaledImageinLabel(path, AdaptiveLayout.getChurchSpace(resizeFactor, i + 1));
			layeredPane.add(exCardLabel, new Integer(200));
		}
	
	}
	
	private void updateFamiliars(Model model){
		fam1.updateFamiliar(model);
		fam2.updateFamiliar(model);
		fam3.updateFamiliar(model);
		fam4.updateFamiliar(model);
	}
	
	
}
