package edu.austral.dissis.chess.engine.factory

import edu.austral.dissis.chess.engine.rules.uniques.KnightMovement
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import edu.austral.dissis.twoDBoardGame.rules.andOrValidator.AndValidator
import edu.austral.dissis.twoDBoardGame.rules.andOrValidator.OrValidator
import edu.austral.dissis.twoDBoardGame.rules.directionsValidator.VerticalFowValidator
import edu.austral.dissis.twoDBoardGame.rules.inPathValidator.ColumnNoPieceInPathValidator
import edu.austral.dissis.twoDBoardGame.rules.inPathValidator.DiagonalNoPieceInPathValidator
import edu.austral.dissis.twoDBoardGame.rules.inPathValidator.RowNoPieceInPathValidator
import edu.austral.dissis.twoDBoardGame.rules.limitsValidator.LimitValidator
import edu.austral.dissis.twoDBoardGame.rules.orientationValidator.ColumnDirectionValidator
import edu.austral.dissis.twoDBoardGame.rules.orientationValidator.DiagonalDirectionValidator
import edu.austral.dissis.twoDBoardGame.rules.orientationValidator.RowDirectionValidator
import edu.austral.dissis.twoDBoardGame.rules.uniqueRules.FirstMovement
import edu.austral.dissis.twoDBoardGame.rules.uniqueRules.PawnEats

fun moveInRow(): RuleManager{
  return AndValidator(
    listOf(
        RowDirectionValidator(),
        RowNoPieceInPathValidator()
    )
  )
}

fun moveInColumn(): RuleManager{
  return AndValidator(
    listOf(
        RowDirectionValidator(),
        RowNoPieceInPathValidator()
    )
  )
}

fun moveInDiagonal(): RuleManager{
  return AndValidator(
    listOf(
        DiagonalDirectionValidator(),
        DiagonalNoPieceInPathValidator()
    )
  )
}

fun moveInL(): RuleManager{
  return KnightMovement()
}

fun move1(): RuleManager{
  return AndValidator(
    listOf(
        OrValidator(
            listOf(
                RowDirectionValidator(),
                ColumnDirectionValidator(),
                DiagonalDirectionValidator()
            )
        ),
      LimitValidator(1)
    )
  )
}

// fun castling right and left

fun pawnMove(): RuleManager{
  return AndValidator(
    listOf(
      ColumnDirectionValidator(),
      LimitValidator(1),
      ColumnNoPieceInPathValidator(),
      VerticalFowValidator()
    )
  )
}

fun pawnFirstMove(): RuleManager{
  return AndValidator(
    listOf(
      FirstMovement(),
      ColumnDirectionValidator(),
      LimitValidator(2),
      ColumnNoPieceInPathValidator(),
      VerticalFowValidator()
    )
  )
}

fun pawnAttack(): RuleManager{
  return AndValidator(
    listOf(
      PawnEats(),
      DiagonalDirectionValidator(),
      LimitValidator(1),
    )
  )
}

// fun crown

// fun crownForward

// fun crowncapture right

// fun crowncapture left