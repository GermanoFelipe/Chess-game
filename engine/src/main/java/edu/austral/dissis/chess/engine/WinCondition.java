package edu.austral.dissis.chess.engine;

public interface WinCondition {
  public boolean winCondition(Board board, Color color);
}
