import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Iterator;
import java.util.ListIterator;

class GLListIteratorTest {
	
	GenericLinkedList<Integer> b1;
	
	@Test
	void GLListIteratorTestOne() { // small test to see if all the method functions are operating accordingly...
		b1 = new GenericLinkedList<>(1);
		b1.addFirst(2);
		b1.addLast(3);
		ListIterator<Integer> iter = b1.listIterator(0);
		assertTrue(iter.hasNext());
		assertEquals(1,iter.next());
		assertEquals(2,iter.previous());
		assertFalse(iter.hasPrevious());
		assertEquals(1,iter.nextIndex());
		assertEquals(0,iter.previousIndex());
	}
}