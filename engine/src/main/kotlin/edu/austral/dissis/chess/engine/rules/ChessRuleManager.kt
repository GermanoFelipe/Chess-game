package edu.austral.dissis.chess.engine.rules

import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.MovementResult
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.rules.RuleManager

//class ChessRuleManager: RuleManager {
//  override fun checkMovement(game: Game, movement: Movement): MovementResult {
//    val pieceToCheck = movement.getPiece()
//    val colorToCheck = pieceToCheck.pieceColor
//    val ruleResult = pieceToCheck.pieceRuleManager
//    val from = movement.getFrom()
//    val to = movement.getTo()
//    val board = movement.getBoard()
//
//    val state = ruleResult.checkMovement(from, to, board)
//    return if (state){
//      Valid(game)
//    } else Invalid()
//  }
//}