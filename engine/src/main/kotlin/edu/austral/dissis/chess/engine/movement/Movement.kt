package edu.austral.dissis.chess.engine.movement

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.game.TurnManager
import edu.austral.dissis.chess.engine.piece.Color
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position

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

// fun allowMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
//    val pieceToMove = defaultBoard.pieces[from]
//    return if (pieceToMove != null) {
//      pieceToMove.pieceValidator.checkMovement(from, to, defaultBoard)
//    } else false
//  }
//
//  fun move(from: Position, to: Position, defaultBoard: DefaultBoard): DefaultBoard {
//    if (allowMovement(from, to, defaultBoard)) {
//      val pieceToMove = defaultBoard.pieces[from]
//      val newPosition = Pair(to, pieceToMove)
//      val pieceMoved = Pair(from, null)
//      val finalPieces = defaultBoard.pieces + newPosition + pieceMoved
//      return DefaultBoard(defaultBoard.size, finalPieces)
//    }
//    return defaultBoard
//  }