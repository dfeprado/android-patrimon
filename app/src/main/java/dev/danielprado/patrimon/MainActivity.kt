package dev.danielprado.patrimon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.danielprado.patrimon.databinding.ActivityMainBinding

class Equity(val value: Double) {
    init {
        if (value <= 0)
            throw IllegalArgumentException("Equity cannot be less or equal than zero")
    }
}

class Percentage(val value: Double) {
    val decimalValue: Double = value / 100
    init {
        if (value <= 0)
            throw IllegalArgumentException("Percentage cannot be less or equal than zero")
    }
}

class MainActivity : AppCompatActivity() {
    lateinit private var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    private fun showResult(message: String) {
        viewBinding.txtResult.apply {
            text = message
            visibility = View.VISIBLE
        }
    }

    fun calculate(view: View) {
        try {
            val equity = Equity(viewBinding.editEquity.value)
            val percentage = Percentage(viewBinding.editPercentage.value)
            val result = (equity.value * percentage.decimalValue) / (1 - percentage.decimalValue)
            showResult("Você deve investir R$ $result para obter ${percentage.value}% de representatividade nesse patrimônio")
        }
        catch(e: IllegalArgumentException) {
            showResult(e.message ?: "Insira todos os valores")
        }
    }
}