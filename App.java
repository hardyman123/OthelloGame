package Othello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
  * This is the  main class where your Othello game will start.
  * The main method of this application calls the App constructor. You
  * will need to fill in the constructor to instantiate your game.
  *
  * Class comments here...
  *
  */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Create top-level object, set up the scene, and show the stage here.
    	stage.setTitle("Othello");
    	PaneOrganizer organizer = new PaneOrganizer();
    	Scene scene = new Scene(organizer.getRoot(), Constants.APP_WIDTH, Constants.APP_HEIGHT);	//width x height
    	stage.setScene(scene);
    	stage.show();
    }

    /*
    * Here is the mainline! No need to change this.
    */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}
