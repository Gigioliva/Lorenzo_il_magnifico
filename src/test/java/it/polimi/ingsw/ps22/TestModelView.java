package it.polimi.ingsw.ps22;

import java.util.Observable;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.model.ModelView;
import it.polimi.ingsw.ps22.server.player.Player;

/**
 * 
 * This class checks whether the {@link ModelView} class works properly or not
 *
 */

public class TestModelView {

	private Model model;
	private ModelView view;

	/**
	 * this method initialize the {@link ModelView} and the {@link Model} by
	 * setting the {@link Player} and the {@link Observable}
	 * 
	 */

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

	/**
	 * this method tests the Clone method in {@link ModelView}
	 */
	
	@Test
	public void testClone() {
		model.sendModel();
		assert (model.getBoard().toString().equals(view.getModel("Sigismondo").getBoard().toString()));
		assert (model.getPlayers().get("Sigismondo").toString()
				.equals(view.getModel("Sigismondo").getPlayers().get("Sigismondo").toString()));
		assert (view.getModel("cacca") == null);
		assert (view.getCurrentPlayer().equals(model.getPlayerGame()));
	}

}
