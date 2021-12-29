import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;




public class BaccaratGameLogicTest {
	BaccaratDealer bD;
	
	@Test
	void handTotalTest() {
		bD = new BaccaratDealer();
		bD.generateDeck();
		ArrayList<Card> result = bD.dealHand();
		assertEquals(3,BaccaratGameLogic.handTotal(result));
		assertEquals(50,bD.deckSize());
		Card crd = new Card("Spades",6);
		Card crd2 = new Card("Hearts",6);
		ArrayList<Card> result2 = new ArrayList<>();
		result2.add(crd);
		result2.add(crd2);
		assertEquals(2,BaccaratGameLogic.handTotal(result2));
	}
	
	
	@Test
	void evalBankerTest() {
		bD = new BaccaratDealer();
		bD.generateDeck();
		Card nullcrd = new Card ("NULL",-1);
		ArrayList<Card> result = bD.dealHand();
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(result,nullcrd));
		Card crd1 = new Card("Spades", 2);
		Card crd2 = new Card("Spades", 2);
		ArrayList<Card> result2 = new ArrayList<>();
		result2.add(crd1);
		result2.add(crd2);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(result2, nullcrd));
		Card crd3 = new Card("Spades", 3);
		Card crd4 = new Card("Spades", 3);
		ArrayList<Card> result3 = new ArrayList<>();
		result3.add(crd3);
		result3.add(crd4);
		Card tstcrd = new Card ("TEST", 6);
		assertTrue(BaccaratGameLogic.evaluateBankerDraw(result3, tstcrd));
		Card tstcrd2 = new Card ("TEST2", 4);
		assertFalse(BaccaratGameLogic.evaluateBankerDraw(result3, tstcrd2));
		
		
	}
	
	@Test
	void whoWonTest() {
		Card crd1 = new Card ("Spades", 5);
		Card crd2 = new Card ("Hearts", 3);
		ArrayList<Card> hnd1 = new ArrayList<>();
		hnd1.add(crd1);
		hnd1.add(crd2);
		Card crd3 = new Card ("Diamonds", 3);
		Card crd4 = new Card ("Clubs", 3);
		ArrayList<Card> hnd2 = new ArrayList<>();
		hnd2.add(crd3);
		hnd2.add(crd4);
		String result = BaccaratGameLogic.whoWon(hnd1, hnd2);
		assertTrue(result.equals("Player"));
		String result2 = BaccaratGameLogic.whoWon(hnd2, hnd1);
		System.out.println(result2);
		assertTrue(result2.equals("Banker"));
		String result3 = BaccaratGameLogic.whoWon(hnd1, hnd1);
		System.out.println(result3);
		assertTrue(result3.equals("Draw"));
	}
}