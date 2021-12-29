import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;
/*
 * Clicker: A: I really get it    B: No idea what you are talking about
 * C: kind of following
 */

public class ThreadedServer{

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	int portNum;
		
	
	ThreadedServer(Consumer<Serializable> call,int pN){
	
		callback = call;
		server = new TheServer();
		server.start();
		portNum = pN;
	}
	
	public class TheServer extends Thread{
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(portNum);){
		    System.out.println("Server is waiting for a client!");
		  
			
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					callback.accept("Server socket did not launch");
				}
			}//end of while
		}
	

		class ClientThread extends Thread{
			
		
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			
			ClientThread(Socket s, int count){
				this.connection = s;
				this.count = count;	
			}
			
			public void updateClients(String message) {
				for(int i = 0; i < clients.size(); i++) {
					ClientThread t = clients.get(i);
					try {
					 t.out.writeObject(message);
					}
					catch(Exception e) {}
				}
			}
			
			public void run(){
					
				try {
					out = new ObjectOutputStream(connection.getOutputStream());
					in = new ObjectInputStream(connection.getInputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				
				updateClients("new client on server: client #"+count);
					
					    try {
					    	BaccaratInfo bI = new BaccaratInfo();
					    	bI = (BaccaratInfo)in.readObject();
					    	callback.accept("Client #" + count +": bet the amount: " + bI.bet);
					    	callback.accept("Client #" + count +": selected to bet on: " + bI.betChoice);
					    	BaccaratGame bG = new BaccaratGame();
					    	bG.theDealer.generateDeck(); // generate a deck
					    	bG.theDealer.shuffleDeck(); // shuffle the deck (creates a new deck and shuffles it for added randomization)
					    	bG.setCurBet(bI.getBet());  // update the bet
					    	bG.setWinnings(bI.getWins()); // update winnings
					    	bG.setPHand(bG.theDealer.dealHand()); // deal a hand to the player
					    	bG.setBHand(bG.theDealer.dealHand()); // deal a had to the banker
					    	if(BaccaratGameLogic.evaluatePlayerDraw(bG.getPHand()) == true) { // does the player get another card?
					    		  Card nucrd = new Card(bG.theDealer.drawOne()); // yes, so draw one
					    		  bG.playerHand.add(nucrd); // giving the player the new card
					    		  if(BaccaratGameLogic.evaluateBankerDraw(bG.getBHand(), nucrd) == true) { // okay now does the banker get a card?
					    			  bG.bankerHand.add(bG.theDealer.drawOne()); // yes, so draw another card and give it to the banker,
					    			 String result = BaccaratGameLogic.whoWon(bG.getPHand(), bG.getBHand()); // evaluate who won based on card total
					    			 bI.setWhoWon(result);
					    			 if(result.equals(bI.getBetChoice())) {  // did the client bet on the winning side
					    				 callback.accept("Player Total: " + BaccaratGameLogic.handTotal(bG.playerHand) + "  " + "Banker Total: " + BaccaratGameLogic.handTotal(bG.bankerHand));
					    				 callback.accept(BaccaratGameLogic.whoWon(bG.getPHand(), bG.getBHand()) + " wins!");
					    				 callback.accept("Client #" + count + ": bet " + bI.getBetChoice() + "! " + "winner!");
					    				 bI.setWins(bG.evaluateWinnings()); // yes the client bet correctly so payout winnings
					    				 bI.setPHand(bG.playerHand);// passing player hand to client
					    				 bI.setBHand(bG.bankerHand);// passing banker hand to client
					    				 callback.accept("Client #" + count + ": Won " + bI.winnings);
					    				 out.writeObject(bI); // send BaccaratInfo to the client
					    				 bI.clear(); // clear for a new game
					    				 bG.clear();
					    				 return;

					    			 }
					    			 else { // client did not bet on the winning side, so need to evaluate a loss
					    				 double loss = bI.getWins() - bI.getBet(); // calculating the lost bet from current winnings
					    				 callback.accept("Player Total: " + BaccaratGameLogic.handTotal(bG.playerHand) + "  " + "Banker Total: " + BaccaratGameLogic.handTotal(bG.bankerHand));
					    				 callback.accept(BaccaratGameLogic.whoWon(bG.getPHand(), bG.getBHand()) + " wins!");
					    				 callback.accept("Client #" + count +": bet " + bI.getBetChoice() + "! " + "Client lost!");
					    				 bI.setWins(loss); // update winnings to reflect the lost bet...
					    				 bI.setWhoWon(BaccaratGameLogic.whoWon(bG.getPHand(), bG.getBHand()));
					    				 bI.setPHand(bG.playerHand);// passing player hand to client
					    				 bI.setBHand(bG.bankerHand);// passing banker hand to client
					    				 callback.accept("Client #" + count + ": Lost " + bI.winnings);
					    				 out.writeObject(bI); // send BaccaratInfo to the client
					    				 bI.clear(); // clear for a new game
					    				 bG.clear();
					    				 return;
					    			 }
					    		  }
					    	}
					    	// if we got here, that means no extra card draws are necessary, still need to check if client won or lost
					    		  String result = BaccaratGameLogic.whoWon(bG.getPHand(), bG.getBHand());
					    		  bI.setWhoWon(result);
					    	if(result.equals(bI.getBetChoice())) {
					    			callback.accept("Player Total: " + BaccaratGameLogic.handTotal(bG.playerHand) + "  " + "Banker Total: " + BaccaratGameLogic.handTotal(bG.bankerHand));
					    			callback.accept(BaccaratGameLogic.whoWon(bG.getPHand(), bG.getBHand()) + " wins!");
					    			callback.accept("Client #" + count + ": bet " + bI.getBetChoice() + "! " + "winner!");
					    			bI.setWins(bG.evaluateWinnings());
					    			bI.setWhoWon(BaccaratGameLogic.whoWon(bG.getPHand(), bG.getBHand()));
					    			bI.setPHand(bG.playerHand);// passing player hand to client
					    			bI.setBHand(bG.bankerHand);// passing banker hand to client
					    			callback.accept("Client #" + count + ": Won " + bI.winnings);
					    			out.writeObject(bI); // send BaccaratInfo to the client
					    			bI.clear(); // clear for new game
					    			bG.clear();
					    			return;
					    	 }
					    	 else {
					    			double loss = bI.getWins() - bI.getBet();
					    			callback.accept("Player Total: " + BaccaratGameLogic.handTotal(bG.playerHand) + "  " + "Banker Total: " + BaccaratGameLogic.handTotal(bG.bankerHand));
					    			callback.accept(BaccaratGameLogic.whoWon(bG.getPHand(), bG.getBHand()) + " wins!");
					    			callback.accept("Client #" + count +": bet " + bI.getBetChoice() + "! " + "Client lost!");
					    		    bI.setWins(loss);
					    		    bI.setWhoWon(BaccaratGameLogic.whoWon(bG.getPHand(), bG.getBHand()));
					    		    bI.setPHand(bG.playerHand);// passing player hand to client
					    	     	bI.setBHand(bG.bankerHand);// passing banker hand to client
					    			callback.accept("Client #" + count + ": Lost " + bI.winnings);
					    			out.writeObject(bI); // send BaccaratInfo back to the client
					    			bI.clear(); // clear for new game
					    			bG.clear(); 
					    			return;
					    	 } 
					    	}
					    catch(Exception e) {
					    	System.out.println(e);
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					    	updateClients("Client #"+count+" has left the server!");
					    	clients.remove(this);
					    }
				}//end of run
			
			
		}//end of client thread
}


