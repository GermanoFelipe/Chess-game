package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.chess.engine.factory.*
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.chess.engine.piece.ChessPieceType
import edu.austral.dissis.chess.engine.rules.winCondition.IsCheckMate
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.chess.test.TestBoard
import edu.austral.dissis.chess.test.TestPiece
import edu.austral.dissis.chess.test.TestPieceSymbols.BISHOP
import edu.austral.dissis.chess.test.TestPieceSymbols.KING
import edu.austral.dissis.chess.test.TestPieceSymbols.KNIGHT
import edu.austral.dissis.chess.test.TestPieceSymbols.PAWN
import edu.austral.dissis.chess.test.TestPieceSymbols.QUEEN
import edu.austral.dissis.chess.test.TestPieceSymbols.ROOK
import edu.austral.dissis.chess.test.TestPosition
import edu.austral.dissis.chess.test.TestSize
import edu.austral.dissis.chess.test.game.*
import edu.austral.dissis.chess.ui.ChessEngine
import edu.austral.dissis.twoDBoardGame.board.SizeOfBoard
import edu.austral.dissis.twoDBoardGame.results.SuccessfullMovementResult
import edu.austral.dissis.twoDBoardGame.results.WinnerResult
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import edu.austral.dissis.twoDBoardGame.rules.andOrValidator.OrValidator
import java.util.*

class TestGameExam () : TestGameRunner {

  val undoStack =  Stack<Game>()

  val redoStack = Stack<Game>()

  private var game: Game = DefualtChessGame()

  fun updateGame(game: Game){
    this.game = game
  }

 // override fun undo(): TestMoveResult {
 //   return if (canUndo()){
 //     undoMove()
 //     updateGame()
 //   }
 //   else TestMoveFailure(getBoard())
 // }

  fun undoMove(){
    if (canUndo()){
      undoStack.push(game)
      val lastState = redoStack.pop()
      updateGame(lastState)
    }
  }

  fun canUndo(): Boolean {
    return !undoStack.isEmpty()
  }


  //override fun redo(): TestMoveResult {
  //  if (redoStack.size > 0) {
  //    undoStack.push(redoStack.pop())
  //    game = undoStack.peek()
  //    return TestMoveSuccess(this)
  //  }
  //  return TestMoveFailure(getBoard())
  //}

  fun redoMove(){
    if (canRedo()){
      redoStack.push(game)
      val lastState = undoStack.pop()
      updateGame(lastState)
    }
  }

  fun canRedo(): Boolean {
    return !redoStack.isEmpty()
  }

  override fun executeMove(from: TestPosition, to: TestPosition): TestMoveResult {
    val myFrom = Position(from.row, from.col)
    val myTo = Position(to.row, to.col)
    val result = game.movePiece(myFrom, myTo)
    game = result.getGameResult()
    if (result is SuccessfullMovementResult) {
      return TestMoveSuccess(this)
    } else if (result is WinnerResult) {
      return if (result.winner == Color.WHITE) {
        WhiteCheckMate(this.getBoard())
      } else {
        BlackCheckMate(this.getBoard())
      }
    }
      return TestMoveFailure(getBoard())
    }

  override fun getBoard(): TestBoard {
    val size = TestSize(8, 8)

    val pieces = mutableMapOf<TestPosition, TestPiece>()

    for ((position, piece) in game.getBoard().getPieces()) {
      pieces[TestPosition(position.row, position.column)] = TestPiece(
        toTestPieceType(piece), toTestTeam(piece)
      )
    }
    return TestBoard(size, pieces)
  }

  override fun withBoard(board: TestBoard): TestGameRunner {
    val map = mutableMapOf<Position, Piece>()

    for ((position, piece) in board.pieces) {
      val myColor = toMyTeam(piece.playerColorSymbol)
      val myPiece = toMyPiece(piece.pieceTypeSymbol, myColor)
      map [Position(position.row, position.col)] = myPiece
  }
    val chesBoard = DefaultBoard(SizeOfBoard(board.size.rows, board.size.cols), map)
    game = Game(chesBoard,
      game.getTurnMan(),
      game.getRules(),
      game.getWinningCondition(),
      game.getMovementApplier())
    return this
}



  fun toTestPieceType(piece: Piece): Char{
    var type =  ' '
    if(piece.type == ChessPieceType.PAWN ) {type = 'P'}
    if(piece.type == ChessPieceType.KNIGHT) {type = 'N'}
    if(piece.type == ChessPieceType.BISHOP) {type = 'B'}
    if(piece.type == ChessPieceType.ROOK) {type = 'R'}
    if(piece.type == ChessPieceType.QUEEN) {type = 'Q'}
    if(piece.type == ChessPieceType.KING) {type = 'K'}
    return type
}

fun toTestTeam(piece: Piece): Char {
  var team = ' '
  if(piece.pieceColor.toString() == "WHITE") {team = 'W'}
  if(piece.pieceColor.toString() == "BLACK") {team = 'B'}
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

fun toMyTeam(playerColorSymbol: Char): Color {
  var team = Color.WHITE
  if(playerColorSymbol == 'B') {team = Color.BLACK}
  return team
}

}