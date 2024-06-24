package edu.austral.dissis.chess.ui

import edu.austral.dissis.chess.engine.factory.createDefaultChess
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.twoDBoardGame.board.Board
import edu.austral.dissis.twoDBoardGame.results.UnsuccessfullMovementResult
import edu.austral.dissis.twoDBoardGame.results.SuccessfullMovementResult
import edu.austral.dissis.twoDBoardGame.results.WinnerResult
import java.util.Stack

class ChessEngine: GameEngine {

  var game = createDefaultChess()

  val undoStack = Stack<Game>()

  val redoStack = Stack<Game>()

  init {
    undoStack.push(game)
  }

  override fun applyMove(move: Move): MoveResult {
    val from = Position(move.from.row, move.from.column)
    val to = Position(move.to.row, move.to.column)

    return when (val result = game.movePiece(from, to)) {
      is SuccessfullMovementResult -> { undoStack.push(game)
        redoStack.clear()
        getNewGameState(result)
      }
      is UnsuccessfullMovementResult -> { InvalidMove(result.message) }

      is WinnerResult -> { GameOver(colorAdapter(result.winner)) }
    }
  }

  override fun init(): InitialState {
    return InitialState(boardSizeAdapter(),
      getPieces(),
      getActualPlayerColor())
  }


  override fun redo(): NewGameState {
    return if(canRedo()){
      redoMove()
      getUpdatedGameState()
    }else
      getUpdatedGameState()
  }

  override fun undo(): NewGameState {
    return if(canUndo()){
      undoMove()
      getUpdatedGameState()
    }else
      getUpdatedGameState()
  }

  private fun getUpdatedGameState(): NewGameState {
    return NewGameState(getPieces(), getActualPlayerColor(), UndoState(canUndo(), canRedo()))
  }


  //Adapter methods


  fun boardSizeAdapter(): BoardSize {
    val board = game.getBoard()
    return BoardSize(board.getColumn(), board.getRow())
  }

  fun piecesAdapter(positions: List<Position>, board: Board): List<ChessPiece> {
    return positions.map {
      val piece = board.getPiece(it)!!
      ChessPiece(
        getId(piece),
        getPiecePlayerColor(piece),
        getPiecePosition(it),
        getPieceType(piece))
    }
  }

  fun getPieces(): List<ChessPiece>{
    val board = game.getBoard()
    return piecesAdapter(board.getUsedPositions(), board)
  }

  fun colorAdapter(color: Color): PlayerColor {
    return when(color){
      Color.WHITE -> PlayerColor.WHITE
      Color.BLACK -> PlayerColor.BLACK
    }
  }

  fun getPiecePlayerColor(piece: Piece): PlayerColor {
    if (piece.pieceColor == Color.WHITE) return PlayerColor.WHITE
    return PlayerColor.BLACK
  }
  fun getId (piece:Piece) = piece.getId()

  fun getPiecePosition (position: Position) = edu.austral.dissis.chess.gui.Position(position.row, position.column)

  fun getPieceType (piece: Piece): String{
    return piece.type.string()
  }

  fun getActualPlayerColor(): PlayerColor {
    if (game.turn.actualTurn() == Color.WHITE) return PlayerColor.WHITE
    return PlayerColor.BLACK
  }

  // MoveResult adapters


fun getNewGameState(state: SuccessfullMovementResult): MoveResult {
  game = state.game
  return NewGameState(getPieces(), getActualPlayerColor(), UndoState(canUndo(), canRedo()))
}

  fun updateGame(newGame: Game){
    this.game = newGame
  }

  fun undoMove() {
    if (canUndo()){
      redoStack.push(game)
      val lastState = undoStack.pop()
      updateGame(lastState)
    }
  }

  fun redoMove() {
    if(canRedo()){
      undoStack.push(game)
      val nextState = redoStack.pop()
      updateGame(nextState)
    }
  }

  fun canUndo(): Boolean {
    return !undoStack.isEmpty()
  }

  fun canRedo(): Boolean {
    return !redoStack.isEmpty()
  }
}