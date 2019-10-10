package Othello;


public class Computer implements Player {
	
	int _playerNumber;
	int _intelligence;
	Referee _referee;
	
	
	public Computer(int miniMaxLevel, int player) {
		_playerNumber = player;
		_intelligence = miniMaxLevel;
	}

	public int getPlayerNumber() {
		return _playerNumber;
	}
	
	public int getIntelligence() {
		return _intelligence;
	}
	
	
	@Override
	public void makeMove(Move move, Board board) {
		// TODO Auto-generated method stub
		int row = move.getRow();
		int col = move.getColumn();
		board.getSquare(row, col).addPiece(_playerNumber);
		System.out.println("i made move at ["+row+","+col+"]");
	}

	



	@Override
	public void addReferee(Referee referee) {
		// TODO Auto-generated method stub
		_referee = referee;
	}
	
	
	
	
}