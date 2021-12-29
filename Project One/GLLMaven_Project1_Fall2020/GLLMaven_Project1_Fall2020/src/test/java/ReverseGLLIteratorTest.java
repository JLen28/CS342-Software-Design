import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Iterator;
import java.util.ListIterator;

 class ReverseGLLIteratorTest {
	GenericLinkedList<Integer> b1;
	
	@Test
	void ReverseGLLIteratorTestOne() {
		b1 = new GenericLinkedList<>(1);
		Iterator<Integer> iter = b1.descendingiterator();
		assertFalse(iter.hasNext());
		b1.addFirst(2);
		assertTrue(iter.hasNext());
		assertEquals(2,iter.next());
		assertFalse(iter.hasNext());
	}
}