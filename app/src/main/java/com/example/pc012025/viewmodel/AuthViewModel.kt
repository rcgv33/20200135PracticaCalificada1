package com.example.pc012025.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pc012025.data.auth.AuthRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class AuthState(val isLoading:Boolean=false, val isLoggedIn:Boolean=false, val error:String?=null)

class AuthViewModel(private val repo: AuthRepository = AuthRepository()) : ViewModel() {
    private val _state = MutableStateFlow(AuthState(isLoggedIn = repo.isLoggedIn))
    val state = _state.asStateFlow()
    val uid get() = repo.currentUser?.uid

    fun login(email:String, pass:String) = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true, error = null)
        val r = repo.login(email, pass)
        _state.value = if (r.isSuccess) AuthState(isLoggedIn = true)
        else AuthState(error = r.exceptionOrNull()?.message)
    }

    fun register(email:String, pass:String) = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true, error = null)
        val r = repo.register(email, pass)
        _state.value = if (r.isSuccess) AuthState(isLoggedIn = true)
        else AuthState(error = r.exceptionOrNull()?.message)
    }

    fun logout(){ repo.logout(); _state.value = AuthState() }
}
