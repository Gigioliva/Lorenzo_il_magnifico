package it.polimi.ingsw.ps22.client.gui;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

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

public class LayeredPanel extends JPanel{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = -5630682030714330058L;

	private JPanel spinPan = new JPanel();
	
	
	private FamiliarButton fam;
	
	private final ImageIcon board = MyImage.createImageIcon("./image/gameboard.jpg");
	
	ArrayList<ActionPanel> actionSpaces = new ArrayList<ActionPanel>();
	
	
	
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
	
		fam = new FamiliarButton(it.polimi.ingsw.ps22.server.model.Color.BLACK);
		fam.setBounds((int)widthScreen - 500, (int)heightScreen - 800, board.getIconWidth()- 400, board.getIconHeight() - 600);

		actionSpaces.add(harvest1);
		actionSpaces.add(harvest2);
		actionSpaces.add(prod1);
		actionSpaces.add(prod2);
		actionSpaces.add(mark1);
		actionSpaces.add(mark2);
		actionSpaces.add(mark3);
		actionSpaces.add(mark4);
		actionSpaces.add(council);
		
		for(int i = 0; i < 4; i++){
			ActionPanel tower1 = new ActionPanel(username, AdaptiveLayout.getCardTerritorySpace(factorScaleBoard, i), new TerritoryListener(), i);
			layeredPane.add(tower1, new Integer(30));
			actionSpaces.add(tower1);
			ActionPanel tower2 = new ActionPanel(username, AdaptiveLayout.getCharacterSpace(factorScaleBoard, i), new CharacterListener(), i);
			layeredPane.add(tower2, new Integer(30));
			actionSpaces.add(tower2);
			ActionPanel tower3 = new ActionPanel(username, AdaptiveLayout.getCardBuildingSpace(factorScaleBoard, i), new BuildingListener(), i);
			layeredPane.add(tower3, new Integer(30));
			actionSpaces.add(tower3);
			ActionPanel tower4 = new ActionPanel(username, AdaptiveLayout.getCardVentureSpace(factorScaleBoard, i), new VentureListener(), i);
			layeredPane.add(tower4, new Integer(30));
			actionSpaces.add(tower4);
		}
			
		fam.addActionListener(new TakeFamiliarListener(actionSpaces));
		layeredPane.add(fam, new Integer(50), 0);
		
		
		
		spinPan.setBounds((int)widthScreen - 500, (int)heightScreen - 500, board.getIconWidth()- 400, board.getIconHeight() - 700);
		spinFamiliar();
		
		this.setBackground(new Color(34,45,32));
		
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
	
}
