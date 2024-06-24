package edu.austral.dissis.chess.engine.game.results

import edu.austral.dissis.chess.engine.board.DefaultBoard

sealed interface GameResult {
  fun getMessage(): String
}

class GameRuleBrokenResult : GameResult {
  override fun getMessage(): String {
    return "Game Rule Broken"
  }
}

class LeavesInCheck : GameResult {
  override fun getMessage(): String {
    return "Leaves in check!"
  }
}

class UsedPosicion : GameResult {
  override fun getMessage(): String {
    return "This position is used"
  }
}

class ValidGame : GameResult {
  override fun getMessage(): String {
    return "Valid Game"
  }
}

class ValidWithBoard(val board: DefaultBoard) : GameResult {
  override fun getMessage(): String {
    return "Valid Game"
  }
}