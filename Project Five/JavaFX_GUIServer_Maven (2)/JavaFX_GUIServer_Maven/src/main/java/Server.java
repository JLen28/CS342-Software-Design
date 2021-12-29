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
	Joe Lenaghan | UIN : 676805596 | Project 5
	Server Code hasnt changed much from base code,
	additions include a condition to check for a private message tag
	that is formatted like " ::(client #):: ", I couldnt find another
	method that made sense to me and I found this idea to be the easiest
	implementation wise.
 */

public class Server {
	

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	
	
	Server(Consumer<Serializable> call){
	
		callback = call;
		server = new TheServer();
		server.start();
	}
	
	
	public class TheServer extends Thread{

		public void run() {
			synchronized (this) { // synchronizing the server code
				
			try(ServerSocket mysocket = new ServerSocket(5555);){
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
			
			public void updateClient(String message, int client) {
				ClientThread t = clients.get(client);
				try {
					t.out.writeObject(message);
				}
				catch(Exception e) {}
			}
			
			public void run(){
				synchronized(this) {
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				
				updateClients("new client on server: client #"+count);
					
				 while(true) {
					    try {
					    	String data = in.readObject().toString();
					    	if(data.contains(" ::") && data.contains(":: ")) // check for the private message tag
					        for(int i = 0; i <= clients.size(); i++) { // private message tag is there, figure out who its for
					        	if(data.contains(" ::" + Integer.toString(i) + ":: ")) {
					        		String nuString = data.replace(" ::" + Integer.toString(i) + ":: ", ""); // need to remove the Pvtmssg tag before we send the message
					        		callback.accept("client " + count + " sent privately: " + nuString + " to " + " client " + i);
					        		updateClient("client " + count + " sent you a private message: " + nuString,i); // update the intended client with the private message
					        		break; // we're good to break out of the loop
					        	}
					        }
					    	else { // if we got here, we've got a message to be sent to all clients
					    	callback.accept("client: " + count + " sent: " + data);
					    	updateClients("client #"+count+" said: "+ data);
					    	}
					    	}
					    catch(Exception e) {
					    	System.out.println(e);
					    	callback.accept("Something went wrong with the socket from client: " + count + "....closing down!");
					    	updateClients("Client #"+count+" has left the server!");
					    	clients.remove(this);
					    	break;
					    }
					}
				}
				}//end of run
				
			
			
		}//end of client thread
}


	
	

	
