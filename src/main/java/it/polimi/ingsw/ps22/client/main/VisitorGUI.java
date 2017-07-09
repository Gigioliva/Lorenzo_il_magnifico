package it.polimi.ingsw.ps22.client.main;

import it.polimi.ingsw.ps22.client.gui.Gui;
import it.polimi.ingsw.ps22.client.gui.LoginFrame;
import it.polimi.ingsw.ps22.server.message.*;

public class VisitorGUI extends VisitorB {
	
	private Gui gui;
	protected LoginFrame login;
	private ViewClient view;
	
	public VisitorGUI(Gui gui,ViewClient view){
		this.gui=gui;
		this.view=view;
	}

	@Override
	public void visit(AskServant mex) {
		gui.askServants(mex);
	}

	@Override
	public void visit(AskCard mex) {
		gui.getCard(mex);
	}

	@Override
	public void visit(AskCosts mex) {
		gui.askCosts(mex);
	}

	@Override
	public void visit(AskCouncilPrivilege mex) {
		gui.askPrivilege(mex);
	}

	@Override
	public void visit(AskEffect mex) {
		gui.askEffect(mex);
	}

	@Override
	public void visit(AskExcomm mex) {
		gui.askExcomm(mex);
	}

	@Override
	public void visit(ChatMessage mex) {
		gui.showChatMessage(mex);
	}

	@Override
	public void visit(ErrorMove mex) {
		gui.errorMove(mex);
	}

	@Override
	public void visit(GenericMessage mex) {
		gui.genericMessage(mex);
	}
	
	@Override
	public void visit(EndDraft mex) {
		gui.setLeaders();
	}

	@Override
	public void visit(AskLeader mex) {
		gui.askLeader(mex);
	}

	@Override
	public void visit(AskCopyLeader mex) {
		gui.askCopyLeader(mex);
	}

	@Override
	public void visit(ChoiceMove mex) {
		if(view.isMyTurn()){
			view.setFlag(false);
			gui.yourTurn(mex);
		}
	}

	@Override
	public void visit(AskUsername mex) {
		if(login==null){
			login=new LoginFrame(view);
		}else{
			login.notifyErrorForm();
		}
	}

	@Override
	public void visit(AskFamily mex) {
		gui.askFamily(mex);
	}
	
	@Override
	public void visit(EndGame mex){
		gui.genericMessage(mex);
	}
	
	@Override
	public void visit(RankingMessage mex){
		login.updateTopPlayers(mex.getString());
	}

}
