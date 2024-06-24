package edu.austral.dissis.twoDBoardGame.rules.boardRulesValidator

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.RuleResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

class InBoardValidator : RuleManager {
    override fun checkMovement(game: Game, movement: Movement): RuleResult {
        return if (movement.getFrom().row in 1..8 && movement.getFrom().column in 1..8 &&
            movement.getTo().row in 1..8 && movement.getTo().column in 1..8
        ) Valid()
        else Invalid()
    }
}