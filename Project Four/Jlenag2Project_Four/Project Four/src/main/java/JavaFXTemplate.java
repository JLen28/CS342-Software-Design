import java.util.HashMap;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JavaFXTemplate extends Application {
	HashMap<String,Scene> SceneMap;
	Button b1;
	Button b2;
	Button b3;
	Button b4;
	Button b5;
	Button b6;
	Button b7;
	Button b8;
	Button b9;
	Button b10;
	Button b11;
	Button b12;
	Button b13;
	Button b14;
	Button b15;
	Button b16;
	Button b17;
	Button b18;
	Text t1;
	Text t2;
	Text t3;
	Text t4;
	Text t5;
	int xdifficulty = 0;
	int odifficulty = 0;
	int gameAmnt = 0;
	int i = 0;
	HLThreading nuGame;
	ListView<String> visualBoard;
	Timeline boardDelay;
	PauseTransition nuScene = new PauseTransition(Duration.seconds(5));
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Tic-Tac-Toe");
		SceneMap = new HashMap<>();
		SceneMap.put("select",buildSelectScreen(primaryStage));
		SceneMap.put("root",buildStartScreen(primaryStage));
		SceneMap.put("game", buildGameScreen(primaryStage));
		
		
		
				
		
		primaryStage.setScene(SceneMap.get("root"));
		primaryStage.show();
	}
	
	public Scene buildStartScreen(Stage S) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: cadetblue;");
		nuScene.setOnFinished(e->S.setScene(SceneMap.get("select")));
		pane.setPadding(new Insets(85));
		t1 = new Text("Tic-Tac-Toe");
		t2 = new Text("Loading...");
		t1.setStyle("-fx-font-size: 45;");
		t2.setStyle("-fx-font-size: 25;");
		VBox myB = new VBox(35,t1,t2);
		myB.setAlignment(Pos.CENTER);
		pane.setCenter(myB);
		nuScene.play();
		return new Scene(pane,700,700);
	}
	
	public Scene buildSelectScreen(Stage S) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: cadetblue;");
		pane.setPadding(new Insets(85));
		b18 = new Button("Confirm your settings");
		nuScene.setOnFinished(e->S.setScene(SceneMap.get("game")));
		b18.setOnAction(e->{
			b18.setDisable(true);
			b17.setDisable(true);
			
			changeScene("game",S);
			});
		b18.setDisable(true);
		t3 = new Text("Select player X difficulty");
		t3.setStyle("-fx-font-size: 30");
		t4 = new Text("Select player O difficulty");
		t4.setStyle("-fx-font-size: 30");
		t5 = new Text("how many games would you like to play?");
		t5.setStyle("-fx-font-size: 30");
		// player difficulty buttons
		b1 = new Button("Novice");
		b1.setOnAction(e->{
			b1.setDisable(true);
			b3.setDisable(true);
			b5.setDisable(true);
			xdifficulty = 1;
		});
		b2 = new Button("Novice");
		b2.setOnAction(e->{
			b2.setDisable(true);
			b4.setDisable(true);
			b6.setDisable(true);
			odifficulty = 1;
		});
		b3 = new Button("Advanced");
		b1.setOnAction(e->{
			b1.setDisable(true);
			b3.setDisable(true);
			b5.setDisable(true);
			xdifficulty = 2;
		});
		b4 = new Button("Advanced");
		b4.setOnAction(e->{
			b2.setDisable(true);
			b4.setDisable(true);
			b6.setDisable(true);
			odifficulty = 2;
		});
		b5 = new Button("Expert");
		b5.setOnAction(e->{
			b1.setDisable(true);
			b3.setDisable(true);
			b5.setDisable(true);
			xdifficulty = 3;
		});
		b6 = new Button("Expert");
		b6.setOnAction(e->{
			b2.setDisable(true);
			b4.setDisable(true);
			b6.setDisable(true);
			odifficulty = 3;
		});
		HBox myH = new HBox(25,b1,b3,b5);
		myH.setAlignment(Pos.CENTER);
		HBox myH2 = new HBox(25,b2,b4,b6);
		myH2.setAlignment(Pos.CENTER);
		VBox myB = new VBox(25,t3,myH);
		VBox myB2 = new VBox(25,t4,myH2);
		myB.setAlignment(Pos.CENTER);
		myB2.setAlignment(Pos.CENTER);
		// end of player difficulty buttons
		// game amount buttons
		b7 = new Button("1");
		b7.setOnAction(e->{
			b7.setDisable(true);
			b8.setDisable(true);
			b9.setDisable(true);
			b10.setDisable(true);
			b11.setDisable(true);
			b12.setDisable(true);
			b13.setDisable(true);
			b14.setDisable(true);
			b15.setDisable(true);
			b16.setDisable(true);
			gameAmnt = 1;
			b18.setDisable(false);
		});
		b8 = new Button("2");
		b8.setOnAction(e->{
			b7.setDisable(true);
			b8.setDisable(true);
			b9.setDisable(true);
			b10.setDisable(true);
			b11.setDisable(true);
			b12.setDisable(true);
			b13.setDisable(true);
			b14.setDisable(true);
			b15.setDisable(true);
			b16.setDisable(true);
			gameAmnt = 2;
			b18.setDisable(false);
		});
		b9 = new Button("3");
		b9.setOnAction(e->{
			b7.setDisable(true);
			b8.setDisable(true);
			b9.setDisable(true);
			b10.setDisable(true);
			b11.setDisable(true);
			b12.setDisable(true);
			b13.setDisable(true);
			b14.setDisable(true);
			b15.setDisable(true);
			b16.setDisable(true);
			gameAmnt = 3;
			b18.setDisable(false);
		});
		b10 = new Button("4");
		b10.setOnAction(e->{
			b7.setDisable(true);
			b8.setDisable(true);
			b9.setDisable(true);
			b10.setDisable(true);
			b11.setDisable(true);
			b12.setDisable(true);
			b13.setDisable(true);
			b14.setDisable(true);
			b15.setDisable(true);
			b16.setDisable(true);
			gameAmnt = 4;
			b18.setDisable(false);
		});
		b11 = new Button("5");
		b11.setOnAction(e->{
			b7.setDisable(true);
			b8.setDisable(true);
			b9.setDisable(true);
			b10.setDisable(true);
			b11.setDisable(true);
			b12.setDisable(true);
			b13.setDisable(true);
			b14.setDisable(true);
			b15.setDisable(true);
			b16.setDisable(true);
			gameAmnt = 5;
			b18.setDisable(false);
		});
		b12 = new Button("6");
		b12.setOnAction(e->{
			b7.setDisable(true);
			b8.setDisable(true);
			b9.setDisable(true);
			b10.setDisable(true);
			b11.setDisable(true);
			b12.setDisable(true);
			b13.setDisable(true);
			b14.setDisable(true);
			b15.setDisable(true);
			b16.setDisable(true);
			gameAmnt = 6;
			b18.setDisable(false);
		});
		b13 = new Button("7");
		b13.setOnAction(e->{
			b7.setDisable(true);
			b8.setDisable(true);
			b9.setDisable(true);
			b10.setDisable(true);
			b11.setDisable(true);
			b12.setDisable(true);
			b13.setDisable(true);
			b14.setDisable(true);
			b15.setDisable(true);
			b16.setDisable(true);
			gameAmnt = 7;
			b18.setDisable(false);
		});
		b14 = new Button("8");
		b14.setOnAction(e->{
			b7.setDisable(true);
			b8.setDisable(true);
			b9.setDisable(true);
			b10.setDisable(true);
			b11.setDisable(true);
			b12.setDisable(true);
			b13.setDisable(true);
			b14.setDisable(true);
			b15.setDisable(true);
			b16.setDisable(true);
			gameAmnt = 8;
			b18.setDisable(false);
		});
		b15 = new Button("9");
		b15.setOnAction(e->{
			b7.setDisable(true);
			b8.setDisable(true);
			b9.setDisable(true);
			b10.setDisable(true);
			b11.setDisable(true);
			b12.setDisable(true);
			b13.setDisable(true);
			b14.setDisable(true);
			b15.setDisable(true);
			b16.setDisable(true);
			gameAmnt = 9;
			b18.setDisable(false);
		});
		b16 = new Button("10");
		b16.setOnAction(e->{
			b7.setDisable(true);
			b8.setDisable(true);
			b9.setDisable(true);
			b10.setDisable(true);
			b11.setDisable(true);
			b12.setDisable(true);
			b13.setDisable(true);
			b14.setDisable(true);
			b15.setDisable(true);
			b16.setDisable(true);
			gameAmnt = 10;
			b18.setDisable(false);
		});
		b17 = new Button("reset");
		b17.setOnAction(e->{
			b18.setDisable(true);
			gameAmnt = 0;
			xdifficulty = 0;
			odifficulty  = 0;
			b1.setDisable(false);
			b2.setDisable(false);
			b3.setDisable(false);
			b4.setDisable(false);
			b5.setDisable(false);
			b6.setDisable(false);
			b7.setDisable(false);
			b8.setDisable(false);
			b9.setDisable(false);
			b10.setDisable(false);
			b11.setDisable(false);
			b12.setDisable(false);
			b13.setDisable(false);
			b14.setDisable(false);
			b15.setDisable(false);
			b16.setDisable(false);
		});
		HBox myH3 = new HBox(25,b7,b8,b9,b10,b11);
		myH3.setAlignment(Pos.CENTER);
		HBox myH4 = new HBox(25,b12,b13,b14,b15,b16);
		myH4.setAlignment(Pos.CENTER);
		VBox myB3 = new VBox(25,t5,myH3,myH4);
		myB3.setAlignment(Pos.CENTER);
		// end of game amount buttons
		VBox myB4 = new VBox(25,myB,myB2,myB3,b17,b18);
		myB4.setAlignment(Pos.CENTER);
		pane.setCenter(myB4);
		return new Scene(pane,700,700);
	}
	
	public Scene buildGameScreen(Stage S) {
		visualBoard = new ListView<>();
		visualBoard.setPadding(new Insets(85));
		nuGame = new HLThreading();
		EventHandler<ActionEvent> myhandler = (e ->{
			nuGame.init();
			String[] board = nuGame.getBoard();
			String line1 = board[0] + " " + board[1] + " " + board[2];
			String line2 = board[3] + " " + board[4] + " " + board[5];
			String line3 = board[6] + " " + board[7] + " " + board[8];
			visualBoard.getItems().add(line1);
			visualBoard.getItems().add(line2);
			visualBoard.getItems().add(line3);
			if(nuGame.getResult() == 0) {
				visualBoard.getItems().add("No more possible moves, Draw!");
			}
			else if(nuGame.getResult() == 1) {
				visualBoard.getItems().add("player X has won the game!");
			}
			else {
				visualBoard.getItems().add("player O has won the game!");
			}
		});
		boardDelay = new Timeline(
				new KeyFrame(Duration.seconds(3),myhandler),
				new KeyFrame(Duration.millis(2000))
				);
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: cadetblue;");
		pane.setPadding(new Insets (75));
		t4 = new Text("Game Results");
		t4.setStyle("-fx-font-size: 35;");
		Button b19 = new Button("press me to play!");
		b19.setOnAction(e->{
			b19.setDisable(true);
			boardDelay.play();
			});
		Button b20 = new Button("return to game select");
		b20.setOnAction(e->{
			gameAmnt = 0;
			xdifficulty = 0;
			odifficulty = 0;
			S.setScene(SceneMap.get("select"));
		});
		Button b21 = new Button("Exit the program");
		b21.setOnAction(e->{
			System.exit(0);
			Platform.exit();
		});
		HBox myHB = new HBox(22.5,b19,b20,b21);
		myHB.setAlignment(Pos.CENTER);
		VBox myB = new VBox(45,t4,visualBoard,myHB);
		myB.setAlignment(Pos.CENTER);
		pane.setCenter(myB);
		return new Scene(pane,700,700);

	}
	
	public void changeScene(String scn,Stage S) {
		nuScene.setOnFinished(e->{
			b18.setDisable(false);
			b1.setDisable(false);
			b2.setDisable(false);
			b3.setDisable(false);
			b4.setDisable(false);
			b5.setDisable(false);
			b6.setDisable(false);
			b7.setDisable(false);
			b8.setDisable(false);
			b9.setDisable(false);
			b10.setDisable(false);
			b11.setDisable(false);
			b12.setDisable(false);
			b13.setDisable(false);
			b14.setDisable(false);
			b15.setDisable(false);
			b16.setDisable(false);
			S.setScene(SceneMap.get("game"));
			});
		nuScene.play();
	}

}

