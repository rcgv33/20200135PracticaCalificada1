package com.example.pc012025.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pc012025.data.firestore.ConversionsRepository
import com.example.pc012025.domain.model.Conversion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.round

data class ConversionUi(val amountText:String="", val from:String="USD", val to:String="EUR", val resultText:String="")

class ConversionViewModel(private val repo: ConversionsRepository = ConversionsRepository()) : ViewModel() {
    private val _ui = MutableStateFlow(ConversionUi())
    val ui = _ui.asStateFlow()

    fun updateAmount(t:String){ _ui.value = _ui.value.copy(amountText = t) }
    fun updateFrom(c:String){ _ui.value = _ui.value.copy(from = c) }
    fun updateTo(c:String){ _ui.value = _ui.value.copy(to = c) }

    fun convertAndSave(uid:String, rates: Map<String,Double>) = viewModelScope.launch {
        val amount = ui.value.amountText.toDoubleOrNull() ?: 0.0
        val res = amount * ((rates[ui.value.to] ?: return@launch) / (rates[ui.value.from] ?: return@launch))
        val rounded = (round(res * 100.0) / 100.0)
        _ui.value = _ui.value.copy(resultText = "${amount} ${ui.value.from} equivalen a $rounded ${ui.value.to}")
        repo.save(
            Conversion(
                uid = uid,
                amount = amount,
                from = ui.value.from,
                to = ui.value.to,
                result = rounded
            )
        )
    }
}
