package edu.austral.dissis.chess.engine.game.results

import edu.austral.dissis.chess.engine.game.Game

sealed interface MovementResult {
  fun getMessage(): String
}

class ValidMovement (game: Game) : MovementResult{
  override fun getMessage(): String {
    return "Valid Movement"
  }
}

class InvalidNoPiece: MovementResult {
  override fun getMessage(): String {
    return "No piece selected"
    }
}

class InvalidMovement: MovementResult {
  override fun getMessage(): String {
    return "Invalid Movement"
  }
}

class LeavesInCheck: MovementResult {
  override fun getMessage(): String {
    return "Leaves in check"
  }
}

class OccupiedSquare: MovementResult {
  override fun getMessage(): String {
    return "Occupied Square"
  }
}