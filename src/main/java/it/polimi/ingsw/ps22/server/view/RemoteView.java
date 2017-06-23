package it.polimi.ingsw.ps22.server.view;

import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.model.ModelView;

public class RemoteView extends View implements Observer {

	private static final long serialVersionUID = 1L;
	private transient Connection connection;
	private boolean isActive = true;

	public RemoteView(String username, Connection connection) {
		super(username);
		this.connection = connection;
		connection.addObserver(this);
	}
	
	public void setConnection(Connection connection){
		this.connection=connection;
		connection.addObserver(this);
		reconnected();
	}
	
	public void reconnected(){
		isActive=true;
		setChanged();
		notifyObservers();
	}

	public void send(Object obj) {
		if (isActive)
			connection.send(obj);
	}

	public void close() {
		isActive = false;
		connection.close();
		connection=null;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(isActive){
			if (o instanceof ModelView && arg == null) {
				Model temp = ((ModelView) o).getModel(username);
				if (temp != null) {
					if (temp.getPlayers().get(username).getConnected()) {
						send(temp);
					} else {
						this.close();
					}
				}
			}
			if (o instanceof ModelView && arg instanceof GenericMessage) {
				GenericMessage mex = ((GenericMessage) arg)
						.accept(new VisitorA(username, ((ModelView) o).getCurrentPlayer()));
				if (mex != null) {
					send(mex);
				}
			}
			if (o instanceof Connection && arg != null) {
				setChanged();
				notifyObservers(arg);
			}
		}
	}

}
