import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Iterator;
import java.util.ListIterator;

class GLLIteratorTest {
	 GenericLinkedList<Integer> b1;
	 
	 @Test
	 void iteratorTest() {
		 b1 = new GenericLinkedList<>(1);
		 Iterator<Integer> iter = b1.iterator();
		 assertFalse(iter.hasNext());
		 b1.addLast(2);
		 assertTrue(iter.hasNext());
		 assertEquals(2,iter.next());
		 assertFalse(iter.hasNext());
	 }
}