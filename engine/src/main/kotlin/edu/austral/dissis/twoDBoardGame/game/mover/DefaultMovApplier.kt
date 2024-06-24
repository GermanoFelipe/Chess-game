package edu.austral.dissis.twoDBoardGame.game.mover

import edu.austral.dissis.chess.engine.factory.DefualtChessGame
import edu.austral.dissis.chess.gui.Move
import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.ActionResult
import edu.austral.dissis.twoDBoardGame.results.ApplyMovement
import edu.austral.dissis.twoDBoardGame.results.ConvertPiece
import edu.austral.dissis.twoDBoardGame.results.RemoveAction
import java.util.Collections

class DefaultMovApplier: MovementApplier {

  override fun applyMovement(movement: Movement, board: DefaultBoard): DefaultBoard {
    return doMove(movement.getFrom(), movement.getTo(), board)
  }

  override fun executeSpecial(move: Movement, board: DefaultBoard, actions: List<ActionResult>): DefaultBoard {
    val from: Position = move.getFrom()
    var newBoard: DefaultBoard = board
    val fowardPositiveYes = fowardPositive(move)

    for (action in actions){
      newBoard = when(action){
        is ApplyMovement ->
          doMove( action.getFrom(from, fowardPositiveYes),
                  action.getTo(from, fowardPositiveYes),
                  newBoard)
        is RemoveAction ->
          removePiece(action.getFrom(from, fowardPositiveYes),
                      newBoard)
        is ConvertPiece -> convertPiece(
          action.getFrom(from, fowardPositiveYes),
          newBoard,
          action.getNewPiece())
      }
    }
    return newBoard
  }

  private fun doMove(from: Position, to: Position, newBoard: DefaultBoard): DefaultBoard {
    return newBoard.movePiece(from, to)
  }

  private fun removePiece(posRemove: Position, newBoard: DefaultBoard): DefaultBoard {
    return newBoard.removePiece(posRemove)
  }

  private fun convertPiece(position: Position, newBoard: DefaultBoard, newPiece: Piece): DefaultBoard{
    val oldPiece : Piece = newBoard.getPiece(position) ?: throw NoSuchElementException("No piece in position")
    return newBoard.addPiece(position, oldPiece.copy(
      type = newPiece.type,
      pieceColor = newPiece.pieceColor,
      hasMoved = newPiece.hasMoved,
      id = oldPiece.getId(),
      pieceRuleManager = newPiece.pieceRuleManager
    ))
  }

  fun fowardPositive(move: Movement): Boolean {
    return move.getTurn() == Color.WHITE
  }
}