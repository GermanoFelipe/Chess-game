package edu.austral.dissis.chess.engine.movement.validator

class DefaultMovementRules {
  fun createRookRules(): MovementValidator {
    val columnValidator = AndValidator(ColumnNoPieceInPathValidator(), ColumnDirectionValidator())
    val rowValidator = AndValidator(RowNoPieceInPathValidator(), RowDirectionValidator())
    val movementValidator = OrValidator(columnValidator, rowValidator)
    val movement = AndValidator(movementValidator, InBoardValidator())
    return movement
  }
}