package edu.austral.dissis.chess.engine;

import java.util.List;

public class Game {

  private final Board board;
  private final Player player;

  public Game(Board board, Player player) {
    this.board = board;
    this.player = player;
  }

  public Board getBoard() {
    return board;
  }
  
  public Color getColor() {
    return player.color;
  }

  public void endGame() {
    if (new CheckMate().winCondition(board, player.getColor())) {
      System.out.println("Checkmate! Game Over!");
    }
  }
}
