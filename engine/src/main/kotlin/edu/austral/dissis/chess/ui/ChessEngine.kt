package edu.austral.dissis.chess.ui

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.factory.createDefaultBoard
import edu.austral.dissis.chess.engine.game.TurnDefault
import edu.austral.dissis.chess.engine.winCondition.CheckMate
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.results.MovementResult
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.twoDBoardGame.game.mover.DefaultMovApplier
import edu.austral.dissis.twoDBoardGame.results.InvalidMovement
import edu.austral.dissis.twoDBoardGame.results.ValidMovement
import edu.austral.dissis.twoDBoardGame.results.WinnerResult
import edu.austral.dissis.twoDBoardGame.rules.RuleManager
import java.util.Stack

class ChessEngine () : GameEngine {

  val board = createDefaultBoard()
  val turn = TurnDefault(Color.WHITE)
  val rules = mutableListOf<RuleManager>()
  val winCondition = CheckMate()
  val movementApplier = DefaultMovApplier()

  var game = Game(board, turn, rules, emptyMap(), winCondition, movementApplier)


  val undoStack = Stack<Game>()

  val redoStack = Stack<Game>()


  override fun applyMove(move: Move): MoveResult {
    val from = Position(move.from.row, move.from.column)
    val to = Position(move.to.row, move.to.column)

    val result = game.movePiece(from, to)

    return when (result) {
      is ValidMovement -> {
        undoStack.push(game)
        redoStack.clear()
        newGameStateAdapter(result)
      }
      is InvalidMovement -> {
        invalidMoveAdapter(result)
      }
      is WinnerResult -> {
        gameOverAdapter(Color.WHITE)
      }
    }
  }

  override fun init(): InitialState {
    val pieces = game.board.getPieces()
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


  fun boardSizeAdapter(position: Position): BoardSize {
    val column = position.row
    val row = position.column
    return BoardSize(column, row)
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

  fun colorAdapter(color: Color): PlayerColor {
    return when(color){
      Color.WHITE -> PlayerColor.WHITE
      Color.BLACK -> PlayerColor.BLACK
    }
  }

  fun positionAdapter(position: Position): edu.austral.dissis.chess.gui.Position {
    val row = position.row
    val column = position.column
    return edu.austral.dissis.chess.gui.Position(row, column)
  }

  // MoveResult adapters

  fun invalidMoveAdapter(invalid : MovementResult): MoveResult {
    val message = invalid.getMessage()
    return InvalidMove(message)
  }

fun newGameStateAdapter(state: ValidMovement): MoveResult {
  game = state.game
  return NewGameState(piecesAdapter(game.board.getPieces()), colorAdapter(game.turn.actualTurn()), UndoState(canUndo(), canRedo()))
}

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
}