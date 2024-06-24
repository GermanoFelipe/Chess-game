package edu.austral.dissis.twoDBoardGame.game

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position

data class Movement (
  private val from: Position,
  private val to: Position,
  private val board: DefaultBoard,
  private val piece: Piece,
  ) {

  fun getFrom(): Position {
    return from
  }

  fun getTo(): Position {
    return to
  }

  fun getBoard(): DefaultBoard {
    return board
  }
  fun getPiece(): Piece {
    return piece
  }
}