package croc.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import croc.exceptions.NotEveryoneChoseCardException;
import croc.exceptions.PlayerAmountException;
import croc.exceptions.UnavailableCardException;
import croc.models.Game;
import croc.models.PirateColor;
import croc.models.Player;

public class GameResolverTest {

	//TODO: write tests to make sure win conditions are working for all 3 gamemodes (2,3 and 4 to 7 players)
	@Test
	public void rightWinner4PlayerTest() throws PlayerAmountException {
		//init
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
		//write game
		
		//check winner
		assertTrue(test.getPlayers()[0].name.equals(test.getWinner().name));
	}
	
	@Test
	public void rightWinner3PlayerTest() throws PlayerAmountException {
		//init
		GameBuilder gb = new GameBuilder(3);
		gb.chooseColor(PirateColor.WHITE);
		gb.chooseColor(PirateColor.BLACK);
		gb.chooseColor(PirateColor.GREEN);
		gb.chooseColor(PirateColor.ORANGE);
		gb.chooseColor(PirateColor.YELLOW);
		gb.chooseColor(PirateColor.PURPLE);
		gb.chooseName("a");
		gb.chooseName("b");
		gb.chooseName("c");
		Game test = gb.createGame();
		//write game
		
		//check winner
		assertTrue(test.getPlayers()[0].name.equals(test.getWinner().name));
	}
	
	@Test
	public void rightWinner2PlayerTest() throws PlayerAmountException {
		//init
		GameBuilder gb = new GameBuilder(2);
		gb.chooseColor(PirateColor.WHITE);
		gb.chooseColor(PirateColor.BLACK);
		gb.chooseColor(PirateColor.GREEN);
		gb.chooseColor(PirateColor.ORANGE);
		gb.chooseName("a");
		gb.chooseName("b");
		Game test = gb.createGame();
		//write game
		
		//check winner
		assertTrue(test.getPlayers()[0].name.equals(test.getWinner().name));
	}
	
	@Test
	public void rightPirateOrderTests() throws PlayerAmountException, UnavailableCardException, NotEveryoneChoseCardException {
		//init
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
		//play a round
		Player[] testP = test.getPlayers();
		testP[0].pirates[0].playCard(1);
		testP[1].pirates[0].playCard(2);
		testP[2].pirates[0].playCard(3);
		testP[3].pirates[0].playCard(4);
		assertTrue(testP[0].pirates[0].isAlive());
		//test
		assertTrue(testP[0].pirates[0].cards[0].isInHand() == false);
		GameResolver tester = new GameResolver(test);
		tester.roundResolve();
		assertTrue(test.getPirateOrder().get(0).owner.name.equals(testP[3].name));
		tester.sharkTurn();
		assertTrue(test.getPirateOrder().get(0).owner.name.equals(testP[0].name));
		//check if P0 recovered card 1 and lost a limb
		assertTrue(testP[0].pirates[0].getLimbCount() == 3);
		assertTrue(testP[0].pirates[0].cards[0].isInHand() == true);
		
		
	}
	
	@Test
	public void PlayerLossRecognitionTest() throws PlayerAmountException, UnavailableCardException, NotEveryoneChoseCardException {
		//init
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
		GameResolver tester = new GameResolver(test);
		//play a round
		Player[] testP = test.getPlayers();
		testP[0].pirates[0].playCard(1);
		testP[1].pirates[0].playCard(2);
		testP[2].pirates[0].playCard(3);
		testP[3].pirates[0].playCard(4);
		//test
		tester.roundResolve();
		tester.sharkTurn();
		testP[0].pirates[0].playCard(1);
		testP[1].pirates[0].playCard(4);
		testP[2].pirates[0].playCard(2);
		testP[3].pirates[0].playCard(3);
		tester.roundResolve();
		tester.sharkTurn();
		testP[0].pirates[0].playCard(1);
		testP[1].pirates[0].playCard(3);
		testP[2].pirates[0].playCard(4);
		testP[3].pirates[0].playCard(2);
		tester.roundResolve();
		tester.sharkTurn();
		tester.hasLost(testP[0]);
		assertTrue(!testP[0].hasLost);
		assertTrue(testP[0].pirates[0].isAlive());
		//cheat a bit, too lazy to go back to a setup where testP0's pirate can lose a round
		testP[0].pirates[0].setlastPlayedCard(1);
		testP[1].pirates[0].setlastPlayedCard(2);
		testP[2].pirates[0].setlastPlayedCard(3);
		testP[3].pirates[0].setlastPlayedCard(4);
		tester.roundResolve();
		tester.sharkTurn();

		//4 rounds lost for testP0's pirate, he should be dead, player should have lost.
		assertTrue(!testP[0].pirates[0].isAlive());

		assertTrue(!testP[0].hasLost);
		tester.hasLost(testP[0]);
		assertTrue(testP[0].hasLost);
	}
}
