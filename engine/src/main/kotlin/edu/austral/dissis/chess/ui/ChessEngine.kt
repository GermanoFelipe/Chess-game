package edu.austral.dissis.chess.ui

import edu.austral.dissis.checkers.factory.createDefaultCheckers
import edu.austral.dissis.chess.engine.factory.createDefaultChess
import edu.austral.dissis.chess.engine.factory.createVariantChess
import edu.austral.dissis.twoDBoardGame.game.Game
import edu.austral.dissis.twoDBoardGame.piece.Color
import edu.austral.dissis.twoDBoardGame.piece.Piece
import edu.austral.dissis.twoDBoardGame.position.Position
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.twoDBoardGame.board.DefaultBoard
import edu.austral.dissis.twoDBoardGame.results.UnsuccessfullMovementResult
import edu.austral.dissis.twoDBoardGame.results.SuccessfullMovementResult
import edu.austral.dissis.twoDBoardGame.results.WinnerResult
import java.util.Stack

class ChessEngine: GameEngine {

  //private var game = createDefaultChess()

  private var game = createDefaultCheckers()

  //private var game = createVariantChess()

  private var undoStack = Stack<Game>()

  private var redoStack = Stack<Game>()

  fun getGame(): Game{
    return game
  }

//  init {
//    undoStack.push(game)
//  }

  override fun applyMove(move: Move): MoveResult {
    val from = Position(move.from.row, move.from.column)
    val to = Position(move.to.row, move.to.column)

    return when (val result = game.movePiece(from, to)) {
      is SuccessfullMovementResult -> { undoStack.push(game)
        redoStack.clear()
        getNewGameState(result)
      }
      is UnsuccessfullMovementResult ->  InvalidMove(result.message)

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

  fun getBoardSize(): BoardSize {
    val board = game.getBoard()
    return BoardSize(board.getColumn(), board.getRow())
  }

  fun getPieces(): List<ChessPiece>{
    val board = game.getBoard()
    return toChessPieceAdapter(board.getUsedPositions(), board)
  }

  fun toChessPieceAdapter(positions: List<Position>, board: DefaultBoard): List<ChessPiece> {
    return positions.map {
      val piece = board.getPiece(it)!!
      ChessPiece(
        getId(piece),
        getPiecePlayerColor(piece),
        getPiecePosition(it),
        getPieceType(piece))
    }
  }

  fun getPieceType (piece: Piece): String{
    return piece.type.string()
  }

  fun getPiecePlayerColor(piece: Piece) =
    if (piece.pieceColor == Color.WHITE) PlayerColor.WHITE
    else PlayerColor.BLACK

  fun getPlayerColor(color: Color) =
    if (color == Color.WHITE) PlayerColor.WHITE
    else PlayerColor.BLACK

  fun getId (piece:Piece) = piece.getId()

  fun getPiecePosition (position: Position) = edu.austral.dissis.chess.gui.Position(position.row, position.column)

  fun getCurrentPlayerColor(): PlayerColor {
    return if (game.getTurn() == Color.WHITE) PlayerColor.WHITE
    else PlayerColor.BLACK
  }

  // MoveResult adapters


fun getNewGameState(state: SuccessfullMovementResult): MoveResult {
  game = state.game
  return NewGameState(
    getPieces(),
    getCurrentPlayerColor(),
    UndoState(canUndo(), canRedo()))
}
  fun getUpdatedGameState(): NewGameState{
    return NewGameState(
      getPieces(),
      getCurrentPlayerColor(),
      UndoState(canUndo(), canRedo()))
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
      val lastState = redoStack.pop()
      updateGame(lastState)
    }
  }

  fun canUndo(): Boolean {
    return !undoStack.isEmpty()
  }

  fun canRedo(): Boolean {
    return !redoStack.isEmpty()
  }
}