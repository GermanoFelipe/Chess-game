package edu.austral.dissis.twoDBoardGame.board

class Size (private val columns: Int, private val rows: Int){

  fun getColumns(): Int {
    return columns
  }

  fun getRows(): Int {
    return rows
  }
}