package edu.austral.dissis.twoDBoardGame.rules.uniqueRules

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class OppositeRow: RuleManager {
  override fun checkMovement(board: DefaultBoard, movement: Movement): RuleResult {
    return if (movement.getTurn() == Color.WHITE){
      if (movement.getTo().row == board.getRow()){
        Valid()
      }else
        Invalid("Invalid movement")
    }else
      if (movement.getTo().row == 1){
        Valid()
      }else
        Invalid("Invalid movement")
  }
}