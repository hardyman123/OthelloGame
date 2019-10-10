package Othello;


public interface Player{
	public void makeMove(Move move, Board board);
	public int getPlayerNumber();
	public int getIntelligence();
	public void addReferee(Referee referee);
}