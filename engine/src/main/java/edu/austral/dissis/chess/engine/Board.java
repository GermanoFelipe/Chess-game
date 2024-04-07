package edu.austral.dissis.chess.engine;

public class Board {
  private int xCord;
  private int yCord;
  private Piece piece;

  public Board(int xCord, int yCord, Piece piece) {
    this.xCord = xCord;
    this.yCord = yCord;
    this.piece = piece;
  }
}
