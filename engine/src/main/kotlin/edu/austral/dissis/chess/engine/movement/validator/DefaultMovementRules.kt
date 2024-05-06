package edu.austral.dissis.chess.engine.movement.validator

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