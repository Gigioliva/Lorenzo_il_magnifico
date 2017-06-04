package it.polimi.ingsw.ps22.server.view;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;

public class RemoteView extends View implements Observer{
	
	private Connection connection;
	
	public RemoteView(String username, Connection connection){
		super(username);
		this.connection=connection;
		connection.addObserver(this);
	}
	
	@Override
	public void showModel(Model model) {
		connection.send(model);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Model && arg==null){
			showModel((Model)o);
		}
		if(o instanceof Model && arg instanceof GenericMessage){
			GenericMessage mex=((GenericMessage)arg).accept(new Visitor(username, ((Model)o).getPlayerGame()));
			if(mex!=null){
				connection.send(mex);
			}
		}
		if(o instanceof ConnectionSocket && arg!=null){
			setChanged();
			notifyObservers(arg);
		}
	}

}
