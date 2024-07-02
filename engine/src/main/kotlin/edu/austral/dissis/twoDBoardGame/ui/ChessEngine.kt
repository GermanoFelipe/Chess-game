package edu.austral.dissis.twoDBoardGame.ui

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.results.UnsuccessfulMovementResult
import edu.austral.dissis.twoDBoardGame.results.SuccessfulMovementResult
import edu.austral.dissis.twoDBoardGame.results.WinnerResult
import java.util.Stack

class ChessEngine (private var game: Game): GameEngine {

  private var undoStack = Stack<Game>()

  private var redoStack = Stack<Game>()

  override fun applyMove(move: Move): MoveResult {
    val from = Position(move.from.row, move.from.column)
    val to = Position(move.to.row, move.to.column)

    return when (val result = game.movePiece(from, to)) {
      is SuccessfulMovementResult -> { undoStack.push(game)
        redoStack.clear()
        getNewGameState(result)
      }
      is UnsuccessfulMovementResult ->  InvalidMove(result.message)

      is WinnerResult -> GameOver(getPlayerColor(result.winner))
    }
  }

  override fun init(): InitialState {
    return InitialState(
      getBoardSize(),
      getPieces(),
      getCurrentPlayerColor())
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

  //Adapter methods

  private fun getBoardSize(): BoardSize {
    val board = game.getBoard()
    return BoardSize(board.getColumn(), board.getRow())
  }

  private fun getPieces(): List<ChessPiece>{
    val board = game.getBoard()
    return toChessPieceAdapter(board.getUsedPositions(), board)
  }

  private fun toChessPieceAdapter(positions: List<Position>, board: DefaultBoard): List<ChessPiece> {
    return positions.map {
      val piece = board.getPiece(it)!!
      ChessPiece(
        getId(piece),
        getPiecePlayerColor(piece),
        getPiecePosition(it),
        getPieceType(piece))
    }
  }

  private fun getPieceType (piece: Piece): String{
    return piece.getType().string()
  }

  private fun getPiecePlayerColor(piece: Piece) =
    if (piece.getColor() == Color.WHITE) PlayerColor.WHITE
    else PlayerColor.BLACK

  private fun getPlayerColor(color: Color) =
    if (color == Color.WHITE) PlayerColor.WHITE
    else PlayerColor.BLACK

  private fun getId (piece:Piece) = piece.getId()

  private fun getPiecePosition (position: Position) = edu.austral.dissis.chess.gui.Position(position.row, position.column)

  private fun getCurrentPlayerColor(): PlayerColor {
    return if (game.getTurn() == Color.WHITE) PlayerColor.WHITE
    else PlayerColor.BLACK
  }

  // MoveResult adapters


private fun getNewGameState(state: SuccessfulMovementResult): MoveResult {
  game = state.game
  return NewGameState(
    getPieces(),
    getCurrentPlayerColor(),
    UndoState(canUndo(), canRedo()))
}
  private fun getUpdatedGameState(): NewGameState{
    return NewGameState(
      getPieces(),
      getCurrentPlayerColor(),
      UndoState(canUndo(), canRedo()))
  }

  private fun updateGame(newGame: Game){
    this.game = newGame
  }

  private fun undoMove() {
    if (canUndo()){
      redoStack.push(game)
      val lastState = undoStack.pop()
      updateGame(lastState)
    }
  }

  private fun redoMove() {
    if(canRedo()){
      undoStack.push(game)
      val lastState = redoStack.pop()
      updateGame(lastState)
    }
  }

  private fun canUndo(): Boolean {
    return !undoStack.isEmpty()
  }

  private fun canRedo(): Boolean {
    return !redoStack.isEmpty()
  }
}