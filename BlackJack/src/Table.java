import java.util.ArrayList;
import java.util.Scanner;

public class Table extends ArrayList<Card> {
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	private static final int[] POINT_VALUES =
		{11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

	Deck newdeck = new Deck(RANKS, SUITS, POINT_VALUES);
	
	public void game(Player newPlayer, Player dealer) {
		Scanner hitstand = new Scanner(System.in);
		Scanner bet = new Scanner(System.in);
		String key = "";
		
		while(!key.equalsIgnoreCase("Q") && newPlayer.getChips()>0) {
			System.out.println("Welcome to BlackJack.\nYou have " + newPlayer.getChips() +" chips! Type Q to quit or any key to play.");
			newdeck.shuffle();
			key = hitstand.nextLine();
			
			if(key.equalsIgnoreCase("q")) {
				break;
			}
			String hitorstand = "";
			System.out.println("How much would you like to bet?");
			int chips = bet.nextInt();
			
			while(chips>newPlayer.getChips()) {
				System.out.println("How much would you like to bet?");
				chips = bet.nextInt();
			}
			
			dealer.getHand().addCard(newdeck.deal());
			System.out.println("The dealer's first card is " + dealer.getHand().getCards());
			
			newPlayer.getHand().addCard(newdeck.deal());
			newPlayer.getHand().addCard(newdeck.deal());
			newPlayer.getHand().oneOrEleven();
			boolean blackjack = newPlayer.getHand().isBlackJack();
			if(blackjack == true) {
				System.out.println("Congrats! You have BlackJack! You will receive double your bet! ");
				newPlayer.setChips(chips*2 + newPlayer.getChips());

			}else {
				System.out.println("Your cards are " + newPlayer.getHand().getCards() + "\nAnd the sum is " + newPlayer.getHand().cardSum()+"\nHit or Stand?");
				hitorstand = hitstand.nextLine();
				while(!hitorstand.equalsIgnoreCase("stand") && newPlayer.getHand().isBust()==false && !hitorstand.equalsIgnoreCase("Q")) {
					if(hitorstand.equalsIgnoreCase("Q")) {
						break;
					}
					
					newPlayer.getHand().addCard(newdeck.deal());
					newPlayer.getHand().oneOrEleven();
					if(newPlayer.getHand().isBust()==true) {
						System.out.println("Your cards are " + newPlayer.getHand().getCards() + "\nwith a sum of " + newPlayer.getHand().cardSum()+" You busted!\n");
						newPlayer.setChips(newPlayer.getChips() - chips);
					}else {
						System.out.println("Your cards are " + newPlayer.getHand().getCards() + "\nwith a sum of " + newPlayer.getHand().cardSum()+"\nHit or Stand?");
						hitorstand = hitstand.nextLine();
					}
					
				}
				
				
				}
			if(newPlayer.getHand().isBust() == false && !hitorstand.equalsIgnoreCase("q")) {
				dealer.getHand().addCard(newdeck.deal());
				dealer.getHand().oneOrEleven();
				
				while(dealer.getHand().cardSum() < 17) {
					dealer.getHand().addCard(newdeck.deal());
					dealer.getHand().oneOrEleven();
				}
				System.out.println("The dealer's cards are" + dealer.getHand().getCards() + "\nwith a sum of " + dealer.getHand().cardSum() );
				
				if(newPlayer.getHand().compareTo(dealer)<0) {
					System.out.println("Congratulations! You won and received your bet of " + chips + "!\n");
					newPlayer.setChips(chips + newPlayer.getChips());
				}else if(newPlayer.getHand().compareTo(dealer)>0) {
					System.out.println("I'm sorry you lost! You lost your bet of " + chips + "!\n");
					newPlayer.setChips(newPlayer.getChips() - chips );
				}else {
					System.out.println("It's a tie!\n");
				}
			
			}
			newPlayer.getHand().getCards().clear();
			dealer.getHand().getCards().clear();
			
		}
		hitstand.close();
		bet.close();
		
		
	}
	
	
	public static void main(String[] args) {
		Table table = new Table();
		Player dealer = new Player(0, new Hand(new ArrayList<Card>()));
		Player newPlayer = new Player(30, new Hand(new ArrayList<Card>()));
		table.game(newPlayer, dealer);
	}
}
