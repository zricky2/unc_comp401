package a2;
import java.util.*;
import a2.Card.Suit;
public class HandEvaluator {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		handEvaluate(scanner);
	}
	
	public static Card.Suit conSuit(String suit) { 
		switch (suit) {
		case "S": return Suit.SPADES;
		case "H": return Suit.HEARTS;
		case "D": return Suit.DIAMONDS;
		case "C": return Suit.CLUBS;
		}
		// This will never happen:
		return null;
	}
	
	public static void handEvaluate(Scanner scanner) {
		int counter = 0;
		int counterTwo = 0;
		Card[] cardArray = new CardImpl[5];
		
		// loop the input
		int numberOpponents = 1;
		for (int i = 0; numberOpponents != 0; i++) {
			 numberOpponents = scanner.nextInt();
			if (numberOpponents == 0) {
				break;
			}
		for (int j=0; j<5; j++) {
				int newRank = scanner.nextInt();
				String suitString = scanner.next();
				Card.Suit newSuit = conSuit(suitString);
				cardArray[j] = new CardImpl(newRank, newSuit);	
		}
		// create deck
		PokerHand hand = new PokerHandImpl(cardArray);
		PokerHand [] opponents = new PokerHand [numberOpponents];
		counter = 0;
		// loop through 10000 games
		for (int a = 0; a < 10000; a++) {
			Deck deck = new DeckImpl();
		for (int z =0; z < 5; z++) {
			deck.findAndRemove(cardArray[z]);
		}
			for (int p = 0; p < numberOpponents; p++) { 
				opponents [p] = deck.dealHand();
			}
				
				counterTwo = 0;
				for (int p=0; p < numberOpponents; p++) {
					if ((hand.compareTo(opponents [p])) == 1) {
						counterTwo++;
					}
			}
		if (counterTwo == numberOpponents) {
			counter++;
			
		}
		}
		
		System.out.println((counter * 100)/ 10000);
		// System.out.println(hand.getHandTypeValue());
		
	}

	
	}
}
	
	


	

