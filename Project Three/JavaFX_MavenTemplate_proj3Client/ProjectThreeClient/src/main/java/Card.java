import java.io.Serializable;

// Joseph Lenaghan | CS342 | Project Three Server Program | UIN:676805596
// Card class for Baccarat
// has two parameters to hold the suite of the card as well as its value
// 




public class Card implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String suite; // suite of the card, 4 possible suites
	int value; // value of the card, possible values are 0 thru 9, 10, jack, queen, and king are zeros
	
	Card() { // default constructor
		this.suite = "";
		this.value = 0;
	}
	Card(Card crd) { // copy constructor
		this.suite = crd.suite;
		this.value = crd.value;
	}
	Card(String theSuite, int theValue) { // argument constructor
		this.suite = theSuite;
		this.value = theValue;
	}
	
	String getCardSuite(){ // return the suite of the card
		return this.suite;
	}
	
	int getCardValue() { // return the value of the card
		return this.value;
	}
	
}