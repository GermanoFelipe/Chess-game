package edu.austral.dissis.twoDBoardGame.rules.boardRulesValidator

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class InBoardValidator : RuleManager {
    override fun checkMovement(game: Game, movement: Movement): RuleResult {
        val fromRow = movement.getFrom().row
        val fromColumn = movement.getFrom().column
        val toRow = movement.getTo().row
        val toColumn = movement.getTo().column
        val row = game.getBoard().getRow()
        val column = game.getBoard().getColumn()

        return if (fromRow in 1..row && fromColumn in 1..column
            && toRow in 1..row && toColumn in 1..column
        ) Valid()
        else Invalid("Movement out of board")
    }
}