package it.polimi.ingsw.ps22;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.board.Board;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.ColorPlayer;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.Stone;
import it.polimi.ingsw.ps22.server.resource.Wood;

public class TestBoard {

	private Board board;
	private Player player;
	private Model model;

	@Before
	public void init() {
		model = new Model();
		board = new Board(model);
		board.reset(1, new ArrayList<Player>());
		player = new Player("Gigi", ColorPlayer.RED);
		player.setFamily(board.getDice());
	}

	@Test
	public void testSet() {
		assert (board.getTower("Building").getTowerSpaces().length == 4);
		assert (!player.getFamily(Color.BLACK).isPlaced());
	}

	@Test
	public void testCouncil() {
		player.getSpecBonus().setSpecBonus("DoubleServant");
		assert (board.getCouncilPalace().Control(3, player.getFamily(Color.WHITE)));
		board.getCouncilPalace().applyMove(3, player.getFamily(Color.WHITE), model);
		assert (player.getSpecificResource("Coin").getQuantity() == 1);
		assert (player.getSpecificResource("Servant").getQuantity() == 1);
	}

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

	@Test
	public void testExcomm() {
		ArrayList<Player> temp = new ArrayList<>();
		temp.add(player);
		board.getChurch(2).applyExcomm(temp);
		assert (board.getChurch(2).getExcomm().contains(player.getColor().getColor()));
	}

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
