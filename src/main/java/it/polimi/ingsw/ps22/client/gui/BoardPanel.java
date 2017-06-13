package it.polimi.ingsw.ps22.client.gui;


import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;

public class BoardPanel extends JPanel{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = -5630682030714330058L;

	private final static int NUM_PLAYERS = 4;
	private JPanel spinPan = new JPanel();
	private final ImageIcon board = MyImage.createImageIcon("./image/gameboard.jpg");
	private ArrayList<ActionButton> actionSpaces = new ArrayList<ActionButton>();
	private HashMap<Integer,ArrayList<TowerPanel>> towers = new HashMap<Integer,ArrayList<TowerPanel>>();
	protected static JLabel zoomedCard;
	private PersonalBoardPanel personalBoard;
	private JLayeredPane layeredPane;
	private String username;
	private ArrayList<PlayersButton> players = new ArrayList<PlayersButton>();
	private double resizeFactor; 

	
	
	public double resizeFactor(ImageIcon c, double heightScreen){
		double factorScaleX = (double)c.getIconHeight() / (heightScreen);
		return factorScaleX;
	}
	
	public BoardPanel(double widthScreen, double heightScreen, String username, String persBonusPath, ArrayList<String> avver, ArrayList<String> personBonusPaths, ViewClient view) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	
		
		double factorScaleBoard = resizeFactor(board, heightScreen);
		this.resizeFactor = factorScaleBoard;
		
		this.username = username;
		
		layeredPane = new JLayeredPane();
		
		Image img = board.getImage();
		Rectangle dimBoard = new Rectangle(0,(int)(board.getIconWidth()/factorScaleBoard),0, (int)(board.getIconHeight()/factorScaleBoard));
		Image image = MyImage.getScaledImage(img, dimBoard);
		ImageIcon board = new ImageIcon(image);
	
        JLabel mapLabel = new JLabel(board);
        mapLabel.setBounds(0, 0, board.getIconWidth(), board.getIconHeight());
        System.out.println(mapLabel.getIcon());
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
		
		CouncilButton council = new CouncilButton(0, username, AdaptiveLayout.getCouncilPalaceSpace(factorScaleBoard), new MarketListener(view));
		addActionPanelToLayeredPane(council);
	
		FamiliarButton fam1 = new FamiliarButton(Color.BLACK, java.awt.Color.BLACK, new TakeFamiliarListener(actionSpaces));
		fam1.setBounds((int)(heightScreen*0.75), (int)heightScreen - 100, 100, 100);
		fam1.setText("black");
		FamiliarButton fam2 = new FamiliarButton(Color.ORANGE, java.awt.Color.ORANGE,new TakeFamiliarListener(actionSpaces));
		fam2.setBounds((int)(heightScreen*0.75) + 100, (int)heightScreen - 100, 100, 100);
		fam2.setText("orange");
		FamiliarButton fam3 = new FamiliarButton(Color.WHITE, java.awt.Color.WHITE, new TakeFamiliarListener(actionSpaces));
		fam3.setBounds((int)(heightScreen*0.75) + 200, (int)heightScreen - 100, 100, 100);
		fam3.setText("white");
		FamiliarButton fam4 = new FamiliarButton(Color.NEUTRAL, java.awt.Color.lightGray, new TakeFamiliarListener(actionSpaces));
		fam4.setBounds((int)(heightScreen*0.75) + 300, (int)heightScreen - 100, 100, 100);
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
			TowerPanel tower1 = new TowerPanel(AdaptiveLayout.getCardTerritorySpace(factorScaleBoard, i),"Territory",i );
			TowerButton space1 = new TowerButton(i, username, AdaptiveLayout.getFamiliarSpace(factorScaleBoard, 0, i), new TerritoryListener(view),"Territory");
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
			
			TowerPanel tower4 = new TowerPanel(AdaptiveLayout.getCardVentureSpace(factorScaleBoard, i), "Venture", i);
			TowerButton space4 = new TowerButton(i, username, AdaptiveLayout.getFamiliarSpace(factorScaleBoard, 3, i), new VentureListener(view), "Venture");
			addActionPanelToLayeredPane(space4);
			towers.get(3).add(tower4);
			layeredPane.add(tower4, new Integer(30));
		}
		
		
		
		/*
		HashMap<Integer,ArrayList<String>> map = new HashMap<Integer,ArrayList<String>>();
		map.put(0,new ArrayList<String>());
		map.put(1,new ArrayList<String>());
		map.put(2,new ArrayList<String>());
		map.put(3,new ArrayList<String>());
		

		map.get(0).add("./image/devcards_f_en_c_1.png");
		map.get(0).add("./image/devcards_f_en_c_2.png");
		map.get(0).add("./image/devcards_f_en_c_5.png");
		map.get(0).add("./image/devcards_f_en_c_4.png");
		
		map.get(1).add("./image/devcards_f_en_c_49.png");
		map.get(1).add("./image/devcards_f_en_c_50.png");
		map.get(1).add("./image/devcards_f_en_c_51.png");
		map.get(1).add("./image/devcards_f_en_c_52.png");
		
		map.get(2).add("./image/devcards_f_en_c_25.png");
		map.get(2).add("./image/devcards_f_en_c_26.png");
		map.get(2).add("./image/devcards_f_en_c_27.png");
		map.get(2).add("./image/devcards_f_en_c_28.png");
		
		map.get(3).add("./image/devcards_f_en_c_73.png");
		map.get(3).add("./image/devcards_f_en_c_74.png");
		map.get(3).add("./image/devcards_f_en_c_75.png");
		map.get(3).add("./image/devcards_f_en_c_76.png");
		this.setCards(map);
		*/
		this.setBackground(new java.awt.Color(55, 55, 55));
		
		personalBoard = new PersonalBoardPanel(widthScreen, heightScreen, username, persBonusPath);
	
		layeredPane.add(personalBoard, new Integer(400),0);
		
		spinPan.setBounds((int)(heightScreen*0.75) + personalBoard.getWidth(), (int)(heightScreen/2.5), 
				(int)(widthScreen - (heightScreen*0.75 + personalBoard.getWidth())),  
					(int)(widthScreen - (heightScreen*0.75 + personalBoard.getWidth()))/3);
		spinFamiliar();
		
		for(int i = 0 ; i < NUM_PLAYERS - 1; i++){
			PlayersButton b1 = new PlayersButton(widthScreen, heightScreen, avver.get(i) ,i, personBonusPaths.get(i));
			players.add(b1);
			layeredPane.add(b1, new Integer(40));
		}
		
		this.add(layeredPane);
        
        
	}
	

	
	private void spinFamiliar(){
		JLabel spinLab = new JLabel("familiar");
		spinPan.add(spinLab);
		SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);  
		JSpinner spinner = new JSpinner(model);
		spinLab.setLabelFor(spinner);
		spinPan.add(spinner);
		spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int value = (int) spinner.getValue();
				for(int i= 0; i < actionSpaces.size(); i++)
					actionSpaces.get(i).setNumServants(value);
				
			}
		});
		spinPan.setBackground(java.awt.Color.GRAY);
		layeredPane.add(spinPan, new Integer(2000),0);
	}
	
	private void addActionPanelToLayeredPane(ActionButton c){
		actionSpaces.add(c);
		layeredPane.add(c, new Integer(30));
	}

	
	//setta le carte nei rispettivi bottoni ed aggiunge i mouse listener per lo zoom
	private void setCards(HashMap<Integer, ArrayList<String>> cardsPaths){
		for(Integer tower: cardsPaths.keySet()){
			ArrayList<String> paths = cardsPaths.get(tower);
			ArrayList<TowerPanel> panels = towers.get(tower);
			for(int i=0; i < panels.size(); i++){
				panels.get(i).setCard(paths.get(i));
				panels.get(i).b.addMouseListener(new MyMouse(zoomedCard, paths.get(i)));
			}
		}
	}
	
	public void updateBoard(Model model){
		updateActionSpaces(model);
		updateTowers(model);
		updatePersonalBoard(model);
		updatePlayers(model);
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
		HashMap<Integer, ArrayList<Player>> tempPlay = new HashMap<Integer, ArrayList<Player>>();
		
		for(String user: model.getPlayers().keySet()){
			int qty = model.getPlayers().get(user).getSpecificResource("FaithPoint").getQuantity();
			if(!tempPlay.containsKey(qty))
				tempPlay.put(qty, new ArrayList<Player>());
			tempPlay.get(qty).add(model.getPlayers().get(user));
		}
		
		for(Integer qty: tempPlay.keySet()){
			FaithTrackLabel lab = new FaithTrackLabel(resizeFactor, qty, tempPlay.get(qty));
			lab.update(model);
			layeredPane.add(lab, new Integer(100));
		}
	}
	
	
}
