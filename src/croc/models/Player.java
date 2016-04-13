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
	
	public Player(int cardAmount, String name_, PirateColor color){
		name = name_;
		
		pirates = new Pirate[1];
		pirates[0] = new Pirate(color, cardAmount, this);
		hasLost = false;
	}
	
	public Player(int cardAmount, String name_, PirateColor colorOne, PirateColor colorTwo){
		name = name_;
		
		pirates = new Pirate[2];
		pirates[0] = new Pirate(colorOne, cardAmount, this);
		pirates[1] = new Pirate(colorTwo, cardAmount, this);
		hasLost = false;
	}
	//TODO: playcard, recoverhand, loselimb
}
