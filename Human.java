package Othello;



public class Human implements Player {
	
	int _playerNumber;
	int _intelligence;
	Board _board;
	Referee _referee;
	
	public Human(int whichPlayer, Board board) {
		_board = board;
		_playerNumber = whichPlayer;
		_intelligence = 0;
	}

	

	public int getPlayerNumber() {
		return _playerNumber;
	}

	@Override
	public void makeMove(Move move, Board board) {
		// TODO Auto-generated method stub
		board.getSquare(move.getRow(), move.getColumn()).addPiece(_playerNumber);
		System.out.println("I just made a move as player "+_playerNumber);
	}

	@Override
	public int getIntelligence() {
		// TODO Auto-generated method stub
		return _intelligence;
	}

	@Override
	public void addReferee(Referee referee) {
		// TODO Auto-generated method stub
		_referee = referee;
	}
	
}