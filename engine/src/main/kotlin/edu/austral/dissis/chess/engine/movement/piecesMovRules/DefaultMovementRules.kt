package edu.austral.dissis.chess.engine.movement.piecesMovRules

import edu.austral.dissis.chess.engine.movement.validator.GeneralPieceRules.PieceRuleValidator
import edu.austral.dissis.twoDBoardGame.rules.andOrValidator.AndValidator
import edu.austral.dissis.twoDBoardGame.rules.andOrValidator.OrValidator
import edu.austral.dissis.twoDBoardGame.rules.orientationValidator.ColumnDirectionValidator
import edu.austral.dissis.twoDBoardGame.rules.orientationValidator.DiagonalDirectionValidator
import edu.austral.dissis.twoDBoardGame.rules.orientationValidator.RowDirectionValidator
import edu.austral.dissis.twoDBoardGame.rules.inPathValidator.ColumnNoPieceInPathValidator
import edu.austral.dissis.twoDBoardGame.rules.inPathValidator.DiagonalNoPieceInPathValidator
import edu.austral.dissis.twoDBoardGame.rules.inPathValidator.RowNoPieceInPathValidator
import edu.austral.dissis.twoDBoardGame.rules.boardRulesValidator.InBoardValidator
import edu.austral.dissis.twoDBoardGame.rules.limitsValidator.LimitValidator

//class DefaultMovementRules {
//  fun createRookRules(): PieceRuleValidator {
//    val limit = 7
//    val columnValidator = AndValidator(ColumnNoPieceInPathValidator(), ColumnDirectionValidator())
//    val rowValidator = AndValidator(RowNoPieceInPathValidator(), RowDirectionValidator())
//
//    val movementValidator = OrValidator(columnValidator, rowValidator)
//    val movementInBoard = AndValidator(movementValidator, InBoardValidator())
//    val movement = AndValidator(movementInBoard, LimitValidator(limit))
//    return movement
//  }
//
//  fun createKingRules(): PieceRuleValidator {
//    val limit = 1
//    val columnValidator = AndValidator(ColumnNoPieceInPathValidator(), ColumnDirectionValidator())
//    val rowValidator = AndValidator(RowNoPieceInPathValidator(), RowDirectionValidator())
//    val diagonalValidator = AndValidator(DiagonalNoPieceInPathValidator(), DiagonalDirectionValidator())
//
//    val movementValidatorOne = OrValidator(columnValidator, rowValidator)
//    val movementValidatorTwo = OrValidator(movementValidatorOne, diagonalValidator)
//
//    val movementInBoard = AndValidator(movementValidatorTwo, InBoardValidator())
//    val movement = AndValidator(movementInBoard, LimitValidator(limit))
//    return movement
//  }
//
//  fun createQueenRules(): PieceRuleValidator {
//    val limit = 7
//    val columnValidator = AndValidator(ColumnNoPieceInPathValidator(), ColumnDirectionValidator())
//    val rowValidator = AndValidator(RowNoPieceInPathValidator(), RowDirectionValidator())
//    val diagonalValidator = AndValidator(DiagonalNoPieceInPathValidator(), DiagonalDirectionValidator())
//
//    val movementValidatorOne = OrValidator(columnValidator, rowValidator)
//    val movementValidatorTwo = OrValidator(movementValidatorOne, diagonalValidator)
//
//    val movementInBoard = AndValidator(movementValidatorTwo, InBoardValidator())
//    val movement = AndValidator(movementInBoard, LimitValidator(limit))
//    return movement
//  }
//  fun createBishopRules(): PieceRuleValidator {
//    val limit = 7
//    val diagonalValidator = AndValidator(DiagonalNoPieceInPathValidator(), DiagonalDirectionValidator())
//
//    val movementInBoard = AndValidator(diagonalValidator, InBoardValidator())
//    val movement = AndValidator(movementInBoard, LimitValidator(limit))
//    return movement
//  }
//
//  fun createPawnRules(): PieceRuleValidator {
//    val limit = 1
//    val columnValidator = AndValidator(ColumnNoPieceInPathValidator(), ColumnDirectionValidator())
//
//    val movementInBoard = AndValidator(columnValidator, InBoardValidator())
//    val movement = AndValidator(movementInBoard, LimitValidator(limit))
//    return movement
//  }
//
//  fun createKnightRules(): PieceRuleValidator {
//    val limit = 1
//    val columnValidator = ColumnDirectionValidator()
//    val rowValidator = RowDirectionValidator()
//
//    val movementValidator = OrValidator(columnValidator, rowValidator)
//    val movementInBoard = AndValidator(movementValidator, InBoardValidator())
//    val movement = AndValidator(movementInBoard, LimitValidator(limit))
//    return movement
//  }
//}