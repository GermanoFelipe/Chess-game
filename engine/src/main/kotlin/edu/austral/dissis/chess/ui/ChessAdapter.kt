package edu.austral.dissis.chess.ui

import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.game.Game
import edu.austral.dissis.chess.engine.game.TurnManager
import edu.austral.dissis.chess.engine.game.TurnDefault
import edu.austral.dissis.chess.engine.piece.Color
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position
import edu.austral.dissis.chess.engine.game.results.MovementResult
import edu.austral.dissis.chess.engine.game.results.ValidMovement
import edu.austral.dissis.chess.engine.movement.Movement
import edu.austral.dissis.chess.engine.rules.RuleManager
import edu.austral.dissis.chess.gui.*

class ChessAdapter : GameEngine { //chess engine implements GameEngine, adapter in another class
  val board = DefaultBoard(boardSizeAdapter() )
  val ruleManager = RuleManager()
  val game = Game(board, TurnDefault, mapOf<Piece, List<Movement>>(), ruleManager)

  override fun applyMove(move: Move): MoveResult {
    val from = Position(move.from.row, move.from.column)
    val to = Position(move.to.row, move.to.column)
    val result = game.movePiece(move.from, move.to)
    return if (result is ValidMovement) {
      val newPieces = piecesAdapter(Game.board.pieces)
      val newTurn = Game.turn.actualTurn(Color.WHITE)
      val newGameState = NewGameState(newPieces, colorAdapter(newTurn), undoState = undo())
      return MoveResult(newGameState)
    } else {
      invalidMoveAdapter(result)
    }
  }

  override fun init(): InitialState {
    val pieces = mapOf<Position, Piece?>()
    val newSize = boardSizeAdapter(Position(8, 8))
    val newPieces = piecesAdapter(pieces)
    val currentPlayer = colorAdapter(Color.WHITE)
    return InitialState(newSize, newPieces, currentPlayer)
  }

  override fun redo(): NewGameState {
    TODO("Not yet implemented")
  }

  override fun undo(): NewGameState {
    TODO("Not yet implemented")
  }

  fun boardSizeAdapter(position: Position): BoardSize {
    val column = position.row
    val row = position.column
    return BoardSize(column, row)
  }
  fun piecesAdapter(pieces: Map<Position, Piece?>): List<ChessPiece> {
    val chessPieces = mutableListOf<ChessPiece>()
    for (piece in pieces){
      val id = piece.value?.pieceColor.toString() + piece.value?.type.toString()
      val color = colorAdapter(piece.value?.pieceColor!!)
      val position = positionAdapter(piece.key)
      val pieceId = piece.value?.type.toString()
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

  fun invalidMoveAdapter(invalid : MovementResult): MoveResult {
    val message = invalid.getMessage()
    return InvalidMove(message)
  }

//  fun newGameStateAdapter(state: Game): GameState {
//    val newPieces = piecesAdapter(state.pieces)
//    val actualTurn = state.turn.actualTurn(Color.WHITE)
//    val undoState = undo()
//    return NewGameState()
//  }

  fun moveAdapter(from: Position, to: Position): Move {
    val newFrom = positionAdapter(from)
    val newTo = positionAdapter(to)
    return Move(newFrom, newTo)
  }

  fun gameOverAdapter(turn: TurnManager): MoveResult {
    return if (turn.actualTurn(Color.WHITE) == Color.WHITE) GameOver(PlayerColor.WHITE)
    else GameOver(PlayerColor.BLACK)
  }
}