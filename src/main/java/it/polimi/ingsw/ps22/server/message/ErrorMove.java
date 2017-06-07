package it.polimi.ingsw.ps22.server.message;
import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.answer.GenericAnswer;
import it.polimi.ingsw.ps22.server.view.VisitorA;

public class ErrorMove extends GenericMessage {

	private static final long serialVersionUID = 1L;

	public ErrorMove() {
		setString("Mossa errata. Riprova");
	}
	
	public GenericMessage accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public GenericAnswer accept(VisitorB visitor){
		return visitor.visit(this);
	}

}
