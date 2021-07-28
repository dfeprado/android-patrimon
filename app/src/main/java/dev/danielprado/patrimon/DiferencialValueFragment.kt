package dev.danielprado.patrimon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.danielprado.patrimon.classes.Equity
import dev.danielprado.patrimon.classes.Percentage
import dev.danielprado.patrimon.classes.Value
import dev.danielprado.patrimon.databinding.FragmentDiferencialValueBinding
import java.lang.IllegalArgumentException

class DiferencialValueFragment: Fragment() {
    lateinit private var layout: FragmentDiferencialValueBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layout = FragmentDiferencialValueBinding.inflate(inflater)
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout.fdvBtnCalculate.setOnClickListener { calculate() }
    }

    private fun showResult(msg: String) {
        layout.fdvTextResult.apply {
            visibility = View.VISIBLE
            text = msg
        }
    }

    private fun calculate() {
        try {
            val equity = Equity(layout.fdvEditEquity.value)
            val value = Value(layout.fdvEditValue.value)
            val percentage = Percentage(layout.fdvEditPercentage.value)

            val result = equity.value * percentage.decimalValue - value.value
            showResult("Você precisa movimentar ${if (result > 0) "R$ ${result}" else "-R$ ${result*-1}"} para alcançar a porcentagem de ${percentage.value}%")
        }
        catch(e: IllegalArgumentException) {
            showResult(e.message ?: "Erro desconhecido")
        }
    }
}