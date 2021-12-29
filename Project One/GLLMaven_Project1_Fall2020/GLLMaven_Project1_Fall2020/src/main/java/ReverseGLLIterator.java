import java.util.Iterator;





public class ReverseGLLIterator <T> implements Iterator<T> {
	
		GenericLinkedList<T>.Node<T> iter = null;// declaring a Generic Linked List Node to serve as our iterator...
		public ReverseGLLIterator(GenericLinkedList<T> head) {
			iter = head.getHead(); // setting iter to the head of the list...
			while(iter.next != null) { // traversing to the end of the list so we can walk through it backwards...
				iter = iter.next; 
			}
			
		}

		public boolean hasNext() { // inherited function, differs from GLLIterator as it checks iter's previous instead of next...
			if(iter.prev == null) {
				return false;
			}
			else {
				return true;
			}
		} // end of hasNext definition...

		public T next() { // inherited function, differs from GLLIterator as it move backwards to the previous instead of forwards to the next...
			if(iter.prev == null) {
				return iter.data;
			}
			iter = iter.prev;
			return iter.data; // return the data of the iter's new location...
		}
		
	}