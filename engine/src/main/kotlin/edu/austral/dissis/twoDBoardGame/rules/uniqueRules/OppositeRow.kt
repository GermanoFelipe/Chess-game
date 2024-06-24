package edu.austral.dissis.twoDBoardGame.rules.uniqueRules

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class OppositeRow: RuleManager {
  override fun checkMovement(game: Game, movement: Movement): RuleResult {
    return if (movement.getTurn() == Color.WHITE){
      if (movement.getTo().row == game.getBoard().getRow()){
        return Valid()
      }else
        Invalid("Piece must be moved to the opposite row")
    }else
      if (movement.getTo().row == 1){
        Valid()
      }else
        Invalid("Piece must be moved to the opposite row")
  }
}