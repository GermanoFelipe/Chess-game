package edu.austral.dissis.chess.engine.game.results

class InvalidMovement: MovementResult {
  override fun getMessage(): String {
    return "Invalid Movement"
  }
}