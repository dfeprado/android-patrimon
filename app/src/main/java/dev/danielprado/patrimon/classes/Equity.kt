package dev.danielprado.patrimon.classes

data class Equity(val value: Double) {
    init {
        if (value <= 0)
            throw IllegalArgumentException("Equity cannot be less or equal than zero")
    }
}