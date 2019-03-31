import java.util.ArrayList;
import java.util.List;

public class Hand implements Comparable<Player>{
	private int currentCards;
	
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	private static final int[] POINT_VALUES =
		{11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	
	private ArrayList<Card> cards;

	
	public Hand(ArrayList<Card> cards) {
		this.cards = cards;
		this.currentCards = cards.size();
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
	
	public void addCard(Card c) {
		cards.add(c);
	}
 
	public int cardSum() {
		 int sum = 0;
		 for (int i = 0; i<cards.size(); i++) {
			 sum+= cards.get(i).getPointValue();
		 }
		 return sum;
	 }
	public boolean isBlackJack(){
		if(cards.size() == 2 && cardSum() == 21 && containsJorQorK() == true) {
			return true;
		}else {
			return false;
		}
	}
	 	public boolean isBust(){
	 		if(cardSum()>21) {
	 			return true;
	 		}else {
	 			return false;
	 		}
	 	}
	 public boolean containsJorQorK() {
			for(int i = 0; i< cards.size(); i++) {
				
				if(cards.get(i).getRank().equals("jack")) {
					return true;
				}else if(cards.get(i).getRank().equals("king")){
					return true;
				}else if(cards.get(i).getRank().equals("queen")) {
					return true;
				}else if(cards.get(i).getRank().equals("10")) {
					return true;
				}
			}
				return false;
			
		}
	 
	 public void oneOrEleven() {
		 if(cardSum()> 21) {
			for(int i = 0; i< cards.size(); i++) {
				
				if(cards.get(i).getRank().equals("ace")) {
					cards.get(i).setPointValue(1);
				}
			}
		 }
		
	}
	 @Override
		public int compareTo(Player dealer) {
			 int dealertotal = dealer.getHand().cardSum();
			 int playertotal = cardSum();
			 
			 if(dealer.getHand().isBust()==false && dealertotal>playertotal) {
				 return 1;//dealer wins
			 }else if(isBust()==false && dealertotal<playertotal) {
				 return -1;
			 }else if(dealer.getHand().isBust()==true && isBust()==false) {
				 return -1;// player wins
			 }else if(dealer.getHand().isBust()==false && isBust()==true) {
				 return 1;
			 }else {
				 return 0;
			 }
		 }
}
