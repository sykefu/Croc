package croc.controller;

import static org.junit.Assert.*;
import org.junit.Test;

import croc.controller.GameBuilder;
import croc.exceptions.PlayerAmountException;
import croc.models.Game;
import croc.models.PirateColor;
import croc.models.Player;

public class GameTest {

	@Test
	public void gameCrocTest7Players() throws PlayerAmountException {
		GameBuilder gb = new GameBuilder(7);
		gb.chooseColor(PirateColor.WHITE);
		gb.chooseColor(PirateColor.BLACK);
		gb.chooseColor(PirateColor.GREEN);
		gb.chooseColor(PirateColor.ORANGE);
		gb.chooseColor(PirateColor.PURPLE);
		gb.chooseColor(PirateColor.RED);
		gb.chooseColor(PirateColor.YELLOW);
		gb.chooseName("a");
		gb.chooseName("b");
		gb.chooseName("c");
		gb.chooseName("d");
		gb.chooseName("e");
		gb.chooseName("f");
		gb.chooseName("g");
		Game test = gb.createGame();
		assertEquals("7 players in game",7,test.getPlayers().length);
		assertEquals("7 pirates in array",7,test.getPirateOrder().size());
		Player[] p = test.getPlayers();
		for(int i = 0; i < test.getPlayers().length; i++){
			assertEquals("player "+i+"'s Pirate has 7 cards",7,p[i].pirates[0].cards.length);
		}
	}
	
	@Test
	public void gameCrocTest6Players() throws PlayerAmountException {
		GameBuilder gb = new GameBuilder(6);
		gb.chooseColor(PirateColor.WHITE);
		gb.chooseColor(PirateColor.BLACK);
		gb.chooseColor(PirateColor.GREEN);
		gb.chooseColor(PirateColor.ORANGE);
		gb.chooseColor(PirateColor.PURPLE);
		gb.chooseColor(PirateColor.RED);
		gb.chooseName("a");
		gb.chooseName("b");
		gb.chooseName("c");
		gb.chooseName("d");
		gb.chooseName("e");
		gb.chooseName("f");
		Game test = gb.createGame();
		assertEquals("6 players in game",6,test.getPlayers().length);
		assertEquals("6 pirates in array",6,test.getPirateOrder().size());
		Player[] p = test.getPlayers();
		for(int i = 0; i < test.getPlayers().length; i++){
			assertEquals("player "+i+"'s Pirate has 7 cards",7,p[i].pirates[0].cards.length);
		}
	}
	
	@Test
	public void gameCrocTest5Players() throws PlayerAmountException {
		GameBuilder gb = new GameBuilder(5);
		gb.chooseColor(PirateColor.WHITE);
		gb.chooseColor(PirateColor.BLACK);
		gb.chooseColor(PirateColor.GREEN);
		gb.chooseColor(PirateColor.ORANGE);
		gb.chooseColor(PirateColor.PURPLE);
		gb.chooseName("a");
		gb.chooseName("b");
		gb.chooseName("c");
		gb.chooseName("d");
		gb.chooseName("e");
		Game test = gb.createGame();
		assertEquals("5 players in game",5,test.getPlayers().length);
		assertEquals("5 pirates in array",5,test.getPirateOrder().size());
		Player[] p = test.getPlayers();
		for(int i = 0; i < test.getPlayers().length; i++){
			assertEquals("player "+i+"'s Pirate has 6 cards",6,p[i].pirates[0].cards.length);
		}
	}
	
	@Test
	public void gameCrocTest4Players() throws PlayerAmountException {
		GameBuilder gb = new GameBuilder(4);
		gb.chooseColor(PirateColor.WHITE);
		gb.chooseColor(PirateColor.BLACK);
		gb.chooseColor(PirateColor.GREEN);
		gb.chooseColor(PirateColor.ORANGE);
		gb.chooseName("a");
		gb.chooseName("b");
		gb.chooseName("c");
		gb.chooseName("d");
		Game test = gb.createGame();
		assertEquals("4 players in game",4,test.getPlayers().length);
		assertEquals("4 pirates in array",4,test.getPirateOrder().size());
		Player[] p = test.getPlayers();
		for(int i = 0; i < test.getPlayers().length; i++){
			assertEquals("player "+i+"'s Pirate has 5 cards",5,p[i].pirates[0].cards.length);
		}
	}
	
	@Test
	public void gameCrocTest3Players() throws PlayerAmountException {
		GameBuilder gb = new GameBuilder(3);
		gb.chooseColor(PirateColor.WHITE);
		gb.chooseColor(PirateColor.BLACK);
		gb.chooseColor(PirateColor.GREEN);
		gb.chooseColor(PirateColor.ORANGE);
		gb.chooseColor(PirateColor.PURPLE);
		gb.chooseColor(PirateColor.RED);
		gb.chooseName("a");
		gb.chooseName("b");
		gb.chooseName("c");
		System.out.println(gb.chooseName("d"));
		Game test = gb.createGame();
		assertEquals("3 players in game",3,test.getPlayers().length);
		assertEquals("6 pirates in array",6,test.getPirateOrder().size());
		Player[] p = test.getPlayers();
		for(int i = 0; i < test.getPlayers().length; i++){
			assertEquals("player "+i+"'s Pirate has 7 cards",7,p[i].pirates[0].cards.length);
			assertEquals("player "+i+"'s Pirate has 7 cards",7,p[i].pirates[1].cards.length);
		}
	}
	
	@Test
	public void gameCrocTest2Players() throws PlayerAmountException {
		GameBuilder gb = new GameBuilder(2);
		gb.chooseColor(PirateColor.WHITE);
		gb.chooseColor(PirateColor.BLACK);
		gb.chooseColor(PirateColor.GREEN);
		gb.chooseColor(PirateColor.ORANGE);
		gb.chooseName("a");
		gb.chooseName("b");
		Game test = gb.createGame();
		assertEquals("2 players in game",2,test.getPlayers().length);
		assertEquals("4 pirates in array",4,test.getPirateOrder().size());
		Player[] p = test.getPlayers();
		for(int i = 0; i < test.getPlayers().length; i++){
			assertEquals("player "+i+"'s Pirate has 7 cards",7,p[i].pirates[0].cards.length);
			assertEquals("player "+i+"'s Pirate has 7 cards",7,p[i].pirates[1].cards.length);
		}
	}
}
