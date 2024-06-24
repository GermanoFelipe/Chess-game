package edu.austral.dissis.chess.engine.game.results

import edu.austral.dissis.chess.engine.game.Game

sealed interface MovementResult {
  fun getMessage(): String
}

class Valid (val game: Game) : MovementResult{
  override fun getMessage(): String {
    return "Valid Movement"
  }
}
class NoPieceInPosition: MovementResult {
  override fun getMessage(): String {
    return "No Piece in Position"
  }
}

class GameRuleBroken: MovementResult {
  override fun getMessage(): String {
    return "Game Rule Broken"
  }
}

class PieceRuleBroken: MovementResult {
  override fun getMessage(): String {
    return "Piece Rule Broken"
  }
}
class InvalidMovement: MovementResult {
  override fun getMessage(): String {
    return "Invalid Movement"
  }
}