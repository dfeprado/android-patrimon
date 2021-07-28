package dev.danielprado.patrimon.classes

import java.lang.IllegalArgumentException

data class Value(val value: Double) {
    init {
        if (value < 0)
            throw IllegalArgumentException("O valor não pode ser negativo")
    }
}