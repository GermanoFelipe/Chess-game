package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.chess.test.game.GameTester
import edu.austral.dissis.chess.ui.ChessEngine
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.util.stream.Stream

class Exam {
   // @TestFactory
   // fun `required exam tests`(): Stream<DynamicTest> {
   //     return GameTester(TestGameExam()).test()
   //     //return GameTester(DummyTestGameRunner()).test()
   // }
   @TestFactory fun `specific test`(): Stream<DynamicTest> {
       return GameTester(TestGameExam()).debug("castling_with_moved_piece.md")
   }
   //return GameTester(DummyTestGameRunner()).test() }
   @TestFactory fun `specific test 2`(): Stream<DynamicTest> {
     return GameTester(TestGameExam()).debug("check_discovered_piece.md")
   }

  @TestFactory fun `specific test 3`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("check_king_moves_out_of_position.md")
  }
  @TestFactory fun `specific test 4`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("check_move_other_piece.md")
  }
  @TestFactory fun `specific test 5`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("check_piece_block.md")
  }
  @TestFactory fun `specific test 6`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("four_redos.md")
  }

  @TestFactory fun `specific test 7`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("invalid_other_turn.md")
  }

  @TestFactory fun `specific test 8`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("invalid_starting_turn.md")
  }

  @TestFactory fun `specific test 9`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("long_castling.md")
  }

  @TestFactory fun `specific test 10`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("mate_fools.md")
  }

  @TestFactory fun `specific test 11`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("mate_pawn.md")
  }

  @TestFactory fun `specific test 12`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("mate_scholars.md")
  }

  @TestFactory fun `specific test 13`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_bishop.md")
  }

  @TestFactory fun `specific test 14`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_bishop_capture.md")
  }

  @TestFactory fun `specific test 15`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_king_capture.md")
  }

  @TestFactory fun `specific test 16`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_king_diagonal.md")
  }

  @TestFactory fun `specific test 17`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_king_horizontal.md")
  }

  @TestFactory fun `specific test 18`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_king_vertical.md")
  }

  @TestFactory fun `specific test 19`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_knight.md")
  }

  @TestFactory fun `specific test 20`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_knight_capture.md")
  }

  @TestFactory fun `specific test 21`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_pawn.md")
  }

  @TestFactory fun `specific test 22`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_pawn_capture.md")
  }

  @TestFactory fun `specific test 23`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_pawn_invalid_step_back.md")
  }

  @TestFactory fun `specific test 24`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_pawn_invalid_vertical_capture.md")
  }

  @TestFactory fun `specific test 25`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_queen.md")
  }

  @TestFactory fun `specific test 26`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("movements_rook.md")
  }

  @TestFactory fun `specific test 27`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("redo_pawn_movement.md")
  }

  @TestFactory fun `specific test 28`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("short_castling.md")
  }

  @TestFactory fun `specific test 29`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("undo_pawn_movement.md")
  }

  @TestFactory fun `specific test 30`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("undo_pawn_movement_still_black_turn.md")
  }

  @TestFactory fun `specific test 31`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("undo_pawn_movement_still_white_turn.md")
  }

  @TestFactory fun `specific test 32`(): Stream<DynamicTest> {
    return GameTester(TestGameExam()).debug("valid_starting_turn.md")
  }
}

// four_redos.md
// mate_fools.md
// mate_pawn.md
// mate_scholars.md
// redo_pawn_movement.md
// undo_pawn_movement.md
// undo_pawn_movement_still_black_turn.md
// undo_pawn_movement_still_white_turn.md