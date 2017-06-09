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

public class LayeredPanel extends JPanel{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = -5630682030714330058L;

	private JPanel spinPan = new JPanel();
	
	
	private final ImageIcon board = MyImage.createImageIcon("./image/gameboard.jpg");
	
	ArrayList<ActionPanel> actionSpaces = new ArrayList<ActionPanel>();
	
	HashMap<Integer,ArrayList<TowerPanel>> towers = new HashMap<Integer,ArrayList<TowerPanel>>();
	
	
	
	//final ImageIcon card = createImageIcon("rsz_devcards_f_en_c_3.png");
	
	
	private JLayeredPane layeredPane;

	
	
	public double resizeFactor(ImageIcon c, double heightScreen){
		double factorScaleX = (double)c.getIconHeight() / (heightScreen);
		return factorScaleX;
	}
	
	public LayeredPanel(double widthScreen, double heightScreen, String username) {
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
        

		Rectangle dimDice1 = AdaptiveLayout.getOrangeDiceSpace(factorScaleBoard);
		JLabel la = MyImage.getScaledImageinLabel("./image/dice/orange.jpg", dimDice1);
		layeredPane.add(la, new Integer(2000));
        
        //cardIcon = new JLabel(card);
  
        //cardIcon.setBounds(200, 100, card.getIconWidth(), card.getIconHeight());
       
        //layeredPane.add(cardIcon, new Integer(25), 0);
	
		ActionPanel harvest1 = new ActionPanel(username, AdaptiveLayout.getHarvestRightSpace(factorScaleBoard), new HarvestListener(), 1);
		layeredPane.add(harvest1, new Integer(30));
		
		ActionPanel harvest2 = new ActionPanel(username, AdaptiveLayout.getHarvestLeftSpace(factorScaleBoard), new HarvestListener(), 0);
		layeredPane.add(harvest2, new Integer(30));
		
		ActionPanel prod1 = new ActionPanel(username, AdaptiveLayout.getProdRightSpace(factorScaleBoard), new ProductionListener(), 1);
		ActionPanel prod2 = new ActionPanel(username, AdaptiveLayout.getProdLeftSpace(factorScaleBoard), new ProductionListener(), 0);
		layeredPane.add(prod1, new Integer(30));
		layeredPane.add(prod2, new Integer(30));
		
		ActionPanel mark1 = new ActionPanel(username, AdaptiveLayout.getMarket1Space(factorScaleBoard), new MarketListener(), 1);
		ActionPanel mark2 = new ActionPanel(username, AdaptiveLayout.getMarket2Space(factorScaleBoard), new MarketListener(), 2);
		ActionPanel mark3 = new ActionPanel(username, AdaptiveLayout.getMarket3Space(factorScaleBoard), new MarketListener(), 3);
		ActionPanel mark4 = new ActionPanel(username, AdaptiveLayout.getMarket4Space(factorScaleBoard), new MarketListener(), 4);
		layeredPane.add(mark1, new Integer(30));
		layeredPane.add(mark2, new Integer(30));
		layeredPane.add(mark3, new Integer(30));
		layeredPane.add(mark4, new Integer(30));
		
		ActionPanel council = new ActionPanel(username, AdaptiveLayout.getCouncilPalaceSpace(factorScaleBoard), new MarketListener(), 0);
		layeredPane.add(council, new Integer(30));
		//hp = new ProductionHarvest();
		/*layeredPane.add(hp, new Integer(100),0);
		hp.setBounds(150,200 + board.getIconHeight()/2, board.getIconWidth()/2 -100, board.getIconHeight()-100);
		*/
	
		FamiliarButton fam1 = new FamiliarButton(Color.BLACK, java.awt.Color.BLACK, new TakeFamiliarListener(actionSpaces));
		fam1.setBounds((int)widthScreen - 600, 0, 100, 100);
		FamiliarButton fam2 = new FamiliarButton(Color.ORANGE, java.awt.Color.ORANGE,new TakeFamiliarListener(actionSpaces));
		fam2.setBounds((int)widthScreen - 600 + 100, 0, 100, 100);
		FamiliarButton fam3 = new FamiliarButton(Color.WHITE, java.awt.Color.WHITE, new TakeFamiliarListener(actionSpaces));
		fam3.setBounds((int)widthScreen - 600 + 200, 0, 100, 100);
		layeredPane.add(fam1, new Integer(50), 0);
		layeredPane.add(fam2, new Integer(50), 0);
		layeredPane.add(fam3, new Integer(50), 0);
		

		actionSpaces.add(harvest1);
		actionSpaces.add(harvest2);
		actionSpaces.add(prod1);
		actionSpaces.add(prod2);
		actionSpaces.add(mark1);
		actionSpaces.add(mark2);
		actionSpaces.add(mark3);
		actionSpaces.add(mark4);
		actionSpaces.add(council);
		
		towers.put(0, new ArrayList<TowerPanel>());
		towers.put(1, new ArrayList<TowerPanel>());
		towers.put(2, new ArrayList<TowerPanel>());
		towers.put(3, new ArrayList<TowerPanel>());
		
		for(int i = 0; i < 4; i++){
			TowerPanel tower1 = new TowerPanel(username, AdaptiveLayout.getCardTerritorySpace(factorScaleBoard, i), new TerritoryListener(), i);
			towers.get(0).add(tower1);
			layeredPane.add(tower1, new Integer(30));
			actionSpaces.add(tower1);
			TowerPanel tower2 = new TowerPanel(username, AdaptiveLayout.getCharacterSpace(factorScaleBoard, i), new CharacterListener(), i);
			towers.get(1).add(tower2);
			layeredPane.add(tower2, new Integer(30));
			actionSpaces.add(tower2);
			TowerPanel tower3 = new TowerPanel(username, AdaptiveLayout.getCardBuildingSpace(factorScaleBoard, i), new BuildingListener(), i);
			towers.get(2).add(tower3);
			layeredPane.add(tower3, new Integer(30));
			actionSpaces.add(tower3);
			TowerPanel tower4 = new TowerPanel(username, AdaptiveLayout.getCardVentureSpace(factorScaleBoard, i), new VentureListener(), i);
			towers.get(3).add(tower4);
			layeredPane.add(tower4, new Integer(30));
			actionSpaces.add(tower4);
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
	
	public void setCards(HashMap<Integer, ArrayList<String>> cardsPaths){
		for(Integer tower: cardsPaths.keySet()){
			ArrayList<String> paths = cardsPaths.get(tower);
			ArrayList<TowerPanel> panels = towers.get(tower);
			for(int i=0; i < panels.size(); i++){
				panels.get(i).setCard(paths.get(i));
			}
		}
	}
	
}
