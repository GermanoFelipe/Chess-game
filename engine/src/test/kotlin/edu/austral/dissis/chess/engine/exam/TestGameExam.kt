package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.chess.engine.factory.*
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.chess.engine.piece.ChessPieceType
import edu.austral.dissis.chess.engine.winCondition.CheckMate
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
import edu.austral.dissis.chess.test.game.TestGameRunner
import edu.austral.dissis.chess.test.game.TestMoveFailure
import edu.austral.dissis.chess.test.game.TestMoveResult
import edu.austral.dissis.chess.test.game.TestMoveSuccess
import edu.austral.dissis.chess.ui.ChessEngine
import edu.austral.dissis.twoDBoardGame.results.ValidMovement
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import edu.austral.dissis.twoDBoardGame.rules.andOrValidator.OrValidator
import java.util.*

class TestGameExam () : TestGameRunner {

  val undoStack = Stack<Game>()

  val redoStack = Stack<Game>()

  private var engine = ChessEngine()

  override fun executeMove(from: TestPosition, to: TestPosition): TestMoveResult {
    val newPos = positionAdapterFromThemToMine(from)
    val fromRow = newPos.row
    val fromCol = newPos.column
    val result = engine.game.movePiece(Position(fromRow, fromCol), Position(to.row, to.col))

    return if (result is ValidMovement) {
      return TestMoveSuccess(this)
    } else {
      TestMoveFailure(this.getBoard())
    }
  }

  override fun getBoard(): TestBoard {
    val rowSize = engine.game.board.getRow()
    val colSize = engine.game.board.getColumn()
    val translatedSize = TestSize(rowSize, colSize)
    var translatedPiece: Map<TestPosition, TestPiece> = mapOf()
    for (piece in engine.game.board.getPieces()){
      val newPosition = translateMyPositionToThem(piece.key)
      val color = translateMyColorToThem(piece.value.pieceColor)
      val translatePiece = translatePieceMineToThem(piece.value)
      val newPiece = TestPiece(translatePiece, color)
      translatedPiece = translatedPiece + (newPosition to newPiece)
    }
    return TestBoard(translatedSize, translatedPiece)
  }

  override fun withBoard(board: TestBoard): TestGameRunner {
    var pieces: Map<Position, Piece> = mapOf()
    for (piece in board.pieces){
      val pieceName = piece.value.pieceTypeSymbol
      val pieceTypeTranslated = translatePieceTypeThemToMine(pieceName)
      val pieceColorTranslated = translateTheirColorToMine(piece.value.playerColorSymbol)
      val id = pieceTypeTranslated.toString()
      val position = positionAdapterFromThemToMine(piece.key)
      if (pieceName == KING) {
        val pieceTranslated =
          Piece(pieceTypeTranslated,
            pieceColorTranslated,
            false,
            id,
            OrValidator(listOf(move1())
        ))
        pieces = pieces + (Position(position.row, position.column) to pieceTranslated)
      }
      if (pieceName == PAWN){
        val pieceTranslated =
          Piece(pieceTypeTranslated,
            pieceColorTranslated,
            false,
            id,
            OrValidator(listOf(pawnMove(), pawnFirstMove())
          ))
        pieces = pieces + (Position(position.row, position.column) to pieceTranslated)
      }
      if (pieceName == QUEEN){
        val pieceTranslated =
          Piece(pieceTypeTranslated,
            pieceColorTranslated,
            false,
            id,
            OrValidator(listOf(moveInRow(), moveInColumn(), moveInDiagonal())
            ))
        pieces = pieces + (Position(position.row, position.column) to pieceTranslated)
      }
      if (pieceName == KNIGHT){
        val pieceTranslated = Piece(pieceTypeTranslated,
          pieceColorTranslated,
          false,
          id,
          OrValidator(listOf(move1())
          ))
        pieces = pieces + (Position(position.row, position.column) to pieceTranslated)
      }
      if (pieceName == BISHOP){
        val pieceTranslated =
          Piece(pieceTypeTranslated,
          pieceColorTranslated,
            false,
            id,
            OrValidator(listOf(moveInDiagonal())
            ))
        pieces = pieces + (Position(position.row, position.column) to pieceTranslated)
      }
      if (pieceName == ROOK){
        val pieceTranslated = Piece(pieceTypeTranslated,
          pieceColorTranslated,
          false,
          id,
          OrValidator(listOf(moveInRow(), moveInColumn())
          ))
        pieces = pieces + (Position(position.row, position.column) to pieceTranslated)

      }
    }
    val newBoard = DefaultBoard(board.size.rows, board.size.cols, pieces)
    val rules = mutableListOf<RuleManager>()
    val winCondition = CheckMate()
    val newGame = Game(newBoard, engine.game.turn, rules, emptyMap(), winCondition, engine.game.movementApplier)
    engine = ChessEngine()
    return this
  }


  fun updateBoard(board: DefaultBoard): DefaultBoard {
    val newBoard = DefaultBoard(8, 8, board.getPieces())
    return newBoard
  }
  //pieces = pieces + Pair(  Pair (piece.key.col, piece.key.row),
  //Piece(reverseTranslatePosition(piece.key).toString(),
  //PieceType.king, reverseTranslatePieceColor(piece.value.playerColorSymbol),
  // listOf( HorizontalMove(1), VerticalMove(1), DiagonalMove(1) ) ) )
  fun translatePieceTypeThemToMine(char: Char): ChessPieceType{
    return when(char){
      'P' -> ChessPieceType.PAWN
      'R' -> ChessPieceType.ROOK
      'N' -> ChessPieceType.KNIGHT
      'B' -> ChessPieceType.BISHOP
      'Q' -> ChessPieceType.QUEEN
      'K' -> ChessPieceType.KING
      else -> {
        throw IllegalArgumentException("Invalid piece type")}
    }
  }

  fun translatePieceMineToThem(piece: Piece): Char{
    return when(piece.type){
      ChessPieceType.PAWN -> 'P'
      ChessPieceType.ROOK -> 'R'
      ChessPieceType.KNIGHT -> 'N'
      ChessPieceType.BISHOP -> 'B'
      ChessPieceType.QUEEN -> 'Q'
      ChessPieceType.KING -> 'K'
      else -> {
        throw IllegalArgumentException("Invalid piece type")}
    }
  }

  fun translateTheirColorToMine(color: Char): Color {
    return when(color){
      'W' -> Color.WHITE
      'B' -> Color.BLACK
      else -> {
        throw IllegalArgumentException("Invalid color")}
    }
  }

  fun translateMyColorToThem(color: Color): Char{
    return when(color){
      Color.WHITE -> 'W'
      Color.BLACK -> 'B'
    }
  }
  fun translateMyPositionToThem(position: Position): TestPosition {
    val row = position.row
    val column = position.column
    return TestPosition(row, column)
  }

  fun positionAdapterFromThemToMine(position: TestPosition): edu.austral.dissis.chess.gui.Position {
    val row = position.row
    val column = position.col
    return edu.austral.dissis.chess.gui.Position(row, column)
  }
}