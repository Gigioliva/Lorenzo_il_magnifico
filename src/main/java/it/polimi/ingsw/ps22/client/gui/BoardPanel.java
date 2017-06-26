package it.polimi.ingsw.ps22.client.gui;


import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import it.polimi.ingsw.ps22.server.card.CardLeader;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.move.EndTurn;
import it.polimi.ingsw.ps22.server.player.Player;

public class BoardPanel extends JPanel{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = -5630682030714330058L;
	
	private final int NUMERE = 3;
	private ArrayList<ExcommLabel> excomm = new ArrayList<>();
	private final int NUMLEADERS = 4;
	private int NUM_PLAYERS;
	private static final int NUM_CARDEXCOMM = 3;
	private ServantSpinner spinner;
	private final ImageIcon board = MyImage.createImageIcon("./image/gameboard.jpg");
	private ArrayList<ActionButton> actionSpaces = new ArrayList<>();
	 HashMap<Integer,ArrayList<TowerPanel>> towers = new HashMap<>();
	protected static JLabel zoomedCard;
	private PersonalBoardPanel personalBoard;
	private JLayeredPane layeredPane;
	private ArrayList<PlayersButton> players = new ArrayList<>();
	private double resizeFactor; 
	private ArrayList<VictoryPointLabel> victory = new ArrayList<>();
	private ArrayList<MilitaryPointLabel> military = new ArrayList<>();
	private ArrayList<FaithTrackLabel> faith = new ArrayList<>();
	private JLabel dice1;
	private JLabel dice2;
	private JLabel dice3;
	private FamiliarButton fam1;
	private FamiliarButton fam2;
	private FamiliarButton fam3;
	private FamiliarButton fam4;
	private ArrayList<OrderPlayerLabel> orederPlayers = new ArrayList<>();
	private String username;
	private transient ViewClient view;
	private ArrayList<LeaderButton> leaders = new ArrayList<>();
	private transient AdaptiveLayout layout = AdaptiveLayout.instance();
	private transient PersonalBoardAdaptive layoutPersonal = PersonalBoardAdaptive.instance();
	
	
	public double resizeFactor(ImageIcon c, double heightScreen){
		double factorScaleX = (double)c.getIconHeight() / (heightScreen);
		return factorScaleX;
	}
	
	public BoardPanel(double widthScreen, double heightScreen, String username, ViewClient view, Model model) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		
		
		ArrayList<String> avver = new ArrayList<>(model.getPlayers().keySet());
		avver.remove(username);
		
		this.username = username;
		this.view = view;
		
		ArrayList<String> personBonusPaths = new ArrayList<>();
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
	
        HarvestLabel haLab = new HarvestLabel(resizeFactor);
		HarvestRightButton harvest1 = new HarvestRightButton(1, username, layout.getHarvestRightSpace(factorScaleBoard),
				new HarvestListener(view), haLab);
		addActionPanelToLayeredPane(harvest1);
		layeredPane.add(haLab,new Integer(10000));
		
		HarvestButton harvest2 = new HarvestButton(0, username, layout.getHarvestLeftSpace(factorScaleBoard), new HarvestListener(view));
		addActionPanelToLayeredPane(harvest2);
		
		ProductionLabel prLab = new ProductionLabel(resizeFactor);
		ProductionRightButton prod1 = new ProductionRightButton(1,username, layout.getProdRightSpace(factorScaleBoard), 
				new ProductionListener(view), prLab);
		layeredPane.add(prLab, new Integer(10000));
		
		ProductionButton prod2 = new ProductionButton(0, username, layout.getProdLeftSpace(factorScaleBoard), new ProductionListener(view));
		addActionPanelToLayeredPane(prod1);
		addActionPanelToLayeredPane(prod2);
		
		MarketButton mark1 = new MarketButton(0, username, layout.getMarket1Space(factorScaleBoard), new MarketListener(view));
		MarketButton mark2 = new MarketButton(1, username, layout.getMarket2Space(factorScaleBoard), new MarketListener(view));
		MarketButton mark3 = new MarketButton(2, username, layout.getMarket3Space(factorScaleBoard), new MarketListener(view));
		MarketButton mark4 = new MarketButton(3, username, layout.getMarket4Space(factorScaleBoard), new MarketListener(view));
		addActionPanelToLayeredPane(mark1);		
		addActionPanelToLayeredPane(mark2);
		addActionPanelToLayeredPane(mark3);
		addActionPanelToLayeredPane(mark4);
		
		CouncilLabel labCou = new CouncilLabel(resizeFactor);
		Rectangle dimLab = layout.getCouncilPalaceFamSpace(resizeFactor, 0);
		labCou.setBounds(dimLab.getInitx(), dimLab.getInity(),500,500);
		
		CouncilButton council = new CouncilButton(0, username, layout.getCouncilPalaceSpace(factorScaleBoard), 
				new CouncilListener(view), labCou);
		layeredPane.add(labCou, new Integer(9000));
		addActionPanelToLayeredPane(council);
	
		Rectangle dimFam1 = layoutPersonal.getBlackButtonSLot(personalBoard.resizeFactor);
		fam1 = new FamiliarButton(Color.BLACK, model.getPlayers().get(username).getColor().getColor(), 
				new TakeFamiliarListener(actionSpaces),username, dimFam1);
		fam1.setBounds(personalBoard.getWidth(), dimFam1.getInity(), dimFam1.getOffsetX(), dimFam1.getOffsetY());
		
		Rectangle dimFam2 = layoutPersonal.getOrangeButtonSLot(personalBoard.resizeFactor);
		fam2 = new FamiliarButton(Color.ORANGE, model.getPlayers().get(username).getColor().getColor(),
				new TakeFamiliarListener(actionSpaces), username, dimFam2);
		fam2.setBounds(personalBoard.getBounds().width + dimFam2.getInitx(), dimFam2.getInity(), dimFam2.getOffsetX(), dimFam2.getOffsetY());
		
		Rectangle dimFam3 = layoutPersonal.getWhiteButtonSLot(personalBoard.resizeFactor);
		fam3 = new FamiliarButton(Color.WHITE, model.getPlayers().get(username).getColor().getColor(),
				new TakeFamiliarListener(actionSpaces), username, dimFam3);
		fam3.setBounds(personalBoard.getBounds().width + dimFam3.getInitx(), dimFam3.getInity(), dimFam3.getOffsetX(), dimFam3.getOffsetY());
		
		Rectangle dimFam4 = layoutPersonal.getNeutralButtonSLot(personalBoard.resizeFactor);
		fam4 = new FamiliarButton(Color.NEUTRAL, model.getPlayers().get(username).getColor().getColor(),
				new TakeFamiliarListener(actionSpaces), username, dimFam4);
		fam4.setBounds(personalBoard.getBounds().width + dimFam4.getInitx(), dimFam4.getInity(), dimFam4.getOffsetX(), dimFam4.getOffsetY());
		
		layeredPane.add(fam1, new Integer(50), 0);
		layeredPane.add(fam2, new Integer(50), 0);
		layeredPane.add(fam3, new Integer(50), 0);
		layeredPane.add(fam4, new Integer(50), 0);
		
		towers.put(0, new ArrayList<>());
		towers.put(1, new ArrayList<>());
		towers.put(2, new ArrayList<>());
		towers.put(3, new ArrayList<>());
		
		Rectangle dimCard = layout.getCardBuildingSpace(factorScaleBoard, 0);
		zoomedCard = new JLabel();
		zoomedCard.setBounds((int)(heightScreen*0.7),(int) heightScreen/2,(int)( dimCard.getOffsetX()*2.2),(int)( dimCard.getOffsetY()*2.2));
		layeredPane.add(zoomedCard, new Integer(2000));
		
		for(int i = 0; i < 4; i++){
			TowerPanel tower1 = new TowerPanel(layout.getCardTerritorySpace(factorScaleBoard, i),"Territory",i);
			TowerButton space1 = new TowerButton(i , username, layout.getFamiliarSpace(factorScaleBoard, 0, i), new TerritoryListener(view),"Territory");
			addActionPanelToLayeredPane(space1);
			towers.get(0).add(tower1);
			layeredPane.add(tower1, new Integer(30));
			
			TowerPanel tower2 = new TowerPanel(layout.getCharacterSpace(factorScaleBoard, i), "Character", i);
			TowerButton space2 = new TowerButton(i,username, layout.getFamiliarSpace(factorScaleBoard, 1, i), new CharacterListener(view), "Character");
			addActionPanelToLayeredPane(space2);
			towers.get(1).add(tower2);
			layeredPane.add(tower2, new Integer(30));

			TowerPanel tower3 = new TowerPanel(layout.getCardBuildingSpace(factorScaleBoard, i), "Building", i);
			TowerButton space3 = new TowerButton(i, username, layout.getFamiliarSpace(factorScaleBoard, 2, i), new BuildingListener(view), "Building");
			addActionPanelToLayeredPane(space3);
			towers.get(2).add(tower3);
			layeredPane.add(tower3, new Integer(30));
			
			TowerPanel tower4 = new TowerPanel(layout.getCardVentureSpace(factorScaleBoard, i), "Venture", i );
			TowerButton space4 = new TowerButton(i, username, layout.getFamiliarSpace(factorScaleBoard, 3, i), new VentureListener(view), "Venture");
			addActionPanelToLayeredPane(space4);
			towers.get(3).add(tower4);
			layeredPane.add(tower4, new Integer(30));
		}
		
		
		this.setBackground(new java.awt.Color(154, 92, 43));
		
	
		layeredPane.add(personalBoard, new Integer(400),0);
		
		spinner = new ServantSpinner(actionSpaces);
		Rectangle dimSpinner = layoutPersonal.getServantRequestSlot(personalBoard.resizeFactor);
		spinner.setBounds(dimSpinner.getInitx()+ personalBoard.getWidth(), dimSpinner.getInity(), 
				(int)widthScreen -dimSpinner.getInitx()- personalBoard.getWidth() ,dimSpinner.getOffsetY());
		spinServant();
		
		for(int i = 0 ; i < NUM_PLAYERS - 1; i++){
			PlayersButton b1 = new PlayersButton(widthScreen, heightScreen, resizeFactor, avver.get(i) ,i, personBonusPaths.get(i), 
					model.getPlayers().get(avver.get(i)).getColor().getColor(), model.getPlayers().get(avver.get(i)).getLeaders());
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
		
		Rectangle rec = layout.getOrangeDiceSpace(factorScaleBoard);
		dice1.setBounds(rec.getInitx(), rec.getInity(), rec.getOffsetX(), rec.getOffsetY());
		layeredPane.add(dice1, new Integer(400));
		
		Font font = new Font("Papyrus", Font.ITALIC + Font.BOLD , dice1.getHeight());
		dice1.setFont(font);
		dice1.setForeground(java.awt.Color.BLACK);
		dice1.setHorizontalAlignment(SwingConstants.CENTER);
		
		dice2 = new JLabel();
		Rectangle rec2 = layout.getBlackDiceSpace(factorScaleBoard);
		dice2.setBounds(rec2.getInitx(), rec2.getInity(), rec2.getOffsetX(), rec2.getOffsetY());
		layeredPane.add(dice2, new Integer(400));
		dice2.setFont(font);
		dice2.setForeground(java.awt.Color.WHITE);
		dice2.setHorizontalAlignment(SwingConstants.CENTER);
		
		dice3 = new JLabel();
		Rectangle rec3 = layout.getWhiteDiceSpace(factorScaleBoard);
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
		
		
		JLabel playerLab = new JLabel();
		playerLab.setBounds((int)widthScreen - 150, 0, 120, 30);
		Font fontlab = new Font("Papyrus", Font.ITALIC + Font.BOLD , playerLab.getHeight());
		playerLab.setFont(fontlab);
		playerLab.setForeground(model.getPlayers().get(username).getColor().getColor());
		playerLab.setHorizontalAlignment(SwingConstants.CENTER);
		playerLab.setText(username);
		
		layeredPane.add(playerLab, new Integer(2000));
		
		
		JPanel chatpan = new JPanel();
		Rectangle dimChat = layoutPersonal.getChatSlot(personalBoard.resizeFactor);
		chatpan.setBounds(dimChat.getInitx() + personalBoard.getWidth(), dimChat.getInity(), dimChat.getOffsetX(), dimChat.getOffsetY());
		chatpan.setOpaque(false);
		layeredPane.add(chatpan, new Integer(4000));
		
		
		for(int i =1; i<= NUMERE; i++){
			ExcommLabel excomm1 = new ExcommLabel(resizeFactor, layout.getChurchSpace(resizeFactor, i));
			layeredPane.add(excomm1, new Integer(8000));
			excomm.add(excomm1);
		}
		
		if(model.getPlayers().size() == 2){
			layeredPane.add(MyImage.getScaledImageinLabel("./image/cover/harvest.png", 
					layout.getHarvestRightCover(resizeFactor)), new Integer(8000));
			layeredPane.add(MyImage.getScaledImageinLabel("./image/cover/prod.png", 
					layout.getProdRightCover(resizeFactor)), new Integer(8000));
		}
		
		if(model.getPlayers().size() <= 3){
			layeredPane.add(MyImage.getScaledImageinLabel("./image/cover/market3.png", 
					layout.getMarket3Cover(resizeFactor)), new Integer(8000));
			layeredPane.add(MyImage.getScaledImageinLabel("./image/cover/market4.png", 
					layout.getMarket4Cover(resizeFactor)), new Integer(8000));
		}
		
		setCardExcomm(model);
		
		setLeaders(model);
	
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
		updateFamiliars(model);
		updateFamSpinner();
		updateLeaders(model);
		updateExcomm(model);
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
			lab.setVisible(false);
			layeredPane.remove(lab);
		}
		victory.clear();
		for(Integer qty: tempPlay.keySet()){
			VictoryPointLabel lab = new VictoryPointLabel(resizeFactor, qty, tempPlay.get(qty));
			lab.update(model);
			victory.add(lab);
			layeredPane.add(lab, new Integer(100));
		}
	}
	
	private HashMap<Integer, ArrayList<Player>> updatePointTrack(Model model, String type){
		HashMap<Integer, ArrayList<Player>> tempPlay = new HashMap<>();
		
		for(String user: model.getPlayers().keySet()){
			int qty = model.getPlayers().get(user).getSpecificResource(type).getQuantity();
			if(!tempPlay.containsKey(qty))
				tempPlay.put(qty, new ArrayList<>());
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
	
	private void setCardExcomm(Model model){
		
		for(int i = 0; i < NUM_CARDEXCOMM; i++){
			String path = CardPath.getExcommCardPathname(model.getBoard().getChurch((i+1)*2).getCardExcomm());
			JLabel exCardLabel = MyImage.getScaledImageinLabel(path, layout.getChurchSpace(resizeFactor, i + 1));
			exCardLabel.addMouseListener(new MyMouse(zoomedCard, path));
			layeredPane.add(exCardLabel, new Integer(200));
		}
	
	}
	
	private void updateFamiliars(Model model){
		fam1.updateFamiliar(model);
		fam2.updateFamiliar(model);
		fam3.updateFamiliar(model);
		fam4.updateFamiliar(model);
	}
	
	private void updateFamSpinner(){
		spinner.updateSpin();
	}
	
	public void setLeaders(Model model){
			for(int i = 0; i< NUMLEADERS; i++){
				LeaderButton b1 = new LeaderButton(i, personalBoard, username);
				leaders.add(b1);
				b1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						LeaderButton b = (LeaderButton)e.getSource();
						(new AskLeaderMoveDialog(view, username, b)).setVisible(true);;
						
					}
				});
				layeredPane.add(b1, new Integer(2500));
			}
	}
	
	private void updateLeaders(Model model){
		if (model.getPlayers().get(username).getLeaders() != null){
			int k = 0;
			for(int i = 0; i< model.getPlayers().get(username).getLeaders().size(); i++){
				//leaders.get(i).updateLeader(model);
				k++;
				CardLeader card = model.getPlayers().get(username).getLeaders().get(i);
				leaders.get(i).setCardName(card.getName());
				
				if (model.getPlayers().get(username).getLeaders().get(i).isPlay()){
					leaders.get(i).setIcon(MyImage.getScaledImageinLabel("./image/leadercard/leadersback.jpg", 
							leaders.get(i).getDim()).getIcon());
					leaders.get(i).setVisible(true);
				}
					
				else{
					
					String path = CardPath.getLeaderCardPathname(card);
					leaders.get(i).setIcon(MyImage.getScaledImageinLabel(path, leaders.get(i).getDim()).getIcon());
					leaders.get(i).addMouseListener(new MyMouse(zoomedCard, path));
					leaders.get(i).setEnabled(true);
					leaders.get(i).setVisible(true);
				}
					
			}
			for(int j = k ; j< NUMLEADERS; j++){
					leaders.get(j).setIcon(null);
					leaders.get(j).setVisible(false);
					leaders.get(j).setEnabled(false);
			}
		}
			
		
	}
	
	
	private void updateExcomm(Model model){
		for(ExcommLabel label: excomm){
			label.update(model);
		}
	}
	
}
