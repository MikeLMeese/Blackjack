import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;
    String a = System.lineSeparator();

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void createFullDeck() {
        // generate cards
        for (Suits cardSuit: Suits.values()) {
            for (Values cardValue: Values.values()) {
                this.deck.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public Card getCard(int i) {
        return this.deck.get(i);
    }

    public void removeCard(int i) {
        this.deck.remove(i);
    }

    public void addCard(Card addCard) {
        this.deck.add(addCard);
    }

    // Get the size of the deck
    public int deckSize() {
        return this.deck.size();
    }

    // Draws from the deck
    public void draw(Deck comingFrom) {
        this.deck.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    // This will move cards back into the deck to continue playing
    public void moveAllToDeck(Deck moveTo) {
        int deckSize = this.deck.size();

        // Returns all cards to the deck
        for (int i = 0; i < deckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }
        for (int i = 0; i < deckSize; i++) {
            this.removeCard(0);
        }
    }

    // Find total value of cards in hand
    public int cardsValue() {
        int total = 0;
        int aces = 0;

        // Get the value of every card in the deck
        for (Card card : this.deck) {
            switch (card.getValue()) {
                case TWO -> total += 2;
                case THREE -> total += 3;
                case FOUR -> total += 4;
                case FIVE -> total += 5;
                case SIX -> total += 6;
                case SEVEN -> total += 7;
                case EIGHT -> total += 8;
                case NINE -> total += 9;
                case TEN, JACK, QUEEN, KING -> total += 10;
                case ACE -> aces += 1;
            }
        }

        // If player total is less than 10, then ace is 11. Otherwise, ace is 1
        for (int i = 0; i < aces; i++) {
            if (total > 10) {
                total += 1;
            } else {
                total += 11;
            }
        }

        return total;
    }

    @Override
    public String toString() {
        String result = "";
        for (Card card : deck) {
            result += card;
            result += a;
        }
        return result;
    }
}