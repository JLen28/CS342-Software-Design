import java.util.ArrayList;
import java.util.Random;
import java.io.*;

//Joseph Lenaghan | UIN: 676805596 | UIC CS342 Fall' 2020 | this class is is designed with the intention of handling
// all of the logic to be performed throughout the player's interaction with the game. Storage was done utilizing an integer
// ArrayList as all the values we will be dealing with are numbers. It handles the drawing as well as the matching process
// and will serve as the skeleton for the GUI to be laid over.



public class KenoLogic {
	private ArrayList<Integer> betCard; // betCard will hold the inputs from the user
	private int draws; // need to keep track of the number of draws so we do not draw more than the user requested...
	private double currentCash; // need to keep track of the users winnings throughout the game...
	private int numRounds; // keeping track of number of rounds the user wants to play...
	private ArrayList<Integer> matchList;
	private ArrayList<Integer> drawList;
	 
	KenoLogic() { // default constructor
		draws = 0;
		currentCash = 0;
		numRounds = 0;
		betCard = new ArrayList<Integer>();
		matchList = new ArrayList<Integer>();
		drawList = 	new ArrayList<Integer>();
	} // end of default constructor...
	
	KenoLogic(ArrayList<Integer> bC,ArrayList<Integer> mL,ArrayList<Integer> dL,int d,double c,int n){ // constructor with args...
		betCard = new ArrayList<Integer>();
		betCard = bC;
		draws = d;
		numRounds = n;
		currentCash = c;
		matchList = mL;
		drawList = dL;
	}// end of argument constructor...
	
	KenoLogic(KenoLogic k){ // copy constructor
		betCard = new ArrayList<Integer>();
		matchList = new ArrayList<Integer>();
		drawList = new ArrayList<Integer>();
		betCard = k.betCard;
		matchList = k.matchList;
		drawList = k.drawList;
		currentCash = k.currentCash;
		draws = k.draws;
		numRounds = k.numRounds;
	}// end of copy constuctor...
	
	public void updateBetCard (ArrayList<Integer> bC){ // in the event we need to update the event card..
		this.betCard = bC;
	}// end of updateBetCard...
	
	public void updateDraws (int d) {
		if(d < 0) {
			this.draws = 0;
		}
		else {
		this.draws = d;
		}
	}
	
	public void updateCash (double c) {
		if(c < 0 ) {
			this.currentCash = 0;
		}
		else {
		this.currentCash = c;
		}
	}
	
	public void updateNumRounds (int n) {
		if(n < 0) {
			this.numRounds = 0;
		}
		else {
		this.numRounds = n;
		}
	}
	
	public ArrayList<Integer> getBetCard() {
		return this.betCard;
	}
	
	public void clearBetCard() {
		betCard.clear();
	}
	public void addToBetCard(int i) {
		betCard.add(i);
	}
	

	public void doQuickPick (int d) { // function randomly picks numbers 1 through 80 if user decides to..
		Random rand = new Random();
		betCard.clear();
		for(int i = 0; i < d; i++) { // don't want to exceed the desired number of draws...
			int randint = rand.nextInt(80); // number is chosen 1...80
			if(this.betCard.contains(randint)){--i;}// dont want any duplicates, if we do, lap over to the next iteration...
			else { this.betCard.add(randint);}// its not a duplicate so add it to the betCard..
		}
		
	}// end of doQuickPick...
	
	public void doDraws (ArrayList<Integer> bC , int d, double c) { // function that handles drawing and majority of game function
		Random rand = new Random();
		int matches = 0;
		if(d == 1) { // one spot game... 
			for(int i = 0; i < 20; i++) {
				int randint = rand.nextInt(80);
				if(this.drawList.contains(randint)) {i--;} // no duplicates, rewind the loop and try again
				else{this.drawList.add(randint);} // not a duplicate, add to the drawlist
				if(this.betCard.contains(randint)) {
					this.matchList.add(randint);
					matches++; // we got a positive match
					c += 2;
					this.currentCash = c;
				}

			}
			
		}// end of 1 spot game branch
		else if(d == 4) { // four spot game...
			for(int i = 0; i < 20; i++) {
				int randint = rand.nextInt(80);
				if(this.drawList.contains(randint)) {i--;}
				else{this.drawList.add(randint);}
				if(this.betCard.contains(randint)) {
					this.matchList.add(randint);
					matches++;
				}
			}
			if (matches == 2) { // card had 2 matches
				c += 1;
				this.currentCash = c;
				
			}
			else if(matches == 3) {// card had 3 matches
				c += 5;
				this.currentCash = c;
				
			}
			else if(matches == 4) {// card had 4 matches
				c += 75;
				this.currentCash = c;
				
			}
			
			
		}// end of 4 spot game branch
		else if(d == 8) { // eight spot game...
			for(int i = 0; i < 20; i++) {
				int randint = rand.nextInt(80);
				if(this.drawList.contains(randint)) {i--;}
				else{this.drawList.add(randint);}
				if(this.betCard.contains(randint)) {
					this.matchList.add(randint);
					matches++;
				}
			}
			if (matches == 4) { // card had 4 matches
				c += 2;
				this.currentCash = c;
				
			}
			else if(matches == 5) { // card had 5 matches
				c += 12;
				this.currentCash = c;
				
			}
			else if(matches == 6) { // card had 6 matches
				c += 50;
				this.currentCash = c;
				
			}
			else if(matches == 7) { // card had 7 matches
				c += 750;
				this.currentCash = c;
				
			}
			else if(matches == 8) { // card had 8 matches
				c += 10000;
				this.currentCash = c;
				
			}
			
			
		}//end of 8 spot game branch
		else if(d == 10) { // ten spot game...
			for(int i = 0; i < 20; i++) {
				int randint = rand.nextInt(80);
				if(this.drawList.contains(randint)) {i--;}
				else{this.drawList.add(randint);}
				if(this.betCard.contains(randint)) {
					this.matchList.add(randint);
					matches++;
				}
			}
			if (matches == 0) { // card had no matches
				c += 5;
				this.currentCash = c;
				
			}
			else if(matches == 5) { // card had 5 matches
				c += 2;
				this.currentCash = c;
				
			}
			else if(matches == 6) { // card had 6 matches
				c += 15;
				this.currentCash = c;
				
			}
			else if(matches == 7) { // card had 7 matches
				c += 40;
				this.currentCash = c;
				
			}
			else if(matches == 8) { // card had 8 matches
				c += 450;
				this.currentCash = c;
				
			}
			else if(matches == 9) { // card had 9 matches
				c += 4250;
				this.currentCash = c;
				
			}
			else if(matches == 10) { // card had 10 matches
				c += 100000;
				this.currentCash = c;
				
			}

			// end of 10 spot game branch
		}

	}// end of dodraws
	
	public double getCash() { // return the current cash stack
		return this.currentCash;
	}// end of getCash
	
	public int getDraws() {
		return this.draws;
	}
	
	public int getRounds() {
		return this.numRounds;
	}
	
	public int getBetCardSize() {
		return this.betCard.size();
	}
	
	public ArrayList<Integer> getmatchList() {
		return this.matchList;
	}
	
	public ArrayList<Integer> getDrawList() {
		return this.drawList;
	}
	public void clear() {
		this.betCard.clear();
		this.matchList.clear();
		this.drawList.clear();
		this.currentCash = 0;
		this.draws = 0;
		this.numRounds = 0;
	}
	
	public String printMatchList() {
		String result = "";        // print the current matchList, used mostly for debugging purposes...
		for(int e:matchList) {
			result = result  + " " + Integer.toString(e);
		}
		 return result;
		}

	
	public String printDrawList() {
		String result = "";        // print the current drawList, used mostly for debugging purposes...
		for(int e:drawList) {
			result = result  + " " + Integer.toString(e);
		}
		 return result;
		}

	
	public String printBetCard() {
		String result = "";        // print the current betCard, used mostly for debugging purposes...
		for(int e:betCard) {
			result = result  + " " + Integer.toString(e);
		}
		 return result;
		}// end of printBetCard
	
	public String printRounds() {
		return Integer.toString(numRounds);
	}
	
	public String printDraws() {
		return Integer.toString(draws);
	}
	
	public String printCash() {
		return Double.toString(currentCash);
	}
	
	
}