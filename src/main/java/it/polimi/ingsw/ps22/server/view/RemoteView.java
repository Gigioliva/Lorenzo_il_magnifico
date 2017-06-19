package it.polimi.ingsw.ps22.server.view;

import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.model.ModelView;

public class RemoteView extends View implements Observer {

	private Connection connection;

	public RemoteView(String username, Connection connection) {
		super(username);
		this.connection = connection;
		connection.addObserver(this);
	}

	@Override
	public void showModel(Model model) {
		connection.send(model);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof ModelView && arg == null) {
			Model temp = ((ModelView) o).getModel(username);
			if (temp != null) {
				showModel(temp);
			}
		}
		if (o instanceof ModelView && arg instanceof GenericMessage) {
			GenericMessage mex = ((GenericMessage) arg)
					.accept(new VisitorA(username, ((ModelView) o).getCurrentPlayer()));
			if (mex != null) {
				connection.send(mex);
			}
		}
		if (o instanceof Connection && arg != null) {
			setChanged();
			notifyObservers(arg);
		}
	}

}
