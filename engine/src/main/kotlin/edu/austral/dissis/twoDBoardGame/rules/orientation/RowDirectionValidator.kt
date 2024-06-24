package edu.austral.dissis.twoDBoardGame.rules.orientation

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class RowDirectionValidator : RuleManager {

    override fun checkMovement(game: Game, movement: Movement): RuleResult {
        return if (movement.getFrom().row == movement.getTo().row
            && movement.getFrom().column != movement.getTo().column) Valid()
        else Invalid()
    }
}