package Othello;


public class Move {
	
	int _column;
	int _row;
	int _value;
	
	public Move(int row, int column, int value) {
		 _column = column;
		 _row = row;
		 _value = value;
	}
	
	
	public void setValue(int value) {
		_value = value;
	}
	
	
	public void negateValue() {
		_value = -1*_value;
	}
	
	public int getColumn() {
		return _column;
	}
	
	public int getRow() {
		return _row;
	}
	
	public int getValue() {
		return _value;
	}
	
	
}