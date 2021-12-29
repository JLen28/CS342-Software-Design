import java.util.ArrayList;
import java.util.HashMap;

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
import javafx.geometry.Pos;
import javafx.geometry.VPos;

public class JavaFXTemplate extends Application {
//||||variables used in the game||||//
	private Menu m1;
	private MenuItem mI1;
	private MenuItem mI2;
	private MenuItem mI3;
	private MenuItem mI4;
	private MenuBar mB1;
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Button b5;
	private Text t1;
	private Text t2;
	private Text t3;
	private Text t4;
	private Text t5;
	private HashMap<String,Scene> SceneMap;
	private PauseTransition scenepause = new PauseTransition(Duration.seconds(5));
	private TextField tf1;
	private TextField tf2;
	private KenoLogic k1;
	private ListView<Integer> drawDisplay;
	private ObservableList<Integer> listToDisplay;
	private Timeline displayDelay;
//|||||||||||||||||||||||||||||||||||

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	private int i = 0;
	private int counter = 0;
	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Keno");
		SceneMap = new HashMap<String,Scene>();
		k1 = new KenoLogic();

		
		//orgainizing our scenemap with all the scenes of the game
		SceneMap.put("rules",createRulesScene(primaryStage));
		SceneMap.put("root",createInitialScene(primaryStage));
		SceneMap.put("odds", createOddsScene(primaryStage));
		SceneMap.put("game", createGameScreen(primaryStage,k1));
		SceneMap.put("sel2", createGameSelectScreenTwo(primaryStage,k1));
		SceneMap.put("select1",createGameSelectScreenOne(primaryStage,k1));
		SceneMap.put("draw", createDrawScreen(primaryStage,k1));
		primaryStage.setScene(SceneMap.get("root")); // game starts here at the root node
		primaryStage.show(); // show the screen to the user
	}
	public Scene createInitialScene(Stage S) { // create the first screen the user sees...
		BorderPane pane = new BorderPane();
		mB1 = new MenuBar(); // bar to hold menu
		m1 = new Menu("Menus"); // Menus to hold the rules,Odds, and the option to exit the app items...
		mI1 = new MenuItem("Rules of Keno"); // menu item 1
		mI1.setOnAction(e->S.setScene(SceneMap.get("rules"))); // action of menu item 1
		mI2 = new MenuItem("Odds of Winning"); // menu item 2
		mI2.setOnAction(e->S.setScene(SceneMap.get("odds"))); // action of menu item 2
		mI3 = new MenuItem("Exit Keno");// menu item 3
		mI3.setOnAction(e->Platform.exit());// action of menu item 3
		m1.getItems().addAll(mI1,mI2,mI3);
		mB1.getMenus().add(m1);
		pane.setTop(mB1);
		Text t1 = new Text("Welcome to Keno!");
		t1.setStyle("-fx-font-size: 45;");
		Button b1 = new Button("Play Now!"); // Button that starts the game...
		b1.setOnAction(e->S.setScene(SceneMap.get("select1")));
		VBox myB = new VBox(75,t1,b1);
		myB.setStyle("-fx-padding: 0 0 200 0");
		myB.setAlignment(Pos.CENTER);
		pane.setCenter(myB);
		return new Scene(pane,700,700);
		
		
	}
	
	public Scene createRulesScene(Stage S) { // create the screen that displays the rules of Carolina Keno...
		BorderPane pane = new BorderPane();
		Button b1 = new Button("Back to the Main Menu"); // button to return to the main menu
		b1.setOnAction(e->S.setScene(SceneMap.get("root")));
		pane.setRight(b1);
		Text t2 = new Text( "Carolina Keno is a fast-paced lottery draw-style game that's easy to play, with a chance to win great cash prizes every 4 minutes. For each Keno drawing, 20 numbers out of 80 will be selected as winning numbers. You can decide how many of these numbers (called Spots) and exactly which numbers you will try to match.\n\n"
				+ "Rule 1: Decide how much to play per draw. Each play costs $1. Play for $2 to double your prize; play for $3 to triple your prize and so on up to $10 per play.\n\n"
				+ "Rule 2:Select how many consecutive draws to play. Pick up to 20. Drawings happen every 4 minutes.\n\n"
				+ "Rule 3: Select how many numbers to match from 1 to 10. In Keno, these are called Spots. The number of Spots you choose and the amount you play per draw will determine the amount you could win. See the prize chart to determine the amount you could win with a $1 play.\n\n"
				+ "Rule 4: Pick as many numbers as you did Spots. You can select numbers from 1 to 80 or choose Quick Pick and let the computer terminal randomly pick some or all of these numbers for you.\n\n");
		t2.setTextAlignment(TextAlignment.LEFT);
		t2.setStyle("-fx-font-size: 16;" + "-fx-padding: 0 0 0 250;");
		t2.setWrappingWidth(300);
		pane.setLeft(t2);
		return new Scene(pane,700,700);
	}
	public Scene createOddsScene(Stage S) { // create the screen tha displays the odds of Carolina Keno...
		BorderPane pane = new BorderPane();
		Button b1 = new Button("Back to the Main Menu"); // button to return to the main menu...
		b1.setOnAction(e->S.setScene(SceneMap.get("root")));
		b1.setStyle("-fx-padding:0 288 0 288");
		pane.setTop(b1);
		t2 = new Text("The Overall Odds of winning a One spot game are 1 in 4\n\n"
				+"The Overall Odds of winning a four spot game are 1 in 3.86\n\n"
				+"The Overall Odds of winning an eight spot game are 1 in 9.77\n\n"
				+"The Overall Odds of winning a ten spot game are 1 in in 9.05\n\n");
		t2.setStyle("-fx-font-size: 20;");
		VBox myB = new VBox(50,t2);
		myB.setAlignment(Pos.CENTER);
		pane.setCenter(myB);
		return new Scene(pane,700,700);
	}
	
	public Scene createGameSelectScreenOne(Stage S,KenoLogic k) { // create the first screen to process the specifications of the game...
		BorderPane pane = new BorderPane();
		Button b1 = new Button("Click me when you've finished deciding"); // button to advance to the next specifications screen
		b1.setDisable(true); // we want the button to only be available when a valid input is entered...
		b1.setOnAction(e->{
			b1.setDisable(true);
			S.setScene(SceneMap.get("sel2"));
		}); // action of b1 that advances to the next screen
		tf1 = new TextField();
		tf1.setPromptText("Please enter a choice of 1,4,8 or 10. Incorrect Values will be refused");
		t2 = new Text("How many Spots would you like to choose?\n\n");
		t2.setStyle("-fx-font-size: 35");
		//below the input of the user is handled so that only valid inputs are accepted, game will not allow
		// the user to advance until a valid input is entered...
		tf1.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER))
		    if(Integer.valueOf(tf1.getText()) == 1) { // user wants a 1 spot game
				k.updateDraws(Integer.valueOf(tf1.getText()));
				tf1.clear();
				b1.setDisable(false);
		    }
		    else if(Integer.valueOf(tf1.getText()) == 4) { // user wants a 4 spot game
				k.updateDraws(Integer.valueOf(tf1.getText()));
				tf1.clear();
				b1.setDisable(false);
		    }
		    else if(Integer.valueOf(tf1.getText()) == 8) { // user wants an 8 spot game
				k.updateDraws(Integer.valueOf(tf1.getText()));
				tf1.clear();
				b1.setDisable(false);
		    }
		    else if(Integer.valueOf(tf1.getText()) == 10) { // user wants a 10 spot game
				k.updateDraws(Integer.valueOf(tf1.getText()));
				tf1.clear();
				b1.setDisable(false);
		    }
		    else{ // user entered an invalid input, notify user in the textfield and demand valid input...
		    	tf1.clear();
		    	tf1.setText("Incorrect value entered! Please only select one,four,eight or ten spots to play.");
		    	b1.setDisable(true);
		    	}
		    }
		);
		VBox myB = new VBox(30,t2,tf1,b1);
		myB.setAlignment(Pos.CENTER);
		pane.setCenter(myB);
		return new Scene(pane,700,700);
		
		
		
	}
	
	public Scene createGameSelectScreenTwo(Stage S,KenoLogic k) { // create the second screen to process user game specifications...
		BorderPane pane = new BorderPane();
		Button b2 = new Button("Ready to pick numbers? Click me!"); // button that advances user to the next screen...
		b2.setOnAction(e->{
			b2.setDisable(true);
			S.setScene(SceneMap.get("game"));
		}); 
		b2.setDisable(true); // we only wan the button to be available when the user enters valid inputs...
		Text t3 = new Text("Select the amount of Rounds you would like to play");
		t3.setStyle("-fx-font-size: 25");
		TextField tf2 = new TextField();
		VBox myB = new VBox(30,t3,tf2,b2);
		myB.setAlignment(Pos.CENTER);
		pane.setCenter(myB);
		// below handles the input of the user to ensure that the proper amount of rounds have been entered by the user
		// otherwise notify the user of their error and demand valid input...
		tf2.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER))
			if(Integer.valueOf(tf2.getText()) >= 1 && Integer.valueOf(tf2.getText()) <= 4) { // input is at least 1 and no more
				k.updateNumRounds(Integer.valueOf(tf2.getText())); 							// than four
				tf2.clear(); // clear the input in case the user would like to revise their decision...
				b2.setDisable(false);
			}
			else { // user has entered an incorrect value, reject and listen for new input, make sure the button is turned off...
				tf2.clear();
				tf2.setText("Incorrect Value entered! you must play at least one round and no more than four!");
				b2.setDisable(true);
			}
		
		});
		return new Scene(pane,700,700);
	}
	
	public Scene createGameScreen(Stage S,KenoLogic k) { // create the screen that contains a gridpane allowing user to select
		BorderPane pane = new BorderPane();				 // to select their betCard
		mB1 = new MenuBar(); // bar to hold menu
		m1 = new Menu("Menus"); // Menus to hold the rules,Odds, and the option to exit the app items...
		mI1 = new MenuItem("Rules of Keno"); // menu item 1
		mI1.setOnAction(e->{
			k.clear();
			S.setScene(SceneMap.get("rules"));
			
		}); // action of menu item 1
		mI2 = new MenuItem("Odds of Winning"); // menu item 2
		mI2.setOnAction(e->{
			k.clear();
			S.setScene(SceneMap.get("odds"));
		}); // action of menu item 2
		mI3 = new MenuItem("Exit Keno");// menu item 3
		mI3.setOnAction(e->Platform.exit());// action of menu item 3
		mI4 = new MenuItem("Change game look");
		mI4.setOnAction(e->{
			pane.setStyle("-fx-background-color: seagreen;");
			mB1.setStyle("-fx-background-color: forestgreen;");
		});
		m1.getItems().addAll(mI1,mI2,mI3,mI4);
		mB1.getMenus().add(m1);
		pane.setTop(mB1);
		GridPane grid = new GridPane(); // grid pane to hold choices 1-80
		grid.setAlignment(Pos.CENTER);
		mB1 = new MenuBar();
		Button b3 = new Button("Begin Drawing!"); //button that will alow the user to start the drawing process
		b3.setOnAction(e->{
			grid.getChildren().clear();
			createGrid(grid,k,b3);
			S.setScene(SceneMap.get("draw"));
		});
		b3.setDisable(true); // button starts turned off, it would be really bad if the betCard was empty and the game could still start
		createGrid(grid,k,b3); // create the grid and pass the button to advance game screen
		Text t2 = new Text("Choose your spots! Dont exceed the number of spots you've chosen!");
		Text t3 = new Text("if you select more than you've chosen or dont like your betCard, simply hit reset.");
		t2.setStyle("-fx-font-size: 18;");
		t3.setStyle("-fx-font-size: 14");
		Button b4 = new Button("Reset");
		b4.setOnAction(e->{
			grid.getChildren().clear();
			k1.getBetCard().clear();
			createGrid(grid,k,b3);
			
		});
		Button b5 = new Button("Random");
		b5.setOnAction(e->{
			k.getBetCard().clear();
			k.doQuickPick(k.getDraws());
			grid.setDisable(true);
			b3.setDisable(false);
		});
		pane.setRight(b5);
		//while(k1.getBetCard().size() != k.getRounds()) {b3.setDisable(true);}
		VBox myB = new VBox(30,t2,t3,grid,b3);
		myB.setAlignment(Pos.CENTER);
		pane.setCenter(myB);
		pane.setLeft(b4);
		return new Scene(pane, 700,700);
		
	}
	
	public Scene createDrawScreen(Stage S,KenoLogic k) {
		BorderPane pane = new BorderPane();
		mB1 = new MenuBar(); // bar to hold menu
		m1 = new Menu("Menus"); // Menus to hold the rules,Odds, and the option to exit the app items...
		mI1 = new MenuItem("Rules of Keno"); // menu item 1
		mI1.setOnAction(e->{
			k.clear();
			S.setScene(SceneMap.get("rules"));
			
		}); // action of menu item 1
		mI2 = new MenuItem("Odds of Winning"); // menu item 2
		mI2.setOnAction(e->{
			k.clear();
			S.setScene(SceneMap.get("odds"));
		}); // action of menu item 2
		mI3 = new MenuItem("Exit Keno");// menu item 3
		mI3.setOnAction(e->Platform.exit());// action of menu item 3
		mI4 = new MenuItem("Change game look"); // menu to change the look of the game...
		mI4.setOnAction(e->{ 
			pane.setStyle("-fx-background-color: seagreen;");
			mB1.setStyle("-fx-background-color: forestgreen;");
		});
		m1.getItems().addAll(mI1,mI2,mI3,mI4); // adding all the menus to the bar...
		mB1.getMenus().add(m1);
		pane.setTop(mB1);
		drawDisplay = new ListView<Integer>(); // initialzing the listview...
		
		Text t3 = new Text();
		Text t4 = new Text();
		Text t5 = new Text();
		EventHandler<ActionEvent> eventHandler = (e->{ // event handler for the pause...
			drawDisplay.getItems().add(k.getDrawList().get(i));
			i++;
			
		});
		Timeline displayDelay = new Timeline( new KeyFrame(Duration.seconds(1), eventHandler));
		displayDelay.setCycleCount(20);
		Button b1 = new Button("press me to Draw! ");
		b1.setDisable(false);
		Button b2 = new Button("Main Menu");
		b1.setOnAction(e->{
			counter++;
			if(counter == k.getRounds()) { // we've reached the max amount of draws the user specified, no more drawing...
				b1.setDisable(true);
			}
			drawDisplay.getItems().clear(); // clear the listview for the next round of draws
			k.getmatchList().clear(); // clear our list of matches...
			k.doDraws(k.getBetCard(), k.getDraws(), k.getCash()); // function to handle drawing
			displayDelay.play(); // pause to display each randomly selected numby 1->20
			drawDisplay.getItems().clear();
			//displaying the results
			t3.setText("Matches for this Draw: " + Integer.toString(k.getmatchList().size()) + "\n");
			t4.setText("Current Winnings: " + Double.toString(k.getCash()) + "\n");
			t5.setText("Your betCard: " + k.printBetCard() + "\n");
		});
		b2.setOnAction(e->{ // button returns the user to the main menu, but first resets the game
			counter = 0;
			t3.setText("");
			t4.setText("");
			t5.setText("");
			b1.setDisable(false);
			drawDisplay.getItems().clear();
			k.getBetCard().clear();
			k.getmatchList().clear();
			k.getDrawList().clear();
			k.updateDraws(0);
			k.updateNumRounds(0);
			i = 0;
			S.setScene(SceneMap.get("root"));
			});
		//styling the text fields
		t3.setStyle("-fx-font-size: 20;");
		t4.setStyle("-fx-font-size: 20;");
		t5.setStyle("-fx-font-size: 20");
		VBox bBox = new VBox(100,b1,t5,t3,t4,b2); // vbox to organize the results display and buttons
		bBox.setStyle("-fx-padding: 50 50 50 50;");
		pane.setRight(bBox);
		pane.setLeft(drawDisplay);
		return new Scene(pane,700,700);
	}
	
	public void createGrid(GridPane g,KenoLogic k,Button b) { // create grid method similar to the one showed in class
		int q = 0;											  // with tinkering to fit my game model
		if(b.isDisable() == false) {
			g.setDisable(false);
			b.setDisable(true);
			}
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 10; j++) {
				Button b1 = new Button(Integer.toString(q+=1));
				b1.setOnAction(e->{
					b1.setDisable(true);
					k.addToBetCard(Integer.valueOf(b1.getText()));
					if(k.getBetCard().size() == k.getDraws()) {
						g.setDisable(true);
						b.setDisable(false);
					}
					else {
						b.setDisable(true);
					}
				});
				g.add(b1, i, j);
			}
		}
	}
	
	
	//TODO: 1. BUILD THE GAME SCREEN AND FINISH THE PROJECT | 2. TEST GAME FOR FLAWS | 3.REVISE AND IMPROVE GUI QUALITY
	
}
