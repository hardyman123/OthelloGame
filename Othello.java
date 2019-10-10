package Othello;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Othello {
	
	Pane _mainPane;
	BorderPane _bPane;
	int _p1Level;
	int _p2Level;
	int _currentPlayer;
	Board _board;
	Human _humanOne;
	Human _humanTwo;
	Computer _computerOne;
	Computer _computerTwo;
	ArrayList<Move> _moves;
	Menu _sideBar;
	Referee _referee;
	
	public Othello() {
		_currentPlayer = 1;
		_bPane = new BorderPane();
		_mainPane = new Pane();
		_board = new Board(_mainPane);
		_mainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new ClickHandler());
		
		
//		_humanOne = new Human(1, _board);
//		_humanTwo = new Human(2);
//		_computerOne = new Computer(1, 1);
//		_computerTwo = new Computer(2, 2);
//		_referee = new Referee(_humanOne, _computerOne, this);
		
		this.setupReferee();
		
		
	}
	
	
	public void setupGame(int player1, int player2) {
		if(player1==0) {
			_humanOne = new Human(1,_board);
			_referee.addPlayer(_humanOne, 1);
		} if(player1==1 || player1==2 || player1==3) {
			_computerOne = new Computer(player1,1);
			_referee.addPlayer(_computerOne, 1);
		}
		if(player2==0) {
			_humanTwo = new Human(2, _board);
			_referee.addPlayer(_humanTwo, 2);
		}if(player2==1 || player2==2 || player2==3) {
			_computerTwo = new Computer(player2, 2);
			_referee.addPlayer(_computerTwo, 2);
		}
	}
	
	
	
	public Menu getSideBar() {
		return _sideBar;
	}
	
	public Referee getReferee() {
		return _referee;
	}
	
	
	public void setSideLabel(int currentPlayer) {
		if(currentPlayer==1) {
			_sideBar.setLabelState(4);
		} else {
			_sideBar.setLabelState(5);
		}
	}
	
	
	
	
	public void setupReferee() {
		_referee = new Referee(this);
	}
	
	
	
	
	public Player getOtherComputer(Player thisComputer) {
		if(thisComputer.getPlayerNumber()==1) {
			return _computerTwo;
		} else {
			return _computerOne;
		}
	}

	
	public void computerMove(int currPlayer, Board board, Player computer) {
		Move bestMove = this.getMiniMaxMove(board, computer.getIntelligence(), computer);
		System.out.println("I made a real move");
		computer.makeMove(bestMove, board);
		this.makeValidMove(bestMove.getRow(), bestMove.getColumn(), currPlayer, board);
		_sideBar.setLabelState(4);
		this.checkGameOver();
		_referee.switchCurrentPlayer();
		_referee.setSideLabel();
		_referee.makeCurrentTurn(_referee.getCurrentPlayer());
	}
	
	
	
		
	public Move getMiniMaxMove(Board board, int intelligence, Player currentPlayer) {
		
		int curr = currentPlayer.getPlayerNumber();
		
		//Computer newCompter = new Computer(intelligence, curr);	// WANT TO TAKE IN A PLAYER 
		
		int otherPlayer = 0;
		if(curr==1) {
			otherPlayer = 2;
		} else {
			otherPlayer = 1;
		}
		if(board.checkGameOver() && board.decideWinner()==curr) {
			Move highValue = new Move(0,0,1000000);
			return highValue;
		} else if(board.checkGameOver() && board.decideWinner()==otherPlayer) {
			Move lowValue = new Move(0,0,-1000000);
			return lowValue;
		} else if(board.checkGameOver() && board.decideWinner()==3) {
			Move neutralValue = new Move(0,0,0);
			return neutralValue;
		}
		ArrayList<Move> validMoves = this.getMoves(currentPlayer, board);
		int numberMoves = validMoves.size();
		if(validMoves.isEmpty()) {
			if(intelligence == 1) {
				Move lowValue = new Move(0,0,-1000000);
				return lowValue;
			} else {
				Move nextLevel = this.getMiniMaxMove(board, intelligence-1,_referee.getOpposingPlayer(currentPlayer));
				nextLevel.negateValue();
				return nextLevel;
			}
		}
		for(int i=0; i<numberMoves; i++) {	
			Board copyBoard = new Board(board);
			
			
			currentPlayer.makeMove(validMoves.get(i), copyBoard);
			//this.makeValidMove(validMoves.get(i).getRow(), validMoves.get(i).getColumn(), currentPlayer.getPlayerNumber(), copyBoard);
			
			System.out.println("BELOW THIS");
			if(intelligence == 1) {
				int newValue = copyBoard.evaluateBoard(curr);
				validMoves.get(i).setValue(newValue);
				System.out.println("ABOVE THIS");
			} else {
				Move anotherLevel = this.getMiniMaxMove(copyBoard, intelligence-1, _referee.getOpposingPlayer(currentPlayer));
				anotherLevel.negateValue();
				validMoves.get(i).setValue(anotherLevel.getValue());
			}
		}
		Move bestMove = this.getHighestValue(validMoves);
		return bestMove;
	}
	
	
	public Move getHighestValue(ArrayList<Move> moves) {
		Move bestMove = new Move(0,0,-1000000);
		int lengthArr = moves.size();	
		for(int i=0; i<lengthArr; i++) {
			if(moves.get(i).getValue()>bestMove.getValue()) {
				bestMove = moves.get(i);
			}
		}
		return bestMove;
	}
	
	
	
	// Gives Othello a reference of the sidepanel
	public void assignSidePanel(Menu menu) {
		_sideBar = menu;
	}
	
	


	
	public void clearLines(Player currPlayer) {
		ArrayList<Move> moves = this.getMoves(currPlayer, _board);
		moves.forEach((n) -> this.resetMoves(n));
	}
	
	
	public void makeValidMove(int row, int col, int currPlayer, Board board) {
		this.flipSandwiches(row, col, board);
		this.setCurrentPlayer(this.getAltPlayer(currPlayer));
	}
	
	
	public void populateOpponentPotentials(Player opponent) {
		
		ArrayList<Move> moves = this.getMoves(opponent, _board);
		moves.forEach((n) -> this.makeMovePotential(n));
	}
	
	public void populatePotentials(Player player) {
		ArrayList<Move> moves = this.getMoves(player, _board);
		moves.forEach((n) -> this.makeMovePotential(n));
	}
	
	
	
	
	public ArrayList<Move> getMoves(Player currentPlayer, Board board) {
		//int curr = currentPlayer.getPlayer();
		ArrayList<Move> validMoves = new ArrayList<Move>();
		for(int row=1; row<9; row++) {
			for(int col=1; col<9; col++) {
				if(_board.getSquare(row, col).getPlayer()==0) {
					if(checkSandwich(row, col, currentPlayer, board)) {
						validMoves.add(new Move(row, col, Constants.VALUES_ARRAY[row][col]));
					}
				}
			}
		}
		return validMoves;
	}
	
	
	
	public void resetMoves(Move move) {
		
		int row = move.getRow();
		int col = move.getColumn();
		
		if(!_board.getSquare(row, col).isItOccupied()) {
			_board.getSquare(row, col).turnToWall();
		}
		
	}
	
	public int getAltPlayer(int player) {
		int altPlayer = 0;
		if(player==1) {
			altPlayer = 2;
		} else {
			altPlayer = 1;
		}
		return altPlayer;
	}
	
	
	public void makeMovePotential(Move move) {
		_board.getSquare(move.getRow(), move.getColumn()).makePotential();
	}
	
	
	
	
	
	
	
	
	
	public void flipSandwiches(int row, int col, Board board) {
		
		//int row = move.getRow();
		//int col = move.getColumn();
		int playern = this.getCurrentPlayer();
		ArrayList<Move> movesToSwitch = new ArrayList<Move>();
		
		for(int i=-1; i<2; i++) {
			for(int j=-1; j<2; j++) {
				if(!(i==0 && j==0)) {
					boolean flag = false;
					int rowOff=row+i;
					int colOff=col+j;
					
					ArrayList<Move> moveList = new ArrayList<Move>();
					while(true) {
						if(board.getSquare(rowOff, colOff).getPlayer()==0) {
							break;
						}
						if(board.getSquare(rowOff, colOff).getPlayer()==this.getOtherPlayer()) {
							moveList.add(new Move(rowOff,colOff,Constants.VALUES_ARRAY[rowOff][colOff]));
							rowOff += i;
							colOff += j;
							flag = true;
						} if(board.getSquare(rowOff, colOff).getPlayer()==this.getCurrentPlayer()) {
							if(flag==true) {
								movesToSwitch.addAll(moveList);
							}
							break;
						}
					}	
				}
			}
		}
		//movesToSwitch.forEach((n) -> this.mommyHelper(n));
		movesToSwitch.forEach((n) -> this.sandwichHelper(n));
	}
	
	public void sandwichHelper(Move move) {
		_board.getSquare(move.getRow(), move.getColumn()).flipPiece();
	}
	
	
	
	// Checks if there is a sandwich at this square
	public boolean checkSandwich(int row, int col, Player player, Board board) {
//		if(_board.getSquare(row, col).getPlayer()==this.getOtherPlayer()) {
//			return false;
//		}
		int curr = player.getPlayerNumber();
		int opPlayer = this.getAltPlayer(curr);
	//	System.out.println("The opponent Player is: "+opPlayer+" and the current player is: "+player);
		boolean isSandwich = false;
		for(int i=-1; i<2; i++) {
			for(int j=-1; j<2; j++) {
				if(!(i==0 && j==0)){

					boolean flag = false;
					
					int rowOff=row+i;
					int colOff=col+j;
					
					while(true) {
						if(board.getSquare(rowOff, colOff).getPlayer()==0) {
							//System.out.println("step 1");
							break;
						}
						// IF ANYTHING GOES WRONG CHECK THIS LINE!!!
						if(board.getSquare(rowOff, colOff).getPlayer()==this.getAltPlayer(curr)) {
							//System.out.println("rowOff: "+i);
							//System.out.println("colOff: "+j);
							rowOff += i;
							colOff += j;
							flag = true;
						} if(board.getSquare(rowOff, colOff).getPlayer()==curr) {
							//System.out.println("step 3");
							if(flag==true) {
								isSandwich = true;
							}
							break;
						}
					}
				}
			}
		}
		return isSandwich;
	}			
	
	// This method returns the opposing player is
	public int getOtherPlayer() {
		if(_currentPlayer == 1) {
			return 2;
		} else {
			return 1;
		}
	}
	
	public void setCurrentPlayer(int currPlayer) {
		_currentPlayer = currPlayer;
	}
	
	// This method returns who the current player is
	public int getCurrentPlayer() {
		//System.out.println("The current player is player: " + _currentPlayer);
		return _currentPlayer;
	}
	
	public void setupLevels(int player, int level) {
		// Will be called in Menu when you click apply settings and will give
		// the player selected a level of difficulty (human, c1, c2, or c3)
		if(player==1) {
			_p1Level = level;
		} else {
			_p2Level = level;
		}
	}
	
	public boolean checkGameOver() {
		
		if(this.getMoves(_referee.getCurrentPlayer(), _board).isEmpty() && this.getMoves(_referee.getOtherPlayer(), _board).isEmpty()) {
			_sideBar.setLabelState(_board.decideWinner());
			System.out.println("Game Over");
			return true;
		}
		if(_board.checkGameOver()) {
			_sideBar.setLabelState(_board.decideWinner());
			System.out.println("Game Over");
			return true;
		}
		
		return false;
	}
	
	
	
	// This returns the board
	public Board getBoard(){
		return _board;
	}
	
	// This class handles getting the coordinates of a mouse click and assigning them to a squares coordinates
	private class ClickHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			int col = (int) (event.getSceneX()/Constants.SQUARE_SIZE);
			int row = (int) (event.getSceneY()/Constants.SQUARE_SIZE);
			int player = _board.getSquare(row, col).getPlayer();
			String playerColor = null;
			if(player == 1) {
				playerColor = "white";
			} else if(player == 2) {
				playerColor = "black";
			}
			if(row==0 || col==0 || row==9|| col==9) {
				return;
			}
			if(_board.getSquare(row, col).isItOccupied()) {
				System.out.println("The square [" + row + "," + col + "] has a " + playerColor + " piece in it!");
			} else {
				

				
				
				if(_referee.getCurrentPlayer().getIntelligence()==0 && Othello.this.checkSandwich(row,col,_referee.getCurrentPlayer(), _board)) {
		
					Move newMove = new Move(row,col,0);
					_referee.getCurrentPlayer().makeMove(newMove, _board);
					Othello.this.clearLines(_referee.getCurrentPlayer());
					Othello.this.makeValidMove(newMove.getRow(), newMove.getColumn(), _referee.getCurrentPlayer().getPlayerNumber(), _board);
					_referee.switchCurrentPlayer();
					_referee.setSideLabel();
					Othello.this.checkGameOver();
					_referee.makeCurrentTurn(_referee.getCurrentPlayer());
				}
				
				
				

				
				System.out.println("---------------");
			}
		}
	}
	
	
	
	
	

//	public void refMoveMaker(int appropriatePlayer, int row, int col) {
//		
//		if(appropriatePlayer==1) {
//			
//			if(Othello.this.checkSandwich(row, col, _humanOne.getPlayer()) && _humanOne.getPlayer()==_currentPlayer) {
//				_humanOne.makeMove(row, col, _board);
//				Othello.this.makeValidMove(row, col);
//				_sideBar.setLabelState(2);
//				Othello.this.checkGameOver();
//				System.out.println("current player is: " +_currentPlayer);
//			}
//			
//		} else if(appropriatePlayer==2) {
//			if(Othello.this.checkSandwich(row, col, _humanTwo.getPlayer()) && _humanTwo.getPlayer()==_currentPlayer) {
//				_humanTwo.makeMove(row, col, _board);
//				Othello.this.makeValidMove(row, col);
//				_sideBar.setLabelState(1);
//				Othello.this.checkGameOver();
//				System.out.println("current player is: " +_currentPlayer);
//
//			}
//		}
//	}
	
	
//	public void flippSandwiches(int row, int col) {
//		for(int i=-1; i<2; i++) {
//			for(int j=-1; j<2; j++) {
//				if(!(i==0 && j==0)) {
//					boolean flag = false;
//					int rowNew = row+i;
//					int colNew = col+j;
//					int numberOfFlips = 0;
//					while(_board.getSquare(rowNew,colNew).getPlayer() != 0 && _board.getSquare(rowNew, colNew).getPlayer() == this.getOtherPlayer()) {
//						numberOfFlips++;
//						rowNew += i;
//						colNew += j;
//						if(_board.getSquare(rowNew, colNew).getPlayer() == this.getCurrentPlayer()) {
//							for(int k=numberOfFlips; k>0; k--) {
//								rowNew -= i;
//								colNew -= j;
//								_board.getSquare(rowNew, colNew).flipPiece();
//							}		
//						}				
//					}
//				}
//			}
//		}
//	}
	
	
	
	public Pane getRoot() {
		return _mainPane;
	}
}