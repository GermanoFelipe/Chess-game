package edu.austral.dissis.chess.engine;


public class Piece {
  private Color color;
  private int initialPosition;
  public int actualPosition;
  private boolean isAlive;
  private boolean hasMoved;

  public Piece(Color color, int initialPosition, int actualPosition, boolean isAlive, boolean hasMoved) {
    this.color = color;
    this.initialPosition = initialPosition;
    this.actualPosition = actualPosition;
    this.isAlive = isAlive;
    this.hasMoved = hasMoved;
  }

  public boolean isAlive() {
    return isAlive;
  }
  public Color getColor() {
    return color;
  }
  public int getActualPosition() {
    return actualPosition;
  }
}
