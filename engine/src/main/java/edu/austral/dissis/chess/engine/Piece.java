package edu.austral.dissis.chess.engine;


import java.util.List;

public class Piece{
  private Color color;
  private List<List> initialPosition;
  public List<List> actualPosition;
  private boolean isAlive;
  private boolean hasMoved;

  public Piece(Color color, List<List> initialPosition, List<List> actualPosition, boolean isAlive, boolean hasMoved) {
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
  public List<List> getActualPosition() {
    return actualPosition;
  }

  public void move(Board board){
    hasMoved = true;
  }

  public void moveDown() {
  }
}
