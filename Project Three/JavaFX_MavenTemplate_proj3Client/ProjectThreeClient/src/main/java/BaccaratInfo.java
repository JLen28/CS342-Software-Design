import java.io.*;
import java.util.ArrayList;


class BaccaratInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	////////////////////////////////////////////////
	
	double bet;
	double winnings;
	String betChoice;
	String whoWon;
	ArrayList<Card> pHand;
	ArrayList<Card> bHand;
	BaccaratInfo() {
		bet = 0.0;
		winnings = 0.0;
		betChoice = " ";
		whoWon = " ";
		pHand = new ArrayList<Card>();
		bHand = new ArrayList<Card>();
	}
	
	BaccaratInfo(BaccaratInfo bI) {
		this.bet = bI.bet;
		this.betChoice = bI.betChoice;
		this.winnings = bI.winnings;
		this.whoWon = bI.whoWon;
		this.pHand = bI.pHand;
		this.bHand = bI.bHand;
	}
	
	void setBetChoice (String bC) {
		this.betChoice = bC;
	}
	
	void setWhoWon(String w) {
		this.whoWon = w;
	}
	
	void setBet (double b) {
		this.bet = b;
	}
	
	void setWins(double w) {
		this.winnings = w;
	}
	
	void setPHand(ArrayList<Card> pH) {
		this.pHand = pH;
	}
	
	void setBHand(ArrayList<Card> bH) {
		this.bHand = bH;
	}
	
	String getWhoWOn() {
		return this.whoWon;
	}
	ArrayList<Card> getPHand() {
		return this.pHand;
	}
	
	ArrayList<Card> getBHand() {
		return this.bHand;
	}
	
	String getBetChoice() {
		return this.betChoice;
	}
	
	double getBet() {
		return this.bet;
	}
	
	double getWins() {
		return this.winnings;
	}
	
	void clear() {
		this.bet = 0.0;
		this.betChoice = " ";
		pHand.clear();
		bHand.clear();
	}
}