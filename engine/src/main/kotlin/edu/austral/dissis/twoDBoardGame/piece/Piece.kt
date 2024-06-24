package edu.austral.dissis.twoDBoardGame.piece


import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

data class Piece(val type: PieceType,
                 val pieceColor: Color,
                 val hasMoved: Boolean,
                 val id: String,
                 val pieceRuleManager: RuleManager,
            ) {

  fun validateMovement(movement: Movement, game: Game): RuleResult {
    return pieceRuleManager.checkMovement(game, movement)
  }
  fun hasMoved(): Boolean {
    return hasMoved
  }
}