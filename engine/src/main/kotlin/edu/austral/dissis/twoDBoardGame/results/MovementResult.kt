package edu.austral.dissis.twoDBoardGame.results

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.piece.Color

sealed interface MovementResult{
  fun getGameResult(): Game
}

class UnsuccessfulMovementResult(
  val message: String,
  val game: Game
  ) : MovementResult {

  override fun getGameResult(): Game {
    return game
  }
}

class SuccessfulMovementResult (
  val game: Game
  ): MovementResult {
  override fun getGameResult(): Game {
    return game
  }
}

class WinnerResult (
  val winner: Color,
  val game: Game
  ): MovementResult {
  override fun getGameResult(): Game {
    return game
  }
}