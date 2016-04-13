package croc.models;
/**
 * Holds data revelant to the Pirate.
 * @author sykefu
 *
 */
public class Pirate {
	boolean leftLeg;
	boolean rightLeg;
	boolean leftArm;
	boolean rightArm;
	int limbCount;
	public final PirateColor color;
	public Card[] cards;
	private int lastPlayedCard;
	final public Player owner;
	
	public int getLastPlayedCard(){
		return lastPlayedCard;
	}
	
	public void lastPlayedCardRead(){
		lastPlayedCard = 10;
	}
	
	/**
	 * only to be used for debug purpose
	 * @param i set value of card, for debug.
	 */
	public void setlastPlayedCard(int i){
		lastPlayedCard = i;
	}
	
	public Pirate(PirateColor color_, int cardAmount, Player owner_){
		leftLeg = true;
		leftArm = true;
		rightArm = true;
		rightLeg = true;
		limbCount = 4;
		color = color_;
		cards = new Card[cardAmount];
		for(int i = 1; i <= cardAmount; i++)
			cards[i-1] = new Card(i);
		owner = owner_;
	}
	
	public boolean isAlive(){
		if(limbCount > 0){
			return true;
		}
		
		return false;
	}
	
	public String getColor(){
		switch(color){
		case WHITE:
			return "white";
		case RED:
			return "red";
		case GREEN:
			return "green";
		case PURPLE:
			return "purple";
		case ORANGE:
			return "orange";
		case YELLOW:
			return "yellow";
		case BLACK:
			return "black";
		default:
			return "undefined color";
		}
	}
	
	public boolean popLeftLeg(){
		if(leftLeg){
			leftLeg = false;
			limbCount--;
			return true;
		}
		return false;
	}
	
	public boolean popRightLeg(){
		if(rightLeg){
			rightLeg = false;
			limbCount--;
			return true;
		}
		return false;
	}
	
	public boolean popLeftArm(){
		if(leftArm){
			leftArm = false;
			limbCount--;
			return true;
		}
		return false;
	}
	
	public boolean popRightArm(){
		if(rightArm){
			rightArm = false;
			limbCount--;
			return true;
		}
		return false;
	}

	public boolean hasLeftLeg() {
		return leftLeg;
	}

	public boolean hasRightLeg() {
		return rightLeg;
	}

	public boolean hasLeftArm() {
		return leftArm;
	}

	public boolean hasRightArm() {
		return rightArm;
	}
	
	public int getLimbCount(){
		return limbCount;
	}
	
	private void RecoverHand(){
		for(int i = 0; i < cards.length; i++){
			cards[i].recoverCard();
		}
	}
	/**
	 * Plays a card (1-7 to 1-5 depending on amount of players).
	 * @param cardValue value of card played
	 * @return value of card played, -1 if error
	 */
	public int playCard(int cardValue){
		try {
			if(cards[cardValue-1].isInHand()){
				cards[cardValue-1].playCard();
				lastPlayedCard = cards[cardValue-1].value;
				return cards[cardValue-1].value;
			}
			else
				return -1;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Should play a card you have");
			return -1;
		}
	}
	
	public boolean popLimb(){
		boolean limbPopped = false;
		int limbToPop;
		while(!limbPopped && limbCount > 0){
			limbToPop = (int) (Math.random()*4);
			if(limbToPop == 0)
				limbPopped = popLeftArm();
			else if(limbToPop == 1)
				limbPopped = popLeftLeg();
			else if(limbToPop == 2)
				limbPopped = popRightArm();
			else if(limbToPop == 3)
				limbPopped = popRightLeg();
			else
				System.out.println("popLimb error, value shouldn't be reached.");
		}
		if(limbPopped){
			RecoverHand();
			return true;
		}
		else
			return false;
		
	}
}
