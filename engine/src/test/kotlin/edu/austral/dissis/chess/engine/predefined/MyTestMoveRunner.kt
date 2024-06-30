package edu.austral.dissis.chess.engine.predefined

import edu.austral.dissis.chess.engine.chessTurn.TurnDefault
import edu.austral.dissis.chess.engine.factory.*
import edu.austral.dissis.chess.engine.rules.winCondition.IsCheckMate
import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.Validity
import edu.austral.dissis.chess.test.move.TestMoveRunner
import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.board.SizeOfBoard
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.game.mover.DefaultMovApplier
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.results.SuccessfullMovementResult
import edu.austral.dissis.twoDBoardGame.results.Valid
object MyTestMoveRunner : TestMoveRunner {

  override fun executeMove(testBoard: TestBoard, from: TestPosition, to: TestPosition): Validity {
    val newBoard = withBoard(testBoard)
    val game: Game = Game(
      newBoard,
      turn = TurnDefault(Color.WHITE),
      rules = createNormalRules(),
      winningCondition = IsCheckMate(),
      movementApplier = DefaultMovApplier()
    )
    val myFromPosition = Position(from.row, from.col)
    val myToPosition = Position(to.row, to.col)
    if (!newBoard.inBounds(myToPosition)) return Validity.INVALID
    val result = game.movePiece(myFromPosition, myToPosition)
    println(result)
    return if (result is SuccessfullMovementResult) {
      Validity.VALID
    } else Validity.INVALID
  }

  fun withBoard(board: TestBoard): DefaultBoard {
    val map = mutableMapOf<Position, Piece>()

    for ((position, piece) in board.pieces) {
      val myColor = toMyTeam(piece.playerColorSymbol)
      val myPiece = toMyPiece(piece.pieceTypeSymbol, myColor)
      map [Position(position.row, position.col)] = myPiece
    }
    val chesBoard = DefaultBoard(SizeOfBoard(board.size.rows, board.size.cols), map)
    return chesBoard
  }

  fun toMyTeam(playerColorSymbol: Char): Color {
    var team = Color.WHITE
    if(playerColorSymbol == 'B') {team = Color.BLACK}
    return team
  }
  fun toMyPiece(testType: Char, color: Color): Piece {
    return when (testType) {
      'P' -> createPawn(color)
      'B' -> createBishop(color)
      'N' -> createKnight(color)
      'R' -> createRook(color)
      'Q' -> createQueen(color)
      'K' -> createKing(color)
      else -> throw IllegalArgumentException("Invalid piece type")
    }
  }

}