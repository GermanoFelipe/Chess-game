package edu.austral.dissis.chess.engine.movement.validator.andOrValidator

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.chess.engine.piece.Position

class AndValidator (val validator1: PieceRuleValidator,
                    val validator2: PieceRuleValidator
                    ): PieceRuleValidator {
  override fun checkMovement(from: Position, to: Position, defaultBoard: DefaultBoard): Boolean {
    return validator1.checkMovement(from, to, defaultBoard) && validator2.checkMovement(from, to, defaultBoard)
  }
}