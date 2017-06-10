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

import it.polimi.ingsw.ps22.server.model.Color;

public class BoardPanel extends JPanel{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = -5630682030714330058L;

	private final static int NUM_PLAYERS = 4;
	private JPanel spinPan = new JPanel();
	private final ImageIcon board = MyImage.createImageIcon("./image/gameboard.jpg");
	private ArrayList<ActionPanel> actionSpaces = new ArrayList<ActionPanel>();
	private HashMap<Integer,ArrayList<TowerPanel>> towers = new HashMap<Integer,ArrayList<TowerPanel>>();
	private JLabel zoomedCard;
	private PersonalBoardPanel personalBoard;
	private JLayeredPane layeredPane;

	
	
	public double resizeFactor(ImageIcon c, double heightScreen){
		double factorScaleX = (double)c.getIconHeight() / (heightScreen);
		return factorScaleX;
	}
	
	public BoardPanel(double widthScreen, double heightScreen, String username) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	
		
		double factorScaleBoard = resizeFactor(board, heightScreen);
		
		layeredPane = new JLayeredPane();
		
		Image img = board.getImage();
		Rectangle dimBoard = new Rectangle(0,(int)(board.getIconWidth()/factorScaleBoard),0, (int)(board.getIconHeight()/factorScaleBoard));
		Image image = MyImage.getScaledImage(img, dimBoard);
		ImageIcon board = new ImageIcon(image);
	
        JLabel mapLabel = new JLabel(board);
        mapLabel.setBounds(0, 0, board.getIconWidth(), board.getIconHeight());
        System.out.println(mapLabel.getIcon());
        layeredPane.add(mapLabel, new Integer(20), 0);
	
		ActionPanel harvest1 = new ActionPanel(username, AdaptiveLayout.getHarvestRightSpace(factorScaleBoard), new HarvestListener(), 1);
		addActionPanelToLayeredPane(harvest1);
		
		ActionPanel harvest2 = new ActionPanel(username, AdaptiveLayout.getHarvestLeftSpace(factorScaleBoard), new HarvestListener(), 0);
		addActionPanelToLayeredPane(harvest2);
		
		ActionPanel prod1 = new ActionPanel(username, AdaptiveLayout.getProdRightSpace(factorScaleBoard), new ProductionListener(), 1);
		ActionPanel prod2 = new ActionPanel(username, AdaptiveLayout.getProdLeftSpace(factorScaleBoard), new ProductionListener(), 0);
		addActionPanelToLayeredPane(prod1);
		addActionPanelToLayeredPane(prod2);
		
		ActionPanel mark1 = new ActionPanel(username, AdaptiveLayout.getMarket1Space(factorScaleBoard), new MarketListener(), 1);
		ActionPanel mark2 = new ActionPanel(username, AdaptiveLayout.getMarket2Space(factorScaleBoard), new MarketListener(), 2);
		ActionPanel mark3 = new ActionPanel(username, AdaptiveLayout.getMarket3Space(factorScaleBoard), new MarketListener(), 3);
		ActionPanel mark4 = new ActionPanel(username, AdaptiveLayout.getMarket4Space(factorScaleBoard), new MarketListener(), 4);
		addActionPanelToLayeredPane(mark1);		
		addActionPanelToLayeredPane(mark2);
		addActionPanelToLayeredPane(mark3);
		addActionPanelToLayeredPane(mark4);
		
		ActionPanel council = new ActionPanel(username, AdaptiveLayout.getCouncilPalaceSpace(factorScaleBoard), new MarketListener(), 0);
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
		layeredPane.add(fam1, new Integer(50), 0);
		layeredPane.add(fam2, new Integer(50), 0);
		layeredPane.add(fam3, new Integer(50), 0);
		
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
			TowerPanel tower1 = new TowerPanel(AdaptiveLayout.getCardTerritorySpace(factorScaleBoard, i));
			ActionPanel space1 = new ActionPanel(username, AdaptiveLayout.getFamiliarSpace(factorScaleBoard, 0, i), new TerritoryListener(), i);
			addActionPanelToLayeredPane(space1);
			towers.get(0).add(tower1);
			layeredPane.add(tower1, new Integer(30));
			
			TowerPanel tower2 = new TowerPanel(AdaptiveLayout.getCharacterSpace(factorScaleBoard, i));
			ActionPanel space2 = new ActionPanel(username, AdaptiveLayout.getFamiliarSpace(factorScaleBoard, 1, i), new CharacterListener(), i);
			addActionPanelToLayeredPane(space2);
			towers.get(1).add(tower2);
			layeredPane.add(tower2, new Integer(30));

			TowerPanel tower3 = new TowerPanel(AdaptiveLayout.getCardBuildingSpace(factorScaleBoard, i));
			ActionPanel space3 = new ActionPanel(username, AdaptiveLayout.getFamiliarSpace(factorScaleBoard, 2, i), new BuildingListener(), i);
			addActionPanelToLayeredPane(space3);
			towers.get(2).add(tower3);
			layeredPane.add(tower3, new Integer(30));
			
			TowerPanel tower4 = new TowerPanel(AdaptiveLayout.getCardVentureSpace(factorScaleBoard, i));
			ActionPanel space4 = new ActionPanel(username, AdaptiveLayout.getFamiliarSpace(factorScaleBoard, 3, i), new VentureListener(), i);
			addActionPanelToLayeredPane(space4);
			towers.get(3).add(tower4);
			layeredPane.add(tower4, new Integer(30));
		}
		
		
		
		
		spinPan.setBounds((int)widthScreen - 500, (int)heightScreen - 500, board.getIconWidth()- 400, board.getIconHeight() - 700);
		spinFamiliar();
		
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
		
		this.setBackground(new java.awt.Color(55, 55, 55));
		
		personalBoard = new PersonalBoardPanel(widthScreen, heightScreen, username);
	
		layeredPane.add(personalBoard, new Integer(400),0);
		
		for(int i = 0 ; i < NUM_PLAYERS - 1; i++){
			PlayersButton b1 = new PlayersButton(widthScreen, heightScreen, "Gianni " + i,i);
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
		layeredPane.add(spinPan, new Integer(2000),0);
	}
	
	private void addActionPanelToLayeredPane(ActionPanel c){
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
	
	
}
