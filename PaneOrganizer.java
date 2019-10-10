package Othello;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PaneOrganizer {
	
	private BorderPane _bPane;
	
	public PaneOrganizer() {
		_bPane = new BorderPane();
		Othello othello = new Othello();
		Menu menu = new Menu(othello);
		_bPane.setCenter(othello.getRoot());
		_bPane.setRight(menu.getRoot());
	}
	
	
	
	public Pane getRoot() {
		return _bPane;
	}
	
	
}