package it.polimi.ingsw.ps22.client.main;

import it.polimi.ingsw.ps22.server.message.*;
import it.polimi.ingsw.ps22.server.answer.*;

public abstract class VisitorB {

	public abstract AnswerServant visit(AskServant mex);

	public abstract AnswerCard visit(AskCard mex);

	public abstract AnswerCosts visit(AskCosts mex);

	public abstract AnswerCouncilPrivilege visit(AskCouncilPrivilege mex);

	public abstract AnswerEffect visit(AskEffect mex);

	public abstract AnswerExcomm visit(AskExcomm mex);

	public abstract GenericAnswer visit(ChatMessage mex);

	public abstract GenericAnswer visit(ErrorMove mex);

	public abstract GenericAnswer visit(GenericMessage mex);

	public abstract AnswerLeader visit(AskLeader mex);

	public abstract AnswerCopyLeader visit(AskCopyLeader mex);

	public abstract GenericAnswer visit(ChoiceMove mex);

}
