package edu.austral.dissis.twoDBoardGame.rules.boardRulesValidator

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class InBoardValidator : RuleManager {
    override fun checkMovement(board: DefaultBoard, movement: Movement): RuleResult {
        return if (board.inBounds(movement.getTo())) {
            Valid()
        } else Invalid("Invalid Movement: Position out of board")
    }
}