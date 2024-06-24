package edu.austral.dissis.chess.engine.game.results

sealed interface ValidationResults{
    fun getMessage(): String
}

class InvalidResult : ValidationResults {
    override fun getMessage(): String {
        return "Invalid Movement"
    }
}

class ValidResult: ValidationResults {
    override fun getMessage(): String {
        return "Valid Movement"
    }
}