package edu.austral.dissis.twoDBoardGame.winCondition

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

interface WinCondition {
  fun checkWinner(board: DefaultBoard, color: Color, gameRules: List<RuleManager>): Boolean
}