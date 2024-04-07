package edu.austral.dissis.chess.engine;

import java.util.List;

public class Player {

  private boolean turn;
  public Color color;
  public List<Piece> pieces;
  public List<Piece> deadPieces;

  public Player(boolean turn, Color color, List<Piece> pieces, List<Piece> deadPieces) {
    this.turn = turn;
    this.color = color;
    this.pieces = pieces;
    this.deadPieces = deadPieces;
  }

  public Color getColor() {
    return color;
  }

  //public
}
