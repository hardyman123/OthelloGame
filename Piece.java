package Othello;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Piece {
	
	Ellipse _piece;
	int _player;
	SmartSquare _parentSquare;
	
	public Piece(int player, SmartSquare square) {
		_parentSquare = square;
		_player = player;
		_piece = new Ellipse(Constants.CIRCLE_RADIUS,Constants.CIRCLE_RADIUS);
		this.setPieceColor(player);
	}
	
	// Returns which player owns this piece
	public int getPlayer() {
		return _player;
	}
	
	// Gets the current owner switches it to the alternate and changes the color 
	// of the piece accordingly
	public void flipPiece() {
		if(this.getPlayer()==1) {
			_player = 2;
			//_parentSquare.setPlayer(_player);
		} else {
			_player = 1;
			//_parentSquare.setPlayer(_player);
		}
		this.setPieceColor(_player);
	}
	
	// Sets the X location of a piece according to the squares position
	public void setX(double xLoc) {
		_piece.setCenterX(xLoc*Constants.SQUARE_SIZE + Constants.CIRCLE_OFFSET);
	}
	
	// Sets the Y location of a piece according to the squares position
	public void setY(double yLoc) {
		_piece.setCenterY(yLoc*Constants.SQUARE_SIZE + Constants.CIRCLE_OFFSET);
	}
	
	// Given a player, it colors the piece according to who owns it
	public void setPieceColor(int player) {
		if(player == 1) {
			_piece.setFill(Color.WHITE);
		} else {
			_piece.setFill(Color.BLACK);
		}
	}
	
	// Returns the node circle
	public Node getRoot() {
		return _piece;
	}
}