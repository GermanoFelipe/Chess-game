package edu.austral.dissis.chess.ui

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.game.Game
import edu.austral.dissis.chess.engine.game.TurnDefault
import edu.austral.dissis.chess.engine.game.results.MovementResult
import edu.austral.dissis.chess.engine.game.results.ValidMovement
import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.movement.piecesMovRules.DefaultMovementRules
import edu.austral.dissis.chess.engine.piece.ChessPieceType
import edu.austral.dissis.chess.engine.piece.Color
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position
import edu.austral.dissis.chess.engine.rules.ChessRuleManager
import edu.austral.dissis.chess.engine.rules.RuleManager
import edu.austral.dissis.chess.gui.*
import java.util.Stack

class ChessEngine (var game: Game) : GameEngine {


  val undoStack = Stack<Game>()

  val redoStack = Stack<Game>()


  override fun applyMove(move: Move): MoveResult {
    val from = Position(move.from.row, move.from.column)
    val to = Position(move.to.row, move.to.column)

    //val moveAdapted = moveAdapter(from, to)
    val result = game.movePiece(from, to)

    return if (result is ValidMovement) {
      val newPieces = piecesAdapter(game.board.getPieces())

      val newTurn = game.turn.nextTurn()

      return NewGameState(
        newPieces,
        colorAdapter(newTurn.actualTurn()),
        UndoState(canUndo(), canRedo())
      )
    } else {
      invalidMoveAdapter(result)
    }
  }

  override fun init(): InitialState {
    val pieces = pieceCreator()
    val newSize = boardSizeAdapter(Position(8, 8))
    val newPieces = piecesAdapter(pieces)
    val currentPlayer = colorAdapter(Color.WHITE)

    return InitialState(newSize, newPieces, currentPlayer)
  }


  override fun redo(): NewGameState {
    redoMove()
    val result = game
    return NewGameState(piecesAdapter(result.board.getPieces()), colorAdapter(result.turn.actualTurn()), UndoState(canUndo(), canRedo()))
  }

  override fun undo(): NewGameState {
    undoMove()
    val result = game
    return NewGameState(piecesAdapter(result.board.getPieces()), colorAdapter(result.turn.actualTurn()), UndoState(canUndo(), canRedo()))
  }


  //Adapter methods
  

  fun colorAdapter(color: Color): PlayerColor {
    return when(color){
      Color.WHITE -> PlayerColor.WHITE
      Color.BLACK -> PlayerColor.BLACK
    }
  }

  fun boardSizeAdapter(position: Position): BoardSize {
    val column = position.row
    val row = position.column
    return BoardSize(column, row)
  }

  fun positionAdapter(position: Position): edu.austral.dissis.chess.gui.Position {
    val row = position.row
    val column = position.column
    return edu.austral.dissis.chess.gui.Position(row, column)
  }

  fun piecesAdapter(pieces: Map<Position, Piece>): List<ChessPiece> {
    val chessPieces = mutableListOf<ChessPiece>()
    for (piece in pieces){
      val id = piece.key.toString()
      val color = colorAdapter(piece.value.pieceColor)
      val position = positionAdapter(piece.key)
      val pieceId = piece.value.id
      chessPieces.add(ChessPiece(id, color, position, pieceId))
    }
    return chessPieces
  }

  // MoveResult adapters

  fun invalidMoveAdapter(invalid : MovementResult): MoveResult {
    val message = invalid.getMessage()
    return InvalidMove(message)
  }

// fun newGameStateAdapter(state: DefaultBoard): NewGameState {
//   val newPieces = piecesAdapter(state.pieces)
//   val actualTurn = colorAdapter(state.turn.actualTurn()
//   val undoState = undo()
//   return NewGameState(newPieces, actualTurn, UndoState(canUndo(), canRedo()))
// }

  fun gameOverAdapter(color: Color): MoveResult {
    val winner = colorAdapter(color)
    return GameOver(winner)
  }

  fun moveAdapter(from: Position, to: Position): Move {
    return Move(positionAdapter(from), positionAdapter(to))
  }

  fun initialStateAdapter(state: DefaultBoard): InitialState {
    val newPieces = piecesAdapter(state.getPieces())
    //val currentPlayer = colorAdapter(state.turn.actualTurn(Color.WHITE))
    val newSize = boardSizeAdapter(Position(8, 8))
    return InitialState(newSize, newPieces, colorAdapter(Color.WHITE)) //bad color
  }

  fun undoMove() {
    redoStack.push(game)
    val lastState = undoStack.pop()
    game = lastState
  }

  fun redoMove() {
    undoStack.push(game)
    val lastState = redoStack.pop()
    game = lastState
  }

  fun canUndo(): Boolean {
    return !undoStack.isEmpty()
  }

  fun canRedo(): Boolean {
    return !redoStack.isEmpty()
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