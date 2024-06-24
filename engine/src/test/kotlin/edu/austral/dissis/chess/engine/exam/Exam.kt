package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.chess.test.game.GameTester
import edu.austral.dissis.chess.ui.ChessEngine
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class Exam {
    @TestFactory
    fun `required exam tests`(): Stream<DynamicTest> {
        return GameTester(TestGameExam(ChessEngine())).test()
        //return GameTester(DummyTestGameRunner()).test()
    }

  //  @TestFactory
  //  fun `specific test`(): Stream<DynamicTest> {
  //      return GameTester(TestGameExam(ChessEngine())).debug("invalid_starting_turn.md")
  //      //return GameTester(DummyTestGameRunner()).test()
  //  }
}
