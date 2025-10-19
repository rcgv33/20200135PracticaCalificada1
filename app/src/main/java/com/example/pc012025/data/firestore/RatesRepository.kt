package com.example.pc012025.data.firestore

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RatesRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val doc = db.collection("rates").document("base")

    suspend fun getRates(): Map<String, Double> {
        val snap = doc.get().await()
        return snap.data?.filter { it.key != "base" }?.mapValues { it.value.toString().toDouble() }
            ?: emptyMap()
    }

    // solo para inicializar si está vacío
    suspend fun seedIfEmpty(defaults: Map<String, Double>) {
        val snap = doc.get().await()
        if (!snap.exists()) {
            val payload = defaults.toMutableMap()
            payload["base"] = "USD"
            doc.set(payload).await()
        }
    }
}
