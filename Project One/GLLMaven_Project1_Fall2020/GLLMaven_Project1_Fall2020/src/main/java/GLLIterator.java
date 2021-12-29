import java.util.Iterator;







public class GLLIterator <T> implements Iterator<T> { 

	GenericLinkedList<T>.Node<T> iter = null; // declaring a GenericLinkedList Node to serve as our iterator...
	public GLLIterator(GenericLinkedList<T> head) {// constructor...
		iter = head.getHead(); // setting iter to the head of the LL...
	}// end of constructor definition..
	
	
	public boolean hasNext() { // inherited function, checks if the iterator node has a node in front of it...
		if(iter.next == null) {
		return false;
		}
		else {
			return true;
		}
	}// end of hasNext definition...
	
	public T next() { // inherited function, moves the iterator one node forward...
		if(iter.next == null) {
			return iter.data;
		}
		iter = iter.next;
		return iter.data; // return the data of iterator's new location...
	}// end of next definition...
}
