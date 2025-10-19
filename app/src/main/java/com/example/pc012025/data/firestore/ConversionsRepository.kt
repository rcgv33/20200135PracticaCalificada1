package com.example.pc012025.data.firestore


import com.example.pc012025.domain.model.Conversion
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ConversionsRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val col = db.collection("conversions")

    suspend fun save(c: Conversion) { col.add(c).await() }

    suspend fun byUser(uid: String): List<Conversion> =
        col.whereEqualTo("uid", uid).orderBy("timestamp")
            .get().await().documents.mapNotNull { it.toObject(Conversion::class.java) }
}
