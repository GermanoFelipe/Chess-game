package edu.austral.dissis.chess.engine;

import java.util.List;
import java.util.Map;

public class Board {
  private Piece piece;

  private List<List> board;

  public Board(Piece piece, List<List> board) {
    this.piece = piece;
    this.board = board;
  }

  public List<List> getPosition(){
    //return piece.actualPosition;
    return null;
  }
  public List<List> getBoard (){
    return board;
  }
}
