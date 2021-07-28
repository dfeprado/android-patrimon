package dev.danielprado.patrimon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.danielprado.patrimon.classes.Equity
import dev.danielprado.patrimon.classes.Value
import dev.danielprado.patrimon.databinding.FragmentWeightByValueBinding
import java.lang.IllegalArgumentException
import java.text.DecimalFormat

class WeightByValueFragment: Fragment() {
    lateinit private var layoutBinding: FragmentWeightByValueBinding
    private val formatter = DecimalFormat("0.00")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutBinding = FragmentWeightByValueBinding.inflate(inflater)
        return layoutBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutBinding.wbvBtnCalculate.setOnClickListener { calculate() }
    }

    private fun showResult(msg: String) {
        layoutBinding.wbvResultText.apply {
            visibility = View.VISIBLE
            text = msg
        }
    }

    private fun calculate() {
        try {
            val equity = Equity(layoutBinding.wbyEditEquity.value)
            val value = Value(layoutBinding.wbvEdtValue.value)

            val result = (value.value/(value.value + equity.value))*100
            showResult("Esse ativo terá uma representatividade de ${formatter.format(result)}% frente a esse patrimônio")
        }
        catch(e: IllegalArgumentException) {
            showResult(e.message ?: "Erro desconhecido")
        }
    }
}