package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.factory.createDefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.chess.engine.game.TurnDefault
import edu.austral.dissis.chess.engine.winCondition.CheckMate
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.chess.test.game.GameTester
import edu.austral.dissis.chess.ui.ChessEngine
import edu.austral.dissis.twoDBoardGame.game.mover.DefaultMovApplier
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
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
