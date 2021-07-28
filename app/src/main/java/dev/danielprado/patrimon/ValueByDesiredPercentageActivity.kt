package dev.danielprado.patrimon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.danielprado.patrimon.classes.Equity
import dev.danielprado.patrimon.classes.Percentage
import dev.danielprado.patrimon.databinding.ActivityValueByDesiredPercentageBinding
import java.text.DecimalFormat

class ValueByDesiredPercentageActivity: Fragment() {
    private val decimalFormatter = DecimalFormat("R$ 0.00")
    lateinit private var layoutBinding: ActivityValueByDesiredPercentageBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutBinding = ActivityValueByDesiredPercentageBinding.inflate(inflater)
        return layoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutBinding.btnCalculate.setOnClickListener {
            calculate()
        }
    }

    private fun showResult(message: String) {
        layoutBinding.txtResult.apply {
            text = message
            visibility = View.VISIBLE
        }
    }

    private fun calculate() {
        try {
            val equity = Equity(layoutBinding.editEquity.value)
            val percentage = Percentage(layoutBinding.editPercentage.value)
            val result = (equity.value * percentage.decimalValue) / (1 - percentage.decimalValue)
            showResult("Você deve investir ${decimalFormatter.format(result)} para obter ${percentage.value}% de representatividade nesse patrimônio")
        }
        catch(e: IllegalArgumentException) {
            showResult(e.message ?: "Insira todos os valores")
        }
    }
}