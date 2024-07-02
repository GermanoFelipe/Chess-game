package edu.austral.dissis.chess.engine.factory

import edu.austral.dissis.chess.engine.rules.KnightMovement
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.results.ApplyMovement
import edu.austral.dissis.twoDBoardGame.results.ConvertPiece
import edu.austral.dissis.twoDBoardGame.results.RelativePosition
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import edu.austral.dissis.twoDBoardGame.rules.andOrValidator.AndValidator
import edu.austral.dissis.twoDBoardGame.rules.andOrValidator.OrValidator
import edu.austral.dissis.twoDBoardGame.rules.directionsValidator.ToLeftValidator
import edu.austral.dissis.twoDBoardGame.rules.directionsValidator.ToRightValidator
import edu.austral.dissis.twoDBoardGame.rules.directionsValidator.VerticalFowValidator
import edu.austral.dissis.twoDBoardGame.rules.inPathValidator.ColumnNoPieceInPathValidator
import edu.austral.dissis.twoDBoardGame.rules.inPathValidator.DiagonalNoPieceInPathValidator
import edu.austral.dissis.twoDBoardGame.rules.inPathValidator.RowNoPieceInPathValidator
import edu.austral.dissis.twoDBoardGame.rules.limitsValidator.LimitValidator
import edu.austral.dissis.twoDBoardGame.rules.limitsValidator.PermanentLimit
import edu.austral.dissis.twoDBoardGame.rules.orientationValidator.ColumnDirectionValidator
import edu.austral.dissis.twoDBoardGame.rules.orientationValidator.DiagonalDirectionValidator
import edu.austral.dissis.twoDBoardGame.rules.orientationValidator.RowDirectionValidator
import edu.austral.dissis.twoDBoardGame.rules.uniqueRules.FirstMovement
import edu.austral.dissis.twoDBoardGame.rules.uniqueRules.OppositeRow
import edu.austral.dissis.twoDBoardGame.rules.uniqueRules.Enemy

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
        ColumnDirectionValidator(),
        ColumnNoPieceInPathValidator()
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

fun castlingRight(): RuleManager{
  return AndValidator(
    listOf(
      FirstMovement(),
      RowDirectionValidator(),
      RowNoPieceInPathValidator(),
      PermanentLimit(2),
      ToRightValidator()
    )
  ).withSpecial(listOf(
    ApplyMovement(RelativePosition(0,3), RelativePosition(0,1))
  ))
}

fun castlingLeft(): RuleManager{
  return AndValidator(
    listOf(
      FirstMovement(),
      RowDirectionValidator(),
      RowNoPieceInPathValidator(),
      PermanentLimit(2),
      ToLeftValidator()
    )
  ).withSpecial(listOf(
    ApplyMovement(RelativePosition(0,-4), RelativePosition(0,-1))
  ))
}

fun pawnMove(): RuleManager{
  return AndValidator(
    listOf(
      ColumnDirectionValidator(),
      LimitValidator(1),
      ColumnNoPieceInPathValidator(true),
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
      ColumnNoPieceInPathValidator(true),
      VerticalFowValidator()
    )
  )
}

fun pawnAttack(): RuleManager{
  return AndValidator(
    listOf(
      Enemy(),
      DiagonalDirectionValidator(),
      LimitValidator(1),
    )
  )
}

fun crown(color: Color): RuleManager{
  return OrValidator(
    listOf(
      crownForward(color),
      crownRight(color),
      crownLeft(color)
    )
  )
}

fun crownForward(color: Color): RuleManager{
  return AndValidator(
    listOf(
      ColumnDirectionValidator(),
      LimitValidator(1),
      ColumnNoPieceInPathValidator(true),
      VerticalFowValidator(),
      OppositeRow()
    )).withSpecial(listOf(
      ConvertPiece(RelativePosition(1,0), createQueen(color))
  )
  )
}

fun crownRight(color: Color): RuleManager{
  return AndValidator(
    listOf(
      pawnAttack(),
      ToRightValidator(),
      OppositeRow()
    )
  ).withSpecial(listOf(
    ConvertPiece(RelativePosition(1,1), createQueen(color))
  ))
}

fun crownLeft(color: Color): RuleManager{
  return AndValidator(
    listOf(
      pawnAttack(),
      ToLeftValidator(),
      OppositeRow()
    )
  ).withSpecial(listOf(
    ConvertPiece(RelativePosition(1,-1), createQueen(color))
  ))
}