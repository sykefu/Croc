package croc.game;

import java.awt.Rectangle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import croc.exceptions.UnavailableCardException;
import croc.models.DataRectangle;

public class GameInputProcessor implements InputProcessor {

	/**
	 * Constructs MyInputProcessor.
	 * @param g_ Adds the GameScreen which is concerned by the input processor.
	 */	
	GameScreen g;
	public GameInputProcessor(GameScreen g_){
		g = g_;
	}
	
	
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for(DataRectangle r: g.pCardSelector){
			if(r.contains(g.cursor.x, g.cursor.y) && g.nextPlayer != null){
				if(g.croc.getPlayers()[g.nextPlayer].pirates[0].cards[r.value -1].isInHand()){
					try {
						g.croc.getPlayers()[g.nextPlayer].pirates[0].playCard(r.value);
						g.selectCount++;
						if(g.selectCount == g.croc.getPlayers()[g.nextPlayer].pirates.length){
							g.playedCount++;
							if(g.croc.getPlayers().length <= 3)
								g.playedCount++;
							g.selectCount = 0;
							g.selectNextPlayer();
						}
					} catch (UnavailableCardException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if(g.nextPlayer != null){
			if(g.croc.getPlayers()[g.nextPlayer].pirates.length > 1){
				for(DataRectangle r: g.pCardSelector2){
					if(r.contains(g.cursor.x, g.cursor.y)){
					
						if(g.croc.getPlayers()[g.nextPlayer].pirates[1].cards[r.value -1].isInHand()){
							try {
								g.croc.getPlayers()[g.nextPlayer].pirates[1].playCard(r.value);
								g.selectCount++;
								if(g.selectCount == g.croc.getPlayers()[g.nextPlayer].pirates.length){
									g.playedCount++;
									if(g.croc.getPlayers().length <= 3)
										g.playedCount++;
									g.selectCount = 0;
									g.selectNextPlayer();
								}
							} catch (UnavailableCardException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
