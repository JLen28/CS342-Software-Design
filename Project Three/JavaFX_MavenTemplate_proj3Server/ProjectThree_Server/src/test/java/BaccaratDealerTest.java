import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BaccaratDealerTest {
	BaccaratDealer bD;
	
	@Test
	void genDeckTest() {
		bD = new BaccaratDealer();
		bD.generateDeck();
		assertEquals(52,bD.deckSize());
	}
	
	@Test
	void DealerArgConTest() {
		ArrayList<Card> deck = new ArrayList<>();
		Card crd = new Card("Spades",1);
		deck.add(crd);
		bD = new BaccaratDealer(deck);
		assertEquals(deck,bD.deck);
		
	}
	
	@Test 
	void DealerCopyConTest() {
		ArrayList<Card> deck = new ArrayList<>();
		Card crd = new Card("Spades",1);
		Card crd2 = new Card("Spades",2);
		Card crd3 = new Card("Spades",3);
		deck.add(crd);
		deck.add(crd2);
		deck.add(crd3);
		BaccaratDealer bD2 = new BaccaratDealer(deck);
		bD = new BaccaratDealer(bD2);
		assertEquals(bD.deck,bD2.deck);
		
	}
	
	@Test
	void dealHandTest() {
		bD = new BaccaratDealer();
		bD.generateDeck();
		ArrayList<Card> result = bD.dealHand();
		assertEquals(50,bD.deckSize());
		assertEquals(1,result.get(0).getCardValue());
		assertEquals(2,result.get(1).getCardValue());
		assertEquals("Spades",result.get(0).getCardSuite());
		assertEquals("Spades",result.get(1).getCardSuite());
	}
	
	@Test
	void dealHandTestTwo() {
		bD = new BaccaratDealer();
		bD.generateDeck();
		bD.shuffleDeck();
		bD.dealHand();
		bD.dealHand();
		bD.drawOne();
		assertEquals(47,bD.deckSize());
		bD.shuffleDeck();
		ArrayList<Card> result = new ArrayList<>(bD.dealHand());
		assertEquals(2,result.size());
		
	}
	
	@Test
	void drawOneTest() {
		bD = new BaccaratDealer();
		bD.generateDeck();
		Card crd = bD.drawOne();
		assertEquals(1,crd.getCardValue());
		assertEquals("Spades",crd.getCardSuite());
		assertEquals(51,bD.deckSize());
		Card crd2 = bD.drawOne();
		assertEquals(2,crd2.getCardValue());
		assertEquals("Spades",crd2.getCardSuite());
		assertEquals(50,bD.deckSize());
	}
	
	@Test
	void DrawOneTestTwo() {
		bD = new BaccaratDealer();
		bD.generateDeck();
		bD.shuffleDeck();
		bD.drawOne();
		assertEquals(51,bD.deckSize());
		bD.dealHand();
		assertEquals(49,bD.deckSize());
		bD.shuffleDeck();
		bD.drawOne();
		bD.drawOne();
		assertEquals(50,bD.deckSize());
	}
	
	@Test
	void shuffleTest() {
		bD = new BaccaratDealer();
		bD.generateDeck();
		bD.shuffleDeck();
		assertEquals(52,bD.deckSize());
		
	}
	
	@Test
	void shuffleTestTwo() {
		bD = new BaccaratDealer();
		bD.generateDeck();
		bD.shuffleDeck();
		bD.dealHand();
		bD.drawOne();
		assertEquals(49,bD.deckSize());
		bD.shuffleDeck();
		assertEquals(52,bD.deckSize());
	}
}