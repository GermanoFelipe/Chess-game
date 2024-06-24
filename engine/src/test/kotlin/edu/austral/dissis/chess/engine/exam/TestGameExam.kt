package edu.austral.dissis.chess.engine.exam

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.results.Valid
import edu.austral.dissis.chess.engine.movement.piecesMovRules.DefaultMovementRules
import edu.austral.dissis.chess.engine.piece.ChessPieceType
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
import java.util.*

class TestGameExam (private var engine: ChessEngine) : TestGameRunner {

  val undoStack = Stack<Game>()

  val redoStack = Stack<Game>()

  override fun executeMove(from: TestPosition, to: TestPosition): TestMoveResult {
    val newPos = positionAdapterFromThemToMine(from)
    val fromRow = newPos.row
    val fromCol = newPos.column
    val result = engine.game.movePiece(Position(fromRow, fromCol), Position(to.row, to.col))

    return if (result is Valid) {
      return TestMoveSuccess(this)
    } else {
      TestMoveFailure(this.getBoard())
    }
  }

  override fun getBoard(): TestBoard {
    val rowSize = engine.game.board.row
    val colSize = engine.game.board.column
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
        val pieceTranslated = Piece(pieceTypeTranslated, pieceColorTranslated, false, id, DefaultMovementRules().createKingRules())
        pieces = pieces + (Position(position.row, position.column) to pieceTranslated)
      }
      if (pieceName == PAWN){
        val pieceTranslated = Piece(pieceTypeTranslated, pieceColorTranslated, false, id, DefaultMovementRules().createPawnRules())
        pieces = pieces + (Position(position.row, position.column) to pieceTranslated)
      }
      if (pieceName == QUEEN){
        val pieceTranslated = Piece(pieceTypeTranslated, pieceColorTranslated, false, id, DefaultMovementRules().createQueenRules())
        pieces = pieces + (Position(position.row, position.column) to pieceTranslated)
      }
      if (pieceName == KNIGHT){
        val pieceTranslated = Piece(pieceTypeTranslated, pieceColorTranslated, false, id, DefaultMovementRules().createKnightRules())
        pieces = pieces + (Position(position.row, position.column) to pieceTranslated)
      }
      if (pieceName == BISHOP){
        val pieceTranslated = Piece(pieceTypeTranslated, pieceColorTranslated, false, id, DefaultMovementRules().createBishopRules())
        pieces = pieces + (Position(position.row, position.column) to pieceTranslated)
      }
      if (pieceName == ROOK){
        val pieceTranslated = Piece(pieceTypeTranslated, pieceColorTranslated, false, id, DefaultMovementRules().createRookRules())
        pieces = pieces + (Position(position.row, position.column) to pieceTranslated)

      }
    }
    val newBoard = DefaultBoard(board.size.rows, board.size.cols, pieces)
    val newGame = Game(newBoard, engine.game.turn, mapOf(), engine.game.ruleManager)
    engine = ChessEngine(newGame)
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

  fun pieceCreator(): Map<Position, Piece> {
    val pieces = mutableMapOf<Position, Piece>()

    for (i in 1..8){
      pieces[Position(2, i)] = Piece(ChessPieceType.PAWN, Color.WHITE, false, ChessPieceType.PAWN.string, DefaultMovementRules().createPawnRules())
      pieces[Position(7, i)] = Piece(ChessPieceType.PAWN, Color.BLACK, false, ChessPieceType.PAWN.string, DefaultMovementRules().createPawnRules())
    }

    pieces[Position(1, 1)] = Piece(ChessPieceType.ROOK, Color.WHITE, false, ChessPieceType.ROOK.string, DefaultMovementRules().createRookRules())
    pieces[Position(1, 8)] = Piece(ChessPieceType.ROOK, Color.WHITE, false, ChessPieceType.ROOK.string, DefaultMovementRules().createRookRules())
    pieces[Position(8, 1)] = Piece(ChessPieceType.ROOK, Color.BLACK, false, ChessPieceType.ROOK.string, DefaultMovementRules().createRookRules())
    pieces[Position(8, 8)] = Piece(ChessPieceType.ROOK, Color.BLACK, false, ChessPieceType.ROOK.string, DefaultMovementRules().createRookRules())

    pieces[Position(1, 2)] = Piece(ChessPieceType.KNIGHT, Color.WHITE, false, ChessPieceType.KNIGHT.string, DefaultMovementRules().createKnightRules())
    pieces[Position(1, 7)] = Piece(ChessPieceType.KNIGHT, Color.WHITE, false, ChessPieceType.KNIGHT.string, DefaultMovementRules().createKnightRules())
    pieces[Position(8, 2)] = Piece(ChessPieceType.KNIGHT, Color.BLACK, false, ChessPieceType.KNIGHT.string, DefaultMovementRules().createKnightRules())
    pieces[Position(8, 7)] = Piece(ChessPieceType.KNIGHT, Color.BLACK, false, ChessPieceType.KNIGHT.string, DefaultMovementRules().createKnightRules())

    pieces[Position(1, 3)] = Piece(ChessPieceType.BISHOP, Color.WHITE, false, ChessPieceType.BISHOP.string, DefaultMovementRules().createBishopRules())
    pieces[Position(1, 6)] = Piece(ChessPieceType.BISHOP, Color.WHITE, false, ChessPieceType.BISHOP.string, DefaultMovementRules().createBishopRules())
    pieces[Position(8, 3)] = Piece(ChessPieceType.BISHOP, Color.BLACK, false, ChessPieceType.BISHOP.string, DefaultMovementRules().createBishopRules())
    pieces[Position(8, 6)] = Piece(ChessPieceType.BISHOP, Color.BLACK, false, ChessPieceType.BISHOP.string, DefaultMovementRules().createBishopRules())

    pieces[Position(1, 4)] = Piece(ChessPieceType.QUEEN, Color.WHITE, false, ChessPieceType.QUEEN.string, DefaultMovementRules().createQueenRules())
    pieces[Position(8, 4)] = Piece(ChessPieceType.QUEEN, Color.BLACK, false, ChessPieceType.QUEEN.string, DefaultMovementRules().createQueenRules())

    pieces[Position(1, 5)] = Piece(ChessPieceType.KING, Color.WHITE, false, ChessPieceType.KING.string, DefaultMovementRules().createKingRules())
    pieces[Position(8, 5)] = Piece(ChessPieceType.KING, Color.BLACK, false, ChessPieceType.KING.string, DefaultMovementRules().createKingRules())

    return pieces
  }
}