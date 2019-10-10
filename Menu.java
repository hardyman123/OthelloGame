package Othello;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Menu {
	
	BorderPane _bPane;
	VBox _mainVBox;
	HBox _optionsHBox;
	VBox _player1VBox;
	VBox _player2VBox;
	Button _applyButton;
	Button _resetButton;
	Button _quitButton;
	Button _startButton;
	ToggleGroup _p1g;
	ToggleGroup _p2g;
	int _player1Level;
	int _player2Level;
	Othello _othello;
	Label _gameState;
	boolean _setup;
	
	// Instantiates the player levels, and sets up the side Menu
	public Menu(Othello othello) {
		_setup = false;
		_othello = othello;
		_bPane = new BorderPane();
		_player1Level = 0;
		_player2Level = 0;
		_gameState = new Label();
		_othello.assignSidePanel(this);
		this.setupMainPanel();
	}
	
	// Sets up the panel with the label instructing a user what to do, adding the two
	// player difficulty menus and the buttons to reset, apply settings, and quit
	public void setupMainPanel() {
		_mainVBox = new VBox(Constants.PANEL_PADDING);
		_mainVBox.setAlignment(Pos.CENTER);
		_mainVBox.setPrefWidth(Constants.PANEL_WIDTH);
		
		//Label instructions = new Label("Select options and Apply Settings!");
		//instructions.setPadding(Constants.INSTRUCTIONS_PADDING);
		
		_optionsHBox = new HBox();
		this.setupPlayer1();
		this.setupPlayer2();
		_optionsHBox.getChildren().addAll(_player1VBox, _player2VBox);
		_optionsHBox.setAlignment(Pos.CENTER);
		
		this.setupApplyButton();
		this.setupResetButton();
		this.setupQuitButton();
		this.setupStartButton();
		//this.setLabelState(0);
		
		_gameState = new Label();
		_gameState.setPadding(Constants.INSTRUCTIONS_PADDING);
		this.setLabelState(0);
		
		
		_mainVBox.getChildren().addAll(_gameState, _optionsHBox, _applyButton, _startButton, _resetButton, _quitButton);
		_bPane.setCenter(_mainVBox);
	}
	
	
	public void setLabelState(int condition) {
		switch(condition) {
		case(0):
			_gameState.setText("Select Options and Apply Settings to Start!");
			break;
		case(1):
			_gameState.setText("GAME OVER! Player 1 WINS!");
			//_gameState.setText("Player 1's Turn!");
			break;
		case(2):
			_gameState.setText("GAME OVER! Player 2 WINS!");
			//_gameState.setText("Player 2's Turn!");
			break;
		case(3):
			_gameState.setText("GAME OVER! IT'S A TIE!");
			break;
		case(4):
			_gameState.setText("Player 1's Turn!");
			//_gameState.setText("GAME OVER! Player 2 WINS!");
			break;
		case(5):
			_gameState.setText("Player 2's Turn!");
			//_gameState.setText("GAME OVER! IT'S A TIE!");
			break;
		default:
			break;
		}		
	}
	
	public boolean isSetupComplete() {
		return _setup;
	}
	
	public void completeSetup() {
		_setup = true;
	}
	
	public void setupStartButton() {
		_startButton = new Button("Start Game");
		_startButton.setOnAction(new StartHandler());
	}
	
	
	// Creates the quit button and assigns it to its eventhandler
	public void setupQuitButton() {
		_quitButton = new Button("Quit");
		_quitButton.setOnAction(new QuitHandler());
	}
	
	// Creates the reset button and assigns it to its eventhandler
	public void setupResetButton() {
		_resetButton = new Button("Reset Game");
		_resetButton.setOnAction(new ResetHandler());
	}
	
	// Creates the settings button and assigns it to its eventhandler
	public void setupApplyButton() {
		_applyButton = new Button("Apply Settings");
		_applyButton.setOnAction(new SettingsHandler());
	}
	
	// Sets up the toggle buttons and group for player 1
	public void setupPlayer1() {
		_player1VBox = new VBox(Constants.SETTINGS_PADDING);
		Label player1ops = new Label("Select P1 Settings");
		_player1VBox.setPadding(Constants.PLAYER_BOX_PADDING);
		
		_p1g = new ToggleGroup();
		//_p1g.selectedToggleProperty().addListener(new ToggleListener());
		RadioButton human = new RadioButton("Human");
		human.setToggleGroup(_p1g);
		human.setSelected(true);
				
		RadioButton c1 = new RadioButton("Level 1");
		c1.setToggleGroup(_p1g);
		
		RadioButton c2 = new RadioButton("Level 2");
		c2.setToggleGroup(_p1g);
		
		RadioButton c3 = new RadioButton("Level 3");
		c3.setToggleGroup(_p1g);
		
		_player1VBox.getChildren().addAll(player1ops, human, c1, c2, c3);
	}
	
	// Sets up the toggle buttons and group for player 2
	public void setupPlayer2() {
		_player2VBox = new VBox(Constants.SETTINGS_PADDING);
		Label player2ops = new Label("Select P2 Settings");
		_player2VBox.setPadding(Constants.PLAYER_BOX_PADDING);
		
		_p2g = new ToggleGroup();
		//_p2g.selectedToggleProperty().addListener(new ToggleListener());
		RadioButton human = new RadioButton("Human");
		human.setToggleGroup(_p2g);
		human.setSelected(true);
		
		RadioButton c1 = new RadioButton("Level 1");
		c1.setToggleGroup(_p2g);
		
		RadioButton c2 = new RadioButton("Level 2");
		c2.setToggleGroup(_p2g);
		
		RadioButton c3 = new RadioButton("Level 3");
		c3.setToggleGroup(_p2g);
		
		_player2VBox.getChildren().addAll(player2ops, human, c1, c2, c3);
	}
	
	// Depending on the label and player passed, it assigns that player a difficulty
	// level based on the label of the button selected for it
	public void assignLevel(String label, int player) {
		int level;
		switch (label) {
		case ("Human"):
			level = 0;
			break;
		case ("Level 1"):
			level = 1;
			break;
		case ("Level 2"):
			level = 2;
			break;
		case ("Level 3"):
			level = 3;
			break;
		default:
			level = 0;
			break;
		}
		
		if(player == 1) {
			_player1Level = level;
		} else {
			_player2Level = level;
		}
	}
	
	// Returns the string value of the current selected button in the toggle group passed
	public String getToggleGroupToggle(ToggleGroup group) {
		RadioButton button = (RadioButton) group.getSelectedToggle();
		String text = button.getText();
		return text;
	}
	
	// Not needed AFAIK
	@SuppressWarnings("unused")
	private class ToggleListener implements ChangeListener<Toggle> {
		@Override
		public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
		}
	}
	
	
	private class StartHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			
			if(Menu.this.isSetupComplete()) {
				_othello.getReferee().startGame();
				//_othello.startGame();
				Menu.this.setLabelState(4);
			}
		}	
	}
	
	
	
	// Handles assigning levels to each player based off of the radio buttons selected
	private class SettingsHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			Menu.this.assignLevel(Menu.this.getToggleGroupToggle(_p1g), 1);
			Menu.this.assignLevel(Menu.this.getToggleGroupToggle(_p2g), 2);
			
			_othello.setupGame(_player1Level, _player2Level);
			
			_othello.setupLevels(1, _player1Level);
			_othello.setupLevels(2, _player2Level);
			System.out.println(_player1Level);
			System.out.println("^Player 1's level");
			System.out.println(_player2Level);
			System.out.println("^Player 2's level");
			System.out.println("------------------");
			
			_othello.getBoard().evaluateBoard(_othello.getCurrentPlayer());
			Menu.this.completeSetup();
			//_othello.startGame();
		}
	}
	
	// Handles restarting a game
	private class ResetHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			System.out.println("Reset Game");
			//_othello.setCurrentPlayer(1);
			//_othello.checkMoves(1);
			//Menu.this.setLabelState(4);
		}
	}
	
	// Handles quitting the game
	private class QuitHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			System.exit(0);
		}
	}
	
	// Returns the root node
	public Pane getRoot() {
		return _bPane;
	}
	
}