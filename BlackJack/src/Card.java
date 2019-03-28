public class Card {
	
	private String suit;
	private String rank;
	private int PointValue;
	
	public Card(String rank, String suit, int PointValue) {
		this.rank = rank;
		this.suit = suit;
		this.PointValue = PointValue;
	}
	
	
	public String getSuit() {
		return suit;
   }

	
	public String getRank() {
		return rank;
	}

	public int getPointValue() {
		return PointValue;
	}
	
	public void setPointValue(int PointValue) {
		this.PointValue = PointValue;
	}
	
	public boolean matches(Card otherCard) {
		return otherCard.getSuit().equals(this.getSuit())
				&& otherCard.getRank().equals(this.getRank())
				&& otherCard.getPointValue() == this.getPointValue();
	}
	
	public String toString() {
		return rank + " of " + suit + " (point value = " + PointValue + ")";
	}

	
}

