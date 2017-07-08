package it.polimi.ingsw.ps22;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.board.Board;
import it.polimi.ingsw.ps22.server.board.ChurchSpace;
import it.polimi.ingsw.ps22.server.board.TowerCharacterZone;
import it.polimi.ingsw.ps22.server.board.TowerSpace;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.Wood;

/**
 * 
 * This class checks whether the {@link Board} class works properly or not
 *
 */

public class TestBoard {

	private Board board;
	private Player player;
	private Model model;

	/**
	 * this method initialize the {@link Model}, the {@link Board} and add one
	 * {@link Player} to the game
	 * 
	 */

	@Before
	public void init() {
		model = new Model();
		board = new Board(model);
		board.reset(1, new ArrayList<Player>());
		model.addPlayers("Gigi");
		player = model.getPlayers().get("Gigi");
		player.setFamily(board.getDice());
	}

	/**
	 * this method tests the correct initialization of the various objects
	 * [{@link Model}, {@link Board}, {@link Player}]
	 */

	@Test
	public void testSet() {
		assert (board.getTower("Building").getTowerSpaces().length == 4);
		assert (board.getTower("Territory").getTowerSpaces().length == 4);
		assert (board.getTower("Venture").getTowerSpaces().length == 4);
		assert (board.getTower("Character").getTowerSpaces().length == 4);
		assert (!player.getFamily(Color.BLACK).isPlaced());
		assert (model.getPlayers().containsKey("Gigi"));
	}

	/**
	 * this method tests the Council class in the package
	 * {@link it.polimi.ingsw.ps22.server.board}
	 */

	@Test
	public void testCouncil() {
		player.getSpecBonus().setSpecBonus("DoubleServant");
		assert (board.getCouncilPalace().Control(3, player.getFamily(Color.WHITE)));
		board.getCouncilPalace().applyMove(3, player.getFamily(Color.WHITE), model);
		assert (player.getSpecificResource("Coin").getQuantity() == 1);
		assert (player.getSpecificResource("Servant").getQuantity() == 1);
	}

	/**
	 * this method tests the Control method in {@link TowerSpace}
	 */

	@Test
	public void testControl() {
		assert (board.getTower("Territory").getTowerSpaces()[0].controlPlacement());
		assert (board.getTower("Territory").Control(0, 0, player.getFamily(Color.BLACK)));
		assert (board.getMarket().Control(0, 0, player.getFamily(Color.BLACK)));
		assert (board.getProdZone().Control(0, 0, player.getFamily(Color.BLACK)));
		assert (board.getHarvestZone().Control(0, 0, player.getFamily(Color.BLACK)));
		board.getTower("Territory").placeFamily(0, 0, player.getFamily(Color.BLACK));
		board.getTower("Territory").takeCard(0, player);
		assert (board.getTower("Territory").getTowerSpaces()[0].getCard() == null);
		assert (board.getTower("Territory").getTowerSpaces()[0].getFamilies().contains(player.getFamily(Color.BLACK)));
	}

	/**
	 * this method tests the applyExcomm method in {@link ChurchSpace}
	 */

	@Test
	public void testExcomm() {
		ArrayList<Player> temp = new ArrayList<>();
		temp.add(player);
		board.getChurch(2).applyExcomm(temp);
		assert (board.getChurch(2).getExcomm().contains(player.getColor().getColor()));
	}

	/**
	 * this method tests the {@link TowerCharacterZone} class in the package
	 * {@link it.polimi.ingsw.ps22.server.board}
	 */

	@Test
	public void testCharacterTower() {
		player.addSpecificResource("Coin", new Coin(7));
		assert (board.getTower("Character").Control(0, 0, player.getFamily(Color.BLACK)));
		board.getTower("Character").placeFamily(0, 0, player.getFamily(Color.BLACK));
		board.getTower("Character").takeCard(0, player);
		assert (board.getTower("Character").getTowerSpaces()[0].getCard() == null);
		assert (board.getTower("Character").getTowerSpaces()[0].getFamilies().contains(player.getFamily(Color.BLACK)));
		assert (player.getDevelopmentCard("Character").size() == 1);
	}

	/**
	 * this method tests the {@link TowerBuildingZone} class in the package
	 * {@link it.polimi.ingsw.ps22.server.board}
	 */
	
	@Test
	public void testBuildingTower() {
		player.addSpecificResource("Coin", new Coin(7));
		player.addSpecificResource("Stone", new Stone(3));
		player.addSpecificResource("Wood", new Wood(3));
		assert (board.getTower("Building").Control(0, 0, player.getFamily(Color.BLACK)));
		board.getTower("Building").placeFamily(0, 0, player.getFamily(Color.BLACK));
		board.getTower("Building").takeCard(0, player);
		assert (board.getTower("Building").getTowerSpaces()[0].getCard() == null);
		assert (board.getTower("Building").getTowerSpaces()[0].getFamilies().contains(player.getFamily(Color.BLACK)));
		assert (player.getDevelopmentCard("Building").size() == 1);
	}

	/**
	 * this method tests the {@link TowerVentureZone} class in the package
	 * {@link it.polimi.ingsw.ps22.server.board}
	 */
	
	@Test
	public void testVentureTower() {
		player.addSpecificResource("Coin", new Coin(8));
		player.addSpecificResource("Stone", new Stone(5));
		player.addSpecificResource("Wood", new Wood(5));
		player.addSpecificResource("MilitaryPoint", new Wood(5));
		if (board.getTower("Venture").getTowerSpaces()[0].getCard().getRequisiteCost().size() == 1) {
			assert (board.getTower("Venture").Control(0, 0, player.getFamily(Color.BLACK)));
			board.getTower("Venture").placeFamily(0, 0, player.getFamily(Color.BLACK));
			board.getTower("Venture").takeCard(0, player);
			assert (board.getTower("Venture").getTowerSpaces()[0].getCard() == null);
			assert (board.getTower("Venture").getTowerSpaces()[0].getFamilies()
					.contains(player.getFamily(Color.BLACK)));
			assert (player.getDevelopmentCard("Venture").size() == 1);
		}
	}
}
