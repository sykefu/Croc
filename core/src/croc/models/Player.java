package croc.models;


/**
 * Hold all data used or linked to a player
 * @author sykefu
 *
 */
public class Player {
	final public String name;
	public Card[] cards;
	public Pirate[] pirates;
	public Boolean hasLost;
	public Boolean isBot;
	public Boolean isRemote;
	
	public Player(int cardAmount, String name_, PirateColor color, Boolean isBot_){
		name = name_;
		
		pirates = new Pirate[1];
		pirates[0] = new Pirate(color, cardAmount, this);
		hasLost = false;
		isBot = isBot_;
		isRemote = false;
	}
	
	public Player(int cardAmount, String name_, PirateColor colorOne, PirateColor colorTwo, Boolean isBot_){
		name = name_;
		
		pirates = new Pirate[2];
		pirates[0] = new Pirate(colorOne, cardAmount, this);
		pirates[1] = new Pirate(colorTwo, cardAmount, this);
		hasLost = false;
		isBot = isBot_;
		isRemote = false;
	}
	
	public Player(int cardAmount, String name_, PirateColor color, Boolean isBot_, Boolean isRemote_){
		name = name_;
		
		pirates = new Pirate[1];
		pirates[0] = new Pirate(color, cardAmount, this);
		hasLost = false;
		isBot = isBot_;
		isRemote = isRemote_;
	}
	
	public Player(int cardAmount, String name_, PirateColor colorOne, PirateColor colorTwo, Boolean isBot_, Boolean isRemote_){
		name = name_;
		
		pirates = new Pirate[2];
		pirates[0] = new Pirate(colorOne, cardAmount, this);
		pirates[1] = new Pirate(colorTwo, cardAmount, this);
		hasLost = false;
		isBot = isBot_;
		isRemote = isRemote_;
	}
}
