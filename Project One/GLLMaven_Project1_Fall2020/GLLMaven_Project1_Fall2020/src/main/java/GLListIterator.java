import java.util.ListIterator;




public class GLListIterator <T> implements ListIterator<T> {
		
		GenericLinkedList<T>.Node<T> iter = null; // Declaring a GenericLinkedList Node to serve as our iterator, much like traveller...
		int myIndex;// declaring an index to store passed index from constructor...
		int myLength;// declaring a length to store passed length from constructor...
		public GLListIterator(GenericLinkedList<T> head, int index, int length) { // constructor
			iter = head.getHead(); // iter is set to the head node of the list...
			myIndex = index; // storing index...
			myLength = length; // storing length...
			int i = 0; // declaring our traverser...
			for ( i = 0; i < index; i++) { // traversing through the list..
				iter = iter.next;
			}
		}// end of constructor definition...
		public boolean hasPrevious() { // inherited function, serves to check if the iterator has a previous node...
			if(iter.prev == null) {
				return false;
			}
			else {
				return true;
			}
		}// end of hasPrevious definition...

		public boolean hasNext() { // inherited function, serves to check if the iterator has a next node...
			
			if(iter.next == null) {
				return false;
			}
			else {
				return true;
			}
		}// end of hasNext definition...

		public T next() {// inherited function, moves the iterator one node forward in the LL and returns the data at that node...
			iter = iter.next;
			myIndex++;
			return iter.data;
		}// end of next definition...


		public T previous() {// inherited function, moves the iterator one node back in the LL and returns the data at that node...
			iter = iter.prev;
			myIndex--;
			return iter.data;
		}// end of previous definition...

		public int nextIndex() { // inherited function, returns the index of the next node in the LL that iterator would .next() to...
			int indexPlusOne = myIndex + 1;
			if(indexPlusOne > myLength) {// are we at the tail? if so, we should not increment the index...
				return myIndex;
			}
			return indexPlusOne;
		}// end of nextIndex definition...
		
		public int previousIndex() { // inherited function, returns the index of the previous node in the LL that iterator would .previous() to...
			int indexMinusOne = myIndex -1;
			if(indexMinusOne < 0) {// are we at the head? if so, we should not increment the index...
				return myIndex;
			}
			return indexMinusOne;
		}// end of previousIndex definition...

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(T e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void add(T e) {
			// TODO Auto-generated method stub
			
		}
		
	}
