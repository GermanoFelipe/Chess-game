package edu.austral.dissis.twoDBoardGame.piece


import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

data class Piece(val type: PieceType,
                 val pieceColor: Color,
                 var hasMoved: Boolean,
                 private val id: String,
                 val pieceRuleManager: RuleManager,
            ) {

  fun validateMovement(movement: Movement, board: DefaultBoard): RuleResult {
    return pieceRuleManager.checkMovement(board, movement)
  }
  fun hasMoved(): Boolean {
    return hasMoved
  }

  fun changeHasMoved() {
    hasMoved = true
  }

  fun getId(): String{
    return if (id!= "") id
    else this.hashCode().toString()
  }
}