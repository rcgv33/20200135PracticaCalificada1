package com.example.pc012025.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pc012025.data.firestore.ConversionsRepository
import com.example.pc012025.domain.model.Conversion

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HistoryViewModel(private val repo: ConversionsRepository = ConversionsRepository()) : ViewModel() {
    private val _items = MutableStateFlow<List<Conversion>>(emptyList())
    val items = _items.asStateFlow()

    fun load(uid:String) = viewModelScope.launch { _items.value = repo.byUser(uid) }
    fun fmt(ts:Long) = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(ts))
}
