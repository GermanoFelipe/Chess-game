package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.checkersTurn.TurnCheckers
import edu.austral.dissis.checkers.winCondition.EatAll
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.mover.DefaultMovApplier
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import edu.austral.dissis.twoDBoardGame.rules.boardRulesValidator.NotUsedPos
import edu.austral.dissis.twoDBoardGame.rules.boardRulesValidator.PieceExistsValidator
import edu.austral.dissis.twoDBoardGame.rules.boardRulesValidator.TurnValidator

object DefaultCheckersGame {
 operator fun invoke(): Game {
   return Game(
     board = createCheckersBoard(),
     turn = TurnCheckers(Color.WHITE),
     rules = createNormalCheckersRules(),
     winningCondition = EatAll(),
     movementApplier = DefaultMovApplier()
   )
 }
}

fun createDefaultCheckers(): Game {
  return Game(
    board = createCheckersBoard(),
    turn = TurnCheckers(Color.WHITE),
    rules = createNormalCheckersRules(),
    winningCondition = EatAll(),
    movementApplier = DefaultMovApplier()
  )
}

fun createNormalCheckersRules(): List<RuleManager> {
  return listOf(
    PieceExistsValidator(),
    TurnValidator(),
    NotUsedPos()
  )
}

fun createEatAllCheckers(): Game {
  return Game(
    board = createEatAllBoard(),
    turn = TurnCheckers(Color.WHITE),
    rules = createNormalCheckersRules(),
    winningCondition = EatAll(),
    movementApplier = DefaultMovApplier()
  )
}

fun createKingTests(): Game{
  return Game(
    board = createKingTestBoard(),
    turn = TurnCheckers(Color.WHITE),
    rules = createNormalCheckersRules(),
    winningCondition = EatAll(),
    movementApplier = DefaultMovApplier()
  )
}