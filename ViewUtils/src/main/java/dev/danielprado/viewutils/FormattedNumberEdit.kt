package dev.danielprado.viewutils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import java.text.DecimalFormat

class FormattedNumberEdit(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    var value: Double = 0.0
        private set

    var symbol: String = ""
    var symbolPrefix: Boolean = false

    private val textWatcher = object:TextWatcher {
        private var isEditing = false
        val formatter = DecimalFormat("0.00")
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (isEditing || s == null)
                return

            isEditing = true
            val valueStr = s.toString().replace(Regex("[^0-9]"), "")
            value = if (valueStr.isNotEmpty()) valueStr.toDouble()/100 else 0.0
            s.clear()
            s.append(if (symbolPrefix) "${symbol}${formatter.format(value)}" else "${formatter.format(value)}${symbol}")
            isEditing = false
        }
    }

    private fun applyNewFormat(newFormat: String) {
        textWatcher.formatter.applyPattern(newFormat)
    }

    init {
        addTextChangedListener(textWatcher)

        context.theme.obtainStyledAttributes(attrs, R.styleable.FormattedNumberEdit, 0, 0).apply {
            try {
                symbol = getString(R.styleable.FormattedNumberEdit_formattingSymbol) ?: ""
                symbolPrefix = getInteger(R.styleable.FormattedNumberEdit_formattingSymbolPosition, 0) == 0
            }
            finally {
                recycle()
            }
        }
    }
}