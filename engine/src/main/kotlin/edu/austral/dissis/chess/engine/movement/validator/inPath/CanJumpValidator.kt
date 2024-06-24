package edu.austral.dissis.chess.engine.movement.validator.inPath

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.twoDBoardGame.position.Position

class CanJumpValidator : PieceRuleValidator {
  override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
    return true
  }
}