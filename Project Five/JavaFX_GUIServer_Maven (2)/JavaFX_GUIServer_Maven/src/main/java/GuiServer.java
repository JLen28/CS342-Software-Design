
/////// Joe Lenaghan
////// UIN: 676805596
// this code has not been altered much from the base code,
// one major addition is the large block of buttons used for
// handling private messages being sent between clients.
// currently, the private messages are limited to ten clients,
// a potential major shortcoming depending on the program's end purpose

import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GuiServer extends Application{

	
	TextField s1,s2,s3,s4, c1;
	Button serverChoice,clientChoice,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
	HashMap<String, Scene> sceneMap;
	GridPane grid;
	HBox buttonBox;
	VBox clientBox;
	Scene startScene;
	BorderPane startPane;
	Server serverConnection;
	Client clientConnection;
	int clientCnt = 0;
	
	ListView<String> listItems, listItems2, listItems3;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("The Networked Client/Server GUI Example");
		
		this.serverChoice = new Button("Server");
		this.serverChoice.setStyle("-fx-pref-width: 300px");
		this.serverChoice.setStyle("-fx-pref-height: 300px");
		
		this.serverChoice.setOnAction(e->{ primaryStage.setScene(sceneMap.get("server"));
											primaryStage.setTitle("This is the Server");
				serverConnection = new Server(data -> {
					Platform.runLater(()->{
						listItems.getItems().add(data.toString());
					});

				});
											
		});
		
		
		this.clientChoice = new Button("Client");
		this.clientChoice.setStyle("-fx-pref-width: 300px");
		this.clientChoice.setStyle("-fx-pref-height: 300px");
		
		this.clientChoice.setOnAction(e-> {primaryStage.setScene(sceneMap.get("client"));
											primaryStage.setTitle("This is a client");
											clientConnection = new Client(data->{
							Platform.runLater(()->{listItems2.getItems().add(data.toString());
							clientCnt++;
											});
							});
							
											clientConnection.start();

											
		});
		
		this.buttonBox = new HBox(400, serverChoice, clientChoice);
		startPane = new BorderPane();
		startPane.setPadding(new Insets(70));
		startPane.setCenter(buttonBox);		
		startScene = new Scene(startPane, 800,800);
		
		listItems = new ListView<String>();
		listItems2 = new ListView<String>();
		listItems3 = new ListView<String>();
		
		c1 = new TextField();
		///////////////////////////////////////////
		/////BUTTON BLOCK FOR PRIVATE MESSAGES/////
		//////////////////////////////////////////
		b1 = new Button("Send to Everyone");
		b1.setOnAction(e->{clientConnection.sendAll(c1.getText()); c1.clear();});
		b1.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		b2 = new Button("send to client 1");
		b2.setOnAction(e->{
			String tag = " ::0:: ";
			clientConnection.sendAll(c1.getText().concat(tag)); 
			c1.clear();			
		});
		b2.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		b3 = new Button("send to client 2");
		b3.setOnAction(e->{
			String tag = " ::1:: ";
			clientConnection.sendAll(c1.getText().concat(tag)); 
			c1.clear();			
		});
		b3.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		b4 = new Button("send to client 3");
		b4.setOnAction(e->{
			String tag = " ::2:: ";
			clientConnection.sendAll(c1.getText().concat(tag)); 
			c1.clear();			
		});
		b4.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		b5 = new Button("send to client 4");
		b5.setOnAction(e->{
			String tag = " ::3:: ";
			clientConnection.sendAll(c1.getText().concat(tag)); 
			c1.clear();			
		});
		b5.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		b6 = new Button("send to client 5");
		b6.setOnAction(e->{
			String tag = " ::4:: ";
			clientConnection.sendAll(c1.getText().concat(tag)); 
			c1.clear();			
		});
		b6.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		b7 = new Button("send to client 6");
		b7.setOnAction(e->{
			String tag = " ::5:: ";
			clientConnection.sendAll(c1.getText().concat(tag)); 
			c1.clear();			
		});
		b7.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		b8 = new Button("send to client 7");
		b8.setOnAction(e->{
			String tag = " ::6:: ";
			clientConnection.sendAll(c1.getText().concat(tag)); 
			c1.clear();			
		});
		b8.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		b9 = new Button("send to client 8");
		b9.setOnAction(e->{
			String tag = " ::7:: ";
			clientConnection.sendAll(c1.getText().concat(tag)); 
			c1.clear();			
		});
		b9.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		b10 = new Button("send to client 9");
		b10.setOnAction(e->{
			String tag = " ::8:: ";
			clientConnection.sendAll(c1.getText().concat(tag)); 
			c1.clear();			
		});
		b10.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		b11 = new Button("send to client 10");
		b11.setOnAction(e->{
			String tag = " ::9:: ";
			clientConnection.sendAll(c1.getText().concat(tag)); 
			c1.clear();			
		});
		b11.setMinSize(Button.USE_PREF_SIZE, Button.USE_PREF_SIZE);
		////////////////////////////////////////////////////
		///// END OF BUTTON BLOCK FOR PRIVATE MESSAGES/////
		//////////////////////////////////////////////////
		sceneMap = new HashMap<String, Scene>();
		
		sceneMap.put("server",  createServerGui());
		sceneMap.put("client",  createClientGui());
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
            	listItems3.getItems().remove("Client: " + Integer.toString(clientCnt));
                Platform.exit();
                System.exit(0);
            }
        });
		
		 
		
		primaryStage.setScene(startScene);
		primaryStage.show();
		
	}
	
	public Scene createServerGui() {
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: coral");
		
		pane.setCenter(listItems);
	
		return new Scene(pane, 700, 700);
		
		
	}
	
	public Scene createClientGui() {
		BorderPane pane = new BorderPane();
		HBox myHB = new HBox(20,b2,b3,b4,b5,b6);
		HBox myHB2 = new HBox(20,b7,b8,b9,b10,b11);
		clientBox = new VBox(10, c1,b1,myHB,myHB2,listItems2,listItems3);
		clientBox.setAlignment(Pos.CENTER);
		clientBox.setStyle("-fx-background-color: blue");
		pane.setCenter(clientBox);
		return new Scene(pane, 700, 700);
		
	}

}
