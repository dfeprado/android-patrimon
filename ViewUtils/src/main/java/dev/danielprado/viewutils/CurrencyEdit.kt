package dev.danielprado.viewutils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import java.text.DecimalFormat

public class CurrencyEdit: AppCompatEditText {
    var value: Double = 0.0
        private set
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        textAlignment = View.TEXT_ALIGNMENT_VIEW_END
        addTextChangedListener(object: TextWatcher {
            private val formatter = DecimalFormat("$ 0.00")
            private var isEditing = false
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isEditing || s == null)
                    return

                isEditing = true
                val valueStr = s.toString().replace(Regex("[^0-9]"), "")
                value = if (valueStr.isNotEmpty()) valueStr.toDouble()/100 else 0.0
                s.clear()
                s.append(formatter.format(value))
                isEditing = false
            }
        })
    }
}