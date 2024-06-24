package edu.austral.dissis.chess.engine.winCondition

import edu.austral.dissis.twoDBoardGame.board.Board
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import edu.austral.dissis.twoDBoardGame.winCondition.WinCondition
import java.awt.Color

class CheckMate: WinCondition {
 // val checkValidator = ()

  override fun checkWinner(board: Board, color: Color, gameRules: List<RuleManager>): Boolean {
    TODO("Not yet implemented")
  }
}