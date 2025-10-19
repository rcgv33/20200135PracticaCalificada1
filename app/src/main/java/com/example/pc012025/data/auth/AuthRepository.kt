package com.example.pc012025.data.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    val currentUser get() = auth.currentUser
    val isLoggedIn get() = currentUser != null

    suspend fun login(email: String, password: String) = runCatching {
        auth.signInWithEmailAndPassword(email, password).await()
    }.map { Unit }

    suspend fun register(email: String, password: String) = runCatching {
        auth.createUserWithEmailAndPassword(email, password).await()
    }.map { Unit }

    fun logout() = auth.signOut()
}
