import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class CardTest {
	
	Card crd;
	
	@Test
	void cardDefConTest() {
		crd = new Card();
		assertEquals(0,crd.value);
		assertEquals("",crd.suite);
	}
	
	@Test
	void cardArgConTest() {
		crd = new Card("Spade",10);
		assertEquals(10,crd.value);
		assertEquals("Spade",crd.suite);
	}
	
	@Test
	void cardCopyConTest() {
		crd = new Card("Heart",1);
		Card crd2 = new Card(crd);
		assertEquals(crd2.suite,crd.suite);
		assertEquals(crd2.value,crd.value);
	}
}