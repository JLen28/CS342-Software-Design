import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;



public class ThreadedClient extends Thread{

	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	BaccaratInfo bI;
	String IP;
	int portNum;
	
	private Consumer<Serializable> callback;
	
	ThreadedClient(){
		
		
	}
	
	ThreadedClient(Consumer<Serializable> call,String ip, int pN, double b, String bC){
	
		callback = call;
		this.IP = ip;
		this.portNum = pN;
		bI = new BaccaratInfo();
		bI.setBet(b);
		bI.setBetChoice(bC);
		bI.setWins(0);
	}
	
	public void clear() {
		bI.clear();
	}
	
	public void run() {
		
		try {
		socketClient= new Socket(IP,portNum);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {
			System.out.println(e);
			callback.accept("SOMETHING WENT WRONG");
		}
		
		
		
			 
			try {
				out.writeObject(bI);
				String result = in.readObject().toString();
				callback.accept(result);
				BaccaratInfo bI2 = new BaccaratInfo((BaccaratInfo)in.readObject());
				if(BaccaratGameLogic.handTotal(bI2.getPHand()) == 8) { // check if player won naturally
					callback.accept("Player Won by Natural Win!");
					if(bI.getWins() > bI2.getWins()) { // did the client win their bet?
						callback.accept("Sorry! you've lost your bet of: " + bI.getBet());
						return;
					}
					else { // client didnt win the bet
						callback.accept("Congrats! you've won: " + bI2.getWins() + " in addition to your current bet!");
						return;
					}
				}
				else if(BaccaratGameLogic.handTotal(bI2.getBHand()) == 8) { // check if banker won naturally
					callback.accept("Banker Won by Natural Win!");
					if(bI.getWins() > bI2.getWins()) { // did the client win their bet?
						callback.accept("Sorry! you've lost your bet of: " + bI.getBet());
						return;
					}
					else { // client didnt win their bet
						callback.accept("Congrats! you've won: " + bI2.getWins() + " in addition to your current bet!");
						return;
					}
				}
				if(bI2.getPHand().size() == 3 ) { // player got an extra card, notify the client
					callback.accept("Players hand was: " + bI2.getPHand().get(0).getCardValue() + " of " + bI2.getPHand().get(0).getCardSuite() + " and " + bI2.getPHand().get(1).getCardValue() + " of " + bI2.getPHand().get(0).getCardSuite() + " and and extra card: " + bI2.getPHand().get(2).getCardValue() + " of " + bI2.getPHand().get(0).getCardSuite());
					callback.accept("Bankers hand was: " + bI2.getBHand().get(0).getCardValue() + " of " + bI2.getBHand().get(0).getCardSuite() + " and " + bI2.getBHand().get(1).getCardValue() + " of " + bI2.getBHand().get(0).getCardSuite());
					callback.accept(bI2.getWhoWOn() + " Wins!");
				}
				else if(bI2.getBHand().size() == 3) { // banker got an extra card, notify the client
					callback.accept("Bankers hand was: " + bI2.getBHand().get(0).getCardValue() + " of " + bI2.getBHand().get(0).getCardSuite() + " and " + bI2.getBHand().get(1).getCardValue() + " of " + bI2.getBHand().get(0).getCardSuite() + " and and extra card: " + bI2.getBHand().get(2).getCardValue() + " of " + bI2.getBHand().get(0).getCardSuite());
					callback.accept("Players hand was: " + bI2.getPHand().get(0).getCardValue() + " of " + bI2.getPHand().get(0).getCardSuite() + " and " + bI2.getPHand().get(1).getCardValue() + " of " + bI2.getPHand().get(0).getCardSuite());
					callback.accept(bI2.getWhoWOn() + " Wins!");
				}
				else if(bI2.getPHand().size() == 3 && bI2.getBHand().size() == 3) { // both got an extra card, notify the client
					callback.accept("Players hand was: " + bI2.getPHand().get(0).getCardValue() + " of " + bI2.getPHand().get(0).getCardSuite() + " and " + bI2.getPHand().get(1).getCardValue() + " of " + bI2.getPHand().get(0).getCardSuite() + " and and extra card: " + bI2.getPHand().get(2).getCardValue() + " of " + bI2.getPHand().get(0).getCardSuite());
					callback.accept("Bankers hand was: " + bI2.getBHand().get(0).getCardValue() + " of " + bI2.getBHand().get(0).getCardSuite() + " and " + bI2.getBHand().get(1).getCardValue() + " of " + bI2.getBHand().get(0).getCardSuite() + " and and extra card: " + bI2.getBHand().get(2).getCardValue() + " of " + bI2.getBHand().get(0).getCardSuite());
					callback.accept(bI2.getWhoWOn() + " Wins!");
				}
				else { // no extra card for either or one extra card for one and not the other
					callback.accept("Players hand was: " + bI2.getPHand().get(0).getCardValue() + " of " + bI2.getPHand().get(0).getCardSuite() + " and " + bI2.getPHand().get(1).getCardValue() + " of " + bI2.getPHand().get(0).getCardSuite());
					callback.accept("Bankers hand was: " + bI2.getBHand().get(0).getCardValue() + " of " + bI2.getBHand().get(0).getCardSuite() + " and " + bI2.getBHand().get(1).getCardValue() + " of " + bI2.getBHand().get(0).getCardSuite());
					callback.accept(bI2.getWhoWOn() + " Wins!");
				}
				if(bI.getWins() > bI2.getWins()) { // if the old winnings are greater than previous winnings, we have to have lost
					callback.accept("Sorry! you've lost your bet of: " + bI.getBet());
					return;
				}
				else { // otherwise we had to have won!
					callback.accept("Congrats! you've won: " + bI2.getWins() + " in addition to your current bet!");
					return;
				}
			   
			}
			catch(Exception e) {
				System.out.println(e);
				callback.accept("SOMETHING WENT WRONG");
			}
	
    }
	

}
