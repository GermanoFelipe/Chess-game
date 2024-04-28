package edu.austral.dissis.chess.engine.Piece

class Piece(val initialPosition: Position, val actualPosition: Position, val pieceColor: Color,
            val type: PieceType, val hasMoveed: Boolean, val movements: List<Position>, val id: String) {

  fun getInitialPosition(): Position {
    return initialPosition
  }

  fun getActualPosition(): Position {
    return actualPosition
  }

  fun getColor(): Color {
    return pieceColor
  }

  fun getType(): PieceType {
    return type
  }

  fun getMovements(): List<Position> {
    return movements
  }

  fun getId(): String {
    return id
  }
}