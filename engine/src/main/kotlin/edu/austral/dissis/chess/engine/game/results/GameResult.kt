package edu.austral.dissis.chess.engine.game.results

sealed interface GameResult {
  fun getMessage(): String
}

class CheckMate : GameResult {
  override fun getMessage(): String {
    return "CheckMate"
  }
}

class Check : GameResult {
  override fun getMessage(): String {
    return "Check"
  }
}

class InvalidGame : GameResult {
  override fun getMessage(): String {
    return "Invalid Game"
  }
}

class ValidGame : GameResult {
  override fun getMessage(): String {
    return "Valid Game"
  }
}
