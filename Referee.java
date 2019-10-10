package Othello;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class Referee {
	
	Player _currentPlayer;
	Timeline _timeline;
	Player _player1;
	Player _player2;
	Othello _othello;
	
	public Referee(Othello game) {
		_othello = game;
	}
	
	
	public void makeCurrentTurn(Player player) {
		
		if(_othello.checkGameOver()) {
			return;
		}
		
		_othello.setSideLabel(player.getPlayerNumber());
		
		if(_othello.getMoves(player, _othello.getBoard()).isEmpty()) {
			this.switchCurrentPlayer();
			_othello.setCurrentPlayer(_othello.getOtherPlayer());
			this.makeCurrentTurn(_currentPlayer);
			return;
		}
		
		
		if(player.getIntelligence()==0) {
			_othello.populatePotentials(player);
		} else {
			this.startTimeline();
			//_othello.computerMove(player.getPlayerNumber(), _othello.getBoard(), player);
		}
		
		
	}
	
	
	
	
	
	public void addPlayer(Player player, int playerNumber) {
		if(playerNumber==1) {
			_player1=player;
			_player1.addReferee(this);
		} else if(playerNumber==2) {
			_player2=player;
			_player2.addReferee(this);
		}
	}
	
	
	public void startGame() {
		this.setCurrentPlayer(_player1);
		_othello.getSideBar().setLabelState(1);
		//_othello.populatePotentials(_player1);
		_othello.setCurrentPlayer(1);
		this.makeCurrentTurn(_currentPlayer);
		
	}
	
	
	public void setCurrentPlayer(Player player) {
		_currentPlayer = player;
	}
	
	
	
	// This method returns who the current player is
	public Player getCurrentPlayer() {
		return _currentPlayer;
	}
	
	
	
	public void setSideLabel() {
		if(this.getCurrentPlayer()==_player1) {
			_othello.setSideLabel(4);
		} else if(this.getCurrentPlayer()==_player2) {
			_othello.setSideLabel(5);
		}
	}
	
	
	public Player getOpposingPlayer(Player currentPlayer) {
		if(currentPlayer==_player1) {
			return _player2;
		} else {
			return _player1;
		}
	}

	public Player getRealPlayer() {
		return _player1;
	}
	
	// This method returns the opposing player is
	public Player getOtherPlayer() {
		if(_currentPlayer == _player1) {
			return _player2;
		} else {
			return _player1;
		}
	}
	
	public Player getWhoseTurn() {
		return _currentPlayer;
	}
	
	
	
	// This method switches the current player
	public void switchCurrentPlayer() {
		if(_currentPlayer == _player1) {
			_currentPlayer = _player2;
		} else {
			_currentPlayer = _player1;
		}
	}
	
	
	
	
	
	public void startTimeline() {
		KeyFrame kf = new KeyFrame(Duration.millis(500), new TimeHandler());
		
		_timeline = new Timeline(kf);
		_timeline.setCycleCount(1);
		_timeline.play();
		
	}
	
	
	
	
	
	private class TimeHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			_timeline.stop();
			_othello.computerMove(Referee.this.getCurrentPlayer().getPlayerNumber(), _othello.getBoard(), Referee.this.getCurrentPlayer());
		}
		
	}
	
	
	
	
	
	
}