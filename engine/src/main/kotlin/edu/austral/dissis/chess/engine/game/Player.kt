package edu.austral.dissis.chess.engine.game

import edu.austral.dissis.chess.engine.piece.Color

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