package edu.austral.dissis.chess.engine.predefined

import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.Validity.INVALID
import edu.austral.dissis.chess.test.Validity.VALID
import edu.austral.dissis.chess.test.move.MoveTester
import edu.austral.dissis.chess.test.parser.ParseSettings
import org.junit.jupiter.api.Test

class MyMoveTests {
    private val moveTester: MoveTester = MoveTester(MyTestMoveRunner)

    @Test
    fun `test basic knight moves`() {
        moveTester.testMove(
            "/moves/knight/valid_moves.txt",
            TestPosition(5, 4),
            ParseSettings(INVALID)
        )
    }

    @Test
    fun `test basic bishop moves`() {
        moveTester.testMove(
            "/moves/bishop/valid_bishop.txt",
            TestPosition(4, 4),
            ParseSettings(INVALID)
        )
    }

    @Test
    fun `test basic king moves`() {
        moveTester.testMove(
            "/moves/king/valid_king.txt",
            TestPosition(8, 5),
            ParseSettings(INVALID)
        )
    }

    @Test
    fun `test basic queen moves`() {
        moveTester.testMove(
            "/moves/queen/valid_queen.txt",
            TestPosition(4, 4),
            ParseSettings(INVALID)
        )
    }

    @Test
    fun `test basic rook moves`() {
        moveTester.testMove(
            "/moves/rook/valid_rook.txt",
            TestPosition(4, 4),
            ParseSettings(INVALID)
        )
    }

    @Test
    fun `test basic pawn moves`() {
        moveTester.testMove(
            "/moves/pawn/valid_pawn.txt",
            TestPosition(2, 5),
            ParseSettings(INVALID)
        )
    }
}
