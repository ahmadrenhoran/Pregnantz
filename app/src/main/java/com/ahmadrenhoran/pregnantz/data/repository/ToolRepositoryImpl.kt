package com.ahmadrenhoran.pregnantz.data.repository

import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.Weight
import com.ahmadrenhoran.pregnantz.domain.repository.ToolRepository
import com.ahmadrenhoran.pregnantz.domain.repository.WeightRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ToolRepositoryImpl @Inject constructor(private val auth: FirebaseAuth, private val db: FirebaseDatabase):
    ToolRepository {
    private val weightsRef = db.reference.child("weights")
    private val uid = auth.currentUser?.uid.toString()
    override suspend fun getCurrentWeight(): Response<String> {
        return try {
            val weight = weightsRef.child(uid).limitToLast(1).get().await().children.mapNotNull { it.getValue<Weight>() }
            Response.Success(weight[0].weightKg.toString())
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}