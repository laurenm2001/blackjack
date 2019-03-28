public class Player{
	public int chips;
	private Hand hand;
	
	public Player(int chips, Hand hand) {
		this.chips = chips;
		this.hand = hand;
	}
	
	public int getChips(){
		return chips;
	}
	
	public void setChips(int chips) {
		this.chips = chips;
	}
	
	public Hand getHand(){
		return hand;
	}
	
	public void setHand(Hand hand) {
		this.hand = hand;
	}
}