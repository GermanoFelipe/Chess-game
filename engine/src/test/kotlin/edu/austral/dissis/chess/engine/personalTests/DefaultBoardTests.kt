package edu.austral.dissis.chess.engine.personalTests

import edu.austral.dissis.chess.engine.factory.createDefaultBoard
import edu.austral.dissis.twoDBoardGame.position.Position
import org.junit.jupiter.api.Test

class DefaultBoardTests {

  val board = createDefaultBoard()

  @Test
  fun positionExistTest(): Unit{
    assert(board.positionExists(Position(1,1)))
    assert(board.positionExists(Position(8,8)))
    assert(!board.positionExists(Position(0,0)))
    assert(!board.positionExists(Position(9,9)))
  }

  @Test
  fun getPieceTest(): Unit{
    assert(board.getPiece(Position(1,1) ) != null)
    assert(board.getPiece(Position(8,8) ) != null)
    assert(board.getPiece(Position(5,5) ) == null)
  }
}