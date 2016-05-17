package croc.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import croc.controller.GameResolver;
import croc.exceptions.NotEveryoneChoseCardException;
import croc.models.CrGame;
import croc.models.DataRectangle;
import croc.models.Pirate;
import croc.models.PirateColor;
import croc.models.Player;

public class GameScreen implements Screen{

	
	/**
	 * CrocGame class, contains data relative to a croc game.
	 */
	CrGame croc;
	/**
	 * Game class, needs to be passed around to be able to change screens.
	 */
	public Game game;
	
	/**
	 * used to multiplex the inputprocessor and the stage's processor
	 */
	private InputMultiplexer inputMultiplexer;
	/**
	 * the camera used to display the screen's content
	 */
	OrthographicCamera camera;
	/**
	 * batch used to draw the sprites
	 */
	SpriteBatch batch;
	/**
	 * the text's font
	 */
	BitmapFont text;
	/**
	 * representation of the cursor
	 */
	public Vector3 cursor;
	/**
	 * processor used to process the inputs. (clicks only)
	 */
	GameInputProcessor inputProcessor;
	
	Texture[] cards;
	
	Texture body;
	Texture head;
	Texture leftArm;
	Texture rightArm;
	Texture leftLeg;
	Texture rightLeg;
	
	Texture gameBoard;
	Texture shark;
	Texture handBoard;
	Texture background;
	
	Integer nextPlayer = null;
	boolean turnBegin;
	int playedCount;
	DataRectangle[] pCardSelector;
	DataRectangle[] pCardSelector2;
	Boolean firstRound;
	int selectCount;
	GameResolver gr;
	Boolean sharkTurn;
	
	
	public GameScreen(Game g_, CrGame cg_){
		sharkTurn = false;
		gr = new GameResolver(cg_);
		firstRound = true;
		playedCount = 0;
		selectCount = 0;
		game = g_;
		croc = cg_;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 640);
		text = new BitmapFont((Gdx.files.internal("data/default.fnt")));
		body = new Texture(Gdx.files.internal("data/body.png"));
		head = new Texture(Gdx.files.internal("data/head.png"));
		leftArm = new Texture(Gdx.files.internal("data/leftArm.png"));
		rightArm = new Texture(Gdx.files.internal("data/rightArm.png"));
		leftLeg = new Texture(Gdx.files.internal("data/leftLeg.png"));
		rightLeg = new Texture(Gdx.files.internal("data/rightLeg.png"));
		gameBoard = new Texture(Gdx.files.internal("data/gameboard.png"));
		shark = new Texture(Gdx.files.internal("data/shark.png"));
		handBoard = new Texture(Gdx.files.internal("data/handboard.png"));
		background = new Texture(Gdx.files.internal("data/background.png"));
		batch = new SpriteBatch();
		cursor = new Vector3();
		inputProcessor = new GameInputProcessor(this);
		Gdx.input.setInputProcessor(inputProcessor);
		cards = new Texture[croc.getPirateOrder().get(0).cards.length];
		pCardSelector = new DataRectangle[croc.getPirateOrder().get(0).cards.length];
		pCardSelector2 = new DataRectangle[croc.getPirateOrder().get(0).cards.length];
		for(int i = 0; i < cards.length; i++){
			cards[i] = new Texture(Gdx.files.internal("data/"+(i+1)+".png"));
			pCardSelector[i] = new DataRectangle(50+ i*50, 0, 50, 33, i+1);
			if(croc.getPlayers().length <= 3){
				System.out.println("derp");
				pCardSelector2[i] = new DataRectangle(50+ i*50, 75, 50, 33, i+1);
			}
		}
		gr.gameInit();
		for(Pirate p : croc.getPirateOrder()){
			if(p.owner.isBot){
				p.botCardChooser();
				playedCount++;
			}
		}
		selectNextPlayer();
	}
	
	public void pirateColor(Pirate p){
		if(p.color == PirateColor.WHITE)
			batch.setColor(Color.WHITE);
		else if(p.color == PirateColor.RED)
			batch.setColor(Color.RED);
		else if(p.color == PirateColor.GREEN)
			batch.setColor(Color.GREEN);
		else if(p.color == PirateColor.PURPLE)
			batch.setColor(Color.PURPLE);
		else if(p.color == PirateColor.ORANGE)
			batch.setColor(Color.ORANGE);
		else if(p.color == PirateColor.YELLOW)
			batch.setColor(Color.YELLOW);
		else if(p.color == PirateColor.BLACK)
			batch.setColor(Color.GRAY);
	}

	public void DrawPlayedCards(){
		ArrayList<Pirate> pirates = croc.getPirateOrder();
		for(int i = 0; i < pirates.size(); i++){
			pirateColor(pirates.get(i));
			
			for(int j = 0; j < pirates.get(i).cards.length; j++){
				if(!pirates.get(i).cards[j].isInHand())
					batch.draw(cards[j],100 + 75*j -camera.position.x, camera.viewportHeight- 60*(i+1) -camera.position.y);
			}
		}
	}
	
	public void DrawPirates(){
		ArrayList<Pirate> pirates = croc.getPirateOrder();
		for(int i = 0; i < pirates.size(); i++){
			pirateColor(pirates.get(i));
			batch.draw(head,50-camera.position.x, camera.viewportHeight- 60*(i+1) -camera.position.y);
			batch.draw(body,50-camera.position.x, camera.viewportHeight- 60*(i+1) -camera.position.y);
			if(pirates.get(i).hasLeftArm())
				batch.draw(leftArm,50-camera.position.x, camera.viewportHeight- 60*(i+1) -camera.position.y);
			if(pirates.get(i).hasRightArm())
				batch.draw(rightArm,50-camera.position.x, camera.viewportHeight- 60*(i+1) -camera.position.y);
			if(pirates.get(i).hasLeftLeg())
				batch.draw(leftLeg,50-camera.position.x, camera.viewportHeight- 60*(i+1) -camera.position.y);
			if(pirates.get(i).hasRightLeg())
				batch.draw(rightLeg,50-camera.position.x, camera.viewportHeight- 60*(i+1) -camera.position.y);
			
		}
	}
	
	public void DrawPlayerAvailableCards(){
		if(nextPlayer != null){
			Player selected = croc.getPlayers()[nextPlayer];
			for(int i = 0; i < selected.pirates.length; i++){
				pirateColor(selected.pirates[i]);
				for(int j = 0; j < selected.pirates[i].availableCards.size(); j++){
					int temp = selected.pirates[i].availableCards.get(j).value;
					batch.draw(cards[temp - 1],temp*50 -camera.position.x,i*75 -camera.position.y);
				}
			}
		}
	}
	
	public void selectNextPlayer(){
		int i = 0;
		Integer nextPlayerTemp = nextPlayer;
		while(i < croc.getPlayers().length && nextPlayerTemp == nextPlayer){
			if(croc.getPlayers()[i].isBot == false && croc.getPlayers()[i].isRemote == false && !croc.getPlayers()[i].pirates[0].hasPlayed && !croc.getPlayers()[i].hasLost){
				nextPlayer = i;
			}
			i++;
		}
		if(nextPlayerTemp == nextPlayer)
			nextPlayer = null;
	}
	
	public void drawBgandProps(){
		batch.draw(background, -camera.position.x, -camera.position.y);
		batch.draw(gameBoard, -camera.position.x, -camera.position.y);
		batch.draw(handBoard, -camera.position.x, -camera.position.y);
		batch.draw(shark, -camera.position.x, -camera.position.y);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		//drawing gray background (also clearing screen)
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//tell camera to update it's matrices
		camera.update();
		//tell spritbatch to render in the camera's coordinate system
		batch.setProjectionMatrix(camera.projection);		
		//selection logic
		cursor.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(cursor);
		//begin a new batch and draws everything
		batch.begin();
		batch.setColor(Color.WHITE);
		drawBgandProps();
		DrawPlayedCards();
		DrawPlayerAvailableCards();
		DrawPirates();
		batch.end();

		if(sharkTurn && !firstRound){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gr.sharkTurn();
			
			playedCount = 0;
			gr.victoryCondition();
			for(Player p: croc.getPlayers())
				gr.hasLost(p);
			for(Pirate p: croc.getPirateOrder())
				p.hasPlayed = false;
			if(croc.getWinner() != null){
				if(!croc.getWinner().isRemote && !croc.getWinner().isBot)
					game.setScreen(new EndScreen(game, croc.getWinner(), true));
				else
					game.setScreen(new EndScreen(game, croc.getWinner(), false));
			}
			for(Pirate p : croc.getPirateOrder()){
				if(p.owner.isBot){
					p.botCardChooser();
					playedCount++;
				}
			}
			sharkTurn = false;
			selectNextPlayer();	
		}
		
		if(playedCount == croc.getPirateOrder().size()){
			try {
				gr.roundResolve();
				batch.begin();
				batch.setColor(Color.WHITE);
				drawBgandProps();
				DrawPlayedCards();
				DrawPlayerAvailableCards();
				DrawPirates();
				batch.end();
			} catch (NotEveryoneChoseCardException e) {
			}
			if(firstRound){
				firstRound = false;
				playedCount = 0;
				for(Pirate p : croc.getPirateOrder()){
					if(p.owner.isBot){
						p.botCardChooser();
						playedCount++;
					}
				}
				selectNextPlayer();
			}
			else{
				sharkTurn = true;
			}
		}
	}
	
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
