package edu.austral.dissis.twoDBoardGame.winCondition

import edu.austral.dissis.twoDBoardGame.board.Board
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import java.awt.Color

interface WinCondition {
  fun checkWinner(board: Board, color: Color, gameRules: List<RuleManager>): Boolean
}