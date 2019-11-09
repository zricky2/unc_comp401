package a2;

public class CardImpl implements Card {
	
	private int rank; 
	
	private Card.Suit suite;
	
	public CardImpl (int rank, Card.Suit suite) {
		this.rank = rank; {
			if (rank < 2 || rank > 14) {
				throw new RuntimeException("Rank is invalid");
			};
		}
		this.suite = suite;
	}
	
	public int getRank() {
		if (rank < 2 || rank > 14) {
			throw new RuntimeException("Rank is invalid");
		}
		return rank;
	}
	public Card.Suit getSuit() {
		return suite; 
	}
	public String toString() {
		if (rank == 11) {
			return "Jack of " + suite;
		} else if (rank == 12) {
			return "Queen of "+ suite;
		} else if (rank == 13) {
			return "King of " + suite;
		} else if (rank == 14) {
			return "Ace of " + suite;
		} else {
			return rank + " of " + suite;
		}
	}
	public boolean equals(Card other) {
		if (rank == other.getRank() && suite.equals(other.getSuit())) {
			return true;
		} else {
			return false;
		}
	}
}
