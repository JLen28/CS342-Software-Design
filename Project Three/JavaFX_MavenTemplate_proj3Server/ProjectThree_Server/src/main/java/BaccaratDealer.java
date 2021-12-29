import java.util.ArrayList;
import java.util.Collections;

public class BaccaratDealer {
	ArrayList<Card> deck;
	
	BaccaratDealer(){ // default constructor
		this.deck = new ArrayList<Card>();
	}
	BaccaratDealer(ArrayList<Card> d){ // argument constructor
		this.deck = d;
	}
	BaccaratDealer(BaccaratDealer bD){ // copy constructor
		this.deck = bD.deck;
	}
	public void generateDeck() {
		deck = new ArrayList<Card>();
		for(int i = 1; i < 10; i++) {
			Card crd = new Card("Spades",i); // add cards ace through 9 spades to the deck
			this.deck.add(crd);
		}
		for(int i = 0; i < 4; i++) {
			Card crd = new Card("Spades",0); // face cards evaluate to zero, there are 4 face cards...
			this.deck.add(crd);
		}
		//Done adding 13 spade cards//
		for(int i = 1; i < 10; i++) {
			Card crd = new Card("Clubs",i); // add cards ace through 9 spades to the deck
			this.deck.add(crd);
		}
		for(int i = 0; i < 4; i++) {
			Card crd = new Card("Clubs",0); // face cards evaluate to zero, there are 4 face cards...
			this.deck.add(crd);
		}
		//Done adding 13 club cards//
		for(int i = 1; i < 10; i++) {
			Card crd = new Card("Hearts",i); // add cards ace through 9 spades to the deck
			this.deck.add(crd);
		}
		for(int i = 0; i < 4; i++) {
			Card crd = new Card("Hearts",0); // face cards evaluate to zero, there are 4 face cards...
			this.deck.add(crd);
		}
		//Done adding 13 heart cards//
		for(int i = 1; i < 10; i++) {
			Card crd = new Card("Diamonds",i); // add cards ace through 9 spades to the deck
			this.deck.add(crd);
		}
		for(int i = 0; i < 4; i++) {
			Card crd = new Card("Diamonds",0); // face cards evaluate to zero, there are 4 face cards...
			this.deck.add(crd);
		}
	} // end of generate deck
	
	public void shuffleDeck() { // like generate deck but we shuffle the deck at the end
		deck = new ArrayList<Card>();
		for(int i = 1; i < 10; i++) {
			Card crd = new Card("Spades",i); // add cards ace through 9 spades to the deck
			this.deck.add(crd);
		}
		for(int i = 0; i < 4; i++) {
			Card crd = new Card("Spades",0); // face cards evaluate to zero, there are 4 face cards...
			this.deck.add(crd);
		}
		//Done adding 13 spade cards//
		for(int i = 1; i < 10; i++) {
			Card crd = new Card("Clubs",i); // add cards ace through 9 spades to the deck
			this.deck.add(crd);
		}
		for(int i = 0; i < 4; i++) {
			Card crd = new Card("Clubs",0); // face cards evaluate to zero, there are 4 face cards...
			this.deck.add(crd);
		}
		//Done adding 13 club cards//
		for(int i = 1; i < 10; i++) {
			Card crd = new Card("Hearts",i); // add cards ace through 9 spades to the deck
			this.deck.add(crd);
		}
		for(int i = 0; i < 4; i++) {
			Card crd = new Card("Hearts",0); // face cards evaluate to zero, there are 4 face cards...
			this.deck.add(crd);
		}
		//Done adding 13 heart cards//
		for(int i = 1; i < 10; i++) {
			Card crd = new Card("Diamonds",i); // add cards ace through 9 spades to the deck
			this.deck.add(crd);
		}
		for(int i = 0; i < 4; i++) {
			Card crd = new Card("Diamonds",0); // face cards evaluate to zero, there are 4 face cards...
			this.deck.add(crd);
		}
		Collections.shuffle(this.deck); // shuffle the deck
	}// end of shuffle deck
	
	public ArrayList<Card> dealHand(){ // draw the first two cards from the top of the deck
		ArrayList<Card> hand = new ArrayList<Card>(); // initializing a list to hold the result of the hand...
		hand.add(this.deck.get(0)); // pulling the first card off the top of the deck
		hand.add(this.deck.get(1)); // pulling the second card off the top fo the deck
		this.deck.remove(0); // remove the first card we pulled from the deck
		this.deck.remove(0);// remove the second card we pulled from the deck
		return hand; // return the hand
	}// end of dealHand
	
	public Card drawOne() { // draw the top card of the deck
		Card crd = this.deck.get(0); // pull a card from the top of the deck
		this.deck.remove(0); // remove from the top of the deck
		return crd; // return the card
	}
	public int deckSize() { // return the size of this deck at any given time
		return this.deck.size();
	} // end of deck size
	
	public void displayDeck() { // display the deck
		for(Card e : this.deck) {
			System.out.println(e.getCardSuite());
			System.out.println(e.getCardValue());
		}
			
		}
}