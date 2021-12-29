//
// Joseph Lenaghan | CS342 | Project Three Server Program | UIN:676805596

// GUI for Server with easily navigated interface to display information about the server and games being played on it

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
	private Text t1;
	private Text t2;
	private Text t3;
	private TextField tf1;
	private TextField tf2;
	private ListView<String> listItems;
	int portNum = 0;
	HashMap<String,Scene> SceneMap = new HashMap<>();
	ThreadedServer myNet;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("~*Baccarat Server*~");
		
		SceneMap.put("root", createFirstScreen(primaryStage));		
		primaryStage.setScene(SceneMap.get("root"));
		primaryStage.show();
	}
	public Scene createFirstScreen(Stage S) {
		BorderPane pane = new BorderPane();
		pane.setStyle("-fx-background-color: peru");
		pane.setPadding(new Insets(85));
		portNum = 0;
		t1 = new Text("Baccarat Server");
		tf1 = new TextField();
		t1.setStyle("-fx-font-size: 34;");
		t2 = new Text("Enter a port number");
		b1 = new Button ("Turn Server OFF and exit");
		b2 = new Button ("Turn Server ON");
		HBox myHB = new HBox(100,b1,b2);
		b1.setDisable(true);
		b2.setDisable(true);
		myHB.setAlignment(Pos.CENTER);
		VBox myVB = new VBox(75,t1,t2,tf1,myHB);
		tf1.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER)) {
			portNum =(Integer.valueOf(tf1.getText()));
			tf1.clear();
			b1.setDisable(false);
			b2.setDisable(false);
		}
		});
		b1.setOnAction(e->{ // exit the program 
			System.exit(0);
			Platform.exit();
			});
		b2.setOnAction(e->{
			SceneMap.put("stats",createStatScreen(S));
			S.setScene(SceneMap.get("stats"));
			});
		pane.setCenter(myVB);
		myVB.setAlignment(Pos.CENTER);
		return new Scene(pane,700,700);
		
	}
	
	public Scene createStatScreen(Stage S) {
		b4 = new Button("Return to menu");
		b4.setOnAction(e->S.setScene(SceneMap.get("root")));
		listItems = new ListView<String>();
		myNet = new ThreadedServer(data -> {
			Platform.runLater(()->{listItems.getItems().add(data.toString());});
		},portNum);
		BorderPane pane = new BorderPane();
		VBox myB = new VBox(50,b4,listItems);
		pane.setPadding(new Insets(85));
		pane.setStyle("-fx-background-color: peru");
		pane.setCenter(myB);
		return new Scene(pane,700,700);
	}
	
}

