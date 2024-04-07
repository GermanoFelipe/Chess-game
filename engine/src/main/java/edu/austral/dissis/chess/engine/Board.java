package edu.austral.dissis.chess.engine;

import java.util.List;

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
