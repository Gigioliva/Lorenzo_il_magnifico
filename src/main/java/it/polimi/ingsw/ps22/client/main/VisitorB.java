package it.polimi.ingsw.ps22.client.main;

import it.polimi.ingsw.ps22.server.message.*;

public abstract class VisitorB {

	public abstract void visit(AskServant mex);

	public abstract void visit(AskCard mex);

	public abstract void visit(AskCosts mex);

	public abstract void visit(AskCouncilPrivilege mex);

	public abstract void visit(AskEffect mex);

	public abstract void visit(AskExcomm mex);

	public abstract void visit(ChatMessage mex);

	public abstract void visit(ErrorMove mex);

	public abstract void visit(GenericMessage mex);

	public abstract void visit(AskLeader mex);

	public abstract void visit(AskCopyLeader mex);

	public abstract void visit(ChoiceMove mex);
	
	public abstract void visit(AskUsername mex);
	
	public abstract void visit(AskFamily mex);
	
	public abstract void visit(EndDraft mex);
	
	public abstract void visit(EndGame mex);

}
