package edu.austral.dissis.twoDBoardGame.results

import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position

sealed interface ActionResult

class RelativePosition(
  private val row: Int = 0,
  private val column: Int = 0,
){
  fun getRow(): Int {
    return row
  }

  fun getColumn(): Int {
    return column
  }

  fun getAbsPos(
    position: Position,
    isFoward: Boolean
  ): Position{

    return Position(
      position.row + row * if (isFoward) 1 else -1,
      position.column + column
    )
  }
}


class ApplyMovement(
  private val from: RelativePosition = RelativePosition(),
  private val to: RelativePosition = RelativePosition()
    ) : ActionResult {

  fun getFrom(position: Position, fowardPositive: Boolean): Position {
    return from.getAbsPos(position, fowardPositive)
  }

  fun getTo(position: Position, fowardPositive: Boolean): Position {
    return to.getAbsPos(position, fowardPositive)
  }
}

class ConvertPiece (
  private val from: RelativePosition = RelativePosition(),
  private val newPiece: Piece
        ): ActionResult{
          fun getFrom(position: Position, fowardPositive: Boolean): Position {
            return from.getAbsPos(position, fowardPositive)
          }

  fun getNewPiece(): Piece {
    return newPiece
  } }

class RemoveAction (
  private val from: RelativePosition = RelativePosition()
): ActionResult {
  fun getFrom(position: Position, fowardPositive: Boolean): Position {
    return from.getAbsPos(position, fowardPositive)
  }
}

