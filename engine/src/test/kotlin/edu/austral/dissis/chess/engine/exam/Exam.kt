package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.chess.engine.game.TurnDefault
import edu.austral.dissis.twoDBoardGame.game.Movement
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.chess.engine.rules.ChessRuleManager
import edu.austral.dissis.chess.test.game.GameTester
import edu.austral.dissis.chess.ui.ChessEngine
import edu.austral.dissis.twoDBoardGame.piece.Color
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class Exam {

    val board = DefaultBoard(8,8, emptyMap())
    val turn = TurnDefault(Color.WHITE)
    val rules = ChessRuleManager()

    val game = Game(board, turn, mapOf<Piece, List<Movement>>(), rules)
    @TestFactory
    fun `required exam tests`(): Stream<DynamicTest> {
        return GameTester(TestGameExam(ChessEngine(game))).test()
        //return GameTester(DummyTestGameRunner()).test()
    }

    @TestFactory
    fun `specific test`(): Stream<DynamicTest> {
        return GameTester(TestGameExam(ChessEngine(game))).debug("invalid_starting_turn.md")
        //return GameTester(DummyTestGameRunner()).test()
    }
}
