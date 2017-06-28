package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.model.ModelView;
import it.polimi.ingsw.ps22.server.player.Player;

public class TestModelView {

	private Model model;
	private ModelView view;

	@Before
	public void init() {
		model = new Model();
		view = new ModelView();
		model.addPlayers("Sigismondo");
		model.addPlayers("Pastafizia");
		model.addObserver(view);
		model.startGame();
		for (Player el : model.getPlayers().values()) {
			while (!model.getCardLeaderStart(el).isEmpty()) {
				model.getCardLeaderStart(el).remove(0);
			}
		}
		model.draftStart();
	}

	@Test
	public void testClone() {
		model.sendModel();
		assert (model.getBoard().toString().equals(view.getModel("Sigismondo").getBoard().toString()));
		assert (model.getPlayers().get("Sigismondo").toString()
				.equals(view.getModel("Sigismondo").getPlayers().get("Sigismondo").toString()));
		assert(view.getModel("cacca")==null);
		assert(view.getCurrentPlayer().equals(model.getPlayerGame()));
	}

}
