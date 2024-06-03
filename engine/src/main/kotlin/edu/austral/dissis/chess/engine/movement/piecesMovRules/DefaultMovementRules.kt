package edu.austral.dissis.chess.engine.movement.piecesMovRules

import edu.austral.dissis.chess.engine.movement.validator.*
import edu.austral.dissis.chess.engine.movement.validator.andOrValidator.AndValidator
import edu.austral.dissis.chess.engine.movement.validator.andOrValidator.OrValidator
import edu.austral.dissis.chess.engine.movement.validator.directions.ColumnDirectionValidator
import edu.austral.dissis.chess.engine.movement.validator.directions.DiagonalDirectionValidator
import edu.austral.dissis.chess.engine.movement.validator.directions.RowDirectionValidator
import edu.austral.dissis.chess.engine.movement.validator.inPath.ColumnNoPieceInPathValidator
import edu.austral.dissis.chess.engine.movement.validator.inPath.DiagonalNoPieceInPathValidator
import edu.austral.dissis.chess.engine.movement.validator.inPath.RowNoPieceInPathValidator
import edu.austral.dissis.chess.engine.movement.validator.limits.InBoardValidator
import edu.austral.dissis.chess.engine.movement.validator.limits.LimitValidator

class DefaultMovementRules {
  fun createRookRules(): MovementValidator {
    val limit = 7
    val columnValidator = AndValidator(ColumnNoPieceInPathValidator(), ColumnDirectionValidator())
    val rowValidator = AndValidator(RowNoPieceInPathValidator(), RowDirectionValidator())

    val movementValidator = OrValidator(columnValidator, rowValidator)
    val movementInBoard = AndValidator(movementValidator, InBoardValidator())
    val movement = AndValidator(movementInBoard, LimitValidator(limit))
    return movement
  }

  fun createKingRules(): MovementValidator {
    val limit = 1
    val columnValidator = AndValidator(ColumnNoPieceInPathValidator(), ColumnDirectionValidator())
    val rowValidator = AndValidator(RowNoPieceInPathValidator(), RowDirectionValidator())
    val diagonalValidator = AndValidator(DiagonalNoPieceInPathValidator(), DiagonalDirectionValidator())

    val movementValidatorOne = OrValidator(columnValidator, rowValidator)
    val movementValidatorTwo = OrValidator(movementValidatorOne, diagonalValidator)

    val movementInBoard = AndValidator(movementValidatorTwo, InBoardValidator())
    val movement = AndValidator(movementInBoard, LimitValidator(limit))
    return movement
  }

  fun createQueenRules(): MovementValidator {
    val limit = 7
    val columnValidator = AndValidator(ColumnNoPieceInPathValidator(), ColumnDirectionValidator())
    val rowValidator = AndValidator(RowNoPieceInPathValidator(), RowDirectionValidator())
    val diagonalValidator = AndValidator(DiagonalNoPieceInPathValidator(), DiagonalDirectionValidator())

    val movementValidatorOne = OrValidator(columnValidator, rowValidator)
    val movementValidatorTwo = OrValidator(movementValidatorOne, diagonalValidator)

    val movementInBoard = AndValidator(movementValidatorTwo, InBoardValidator())
    val movement = AndValidator(movementInBoard, LimitValidator(limit))
    return movement
  }
  fun createBishopRules(): MovementValidator {
    val limit = 7
    val diagonalValidator = AndValidator(DiagonalNoPieceInPathValidator(), DiagonalDirectionValidator())

    val movementInBoard = AndValidator(diagonalValidator, InBoardValidator())
    val movement = AndValidator(movementInBoard, LimitValidator(limit))
    return movement
  }

  fun createPawnRules(): MovementValidator {
    return TODO("Provide the return value")
  }

  fun createKnightRules(): MovementValidator {
    return TODO("Provide the return value")
  }
}