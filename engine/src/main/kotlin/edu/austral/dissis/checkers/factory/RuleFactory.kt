package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.rules.EnemyInBetweenKing
import edu.austral.dissis.checkers.rules.EnemyInBetweenValidator
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.results.ConvertPiece
import edu.austral.dissis.twoDBoardGame.results.RelativePosition
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import edu.austral.dissis.twoDBoardGame.rules.andOrValidator.AndValidator
import edu.austral.dissis.twoDBoardGame.rules.andOrValidator.OrValidator
import edu.austral.dissis.twoDBoardGame.rules.directionsValidator.ToLeftValidator
import edu.austral.dissis.twoDBoardGame.rules.directionsValidator.ToRightValidator
import edu.austral.dissis.twoDBoardGame.rules.directionsValidator.VerticalBack
import edu.austral.dissis.twoDBoardGame.rules.directionsValidator.VerticalFowValidator
import edu.austral.dissis.twoDBoardGame.rules.limitsValidator.LimitValidator
import edu.austral.dissis.twoDBoardGame.rules.limitsValidator.PermanentLimit
import edu.austral.dissis.twoDBoardGame.rules.orientationValidator.DiagonalDirectionValidator
import edu.austral.dissis.twoDBoardGame.rules.uniqueRules.OppositeRow

fun singleDiagonalFoward(): RuleManager {
  return AndValidator(
    listOf(
      DiagonalDirectionValidator(),
      LimitValidator(1),
      VerticalFowValidator()
    )
  )
}

fun captureFoward(): RuleManager {
  return AndValidator(
    listOf(
      VerticalFowValidator(),
      DiagonalDirectionValidator(),
      PermanentLimit(2),
      EnemyInBetweenValidator()
    )
  )
}

fun captureBackward(): RuleManager {
  return AndValidator(
    listOf(
      VerticalBack(),
      DiagonalDirectionValidator(),
      PermanentLimit(2),
      EnemyInBetweenKing()
    )
  )
}

fun singleDiagonal(): RuleManager {
  return AndValidator(
    listOf(
      DiagonalDirectionValidator(),
      LimitValidator(1)
    )
  )
}

fun crown(color: Color): RuleManager {
  return OrValidator(
    listOf(
      normalCrown(color),
    )
  )
}

fun normalCrown(color: Color): RuleManager{
  return OrValidator(
    listOf(
      normalCrownRight(color),
      normalCrownLeft(color)
    )
  )
}

fun normalCrownRight(color: Color): RuleManager {
  return AndValidator(
    listOf(
      singleDiagonalFoward(),
      OppositeRow(),
      ToRightValidator()
    )
  ).withSpecial(listOf(
    ConvertPiece(
      RelativePosition(1,1),
      createKing(color)
    )
  ))
}

fun normalCrownLeft(color: Color): RuleManager {
  return AndValidator(
    listOf(
      singleDiagonalFoward(),
      OppositeRow(),
      ToLeftValidator()
    )
  ).withSpecial(listOf(
    ConvertPiece(
      RelativePosition(1,-1),
      createKing(color)
    )
  ))
}

fun captureAndCrown(color: Color): RuleManager {
  return OrValidator(
    listOf(
      captureCrownRight(color),
      captureCrownLeft(color)
    )
  )
}

fun captureCrownRight(color: Color): RuleManager {
  return AndValidator(
    listOf(
      captureFoward(),
      OppositeRow(),
      ToRightValidator()
    )
  ).withSpecial(listOf(
    ConvertPiece(
      RelativePosition(2,2),
      createKing(color)
    )
  ))
}

fun captureCrownLeft(color: Color): RuleManager {
  return AndValidator(
    listOf(
      captureFoward(),
      OppositeRow(),
      ToLeftValidator()
    )
  ).withSpecial(listOf(
    ConvertPiece(
      RelativePosition(2,-2),
      createKing(color)
    )
  ))
}
