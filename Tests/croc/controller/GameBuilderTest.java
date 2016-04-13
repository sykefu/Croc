package croc.controller;
import static org.junit.Assert.*;

import org.junit.Test;

import croc.controller.GameBuilder;
import croc.exceptions.PlayerAmountException;
import croc.models.PirateColor;

public class GameBuilderTest {

	@Test
	public void NoDuplicateTest() throws PlayerAmountException {
		GameBuilder gb = new GameBuilder(7);
		gb.chooseColor(PirateColor.WHITE);
		gb.chooseColor(PirateColor.BLACK);
		gb.chooseColor(PirateColor.GREEN);
		gb.chooseColor(PirateColor.ORANGE);
		gb.chooseColor(PirateColor.PURPLE);
		gb.chooseColor(PirateColor.RED);
		gb.chooseColor(PirateColor.YELLOW);
		assertEquals("cannot have two with same color",false, gb.chooseColor(PirateColor.WHITE));
		gb.chooseName("hue");
		assertEquals("cannot have two with same name",false, gb.chooseName("hue"));
		gb.chooseName("t");
		gb.chooseName("g");
		gb.chooseName("h");
		gb.chooseName("i");
		gb.chooseName("j");
		gb.chooseName("k");
	}
	
	@Test
	public void NoCountingErrorTest() throws PlayerAmountException {
		GameBuilder gb = new GameBuilder(7);
		gb.chooseColor(PirateColor.WHITE);
		gb.chooseColor(PirateColor.BLACK);
		gb.chooseColor(PirateColor.GREEN);
		gb.chooseColor(PirateColor.ORANGE);
		gb.chooseColor(PirateColor.PURPLE);
		gb.chooseColor(PirateColor.RED);
		gb.chooseColor(PirateColor.YELLOW);
		gb.chooseColor(PirateColor.WHITE);
		gb.chooseName("hue");
		gb.chooseName("hue");
		gb.chooseName("t");
		gb.chooseName("g");
		gb.chooseName("h");
		gb.chooseName("i");
		gb.chooseName("j");
		gb.chooseName("k");
		assertEquals("Right amount of color counted",7 ,gb.getChosenColorCount());
		assertEquals("Right amount of names counted",7, gb.getChosenNameCount());
	}

}
