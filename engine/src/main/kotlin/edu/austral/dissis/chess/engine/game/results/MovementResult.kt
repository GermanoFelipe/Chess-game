package edu.austral.dissis.chess.engine.game.results

import edu.austral.dissis.chess.engine.game.Game

sealed interface MovementResult {
  fun getMessage(): String

}

class ValidMovement (val game: Game) : MovementResult{
  override fun getMessage(): String {
    return "Valid Movement"
  }
}

class InvalidMovement: MovementResult {
  override fun getMessage(): String {
    return "Invalid Movement"
  }
}