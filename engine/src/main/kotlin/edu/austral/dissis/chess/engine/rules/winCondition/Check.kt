package edu.austral.dissis.chess.engine.rules.winCondition

import edu.austral.dissis.chess.engine.piece.ChessPieceType
import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.Invalid
import edu.austral.dissis.twoDBoardGame.results.Valid
import java.util.NoSuchElementException

class Check {

  fun inCheck(board: DefaultBoard, kingTeam: Color): Boolean{
    val kingPosition = kingPosition(board, kingTeam) ?: throw NoSuchElementException("No king in board")

    val opponentPieces = board.getUsedPositions()

    for(position in opponentPieces){
      if (pieceAttacksKing(board, position, kingTeam, kingPosition)){
        return true
      }
    }
    return false
  }

  fun kingPosition(board: DefaultBoard, kingTeam: Color): Position? {
    board.getUsedPositions().forEach {position ->
      if (board.getPiece(position)?.type == ChessPieceType.KING &&
          board.getPiece(position)?.pieceColor == kingTeam){
          return position
      }
    }
    return null
  }

  fun pieceAttacksKing(board: DefaultBoard,
                       position: Position,
                       kingColor: Color,
                       kingPosition: Position): Boolean{

    if (board.getPiece(position)?.pieceColor != kingColor){
      val piece = board.getPiece(position) ?: throw NoSuchElementException("No piece in position")
      val movement = Movement(position, kingPosition, kingColor)

      when (
        piece.validateMovement(movement, board)
      ) {
        is Valid -> return true
        is Invalid -> {}
      }
    }
    return false
  }
}