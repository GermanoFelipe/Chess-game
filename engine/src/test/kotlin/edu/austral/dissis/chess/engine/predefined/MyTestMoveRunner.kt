package edu.austral.dissis.chess.engine.predefined

import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.Validity
import edu.austral.dissis.chess.test.move.TestMoveRunner
import edu.austral.dissis.twoDBoardGame.results.Valid

class MyTestMoveRunner : TestMoveRunner {
  override fun executeMove(testBoard: TestBoard, from: TestPosition, to: TestPosition): Validity {
      TODO()
  }
}