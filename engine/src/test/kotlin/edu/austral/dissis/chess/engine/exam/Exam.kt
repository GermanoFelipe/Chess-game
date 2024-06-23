package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.game.Game
import edu.austral.dissis.chess.engine.game.TurnDefault
import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.rules.ChessRuleManager
import edu.austral.dissis.chess.test.TestGame
import edu.austral.dissis.chess.test.game.GameTester
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class Exam {

    val board = DefaultBoard(8,8, emptyMap())

    val turn = TurnDefault(edu.austral.dissis.chess.engine.piece.Color.WHITE)
    val rules = ChessRuleManager()

    val game = Game(board, turn, mapOf<Piece, List<Movement>>(), rules)
    @TestFactory
    fun `required exam tests`(): Stream<DynamicTest> {
        return GameTester(TestGameExam(game)).test()
        //return GameTester(DummyTestGameRunner()).test()
    }

}
