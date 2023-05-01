package com.ahmadrenhoran.pregnantz.data.repository

import android.util.Log
import com.ahmadrenhoran.pregnantz.core.Constants
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.Weight
import com.ahmadrenhoran.pregnantz.domain.repository.WeightRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class WeightRepositoryImpl @Inject constructor(private val auth: FirebaseAuth, private val db: FirebaseDatabase): WeightRepository {
    private val weightsRef = db.reference.child("weights")
    private val uid = auth.currentUser?.uid.toString()

    override suspend fun addWeight(weight: Float): Response<Boolean> {
        return try {

            val newWeightRef = weightsRef.child(uid).push()
            val map: MutableMap<String, Any> = HashMap()
            map["id"] = newWeightRef.key.toString()
            map["weightKg"] = weight
            map["createdAt"] = ServerValue.TIMESTAMP
            newWeightRef.setValue(map).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun delWeight(weightId: String): Response<Boolean> {
        return try {
            weightsRef.child(uid).child(weightId).removeValue().await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getWeightHistory(): Response<List<Weight>> {
        return try {
            val weights = weightsRef.child(uid).get().await().children.mapNotNull { it.getValue<Weight>() }
            Response.Success(weights)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

}