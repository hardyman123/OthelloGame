package Othello;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class SmartSquare{
	
	Rectangle _square;
	Pane _mainPane;
	Ellipse _circle;
	boolean _isOccupied;
	Piece _piece;
	int _player;
	
	public SmartSquare() {
		
		_player = 0;
		_isOccupied = false;
		
		_mainPane = new Pane();
		_square = new Rectangle(Constants.SQUARE_SIZE,Constants.SQUARE_SIZE);
		
		
		_mainPane.getChildren().add(_square);
		
	}
	
	public boolean isItOccupied() {
		return _isOccupied;
	}
	
	public void switchPlayer() {
		if(this.getPlayer()==1) {
			this.setPlayer(2);
		} else {
			this.setPlayer(1);
		}
	}
	
	public void setPlayer(int player) {
		_player = player;
	}
	
	public Piece getPiece() {
		return _piece;
	}
	
	public int getPlayer() {
		return _player;
	}
	
	// IF FLIP PIECE GETS CALLED ON A SQUARE THAT DOESN'T HAVE A PIECE, THERE'S A NULLPOINTEREXCEPTION
	public void flipPiece() {
		_piece.flipPiece();
		this.switchPlayer();
	}
	
	public void addPiece(int player) {
		_isOccupied = true;
		_piece = new Piece(player, this);
		this.setPlayer(player);
		//this.setPlayer(_piece.getPlayer());
		//_player = _piece.getPlayer();
		_piece.setX(this.getX());
		_piece.setY(this.getY());
		_square.setFill(Color.GREEN);
		_mainPane.getChildren().add(_piece.getRoot());
	}
	
	public void turnToWall() {
		_square.setFill(Color.GREEN);
	}
	
	public void makePotential() {
		_square.setFill(Color.GRAY);
	}
	
	public void setFill(Paint fill) {
		_square.setFill(fill);
	}
	
	public void setOutline(Paint stroke) {
		_square.setStroke(stroke);
	}
	
	public double getX() {
		double xLoc = _square.getX()/Constants.SQUARE_SIZE;
		return xLoc;
	}
	
	public double getY() {
		double yLoc = _square.getY()/Constants.SQUARE_SIZE;
		return yLoc;
	}
	
	public void setX(int xLoc) {
		_square.setX(xLoc*Constants.SQUARE_SIZE);
	}
	
	public void setY(int yLoc) {
		_square.setY(yLoc*Constants.SQUARE_SIZE);
	}
	
	
	public Node getRoot() {
		return _mainPane;
	}
	
}