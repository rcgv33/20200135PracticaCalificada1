package com.example.pc012025.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pc012025.data.firestore.RatesRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RatesViewModel(private val repo: RatesRepository = RatesRepository()) : ViewModel() {
    private val _rates = MutableStateFlow<Map<String,Double>>(emptyMap())
    val rates = _rates.asStateFlow()

    init {
        viewModelScope.launch {
            repo.seedIfEmpty(mapOf("USD" to 1.0, "EUR" to 0.925, "PEN" to 3.75, "GBP" to 0.79, "JPY" to 156.0))
            _rates.value = repo.getRates()
        }
    }
}
