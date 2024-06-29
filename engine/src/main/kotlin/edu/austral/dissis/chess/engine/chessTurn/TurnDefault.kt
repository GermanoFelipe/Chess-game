package edu.austral.dissis.chess.engine.chessTurn

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.game.TurnManager
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid

class TurnDefault (private val turnColor: Color) : TurnManager {

  override fun getNextTurn(move: Movement, board: DefaultBoard): TurnManager {
    return when(turnColor){
      Color.WHITE -> TurnDefault(Color.BLACK)
      Color.BLACK -> TurnDefault(Color.WHITE)
    }
  }

  override fun actualTurn(): Color {
    return turnColor
  }

  override fun validateTurn(move: Movement, board: DefaultBoard): RuleResult {
    return Valid()
  }
}