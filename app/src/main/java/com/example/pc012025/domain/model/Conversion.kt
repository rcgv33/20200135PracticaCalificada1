package com.example.pc012025.domain.model


data class Conversion(
    val uid: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val amount: Double = 0.0,
    val from: String = "USD",
    val to: String = "EUR",
    val result: Double = 0.0
)
