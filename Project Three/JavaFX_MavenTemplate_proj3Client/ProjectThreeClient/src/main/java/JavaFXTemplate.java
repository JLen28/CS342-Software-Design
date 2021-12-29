//
// Joseph Lenaghan | CS342 | Project Three client Program | UIN:676805596
import java.util.ArrayList;

import java.util.HashMap;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;

public class JavaFXTemplate extends Application {	
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Button b5;
	private Button b6;
	private Text t1;
	private Text t2;
	private Text t3;
	private Text t4;
	private Text t5;
	private TextField tf1;
	private TextField tf2;
	private TextField tf3;
	private ListView<String> listItems;
	ThreadedClient nuClient = new ThreadedClient();
	String ip = " ";
	int portNum = 0;
	double bet = 0.0;
	String betChoice = " ";
	HashMap<String,Scene> SceneMap = new HashMap<>();
	Timeline displayDelay;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("The Game of Baccarat");
		
		
		
				
		SceneMap.put("root",createTitleScreen(primaryStage)); // adding root scene to hashmap
		SceneMap.put("server", createServerScreen(primaryStage)); // adding server select screen to hashmap
		SceneMap.put("bet", createBetScreen(primaryStage)); // adding add bet screen to hashmap
		SceneMap.put("results",createResultScreen(primaryStage)); // adding the results screen to hashmap
		primaryStage.setScene(SceneMap.get("root"));
		primaryStage.show();
	}
	
	public Scene createTitleScreen(Stage S) { // screen that greets the client upon booting program up
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: seagreen");
		t1 = new Text("Welcome to Baccarat!");
		t1.setStyle("-fx-font-size: 34");
		b1 = new Button("Play the Game");
		b1.setOnAction(e->S.setScene(SceneMap.get("server")));
		b2 = new Button("Quit the Game");
		b2.setOnAction(e->{ // exit the program and cease execution
			System.exit(0);
			Platform.exit();
		});
		VBox myB = new VBox(75,t1,b1,b2);
		myB.setAlignment(Pos.CENTER);
		pane.setCenter(myB);
		return new Scene (pane,700,700); 
	} // end of createTitleScreen
	
	public Scene createServerScreen(Stage S) { // screen where the client will input IP and port number
		BorderPane pane = new BorderPane();
		b3 = new Button("Select a bid"); // button to take user to the bidding screen
		b3.setOnAction(e->S.setScene(SceneMap.get("bet")));
		pane.setStyle("-fx-background-color: seagreen");
		tf1 = new TextField();
		tf1.setPrefWidth(250);
		tf1.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER)) {
			ip =((tf1.getText().toString())); // setting the IP, user can re-enter as many times as necessary
			tf1.clear();
		}});
		tf2 = new TextField();
		tf2.setPrefWidth(250);
		tf2.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER)) {
			portNum =(Integer.valueOf(tf2.getText())); // setting the portNum, can be re-entered
			tf2.clear();
		}});
		t2 = new Text("Connect to a Server");
		t2.setStyle("-fx-font-size: 45");
		t3 = new Text("Enter IP here");
		t3.setStyle("-fx-font-size: 20");
		t4 = new Text("Enter port number here");
		t4.setStyle("-fx-font-size: 20");
		t5 = new Text("Enter your port and IP before you select a bid!");
		t5.setStyle("-fx-font-size: 30");
		VBox myB1 = new VBox(15,t3,tf1);
		VBox myB2 = new VBox(15,t4,tf2);
		HBox myHB = new HBox(35,myB1,myB2);
		VBox myB3 = new VBox(75,t2,myHB,t5,b3);
		myB1.setAlignment(Pos.CENTER);
		myB2.setAlignment(Pos.CENTER);
		myHB.setAlignment(Pos.CENTER);
		myB3.setAlignment(Pos.CENTER);
		pane.setCenter(myB3);
		return new Scene(pane,700,700);
		
	} // end of create server screen
	
	public Scene createBetScreen(Stage S) { // create the screen where the user selects their bet amount and who they're betting on
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: seagreen");
		t5 = new Text("Enter a Bet!");
		t5.setStyle("-fx-font-size: 45");
		b3 = new Button("Bid on the Player");
		b4 = new Button("Bid on the Banker");
		b5 = new Button("Bid on a Draw");
		b3.setDisable(true);
		b4.setDisable(true); // buttons start disabled, client needs to bet something
		b5.setDisable(true);
		tf3 = new TextField();
		tf3.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER)) { // bet has been entered
			bet =(Integer.valueOf(tf3.getText()));
			tf3.clear();
			b3.setDisable(false);
			b4.setDisable(false); // enable the buttons once bet has been entered
			b5.setDisable(false);
		}});
		b3.setOnAction(e->{
			b3.setDisable(true);
			b4.setDisable(true); // re-disable the buttons for replay purposes
			b5.setDisable(true);
			S.setScene(SceneMap.get("results")); // proceed to results screen, with new client thread
			if(nuClient.isAlive() != true) {
			nuClient = new ThreadedClient(data->{ // client thread initialized 
				Platform.runLater(()->{
					displayDelay = new Timeline(new KeyFrame(Duration.seconds(1.5),ev->listItems.getItems().add(data.toString())));
					displayDelay.setCycleCount(1);
					displayDelay.play();
				});
			},ip,portNum,bet,"Player");
			nuClient.start();
			};
		});
		b4.setOnAction(e->{
			b3.setDisable(true);
			b4.setDisable(true); // re-disable the buttons for replay purposes
			b5.setDisable(true);
			S.setScene(SceneMap.get("results")); // proceed to results screen, with new client thread
			if(nuClient.isAlive() != true) {
			nuClient = new ThreadedClient(data->{ // client thread initialized 
				Platform.runLater(()->{
					displayDelay = new Timeline(new KeyFrame(Duration.seconds(1.5),eve->listItems.getItems().add(data.toString())));
					displayDelay.setCycleCount(1);
					displayDelay.play();
				});
			},ip,portNum,bet,"Banker");
			nuClient.start();
			}
		});
		b5.setOnAction(e->{
			b3.setDisable(true);
			b4.setDisable(true); // re-disable the buttons for replay purposes
			b5.setDisable(true);
			S.setScene(SceneMap.get("results")); // proceed to results screen, with new client thread
			if(nuClient.isAlive() != true) {
			nuClient = new ThreadedClient(data->{ // client thread initialized 
				Platform.runLater(()->{
					displayDelay = new Timeline(new KeyFrame(Duration.seconds(1.5),even->listItems.getItems().add(data.toString())));
					displayDelay.setCycleCount(1);
					displayDelay.play();
				});
			},ip,portNum,bet,"Draw");
			nuClient.start();
			}
		});
		VBox myB = new VBox(75,t5,tf3,b3,b4,b5);
		myB.setAlignment(Pos.CENTER);
		pane.setCenter(myB);
		pane.setPadding(new Insets(85));
		return new Scene(pane,700,700);
		
	}
	
	public Scene createResultScreen(Stage S) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: seagreen");
		b2 = new Button("Play again!");
		b2.setOnAction(e->{ // Bid again and restart
			nuClient.clear();
			S.setScene(SceneMap.get("bet"));
		});
		b6 = new Button("Main Menu");
		t4 = new Text("-~Game Results!~-");
		t4.setStyle("-fx-font-size: 45");
		b6.setOnAction(e->S.setScene(SceneMap.get("root")));
		listItems = new ListView<String>();
		listItems.prefWidth(400);
		VBox myB = new VBox(75,t4,listItems,b6,b2);
		myB.setAlignment(Pos.CENTER);
		pane.setCenter(myB);
		pane.setPadding(new Insets(85));
		return new Scene(pane,700,700);
	}

}
