package it.polimi.ingsw.ps22.client.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.message.AskCard;
import it.polimi.ingsw.ps22.server.message.AskCopyLeader;
import it.polimi.ingsw.ps22.server.message.AskCosts;
import it.polimi.ingsw.ps22.server.message.AskCouncilPrivilege;
import it.polimi.ingsw.ps22.server.message.AskEffect;
import it.polimi.ingsw.ps22.server.message.AskExcomm;
import it.polimi.ingsw.ps22.server.message.AskFamily;
import it.polimi.ingsw.ps22.server.message.AskLeader;
import it.polimi.ingsw.ps22.server.message.AskServant;
import it.polimi.ingsw.ps22.server.message.ChoiceMove;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;

public class Gui extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BoardPanel board;
	ViewClient view;
	

	
	public void initGui( Model model, ViewClient view){
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		
		
		this.pack(); 
		this.setVisible(true);
		
	
		board = new BoardPanel(this.getWidth(), this.getHeight(), view.getUsername(), view, model); 
		
		//personalBoard = new PersonalBoardPanel(this.getWidth(), this.getHeight(), username);
		
		this.add(board);
		//this.add(personalBoard);
		this.view = view;
		
		Music.playMP3("src/main/java/it/polimi/ingsw/ps22/client/gui/music/Age_Of_Empires.mp3");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	
	}
	
	public void updateGui(Model model){
		board.updateBoard(model);
	}
	
	public void askPrivilege(AskCouncilPrivilege mex){
		new PrivilegeDialog(view, mex);
		return;
	}
	/*

	public void getCard(AskCard mex){
		
		JDialog d = new JDialog();
		JTextField t = new JTextField();
		t.setText("Puoi scegliere una carta extra ");
		d.add(t);
		
		for(String tower: mex.getPossibleCard().keySet()){
			
			ArrayList<DevelopmentCard> cards = mex.getPossibleCard().get(tower);
			
			for(int i=0; i < cards.size(); i++){
				String path = CardPath.getDevCardPathname(cards.get(i));
				if (board.towers.get(toInt(tower)).get(i).path.equals(path)){
					DevelopmentCard card = cards.get(i);
					board.towers.get(toInt(tower)).get(i).b.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							view.send(new AnswerCard(mex.getId(), tower, card.getName()));
						}
					});
				}
			}
		}
	}
	
	private Integer toInt(String type){
		switch(type){
		case "Territory":
			return 1;
		case "Character":
			return 2;
		case "Building":
			return 3;
		case "Venture":
			return 4;
		default:
			throw new IllegalArgumentException();
		}
		
	}
	*/
	public void getCard(AskCard mex){
		new AskCardDialog(view, mex);
	}
	
	public void askCosts(AskCosts mex){
		new AskCostDialog(view, mex);
	}
	
	public void askEffect(AskEffect mex){
		new AskEffectDialog(view, mex);
	}
	
	public void askFamily(AskFamily mex){
		new AskFamilyDialog(view, mex);
	}
	
	public void askServants(AskServant mex){
		new AskServantDialog(view, mex);
	}
	
	public void askCopyLeader(AskCopyLeader mex){
		new AskCopyLeaderDialog(view, mex);
	}
	
	public void askLeader(AskLeader mex){
		new AskLeaderDialog(view, mex);
	}
	
	public void errorMove(){
		JOptionPane.showMessageDialog(this, "You can't do this move");
	}

	
	public void askExcomm(AskExcomm mex){
		new AskExcommDialog(view, mex);
	}
	
	public void yourTurn(ChoiceMove mex){
		JOptionPane.showMessageDialog(this, mex.getString());
	}
	
	public void genericMessage(GenericMessage mex){
		JOptionPane.showMessageDialog(this, mex.getString());
	}

}
