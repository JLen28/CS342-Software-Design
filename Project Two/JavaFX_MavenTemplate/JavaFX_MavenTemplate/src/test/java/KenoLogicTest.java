import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.ArrayList;

class KenoLogicTest {
	

	KenoLogic k1 = new KenoLogic();
	

	
	@Test
	void defaultConTest() {
		KenoLogic k2 = new KenoLogic();
		KenoLogic k3 = new KenoLogic();
		assertEquals(k2,k2);
	}
	
	@Test
	void argConTest() {
		ArrayList<Integer> m1 = new ArrayList<Integer>();
		ArrayList<Integer> m2 = new ArrayList<Integer>();
		ArrayList<Integer> m3 = new ArrayList<Integer>();
		KenoLogic k2 = new KenoLogic(m1,m2,m3,0,0,0);
		assertEquals(k2.getBetCard(),m1);
		assertEquals(k2.getCash(),0);
		assertEquals(k2.getDraws(),0);
	}
	
	@Test
	void copyConTest() {
		KenoLogic k2 = new KenoLogic();
		k2.doQuickPick(10);
		KenoLogic k3 = k2;
		assertEquals(k2.getBetCard(),k3.getBetCard());
	}
	
	@Test
	void clearTest() {
		KenoLogic k1 = new KenoLogic();
		k1.doQuickPick(8);
		k1.updateCash(10);
		k1.updateDraws(10);
		k1.updateNumRounds(10);
		k1.clear();
		assertEquals(0,k1.getBetCardSize());
		assertEquals(0,k1.getCash());
		assertEquals(0,k1.getRounds());
		assertEquals(0,k1.getDraws());
	
	}
	
	@Test
	void updateCashTest() {
		KenoLogic k1 = new KenoLogic();
		k1.updateCash(220);
		assertEquals(220,k1.getCash());
	}
	
	@Test
	void updateRoundsTest() {
		KenoLogic k1 = new KenoLogic();
		k1.updateNumRounds(4);
		assertEquals(4,k1.getRounds());
	}
	
	@Test
	void updateBetCardTest() {
		KenoLogic k1 = new KenoLogic();
		ArrayList<Integer> m1 = new ArrayList<>();
		m1.add(1);
		m1.add(2);
		m1.add(4);
		m1.add(90);
		k1.updateBetCard(m1);
		assertEquals(m1,k1.getBetCard());
	}
	
	@Test
	void updateDrawsTest() {
		KenoLogic k1 = new KenoLogic();
		k1.updateDraws(20);
		assertEquals(20,k1.getDraws());
	}
	
	@Test
	void getBetCardTest() {
		KenoLogic k1 = new KenoLogic();
		k1.doQuickPick(8);
		assertEquals(8,k1.getBetCard().size());
		k1.clearBetCard();
		assertEquals(0,k1.getBetCard().size());
	}
	
	@Test
	void clearBetCardTest() {
		KenoLogic k1 = new KenoLogic();
		k1.doQuickPick(10);
		k1.updateCash(200);
		k1.updateNumRounds(4);
		k1.updateDraws(4);
		k1.clearBetCard();
		assertEquals(0,k1.getBetCard().size());
		assertEquals(200,k1.getCash());
		assertEquals(4,k1.getDraws());
		assertEquals(4,k1.getRounds());
	}
	
	@Test
	void drawListTest() {
		KenoLogic k1 = new KenoLogic();
		k1.doQuickPick(4);
		k1.doDraws(k1.getBetCard(), k1.getDraws(), k1.getCash());
		assertEquals(0,k1.getDrawList().size());
	}
	
	@Test
	void getRoundsTest() {
		ArrayList<Integer> m1 = new ArrayList<>();
		KenoLogic k1 = new KenoLogic(m1,m1,m1,10,10,10);
		assertEquals(10,k1.getRounds());
		
	}
	
	@Test
	void getDrawsTest() {
		ArrayList<Integer> m1 = new ArrayList<>();
		KenoLogic k1 = new KenoLogic(m1,m1,m1,10,10,10);
		assertEquals(10,k1.getDraws());
		
	}
	
	@Test
	void getCashTest() {
		ArrayList<Integer> m1 = new ArrayList<>();
		KenoLogic k1 = new KenoLogic(m1,m1,m1,10,10,10);
		assertEquals(10,k1.getCash());
		
	}
	
	@Test
	void constTestTwo() {
		ArrayList<Integer> m1 = new ArrayList<>();
		m1.add(1);
		m1.add(2);
		m1.add(3);
		m1.add(4);
		KenoLogic k1 = new KenoLogic(m1,m1,m1,10,10,10);
		KenoLogic k2 = new KenoLogic(k1);
		assertEquals(k1.getBetCard(),k2.getBetCard());
		k1.clear();
		KenoLogic k3 = new KenoLogic(k1);
		assertEquals(k3.getBetCard(),k1.getBetCard());
		
	}
	
	@Test
	void EmptyTest() {
		ArrayList<Integer> m1 = new ArrayList<>();
		m1.add(1);
		m1.add(2);
		m1.add(3);
		m1.add(4);
		KenoLogic k1 = new KenoLogic(m1,m1,m1,10,20,30);
		k1.clear();
		assertTrue(k1.getBetCard().isEmpty());
		assertTrue(k1.getDrawList().isEmpty());
		assertTrue(k1.getmatchList().isEmpty());
		
	}
	
	@Test
	void updateCashTestTwo() {
		KenoLogic k1 = new KenoLogic();
		k1.updateCash(-1);
		assertEquals(0,k1.getCash());
		k1.updateCash(-0.9);
		assertEquals(0,k1.getCash());
		k1.updateCash(200);
		assertEquals(200,k1.getCash());
	}
	
	@Test
	void updateRoundsTestTwo() {
		KenoLogic k1 = new KenoLogic();
		k1.updateNumRounds(-1);
		assertEquals(0,k1.getRounds());
		k1.updateNumRounds(-9999999);
		assertEquals(0,k1.getRounds());
		k1.updateNumRounds(2);
		assertEquals(2,k1.getRounds());
	}
	
	@Test
	void updateDrawsTestTwo() {
		KenoLogic k1 = new KenoLogic();
		k1.updateDraws(-1);
		assertEquals(0,k1.getDraws());
		k1.updateDraws(-9999999);
		assertEquals(0,k1.getDraws());
		k1.updateDraws(2);
		assertEquals(2,k1.getDraws());
	}
	
	@Test
	void quickPickTest() {
		KenoLogic k1 = new KenoLogic();
		k1.doQuickPick(1);
		assertEquals(1,k1.getBetCard().size());
		k1.doQuickPick(4);
		assertEquals(4,k1.getBetCard().size());
		k1.doQuickPick(8);
		assertEquals(8,k1.getBetCard().size());
		k1.doQuickPick(10);
		assertEquals(10,k1.getBetCard().size());
	}
	
	@Test
	void printBetCardTest() {
		ArrayList<Integer> m1 = new ArrayList<>();
		m1.add(1);
		m1.add(2);
		m1.add(3);
		KenoLogic k1 = new KenoLogic();
		k1.updateBetCard(m1);
		assertEquals(" 1 2 3",k1.printBetCard());
	}
	
	@Test
	void printmatchListTest() {
		ArrayList<Integer> m1 = new ArrayList<>();
		m1.add(1);
		m1.add(2);
		m1.add(3);
		KenoLogic k1 = new KenoLogic(m1,m1,m1,0,0,0);
		assertEquals(" 1 2 3",k1.printMatchList());
	}
	
	@Test
	void printdrawListTest() {
		ArrayList<Integer> m1 = new ArrayList<>();
		m1.add(1);
		m1.add(2);
		m1.add(3);
		KenoLogic k1 = new KenoLogic(m1,m1,m1,0,0,0);
		assertEquals(" 1 2 3",k1.printDrawList());
	}
	
	@Test
	void printOthersTest() {
		KenoLogic k1 = new KenoLogic();
		k1.updateCash(0.0);
		k1.updateDraws(2);
		k1.updateNumRounds(3);
		assertEquals("0.0",k1.printCash());
		assertEquals("2",k1.printDraws());
		assertEquals("3",k1.printRounds());
	}
	
	@Test
	void RigorTest() {
		KenoLogic k1 = new KenoLogic();
		k1.getBetCard().add(10);
		k1.getBetCard().add(20);
		k1.doQuickPick(8);
		assertEquals(8,k1.getBetCardSize());
		k1.clear();
		k1.updateCash(-99);
		k1.updateDraws(2309);
		assertEquals(0,k1.getCash());
		assertEquals(2309,k1.getDraws());
		k1.clear();
		KenoLogic k2 = new KenoLogic(k1);
		assertNotEquals(k1,k2);
		
	}
	
	
	
	
}