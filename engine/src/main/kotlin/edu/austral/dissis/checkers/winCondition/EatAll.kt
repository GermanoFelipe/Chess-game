package edu.austral.dissis.checkers.winCondition

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import edu.austral.dissis.twoDBoardGame.winCondition.WinCondition

class EatAll: WinCondition {

  override fun checkWinner(board: DefaultBoard, color: Color, gameRules: List<RuleManager>): Boolean {
    return board.getUsedPositions().none { board.getPiece(it)?.pieceColor == color}
  }
}