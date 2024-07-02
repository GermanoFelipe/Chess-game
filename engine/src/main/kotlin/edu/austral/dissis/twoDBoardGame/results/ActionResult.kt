package edu.austral.dissis.twoDBoardGame.results

import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position

sealed interface ActionResult

class RelativePosition(
  private val row: Int = 0,
  private val column: Int = 0,
){

  fun getAbsPos(
    position: Position,
    isForward: Boolean
  ): Position{

    return Position(
      position.row + row * if (isForward) 1 else -1,
      position.column + column
    )
  }
}


class ApplyMovement(
  private val from: RelativePosition = RelativePosition(),
  private val to: RelativePosition = RelativePosition()
    ) : ActionResult {

  fun getFrom(position: Position, forwardPositive: Boolean): Position {
    return from.getAbsPos(position, forwardPositive)
  }

  fun getTo(position: Position, forwardPositive: Boolean): Position {
    return to.getAbsPos(position, forwardPositive)
  }
}

class ConvertPiece (
  private val from: RelativePosition = RelativePosition(),
  private val newPiece: Piece
        ): ActionResult{
          fun getFrom(position: Position, forwardPositive: Boolean): Position {
            return from.getAbsPos(position, forwardPositive)
          }

  fun getNewPiece(): Piece {
    return newPiece
  } }

class RemoveAction (
  private val from: RelativePosition = RelativePosition()
): ActionResult {
  fun getFrom(position: Position, forwardPositive: Boolean): Position {
    return from.getAbsPos(position, forwardPositive)
  }
}

