import java.util.ArrayList;

public class BaccaratGameLogic {
	
	
	
	public static int handTotal(ArrayList<Card> hand) {
		int val1 = hand.get(0).getCardValue();
		int val2 = hand.get(1).getCardValue();
		int total = val1 + val2;
		if(total >= 10) { // total is greater than ten, min possible is 10 and max possible is 18, both can be reduced to single
			return total - 10; // digit by subtracting a base of ten
		}
		else {
			return total; // the total is less than 10 and no computation is necessary, return the total...
		}
	} // end of handTotal
    public static boolean evaluatePlayerDraw(ArrayList<Card> hand) { //decides if the player should recieve a third card
    	if(BaccaratGameLogic.handTotal(hand) <= 5) {
    		return true;
    	}
    	return false;
    }// end of eval player draw
    
    public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
    	if(BaccaratGameLogic.handTotal(hand) <= 3) {
    		return true;
    	}
    	else if(BaccaratGameLogic.handTotal(hand) == 4 &&  ((playerCard.getCardValue() >= 2 && playerCard.getCardValue() <= 7) || playerCard.getCardValue() == -1)) {
    		return true;
    	}
    	else if(BaccaratGameLogic.handTotal(hand) == 5 && ((playerCard.getCardValue() >= 4 && playerCard.getCardValue() <= 7) || playerCard.getCardValue() == -1)) {
    		return true;
    	}
    	else if(BaccaratGameLogic.handTotal(hand) == 6 && (playerCard.getCardValue() >= 6 && playerCard.getCardValue() <= 7)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    } // end of eval banker draw
    
    public static String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
    	int hand1Total = BaccaratGameLogic.handTotal(hand1);
    	int hand2Total = BaccaratGameLogic.handTotal(hand2);
    	
       	if((hand1Total == 8 && hand2Total == 8) || (hand1Total == 9 && hand2Total == 9)) {
    		return "Draw";
    	}
    	else if(hand1Total == 8 || hand1Total == 9) {
    		return "Player";
    	}
    	else if(hand2Total == 8 || hand2Total ==9) {
    		return "Banker";
    	}
    	else if(hand1Total > hand2Total) {
    		return "Player";
    		
    	}
    	else if( hand1Total == hand2Total) {
    		return "Draw";
    	}
    	else {
    		return "Banker";
    	}
    }
}