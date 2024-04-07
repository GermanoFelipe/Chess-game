package edu.austral.dissis.chess.engine;

public class CheckMate implements WinCondition{

  @Override
  public boolean winCondition(Board board, Color color) {
    //if (board.getPiece().isAlive() == false){
    //  System.out.println("CheckMate");
    return false;
    //}
  }
}
