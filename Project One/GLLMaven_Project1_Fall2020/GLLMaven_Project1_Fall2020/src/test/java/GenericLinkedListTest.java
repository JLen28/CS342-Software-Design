import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


 class GenericLinkedListTest {
	 
	 GenericLinkedList<Integer> b1;
	 
	 
	 @Test
	 void getHeadTest() { // checking the constructor is working effectively...
		 b1 = new GenericLinkedList<>(10);
		 b1.addFirst(100);
		 assertEquals(100, b1.getHeadData());
		 b1.addFirst(300);
		 assertEquals(300,b1.getHeadData());
		 assertEquals(100,b1.getHeadNext());
	 }
	 

	 
	 @Test
	 void getLengthTest() { // checking that the length has been properly incremented...
		 b1 = new GenericLinkedList<>(20);
		 assertEquals(1,b1.size());
		 b1.addFirst(30);
		 b1.addFirst(24);
		 b1.addLast(19);
		 assertEquals(4,b1.size());
	 }
	 
	 @Test
	 void addFirstTest() { // testing the addFirst method is correctly adding to the front of the list..
		 b1 = new GenericLinkedList<>(10);
		 b1.addFirst(20);
		 b1.addFirst(56);
		 b1.addLast(89);
		 assertEquals(4,b1.size());
		 assertEquals(56,b1.getHeadData());
	 }
	 
	 @Test
	 void addLastTest() { // testing the addLast method making sure it is correctly adding to the back of the list.. 
		 b1 = new GenericLinkedList<>(10);
		 assertEquals(10,b1.getTailData());
		 assertEquals(10,b1.getHeadData());
		 b1.addLast(20);
		 b1.addLast(30);
		 assertEquals(30,b1.getTailData());
		 b1.addLast(40);
		 assertEquals(40,b1.getTailData());
		 assertEquals(10,b1.getHeadData());
		 b1.addFirst(50);
		 assertEquals(50,b1.getHeadData());
	 }
	 
	 @Test
	 void containsTest( ) { // testing the contains method making sure it can properly find a data value within the LL...
		 b1 = new GenericLinkedList<>(10);
		 b1.addLast(20);
		 b1.addFirst(30);
		 b1.addLast(60);
		 assertTrue(b1.contains(30));
		 assertTrue(b1.contains(60));
		 assertFalse(b1.contains(999));
	 }
	
	 @Test
	 void removeTest() { // Testing head removal within the remove function...
		 b1 = new GenericLinkedList<>(20);
		 //assertFalse(b1.remove(999));
		 assertEquals(20,b1.getHeadData());
		 assertTrue(b1.remove(20));
		 assertFalse(b1.remove(20));
		 b1.addFirst(10);
		 assertTrue(b1.remove(10));
		 assertFalse(b1.remove(10));
		 b1.addFirst(30);
		 b1.addLast(40);
		 assertTrue(b1.remove(30));
		 assertEquals(40,b1.getHeadData());
		 assertTrue(b1.remove(40));
		 assertEquals(0,b1.size());

	 }
	 @Test
	 void removeTestTwo() { // Testing of more removal functionality this time focusing on body and tail removals...
		 b1 = new GenericLinkedList<>(1);
		 b1.addLast(2);
		 assertTrue(b1.remove(2));
		 b1.addFirst(0);
		 b1.addLast(3);
		 assertTrue(b1.remove(1));
		 assertTrue(b1.remove(0));
		 assertTrue(b1.remove(3));
	 }
	 
	 @Test
	 void clearTest() { // Testing clear functionality...
		 b1 = new GenericLinkedList<>(1);
		 b1.clear();
		 assertEquals(0,b1.size());
		 b1.addFirst(2);
		 b1.addLast(3);
		 b1.addLast(4);
		 b1.clear();
		 assertFalse(b1.remove(2));
		 assertFalse(b1.remove(3));
		 assertFalse(b1.remove(4));
		 assertEquals(0,b1.size());
	 }
	 
	 @Test
	 void getTest() { // Testing get functionality...
		 b1 = new GenericLinkedList<>(1);
		 assertEquals(1, b1.get(0));
		 b1.addFirst(2);
		 assertEquals(2,b1.get(0));
		 assertEquals(1,b1.get(1));
		 b1.addLast(3);
		 assertEquals(null,b1.get(4));
		 assertEquals(3,b1.get(2));
		 assertEquals(null,b1.get(-1));
	 }
	 
	 @Test
	 void setTest() { // Testing set functionality...
		 b1 = new GenericLinkedList<>(1);
		 assertEquals(1,b1.set(0, 2));
		 assertEquals(2,b1.getHeadData());
		 b1.addLast(3);
		 b1.addFirst(4);
		 assertEquals(3,b1.set(2,5));
		 assertEquals(5,b1.get(2));
	 }
	 
	 @Test
	 void removeHeadTest() { // Testing removeHead functionality, assuring all possible cases can be removed...
		 b1 = new GenericLinkedList<>(1);
		 assertEquals(1,b1.removeHead());
		 b1.addLast(2);
		 b1.addLast(3);
		 assertEquals(2,b1.removeHead());
		 assertEquals(3,b1.removeHead());
	 }
	 
	 @Test
	 void removeTail() { // Testing removeTail functionality....
		 b1 = new GenericLinkedList<>(1);
		 assertEquals(1,b1.removeTail());
		 b1.addLast(2);
		 b1.addLast(3);
		 assertEquals(3,b1.removeTail());
		 assertEquals(2,b1.removeTail());
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
 }