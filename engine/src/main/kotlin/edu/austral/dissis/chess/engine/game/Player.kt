package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.game.results.MovementResult
import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.piece.Color
import edu.austral.dissis.chess.engine.piece.Position

class Player (private val color: Color) {

//  fun movePiece(board: Board, from: Position, to: Position): MovementResult {
//    val piece = board.getPiece(from) ?: throw IllegalArgumentException("No piece at $from")
//    if (piece.pieceValidator)
//    return ruleManager.validateMovement(board, movement)
//  }
  fun getColor(): Color {
    return color
  }
}