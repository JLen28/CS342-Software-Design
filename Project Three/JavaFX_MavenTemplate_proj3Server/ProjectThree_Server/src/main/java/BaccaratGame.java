import java.io.*;
import java.util.ArrayList;

public class BaccaratGame {
	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	double currentBet;
	double totalWinnings;
	
	BaccaratGame() { // default constructor...
		this.playerHand = new ArrayList<Card>();
		this.bankerHand = new ArrayList<Card>();
		this.theDealer = new BaccaratDealer();
		this.currentBet = 0;
		this.totalWinnings = 0;
		
	}
	
	BaccaratGame(ArrayList<Card> pH,ArrayList<Card> bH, BaccaratDealer bD, double cB, double tW) { // argument constructor...
		this.playerHand = new ArrayList<Card>(pH);
		this.bankerHand = new ArrayList<Card>(bH);
		this.theDealer = new BaccaratDealer(bD);
		this.currentBet = cB;
		this.totalWinnings = tW;
	}
	
	BaccaratGame(BaccaratGame bG) { // copy construcotor...
		this.playerHand = bG.playerHand;
		this.bankerHand = bG.bankerHand;
		this.theDealer = bG.theDealer;
		this.currentBet = bG.currentBet;
		this.totalWinnings = bG.totalWinnings;
	}
	
	
	// note to self: DONT FORGET TO TEST THIS FUNCTION LATER!!! whoWon has been tested but this HAS NOT! //
	public double evaluateWinnings() { // evaluate the winnings of the round
		String result = BaccaratGameLogic.whoWon(playerHand, bankerHand);
		if(result.equals("Player")) { // player won, so 1:1 payout...
			return this.currentBet = this.currentBet * 2;
		}
		else if(result.equals("Banker")) { // banker won, so 1:1 payout with .05 casino tax imposed...
			double wins = this.currentBet * 2;
			wins = wins * .05;
			return this.currentBet = this.currentBet + wins;
		}
		else { // tie, so 8:1 payout...
			return this.currentBet = this.currentBet * 8;
		}
	} // end of evaluate winnnings
	
	
	void clear() {
		this.playerHand = new ArrayList<Card>();
		this.bankerHand = new ArrayList<Card>();
		this.theDealer = new BaccaratDealer();
		this.currentBet = 0.0;
		this.totalWinnings = 0.0;
	}
	//Getters and Setters found below//
	
	ArrayList<Card> getPHand() { // get player hand
		return this.playerHand;
	}
	
	ArrayList<Card> getBHand() { // get banker hand
		return this.bankerHand;
	}
	
	void setPHand(ArrayList<Card> pH) { // set player hand
		this.playerHand = pH;
	}
	
	void setBHand(ArrayList<Card> bH) { /// set banker hand
		this.bankerHand = bH;
	}
	
	BaccaratDealer getDealer() { // get Dealer
		return this.theDealer;
	}
	
	void setDealer(BaccaratDealer bD) { // set the Dealer
		this.theDealer = bD;
	}
	
	double getCurBet() { // get the current bet
		return this.currentBet;
	}
	
	double getWinnings() { // get the total winnings
		return this.totalWinnings;
	}

	void setCurBet(double cB) { // set the current bet
		this.currentBet = cB;
	}
	
	void setWinnings(double w) { // set the winnings
		this.totalWinnings = w;
	}
}