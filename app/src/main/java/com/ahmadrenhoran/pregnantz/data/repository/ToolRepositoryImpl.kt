package com.ahmadrenhoran.pregnantz.data.repository

import com.ahmadrenhoran.pregnantz.domain.model.PanicNumber
import com.ahmadrenhoran.pregnantz.domain.model.Response
import com.ahmadrenhoran.pregnantz.domain.model.Weight
import com.ahmadrenhoran.pregnantz.domain.repository.ToolRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ToolRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseDatabase
) :
    ToolRepository {
    private val weightsRef = db.reference.child("weights")
    private val numbersRef = db.reference.child("panic_numbers")
    private val uid = auth.currentUser?.uid.toString()
    override suspend fun getCurrentWeight(): Response<Float> {
        return try {
            val weight = weightsRef.child(uid).limitToLast(1).get()
                .await().children.mapNotNull { it.getValue<Weight>() }
            Response.Success(weight[0].weightKg)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun insertPanicNumber(number: String): Response<Boolean> {
        return try {
            val newNumberRef = numbersRef.child(uid).push()
            val map: MutableMap<String, Any> = HashMap()
            map["id"] = newNumberRef.key.toString()
            map["number"] = number
            newNumberRef.setValue(map).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun deletePanicNumber(numberId: String): Response<Boolean> {
        return try {
            numbersRef.child(uid).child(numberId).removeValue().await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }

    override suspend fun getListPanicNumbers(): Response<List<PanicNumber>> {
        return try {
            val weight = numbersRef.child(uid).get().await().children.mapNotNull { it.getValue<PanicNumber>() }
            Response.Success(weight)
        } catch (e: Exception) {
            Response.Failure(e)
        }
    }
}