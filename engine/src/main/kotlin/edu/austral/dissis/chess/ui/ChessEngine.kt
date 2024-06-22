package edu.austral.dissis.chess.ui

import edu.austral.dissis.chess.engine.board.Board
import edu.austral.dissis.chess.engine.board.DefaultBoard
import edu.austral.dissis.chess.engine.game.results.MovementResult
import edu.austral.dissis.chess.engine.movement.piecesMovRules.DefaultMovementRules
import edu.austral.dissis.chess.engine.piece.ChessPieceType
import edu.austral.dissis.chess.engine.piece.Color
import edu.austral.dissis.chess.engine.piece.Piece
import edu.austral.dissis.chess.engine.piece.Position
import edu.austral.dissis.chess.gui.*

class ChessEngine : GameEngine {

  override fun applyMove(move: Move): MoveResult {
    TODO("Not yet implemented")
  }

  override fun init(): InitialState {
    val pieces = pieceCreator()
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

  fun piecesAdapter(pieces: Map<Position, Piece?>): List<ChessPiece> {
    val chessPieces = mutableListOf<ChessPiece>()
    for (piece in pieces){
      val id = piece.key.toString()
      val color = colorAdapter(piece.value?.pieceColor!!)
      val position = positionAdapter(piece.key)
      val pieceId = piece.value!!.id
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
 //   val actualTurn = state.turn.actualTurn(Color.WHITE)
 //   val undoState = undo()
 //   return NewGameState(newPieces, actualTurn, undoState)
 // }

  fun gameOverAdapter(color: Color): MoveResult {
    val winner = colorAdapter(color)
    return GameOver(winner)
  }

  fun moveAdapter(from: Position, to: Position): Move {
    return Move(positionAdapter(from), positionAdapter(to))
  }

  fun initialStateAdapter(state: DefaultBoard): InitialState {
    val newPieces = piecesAdapter(state.pieces)
    //val currentPlayer = colorAdapter(state.turn.actualTurn(Color.WHITE))
    val newSize = boardSizeAdapter(Position(8, 8))
    return InitialState(newSize, newPieces, colorAdapter(Color.WHITE)) //bad color
  }

 // fun undoStateAdapter(state: DefaultBoard): NewGameState {
 //   val newPieces = piecesAdapter(state.pieces)
 //   val currentPlayer = colorAdapter(state.turn.actualTurn(Color.WHITE))
 //   val undoState = undo()
 //   return NewGameState(newPieces, currentPlayer, undoState)
 // }


  fun pieceCreator(): Map<Position, Piece?> {
    val pieces = mutableMapOf<Position, Piece?>()

    for (i in 1..8){
      pieces[Position(2, i)] = Piece(ChessPieceType.PAWN, Color.WHITE, false, ChessPieceType.PAWN.string, DefaultMovementRules())
      pieces[Position(7, i)] = Piece(ChessPieceType.PAWN, Color.BLACK, false, ChessPieceType.PAWN.string, DefaultMovementRules())
    }

    pieces[Position(1, 1)] = Piece(ChessPieceType.ROOK, Color.WHITE, false, ChessPieceType.ROOK.string, DefaultMovementRules())
    pieces[Position(1, 8)] = Piece(ChessPieceType.ROOK, Color.WHITE, false, ChessPieceType.ROOK.string, DefaultMovementRules())
    pieces[Position(8, 1)] = Piece(ChessPieceType.ROOK, Color.BLACK, false, ChessPieceType.ROOK.string, DefaultMovementRules())
    pieces[Position(8, 8)] = Piece(ChessPieceType.ROOK, Color.BLACK, false, ChessPieceType.ROOK.string, DefaultMovementRules())

    pieces[Position(1, 2)] = Piece(ChessPieceType.KNIGHT, Color.WHITE, false, ChessPieceType.KNIGHT.string, DefaultMovementRules())
    pieces[Position(1, 7)] = Piece(ChessPieceType.KNIGHT, Color.WHITE, false, ChessPieceType.KNIGHT.string, DefaultMovementRules())
    pieces[Position(8, 2)] = Piece(ChessPieceType.KNIGHT, Color.BLACK, false, ChessPieceType.KNIGHT.string, DefaultMovementRules())
    pieces[Position(8, 7)] = Piece(ChessPieceType.KNIGHT, Color.BLACK, false, ChessPieceType.KNIGHT.string, DefaultMovementRules())

    pieces[Position(1, 3)] = Piece(ChessPieceType.BISHOP, Color.WHITE, false, ChessPieceType.BISHOP.string, DefaultMovementRules())
    pieces[Position(1, 6)] = Piece(ChessPieceType.BISHOP, Color.WHITE, false, ChessPieceType.BISHOP.string, DefaultMovementRules())
    pieces[Position(8, 3)] = Piece(ChessPieceType.BISHOP, Color.BLACK, false, ChessPieceType.BISHOP.string, DefaultMovementRules())
    pieces[Position(8, 6)] = Piece(ChessPieceType.BISHOP, Color.BLACK, false, ChessPieceType.BISHOP.string, DefaultMovementRules())

    pieces[Position(1, 4)] = Piece(ChessPieceType.QUEEN, Color.WHITE, false, ChessPieceType.QUEEN.string, DefaultMovementRules())
    pieces[Position(8, 4)] = Piece(ChessPieceType.QUEEN, Color.BLACK, false, ChessPieceType.QUEEN.string, DefaultMovementRules())

    pieces[Position(1, 5)] = Piece(ChessPieceType.KING, Color.WHITE, false, ChessPieceType.KING.string, DefaultMovementRules())
    pieces[Position(8, 5)] = Piece(ChessPieceType.KING, Color.BLACK, false, ChessPieceType.KING.string, DefaultMovementRules())


    return pieces
  }
}