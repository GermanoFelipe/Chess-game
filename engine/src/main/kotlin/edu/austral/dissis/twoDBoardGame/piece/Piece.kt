package edu.austral.dissis.twoDBoardGame.piece

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

data class Piece(private val type: PieceType,
                 private val pieceColor: Color,
                 private var hasMoved: Boolean,
                 private val id: String,
                 private val pieceRuleManager: RuleManager,
            ) {

  fun validateMovement(
    movement: Movement,
    board: DefaultBoard
  ): RuleResult {

    return pieceRuleManager.checkMovement(board, movement)

  }

  fun getType(): PieceType {
    return type
  }

  fun getColor(): Color{
    return pieceColor
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

  fun getPieceRuleManager(): RuleManager {
    return pieceRuleManager
  }
}