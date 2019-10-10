package Othello;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Board {
	
	SmartSquare[][] _board;
	Pane _boardPane;
	
	public Board(Pane pane) {
		_boardPane = pane;
		this.setupBoard();
	}
	
	
	
	public Board copyBoard(Board board) {
		
		Board copyBoard = new Board(board);
		
		return copyBoard;
	}
	
	
//	public Board(Board board) {
//		
//		
//		
//		return board;
//	}
	
	
	
	
	
	Board(Board board){
		_board = new SmartSquare[10][10];
		
		for(int row=0; row<10; row++) {
			for(int col=0; col<10; col++) {
				SmartSquare square = new SmartSquare();
				square.setY(row);
				square.setX(col);
				_board[row][col] = square;
				if(board.getSquare(row, col).getPlayer()==1) {
					_board[row][col].addPiece(1);
				}
				if(board.getSquare(row, col).getPlayer()==2) {
					_board[row][col].addPiece(2);
				}
				
				//_board[row][col]=board.getSquare(row, col);
			}
		}
		
	}
	
	
	
	
	
	
	public boolean checkGameOver() {
		
		for(int row=1; row<9; row++) {
			for(int col=1; col<9; col++) {
				if(_board[row][col].getPlayer()==0) {
					//System.out.println("Not Over");
					return false;
				}
			}
		}
		System.out.println("GAME OVER!");
		return true;
	}
	
	
	public int decideWinner() {
		int playerOnePieces = 0;
		int playerTwoPieces = 0;
		int caseWinner = 3;
		for(int row=1; row<9; row++) {
			for(int col=1; col<9; col++) {		
				if(_board[row][col].getPlayer()==1) {
					playerOnePieces++;
				}
				if(_board[row][col].getPlayer()==2) {
					playerTwoPieces++;
				}
			}
		}
		if(playerOnePieces>playerTwoPieces) {
			caseWinner = 1;
		}
		if(playerTwoPieces>playerOnePieces) {
			caseWinner = 2;
		}
		return caseWinner;
	}
	
	
	public int evaluateBoard(int currentPlayer) {	
		int playerOneScore = 0;
		int playerTwoScore = 0;
		int position = 0;
		
		for(int row=1; row<9; row++) {
			for(int col=1; col<9; col++) {				
				if(_board[row][col].getPlayer()==1) {
					playerOneScore += Constants.VALUES_ARRAY[row][col];
				}
				if(_board[row][col].getPlayer()==2) {
					playerTwoScore += Constants.VALUES_ARRAY[row][col];
				}
			}
		}
		
		if(currentPlayer==1) {
			position = playerOneScore-playerTwoScore;
			System.out.println("Player 1's position is: " + position);
		}
		else if(currentPlayer==2) {
			position = playerTwoScore-playerOneScore;
			System.out.println("Player 2's position is: " + position);

		}
		return position;
	}
	
	
	
	
	
	

	public void setupBoard() {
		_board = new SmartSquare[10][10];
		
		for(int row=0; row<10; row++) {
			for(int col=0; col<10; col++) {
				if(row>0 && col>0 && row<9 && col<9) {
					SmartSquare square = new SmartSquare();
					square.setFill(Color.GREEN);
					square.setOutline(Color.BLACK);
					square.setY(row);
					square.setX(col);
					_board[row][col] = square;
					_boardPane.getChildren().add(square.getRoot());
				} else {
					SmartSquare square = new SmartSquare();
					square.setFill(Color.BLACK);
					square.setOutline(Color.BLACK);
					square.setY(row);
					square.setX(col);
					_board[row][col] = square;
					_boardPane.getChildren().add(square.getRoot());
				}
			}
		}
		
		this.addPiece(4, 4, 1);
		this.addPiece(4, 5, 2);
		this.addPiece(5, 4, 2);
		this.addPiece(5, 5, 1);
	}
	
	public void addPiece(int row, int col, int player) {
		_board[row][col].addPiece(player);
	}
	
	public SmartSquare getSquare(int row, int col) {
		return _board[row][col];
	}
	
	
	public Pane getRoot() {
		return _boardPane;
	}
	
	
	public SmartSquare[][] getBoard() {
		return _board;
	}
	
	public void setBoard(SmartSquare[][] copy) {
		_board = copy;
	}
	
	
	
}