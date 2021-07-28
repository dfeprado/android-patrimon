package dev.danielprado.patrimon.classes

data class Percentage(val value: Double) {
    val decimalValue: Double = value / 100
    init {
        if (value <= 0)
            throw IllegalArgumentException("Percentage cannot be less or equal than zero")
    }
}
