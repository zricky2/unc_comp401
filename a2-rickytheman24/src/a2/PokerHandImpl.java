package a2;

public class PokerHandImpl implements PokerHand {

	private Card[] cards;
	
	// constructor
	public PokerHandImpl(Card[] cards) {
		if (cards == null) {
			throw new RuntimeException("Empty Hand");
		}
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] == null) {
				throw new RuntimeException("Empty Card");
			}
		}
		this.cards = cards.clone(); 
	}

	public Card[] getCards() {
		return cards.clone();
	}

	
	public boolean contains(Card c) {
		for (int i = 0; i < cards.length; i++) {
			Card currentCard = cards[i];
			if (currentCard.equals(c)) {
				return true;
			}
		}
		return false;
	}

	public boolean isOnePair() {
		int counter = 0;
		for (int i = 0; i < cards.length; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i].getRank() > cards[j].getRank()) {
					Card tmp = cards[i];
					cards[i] = cards[j];
					cards[j] = tmp;
				}
			}
		}
		for (int i = 0; i < cards.length - 1; i++) {
			if (cards[i].getRank() == (cards[i + 1].getRank())) {
				counter++;
			}
		}
		if (counter == 1) {
			return true;
		} else {
			return false;
		}
	}

	/// fix two pair problem detected
	public boolean isTwoPair() {
		int counter = 0;
		for (int i = 0; i < cards.length; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i].getRank() > cards[j].getRank()) {
					Card tmp = cards[i];
					cards[i] = cards[j];
					cards[j] = tmp;
				}
			}
		}
		if (cards[0].getRank() == cards[1].getRank()) {
			if (cards[1].getRank() < cards[2].getRank()) {
				if (cards[2].getRank() == cards[3].getRank()) {
					if (cards[3].getRank() < cards[4].getRank()) {
						counter++;
					}
				}
			}
		}
		if (cards[0].getRank() == cards[1].getRank()) {
			if (cards[1].getRank() < cards[2].getRank()) {
				if (cards[2].getRank() < cards[3].getRank()) {
					if (cards[3].getRank() == cards[4].getRank()) {
						counter++;
					}
				}
			}
		}
		if (cards[0].getRank() < cards[1].getRank()) {
			if (cards[1].getRank() == cards[2].getRank()) {
				if (cards[2].getRank() < cards[3].getRank()) {
					if (cards[3].getRank() == cards[4].getRank()) {
						counter++;
					}
				}
			}
		}
	return (counter == 1);
	}

	public boolean isThreeOfAKind() {
		int counter = 0;
		for (int i = 0; i < cards.length; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i].getRank() > cards[j].getRank()) {
					Card tmp = cards[i];
					cards[i] = cards[j];
					cards[j] = tmp;
				}
			}
		}
		if (cards[0].getRank() == cards[1].getRank()) {
			if (cards[1].getRank() == cards[2].getRank()) {
				if (cards[2].getRank() < cards[3].getRank()) {
					if (cards[3].getRank() < cards[4].getRank()) {
					return true;
					}
				}
			}
		}
		if (cards[0].getRank() < cards[1].getRank()) {
			if (cards[1].getRank() == cards[2].getRank()) {
				if (cards[2].getRank() == cards[3].getRank()) {
					if (cards[3].getRank() < cards[4].getRank()) {
						return true;
					}
				}
			}
		}
		if (cards[0].getRank() < cards[1].getRank()) {
			if (cards[1].getRank() < cards[2].getRank()) {
				if (cards[2].getRank() == cards[3].getRank()) {
					if (cards[3].getRank() == cards[4].getRank()) {
						return true;
					}
				}
			}
		}
		return false;
		}

	public boolean isStraight() {
		int counter = 0;
		for (int i = 0; i < cards.length; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i].getRank() > cards[j].getRank()) {
					Card tmp = cards[i];
					cards[i] = cards[j];
					cards[j] = tmp;
				}
			}
		}
		for (int x = 0; x < cards.length - 1; x++) {
			if (cards[x].getRank() == cards[x + 1].getRank() - 1) {
				counter++;
			}
		}
		if (cards[0].getRank() == 2) {
			if (cards[1].getRank() == 3) {
				if (cards[2].getRank() == 4) {
					if (cards[3].getRank() == 5) {
						if (cards[4].getRank() == 14) {
							counter = 4;
						}
					}
				}
			}
		}
		if (counter == 4) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFlush() {
		int counter = 0;
		for (int i = 0; i < cards.length - 1; i++) {
			if ((cards[i].getSuit()) == (cards[i + 1].getSuit())) {
				counter++;
			}
		}
		return (counter == 4); 
	}

	public boolean isFullHouse() {
		for (int i = 0; i < cards.length; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i].getRank() > cards[j].getRank()) {
					Card tmp = cards[i];
					cards[i] = cards[j];
					cards[j] = tmp;
				}
			}
		}
		if (cards[0].getRank() == cards[1].getRank()) {
			if (cards[1].getRank() == cards[2].getRank()) {
				if (cards[2].getRank() < cards[3].getRank() && cards[3].getRank() == cards[4].getRank()) {
					return true;
				}
			}
		}
				if (cards[0].getRank() == cards[1].getRank()) {
					if (cards[1].getRank() < cards[2].getRank()) {
						if (cards[2].getRank() == cards[3].getRank()) {
							if (cards[3].getRank() == cards[4].getRank()) {
								return true;
							}
						}
					}
				}
				return false;
	}

	public boolean isFourOfAKind() {
		for (int i = 0; i < cards.length; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i].getRank() > cards[j].getRank()) {
					Card tmp = cards[i];
					cards[i] = cards[j];
					cards[j] = tmp;
				}
			}
		}
		if (cards[0].getRank() == cards[1].getRank()) {
			if (cards[1].getRank() == cards[2].getRank()) {
				if (cards[2].getRank() == cards[3].getRank()) {
					if (cards[3].getRank() < cards[4].getRank()) {
						return true;
					}
				}
			}
		}
		if (cards[0].getRank() < cards[1].getRank()) {
			if (cards[1].getRank() == cards[2].getRank()) {
				if (cards[2].getRank() == cards[3].getRank()) {
					if (cards[3].getRank() == cards[4].getRank()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isStraightFlush() {
		for (int i = 0; i < cards.length; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				if (cards[i].getRank() > cards[j].getRank()) {
					Card tmp = cards[i];
					cards[i] = cards[j];
					cards[j] = tmp;
				}
			}
		}
		if (isStraight() && isFlush()) {
			return true;
		} else {
			return false;
		}
	}
public int getHandTypeValue() {
	if (isFourOfAKind()) {
		return 8;
	} else if (isFullHouse()) {
		return 7;
	} else if (isFlush()) {
		if (isStraightFlush()) {
			return 9;
			}
		return 6;
	} else if (isStraight()) {
		if (isStraightFlush()) {
			return 9;
			}
		return 5;
	} else if (isThreeOfAKind()) {
		return 4;
	} else if (isTwoPair()) {
		return 3;
	} else if (isOnePair()) {
		return 2;
	} else {
		return 1;
	}
}

public int getHandRank() {	
	if (isStraightFlush()) {
		if (cards[cards.length - 1].getRank() == 14 && cards[0].getRank() == 2) {
			return 5;
		}
		return cards[cards.length - 1].getRank();
	} else if (isFourOfAKind()) {
		return cards[2].getRank();
	} else if (isFullHouse()) {
		return cards[2].getRank();
	} else if (isFlush()) {
		return cards[cards.length - 1].getRank();
	} else if (isStraight()) {
		if (cards[cards.length - 1].getRank() == 14 && cards[0].getRank() == 2) {
			return 5;
		}
		return cards[cards.length - 1].getRank();
	} else if (isThreeOfAKind()) {
		return cards[2].getRank();
	} else if (isTwoPair()) {
			return cards[3].getRank();
	} else if (isOnePair()) {
		for (int i = 0; i < cards.length - 1; i++) {
			if ((cards[i].getRank() == cards[i + 1].getRank())) {
				return cards[i].getRank();
			}
		}
	} else {
		return cards[cards.length - 1].getRank();
	}
	return 0;
}

	public int compareTo(PokerHand other) {
		if (other == null) {
			throw new RuntimeException("Empty Hand");
		}
		if (getHandTypeValue() < other.getHandTypeValue()) {
			return -1;
		} else if (getHandTypeValue() > other.getHandTypeValue()) {
			return 1;
		} else {
			if (getHandRank() < other.getHandRank()) {
				return -1;
			} else if (getHandRank() > other.getHandRank()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}

